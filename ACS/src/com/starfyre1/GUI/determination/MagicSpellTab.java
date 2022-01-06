/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

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
					+ "For most Mages there is also a monitory investment.";																	//$NON-NLS-1$

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
	JTextField[]				mSpellLabel;
	JTextField[]				mSchoolLabel;
	JTextField[]				mCostLabel;
	JTextField[]				mPointsField;

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

		mSpellLabel = new JTextField[ROWS];
		mSchoolLabel = new JTextField[ROWS];
		mCostLabel = new JTextField[ROWS];
		mPointsField = new JTextField[ROWS];
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
		JLabel header = new JLabel("School:", SwingConstants.CENTER); //$NON-NLS-1$
		schoolPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		costPanel.add(new JLabel("Cost:", SwingConstants.CENTER)); //$NON-NLS-1$
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER));
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mSpellLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT);
			spellPanel.add(mSpellLabel[i]);

			mSchoolLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT);
			schoolPanel.add(mSchoolLabel[i]);

			mCostLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT);
			costPanel.add(mCostLabel[i]);

			mPointsField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT);
			dpPerWeekPanel.add(mPointsField[i]);

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

	public ArrayList<MagicSpellDeterminationRecord> getRecordsToLearn() {
		ArrayList<MagicSpellDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!mSpellLabel[i].getText().isBlank() && !mSchoolLabel[i].getText().isBlank() && !mPointsField[i].getText().isBlank()) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new MagicSpellDeterminationRecord(mSpellLabel[i].getText().trim(), mSchoolLabel[i].getText().trim(), TKStringHelpers.getIntValue(mCostLabel[i].getText().trim(), 0), TKStringHelpers.getIntValue(mPointsField[i].getText().trim(), 0), campaignDate));
			}
		}
		return list;
	}
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
