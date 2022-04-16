/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WeaponProficiencyTab extends DeterminationTab {
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
	private JLabel[]			mTeacherLabel;
	private JTextField[]		mDPPerWeekField;
	private JLabel[]			mUsedLabel;
	private JLabel[]			mBonusLabel;
	private JLabel[]			mSuccessfulLabel;
	private JLabel[]			mStartDateLabel;
	private JLabel[]			mCompletionDateLabel;

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
			if (source.equals(mLearnButton)) {
				ArrayList<WeaponProficiencyDeterminationRecord> list = getRecordsToLearn();
				for (WeaponProficiencyDeterminationRecord record : list) {
					DeterminationList.addWeaponRecord(record);
				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mWeaponPopup[i].getMenu().setEnabled(enable);

			mDPPerWeekField[i].setEnabled(enable);
			mDPPerWeekField[i].setEditable(enable);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = DeterminationList.getWeaponRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				WeaponProficiencyDeterminationRecord record = list.get(i);

				mWeaponPopup[i].selectPopupMenuItem(record.getWeapon());
				mTeacherLabel[i].setText(String.valueOf(record.getTeacher()));
				mBonusLabel[i].setText(String.valueOf(record.getBonus()));
				mDPPerWeekField[i].setText(String.valueOf(record.getDPPerWeek()));
				mUsedLabel[i].setText(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				// DW _Count successful vs attempted
				mSuccessfulLabel[i].setText(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				mStartDateLabel[i].setText(record.getStartDate());
				mCompletionDateLabel[i].setText(record.getCompletionDate());

			}
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
		int currentTeacher = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mWeaponPopup = new TKPopupMenu[ROWS];
		mTeacherLabel = new JLabel[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mUsedLabel = new JLabel[ROWS];
		mBonusLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel weaponPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(PROFICIENCY_TITLE);
		weaponPanel.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		teacherPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:")); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus")); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:")); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mWeaponPopup[i] = new TKPopupMenu(getWeaponsMenu());
			mWeaponPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mWeaponPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mWeaponPopup[i].setMinimumSize(size2);
			mWeaponPopup[i].setPreferredSize(size2);
			weaponPanel.add(mWeaponPopup[i]);
			mTeacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			mTeacherLabel[i].setMinimumSize(size);
			mTeacherLabel[i].setPreferredSize(size);
			teacherPanel.add(mTeacherLabel[i]);

			mBonusLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mBonusLabel[i].setMinimumSize(size);
			mBonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(mBonusLabel[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			mUsedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			mUsedLabel[i].setMinimumSize(size);
			mUsedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(mUsedLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successfulPanel.add(mSuccessfulLabel[i]);
		}

		teacherPanel.add(Box.createVerticalGlue());
		dpSpentPanel.add(Box.createVerticalGlue());
		bonusAmountPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		outerWrapper.add(weaponPanel);
		outerWrapper.add(teacherPanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(dpPerWeekPanel);
		outerWrapper.add(dpSpentPanel);
		outerWrapper.add(successfulPanel);

		updateEnabledState();

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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
		//		expertisePopupMenu.addItemListener(this);

		return weaponPopupMenu;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponPopup[i].getSelectedItem().equals(SELECT_WEAPON) || mTeacherLabel[i].getText().isBlank() || mDPPerWeekField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<WeaponProficiencyDeterminationRecord> getRecordsToLearn() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponPopup[i].getSelectedItem().equals(SELECT_WEAPON) || mTeacherLabel[i].getText().isBlank() || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new WeaponProficiencyDeterminationRecord(mWeaponPopup[i].getSelectedItem(), TKStringHelpers.getIntValue(mTeacherLabel[i].getText().trim(), 0), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText(), 0), COST, campaignDate));
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
