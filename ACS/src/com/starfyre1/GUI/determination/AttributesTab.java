/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AttributesTab extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).";																								//$NON-NLS-1$

	static final String				PHYSICAL_TAB_TITLE		= "Attributes";																																					//$NON-NLS-1$
	static final String				PHYSICAL_TAB_TOOLTIP	= "To raise your physical attributes:";																															//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 50";																																					//$NON-NLS-1$
	private static final String		MAINTAINENCE_TEXT		= "Maintain: 1 DP / week";																																		//$NON-NLS-1$
	private static final String		PHYSICAL_TEXT			= PHYSICAL_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 + 1/2 level > stat";																																	//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: (1D20 + ";																																			//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= ") > ";																																						//$NON-NLS-1$

	public static final String[]	ATTRIBUTE_NAMES			= new String[] { AttributesRecord.STRENGTH, AttributesRecord.CONSTITUTION, AttributesRecord.WISDOM, AttributesRecord.DEXTERITY, AttributesRecord.BOW_SKILL };
	private static final int[]		ATTRIBUTE_NUMBERS		= new int[] { AttributesRecord.STR, AttributesRecord.CON, AttributesRecord.WIS, AttributesRecord.DEX, AttributesRecord.BOW };

	private static final int		ROWS					= 5;
	private static final int		COST					= 50;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JCheckBox[]				mAttrCheckBox;
	private JTextField[]			mDPPerWeekField;

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
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JCheckBox) {
			JCheckBox checkBox = (JCheckBox) source;
			if (source.equals(mAttrCheckBox[0])) {
				mDPPerWeekField[0].setEnabled(checkBox.isSelected());
				mDPPerWeekField[0].setEditable(checkBox.isSelected());
			} else if (source.equals(mAttrCheckBox[1])) {
				mDPPerWeekField[1].setEnabled(checkBox.isSelected());
				mDPPerWeekField[1].setEditable(checkBox.isSelected());
			} else if (source.equals(mAttrCheckBox[2])) {
				mDPPerWeekField[2].setEnabled(checkBox.isSelected());
				mDPPerWeekField[2].setEditable(checkBox.isSelected());
			} else if (source.equals(mAttrCheckBox[3])) {
				mDPPerWeekField[3].setEnabled(checkBox.isSelected());
				mDPPerWeekField[3].setEditable(checkBox.isSelected());
			} else if (source.equals(mAttrCheckBox[4])) {
				mDPPerWeekField[4].setEnabled(checkBox.isSelected());
				mDPPerWeekField[4].setEditable(checkBox.isSelected());
			}
			updateDialogButtons();
		} else if (source instanceof JButton) {
			if (source.equals(mLearnButton)) {
				ArrayList<AttributeDeterminationRecord> list = getRecordsToLearn();
				for (AttributeDeterminationRecord record : list) {
					DeterminationList.addAttribRecord(record);
				}

				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}

	}

	@Override
	protected void loadDisplay() {
		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), PHYSICAL_DESCRIPTION, PHYSICAL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mAttrCheckBox = new JCheckBox[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] maintLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel wrapperPanel = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel attrPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		JPanel successPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel header = new JLabel("Attributes:", SwingConstants.CENTER); //$NON-NLS-1$
		attrPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mAttrCheckBox[i] = TKComponentHelpers.createCheckBox(ATTRIBUTE_NAMES[i], false, this);
			attrPanel.add(mAttrCheckBox[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			mDPPerWeekField[i].getDocument().addDocumentListener(this);
			mDPPerWeekField[i].setEnabled(false);
			mDPPerWeekField[i].setEditable(false);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			Dimension checkBoxSize = new Dimension(mAttrCheckBox[i].getMinimumSize().width, TEXT_FIELD_HEIGHT);
			mAttrCheckBox[i].setMaximumSize(new Dimension(checkBoxSize));
			mAttrCheckBox[i].setPreferredSize(checkBoxSize);

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

		updateEnabledState();

		return wrapperPanel;
	}

	private void updateEnabledState() {
		AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		// DW Also need to make sure we can't increase it more than 3 times... will need to check against DeterminationPointsRecord when created

		for (int i = 0; i < ROWS; i++) {
			mAttrCheckBox[i].setEnabled(attribs == null ? false : attribs.getModifiedStat(ATTRIBUTE_NUMBERS[i]) < 18);
			mDPPerWeekField[i].setEnabled(mAttrCheckBox[i].isEnabled());
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (mAttrCheckBox[i].isSelected() && mAttrCheckBox[i].isEnabled() && !mDPPerWeekField[i].getText().isBlank()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<AttributeDeterminationRecord> getRecordsToLearn() {
		ArrayList<AttributeDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (mAttrCheckBox[i].isSelected() && !mDPPerWeekField[i].getText().isBlank()) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new AttributeDeterminationRecord(i, TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), COST, campaignDate));
			}
		}
		return list;
	}

	@Override
	protected String getSuccessText() {
		HeaderRecord record = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		AttributesRecord attributesRecord = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null || attributesRecord == null) {
			return "Success: ?"; //$NON-NLS-1$
		}
		int level = record.getLevel() / 2;
		// DW ATTRIBUTE_NUMBERS[0] needs to be [currently selected stat]
		int success = attributesRecord.getModifiedStat(ATTRIBUTE_NUMBERS[0]);
		return SUCCESS_TEXT1 + level + SUCCESS_TEXT2 + success;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
