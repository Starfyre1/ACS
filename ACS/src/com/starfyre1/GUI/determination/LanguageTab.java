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
import com.starfyre1.dataModel.LanguageRecord;
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
	private static final String		LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must successfully learn the language twice";	// //$NON-NLS-1$

	static final String				LANGUAGE_TAB_TITLE		= "Languages";																					//$NON-NLS-1$
	static final String				LANGUAGE_TAB_TOOLTIP	= "To learn a new language or become fluent:";													//$NON-NLS-1$
	static final String				LANGUAGE_TITLE			= "Language";																					//$NON-NLS-1$

	private static final String		CHOOSE_LANGUAGE			= "Choose Language";																			//$NON-NLS-1$
	private static final String		CHOOSE_SOURCE			= "Choose Source";																				//$NON-NLS-1$
	private static final String		IMMERSIVE				= "Immersive";																					//$NON-NLS-1$
	private static final String		TUTOR					= "Tutor";																						//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 40, 60, 80 (Immersive, Tutor, Book)";													//$NON-NLS-1$
	private static final String		MAINTENANCE_TEXT		= "Maintain: 1 DP for Fluent";																	//$NON-NLS-1$
	private static final String		LANGUAGE_TEXT			= LANGUAGE_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 - (1/4 level) < (Wisdom)";																//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: (1D20 - ";																			//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= ") < ";																						//$NON-NLS-1$

	private static final String		IMMERSIVE_DESCRIPTION	= "Learning where the language is spoken by natives and have a good tutor.";					//$NON-NLS-1$
	private static final String		TUTOR_DESCRIPTION		= "Learning the language from a native tutor but not around natives";							//$NON-NLS-1$
	private static final String		BOOK_DESCRIPTION		= "Learning the language from a book and a bar buddy who knows a few words.";					//$NON-NLS-1$

	private static final String[]	SOURCES					= { IMMERSIVE, TUTOR, "Book" };																	//$NON-NLS-1$
	private static final String[]	SOURCE_DESCRIPTIONS		= { IMMERSIVE_DESCRIPTION, TUTOR_DESCRIPTION, BOOK_DESCRIPTION };

	private static final int		ROWS					= 5;
	private static final int		COST40					= 40;
	private static final int		COST60					= 60;
	private static final int		COST80					= 80;

	private static final String		SAVE					= "Save";																						//$NON-NLS-1$
	private static final String		CANCEL					= "Cancel";																						//$NON-NLS-1$

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

	private JPanel					mLangColumn[];
	private JPanel					mSourceColumn[];
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
				String startDate = CampaignDateChooser.getCampaignDate();
				LanguageDeterminationRecord record = new LanguageDeterminationRecord(mLangPopup.getSelectedItem(), mSourcePopup.getSelectedItem(), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), mDPCost, startDate, startDate);
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
	protected Component createDisplay() {
		mLangColumn = new JPanel[2];
		mSourceColumn = new JPanel[2];
		mDPPerWeekColumn = new JPanel[2];
		mDPSpentColumn = new JPanel[2];
		mMaintColumn = new JPanel[2];
		mSuccessfulColumn = new JPanel[2];
		mStartDateColumn = new JPanel[2];
		mEndDateColumn = new JPanel[2];

		return createPage(createCenterPanel(false), createCenterPanel(true), LANGUAGE_DESCRIPTION, LANGUAGE_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel(boolean complete) {
		//		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		int test = complete ? 1 : 0;

		JPanel outerWrapper = new JPanel();

		mLangColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSourceColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPSpentColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mStartDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mEndDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		generateHeaders(complete);

		outerWrapper.add(mLangColumn[test]);
		outerWrapper.add(mSourceColumn[test]);
		outerWrapper.add(mDPPerWeekColumn[test]);
		outerWrapper.add(mDPSpentColumn[test]);
		outerWrapper.add(mMaintColumn[test]);
		outerWrapper.add(mSuccessfulColumn[test]);
		outerWrapper.add(mStartDateColumn[test]);
		outerWrapper.add(mEndDateColumn[test]);

		return outerWrapper;
	}

	private void generateHeaders(boolean complete) {
		int test = complete ? 1 : 0;

		mLangColumn[test].add(new JLabel(LANGUAGE_TITLE));
		mSourceColumn[test].add(new JLabel("Source")); //$NON-NLS-1$
		mDPPerWeekColumn[test].add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn[test].add(new JLabel("DP Spent")); //$NON-NLS-1$
		mMaintColumn[test].add(new JLabel("Maint")); //$NON-NLS-1$
		mSuccessfulColumn[test].add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn[test].add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn[test].add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mLangColumn[0].removeAll();
		mSourceColumn[0].removeAll();
		mDPPerWeekColumn[0].removeAll();
		mDPSpentColumn[0].removeAll();
		mMaintColumn[0].removeAll();
		mSuccessfulColumn[0].removeAll();
		mStartDateColumn[0].removeAll();
		mEndDateColumn[0].removeAll();
		generateHeaders(false);

		mLangColumn[1].removeAll();
		mSourceColumn[1].removeAll();
		mDPPerWeekColumn[1].removeAll();
		mDPSpentColumn[1].removeAll();
		mMaintColumn[1].removeAll();
		mSuccessfulColumn[1].removeAll();
		mStartDateColumn[1].removeAll();
		mEndDateColumn[1].removeAll();
		generateHeaders(true);

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
			String endDate = record.getEndDate();
			JLabel endDateLabel = new JLabel(endDate);

			int which = endDate.isBlank() ? 0 : 1;
			mLangColumn[which].add(languageLabel);
			mSourceColumn[which].add(sourceLabel);
			mDPPerWeekColumn[which].add(DPPerWeekLabel);
			mDPSpentColumn[which].add(DPSpentLabel);
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
		for (int i = 0; i < LanguageRecord.LANGUAGES.length; i++) {
			String name = LanguageRecord.LANGUAGES[i];
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.setToolTipText(LanguageRecord.LANGUAGES_DESCRIPTIONS[i]);
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
