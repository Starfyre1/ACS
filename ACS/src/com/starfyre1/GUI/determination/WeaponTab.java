/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WeaponTab extends DeterminationTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";	//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																	//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "To learn or improve a weapon proficiency:";												//$NON-NLS-1$
	private static final String	SELECT_WEAPON_PROFICIENCY		= "Select Weapon Proficiency";																//$NON-NLS-1$
	private static final String	COST_TEXT						= "Cost: 40";																				//$NON-NLS-1$
	private static final String	MAINTENANCE_TEXT				= "Maintain: 1 DP";																			//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TEXT			= WEAPON_PROFICIENCY_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP					= "1D20 < (Dexerity)";																		//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1					= "Success: 1D20 < ";																		//$NON-NLS-1$

	static final String			PROFICIENCY_TITLE				= "Weapon";																					//$NON-NLS-1$
	private static final String	SELECT_WEAPON					= "Select Weapon";																			//$NON-NLS-1$
	private static final int	COST							= 40;

	private static final String	SAVE							= "Save";																					//$NON-NLS-1$
	private static final String	CANCEL							= "Cancel";																					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOkButton;
	private JButton				mCancelButton;

	private TKPopupMenu			mWeaponPopup;
	private TKPopupMenu			mTeacherPopup;
	private JTextField			mDPPerWeekField;
	private JLabel				mDPSpentLabel;
	private JLabel				mBonusLabel;
	private JLabel				mMaintLabel;
	private JLabel				mSuccessfulLabel;
	private JLabel				mStartDateLabel;
	private JLabel				mEndDateLabel;

	private JPanel				mWeaponColumn[];
	private JPanel				mTeacherColumn[];
	private JPanel				mDPPerWeekColumn[];
	private JPanel				mDPSpentColumn[];
	private JPanel				mBonusAmountColumn[];
	private JPanel				mMaintColumn[];
	private JPanel				mSuccessfulColumn[];
	private JPanel				mStartDateColumn[];
	private JPanel				mEndDateColumn[];

	private JDialog				mNewEntryDialog;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WeaponTab}.
	 *
	 * @param owner
	 */
	public WeaponTab(Object owner) {
		super(owner, WEAPON_PROFICIENCY_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), SELECT_WEAPON_PROFICIENCY, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				TeacherDeterminationRecord teacherRecord = DeterminationList.getWeaponsTeacherRecord(mTeacherPopup.getSelectedItem());
				int teacherID = 0;
				if (teacherRecord != null) {
					teacherID = teacherRecord.getID();
				}

				String startDate = CampaignDateChooser.getCampaignDate();
				WeaponDeterminationRecord record = new WeaponDeterminationRecord(mWeaponPopup.getSelectedItem(), teacherID, TKStringHelpers.getIntValue(mBonusLabel.getText(), 0), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), COST, startDate, startDate);
				DeterminationList.addWeaponRecord(record);
				((DeterminationDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
		} else if (source instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) source;
			if (mTeacherPopup == getPopup(menuItem)) { //.getParent()) {
				updateBonusValueFromTeacher(menuItem);
			} else {
				updateTeacherPopup(menuItem);
			}
			updateEnabledState();
		}
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		mOkButton = TKComponentHelpers.createButton(SAVE, this);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);

		panel.add(mOkButton);
		panel.add(mCancelButton);

		return panel;
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		mWeaponPopup.getMenu().setEnabled(headerRecord == null ? false : headerRecord.getCharacterClass() != null);

		boolean enable = mWeaponPopup.getMenu().isEnabled() && mWeaponPopup.getSelectedItem() != SELECT_WEAPON;
		mTeacherPopup.getMenu().setEnabled(enable);

		mDPPerWeekField.setEnabled(mTeacherPopup.getSelectedItem() != TeacherTab.SELECT_TEACHER);
		mDPPerWeekField.setEditable(mTeacherPopup.getSelectedItem() != TeacherTab.SELECT_TEACHER);
	}

	// This is if the Proficiency has just been set
	private void updateTeacherPopup(JMenuItem menuItem) {
		if (!menuItem.getText().equals(SELECT_WEAPON)) {
			// DW probably should remove ActionListener from JMenuItem's in mTeacherPopup's
			//			mTeacherPopup = new TKPopupMenu(TeacherTab.getTeacherPopup(this));
			//			Dimension size3 = new Dimension(mTeacherPopup.getPreferredSize().width, TEXT_FIELD_HEIGHT);
			//			mTeacherPopup.setMinimumSize(size3);
			//			mTeacherPopup.setPreferredSize(size3);
			mTeacherPopup.setEnabled(true);
		}
	}

	// This is if the teacher has just been set
	private void updateBonusValueFromTeacher(JMenuItem menuItem) {
		if (!menuItem.getText().equals(TeacherTab.SELECT_TEACHER)) {
			TeacherDeterminationRecord teacherRecord = DeterminationList.getWeaponsTeacherRecord(menuItem.getText());
			if (teacherRecord != null) {
				String bonus;
				if (teacherRecord.getExpertise().equals(mWeaponPopup.getSelectedItem())) {
					bonus = String.valueOf(teacherRecord.getBonus());
				} else {
					bonus = "0"; //$NON-NLS-1$
				}
				mBonusLabel.setText(bonus);
				// DW update Bonus
			}
		}
	}

	private TKPopupMenu getPopup(JMenuItem menuItem) {

		JPopupMenu popup = (JPopupMenu) menuItem.getParent();
		JMenu menu = (JMenu) popup.getInvoker();
		TKPopupMenu popup3;
		if (menu.getParent() instanceof TKPopupMenu) {
			popup3 = (TKPopupMenu) menu.getParent();
		} else {
			JPopupMenu popup2 = (JPopupMenu) menu.getParent();
			ComboMenu menu2 = (ComboMenu) popup2.getInvoker();
			popup3 = (TKPopupMenu) menu2.getParent();
		}

		return popup3;
	}

	public void updateDisplay() {
		loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		mWeaponColumn = new JPanel[2];
		mTeacherColumn = new JPanel[2];
		mDPPerWeekColumn = new JPanel[2];
		mDPSpentColumn = new JPanel[2];
		mBonusAmountColumn = new JPanel[2];
		mMaintColumn = new JPanel[2];
		mSuccessfulColumn = new JPanel[2];
		mStartDateColumn = new JPanel[2];
		mEndDateColumn = new JPanel[2];

		return createPage(createCenterPanel(false), createCenterPanel(true), WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel(boolean complete) {

		int test = complete ? 1 : 0;

		JPanel outerWrapper = new JPanel();
		mWeaponColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mBonusAmountColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders(complete);

		outerWrapper.add(mWeaponColumn[test]);
		outerWrapper.add(mTeacherColumn[test]);
		outerWrapper.add(mBonusAmountColumn[test]);
		outerWrapper.add(mDPPerWeekColumn[test]);
		outerWrapper.add(mDPSpentColumn[test]);
		outerWrapper.add(mMaintColumn[test]);
		outerWrapper.add(mSuccessfulColumn[test]);
		outerWrapper.add(mStartDateColumn[test]);
		outerWrapper.add(mEndDateColumn[test]);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders(boolean complete) {
		int test = complete ? 1 : 0;

		mWeaponColumn[test].add(new JLabel(PROFICIENCY_TITLE));
		mTeacherColumn[test].add(new JLabel("Teacher")); //$NON-NLS-1$
		mDPPerWeekColumn[test].add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn[test].add(new JLabel("DP Spent")); //$NON-NLS-1$
		mBonusAmountColumn[test].add(new JLabel("Bonus")); //$NON-NLS-1$
		mMaintColumn[test].add(new JLabel("Maint")); //$NON-NLS-1$
		mSuccessfulColumn[test].add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn[test].add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn[test].add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mWeaponColumn[0].removeAll();
		mTeacherColumn[0].removeAll();
		mBonusAmountColumn[0].removeAll();
		mDPPerWeekColumn[0].removeAll();
		mDPSpentColumn[0].removeAll();
		mSuccessfulColumn[0].removeAll();
		mMaintColumn[0].removeAll();
		mStartDateColumn[0].removeAll();
		mEndDateColumn[0].removeAll();
		generateHeaders(false);

		mWeaponColumn[1].removeAll();
		mTeacherColumn[1].removeAll();
		mBonusAmountColumn[1].removeAll();
		mDPPerWeekColumn[1].removeAll();
		mDPSpentColumn[1].removeAll();
		mSuccessfulColumn[1].removeAll();
		mMaintColumn[1].removeAll();
		mStartDateColumn[1].removeAll();
		mEndDateColumn[1].removeAll();
		generateHeaders(true);

	}

	void addRecord(WeaponDeterminationRecord record) {
		if (record != null) {
			JLabel weaponLabel = new JLabel(record.getWeapon());
			JLabel teacherLabel = new JLabel(DeterminationList.getTeacher(record.getTeacher()).getTeacher());
			JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
			JLabel startDateLabel = new JLabel(record.getStartDate());
			String endDate = record.getEndDate();
			JLabel endDateLabel = new JLabel(endDate);

			int which = endDate.isBlank() ? 0 : 1;
			mWeaponColumn[which].add(weaponLabel);
			mTeacherColumn[which].add(teacherLabel);
			mBonusAmountColumn[which].add(bonusLabel);
			mDPPerWeekColumn[which].add(DPPerWeekLabel);
			mDPSpentColumn[which].add(usedLabel);
			mMaintColumn[which].add(maintLabel);
			mSuccessfulColumn[which].add(successLabel);
			mStartDateColumn[which].add(startDateLabel);
			mEndDateColumn[which].add(endDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and End Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel weaponColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(PROFICIENCY_TITLE);
		weaponColumn.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		teacherColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dPSpentColumn.add(new JLabel("Used")); //$NON-NLS-1$
		bonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		maintColumn.add(new JLabel("Maint")); //$NON-NLS-1$
		successfulColumn.add(new JLabel("Successful")); //$NON-NLS-1$

		mWeaponPopup = new TKPopupMenu(getWeaponsMenu());
		mWeaponPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mWeaponPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mWeaponPopup.getPreferredSize().width, POPUP_HEIGHT);
		mWeaponPopup.setMaximumSize(size2);
		weaponColumn.add(mWeaponPopup);

		mTeacherPopup = new TKPopupMenu(TeacherTab.getTeacherPopup(this, TeacherTab.WEAPONS));
		Dimension size3 = new Dimension(mTeacherPopup.getPreferredSize().width, POPUP_HEIGHT);
		mTeacherPopup.setMaximumSize(size3);
		teacherColumn.add(mTeacherPopup);

		mBonusLabel = new JLabel(String.valueOf(currentMaintenance));
		mBonusLabel.setMinimumSize(size);
		mBonusLabel.setPreferredSize(size);
		bonusAmountColumn.add(mBonusLabel);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		dPPerWeekColumn.add(mDPPerWeekField);

		mDPSpentLabel = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
		mDPSpentLabel.setMinimumSize(size);
		mDPSpentLabel.setPreferredSize(size);
		dPSpentColumn.add(mDPSpentLabel);

		mMaintLabel = new JLabel(String.valueOf(currentMaintenance));
		mMaintLabel.setMinimumSize(size);
		mMaintLabel.setPreferredSize(size);
		maintColumn.add(mMaintLabel);

		mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
		mSuccessfulLabel.setMinimumSize(size);
		mSuccessfulLabel.setPreferredSize(size);
		successfulColumn.add(mSuccessfulLabel);

		mStartDateLabel = new JLabel();
		mEndDateLabel = new JLabel();

		teacherColumn.add(Box.createVerticalGlue());
		dPSpentColumn.add(Box.createVerticalGlue());
		bonusAmountColumn.add(Box.createVerticalGlue());
		maintColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(weaponColumn);
		outerWrapper.add(teacherColumn);
		outerWrapper.add(bonusAmountColumn);
		outerWrapper.add(dPPerWeekColumn);
		outerWrapper.add(dPSpentColumn);
		outerWrapper.add(maintColumn);
		outerWrapper.add(successfulColumn);

		updateEnabledState();

		buttonWrapper.add(outerWrapper, BorderLayout.NORTH);
		buttonWrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		return buttonWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		ArrayList<WeaponDeterminationRecord> list = DeterminationList.getWeaponRecords();
		if (list.size() > 0) {
			for (WeaponDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mWeaponPopup.getSelectedItem().equals(SELECT_WEAPON) || mTeacherPopup.getSelectedItem().equals(TeacherTab.SELECT_TEACHER) || mDPPerWeekField.getText().isBlank())) {
		return true;
		//			}
		//		}
		//		return false;
	}

	private JMenu getWeaponsMenu() {
		JMenu weaponPopupMenu = TKPopupMenu.createMenu(SELECT_WEAPON);

		String weaponList[] = WeaponList.getProficiencyList();

		weaponPopupMenu.addSeparator();
		for (String name : weaponList) {
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.addActionListener(this);
				weaponPopupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(SELECT_WEAPON);
		menuItem.addActionListener(this);
		weaponPopupMenu.add(menuItem, 0);

		return weaponPopupMenu;
	}

	@Override
	public String getSuccessText() {
		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null) {
			return "Success: ?"; //$NON-NLS-1$
		}
		int success = record.getModifiedStat(AttributesRecord.DEX);
		return SUCCESS_TEXT1 + success;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
