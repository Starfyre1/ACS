/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WeaponProficiencyTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character must first find a teacher with a higher Hit Bonus.\r\n"					// //$NON-NLS-1$
					+ "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";												//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																	//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "To learn or improve a weapon proficiency:";												//$NON-NLS-1$
	private static final String	COST_TEXT						= "Cost: 40";																				//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT				= "";																						//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TEXT			= WEAPON_PROFICIENCY_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP					= "1D20 < (Dexerity)";																		//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1					= "Success: 1D20 < ";																		//$NON-NLS-1$

	private static final int	ROWS							= 5;
	private static final int	COST							= 40;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JTextField[]				mWeaponField;
	JLabel[]					mTeacherLabel;
	JTextField[]				mPointsField;

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
	}

	@Override
	protected void updateDialogButtons() {
		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnyProficiencyChoosen(), false);
	}

	@Override
	protected void loadDisplay() {
		setSuccessText(getSuccessText());
		updateDialogButtons();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int completed = 0;
		int attempted = 0;
		int currentTeacher = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		mWeaponField = new JTextField[ROWS];
		mTeacherLabel = new JLabel[ROWS];
		mPointsField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] bonusLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel weaponPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		weaponPanel.add(new JLabel(WEAPON_PROFICIENCY_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("Teacher", SwingConstants.CENTER); //$NON-NLS-1$
		teacherPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mWeaponField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
			weaponPanel.add(mWeaponField[i]);

			mTeacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			mTeacherLabel[i].setMinimumSize(size);
			mTeacherLabel[i].setPreferredSize(size);
			teacherPanel.add(mTeacherLabel[i]);

			mPointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this);
			dpPerWeekPanel.add(mPointsField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(usedLabel[i]);

			bonusLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			bonusLabel[i].setMinimumSize(size);
			bonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(bonusLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);
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

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private boolean isAnyProficiencyChoosen() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponField[i].getText().isBlank() || mTeacherLabel[i].getText().isBlank() || mPointsField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<WeaponProficiencyDeterminationRecord> getRecordsToLearn() {
		ArrayList<WeaponProficiencyDeterminationRecord> list = new ArrayList<>();
		// DW _finish
		for (int i = 0; i < ROWS; i++) {
			if (!(mWeaponField[i].getText().isBlank() || mTeacherLabel[i].getText().isBlank() || mPointsField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new WeaponProficiencyDeterminationRecord(mWeaponField[i].getText().trim(), TKStringHelpers.getIntValue(mTeacherLabel[i].getText().trim(), 0), TKStringHelpers.getIntValue(mPointsField[i].getText(), 0), COST, campaignDate));
			}
		}
		return list;
	}

	private String getSuccessText() {
		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null) {
			return "?"; //$NON-NLS-1$
		}
		int success = record.getModifiedStat(AttributesRecord.DEX);
		return SUCCESS_TEXT1 + success;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
