/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
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
	private static final String		MAINTENANCE_TEXT		= "Maintain: 1 DP / week";																																																	//$NON-NLS-1$
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

	private JPanel					mAttrColumn;
	private JPanel					mDPPerWeekColumn;
	private JPanel					mDPSpentColumn;
	private JPanel					mMaintColumn;
	private JPanel					mSuccessfulColumn;
	private JPanel					mStartDateColumn;
	private JPanel					mEndDateColumn;

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
				AttributeDeterminationRecord record = new AttributeDeterminationRecord(getAttributeNumber(mAttrPopup.getSelectedItem()), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), COST, CampaignDateChooser.getCampaignDate(), null);
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

	private TKPopupMenu getPopup(JMenuItem menuItem) {

		JPopupMenu popup = (JPopupMenu) menuItem.getParent();
		JMenu menu = (JMenu) popup.getInvoker();
		TKPopupMenu popup3;
		if (menu.getParent() instanceof TKPopupMenu) {
			popup3 = (TKPopupMenu) menu.getParent();
		} else {
			JPopupMenu popup2 = (JPopupMenu) menu.getParent();
			ComboMenu menu2 = (ComboMenu) popup2.getInvoker();
			popup3 = (TKPopupMenu) menu2.getParent();
		}

		return popup3;
	}

	public void updateDisplay() {

		loadDisplay();
	}

	@Override
	protected void loadDisplay() {
		ArrayList<AttributeDeterminationRecord> list = DeterminationList.getAttribRecords();
		JPanel wrapper = new JPanel();
		if (list.size() > 0) {
			for (AttributeDeterminationRecord record : list) {
				JLabel attrLabel = new JLabel(AttributesTab.ATTRIBUTE_NAMES[record.getAttribute()]);
				JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
				JLabel usedLabel = new JLabel(String.valueOf(record.getDPTotalSpent()) + " / " + record.getDPCost()); //$NON-NLS-1$
				JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
				// DW _Count successful vs attempted
				JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				JLabel startDateLabel = new JLabel(record.getStartDate());
				JLabel endDateLabel = new JLabel(record.getEndDate());

				wrapper.add(attrLabel);
				wrapper.add(DPPerWeekLabel);
				wrapper.add(usedLabel);
				wrapper.add(maintLabel);
				wrapper.add(successLabel);
				wrapper.add(startDateLabel);
				wrapper.add(endDateLabel);
			}
		}
		//		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), PHYSICAL_DESCRIPTION, ATTRIBUTES_TAB_TOOLTIP, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and End Date (popup?)
		//		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		mAttrColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders();

		outerWrapper.add(mAttrColumn);
		outerWrapper.add(mDPPerWeekColumn);
		outerWrapper.add(mDPSpentColumn);
		outerWrapper.add(mMaintColumn);
		outerWrapper.add(mSuccessfulColumn);
		outerWrapper.add(mStartDateColumn);
		outerWrapper.add(mEndDateColumn);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders() {
		mAttrColumn.add(new JLabel(ATTRIBUTES_TAB_TITLE));
		mDPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn.add(new JLabel("Used:")); //$NON-NLS-1$
		mMaintColumn.add(new JLabel("Maint:")); //$NON-NLS-1$
		mSuccessfulColumn.add(new JLabel("Successful:")); //$NON-NLS-1$
		mStartDateColumn.add(new JLabel("Start Date:")); //$NON-NLS-1$
		mEndDateColumn.add(new JLabel("End Date:")); //$NON-NLS-1$
	}

	void clearTab() {
		mAttrColumn.removeAll();
		mDPPerWeekColumn.removeAll();
		mDPSpentColumn.removeAll();
		mMaintColumn.removeAll();
		mSuccessfulColumn.removeAll();
		mStartDateColumn.removeAll();
		mEndDateColumn.removeAll();
		generateHeaders();

	}

	void addRecord(AttributeDeterminationRecord record) {
		if (record != null) {
			JLabel attrLabel = new JLabel(AttributesTab.ATTRIBUTE_NAMES[record.getAttribute()]);
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			JLabel endDateLabel = new JLabel(record.getEndDate());

			mAttrColumn.add(attrLabel);
			mDPPerWeekColumn.add(DPPerWeekLabel);
			mDPSpentColumn.add(usedLabel);
			mMaintColumn.add(maintLabel);
			mSuccessfulColumn.add(successLabel);
			mStartDateColumn.add(startDateLabel);
			mEndDateColumn.add(endDateLabel);
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
				yield 2;
			case AttributesRecord.DEXTERITY:
				yield 3;
			case AttributesRecord.BOW_SKILL:
				yield 4;
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
