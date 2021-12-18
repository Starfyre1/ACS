/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;

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

public class MagicSpellTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	MAGIC_SPELL_DESCRIPTION	= "A power 3 spell would cost 96 D.P.'s ((3+1) Squared, times 6).\r\n"					// //$NON-NLS-1$
					+ "\r\n"																													// //$NON-NLS-1$
					+ "For most Mages there is also a monitory investment.\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																													// //$NON-NLS-1$
					+ "The success rate is outlined in the Research Section of the Magic System.";												//$NON-NLS-1$

	static final String			MAGIC_SPELL_TAB_TITLE	= "Magic Spells";																		//$NON-NLS-1$
	static final String			MAGIC_SPELL_TAB_TOOLTIP	= "To research a new magical spell:";													//$NON-NLS-1$
	private static final String	COST_TEXT				= "Cost: 6 X (spell power + 1/squared)";												//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT		= "";																					//$NON-NLS-1$
	private static final String	MAGIC_SPELL_TEXT		= MAGIC_SPELL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP			= "TBD";																				//$NON-NLS-1$
	private static final String	SUCCESS_TEXT			= "Success: TBD";																		//$NON-NLS-1$

	private static final int	ROWS					= 5;
	private static final int	COST					= 0;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MagicSpellTab}.
	 *
	 * @param owner
	 */
	public MagicSpellTab(Object owner) {
		super(owner, MAGIC_SPELL_TAB_TITLE);
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
		return createPage(createCenterPanel(), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, SUCCESS_TEXT, SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int completed = 0;
		int attempted = 0;
		Dimension size = new Dimension();

		JTextField[] spellLabel = new JTextField[ROWS];
		JTextField[] schoolLabel = new JTextField[ROWS];
		JTextField[] costLabel = new JTextField[ROWS];
		JTextField[] pointsField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel spellPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel schoolPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		spellPanel.add(new JLabel("Spell:", SwingConstants.CENTER)); //$NON-NLS-1$
		schoolPanel.add(new JLabel("School:", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("# use:", SwingConstants.CENTER); //$NON-NLS-1$
		costPanel.add(new JLabel("Cost:", SwingConstants.CENTER)); //$NON-NLS-1$
		dpPerWeekPanel.add(header);
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			spellLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, 20);
			spellPanel.add(spellLabel[i]);

			if (i == 0) {
				size = new Dimension(header.getPreferredSize().width, spellLabel[0].getPreferredSize().height);
			}

			schoolLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, 20);
			schoolPanel.add(schoolLabel[i]);

			costLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20);
			costPanel.add(costLabel[i]);

			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
			dpPerWeekPanel.add(pointsField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(usedLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);

		}
		dpSpentPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		outerWrapper.add(spellPanel);
		outerWrapper.add(schoolPanel);
		outerWrapper.add(costPanel);
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
