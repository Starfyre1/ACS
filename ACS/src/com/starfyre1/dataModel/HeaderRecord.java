/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class HeaderRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "HEADER_SECTTION_START";		//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "HEADER_SECTTION_END";		//$NON-NLS-1$

	private static final String	PLAYER_NAME_KEY			= "PLAYER_NAME_KEY";			//$NON-NLS-1$
	private static final String	CHARACTER_NAME_KEY		= "CHARACTER_NAME_KEY";			//$NON-NLS-1$
	private static final String	CLASS_KEY				= "CLASS_KEY";					//$NON-NLS-1$
	private static final String	CURRENT_EXPERIENCE_KEY	= "CURRENT_EXPERIENCE_KEY";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String				mPlayerName				= TKStringHelpers.EMPTY_STRING;
	private String				mCharacterName			= TKStringHelpers.EMPTY_STRING;
	private String				mClass					= TKStringHelpers.EMPTY_STRING;
	private int					mExperience				= 0;

	private int					mOldExperience			= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link HeaderRecord}.
	 */
	public HeaderRecord() {
		updateOldRecords();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	private void updateOldRecords() {
		mOldExperience = mExperience;

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The playerName. */
	public String getPlayerName() {
		return mPlayerName;
	}

	/** @param playerName The value to set for playerName. */
	public void setPlayerName(String playerName) {
		mPlayerName = playerName;
	}

	/** @return The characterName. */
	public String getCharacterName() {
		return mCharacterName;
	}

	/** @param characterName The value to set for characterName. */
	public void setCharacterName(String characterName) {
		mCharacterName = characterName;
	}

	/** @return The class. */
	public BaseClass getCharacterClass() {
		return ClassList.getCharacterClass(mClass);
	}

	/** @param characterClass The value to set for class. */
	public void setClass(String characterClass) {
		mClass = characterClass;
	}

	public String getCharacterClassName() {
		return mClass;
	}

	/** @return The level. */
	public int getLevel() {
		if (mExperience < 1000) {
			return 1;
		} else if (mExperience < 3000) {
			return 2;
		} else if (mExperience < 7000) {
			return 3;
		} else if (mExperience < 15000) {
			return 4;
		} else if (mExperience < 30000) {
			return 5;
		} else if (mExperience < 60000) {
			return 6;
		} else if (mExperience < 120000) {
			return 7;
		} else {
			return 8 + (mExperience - 100000) / 100000;
		}
	}

	/** @return The currentExperience. */
	public int getCurrentExperience() {
		return mExperience;
	}

	/** @param currentExperience The value to set for currentExperience. */
	public void setCurrentExperience(int currentExperience) {
		mExperience = currentExperience;

	}

	/** @return The oldExperience. */
	public int getOldExperience() {
		return mOldExperience;
	}

	/** @return The nextLevel. */
	public int getNextLevel() {
		int level = getLevel();
		if (level < 2) {
			return 1000;
		} else if (level < 3) {
			return 3000;
		} else if (level < 4) {
			return 7000;
		} else if (level < 5) {
			return 15000;
		} else if (level < 6) {
			return 30000;
		} else if (level < 7) {
			return 60000;
		} else if (level < 8) {
			return 120000;
		} else {
			return 200000 + 100000 * (level - 8);
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
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
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());
		br.write(PLAYER_NAME_KEY + TKStringHelpers.SPACE + mPlayerName.replace(" ", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(CHARACTER_NAME_KEY + TKStringHelpers.SPACE + mCharacterName.replace(" ", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(CLASS_KEY + TKStringHelpers.SPACE + mClass.replace(" ", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(CURRENT_EXPERIENCE_KEY + TKStringHelpers.SPACE + mExperience + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
		updateOldRecords();
	}

	@Override
	public void setKeyValuePair(String key, String value) {
		value = value.replace("~", " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (key.equals(PLAYER_NAME_KEY)) {
			mPlayerName = value;
		} else if (key.equals(CHARACTER_NAME_KEY)) {
			mCharacterName = value;
		} else if (key.equals(CLASS_KEY)) {
			mClass = value;
		} else if (key.equals(CURRENT_EXPERIENCE_KEY)) {
			mExperience = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

}
