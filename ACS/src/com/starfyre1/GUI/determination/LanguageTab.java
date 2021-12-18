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

public class LanguageTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must learn the language again, and again successfully roll below their Wisdom stat";	// //$NON-NLS-1$

	static final String			LANGUAGE_TAB_TITLE		= "Languages";																															//$NON-NLS-1$
	static final String			LANGUAGE_TAB_TOOLTIP	= "To learn a new language or become fluent in a known one:";																			//$NON-NLS-1$
	private static final String	COST_TEXT				= "Cost: 40 (immersive) or 80 (Tutor)";																									//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT		= "Maintain: 1 DP / week for Fluent";																									//$NON-NLS-1$
	private static final String	LANGUAGE_TEXT			= LANGUAGE_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP			= "1D20 - (1/4 level) < (Wisdom)";																										//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1			= "Success: 1D20 - ";																													//$NON-NLS-1$
	private static final String	SUCCESS_TEXT2			= " < ";																																//$NON-NLS-1$

	private static final int	ROWS					= 5;
	private static final int	COST					= 40;
	private static final int	COST2					= 80;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link LanguageTab}.
	 *
	 * @param owner
	 */
	public LanguageTab(Object owner) {
		super(owner, LANGUAGE_TAB_TITLE);
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
		return createPage(createCenterPanel(), LANGUAGE_DESCRIPTION, LANGUAGE_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private String getSuccessText() {
		int level = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() / 4;
		int success = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WIS);
		return SUCCESS_TEXT1 + level + SUCCESS_TEXT2 + success;
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;
		Dimension size = new Dimension();

		JTextField[] langField = new JTextField[ROWS];
		JTextField[] pointsField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] maintLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel wrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel langPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		langPanel.add(new JLabel(LANGUAGE_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("# use:", SwingConstants.CENTER); //$NON-NLS-1$
		dpPerWeekPanel.add(header);
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			langField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, 20);
			langPanel.add(langField[i]);

			if (i == 0) {
				size = new Dimension(header.getPreferredSize().width, langField[0].getPreferredSize().height);
			}

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

			successfulLabel[i] = new JLabel(completed + " / " + attempted);
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);

		}
		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		wrapper.add(langPanel);
		wrapper.add(dpPerWeekPanel);
		wrapper.add(dpSpentPanel);
		wrapper.add(maintPanel);
		wrapper.add(successfulPanel);

		return wrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
