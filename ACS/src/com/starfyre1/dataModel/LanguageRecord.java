/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LanguageRecord implements Savable {

	// DW Rework this to be a proper class with language, type, description and keep the array of LanguageRecord()'s in LanguageList
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String		FILE_SECTION_START_KEY	= "LANGUAGE_SECTION_START";																																																						//$NON-NLS-1$
	public static final String		FILE_SECTION_END_KEY	= "LANGUAGE_SECTION_END";																																																						//$NON-NLS-1$
	private static final String		LANGUAGE_KEY			= "LANGUAGE_KEY";																																																								//$NON-NLS-1$

	private static final String		COMMON_DESCRIPTION		= "A mixture of many languages, this trade language is spoken world wide on Athri.";																																							//$NON-NLS-1$
	private static final String		EA_DESCRIPTION			= "Elven, spoken through the elven and human kingdoms above the ground.";																																										//$NON-NLS-1$
	private static final String		LEA_DESCRIPTION			= "High Elven, spoken by the elite and royalty of the Elven society.";																																											//$NON-NLS-1$
	private static final String		TRIA_DESCRIPTION		= "Spoken by Tsiri (Dark Elven) above and below ground.";																																														//$NON-NLS-1$
	private static final String		TETSRI_DESCRIPTION		= "Spoken by the humans of the Tetsuru.";																																																		//$NON-NLS-1$
	private static final String		NORTHRIN_DESCRIPTION	= "Spoken by the humans of the Northlands.";																																																	//$NON-NLS-1$
	private static final String		ERU_DESCRIPTION			= "Spoken by the d'etri or Dwarrow of the world.";																																																//$NON-NLS-1$
	private static final String		GALON_DESCRIPTION		= "Spoken by the E'Sprey or Giants of T'Sal.";																																																	//$NON-NLS-1$
	private static final String		UTA_DESCRIPTION			= "Spoken by the dark creatures of the world.";																																																	//$NON-NLS-1$
	private static final String		DUTA_DESCRIPTION		= "The Silent language, Thieves Tongue, a Sign Language with many variations and many names.";																																					//$NON-NLS-1$
	private static final String		UTRU_DESCRIPTION		= "Spoken by Goblinkin around the world.";																																																		//$NON-NLS-1$
	private static final String		KAZQ_DESCRIPTION		= "Spoken by most Dwarves around the world.";																																																	//$NON-NLS-1$
	private static final String		KAZTCH_DESCRIPTION		= "Dwarves war tongue";																																																							//$NON-NLS-1$

	public static final String[]	LANGUAGES				= { "Common", "Ea'", "L'Ea'", "T'Ria'", "TetSri", "Northrin", "Eru", "Galon", "Uta", "D'Uta", "U'Tru", "Kazq", "Kaz'tch" };																														//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$
	public static final String[]	LANGUAGES_TYPES			= { "Trade Language", "Elven", "High Elven", "Dark Elven", "Human - Tetsuru", "Human - Northlands", "D'etri / Dwarrow", "E'Sprey / Giants (T'Sal)", "Dark Creatures", "Thieves Tongue (Sign)", "Goblinkin", "Dwarves", "Dwarves war tongue" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$//$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$
	public static final String[]	LANGUAGES_DESCRIPTIONS	= { COMMON_DESCRIPTION, EA_DESCRIPTION, LEA_DESCRIPTION, TRIA_DESCRIPTION, TETSRI_DESCRIPTION,																																					//
					NORTHRIN_DESCRIPTION, ERU_DESCRIPTION, GALON_DESCRIPTION, UTA_DESCRIPTION, DUTA_DESCRIPTION, UTRU_DESCRIPTION, KAZQ_DESCRIPTION, KAZTCH_DESCRIPTION };
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet			mCharacterSheet;
	private List<String>			mKnownLanguages			= new ArrayList<String>();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public LanguageRecord(CharacterSheet sheet, boolean generate) {
		mCharacterSheet = sheet;

		if (generate) {
			generate();
		}

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void updateValues() {
		//		BaseClass base = mCharacterSheet.getHeaderRecord().getCharacterClass();
		//		if (base != null) {
		//			//			generateHitBonus();
		//		} else {
		//			clearRecords();
		//		}
	}

	public void clearRecords() {
		//		mHitLevelBonus = 0;
		//		mBowLevelBonus = 0;
	}

	public void generate() {
		BaseClass base = mCharacterSheet.getHeaderRecord().getCharacterClass();
		if (base == null) {
			return;
		}
		ArrayList<String> languages = base.getLanguages();
		for (String lang : languages) {
			addKnownLanguage(lang);
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The languages. */
	public static String[] getLanguages() {
		return LANGUAGES;
	}

	/** @return The languagesDescriptions. */
	public static String[] getLanguagesDescriptions() {
		return LANGUAGES_DESCRIPTIONS;
	}

	/** @return The languagesDescriptions. */
	public static String[] getLanguagesTypes() {
		return LANGUAGES_TYPES;
	}

	public static int getNumLang() {
		return LANGUAGES.length;
	}

	/**
	 * @param name
	 * @return true if language is known
	 */
	public boolean isLanguageKnown(String name) {
		if (mKnownLanguages.isEmpty()) {
			return false;
		}

		for (String lang : mKnownLanguages) {
			if (lang.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public int getLanguageID(String name) {
		for (int i = 0; i < LANGUAGES.length; i++) {
			String lang = LANGUAGES[i];
			if (lang.equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void addKnownLanguage(String name) {
		if (!mKnownLanguages.contains(name)) {
			mKnownLanguages.add(name);
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, " ", false); //$NON-NLS-1$
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						String value = ""; //$NON-NLS-1$
						setKeyValuePair(key, value);
					} else {
						String value = tokenizer.nextToken();
						while (tokenizer.hasMoreTokens()) {
							value = value + " " + tokenizer.nextToken(); //$NON-NLS-1$
						}
						setKeyValuePair(key, value);
					}
				}
			}
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());
		for (String name : mKnownLanguages) {
			br.write(TKStringHelpers.TAB + LANGUAGE_KEY + TKStringHelpers.SPACE + name + System.lineSeparator());
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (LANGUAGE_KEY.equals(key)) {
			addKnownLanguage(value);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
