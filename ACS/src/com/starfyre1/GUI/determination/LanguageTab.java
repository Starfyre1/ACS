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
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
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

public class LanguageTab extends DeterminationTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must successfully learn the language twice";								// //$NON-NLS-1$

	static final String				LANGUAGE_TAB_TITLE		= "Languages";																												//$NON-NLS-1$
	static final String				LANGUAGE_TAB_TOOLTIP	= "To learn a new language or become fluent:";																				//$NON-NLS-1$

	private static final String		CHOOSE_LANGUAGE			= "Choose Language";																										//$NON-NLS-1$
	private static final String		CHOOSE_SOURCE			= "Choose Source";																											//$NON-NLS-1$
	private static final String		IMMERSIVE				= "Immersive";																												//$NON-NLS-1$
	private static final String		TUTOR					= "Tutor";																													//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 40, 60, 80 (Immersive, Tutor, Book)";																				//$NON-NLS-1$
	private static final String		MAINTAINENCE_TEXT		= "Maintain: 1 DP / week for Fluent";																						//$NON-NLS-1$
	private static final String		LANGUAGE_TEXT			= LANGUAGE_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 - (1/4 level) < (Wisdom)";																							//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: (1D20 - ";																										//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= ") < ";																													//$NON-NLS-1$

	private static final String		COMMON_DESCRIPTION		= "A mixture of many languages, this trade language is spoken world wide on Athri.";										//$NON-NLS-1$
	private static final String		EA_DESCRIPTION			= "Elven, spoken through the elven and human kingdoms above the ground.";													//$NON-NLS-1$
	private static final String		LEA_DESCRIPTION			= "High Elven, spoken by the elite and royalty of the Elven society.";														//$NON-NLS-1$
	private static final String		TRIA_DESCRIPTION		= "Spoken by Tsiri (Dark Elven) above and below ground.";																	//$NON-NLS-1$
	private static final String		TETSRI_DESCRIPTION		= "Spoken by the humans of the Tetsuru.";																					//$NON-NLS-1$
	private static final String		NORTHRIN_DESCRIPTION	= "Spoken by the humans of the Northlands.";																				//$NON-NLS-1$
	private static final String		ERU_DESCRIPTION			= "Spoken by the d'etri or Dwarrow of the world.";																			//$NON-NLS-1$
	private static final String		GALON_DESCRIPTION		= "Spoken by the E'Sprey or Giants of T'Sal.";																				//$NON-NLS-1$
	private static final String		UTA_DESCRIPTION			= "Spoken by the dark creatures of the world.";																				//$NON-NLS-1$
	private static final String		DUTA_DESCRIPTION		= "The Silent language, Thieves Tongue, a Sign Language with many variations and many names.";								//$NON-NLS-1$
	private static final String		UTRU_DESCRIPTION		= "Spoken by Goblinkin around the world.";																					//$NON-NLS-1$
	private static final String		KAZQ_DESCRIPTION		= "Spoken by most Dwarves around the world.";																				//$NON-NLS-1$
	private static final String		KAZTCH_DESCRIPTION		= "Dwarves war tongue";																										//$NON-NLS-1$

	private static final String		IMMERSIVE_DESCRIPTION	= "Learning where the language is spoken by natives and have a good tutor.";												//$NON-NLS-1$
	private static final String		TUTOR_DESCRIPTION		= "Learning the language from a native tutor but not around natives";														//$NON-NLS-1$
	private static final String		BOOK_DESCRIPTION		= "Learning the language from a book and a bar buddy who knows a few words.";												//$NON-NLS-1$

	private static final String[]	LANGUAGES				= { "Common", "Ea'", "L'Ea'", "T'Ria'", "TetSri", "Northrin", "Eru", "Galon", "Uta", "D'Uta", "U'Tru", "Kazq", "Kaz'tch" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$
	private static final String[]	SOURCES					= { IMMERSIVE, TUTOR, "Book" };																								//$NON-NLS-1$
	private static final String[]	LANGUAGE_DESCRIPTIONS	= { COMMON_DESCRIPTION, EA_DESCRIPTION, LEA_DESCRIPTION, TRIA_DESCRIPTION, TETSRI_DESCRIPTION,								//
					NORTHRIN_DESCRIPTION, ERU_DESCRIPTION, GALON_DESCRIPTION, UTA_DESCRIPTION, DUTA_DESCRIPTION, UTRU_DESCRIPTION, KAZQ_DESCRIPTION, KAZTCH_DESCRIPTION };
	private static final String[]	SOURCE_DESCRIPTIONS		= { IMMERSIVE_DESCRIPTION, TUTOR_DESCRIPTION, BOOK_DESCRIPTION };

	private static final int		ROWS					= 5;
	private static final int		COST40					= 40;
	private static final int		COST60					= 60;
	private static final int		COST80					= 80;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]			mLangPopup;
	private TKPopupMenu[]			mSourcePopup;
	private JTextField[]			mDPPerWeekField;
	private JLabel[]				mUsedLabel;
	private int[]					mCurrentlySpentLabel;
	private int[]					mCostLabel;
	private JLabel[]				mMaintLabel;
	private JLabel[]				mSuccessfulLabel;
	private JLabel[]				mStartDateLabel;
	private JLabel[]				mCompletionDateLabel;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link LanguageTab}.
	 *
	 * @param owner
	 */
	public LanguageTab(Object owner) {
		super(owner, LANGUAGE_TAB_TITLE);
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
			if (popup.getInvoker().equals(mSourcePopup[i].getMenu())) {
				// DW handle -1 as failure to find index
				if (((JMenuItem) source).getText().equals(CHOOSE_SOURCE)) {
					mCostLabel[i] = 0;
					mUsedLabel[i].setText(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
				} else if (((JMenuItem) source).getText().equals(IMMERSIVE)) {
					mCostLabel[i] = COST40;
					mUsedLabel[i].setText(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
				} else if (((JMenuItem) source).getText().equals(TUTOR)) {
					mCostLabel[i] = COST60;
					mUsedLabel[i].setText(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
				} else {
					mCostLabel[i] = COST80;
					mUsedLabel[i].setText(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
				}
			}
		} else if (source instanceof JButton) {
			if (source.equals(mLearnButton)) {
				ArrayList<LanguageDeterminationRecord> list = getRecordsToLearn();
				for (LanguageDeterminationRecord record : list) {
					DeterminationList.addLanguageRecord(record);
				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mLangPopup[i].getMenu().setEnabled(enable);
			mSourcePopup[i].getMenu().setEnabled(enable);

			mDPPerWeekField[i].setEnabled(enable);
			mDPPerWeekField[i].setEditable(enable);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<LanguageDeterminationRecord> list = DeterminationList.getLanguageRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LanguageDeterminationRecord record = list.get(i);

				mLangPopup[i].selectPopupMenuItem(record.getLanguage());
				mSourcePopup[i].selectPopupMenuItem(record.getSource());
				mDPPerWeekField[i].setText(String.valueOf(record.getDPPerWeek()));
				mCurrentlySpentLabel[i] = record.getDPTotalSpent();
				mCostLabel[i] = record.getDPCost();

				mUsedLabel[i] = new JLabel(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
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
		return createPage(createCenterPanel(), LANGUAGE_DESCRIPTION, LANGUAGE_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mLangPopup = new TKPopupMenu[ROWS];
		mSourcePopup = new TKPopupMenu[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mUsedLabel = new JLabel[ROWS];
		mCurrentlySpentLabel = new int[ROWS];
		mCostLabel = new int[ROWS];
		mMaintLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];
		mStartDateLabel = new JLabel[ROWS];
		mCompletionDateLabel = new JLabel[ROWS];

		JPanel wrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel langPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel sourcePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		langPanel.add(new JLabel(LANGUAGE_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$

		sourcePanel.add(new JLabel("Source:", SwingConstants.CENTER)); //$NON-NLS-1$

		JLabel header = new JLabel("DP/Week", SwingConstants.CENTER); //$NON-NLS-1$
		dpPerWeekPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mLangPopup[i] = new TKPopupMenu(getLanguagePopup());
			mLangPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mLangPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mLangPopup[i].setMinimumSize(size2);
			mLangPopup[i].setPreferredSize(size2);
			mLangPopup[i].getMenu().setEnabled(false);
			langPanel.add(mLangPopup[i]);

			mSourcePopup[i] = new TKPopupMenu(getSourcePopup());
			mSourcePopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size3 = new Dimension(mSourcePopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mSourcePopup[i].setMinimumSize(size3);
			mSourcePopup[i].setPreferredSize(size3);
			mSourcePopup[i].getMenu().setEnabled(false);
			sourcePanel.add(mSourcePopup[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			// DW mCurrentlySpentLabel[i] && mCostLabel[i] should be values and not labels
			mUsedLabel[i] = new JLabel(mCurrentlySpentLabel[i] + " / " + mCostLabel[i]); //$NON-NLS-1$
			mUsedLabel[i].setMinimumSize(size);
			mUsedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(mUsedLabel[i]);

			mMaintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mMaintLabel[i].setMinimumSize(size);
			mMaintLabel[i].setPreferredSize(size);
			maintPanel.add(mMaintLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successfulPanel.add(mSuccessfulLabel[i]);

			mStartDateLabel[i] = new JLabel();
			mCompletionDateLabel[i] = new JLabel();
		}
		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		wrapper.add(langPanel);
		wrapper.add(sourcePanel);
		wrapper.add(dpPerWeekPanel);
		wrapper.add(dpSpentPanel);
		wrapper.add(maintPanel);
		wrapper.add(successfulPanel);

		updateEnabledState();

		return wrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private JMenu getLanguagePopup() {
		JMenu popupMenu = TKPopupMenu.createMenu(CHOOSE_LANGUAGE);

		popupMenu.addSeparator();
		for (int i = 0; i < LANGUAGES.length; i++) {
			String name = LANGUAGES[i];
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.setToolTipText(LANGUAGE_DESCRIPTIONS[i]);
				menuItem.addActionListener(this);
				popupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(CHOOSE_LANGUAGE);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem, 0);
		//		languagePopupMenu.addItemListener(this);

		return popupMenu;
	}

	private JMenu getSourcePopup() {
		JMenu popupMenu = TKPopupMenu.createMenu(CHOOSE_SOURCE);

		popupMenu.addSeparator();
		for (int i = 0; i < SOURCES.length; i++) {
			String name = SOURCES[i];
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.setToolTipText(SOURCE_DESCRIPTIONS[i]);
				menuItem.addActionListener(this);
				popupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(CHOOSE_SOURCE);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem, 0);
		//		languagePopupMenu.addItemListener(this);

		return popupMenu;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mLangPopup[i].getSelectedItem().equals(CHOOSE_LANGUAGE) || mDPPerWeekField[i].getText().isBlank() || mSourcePopup[i].getSelectedItem().equals(CHOOSE_SOURCE))) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<LanguageDeterminationRecord> getRecordsToLearn() {
		ArrayList<LanguageDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mLangPopup[i].getSelectedItem().equals(CHOOSE_LANGUAGE) || mDPPerWeekField[i].getText().isBlank() || mSourcePopup[i].getSelectedItem().equals(CHOOSE_SOURCE))) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new LanguageDeterminationRecord(mLangPopup[i].getSelectedItem(), mSourcePopup[i].getSelectedItem(), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), mCostLabel[i], campaignDate, campaignDate));
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
		int level = record.getLevel() / 4;
		int success = attributesRecord.getModifiedStat(AttributesRecord.WIS);
		return SUCCESS_TEXT1 + level + SUCCESS_TEXT2 + success;
	}

	/** @return The languages. */
	public static String[] getLanguages() {
		return LANGUAGES;
	}

	/** @return The languagesDescriptions. */
	public static String[] getLanguagesDescriptions() {
		return LANGUAGE_DESCRIPTIONS;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
