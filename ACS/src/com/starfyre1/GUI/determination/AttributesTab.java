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

public class AttributesTab extends DeterminationTab implements ActionListener, FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).\r\n"																							// //$NON-NLS-1$
					+ "\r\n"																																																// //$NON-NLS-1$
					+ "There is a 10% chance per week that the stat will drop down to the original number if not maintained.  This chance is cumulative.";																	//$NON-NLS-1$

	static final String				PHYSICAL_TAB_TITLE		= "Attributes";																																					//$NON-NLS-1$
	static final String				PHYSICAL_TAB_TOOLTIP	= "To raise your physical attributes:";																															//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 50";																																					//$NON-NLS-1$
	private static final String		MAINTAINENCE_TEXT		= "Maintain: 1 DP / week";																																		//$NON-NLS-1$
	private static final String		PHYSICAL_TEXT			= PHYSICAL_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 + 1/2 level > stat";																																	//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: 1D20 + ";																																			//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= " > ";																																						//$NON-NLS-1$

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
	 * Creates a new {@link AttributesTab}.
	 *
	 * @param owner
	 */
	public AttributesTab(Object owner) {
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
		return createPage(createCenterPanel(), PHYSICAL_DESCRIPTION, PHYSICAL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private String getSuccessText() {
		int level = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() / 2;
		int success = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(ATTRIBUTE_NUMBERS[0]);
		return SUCCESS_TEXT1 + level + SUCCESS_TEXT2 + success;
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

		JPanel wrapperPanel = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel attrPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		JPanel successPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		attrPanel.add(new JLabel("Attributes:", SwingConstants.CENTER)); //$NON-NLS-1$
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mAttrCheckBox[i] = TKComponentHelpers.createCheckBox(ATTRIBUTE_NAMES[i], false, this);
			attrPanel.add(mAttrCheckBox[i]);

			if (i == 0) {
				size = new Dimension(mAttrCheckBox[0].getPreferredSize());
			}

			pointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, size.height, this);
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
			successPanel.add(successfulLabel[i]);

		}

		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successPanel.add(Box.createVerticalGlue());

		wrapperPanel.add(attrPanel);
		wrapperPanel.add(dpPerWeekPanel);
		wrapperPanel.add(dpSpentPanel);
		wrapperPanel.add(maintPanel);
		wrapperPanel.add(successPanel);

		updateCheckboxEnabledState();

		return wrapperPanel;
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
