/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
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

public class SkillTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_DESCRIPTION	= "The character must find a teacher with the skill they want at the Highest Skill level they can find.\r\n"					// //$NON-NLS-1$
					+ "To further increase the skill it will require further training and D.P. assignment";																			//$NON-NLS-1$

	static final String			SKILL_TAB_TITLE		= "Skills";																														//$NON-NLS-1$
	static final String			SKILL_TAB_TOOLTIP	= "To learn or improve a skill:";																								//$NON-NLS-1$
	private static final String	COST_TEXT			= "Cost: 40-60";																												//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT	= "Maintain: 1 DP / week";																										//$NON-NLS-1$
	private static final String	SKILL_TEXT			= SKILL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP		= "1D20 < (Intelligence)";																										//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1		= "Success: 1D20 < ";																											//$NON-NLS-1$

	private static final int	ROWS				= 5;
	private static final int	COST				= 40;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JTextField[]				mSkillsField;
	JLabel[]					mTeacherLabel;
	JTextField[]				mPointsField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link SkillTab}.
	 *
	 * @param owner
	 */
	public SkillTab(Object owner) {
		super(owner, SKILL_TAB_TITLE);
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
		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnySkillChoosen(), false);
	}

	@Override
	protected void loadDisplay() {
		setSuccessText(getSuccessText());
		updateDialogButtons();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), SKILLS_DESCRIPTION, SKILL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int currentTeacher = 0;
		int currentBonus = 0;
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		mSkillsField = new JTextField[ROWS];
		mTeacherLabel = new JLabel[ROWS];
		mPointsField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] bonusLabel = new JLabel[ROWS];
		JLabel[] maintLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel skillsPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		skillsPanel.add(new JLabel(SKILL_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("Teacher", SwingConstants.CENTER); //$NON-NLS-1$
		teacherPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mSkillsField[i] = new JTextField();
			mSkillsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
			skillsPanel.add(mSkillsField[i]);

			mTeacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			mTeacherLabel[i].setMinimumSize(size);
			mTeacherLabel[i].setPreferredSize(size);
			teacherPanel.add(mTeacherLabel[i]);

			bonusLabel[i] = new JLabel(String.valueOf(currentBonus));
			bonusLabel[i].setMinimumSize(size);
			bonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(bonusLabel[i]);

			mPointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this);
			dpPerWeekPanel.add(mPointsField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(usedLabel[i]);

			maintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			maintLabel[i].setMinimumSize(size);
			maintLabel[i].setPreferredSize(size);
			maintPanel.add(maintLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);

		}
		teacherPanel.add(Box.createVerticalGlue());
		bonusAmountPanel.add(Box.createVerticalGlue());
		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		outerWrapper.add(skillsPanel);
		outerWrapper.add(teacherPanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(dpPerWeekPanel);
		outerWrapper.add(dpSpentPanel);
		outerWrapper.add(maintPanel);
		outerWrapper.add(successfulPanel);

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private boolean isAnySkillChoosen() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mSkillsField[i].getText().isBlank() || mTeacherLabel[i].getText().isBlank() || mPointsField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<SkillDeterminationRecord> getRecordsToLearn() {
		ArrayList<SkillDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mSkillsField[i].getText().isBlank() || mTeacherLabel[i].getText().isBlank() || mPointsField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new SkillDeterminationRecord(mSkillsField[i].getText().trim(), TKStringHelpers.getIntValue(mTeacherLabel[i].getText().trim(), 0), TKStringHelpers.getIntValue(mPointsField[i].getText().trim(), 0), COST, campaignDate));
			}
		}
		return list;
	}

	private String getSuccessText() {
		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null) {
			return "?"; //$NON-NLS-1$
		}
		int success = record.getModifiedStat(AttributesRecord.INT);
		return SUCCESS_TEXT1 + success;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
