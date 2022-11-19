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
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
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

public class LanguageTab extends DeterminationTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must successfully learn the language twice";								// //$NON-NLS-1$

	static final String				LANGUAGE_TAB_TITLE		= "Languages";																												//$NON-NLS-1$
	static final String				LANGUAGE_TAB_TOOLTIP	= "To learn a new language or become fluent:";																				//$NON-NLS-1$
	static final String				LANGUAGE_TITLE			= "Language";																												//$NON-NLS-1$

	private static final String		CHOOSE_LANGUAGE			= "Choose Language";																										//$NON-NLS-1$
	private static final String		CHOOSE_SOURCE			= "Choose Source";																											//$NON-NLS-1$
	private static final String		IMMERSIVE				= "Immersive";																												//$NON-NLS-1$
	private static final String		TUTOR					= "Tutor";																													//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 40, 60, 80 (Immersive, Tutor, Book)";																				//$NON-NLS-1$
	private static final String		MAINTENANCE_TEXT		= "Maintain: 1 DP for Fluent";																								//$NON-NLS-1$
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

	private static final String		SAVE					= "Save";																													//$NON-NLS-1$
	private static final String		CANCEL					= "Cancel";																													//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton					mOkButton;
	private JButton					mCancelButton;

	private TKPopupMenu				mLangPopup;
	private TKPopupMenu				mSourcePopup;
	private JTextField				mDPPerWeekField;
	private JLabel					mDPSpentLabel;
	private int						mDPCost;
	private JLabel					mMaintLabel;
	private JLabel					mSuccessfulLabel;
	private JLabel					mStartDateLabel;
	private JLabel					mEndDateLabel;

	private JPanel					mLangColumn;
	private JPanel					mSourceColumn;
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), CHOOSE_LANGUAGE, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				LanguageDeterminationRecord record = new LanguageDeterminationRecord(mLangPopup.getSelectedItem(), mSourcePopup.getSelectedItem(), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), mDPCost, CampaignDateChooser.getCampaignDate(), null);
				DeterminationList.addLanguageRecord(record);
				((DeterminationPointsDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
		} else if (source instanceof JMenuItem) {
			JPopupMenu popup = (JPopupMenu) ((JMenuItem) source).getParent();
			if (popup.getInvoker().equals(mSourcePopup.getMenu())) {
				// DW handle -1 as failure to find index
				if (((JMenuItem) source).getText().equals(CHOOSE_SOURCE)) {
					mDPCost = 0;
					mDPSpentLabel.setText(mDPSpentLabel + " / " + mDPCost); //$NON-NLS-1$
				} else if (((JMenuItem) source).getText().equals(IMMERSIVE)) {
					mDPCost = COST40;
					mDPSpentLabel.setText(0 + " / " + mDPCost); //$NON-NLS-1$
				} else if (((JMenuItem) source).getText().equals(TUTOR)) {
					mDPCost = COST60;
					mDPSpentLabel.setText(0 + " / " + mDPCost); //$NON-NLS-1$
				} else {
					mDPCost = COST80;
					mDPSpentLabel.setText(0 + " / " + mDPCost); //$NON-NLS-1$
				}
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
		for (int i = 0; i < ROWS; i++) {
			mLangPopup.getMenu().setEnabled(enable);
			mSourcePopup.getMenu().setEnabled(enable);

			mDPPerWeekField.setEnabled(enable);
			mDPPerWeekField.setEditable(enable);
		}
	}

	public void updateDisplay() {

		loadDisplay();
	}

	@Override
	protected void loadDisplay() {
		ArrayList<LanguageDeterminationRecord> list = DeterminationList.getLanguageRecords();
		JPanel wrapper = new JPanel();
		if (list.size() > 0) {
			for (LanguageDeterminationRecord record : list) {
				JLabel languageLabel = new JLabel(record.getLanguage());
				JLabel sourceLabel = new JLabel(record.getSource());
				JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
				JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
				JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				JLabel startDateLabel = new JLabel(record.getStartDate());
				JLabel endDateLabel = new JLabel(record.getEndDate());

				wrapper.add(languageLabel);
				wrapper.add(sourceLabel);
				wrapper.add(DPPerWeekLabel);
				wrapper.add(usedLabel);
				wrapper.add(maintLabel);
				wrapper.add(successLabel);
				wrapper.add(startDateLabel);
				wrapper.add(endDateLabel);
			}
		}
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), LANGUAGE_DESCRIPTION, LANGUAGE_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel() {
		//		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));

		mLangColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSourceColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mStartDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mEndDateColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		generateHeaders();

		outerWrapper.add(mLangColumn);
		outerWrapper.add(mSourceColumn);
		outerWrapper.add(mDPPerWeekColumn);
		outerWrapper.add(mDPSpentColumn);
		outerWrapper.add(mMaintColumn);
		outerWrapper.add(mSuccessfulColumn);
		outerWrapper.add(mStartDateColumn);
		outerWrapper.add(mEndDateColumn);

		return outerWrapper;
	}

	private void generateHeaders() {
		mLangColumn.add(new JLabel(LANGUAGE_TITLE));
		mSourceColumn.add(new JLabel("Source")); //$NON-NLS-1$
		mDPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn.add(new JLabel("DP Spent")); //$NON-NLS-1$
		mMaintColumn.add(new JLabel("Maint")); //$NON-NLS-1$
		mSuccessfulColumn.add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn.add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn.add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mLangColumn.removeAll();
		mSourceColumn.removeAll();
		mDPPerWeekColumn.removeAll();
		mDPSpentColumn.removeAll();
		mMaintColumn.removeAll();
		mSuccessfulColumn.removeAll();
		mStartDateColumn.removeAll();
		mEndDateColumn.removeAll();
		generateHeaders();

	}

	void addRecord(LanguageDeterminationRecord record) {
		if (record != null) {
			JLabel languageLabel = new JLabel(record.getLanguage());
			JLabel sourceLabel = new JLabel(record.getSource());
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel DPSpentLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel startDateLabel = new JLabel(record.getStartDate());
			JLabel endDateLabel = new JLabel(record.getEndDate());

			mLangColumn.add(languageLabel);
			mSourceColumn.add(sourceLabel);
			mDPPerWeekColumn.add(DPPerWeekLabel);
			mDPSpentColumn.add(DPSpentLabel);
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
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel weaponColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(LANGUAGE_TITLE);
		weaponColumn.add(label);
		JLabel header = new JLabel("Source"); //$NON-NLS-1$
		teacherColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dPSpentColumn.add(new JLabel("Used")); //$NON-NLS-1$
		bonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		maintColumn.add(new JLabel("Maint")); //$NON-NLS-1$
		successfulColumn.add(new JLabel("Successful")); //$NON-NLS-1$

		mLangPopup = new TKPopupMenu(getLanguageMenu());
		mLangPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mLangPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mLangPopup.getPreferredSize().width, POPUP_HEIGHT);
		mLangPopup.setMaximumSize(size2);
		weaponColumn.add(mLangPopup);

		mSourcePopup = new TKPopupMenu(getSourceMenu());
		Dimension size3 = new Dimension(mSourcePopup.getPreferredSize().width, POPUP_HEIGHT);
		mSourcePopup.setMaximumSize(size3);
		teacherColumn.add(mSourcePopup);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		dPPerWeekColumn.add(mDPPerWeekField);

		mDPSpentLabel = new JLabel(currentlySpent + " / " + mDPCost); //$NON-NLS-1$
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

		teacherColumn.add(Box.createVerticalGlue());
		dPSpentColumn.add(Box.createVerticalGlue());
		bonusAmountColumn.add(Box.createVerticalGlue());
		maintColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(weaponColumn);
		outerWrapper.add(teacherColumn);
		outerWrapper.add(bonusAmountColumn);
		outerWrapper.add(dPPerWeekColumn);
		outerWrapper.add(dPSpentColumn);
		outerWrapper.add(maintColumn);
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
		ArrayList<LanguageDeterminationRecord> list = DeterminationList.getLanguageRecords();
		if (list.size() > 0) {
			for (LanguageDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
	}

	private JMenu getLanguageMenu() {
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

	private JMenu getSourceMenu() {
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
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mLangPopup.getSelectedItem().equals(CHOOSE_LANGUAGE) || mDPPerWeekField.getText().isBlank() || mSourcePopup.getSelectedItem().equals(CHOOSE_SOURCE))) {
		return true;
		//			}
		//		}
		//		return false;
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
