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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PhysicalTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).\r\n"																							// //$NON-NLS-1$
					+ "\r\n"																																																// //$NON-NLS-1$
					+ "There is a 10% chance per week that the stat will drop down to the original number if not maintained.  This chance is cumulative.";																	//$NON-NLS-1$

	static final String				PHYSICAL_TAB_TITLE		= "Attributes";																																					//$NON-NLS-1$
	static final String				PHYSICAL_TAB_TOOLTIP	= "Attributes";																																					//$NON-NLS-1$
	private static final String		PHYSICAL_COST			= "Cost: 50";																																					//$NON-NLS-1$
	private static final String		PHYSICAL_COST2			= "Maintain: 1 DP / week";																																		//$NON-NLS-1$
	private static final String		PHYSICAL_TITLE			= "To raise your physical statistics:";																															//$NON-NLS-1$
	private static final String		PHYSICAL_TITLE2			= "Success: 1D20 + 1/2 level > stat";																															//$NON-NLS-1$

	private static final String[]	ATTRIBUTE_NAMES			= new String[] { AttributesRecord.STRENGTH, AttributesRecord.CONSTITUTION, AttributesRecord.WISDOM, AttributesRecord.DEXTERITY, AttributesRecord.BOW_SKILL };
	private static final int[]		ATTRIBUTE_NUMBERS		= new int[] { AttributesRecord.STR, AttributesRecord.CON, AttributesRecord.WIS, AttributesRecord.DEX, AttributesRecord.BOW };

	private static final int		ROWS					= 5;
	private static final int		COST					= 50;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JCheckBox[]						mAttrCheckBox;																																											//= new JCheckBox[5];

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link PhysicalTab}.
	 *
	 * @param owner
	 */
	public PhysicalTab(Object owner) {
		super(owner, PHYSICAL_TAB_TITLE);
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
		if (source instanceof JCheckBox) {
			//			AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
			System.out.println(((JCheckBox) source).getText());
			updateDialogButtons();
			if (source.equals(mAttrCheckBox[0])) {
				//				if (mStrCheckBox.isSelected()) {
				//					int points = ((DeterminationPointsDisplay) getOwner()).getDeterminationPoints();
				//					String valueSpent = JOptionPane.showInputDialog(this, "You currently have " + points + " available...", "How many D.P's to spend on Strength?", JOptionPane.QUESTION_MESSAGE);
				//					// null for cancel and "" for ok with no value
				//					System.out.println(":" + valueSpent + ":");
				//				}
				//			} else if (source.equals(mAttrCheckBox[1])) {
				//
				//			} else if (source.equals(mAttrCheckBox[2])) {
				//
				//			} else if (source.equals(mAttrCheckBox[3])) {
				//
				//			} else if (source.equals(mAttrCheckBox[4])) {

			}
		}
	}

	private boolean isAnyBoxChecked() {
		for (int i = 0; i < ROWS; i++) {
			if (mAttrCheckBox[i].isSelected()) {
				return true;
			}
		}
		return false;
	}

	private void updateDialogButtons() {
		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnyBoxChecked(), false);
	}

	@Override
	protected void loadDisplay() {
		//DW to do
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), PHYSICAL_DESCRIPTION, PHYSICAL_TITLE, PHYSICAL_TITLE2, PHYSICAL_COST, PHYSICAL_COST2);
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;
		Dimension size = new Dimension();

		mAttrCheckBox = new JCheckBox[5];
		JTextField[] pointsField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] maintLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel wrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel first = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel second = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel third = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel fourth = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		JPanel fifth = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		first.add(new JLabel("Attributes:", SwingConstants.CENTER)); //$NON-NLS-1$
		second.add(new JLabel("# use:", SwingConstants.CENTER)); //$NON-NLS-1$
		third.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		fourth.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		fifth.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mAttrCheckBox[i] = TKComponentHelpers.createCheckBox(ATTRIBUTE_NAMES[i], false, this);
			first.add(mAttrCheckBox[i]);

			if (i == 0) {
				size = new Dimension(mAttrCheckBox[0].getPreferredSize());
			}

			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, size.height, this);
			second.add(pointsField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			third.add(usedLabel[i]);

			maintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			maintLabel[i].setMinimumSize(size);
			maintLabel[i].setPreferredSize(size);
			fourth.add(maintLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			fifth.add(successfulLabel[i]);

		}

		third.add(Box.createVerticalGlue());
		fourth.add(Box.createVerticalGlue());
		fifth.add(Box.createVerticalGlue());

		wrapper.add(first);
		wrapper.add(second);
		wrapper.add(third);
		wrapper.add(fourth);
		wrapper.add(fifth);

		updateCheckboxEnabledState();

		return wrapper;
	}

	private void updateCheckboxEnabledState() {
		AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		// DW Also need to make sure we can't increase it more than 3 times... will need to check against DeterminationPointsRecord when created

		for (int i = 0; i < ROWS; i++) {
			mAttrCheckBox[i].setEnabled(attribs.getModifiedStat(ATTRIBUTE_NUMBERS[i]) < 18);
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
