/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
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

public class WeaponProficiencyTab extends DeterminationTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";	//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																	//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "To learn or improve a weapon proficiency:";												//$NON-NLS-1$
	private static final String	SELECT_WEAPON_PROFICIENCY		= "Select Weapon Proficiency";																//$NON-NLS-1$
	private static final String	COST_TEXT						= "Cost: 40";																				//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT				= "";																						//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TEXT			= WEAPON_PROFICIENCY_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP					= "1D20 < (Dexerity)";																		//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1					= "Success: 1D20 < ";																		//$NON-NLS-1$

	static final String			PROFICIENCY_TITLE				= "Weapon:";																				//$NON-NLS-1$
	private static final String	SELECT_WEAPON					= "Select Weapon";																			//$NON-NLS-1$
	private static final int	COST							= 40;

	private static final String	SAVE							= "Save";																					//$NON-NLS-1$
	private static final String	CANCEL							= "Cancel";																					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu			mWeaponPopup;
	private TKPopupMenu			mTeacherPopup;
	private JTextField			mDPPerWeekField;
	private JLabel				mUsedLabel;
	private JLabel				mBonusLabel;
	private JLabel				mSuccessfulLabel;
	private JLabel				mStartDateLabel;
	private JLabel				mCompletionDateLabel;

	JPanel						mWeaponColumn;
	JPanel						mTeacherColumn;
	JPanel						mDPPerWeekColumn;
	JPanel						mDPSpentColumn;
	JPanel						mBonusAmountColumn;
	JPanel						mSuccessfulColumn;
	JPanel						mStartDateColumn;
	JPanel						mCompletionDateColumn;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WeaponProficiencyTab}.
	 *
	 * @param owner
	 */
	public WeaponProficiencyTab(Object owner) {
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
				JDialog dialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), SELECT_WEAPON_PROFICIENCY, true);
				dialog.setSize(800, 400);

				dialog.add(createDialogPanel());
				dialog.setVisible(true);
				//				ArrayList<WeaponProficiencyDeterminationRecord> list = getRecordsToLearn();
				//				for (WeaponProficiencyDeterminationRecord record : list) {
				//					DeterminationList.addWeaponRecord(record);
				//				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		} else if (source instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) source;
			if (mTeacherColumn == getPopup(menuItem).getParent()) {
				updateBonusValueFromTeacher(menuItem);
			} else {
				updateTeacherPopup(menuItem);
			}
			updateEnabledState();
		}
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		JButton okButton = new JButton(SAVE);
		JButton cancelButton = new JButton(CANCEL);

		panel.add(okButton);
		panel.add(cancelButton);

		return panel;
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

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		mWeaponPopup.getMenu().setEnabled(headerRecord == null ? false : headerRecord.getCharacterClass() != null);

		boolean enable = mWeaponPopup.getMenu().isEnabled() && mWeaponPopup.getSelectedItem() != SELECT_WEAPON;
		mTeacherPopup.getMenu().setEnabled(enable);

		mDPPerWeekField.setEnabled(mTeacherPopup.getSelectedItem() != TeacherTab.SELECT_TEACHER);
		mDPPerWeekField.setEditable(mTeacherPopup.getSelectedItem() != TeacherTab.SELECT_TEACHER);
	}

	@Override
	protected void loadDisplay() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = DeterminationList.getWeaponRecords();
		JPanel wrapper = new JPanel();
		if (list.size() > 0) {
			for (WeaponProficiencyDeterminationRecord record : list) {
				JLabel weaponLabel = new JLabel(record.getWeapon());
				JLabel teacherLabel = new JLabel(DeterminationList.getTeacher(record.getTeacher()).getTeacher());
				JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
				JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
				JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				JLabel startDateLabel = new JLabel(record.getStartDate());
				JLabel completionDateLabel = new JLabel(record.getCompletionDate());

				wrapper.add(weaponLabel);
				wrapper.add(teacherLabel);
				wrapper.add(bonusLabel);
				wrapper.add(DPPerWeekLabel);
				wrapper.add(usedLabel);
				wrapper.add(successLabel);
				wrapper.add(startDateLabel);
				wrapper.add(completionDateLabel);
			}
		}
		//		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		//		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		mWeaponColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mBonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mCompletionDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(PROFICIENCY_TITLE);
		mWeaponColumn.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		mTeacherColumn.add(header);
		mDPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn.add(new JLabel("Used:")); //$NON-NLS-1$
		mBonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		mSuccessfulColumn.add(new JLabel("Successful:")); //$NON-NLS-1$
		mStartDateColumn.add(new JLabel("Start Date:")); //$NON-NLS-1$
		mCompletionDateColumn.add(new JLabel("Completion Date:")); //$NON-NLS-1$

		outerWrapper.add(mWeaponColumn);
		outerWrapper.add(mTeacherColumn);
		outerWrapper.add(mBonusAmountColumn);
		outerWrapper.add(mDPPerWeekColumn);
		outerWrapper.add(mDPSpentColumn);
		outerWrapper.add(mSuccessfulColumn);
		outerWrapper.add(mStartDateColumn);
		outerWrapper.add(mCompletionDateColumn);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	void addRecord(WeaponProficiencyDeterminationRecord record) {
		if (record != null) {
			JLabel weaponLabel = new JLabel(record.getWeapon());
			JLabel teacherLabel = new JLabel(DeterminationList.getTeacher(record.getTeacher()).getTeacher());
			JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			JLabel completionDateLabel = new JLabel(record.getCompletionDate());

			mWeaponColumn.add(weaponLabel);
			mTeacherColumn.add(teacherLabel);
			mBonusAmountColumn.add(bonusLabel);
			mDPPerWeekColumn.add(DPPerWeekLabel);
			mDPSpentColumn.add(usedLabel);
			mSuccessfulColumn.add(successLabel);
			mStartDateColumn.add(startDateLabel);
			mCompletionDateColumn.add(completionDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and Completion Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		mWeaponColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mBonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(PROFICIENCY_TITLE);
		mWeaponColumn.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		mTeacherColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		mDPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn.add(new JLabel("Used:")); //$NON-NLS-1$
		mBonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		mSuccessfulColumn.add(new JLabel("Successful:")); //$NON-NLS-1$

		mWeaponPopup = new TKPopupMenu(getWeaponsMenu());
		mWeaponPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mWeaponPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mWeaponPopup.getPreferredSize().width, POPUP_HEIGHT);
		mWeaponPopup.setMaximumSize(size2);
		mWeaponColumn.add(mWeaponPopup);

		mTeacherPopup = new TKPopupMenu(TeacherTab.getTeacherPopup(this));
		Dimension size3 = new Dimension(mTeacherPopup.getPreferredSize().width, POPUP_HEIGHT);
		mTeacherPopup.setMaximumSize(size3);
		mTeacherColumn.add(mTeacherPopup);

		mBonusLabel = new JLabel(String.valueOf(currentMaintenance));
		mBonusLabel.setMinimumSize(size);
		mBonusLabel.setPreferredSize(size);
		mBonusAmountColumn.add(mBonusLabel);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		mDPPerWeekColumn.add(mDPPerWeekField);

		mUsedLabel = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
		mUsedLabel.setMinimumSize(size);
		mUsedLabel.setPreferredSize(size);
		mDPSpentColumn.add(mUsedLabel);

		mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
		mSuccessfulLabel.setMinimumSize(size);
		mSuccessfulLabel.setPreferredSize(size);
		mSuccessfulColumn.add(mSuccessfulLabel);

		mStartDateLabel = new JLabel();
		mCompletionDateLabel = new JLabel();

		mTeacherColumn.add(Box.createVerticalGlue());
		mDPSpentColumn.add(Box.createVerticalGlue());
		mBonusAmountColumn.add(Box.createVerticalGlue());
		mSuccessfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(mWeaponColumn);
		outerWrapper.add(mTeacherColumn);
		outerWrapper.add(mBonusAmountColumn);
		outerWrapper.add(mDPPerWeekColumn);
		outerWrapper.add(mDPSpentColumn);
		outerWrapper.add(mSuccessfulColumn);

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
		ArrayList<WeaponProficiencyDeterminationRecord> list = DeterminationList.getWeaponRecords();
		if (list.size() > 0) {
			for (WeaponProficiencyDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
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
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mWeaponPopup.getSelectedItem().equals(SELECT_WEAPON) || mTeacherPopup.getSelectedItem().equals(TeacherTab.SELECT_TEACHER) || mDPPerWeekField.getText().isBlank())) {
		return true;
		//			}
		//		}
		//		return false;
	}

	@Override
	protected String getSuccessText() {
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
