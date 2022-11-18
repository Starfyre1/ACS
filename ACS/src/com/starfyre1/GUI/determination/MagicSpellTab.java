/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.component.MagicAreaPopup;
import com.starfyre1.GUI.spells.SpellSelector;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKFloatFilter;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.spells.SpellRecord;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MagicSpellTab extends DeterminationTab implements ItemListener, MouseListener {
	private static final String	SUCCESSFUL_LABEL		= "Successful:";													//$NON-NLS-1$
	private static final String	USED_LABEL				= "   Used:    ";													//$NON-NLS-1$
	private static final String	DP_WEEK_LABEL			= "DP/Week";														//$NON-NLS-1$
	private static final String	COST_LABEL				= "$ Cost:";														//$NON-NLS-1$
	private static final String	SPELL_LABEL				= "Spell:";															//$NON-NLS-1$
	private static final String	SCHOOL_LABEL			= "School:";														//$NON-NLS-1$
	private static final String	RESEARCH_CHANCE			= "Chance:";														//$NON-NLS-1$

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	MAGIC_SPELL_DESCRIPTION	= "A power 3 spell would cost 96 D.P.'s ((3+1) Squared, times 6).";	//$NON-NLS-1$

	static final String			MAGIC_SPELL_TAB_TITLE	= "Magic Spells";													//$NON-NLS-1$
	static final String			MAGIC_SPELL_TAB_TOOLTIP	= "To research a new magical spell:";								//$NON-NLS-1$
	private static final String	COST_TEXT				= "Cost: 6 X (spell power + 1/squared)";							//$NON-NLS-1$
	private static final String	MAINTENANCE_TEXT		= "";																//$NON-NLS-1$
	private static final String	MAGIC_SPELL_TEXT		= MAGIC_SPELL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP			= "TBD";															//$NON-NLS-1$
	private static final String	SUCCESS_TEXT			= "Success: TBD";													//$NON-NLS-1$
	private static final String	CHOOSE_SCHOOL			= "Choose School";													//$NON-NLS-1$
	private static final String	CHOOSE_SPELL			= "Choose Spell";													//$NON-NLS-1$

	private static final String	SAVE					= "Save";															//$NON-NLS-1$
	private static final String	CANCEL					= "Cancel";															//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOkButton;
	private JButton				mCancelButton;

	private TKPopupMenu			mSchoolPopup;
	private JLabel				mSpellLabel;
	private JTextField			mCostField;
	private JTextField			mDPPerWeekField;
	private JLabel				mDPSpentLabel;
	private JLabel				mResearchChanceLabel;
	private JLabel				mSuccessfulLabel;
	private JLabel				mStartDateLabel;
	private JLabel				mEndDateLabel;

	private Color				mOldColor;

	private JPanel				mSchoolColumn;
	private JPanel				mSpellColumn;
	private JPanel				mCostColumn;
	private JPanel				mDPPerWeekColumn;
	private JPanel				mDPSpentColumn;
	private JPanel				mResearchChanceColumn;
	private JPanel				mSuccessfulColumn;
	private JPanel				mStartDateColumn;
	private JPanel				mEndDateColumn;

	private JDialog				mNewEntryDialog;

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
	//	private int getPanelIndex(JMenuItem menuItem) {
	//
	//		JPopupMenu popup = (JPopupMenu) menuItem.getParent();
	//		JMenu menu = (JMenu) popup.getInvoker();
	//		TKPopupMenu popup3;
	//		if (menu.getParent() instanceof TKPopupMenu) {
	//			popup3 = (TKPopupMenu) menu.getParent();
	//		} else {
	//			JPopupMenu popup2 = (JPopupMenu) menu.getParent();
	//			ComboMenu menu2 = (ComboMenu) popup2.getInvoker();
	//			popup3 = (TKPopupMenu) menu2.getParent();
	//		}
	//
	//		return getIndexInPanel((JPanel) popup3.getParent(), popup3);
	//	}
	//
	//	private int getIndexInPanel(JPanel panel, Object which) {
	//		for (int i = 0; i < panel.getComponentCount(); i++) {
	//			Component comp = panel.getComponent(i);
	//			if (comp == which) {
	//				// i == 0 is label;
	//				return i - 1;
	//			}
	//		}
	//		return -1;
	//	}
	//
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), MAGIC_SPELL_TAB_TITLE, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				MagicSpellDeterminationRecord record = new MagicSpellDeterminationRecord();
				DeterminationList.addMagicSpellRecord(record);
				((DeterminationPointsDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
			//		} else if (source instanceof JMenuItem) {
			//			int index = getPanelIndex((JMenuItem) source);
			//			// DW handle -1 as failure to find index
			//			mSpellLabel[index].setText(CHOOSE_SPELL);
			//			mSpellLabel[index].addMouseListener(this);
			//			if (getOldColor(index) == null) {
			//				setOldColor(index, mSpellLabel[index].getForeground());
			//			}
			//			mSpellLabel[index].setForeground(Color.BLUE);
			//			String text = ((JMenuItem) source).getText();
			//			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
			//				SpellRecord record = selectSpell(text, index);
			//				if (record != null) {
			//					mSpellLabel[index].removeMouseListener(this);
			//					mSpellLabel[index].setForeground(getOldColor(index));
			//					setOldColor(index, null);
			//					mDPCost[index] = getDPCost(record);
			//					mUsedLabel[index].setText(mCurrentlySpentLabel[index] + " / " + mDPCost[index]); //$NON-NLS-1$
			//
			//					CharacterSheet owner = ACS.getInstance().getCharacterSheet();
			//					AttributesRecord attr = owner.getAttributesRecord();
			//					int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
			//					int spellPower = record.getLevel();
			//					int charLevel = owner.getHeaderRecord().getLevel();
			//					mResearchChanceLabel[index].setText(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
			//					mResearchChanceLabel[index].revalidate();
			//				}
			//			} else {
			//				mSpellLabel[index].removeMouseListener(this);
			//				mSpellLabel[index].setText(CHOOSE_SCHOOL);
			//				mSpellLabel[index].setForeground(getOldColor(index));
			//				setOldColor(index, null);
			//			}
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
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		mSchoolPopup.getMenu().setEnabled(enable);

		mCostField.setEnabled(enable);
		mCostField.setEditable(enable);

		mDPPerWeekField.setEnabled(enable);
		mDPPerWeekField.setEditable(enable);
	}

	private SpellRecord selectSpell(String school) {
		SpellSelector selector = new SpellSelector((CharacterSheet) ((DeterminationPointsDisplay) getOwner()).getOwner(), school);
		SpellRecord record = selector.getSpellToLearn();
		if (record != null) {
			mSpellLabel.setText(record.getName());
			return record;
			//					mCurrentList.addToKnownSpells(record);
		}
		return null;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// DW do we need to do anything here?
	}

	@Override

	public void mouseClicked(MouseEvent e) {
		//		Object source = e.getSource();
		//
		//		if (source instanceof JLabel) {
		//			JLabel label = (JLabel) source;
		//			label.getParent();
		//
		//			int index = getIndexInPanel((JPanel) label.getParent(), source);
		//			// DW handle -1 as failure to find index
		//
		//			String text = mSchoolPopup[index].getSelectedItem();
		//			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
		//				SpellRecord record = selectSpell(text, index);
		//				if (record != null) {
		//					((JLabel) source).removeMouseListener(this);
		//					mSpellLabel[index].setForeground(getOldColor(index));
		//					setOldColor(index, null);
		//				}
		//			}
		//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing to do
	}

	@Override
	protected void loadDisplay() {
		ArrayList<MagicSpellDeterminationRecord> list = DeterminationList.getMagicSpellRecords();
		JPanel wrapper = new JPanel();
		CharacterSheet owner = ACS.getInstance().getCharacterSheet();
		if (list.size() > 0) {
			for (MagicSpellDeterminationRecord record : list) {
				JLabel schoolLabel = new JLabel(record.getSchool());
				JLabel spellLabel = new JLabel(record.getSpell());
				JLabel costLabel = new JLabel(String.valueOf(record.getCost()));
				JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				AttributesRecord attr = owner.getAttributesRecord();
				int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
				int spellPower = 0;
				int charLevel = owner.getHeaderRecord().getLevel();
				JLabel researchChanceLabel = new JLabel(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
				// DW _Count successful vs attempted
				JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				JLabel startDateLabel = new JLabel(record.getStartDate());
				JLabel endDateLabel = new JLabel(record.getEndDate());

				wrapper.add(schoolLabel);
				wrapper.add(spellLabel);
				wrapper.add(costLabel);
				wrapper.add(usedLabel);
				wrapper.add(researchChanceLabel);
				wrapper.add(successLabel);
				wrapper.add(startDateLabel);
				wrapper.add(endDateLabel);
			}
		}
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, SUCCESS_TEXT, SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel() {
		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		mSchoolColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSpellColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mCostColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mResearchChanceColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders();

		outerWrapper.add(mSchoolColumn);
		outerWrapper.add(mSpellColumn);
		outerWrapper.add(mCostColumn);
		outerWrapper.add(mDPPerWeekColumn);
		outerWrapper.add(mDPSpentColumn);
		outerWrapper.add(mResearchChanceColumn);
		outerWrapper.add(mSuccessfulColumn);
		outerWrapper.add(mStartDateColumn);
		outerWrapper.add(mEndDateColumn);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders() {
		mSchoolColumn.add(new JLabel(SCHOOL_LABEL));
		mSpellColumn.add(new JLabel(SPELL_LABEL));
		mCostColumn.add(new JLabel(COST_LABEL));
		mDPPerWeekColumn.add(new JLabel(DP_WEEK_LABEL));
		mDPSpentColumn.add(new JLabel(USED_LABEL));
		mResearchChanceColumn.add(new JLabel(RESEARCH_CHANCE));
		mSuccessfulColumn.add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn.add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn.add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mSchoolColumn.removeAll();
		mSpellColumn.removeAll();
		mCostColumn.removeAll();
		mDPPerWeekColumn.removeAll();
		mDPSpentColumn.removeAll();
		mResearchChanceColumn.removeAll();
		mSuccessfulColumn.removeAll();
		mStartDateColumn.removeAll();
		mEndDateColumn.removeAll();
		generateHeaders();

	}

	void addRecord(MagicSpellDeterminationRecord record) {
		if (record != null) {
			JLabel schoolLabel = new JLabel(record.getSchool());
			JLabel spellLabel = new JLabel(record.getSpell());
			JLabel costLabel = new JLabel(String.valueOf(record.getCost()));
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			CharacterSheet owner = ACS.getInstance().getCharacterSheet();
			AttributesRecord attr = owner.getAttributesRecord();
			int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
			int spellPower = 0;
			int charLevel = owner.getHeaderRecord().getLevel();
			JLabel researchChanceLabel = new JLabel(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			JLabel endDateLabel = new JLabel(record.getEndDate());

			mSchoolColumn.add(schoolLabel);
			mSpellColumn.add(spellLabel);
			mCostColumn.add(costLabel);
			mDPPerWeekColumn.add(DPPerWeekLabel);
			mDPSpentColumn.add(usedLabel);
			mResearchChanceColumn.add(researchChanceLabel);
			mSuccessfulColumn.add(successLabel);
			mStartDateColumn.add(startDateLabel);
			mEndDateColumn.add(endDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and End Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();
		TKFloatFilter floatFilter = TKFloatFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel schoolColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel spellColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel costColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel researchChanceColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		JLabel header = new JLabel(SCHOOL_LABEL);
		schoolColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		spellColumn.add(new JLabel(SPELL_LABEL));
		costColumn.add(new JLabel(COST_LABEL));
		dPPerWeekColumn.add(new JLabel(DP_WEEK_LABEL));
		dPSpentColumn.add(new JLabel("Used")); //$NON-NLS-1$
		researchChanceColumn.add(new JLabel(RESEARCH_CHANCE));
		successfulColumn.add(new JLabel("Successful")); //$NON-NLS-1$

		mSchoolPopup = new TKPopupMenu(MagicAreaPopup.generateMagicAreaPopup(this, this));
		mSchoolPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mSchoolPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mSchoolPopup.getPreferredSize().width, POPUP_HEIGHT);
		mSchoolPopup.setMaximumSize(size2);
		schoolColumn.add(mSchoolPopup);

		mSpellLabel = TKComponentHelpers.createLabel(CHOOSE_SCHOOL);
		Dimension size3 = new Dimension(mSpellLabel.getPreferredSize().width, POPUP_HEIGHT);
		mSpellLabel.setMaximumSize(size3);
		mSpellLabel.setPreferredSize(size3);
		mOldColor = null;
		spellColumn.add(mSpellLabel);

		// DW this filter may need to be a float filter if cost can have copper
		mCostField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, floatFilter);
		costColumn.add(mCostField);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		dPPerWeekColumn.add(mDPPerWeekField);

		mResearchChanceLabel = new JLabel(String.valueOf(currentMaintenance));
		mResearchChanceLabel.setMinimumSize(size);
		mResearchChanceLabel.setPreferredSize(size);
		mResearchChanceColumn.add(mResearchChanceLabel);

		// DW figure cost: 6 X (spell power + 1/squared)
		mDPSpentLabel = new JLabel(currentlySpent + " / ");// + getDPCost()); //$NON-NLS-1$
		mDPSpentLabel.setMinimumSize(size);
		mDPSpentLabel.setPreferredSize(size);
		dPSpentColumn.add(mDPSpentLabel);

		mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
		mSuccessfulLabel.setMinimumSize(size);
		mSuccessfulLabel.setPreferredSize(size);
		successfulColumn.add(mSuccessfulLabel);

		mStartDateLabel = new JLabel();
		mEndDateLabel = new JLabel();

		schoolColumn.add(Box.createVerticalGlue());
		spellColumn.add(Box.createVerticalGlue());
		costColumn.add(Box.createVerticalGlue());
		dPPerWeekColumn.add(Box.createVerticalGlue());
		dPSpentColumn.add(Box.createVerticalGlue());
		researchChanceColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(schoolColumn);
		outerWrapper.add(spellColumn);
		outerWrapper.add(costColumn);
		outerWrapper.add(dPPerWeekColumn);
		outerWrapper.add(dPSpentColumn);
		outerWrapper.add(researchChanceColumn);
		outerWrapper.add(successfulColumn);

		updateEnabledState();

		buttonWrapper.add(outerWrapper, BorderLayout.NORTH);
		buttonWrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		return buttonWrapper;
	}

	//		// DW _add Start and End Date (popup?)
	//		int completed = 0;
	//		int attempted = 0;
	//
	//		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();
	//		TKFloatFilter floatFilter = TKFloatFilter.getFilterInstance();
	//
	//		mSchoolPopup = new TKPopupMenu[ROWS];
	//		mSpellLabel = new JLabel[ROWS];
	//		mOldColor = new Color[ROWS];
	//		mCostField = new JTextField[ROWS];
	//		mDPPerWeekField = new JTextField[ROWS];
	//		mUsedLabel = new JLabel[ROWS];
	//		mCurrentlySpentLabel = new int[ROWS];
	//		mDPCost = new int[ROWS];
	//		mResearchChanceLabel = new JLabel[ROWS];
	//		mSuccessfulLabel = new JLabel[ROWS];
	//		mStartDateLabel = new JLabel[ROWS];
	//		mEndDateLabel = new JLabel[ROWS];
	//
	//		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
	//		JPanel schoolPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
	//		JPanel spellPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
	//		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
	//		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
	//		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
	//		JPanel researchChancePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
	//		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
	//
	//		schoolPanel.add(new JLabel(SCHOOL_LABEL, SwingConstants.CENTER));
	//		spellPanel.add(new JLabel(SPELL_LABEL, SwingConstants.CENTER));
	//		costPanel.add(new JLabel(COST_LABEL, SwingConstants.CENTER));
	//		dpPerWeekPanel.add(new JLabel(DP_WEEK_LABEL, SwingConstants.CENTER));
	//		dpSpentPanel.add(new JLabel(USED_LABEL, SwingConstants.CENTER));
	//		researchChancePanel.add(new JLabel(RESEARCH_CHANCE, SwingConstants.CENTER));
	//		successfulPanel.add(new JLabel(SUCCESSFUL_LABEL, SwingConstants.CENTER));
	//
	//		Dimension size = new Dimension(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT);
	//		for (int i = 0; i < ROWS; i++) {
	//			mSchoolPopup = new TKPopupMenu(MagicAreaPopup.generateMagicAreaPopup(this, this));
	//			mSchoolPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
	//			Dimension size2 = new Dimension(mSchoolPopup.getPreferredSize().width, TEXT_FIELD_HEIGHT);
	//			mSchoolPopup.setMinimumSize(size2);
	//			mSchoolPopup.setPreferredSize(size2);
	//			schoolPanel.add(mSchoolPopup);
	//
	//			mSpellLabel = TKComponentHelpers.createLabel(CHOOSE_SCHOOL);
	//			mSpellLabel.setMinimumSize(size2);
	//			mSpellLabel.setPreferredSize(size2);
	//			mOldColor = null;
	//			spellPanel.add(mSpellLabel);
	//
	//			// DW this filter may need to be a float filter if cost can have copper
	//			mCostField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, floatFilter);
	//			costPanel.add(mCostField);
	//
	//			mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, intFilter);
	//			dpPerWeekPanel.add(mDPPerWeekField);
	//
	//			mUsedLabel = new JLabel(mCurrentlySpentLabel + " / " + mDPCost); //$NON-NLS-1$
	//			mUsedLabel.setMinimumSize(size);
	//			mUsedLabel.setPreferredSize(size);
	//			mUsedLabel.setSize(size);
	//			dpSpentPanel.add(mUsedLabel);
	//
	//			mResearchChanceLabel = new JLabel(SUCCESS_TOOLTIP);
	//			mResearchChanceLabel.setMinimumSize(size);
	//			mResearchChanceLabel.setPreferredSize(size);
	//			mResearchChanceLabel.setSize(size);
	//			researchChancePanel.add(mResearchChanceLabel);
	//
	//			mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
	//			mSuccessfulLabel.setMinimumSize(size);
	//			mSuccessfulLabel.setPreferredSize(size);
	//			successfulPanel.add(mSuccessfulLabel);
	//
	//			mStartDateLabel = new JLabel();
	//			mEndDateLabel = new JLabel();
	//		}
	//		spellPanel.add(Box.createVerticalGlue());
	//		dpSpentPanel.add(Box.createVerticalGlue());
	//		researchChancePanel.add(Box.createVerticalGlue());
	//		successfulPanel.add(Box.createVerticalGlue());
	//
	//		Dimension size1 = new Dimension(100, 200);
	//		costPanel.setMaximumSize(size1);
	//
	//		outerWrapper.add(schoolPanel);
	//		outerWrapper.add(spellPanel);
	//		outerWrapper.add(costPanel);
	//		outerWrapper.add(dpPerWeekPanel);
	//		outerWrapper.add(dpSpentPanel);
	//		outerWrapper.add(researchChancePanel);
	//		outerWrapper.add(successfulPanel);
	//
	//		updateEnabledState();
	//
	//		return outerWrapper;
	//	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		ArrayList<MagicSpellDeterminationRecord> list = DeterminationList.getMagicSpellRecords();
		if (list.size() > 0) {
			for (MagicSpellDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mSpellLabel.getText().isBlank() || mSchoolPopup.getSelectedItem().equals(MagicAreaPopup.SELECT_MAGIC_AREA) || mDPPerWeekField.getText().isBlank())) {
		return true;
		//			}
		//		}
		//		return false;
	}

	private int getDPCost(int level) {
		level++;
		int cost = level * level * 6;

		return cost;
	}

	private int getDPCost(SpellRecord spellRecord) {
		return getDPCost(spellRecord.getLevel());
	}

	@Override
	protected String getSuccessText() {
		//		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		//		if (record == null) {
		return SUCCESS_TEXT;
		//		}
		//		int success = record.getModifiedStat(AttributesRecord.INT);
		//		return SUCCESS_TEXT1 + success;
	}

	/** @return The oldColor. */
	public Color getOldColor() {
		return mOldColor;
	}

	/** @param oldColor The value to set for oldColor. */
	public void setOldColor(Color oldColor) {
		mOldColor = oldColor;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
