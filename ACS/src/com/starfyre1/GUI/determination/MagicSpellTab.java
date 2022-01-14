/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.component.MagicAreaPopup;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.GUI.spells.SpellSelector;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MagicSpellTab extends DeterminationTab implements ActionListener, ItemListener, MouseListener {
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
	private static final int	COST					= 0;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]		mSchoolPopup;
	private JLabel[]			mSpellLabel;
	private JTextField[]		mCostField;
	private JTextField[]		mDPPerWeekField;

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
		JPopupMenu popup2 = (JPopupMenu) menu.getParent();
		ComboMenu menu2 = (ComboMenu) popup2.getInvoker();
		TKPopupMenu popup3 = (TKPopupMenu) menu2.getParent();

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
				}
			}
		}
	}

	private SpellRecord selectSpell(String text, int index) {
		SpellSelector selector = new SpellSelector((CharacterSheet) ((DeterminationPointsDisplay) getOwner()).getOwner(), text);
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

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TEXT, SUCCESS_TEXT, SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mSchoolPopup = new TKPopupMenu[ROWS];
		mSpellLabel = new JLabel[ROWS];
		mOldColor = new Color[ROWS];
		mCostField = new JTextField[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel schoolPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel spellPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel header = new JLabel("School:", SwingConstants.CENTER); //$NON-NLS-1$
		schoolPanel.add(header);
		Dimension size = new Dimension(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT);
		spellPanel.add(new JLabel("Spell:", SwingConstants.CENTER)); //$NON-NLS-1$
		costPanel.add(new JLabel("Cost:", SwingConstants.CENTER)); //$NON-NLS-1$
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mSchoolPopup[i] = new TKPopupMenu(MagicAreaPopup.generateMagicAreaPopup(this, this));
			Dimension size2 = new Dimension(mSchoolPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mSchoolPopup[i].setMinimumSize(size2);
			mSchoolPopup[i].setPreferredSize(size2);
			schoolPanel.add(mSchoolPopup[i]);

			mSpellLabel[i] = TKComponentHelpers.createLabel(CHOOSE_SCHOOL);
			mSpellLabel[i].setMinimumSize(size);
			mSpellLabel[i].setPreferredSize(size);
			mOldColor[i] = null;
			spellPanel.add(mSpellLabel[i]);

			// DW this filter may need to be a float filter if cost can have copper
			mCostField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			costPanel.add(mCostField[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(usedLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);

		}
		spellPanel.add(Box.createVerticalGlue());
		dpSpentPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		Dimension size1 = new Dimension(100, 200);
		costPanel.setMaximumSize(size1);

		outerWrapper.add(schoolPanel);
		outerWrapper.add(spellPanel);
		outerWrapper.add(costPanel);
		outerWrapper.add(dpPerWeekPanel);
		outerWrapper.add(dpSpentPanel);
		outerWrapper.add(successfulPanel);

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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
				list.add(new MagicSpellDeterminationRecord(mSpellLabel[i].getText().trim(), mSchoolPopup[i].getSelectedItem(), TKStringHelpers.getIntValue(mCostField[i].getText().trim(), 0), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), campaignDate));
			}
		}
		return list;
	}

	@Override
	protected String getSuccessText() {
		//		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		//		if (record == null) {
		return "TBD"; //$NON-NLS-1$
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
