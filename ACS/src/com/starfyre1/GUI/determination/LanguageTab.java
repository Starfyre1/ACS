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

public class LanguageTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must learn the language again, and again successfully roll below their Wisdom stat";	// //$NON-NLS-1$

	static final String			LANGUAGE_TAB_TITLE		= "Languages";																															//$NON-NLS-1$
	static final String			LANGUAGE_TAB_TOOLTIP	= "Languages";																															//$NON-NLS-1$
	private static final String	LANGUAGE_COST			= "Cost: 40 (immersive) or 80 (Tutor)";																									//$NON-NLS-1$
	private static final String	LANGUAGE_COST2			= "Maintain: For fluent is 1 DP / week";																								//$NON-NLS-1$
	private static final String	LANGUAGE_TITLE			= "To learn a new language:";																											//$NON-NLS-1$
	private static final String	LANGUAGE_TITLE2			= "Success: 1D20 - 1/4 level < Wisdom";																									//$NON-NLS-1$

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
		return createPage(createCenterPanel(), LANGUAGE_DESCRIPTION, LANGUAGE_TITLE, LANGUAGE_TITLE2, LANGUAGE_COST, LANGUAGE_COST2);
	}

	private JPanel createCenterPanel() {
		JPanel wrapper = new JPanel();
		BoxLayout blw = new BoxLayout(wrapper, BoxLayout.X_AXIS);
		wrapper.setLayout(blw);
		wrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		wrapper.setAlignmentY(TOP_ALIGNMENT);

		JPanel first = new JPanel();
		BoxLayout bll = new BoxLayout(first, BoxLayout.Y_AXIS);
		first.setLayout(bll);
		first.setAlignmentY(TOP_ALIGNMENT);

		first.add(new JLabel(LANGUAGE_TAB_TITLE + ":", SwingConstants.CENTER));
		JTextField[] langField = new JTextField[ROWS];
		for (int i = 0; i < ROWS; i++) {
			langField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20);
			first.add(langField[i]);
		}
		Dimension panel = first.getMinimumSize();
		first.setMaximumSize(new Dimension(panel.width + 10, panel.height));

		JPanel second = new JPanel();
		BoxLayout blc = new BoxLayout(second, BoxLayout.Y_AXIS);
		second.setLayout(blc);
		second.setBorder(new EmptyBorder(0, 15, 0, 5));
		second.setAlignmentY(TOP_ALIGNMENT);

		second.add(new JLabel("# use:", SwingConstants.CENTER));
		JTextField[] pointsField = new JTextField[ROWS];
		for (int i = 0; i < ROWS; i++) {
			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
			second.add(pointsField[i]);
		}

		JPanel third = new JPanel();
		BoxLayout blr = new BoxLayout(third, BoxLayout.Y_AXIS);
		third.setLayout(blr);
		third.setBorder(new EmptyBorder(0, 5, 0, 5));
		third.setAlignmentY(TOP_ALIGNMENT);

		Dimension size = new Dimension(pointsField[0].getPreferredSize());
		int currentlySpent = 0;
		JLabel strLabel = new JLabel(currentlySpent + " / " + COST);
		strLabel.setMinimumSize(size);
		strLabel.setPreferredSize(size);
		JLabel conLabel = new JLabel(currentlySpent + " / " + COST);
		conLabel.setMinimumSize(size);
		conLabel.setPreferredSize(size);
		JLabel wisLabel = new JLabel(currentlySpent + " / " + COST);
		wisLabel.setMinimumSize(size);
		wisLabel.setPreferredSize(size);
		JLabel dexLabel = new JLabel(currentlySpent + " / " + COST);
		dexLabel.setMinimumSize(size);
		dexLabel.setPreferredSize(size);
		JLabel bowLabel = new JLabel(currentlySpent + " / " + COST);
		bowLabel.setMinimumSize(size);
		bowLabel.setPreferredSize(size);
		third.add(new JLabel("Used:", SwingConstants.CENTER));
		third.add(strLabel);
		third.add(conLabel);
		third.add(wisLabel);
		third.add(dexLabel);
		third.add(bowLabel);
		third.add(Box.createVerticalGlue());

		JPanel fourth = new JPanel();
		BoxLayout blf = new BoxLayout(fourth, BoxLayout.Y_AXIS);
		fourth.setLayout(blf);
		fourth.setBorder(new EmptyBorder(0, 5, 0, 5));
		fourth.setAlignmentY(TOP_ALIGNMENT);

		int currentMaintenance = 0;
		JLabel strMaintenanceLabel = new JLabel("" + currentMaintenance);
		strMaintenanceLabel.setMinimumSize(size);
		strMaintenanceLabel.setPreferredSize(size);
		JLabel conMaintenanceLabel = new JLabel("" + currentMaintenance);
		conMaintenanceLabel.setMinimumSize(size);
		conMaintenanceLabel.setPreferredSize(size);
		JLabel wisMaintenanceLabel = new JLabel("" + currentMaintenance);
		wisMaintenanceLabel.setMinimumSize(size);
		wisMaintenanceLabel.setPreferredSize(size);
		JLabel dexMaintenanceLabel = new JLabel("" + currentMaintenance);
		dexMaintenanceLabel.setMinimumSize(size);
		dexMaintenanceLabel.setPreferredSize(size);
		JLabel bowMaintenanceLabel = new JLabel("" + currentMaintenance);
		bowMaintenanceLabel.setMinimumSize(size);
		bowMaintenanceLabel.setPreferredSize(size);
		fourth.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		fourth.add(strMaintenanceLabel);
		fourth.add(conMaintenanceLabel);
		fourth.add(wisMaintenanceLabel);
		fourth.add(dexMaintenanceLabel);
		fourth.add(bowMaintenanceLabel);
		fourth.add(Box.createVerticalGlue());

		JPanel fifth = new JPanel();
		BoxLayout bl = new BoxLayout(fifth, BoxLayout.Y_AXIS);
		fifth.setLayout(bl);
		fifth.setAlignmentY(TOP_ALIGNMENT);
		fifth.setBorder(new EmptyBorder(0, 15, 0, 0));

		int completed = 0;
		int attempted = 0;
		JLabel strCompleteLabel = new JLabel(completed + " / " + attempted);
		strCompleteLabel.setMinimumSize(size);
		strCompleteLabel.setPreferredSize(size);
		JLabel conCompleteLabel = new JLabel(completed + " / " + attempted);
		conCompleteLabel.setMinimumSize(size);
		conCompleteLabel.setPreferredSize(size);
		JLabel wisCompleteLabel = new JLabel(completed + " / " + attempted);
		wisCompleteLabel.setMinimumSize(size);
		wisCompleteLabel.setPreferredSize(size);
		JLabel dexCompleteLabel = new JLabel(completed + " / " + attempted);
		dexCompleteLabel.setMinimumSize(size);
		dexCompleteLabel.setPreferredSize(size);
		JLabel bowCompleteLabel = new JLabel(completed + " / " + attempted);
		bowCompleteLabel.setMinimumSize(size);
		bowCompleteLabel.setPreferredSize(size);
		fifth.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$
		fifth.add(strCompleteLabel);
		fifth.add(conCompleteLabel);
		fifth.add(wisCompleteLabel);
		fifth.add(dexCompleteLabel);
		fifth.add(bowCompleteLabel);
		fifth.add(Box.createVerticalGlue());

		wrapper.add(first);
		wrapper.add(second);
		wrapper.add(third);
		wrapper.add(fourth);
		wrapper.add(fifth);

		return wrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
