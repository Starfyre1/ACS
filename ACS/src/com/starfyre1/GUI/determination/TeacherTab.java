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

public class TeacherTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "The payment of Teachers will be up to the Game Masters running that particular campaign.\r\n"													// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "Remember that these people will not be very cheap, they are usually very special people whose time is very precious.\r\n"															// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "But these are up to you, if a character starts selling there own time to pupils for weapons training, don't let it worry you unless it starts to get in the way of your campaign.";	//$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																																		//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "A record of teachers used, for what, and how much";																								//$NON-NLS-1$
	static final String			TEACHER_TITLE			= "Teacher's Name";																																	//$NON-NLS-1$

	private static final int	ROWS					= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link TeacherTab}.
	 *
	 * @param owner
	 */
	public TeacherTab(Object owner) {
		super(owner, TEACHER_TAB_TITLE);
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
		return createPage(createCenterPanel(), TEACHERS_DESCRIPTION, "", "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	private JPanel createCenterPanel() {
		int currentMaintenance = 0;

		JTextField[] teacherNameField = new JTextField[ROWS];
		JTextField[] expertiseField = new JTextField[ROWS];
		JTextField[] costLabel = new JTextField[ROWS];
		JLabel[] bonusLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel expertisePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		teacherPanel.add(new JLabel(TEACHER_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("Expertise:", SwingConstants.CENTER); //$NON-NLS-1$
		expertisePanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		costPanel.add(new JLabel("Cost:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			teacherNameField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT);
			teacherPanel.add(teacherNameField[i]);

			expertiseField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT);
			expertisePanel.add(expertiseField[i]);

			costLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT);
			costPanel.add(costLabel[i]);

			bonusLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			bonusLabel[i].setMinimumSize(size);
			bonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(bonusLabel[i]);
		}
		bonusAmountPanel.add(Box.createVerticalGlue());

		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(costPanel);
		outerWrapper.add(bonusAmountPanel);

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
