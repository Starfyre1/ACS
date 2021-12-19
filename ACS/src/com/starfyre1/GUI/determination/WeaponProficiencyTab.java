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

public class WeaponProficiencyTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character must first find a teacher with a higher Hit Bonus than the their own.\r\n"					// //$NON-NLS-1$
					+ "\r\n"																																				// //$NON-NLS-1$
					+ "The character gains 1/4 the difference between the teachers Hit Bonus and their own.\r\n"															// //$NON-NLS-1$
					+ "\r\n"																																				// //$NON-NLS-1$
					+ "The bonus will apply only when the character is using that particular weapon.";																		//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																					//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "To learn or improve a weapon proficiency:";																//$NON-NLS-1$
	private static final String	COST_TEXT						= "Cost: 40";																								//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT				= "";																										//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TEXT			= WEAPON_PROFICIENCY_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP					= "1D20 < (Dexerity)";																						//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1					= "Success: 1D20 < ";																						//$NON-NLS-1$

	private static final int	ROWS							= 5;
	private static final int	COST							= 40;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

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
		return createPage(createCenterPanel(), WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private String getSuccessText() {
		int success = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		return SUCCESS_TEXT1 + success;
	}

	private JPanel createCenterPanel() {
		int completed = 0;
		int attempted = 0;
		int currentTeacher = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;
		Dimension size = new Dimension();

		JTextField[] langField = new JTextField[ROWS];
		JLabel[] teacherLabel = new JLabel[ROWS];
		JTextField[] pointsField = new JTextField[ROWS];
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
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			langField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, 20);
			weaponPanel.add(langField[i]);

			if (i == 0) {
				size = new Dimension(header.getPreferredSize().width, langField[0].getPreferredSize().height);
			}

			teacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			teacherLabel[i].setMinimumSize(size);
			teacherLabel[i].setPreferredSize(size);
			teacherPanel.add(teacherLabel[i]);

			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20);
			dpPerWeekPanel.add(pointsField[i]);

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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
