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
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LanguageTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		LANGUAGE_DESCRIPTION	= "To learn the language fluently, the character must successfully learn the language twice";		// //$NON-NLS-1$

	static final String				LANGUAGE_TAB_TITLE		= "Languages";																						//$NON-NLS-1$
	static final String				LANGUAGE_TAB_TOOLTIP	= "To learn a new language or become fluent in a known one:";										//$NON-NLS-1$

	private static final String		CHOOSE_LANGUAGE			= "Choose Language";																				//$NON-NLS-1$
	private static final String		COST_TEXT				= "Cost: 40 (immersive) or 80 (Tutor)";																//$NON-NLS-1$
	private static final String		MAINTAINENCE_TEXT		= "Maintain: 1 DP / week for Fluent";																//$NON-NLS-1$
	private static final String		LANGUAGE_TEXT			= LANGUAGE_TAB_TOOLTIP;
	private static final String		SUCCESS_TOOLTIP			= "1D20 - (1/4 level) < (Wisdom)";																	//$NON-NLS-1$
	private static final String		SUCCESS_TEXT1			= "Success: 1D20 - ";																				//$NON-NLS-1$
	private static final String		SUCCESS_TEXT2			= " < ";																							//$NON-NLS-1$

	private static final String		COMMON_DESCRIPTION		= "A mixture of many languages, this trade language is spoken world wide on Athri.";				//$NON-NLS-1$
	private static final String		EA_DESCRIPTION			= "Elven, spoken through the elven and human kingdoms above the ground.";							//$NON-NLS-1$
	private static final String		LEA_DESCRIPTION			= "High Elven, spoken by the elite and royalty of the Elven society.";								//$NON-NLS-1$
	private static final String		TRIA_DESCRIPTION		= "Spoken by Tsiri (Dark Elven) above and below ground.";											//$NON-NLS-1$
	private static final String		TETSRI_DESCRIPTION		= "Spoken by the humans of the Tetsuru.";															//$NON-NLS-1$
	private static final String		NORTHRIN_DESCRIPTION	= "Spoken by the humans of the Northlands.";														//$NON-NLS-1$
	private static final String		ERU_DESCRIPTION			= "Spoken by the d'etri or Dwarrow of the world.";													//$NON-NLS-1$
	private static final String		GALON_DESCRIPTION		= "Spoken by the E'Sprey or Giants of T'Sal.";														//$NON-NLS-1$
	private static final String		UTA_DESCRIPTION			= "Spoken by the dark creatures of the world.";														//$NON-NLS-1$
	private static final String		DUTA_DESCRIPTION		= "The Silent language, Thieves Tongue, a Sign Language with many variations and many names.";		//$NON-NLS-1$

	private static final String[]	LANGUAGES				= { "Common", "Ea'", "L'Ea'", "T'Ria'", "TetSri", "Northrin", "Eru", "Galon", "Uta", "D'Uta" };		//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
	private static final String[]	LANGUAGES_DESCRIPTIONS	= { COMMON_DESCRIPTION, EA_DESCRIPTION, LEA_DESCRIPTION, TRIA_DESCRIPTION, TETSRI_DESCRIPTION,		//
					NORTHRIN_DESCRIPTION, ERU_DESCRIPTION, GALON_DESCRIPTION, UTA_DESCRIPTION, DUTA_DESCRIPTION };

	private static final int		ROWS					= 5;
	private static final int		COST					= 40;
	private static final int		COST2					= 80;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]			mLangField;
	private JTextField[]			mDPPerWeekField;

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
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), LANGUAGE_DESCRIPTION, LANGUAGE_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mLangField = new TKPopupMenu[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		JLabel[] usedLabel = new JLabel[ROWS];
		JLabel[] maintLabel = new JLabel[ROWS];
		JLabel[] successfulLabel = new JLabel[ROWS];

		JPanel wrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel langPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		langPanel.add(new JLabel(LANGUAGE_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("DP/Week", SwingConstants.CENTER); //$NON-NLS-1$
		dpPerWeekPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mLangField[i] = new TKPopupMenu(getLanguagePopup());
			mLangField[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mLangField[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mLangField[i].setMinimumSize(size2);
			mLangField[i].setPreferredSize(size2);
			langPanel.add(mLangField[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			usedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			usedLabel[i].setMinimumSize(size);
			usedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(usedLabel[i]);

			maintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			maintLabel[i].setMinimumSize(size);
			maintLabel[i].setPreferredSize(size);
			maintPanel.add(maintLabel[i]);

			successfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			successfulLabel[i].setMinimumSize(size);
			successfulLabel[i].setPreferredSize(size);
			successfulPanel.add(successfulLabel[i]);

		}
		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		wrapper.add(langPanel);
		wrapper.add(dpPerWeekPanel);
		wrapper.add(dpSpentPanel);
		wrapper.add(maintPanel);
		wrapper.add(successfulPanel);

		return wrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private JMenu getLanguagePopup() {
		JMenu languagePopupMenu = TKPopupMenu.createMenu(CHOOSE_LANGUAGE);

		languagePopupMenu.addSeparator();
		for (int i = 0; i < LANGUAGES.length; i++) {
			String name = LANGUAGES[i];
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.setToolTipText(LANGUAGES_DESCRIPTIONS[i]);
				menuItem.addActionListener(this);
				languagePopupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(CHOOSE_LANGUAGE);
		menuItem.addActionListener(this);
		languagePopupMenu.add(menuItem, 0);
		//		languagePopupMenu.addItemListener(this);

		return languagePopupMenu;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mLangField[i].getSelectedItem().equals(CHOOSE_LANGUAGE) || mDPPerWeekField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<LanguageDeterminationRecord> getRecordsToLearn() {
		ArrayList<LanguageDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mLangField[i].getSelectedItem().equals(CHOOSE_LANGUAGE) || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new LanguageDeterminationRecord(mLangField[i].getSelectedItem(), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), COST, campaignDate));
			}
		}
		return list;
	}

	@Override
	protected String getSuccessText() {
		HeaderRecord record = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		AttributesRecord attributesRecord = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null || attributesRecord == null) {
			return "?"; //$NON-NLS-1$
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
		return LANGUAGES_DESCRIPTIONS;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
