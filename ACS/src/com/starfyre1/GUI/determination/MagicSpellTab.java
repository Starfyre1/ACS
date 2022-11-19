/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.component.MagicAreaPopup;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.GUI.spells.SpellSelector;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKFloatFilter;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MagicSpellTab extends DeterminationTab implements ItemListener, MouseListener {
	private static final String	USED_LABEL				= "DP Spent";														//$NON-NLS-1$
	private static final String	DP_WEEK_LABEL			= "DP/Week";														//$NON-NLS-1$
	private static final String	COST_LABEL				= "$ Cost:";														//$NON-NLS-1$
	private static final String	SPELL_LABEL				= "Spell";															//$NON-NLS-1$
	private static final String	SCHOOL_LABEL			= "School";															//$NON-NLS-1$
	private static final String	RESEARCH_CHANCE			= "Chance";															//$NON-NLS-1$

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
	private static final String	SUCCESS_TEXT			= "Success";														//$NON-NLS-1$
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
	private int					mDPCost;
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
				MagicSpellDeterminationRecord record = new MagicSpellDeterminationRecord(mSpellLabel.getText(), mSchoolPopup.getSelectedItem(), TKStringHelpers.getFloatValue(mCostField.getText(), 0f), TKStringHelpers.getIntValue(mResearchChanceLabel.getText(), 0), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), mDPCost, CampaignDateChooser.getCampaignDate(), null);
				DeterminationList.addMagicSpellRecord(record);
				((DeterminationPointsDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
		} else if (source instanceof JMenuItem) {
			mSpellLabel.setText(CHOOSE_SPELL);
			mSpellLabel.addMouseListener(this);
			if (getOldColor() == null) {
				setOldColor(mSpellLabel.getForeground());
			}
			mSpellLabel.setForeground(Color.BLUE);
			String text = ((JMenuItem) source).getText();
			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
				SpellRecord record = selectSpell(text);
				if (record != null) {
					mSpellLabel.removeMouseListener(this);
					mSpellLabel.setForeground(getOldColor());
					setOldColor(null);
					mDPCost = getDPCost(record);
					mDPSpentLabel.setText(0 + " / " + mDPCost); //$NON-NLS-1$

					CharacterSheet owner = ACS.getInstance().getCharacterSheet();
					AttributesRecord attr = owner.getAttributesRecord();
					int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
					int spellPower = record.getLevel();
					int charLevel = owner.getHeaderRecord().getLevel();
					mResearchChanceLabel.setText(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
					mResearchChanceLabel.revalidate();
				}
			} else {
				mSpellLabel.removeMouseListener(this);
				mSpellLabel.setText(CHOOSE_SCHOOL);
				mSpellLabel.setForeground(getOldColor());
				setOldColor(null);
			}
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
		Object source = e.getSource();

		if (source instanceof JLabel) {
			JLabel label = (JLabel) source;
			label.getParent();

			String text = mSchoolPopup.getSelectedItem();
			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
				SpellRecord record = selectSpell(text);
				if (record != null) {
					((JLabel) source).removeMouseListener(this);
					mSpellLabel.setForeground(getOldColor());
					setOldColor(null);
				}
			}
		}
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
		return createPage(createCenterPanel(), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, null, SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
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
		mSuccessfulColumn.add(new JLabel(SUCCESS_TEXT));
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

		// DW figure cost: 6 X (spell power + 1/squared)
		mDPSpentLabel = new JLabel(currentlySpent + " / " + mDPCost); //$NON-NLS-1$
		mDPSpentLabel.setMinimumSize(size);
		mDPSpentLabel.setPreferredSize(size);
		dPSpentColumn.add(mDPSpentLabel);

		mResearchChanceLabel = new JLabel(String.valueOf(currentMaintenance));
		mResearchChanceLabel.setMinimumSize(size);
		mResearchChanceLabel.setPreferredSize(size);
		researchChanceColumn.add(mResearchChanceLabel);

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
		return null; //SUCCESS_TEXT;
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
