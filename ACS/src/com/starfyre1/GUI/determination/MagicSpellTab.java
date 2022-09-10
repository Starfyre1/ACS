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
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.spells.SpellRecord;
import com.starfyre1.startup.ACS;

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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
	private static final String	MAINTAINENCE_TEXT		= "";																//$NON-NLS-1$
	private static final String	MAGIC_SPELL_TEXT		= MAGIC_SPELL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP			= "TBD";															//$NON-NLS-1$
	private static final String	SUCCESS_TEXT			= "Success: TBD";													//$NON-NLS-1$
	private static final String	CHOOSE_SCHOOL			= "Choose School";													//$NON-NLS-1$
	private static final String	CHOOSE_SPELL			= "Choose Spell";													//$NON-NLS-1$

	private static final int	ROWS					= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]		mSchoolPopup;
	private JLabel[]			mSpellLabel;
	private JTextField[]		mCostField;
	private JTextField[]		mDPPerWeekField;
	private JLabel[]			mUsedLabel;
	private int[]				mDPCost;
	private int[]				mCurrentlySpentLabel;
	private JLabel[]			mResearchChanceLabel;
	private JLabel[]			mSuccessfulLabel;
	private JLabel[]			mStartDateLabel;
	private JLabel[]			mCompletionDateLabel;

	private Color[]				mOldColor;

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
			int index = getPanelIndex((JMenuItem) source);
			// DW handle -1 as failure to find index
			mSpellLabel[index].setText(CHOOSE_SPELL);
			mSpellLabel[index].addMouseListener(this);
			if (getOldColor(index) == null) {
				setOldColor(index, mSpellLabel[index].getForeground());
			}
			mSpellLabel[index].setForeground(Color.BLUE);
			String text = ((JMenuItem) source).getText();
			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
				SpellRecord record = selectSpell(text, index);
				if (record != null) {
					mSpellLabel[index].removeMouseListener(this);
					mSpellLabel[index].setForeground(getOldColor(index));
					setOldColor(index, null);
					mDPCost[index] = getDPCost(record);
					mUsedLabel[index].setText(mCurrentlySpentLabel[index] + " / " + mDPCost[index]); //$NON-NLS-1$

					CharacterSheet owner = ACS.getInstance().getCharacterSheet();
					AttributesRecord attr = owner.getAttributesRecord();
					int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
					int spellPower = record.getLevel();
					int charLevel = owner.getHeaderRecord().getLevel();
					mResearchChanceLabel[index].setText(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
					mResearchChanceLabel[index].revalidate();
				}
			} else {
				mSpellLabel[index].removeMouseListener(this);
				mSpellLabel[index].setText(CHOOSE_SCHOOL);
				mSpellLabel[index].setForeground(getOldColor(index));
				setOldColor(index, null);
			}
		} else if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				ArrayList<MagicSpellDeterminationRecord> list = getRecordsToLearn();
				for (MagicSpellDeterminationRecord record : list) {
					DeterminationList.addMagicSpellRecord(record);
				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}

	}

	private SpellRecord selectSpell(String school, int index) {
		SpellSelector selector = new SpellSelector((CharacterSheet) ((DeterminationPointsDisplay) getOwner()).getOwner(), school);
		SpellRecord record = selector.getSpellToLearn();
		if (record != null) {
			mSpellLabel[index].setText(record.getName());
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

			int index = getIndexInPanel((JPanel) label.getParent(), source);
			// DW handle -1 as failure to find index

			String text = mSchoolPopup[index].getSelectedItem();
			if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text)) {
				SpellRecord record = selectSpell(text, index);
				if (record != null) {
					((JLabel) source).removeMouseListener(this);
					mSpellLabel[index].setForeground(getOldColor(index));
					setOldColor(index, null);
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

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mSchoolPopup[i].getMenu().setEnabled(enable);

			mCostField[i].setEnabled(enable);
			mCostField[i].setEditable(enable);

			mDPPerWeekField[i].setEnabled(enable);
			mDPPerWeekField[i].setEditable(enable);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<MagicSpellDeterminationRecord> list = DeterminationList.getMagicSpellRecords();
		CharacterSheet owner = ACS.getInstance().getCharacterSheet();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				MagicSpellDeterminationRecord record = list.get(i);

				mSchoolPopup[i].selectPopupMenuItem(record.getSchool());
				mSpellLabel[i].setText(record.getSpell());
				mCostField[i].setText(String.valueOf(record.getCost()));
				mDPCost[i] = record.getDPCost();
				mCurrentlySpentLabel[i] = record.getDPTotalSpent();
				mUsedLabel[i].setText(mCurrentlySpentLabel[i] + " / " + mDPCost[i]); //$NON-NLS-1$
				AttributesRecord attr = owner.getAttributesRecord();
				int stats = (attr.getModifiedStat(AttributesRecord.INT) + attr.getModifiedStat(AttributesRecord.WIS)) / 2;
				int spellPower = 0;
				int charLevel = owner.getHeaderRecord().getLevel();
				mResearchChanceLabel[i].setText(String.valueOf(MageList.spellResearchChance(stats, spellPower, charLevel)));
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
		return createPage(createCenterPanel(), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, SUCCESS_TEXT, SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();
		TKFloatFilter floatFilter = TKFloatFilter.getFilterInstance();

		mSchoolPopup = new TKPopupMenu[ROWS];
		mSpellLabel = new JLabel[ROWS];
		mOldColor = new Color[ROWS];
		mCostField = new JTextField[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mUsedLabel = new JLabel[ROWS];
		mCurrentlySpentLabel = new int[ROWS];
		mDPCost = new int[ROWS];
		mResearchChanceLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];
		mStartDateLabel = new JLabel[ROWS];
		mCompletionDateLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel schoolPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel spellPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel researchChancePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		schoolPanel.add(new JLabel(SCHOOL_LABEL, SwingConstants.CENTER));
		spellPanel.add(new JLabel(SPELL_LABEL, SwingConstants.CENTER));
		costPanel.add(new JLabel(COST_LABEL, SwingConstants.CENTER));
		dpPerWeekPanel.add(new JLabel(DP_WEEK_LABEL, SwingConstants.CENTER));
		dpSpentPanel.add(new JLabel(USED_LABEL, SwingConstants.CENTER));
		researchChancePanel.add(new JLabel(RESEARCH_CHANCE, SwingConstants.CENTER));
		successfulPanel.add(new JLabel(SUCCESSFUL_LABEL, SwingConstants.CENTER));

		Dimension size = new Dimension(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT);
		for (int i = 0; i < ROWS; i++) {
			mSchoolPopup[i] = new TKPopupMenu(MagicAreaPopup.generateMagicAreaPopup(this, this));
			mSchoolPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mSchoolPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mSchoolPopup[i].setMinimumSize(size2);
			mSchoolPopup[i].setPreferredSize(size2);
			schoolPanel.add(mSchoolPopup[i]);

			mSpellLabel[i] = TKComponentHelpers.createLabel(CHOOSE_SCHOOL);
			mSpellLabel[i].setMinimumSize(size2);
			mSpellLabel[i].setPreferredSize(size2);
			mOldColor[i] = null;
			spellPanel.add(mSpellLabel[i]);

			// DW this filter may need to be a float filter if cost can have copper
			mCostField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, floatFilter);
			costPanel.add(mCostField[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, intFilter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			mUsedLabel[i] = new JLabel(mCurrentlySpentLabel[i] + " / " + mDPCost[i]); //$NON-NLS-1$
			mUsedLabel[i].setMinimumSize(size);
			mUsedLabel[i].setPreferredSize(size);
			mUsedLabel[i].setSize(size);
			dpSpentPanel.add(mUsedLabel[i]);

			mResearchChanceLabel[i] = new JLabel(SUCCESS_TOOLTIP);
			mResearchChanceLabel[i].setMinimumSize(size);
			mResearchChanceLabel[i].setPreferredSize(size);
			mResearchChanceLabel[i].setSize(size);
			researchChancePanel.add(mResearchChanceLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successfulPanel.add(mSuccessfulLabel[i]);

			mStartDateLabel[i] = new JLabel();
			mCompletionDateLabel[i] = new JLabel();
		}
		spellPanel.add(Box.createVerticalGlue());
		dpSpentPanel.add(Box.createVerticalGlue());
		researchChancePanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		Dimension size1 = new Dimension(100, 200);
		costPanel.setMaximumSize(size1);

		outerWrapper.add(schoolPanel);
		outerWrapper.add(spellPanel);
		outerWrapper.add(costPanel);
		outerWrapper.add(dpPerWeekPanel);
		outerWrapper.add(dpSpentPanel);
		outerWrapper.add(researchChancePanel);
		outerWrapper.add(successfulPanel);

		updateEnabledState();

		return outerWrapper;
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

	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mSpellLabel[i].getText().isBlank() || mSchoolPopup[i].getSelectedItem().equals(MagicAreaPopup.SELECT_MAGIC_AREA) || mDPPerWeekField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<MagicSpellDeterminationRecord> getRecordsToLearn() {
		ArrayList<MagicSpellDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mSpellLabel[i].getText().isBlank() || mSchoolPopup[i].getSelectedItem().equals(MagicAreaPopup.SELECT_MAGIC_AREA) || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new MagicSpellDeterminationRecord(mSpellLabel[i].getText().trim(), mSchoolPopup[i].getSelectedItem(), TKStringHelpers.getFloatValue(mCostField[i].getText().trim(), 0f), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), mDPCost[i], campaignDate, campaignDate));
			}
		}
		return list;
	}

	private int getDPCost(SpellRecord spellRecord) {
		int level = spellRecord.getLevel() + 1;
		int cost = level * level * 6;

		return cost;
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
	public Color getOldColor(int which) {
		return mOldColor[which];
	}

	/** @param oldColor The value to set for oldColor. */
	public void setOldColor(int which, Color oldColor) {
		mOldColor[which] = oldColor;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
