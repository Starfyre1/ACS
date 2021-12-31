/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.journal.CampaignDateChooser;
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
	public static final String	FILE_SECTTION_START_KEY		= "HEADER_SECTTION_START";																																															//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY		= "HEADER_SECTTION_END";																																															//$NON-NLS-1$

	private static final String	PLAYER_NAME_KEY				= "PLAYER_NAME_KEY";																																																//$NON-NLS-1$
	private static final String	CHARACTER_NAME_KEY			= "CHARACTER_NAME_KEY";																																																//$NON-NLS-1$
	private static final String	CLASS_KEY					= "CLASS_KEY";																																																		//$NON-NLS-1$
	private static final String	CURRENT_EXPERIENCE_KEY		= "CURRENT_EXPERIENCE_KEY";																																															//$NON-NLS-1$
	private static final String	CURRENT_CAMPAIGN_DATE_KEY	= "CURRENT_CAMPAIGN_DATE_KEY";																																														//$NON-NLS-1$

	private static final int	YEAR_AL						= 615;																																																				// YEAR_AD			= YEAR_AL - 268;
	private static final int	DEFAULT_CAMPAIGN_YEAR		= YEAR_AL;
	private static final int	DEFAULT_CAMPAIGN_MONTH		= 4;																																																				// 0=January... 15=Winter
	private static final int	DEFAULT_CAMPAIGN_DAY		= 14;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String				mPlayerName					= TKStringHelpers.EMPTY_STRING;
	private String				mCharacterName				= TKStringHelpers.EMPTY_STRING;
	private String				mClass						= TKStringHelpers.EMPTY_STRING;
	private int					mExperience					= 0;

	private int					mCurrentCampaignYear		= DEFAULT_CAMPAIGN_YEAR;
	private int					mCurrentCampaignMonth		= DEFAULT_CAMPAIGN_MONTH;																																															// 0=January... 15=Winter
	private int					mCurrentCampaignDay			= DEFAULT_CAMPAIGN_DAY;
	private String				mCampaignDate				= new String(CampaignDateChooser.MONTHS_SHORT[mCurrentCampaignMonth] + " " + String.format("%02d", Integer.valueOf(mCurrentCampaignDay)) + ", " + String.format("%04d", Integer.valueOf(mCurrentCampaignYear)));	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$);

	private int					mOldExperience				= 0;

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

	public static int parseCampaignYear(String campaignDate) {
		return TKStringHelpers.getIntValue(campaignDate.substring(8), 0);
	}

	public static int parseCampaignMonth(String campaignDate) {
		return CampaignDateChooser.getMonthIndex(campaignDate.substring(0, 3));
	}

	public static int parseCampaignDay(String campaignDate) {
		return TKStringHelpers.getIntValue(campaignDate.substring(4, 6), 0);
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

	/** @return The campaignDate. */
	public String getCampaignDate() {
		return mCampaignDate;
	}

	/** @param campaignDate The value to set for campaignDate. */
	public void setCampaignDate(String campaignDate) {
		mCampaignDate = campaignDate;
		setCurrentCampaignDay(parseCampaignDay(mCampaignDate));
		setCurrentCampaignMonth(parseCampaignMonth(mCampaignDate));
		setCurrentCampaignYear(parseCampaignYear(mCampaignDate));
	}

	/** @return The currentCampaignYear. */
	public int getCurrentCampaignYear() {
		return mCurrentCampaignYear;
	}

	/** @param currentCampaignYear The value to set for currentCampaignYear. */
	public void setCurrentCampaignYear(int currentCampaignYear) {
		mCurrentCampaignYear = currentCampaignYear;
	}

	/** @return The currentCampaignMonth. */
	public int getCurrentCampaignMonth() {
		return mCurrentCampaignMonth;
	}

	/** @param currentCampaignMonth The value to set for currentCampaignMonth. */
	public void setCurrentCampaignMonth(int currentCampaignMonth) {
		mCurrentCampaignMonth = currentCampaignMonth;
	}

	/** @return The currentCampaignDate. */
	public int getCurrentCampaignDay() {
		return mCurrentCampaignDay;
	}

	/** @param currentCampaignDay The value to set for currentCampaignDay. */
	public void setCurrentCampaignDay(int currentCampaignDay) {
		mCurrentCampaignDay = currentCampaignDay;
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
					while (tokenizer.hasMoreTokens()) {
						value += " " + tokenizer.nextToken(); //$NON-NLS-1$
					}
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
		br.write(CURRENT_CAMPAIGN_DATE_KEY + TKStringHelpers.SPACE + mCampaignDate + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
		updateOldRecords();
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		value = value.replace("~", " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (key.equals(PLAYER_NAME_KEY)) {
			mPlayerName = value;
		} else if (key.equals(CHARACTER_NAME_KEY)) {
			mCharacterName = value;
		} else if (key.equals(CLASS_KEY)) {
			mClass = value;
		} else if (key.equals(CURRENT_EXPERIENCE_KEY)) {
			mExperience = TKStringHelpers.getIntValue(value, 0);
		} else if (key.equals(CURRENT_CAMPAIGN_DATE_KEY)) {
			//			ACS.getInstance().setCampaignDate(value);
			setCampaignDate(value);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

	/**
	 *
	 */
	public void clearRecords() {
		mPlayerName = TKStringHelpers.EMPTY_STRING;
		mCharacterName = TKStringHelpers.EMPTY_STRING;
		mClass = TKStringHelpers.EMPTY_STRING;
		mExperience = 0;
		mOldExperience = 0;
	}

}
