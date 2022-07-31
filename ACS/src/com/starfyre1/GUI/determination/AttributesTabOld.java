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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AttributesTabOld extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		PHYSICAL_DESCRIPTION	= "A stat cannot be raised more than (3) points, or above (18).";																																							//$NON-NLS-1$

	static final String				PHYSICAL_TAB_TITLE		= "Attributes";																																																				//$NON-NLS-1$
	static final String				PHYSICAL_TAB_TOOLTIP	= "To raise your physical attributes:";																																														//$NON-NLS-1$
	private static final String		CHOOSE_ATTRIBUTE		= "Choose Attribute";																																																		//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 50";																																																				//$NON-NLS-1$
	private static final String		MAINTAINENCE_TEXT		= "Maintain: 1 DP / week";																																																	//$NON-NLS-1$
	private static final String		PHYSICAL_TEXT			= PHYSICAL_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 + 1/2 level >= stat";																																																//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: (1D20 + ";																																																		//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= ") >= ";																																																					//$NON-NLS-1$

	public static final String[]	ATTRIBUTE_NAMES			= new String[] { AttributesRecord.STRENGTH, AttributesRecord.CONSTITUTION, AttributesRecord.WISDOM, AttributesRecord.DEXTERITY, AttributesRecord.BOW_SKILL };
	private static final int[]		ATTRIBUTE_NUMBERS		= new int[] { AttributesRecord.STR, AttributesRecord.CON, AttributesRecord.WIS, AttributesRecord.DEX, AttributesRecord.BOW };
	private static final String[]	ATTRIBUTE_DESCRIPTIONS	= new String[] { AttributesRecord.STRENGTH_DESCRIPTION, AttributesRecord.CONSTITUTION_DESCRIPTION, AttributesRecord.WISDOM_DESCRIPTION, AttributesRecord.DEXTERITY_DESCRIPTION, AttributesRecord.BOW_SKILL_DESCRIPTION };

	private static final int		ROWS					= 5;
	private static final int		COST					= 50;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]			mAttrPopup;
	private JTextField[]			mDPPerWeekField;
	private JLabel[]				mDPTotalSpentLabel;
	private JLabel[]				mMaintLabel;
	private JLabel[]				mSuccessfulLabel;
	private JLabel[]				mStartDateLabel;
	private JLabel[]				mCompletionDateLabel;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link AttributesTabOld}.
	 *
	 * @param owner
	 */
	public AttributesTabOld(Object owner) {
		super(owner, PHYSICAL_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private int getPanelIndex(JMenuItem menuItem) {

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

		return getIndexInPanel((JPanel) popup3.getParent(), popup3);
	}

	private int getIndexInPanel(JPanel panel, Object which) {
		for (int i = 0; i < panel.getComponentCount(); i++) {
			Component comp = panel.getComponent(i);
			if (comp == which) {
				// i == 0 is label;
				return i - 1;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JMenuItem) {
			int i = getPanelIndex((JMenuItem) source);
			JPopupMenu popup = (JPopupMenu) ((JMenuItem) source).getParent();
			if (popup.getInvoker().equals(mAttrPopup[i].getMenu())) {
				boolean enable = !mAttrPopup[i].getSelectedItem().equals(CHOOSE_ATTRIBUTE);
				mDPPerWeekField[i].setEnabled(enable);
				mDPPerWeekField[i].setEditable(enable);
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
		ArrayList<AttributeDeterminationRecord> list = DeterminationList.getAttribRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AttributeDeterminationRecord record = list.get(i);
				mAttrPopup[i].selectPopupMenuItem(AttributesTabOld.ATTRIBUTE_NAMES[record.getAttribute()]);
				mDPPerWeekField[i].setText(String.valueOf(record.getDPPerWeek()));
				mDPTotalSpentLabel[i].setText(String.valueOf(record.getDPTotalSpent()) + " / " + record.getDPCost()); //$NON-NLS-1$
				mMaintLabel[i].setText(String.valueOf(record.hasMaintainence()));
				// DW _Count successful vs attempted
				mSuccessfulLabel[i].setText(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				mStartDateLabel[i].setText(record.getStartDate());
				mCompletionDateLabel[i].setText(record.getCompletionDate());
			}
		}
		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), PHYSICAL_DESCRIPTION, PHYSICAL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mAttrPopup = new TKPopupMenu[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mDPTotalSpentLabel = new JLabel[ROWS];
		mMaintLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];
		mStartDateLabel = new JLabel[ROWS];
		mCompletionDateLabel = new JLabel[ROWS];

		JPanel wrapperPanel = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel attrPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpTotalSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		JPanel successPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel header = new JLabel("Attributes:", SwingConstants.CENTER); //$NON-NLS-1$
		attrPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpTotalSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mAttrPopup[i] = new TKPopupMenu(getAttrPopup());
			mAttrPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mAttrPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mAttrPopup[i].setMinimumSize(size2);
			mAttrPopup[i].setPreferredSize(size2);
			mAttrPopup[i].getMenu().setEnabled(false);
			attrPanel.add(mAttrPopup[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			mDPPerWeekField[i].getDocument().addDocumentListener(this);
			mDPPerWeekField[i].setEnabled(false);
			mDPPerWeekField[i].setEditable(false);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			mDPTotalSpentLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			mDPTotalSpentLabel[i].setMinimumSize(size);
			mDPTotalSpentLabel[i].setPreferredSize(size);
			dpTotalSpentPanel.add(mDPTotalSpentLabel[i]);

			mMaintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mMaintLabel[i].setMinimumSize(size);
			mMaintLabel[i].setPreferredSize(size);
			maintPanel.add(mMaintLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successPanel.add(mSuccessfulLabel[i]);

			mStartDateLabel[i] = new JLabel();
			mCompletionDateLabel[i] = new JLabel();

		}

		dpTotalSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successPanel.add(Box.createVerticalGlue());

		wrapperPanel.add(attrPanel);
		wrapperPanel.add(dpPerWeekPanel);
		wrapperPanel.add(dpTotalSpentPanel);
		wrapperPanel.add(maintPanel);
		wrapperPanel.add(successPanel);

		updateEnabledState();

		return wrapperPanel;
	}

	private void updateEnabledState() {
		AttributesRecord attribs = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		// DW Also need to make sure we can't increase it more than 3 times... will need to check against DeterminationPointsRecord when created
		for (int i = 0; i < ROWS; i++) {
			mAttrPopup[i].getMenu().setEnabled(attribs != null);
			boolean enable = attribs != null && !mAttrPopup[i].getSelectedItem().equals(CHOOSE_ATTRIBUTE) && attribs.getModifiedStat(getAttributeNumber(mAttrPopup[i].getSelectedItem())) < 18;
			mDPPerWeekField[i].setEnabled(enable);
			mDPPerWeekField[i].setEditable(enable);
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		for (int i = 0; i < ROWS; i++) {
			pointsSpent += TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0);
		}
		return pointsSpent;
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
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!mAttrPopup[i].getSelectedItem().equals(CHOOSE_ATTRIBUTE) && mAttrPopup[i].isEnabled() && !mDPPerWeekField[i].getText().isBlank()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<AttributeDeterminationRecord> getRecordsToLearn() {
		ArrayList<AttributeDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mAttrPopup[i].getSelectedItem().equals(CHOOSE_ATTRIBUTE) || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new AttributeDeterminationRecord(getAttributeNumber(mAttrPopup[i].getSelectedItem()), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), COST, campaignDate, campaignDate));
			}
		}
		return list;
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
