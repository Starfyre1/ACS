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
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WeaponProficiencyTabOld extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";	//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																	//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "To learn or improve a weapon proficiency:";												//$NON-NLS-1$
	private static final String	COST_TEXT						= "Cost: 40";																				//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT				= "";																						//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TEXT			= WEAPON_PROFICIENCY_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP					= "1D20 < (Dexerity)";																		//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1					= "Success: 1D20 < ";																		//$NON-NLS-1$

	static final String			PROFICIENCY_TITLE				= "Proficiencies:";																			//$NON-NLS-1$
	private static final String	SELECT_WEAPON					= "Select Weapon";																			//$NON-NLS-1$
	private static final int	ROWS							= 5;
	private static final int	COST							= 40;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]		mWeaponPopup;
	private TKPopupMenu[]		mTeacherPopup;
	private JTextField[]		mDPPerWeekField;
	private JLabel[]			mUsedLabel;
	private JLabel[]			mBonusLabel;
	private JLabel[]			mSuccessfulLabel;
	private JLabel[]			mStartDateLabel;
	private JLabel[]			mCompletionDateLabel;

	JPanel						mTeacherColumn;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WeaponProficiencyTabOld}.
	 *
	 * @param owner
	 */
	public WeaponProficiencyTabOld(Object owner) {
		super(owner, WEAPON_PROFICIENCY_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mLearnButton)) {
				ArrayList<WeaponProficiencyDeterminationRecord> list = getRecordsToLearn();
				for (WeaponProficiencyDeterminationRecord record : list) {
					DeterminationList.addWeaponRecord(record);
				}
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

	// This is if the Proficiency has just been set
	private void updateTeacherPopup(JMenuItem menuItem) {
		if (!menuItem.getText().equals(SELECT_WEAPON)) {
			int index = getPanelIndex(menuItem);
			// DW probably should remove ActionListener from JMenuItem's in mTeacherPopup's
			mTeacherColumn.remove(mTeacherPopup[index]);
			mTeacherPopup[index] = new TKPopupMenu(TeacherTabOld.getTeacherPopup(this));
			Dimension size3 = new Dimension(mTeacherPopup[index].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mTeacherPopup[index].setMinimumSize(size3);
			mTeacherPopup[index].setPreferredSize(size3);
			mTeacherColumn.add(mTeacherPopup[index], index + 1);
		}
	}

	// This is if the teacher has just been set
	private void updateBonusValueFromTeacher(JMenuItem menuItem) {
		if (!menuItem.getText().equals(TeacherTabOld.SELECT_TEACHER)) {
			int index = getPanelIndex(menuItem);
			TeacherDeterminationRecord teacherRecord = DeterminationList.getWeaponsTeacherRecord(menuItem.getText());
			if (teacherRecord != null) {
				String bonus;
				if (teacherRecord.getExpertise().equals(mWeaponPopup[index].getSelectedItem())) {
					bonus = String.valueOf(teacherRecord.getBonus());
				} else {
					bonus = "0"; //$NON-NLS-1$
				}
				mBonusLabel[index].setText(bonus);
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

	private int getPanelIndex(JMenuItem menuItem) {

		TKPopupMenu popup3 = getPopup(menuItem);
		return getIndexInPanel((JPanel) popup3.getParent(), popup3);
	}

	private int getIndexInPanel(JPanel panel, Object which) {
		for (int i = 0; i < panel.getComponentCount(); i++) {
			Component comp = panel.getComponent(i);
			if (comp == which) {
				// i == 0 is label;
				return i - 1;
			}
		}
		return -1;
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		for (int i = 0; i < ROWS; i++) {
			mWeaponPopup[i].getMenu().setEnabled(headerRecord == null ? false : headerRecord.getCharacterClass() != null);

			boolean enable = mWeaponPopup[i].getMenu().isEnabled() && mWeaponPopup[i].getSelectedItem() != SELECT_WEAPON;
			mTeacherPopup[i].getMenu().setEnabled(enable);

			mDPPerWeekField[i].setEnabled(mTeacherPopup[i].getSelectedItem() != TeacherTabOld.SELECT_TEACHER);
			mDPPerWeekField[i].setEditable(mTeacherPopup[i].getSelectedItem() != TeacherTabOld.SELECT_TEACHER);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = DeterminationList.getWeaponRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				WeaponProficiencyDeterminationRecord record = list.get(i);
				System.out.println(record.toString());
				mWeaponPopup[i].selectPopupMenuItem(record.getWeapon());

				// DW probably should remove ActionListener from JMenuItem's in mTeacherPopup's
				mTeacherColumn.remove(mTeacherPopup[i]);
				mTeacherPopup[i] = new TKPopupMenu(TeacherTabOld.getTeacherPopup(this));
				TeacherDeterminationRecord teacherRecord = DeterminationList.getTeacher(record.getTeacher());
				mTeacherPopup[i].selectPopupMenuItem(teacherRecord == null ? TeacherTabOld.SELECT_TEACHER : teacherRecord.getTeacher());
				Dimension size3 = new Dimension(mTeacherPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
				mTeacherPopup[i].setMinimumSize(size3);
				mTeacherPopup[i].setPreferredSize(size3);
				mTeacherColumn.add(mTeacherPopup[i], i + 1);

				mBonusLabel[i].setText(String.valueOf(record.getBonus()));
				mDPPerWeekField[i].setText(String.valueOf(record.getDPPerWeek()));
				mUsedLabel[i].setText(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				// DW _Count successful vs attempted
				mSuccessfulLabel[i].setText(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				mStartDateLabel[i].setText(record.getStartDate());
				mCompletionDateLabel[i].setText(record.getCompletionDate());

			}
			mTeacherColumn.revalidate();
			mTeacherColumn.repaint();

		}
		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mWeaponPopup = new TKPopupMenu[ROWS];
		mTeacherPopup = new TKPopupMenu[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mUsedLabel = new JLabel[ROWS];
		mBonusLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];
		mStartDateLabel = new JLabel[ROWS];
		mCompletionDateLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel weaponColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(PROFICIENCY_TITLE);
		weaponColumn.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		mTeacherColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dpSpentColumn.add(new JLabel("Used:")); //$NON-NLS-1$
		bonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		successfulColumn.add(new JLabel("Successful:")); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mWeaponPopup[i] = new TKPopupMenu(getWeaponsMenu());
			mWeaponPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mWeaponPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mWeaponPopup[i].setMinimumSize(size2);
			mWeaponPopup[i].setPreferredSize(size2);
			weaponColumn.add(mWeaponPopup[i]);

			mTeacherPopup[i] = new TKPopupMenu(TeacherTabOld.getTeacherPopup(this));
			Dimension size3 = new Dimension(mTeacherPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mTeacherPopup[i].setMinimumSize(size3);
			mTeacherPopup[i].setPreferredSize(size3);
			mTeacherColumn.add(mTeacherPopup[i]);

			mBonusLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mBonusLabel[i].setMinimumSize(size);
			mBonusLabel[i].setPreferredSize(size);
			bonusAmountColumn.add(mBonusLabel[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekColumn.add(mDPPerWeekField[i]);

			mUsedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			mUsedLabel[i].setMinimumSize(size);
			mUsedLabel[i].setPreferredSize(size);
			dpSpentColumn.add(mUsedLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successfulColumn.add(mSuccessfulLabel[i]);

			mStartDateLabel[i] = new JLabel();
			mCompletionDateLabel[i] = new JLabel();
		}

		mTeacherColumn.add(Box.createVerticalGlue());
		dpSpentColumn.add(Box.createVerticalGlue());
		bonusAmountColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(weaponColumn);
		outerWrapper.add(mTeacherColumn);
		outerWrapper.add(bonusAmountColumn);
		outerWrapper.add(dpPerWeekColumn);
		outerWrapper.add(dpSpentColumn);
		outerWrapper.add(successfulColumn);

		updateEnabledState();

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		for (int i = 0; i < ROWS; i++) {
			pointsSpent += TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0);
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
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponPopup[i].getSelectedItem().equals(SELECT_WEAPON) || mTeacherPopup[i].getSelectedItem().equals(TeacherTabOld.SELECT_TEACHER) || mDPPerWeekField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<WeaponProficiencyDeterminationRecord> getRecordsToLearn() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponPopup[i].getSelectedItem().equals(SELECT_WEAPON) || mTeacherPopup[i].getSelectedItem().equals(TeacherTabOld.SELECT_TEACHER) || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new WeaponProficiencyDeterminationRecord(mWeaponPopup[i].getSelectedItem(), DeterminationList.getTeacherIdByName(mTeacherPopup[i].getSelectedItem()), TKStringHelpers.getIntValue(mBonusLabel[i].getText(), 0), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText(), 0), COST, campaignDate, campaignDate));
			}
		}
		return list;
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
