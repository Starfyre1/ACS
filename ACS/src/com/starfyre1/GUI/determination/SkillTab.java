/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SkillTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_DESCRIPTION	= "The character must find a teacher with the skill they want at the Highest Skill level they can find.\r\n"					// //$NON-NLS-1$
					+ "\r\n"																																						// //$NON-NLS-1$
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
	public void focusGained(FocusEvent e) {
		// Does Nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		// DW Handle Value changed
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	}

	private void updateDialogButtons() {
		//		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnyBoxChecked(), false);
	}

	@Override
	protected void loadDisplay() {
		//DW to do
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), SKILLS_DESCRIPTION, SKILL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private String getSuccessText() {
		int success = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.INT);
		return SUCCESS_TEXT1 + success;
	}

	private JPanel createCenterPanel() {
		int currentTeacher = 0;
		int currentBonus = 0;
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;
		Dimension size = new Dimension();

		JTextField[] skillsField = new JTextField[ROWS];
		JLabel[] teacherLabel = new JLabel[ROWS];
		JTextField[] pointsField = new JTextField[ROWS];
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
		dpPerWeekPanel.add(new JLabel("# use:", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			skillsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, 20);
			skillsPanel.add(skillsField[i]);
			if (i == 0) {
				size = new Dimension(header.getPreferredSize().width, skillsField[0].getPreferredSize().height);
			}

			teacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			teacherLabel[i].setMinimumSize(size);
			teacherLabel[i].setPreferredSize(size);
			teacherPanel.add(teacherLabel[i]);

			bonusLabel[i] = new JLabel(String.valueOf(currentBonus));
			bonusLabel[i].setMinimumSize(size);
			bonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(bonusLabel[i]);

			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
			dpPerWeekPanel.add(pointsField[i]);

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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
