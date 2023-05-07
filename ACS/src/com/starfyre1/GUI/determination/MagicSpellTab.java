/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.component.MagicAreaPopup;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.GUI.spells.SpellSelector;
import com.starfyre1.ToolKit.TKComponentHelpers;
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
	// DW not implemented yet - Determination dates
	//	private JLabel				mStartDateLabel;
	//	private JLabel				mEndDateLabel;

	private Color				mOldColor;

	private JPanel				mSchoolColumn[];
	private JPanel				mSpellColumn[];
	private JPanel				mCostColumn[];
	private JPanel				mDPPerWeekColumn[];
	private JPanel				mDPSpentColumn[];
	private JPanel				mResearchChanceColumn[];
	private JPanel				mSuccessfulColumn[];
	private JPanel				mStartDateColumn[];
	private JPanel				mEndDateColumn[];

	private JDialog				mNewEntryDialog;

	private int					mSpellPower				= 0;

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
				String startDate = CampaignDateChooser.getCampaignDate();
				MagicSpellDeterminationRecord record = new MagicSpellDeterminationRecord(mSpellLabel.getText(), mSchoolPopup.getSelectedItem(), mSpellPower, TKStringHelpers.getFloatValue(mCostField.getText(), 0f), TKStringHelpers.getIntValue(mResearchChanceLabel.getText(), 0), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), mDPCost, startDate, startDate);
				DeterminationList.addMagicSpellRecord(record);
				((DeterminationDisplay) getOwner()).addRecords(true);
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
					mSpellPower = record.getLevel();
					int charLevel = owner.getHeaderRecord().getLevel();
					mResearchChanceLabel.setText(String.valueOf(MageList.spellResearchChance(stats, mSpellPower, charLevel)));
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
		SpellSelector selector = new SpellSelector((CharacterSheet) ((DeterminationDisplay) getOwner()).getOwner(), school);
		SpellRecord record = selector.getSpellToLearn();
		if (record != null) {
			mSpellLabel.setText(record.getName());
			mSpellPower = record.getLevel();
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
	protected Component createDisplay() {
		mSchoolColumn = new JPanel[2];
		mSpellColumn = new JPanel[2];
		mCostColumn = new JPanel[2];
		mDPPerWeekColumn = new JPanel[2];
		mDPSpentColumn = new JPanel[2];
		mResearchChanceColumn = new JPanel[2];
		mSuccessfulColumn = new JPanel[2];
		mStartDateColumn = new JPanel[2];
		mEndDateColumn = new JPanel[2];

		return createPage(createCenterPanel(false), createCenterPanel(true), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, null, SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel(boolean complete) {
		int test = complete ? 1 : 0;

		JPanel outerWrapper = new JPanel();
		mSchoolColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSpellColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mCostColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mResearchChanceColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders(complete);

		outerWrapper.add(mSchoolColumn[test]);
		outerWrapper.add(mSpellColumn[test]);
		outerWrapper.add(mCostColumn[test]);
		outerWrapper.add(mDPPerWeekColumn[test]);
		outerWrapper.add(mDPSpentColumn[test]);
		outerWrapper.add(mResearchChanceColumn[test]);
		outerWrapper.add(mSuccessfulColumn[test]);
		outerWrapper.add(mStartDateColumn[test]);
		outerWrapper.add(mEndDateColumn[test]);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders(boolean complete) {
		int test = complete ? 1 : 0;

		mSchoolColumn[test].add(new JLabel(SCHOOL_LABEL));
		mSpellColumn[test].add(new JLabel(SPELL_LABEL));
		mCostColumn[test].add(new JLabel(COST_LABEL));
		mDPPerWeekColumn[test].add(new JLabel(DP_WEEK_LABEL));
		mDPSpentColumn[test].add(new JLabel(USED_LABEL));
		mResearchChanceColumn[test].add(new JLabel(RESEARCH_CHANCE));
		mSuccessfulColumn[test].add(new JLabel(SUCCESS_TEXT));
		mStartDateColumn[test].add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn[test].add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mSchoolColumn[0].removeAll();
		mSpellColumn[0].removeAll();
		mCostColumn[0].removeAll();
		mDPPerWeekColumn[0].removeAll();
		mDPSpentColumn[0].removeAll();
		mResearchChanceColumn[0].removeAll();
		mSuccessfulColumn[0].removeAll();
		mStartDateColumn[0].removeAll();
		mEndDateColumn[0].removeAll();
		generateHeaders(false);

		mSchoolColumn[1].removeAll();
		mSpellColumn[1].removeAll();
		mCostColumn[1].removeAll();
		mDPPerWeekColumn[1].removeAll();
		mDPSpentColumn[1].removeAll();
		mResearchChanceColumn[1].removeAll();
		mSuccessfulColumn[1].removeAll();
		mStartDateColumn[1].removeAll();
		mEndDateColumn[1].removeAll();
		generateHeaders(true);

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
			int spellPower = record.getLevel();
			int charLevel = owner.getHeaderRecord().getLevel();
			JLabel researchChanceLabel = new JLabel(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			String endDate = record.getEndDate();
			JLabel endDateLabel = new JLabel(endDate);

			int which = endDate.isBlank() ? 0 : 1;
			mSchoolColumn[which].add(schoolLabel);
			mSpellColumn[which].add(spellLabel);
			mCostColumn[which].add(costLabel);
			mDPPerWeekColumn[which].add(DPPerWeekLabel);
			mDPSpentColumn[which].add(usedLabel);
			mResearchChanceColumn[which].add(researchChanceLabel);
			mSuccessfulColumn[which].add(successLabel);
			mStartDateColumn[which].add(startDateLabel);
			mEndDateColumn[which].add(endDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and End Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

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
		mCostField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, ACS.FLOAT_FILTER);
		costColumn.add(mCostField);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, ACS.INTEGER_FILTER);
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

		// DW not implemented yet - Determination dates
		//		mStartDateLabel = new JLabel();
		//		mEndDateLabel = new JLabel();

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
