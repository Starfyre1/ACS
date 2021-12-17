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
	private static final String	PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).\r\n"								// //$NON-NLS-1$
					+ "\r\n"																																// //$NON-NLS-1$
					+ "There is a 10% chance per week that the stat will drop down to the original number if not maintained.  This chance is cumulative.";	//$NON-NLS-1$

	static final String			PHYSICAL_TAB_TITLE		= "Attributes";																						//$NON-NLS-1$
	static final String			PHYSICAL_TAB_TOOLTIP	= "Attributes";																						//$NON-NLS-1$
	private static final String	PHYSICAL_COST			= "Cost: 50";																						//$NON-NLS-1$
	private static final String	PHYSICAL_COST2			= "Maintain: 1 DP / week";																			//$NON-NLS-1$
	private static final String	PHYSICAL_TITLE			= "To raise your physical statistics:";																//$NON-NLS-1$
	private static final String	PHYSICAL_TITLE2			= "Success: 1D20 + 1/2 level > stat";																//$NON-NLS-1$

	private static final int	ROWS					= 5;
	private static final int	COST					= 50;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JCheckBox					mStrCheckBox;
	JCheckBox					mConCheckBox;
	JCheckBox					mWisCheckBox;
	JCheckBox					mDexCheckBox;
	JCheckBox					mBowCheckBox;

	JTextField					mStrTextField;
	JTextField					mConTextField;
	JTextField					mWisTextField;
	JTextField					mDexTextField;
	JTextField					mBowTextField;

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
			AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
			System.out.println(((JCheckBox) source).getText());
			updateDialogButtons();
			if (source.equals(mStrCheckBox)) {
				//				if (mStrCheckBox.isSelected()) {
				//					int points = ((DeterminationPointsDisplay) getOwner()).getDeterminationPoints();
				//					String valueSpent = JOptionPane.showInputDialog(this, "You currently have " + points + " available...", "How many D.P's to spend on Strength?", JOptionPane.QUESTION_MESSAGE);
				//					// null for cancel and "" for ok with no value
				//					System.out.println(":" + valueSpent + ":");
				//				}
			} else if (source.equals(mConCheckBox)) {

			} else if (source.equals(mWisCheckBox)) {

			} else if (source.equals(mDexCheckBox)) {

			} else if (source.equals(mBowCheckBox)) {

			}
		}
	}

	private boolean isAnyBoxChecked() {
		if (mStrCheckBox.isSelected() || //
						mConCheckBox.isSelected() || //
						mWisCheckBox.isSelected() || //
						mDexCheckBox.isSelected() || //
						mBowCheckBox.isSelected()) {
			return true;
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
		JPanel wrapper = new JPanel();
		BoxLayout blw = new BoxLayout(wrapper, BoxLayout.X_AXIS);
		wrapper.setLayout(blw);
		wrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		wrapper.setAlignmentY(TOP_ALIGNMENT);

		JPanel first = new JPanel();
		BoxLayout bll = new BoxLayout(first, BoxLayout.Y_AXIS);
		first.setLayout(bll);
		first.setAlignmentY(TOP_ALIGNMENT);

		mStrCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.STRENGTH, false, this);
		mStrCheckBox.setBorder(new EmptyBorder(2, 0, 2, 5));
		mConCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.CONSTITUTION, false, this);
		mConCheckBox.setBorder(new EmptyBorder(2, 0, 2, 5));
		mWisCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.WISDOM, false, this);
		mWisCheckBox.setBorder(new EmptyBorder(2, 0, 2, 5));
		mDexCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.DEXTERITY, false, this);
		mDexCheckBox.setBorder(new EmptyBorder(2, 0, 2, 5));
		mBowCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.BOW_SKILL, false, this);
		mBowCheckBox.setBorder(new EmptyBorder(2, 0, 2, 5));
		first.add(new JLabel("Attributes:", SwingConstants.CENTER));
		first.add(mStrCheckBox);
		first.add(mConCheckBox);
		first.add(mWisCheckBox);
		first.add(mDexCheckBox);
		first.add(mBowCheckBox);

		JPanel second = new JPanel();
		BoxLayout blc = new BoxLayout(second, BoxLayout.Y_AXIS);
		second.setLayout(blc);
		second.setAlignmentY(TOP_ALIGNMENT);

		int height = mStrCheckBox.getPreferredSize().height;
		mStrTextField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, height, this);
		mConTextField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, height, this);
		mWisTextField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, height, this);
		mDexTextField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, height, this);
		mBowTextField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, height, this);
		second.add(new JLabel("# use:", SwingConstants.CENTER));
		second.add(mStrTextField);
		second.add(mConTextField);
		second.add(mWisTextField);
		second.add(mDexTextField);
		second.add(mBowTextField);

		JPanel third = new JPanel();
		BoxLayout blr = new BoxLayout(third, BoxLayout.Y_AXIS);
		third.setLayout(blr);
		third.setAlignmentY(TOP_ALIGNMENT);

		Dimension size = new Dimension(mStrCheckBox.getPreferredSize());
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
		fourth.setAlignmentY(TOP_ALIGNMENT);
		fourth.setBorder(new EmptyBorder(0, 15, 0, 0));

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

		updateCheckboxEnabledState();

		return wrapper;
	}

	private void updateCheckboxEnabledState() {
		AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		// DW Also need to make sure we can't increase it more than 3 times... will need to check against DeterminationPointsRecord when created
		mStrCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.STR) < 18);
		mConCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.CON) < 18);
		mWisCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.WIS) < 18);
		mDexCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.DEX) < 18);
		mBowCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.BOW) < 18);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
