/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AttributesTab extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).";																																							//$NON-NLS-1$

	static final String				ATTRIBUTES_TAB_TITLE	= "Attributes";																																																				//$NON-NLS-1$
	static final String				ATTRIBUTES_TAB_TOOLTIP	= "To raise your physical attributes:";																																														//$NON-NLS-1$
	private static final String		CHOOSE_ATTRIBUTE		= "Choose Attribute";																																																		//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 50";																																																				//$NON-NLS-1$
	private static final String		MAINTENANCE_TEXT		= "Maintain: 1 DP";																																																			//$NON-NLS-1$
	private static final String		SUCCESS_TOOLTIP			= "1D20 + 1/2 level >= stat";																																																//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: (1D20 + ";																																																		//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= ") >= ";																																																					//$NON-NLS-1$

	public static final String[]	ATTRIBUTE_NAMES			= new String[] { AttributesRecord.STRENGTH, AttributesRecord.CONSTITUTION, AttributesRecord.WISDOM, AttributesRecord.DEXTERITY, AttributesRecord.BOW_SKILL };
	private static final int[]		ATTRIBUTE_NUMBERS		= new int[] { AttributesRecord.STR, AttributesRecord.CON, AttributesRecord.WIS, AttributesRecord.DEX, AttributesRecord.BOW };
	private static final String[]	ATTRIBUTE_DESCRIPTIONS	= new String[] { AttributesRecord.STRENGTH_DESCRIPTION, AttributesRecord.CONSTITUTION_DESCRIPTION, AttributesRecord.WISDOM_DESCRIPTION, AttributesRecord.DEXTERITY_DESCRIPTION, AttributesRecord.BOW_SKILL_DESCRIPTION };

	private static final int		COST					= 50;

	private static final String		SAVE					= "Save";																																																					//$NON-NLS-1$
	private static final String		CANCEL					= "Cancel";																																																					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton					mOkButton;
	private JButton					mCancelButton;

	private TKPopupMenu				mAttrPopup;
	private JTextField				mDPPerWeekField;
	private JLabel					mDPSpentLabel;
	private JLabel					mMaintLabel;
	private JLabel					mSuccessfulLabel;
	private JLabel					mStartDateLabel;
	private JLabel					mEndDateLabel;

	private JPanel					mAttrColumn[];
	private JPanel					mDPPerWeekColumn[];
	private JPanel					mDPSpentColumn[];
	private JPanel					mMaintColumn[];
	private JPanel					mSuccessfulColumn[];
	private JPanel					mStartDateColumn[];
	private JPanel					mEndDateColumn[];

	private JDialog					mNewEntryDialog;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link AttributesTab}.
	 *
	 * @param owner
	 */
	public AttributesTab(Object owner) {
		super(owner, ATTRIBUTES_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), CHOOSE_ATTRIBUTE, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				String startDate = CampaignDateChooser.getCampaignDate();
				AttributeDeterminationRecord record = new AttributeDeterminationRecord(getAttributeNumber(mAttrPopup.getSelectedItem()), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), COST, startDate, startDate);
				DeterminationList.addAttribRecord(record);
				((DeterminationPointsDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
		} else if (source instanceof JMenuItem) {
			JPopupMenu popup = (JPopupMenu) ((JMenuItem) source).getParent();
			if (popup.getInvoker().equals(mAttrPopup.getMenu())) {
				boolean enable = !mAttrPopup.getSelectedItem().equals(CHOOSE_ATTRIBUTE);
				mDPPerWeekField.setEnabled(enable);
				mDPPerWeekField.setEditable(enable);
			}
			updateDialogButtons();
		}

	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		mOkButton = TKComponentHelpers.createButton(SAVE, this);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);

		panel.add(mOkButton);
		panel.add(mCancelButton);

		return panel;
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		mAttrPopup.getMenu().setEnabled(headerRecord == null ? false : headerRecord.getCharacterClass() != null);

		boolean enable = mAttrPopup.getMenu().isEnabled() && mAttrPopup.getSelectedItem() != CHOOSE_ATTRIBUTE;

		mDPPerWeekField.setEnabled(enable);
		mDPPerWeekField.setEditable(enable);
	}

	public void updateDisplay() {

		loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		mAttrColumn			= new JPanel[2];
		mDPPerWeekColumn		= new JPanel[2];
		mDPSpentColumn		= new JPanel[2];
		mMaintColumn			= new JPanel[2];
		mSuccessfulColumn		= new JPanel[2];
		mStartDateColumn		= new JPanel[2];
		mEndDateColumn		= new JPanel[2];

		return createPage(createCenterPanel(false), createCenterPanel(true), PHYSICAL_DESCRIPTION, ATTRIBUTES_TAB_TOOLTIP, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel(boolean complete) {
		// DW _add Start and End Date (popup?)
		//		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();
		int test = complete ? 1 : 0;

		JPanel outerWrapper = new JPanel();
		mAttrColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders(complete);

		outerWrapper.add(mAttrColumn[test]);
		outerWrapper.add(mDPPerWeekColumn[test]);
		outerWrapper.add(mDPSpentColumn[test]);
		outerWrapper.add(mMaintColumn[test]);
		outerWrapper.add(mSuccessfulColumn[test]);
		outerWrapper.add(mStartDateColumn[test]);
		outerWrapper.add(mEndDateColumn[test]);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders(boolean complete) {
		int test = complete ? 1 : 0;

		mAttrColumn[test].add(new JLabel(ATTRIBUTES_TAB_TITLE));
		mDPPerWeekColumn[test].add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn[test].add(new JLabel("DP Spent")); //$NON-NLS-1$
		mMaintColumn[test].add(new JLabel("Maint")); //$NON-NLS-1$
		mSuccessfulColumn[test].add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn[test].add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn[test].add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mAttrColumn[0].removeAll();
		mDPPerWeekColumn[0].removeAll();
		mDPSpentColumn[0].removeAll();
		mMaintColumn[0].removeAll();
		mSuccessfulColumn[0].removeAll();
		mStartDateColumn[0].removeAll();
		mEndDateColumn[0].removeAll();
		generateHeaders(false);

		mAttrColumn[1].removeAll();
		mDPPerWeekColumn[1].removeAll();
		mDPSpentColumn[1].removeAll();
		mMaintColumn[1].removeAll();
		mSuccessfulColumn[1].removeAll();
		mStartDateColumn[1].removeAll();
		mEndDateColumn[1].removeAll();
		generateHeaders(true);

	}

	void addRecord(AttributeDeterminationRecord record) {
		if (record != null) {
			JLabel attrLabel = new JLabel(AttributesTab.ATTRIBUTE_NAMES[record.getAttribute()]);
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			String endDate = record.getEndDate();
			JLabel endDateLabel = new JLabel(endDate);

			int which = endDate.isBlank() ? 0 : 1;
			mAttrColumn[which].add(attrLabel);
			mDPPerWeekColumn[which].add(DPPerWeekLabel);
			mDPSpentColumn[which].add(usedLabel);
			mMaintColumn[which].add(maintLabel);
			mSuccessfulColumn[which].add(successLabel);
			mStartDateColumn[which].add(startDateLabel);
			mEndDateColumn[which].add(endDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and End Date (popup?)
		int completed = 0;
		int attempted = 0;
		boolean currentMaintenance = false;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel attrColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(ATTRIBUTES_TAB_TITLE);
		attrColumn.add(label);

		Dimension size = new Dimension(label.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dPSpentColumn.add(new JLabel("Used:")); //$NON-NLS-1$
		maintColumn.add(new JLabel("Maint")); //$NON-NLS-1$
		successfulColumn.add(new JLabel("Successful:")); //$NON-NLS-1$

		mAttrPopup = new TKPopupMenu(getAttrPopup());
		mAttrPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mAttrPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mAttrPopup.getPreferredSize().width, POPUP_HEIGHT);
		mAttrPopup.setMaximumSize(size2);
		attrColumn.add(mAttrPopup);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		dPPerWeekColumn.add(mDPPerWeekField);

		mDPSpentLabel = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
		mDPSpentLabel.setMinimumSize(size);
		mDPSpentLabel.setPreferredSize(size);
		dPSpentColumn.add(mDPSpentLabel);

		mMaintLabel = new JLabel(String.valueOf(currentMaintenance));
		mMaintLabel.setMinimumSize(size);
		mMaintLabel.setPreferredSize(size);
		maintColumn.add(mMaintLabel);

		mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
		mSuccessfulLabel.setMinimumSize(size);
		mSuccessfulLabel.setPreferredSize(size);
		successfulColumn.add(mSuccessfulLabel);

		mStartDateLabel = new JLabel();
		mEndDateLabel = new JLabel();

		dPSpentColumn.add(Box.createVerticalGlue());
		maintColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(attrColumn);
		outerWrapper.add(maintColumn);
		outerWrapper.add(dPPerWeekColumn);
		outerWrapper.add(dPSpentColumn);
		outerWrapper.add(successfulColumn);

		updateEnabledState();

		buttonWrapper.add(outerWrapper, BorderLayout.NORTH);
		buttonWrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		return buttonWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		ArrayList<AttributeDeterminationRecord> list = DeterminationList.getAttribRecords();
		if (list.size() > 0) {
			for (AttributeDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!mAttrPopup.getSelectedItem().equals(CHOOSE_ATTRIBUTE) && mAttrPopup.isEnabled() && !mDPPerWeekField.getText().isBlank()) {
		return true;
		//			}
		//		}
		//		return false;
	}

	private int getAttributeNumber(String name) {
		int value = switch (name) {
			case AttributesRecord.STRENGTH:
				yield 0;
			case AttributesRecord.CONSTITUTION:
				yield 1;
			case AttributesRecord.WISDOM:
				yield 3;
			case AttributesRecord.DEXTERITY:
				yield 4;
			case AttributesRecord.BOW_SKILL:
				yield 5;
			default:
				throw new IllegalArgumentException("Unexpected value: " + getClass() + name); //$NON-NLS-1$
		};

		return value;
	}

	private JMenu getAttrPopup() {
		JMenu popupMenu = TKPopupMenu.createMenu(CHOOSE_ATTRIBUTE);

		popupMenu.addSeparator();
		for (int i = 0; i < ATTRIBUTE_NAMES.length; i++) {
			String name = ATTRIBUTE_NAMES[i];
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.setToolTipText(ATTRIBUTE_DESCRIPTIONS[i]);
				menuItem.addActionListener(this);
				popupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(CHOOSE_ATTRIBUTE);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem, 0);

		return popupMenu;
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
