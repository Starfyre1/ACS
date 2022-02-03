/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class AttributesRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "ATTRIBUTES_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "ATTRIBUTES_SECTTION_END";	//$NON-NLS-1$

	public static final String	STRENGTH_KEY			= "STRENGTH_KEY";				//$NON-NLS-1$
	public static final String	CONSTITUTION_KEY		= "CONSTITUTION_KEY";			//$NON-NLS-1$
	public static final String	INTELLIGENCE_KEY		= "INTELLIGENCE_KEY";			//$NON-NLS-1$
	public static final String	WISDOM_KEY				= "WISDOM_KEY";					//$NON-NLS-1$
	public static final String	DEXTERITY_KEY			= "DEXTERITY_KEY";				//$NON-NLS-1$
	public static final String	BOW_SKILL_KEY			= "BOW_SKILL_KEY";				//$NON-NLS-1$
	public static final String	CHARISMA_KEY			= "CHARISMA_KEY";				//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE_KEY	= "PERSONAL_APPEARANCE_KEY";	//$NON-NLS-1$
	public static final String	WILLPOWER_KEY			= "WILLPOWER_KEY";				//$NON-NLS-1$

	public static final String	STRENGTH				= "Strength";					//$NON-NLS-1$
	public static final String	CONSTITUTION			= "Constitution";				//$NON-NLS-1$
	public static final String	INTELLIGENCE			= "Intelligence";				//$NON-NLS-1$
	public static final String	WISDOM					= "Wisdom";						//$NON-NLS-1$
	public static final String	DEXTERITY				= "Dexterity";					//$NON-NLS-1$
	public static final String	BOW_SKILL				= "Bow Skill";					//$NON-NLS-1$
	public static final String	CHARISMA				= "Charisma";					//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE		= "Appearance";					//$NON-NLS-1$
	public static final String	WILLPOWER				= "Willpower";					//$NON-NLS-1$

	public static final int		STR						= 0;
	public static final int		CON						= 1;
	public static final int		INT						= 2;
	public static final int		WIS						= 3;
	public static final int		DEX						= 4;
	public static final int		BOW						= 5;
	public static final int		CHA						= 6;
	public static final int		PA						= 7;
	public static final int		WP						= 8;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	public static final String	mStatNames[]			= new String[] {				//
					STRENGTH,															//
					CONSTITUTION,														//
					INTELLIGENCE,														//
					WISDOM,																//
					DEXTERITY,															//
					BOW_SKILL,															//
					CHARISMA,															//
					PERSONAL_APPEARANCE,												//
					WILLPOWER };

	// mStats[0] -> Strength
	// mStats[1] -> Constitution
	// mStats[2] -> Intelligence
	// mStats[3] -> Wisdom
	// mStats[4] -> Dexterity
	// mStats[5] -> Bow Skill
	// mStats[6] -> Charisma
	// mStats[7] -> Personal Appearance
	// mStats[8] -> Willpower};
	private int					mStats[]				= new int[9];
	private int					mStatsOld[]				= new int[9];
	private int					mModifiedStats[]		= new int[9];
	private int					mModifiedStatsOld[]		= new int[9];
	private boolean				mGenerateOwnStats		= true;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public AttributesRecord(boolean generate) {
		mGenerateOwnStats = !generate;

		if (generate) {
			generateStats();

		}
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void generateStats() {

		PreferenceStore prefs = PreferenceStore.getInstance();
		int rerollLowestNumber = prefs.getRerollLowest();
		int numberOfDice = prefs.getNumDice();
		boolean dropLowest = numberOfDice == 4 ? true : false;

		mStats[8] = TKDice.generateDie(numberOfDice, dropLowest, 0, rerollLowestNumber);
		mStats[8] += bonusOver18(mStats[8], 0);

		if (prefs.useCommonDie()) {
			numberOfDice--;
		}

		for (int i = 0; i < mStats.length / 2; i++) {
			int common = 0;
			if (prefs.useCommonDie()) {
				common = TKDice.generateDie(1, false, 0, rerollLowestNumber);
			}
			mStats[i * 2] = TKDice.generateDie(numberOfDice, dropLowest, common, rerollLowestNumber);
			mStats[i * 2 + 1] = TKDice.generateDie(numberOfDice, dropLowest, common, rerollLowestNumber);
			int value = bonusOver18(mStats[i * 2], mStats[i * 2 + 1]);
			mStats[i * 2] += value;
			mStats[i * 2 + 1] += value;
		}
	}

	/**
	 * @param first
	 * @param second
	 */
	private int bonusOver18(int first, int second) {
		int bonus = 0;
		if (first == 18) {
			double next = TKDice.getNext();
			int roll = (int) (next * 6 - 2);
			if (roll < 0) {
				roll = 0;
			}
			bonus += roll;
		}
		if (second == 18) {
			double next = TKDice.getNext();
			int roll = (int) (next * 6 - 2);
			if (roll < 0) {
				roll = 0;
			}
			bonus += roll;
		}
		return bonus;
	}

	@Override
	public String toString() {
		String string = new String();
		for (int i = 0; i < mStatNames.length; i++) {
			string += mStatNames[i] + ": " + mStats[i] + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return string;
	}

	private void updateOldRecords() {
		mStatsOld = mStats.clone();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public int[] getModifiedStats() {
		return mModifiedStats;
	}

	public int getStat(int which) {
		return mStats[which];
	}

	public void setStat(int which, int value) {
		mStats[which] = value;
	}

	public int getModifiedStat(int which) {
		return mModifiedStats[which];
	}

	public void setModifiedStat(int which, int value) {
		mModifiedStats[which] = value;
	}

	public int getStatOld(int which) {
		return mStatsOld[which];
	}

	public void setStatOld(int which, int value) {
		mStatsOld[which] = value;
	}

	public int getModifiedStatOld(int which) {
		return mModifiedStatsOld[which];
	}

	public void setModifiedStatOld(int which, int value) {
		mModifiedStatsOld[which] = value;
	}

	/** @return The generateOwnStats. */
	public boolean isGenerateOwnStats() {
		return mGenerateOwnStats;
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
		br.write(STRENGTH_KEY + TKStringHelpers.SPACE + mStats[0] + System.lineSeparator());
		br.write(CONSTITUTION_KEY + TKStringHelpers.SPACE + mStats[1] + System.lineSeparator());
		br.write(INTELLIGENCE_KEY + TKStringHelpers.SPACE + mStats[2] + System.lineSeparator());
		br.write(WISDOM_KEY + TKStringHelpers.SPACE + mStats[3] + System.lineSeparator());
		br.write(DEXTERITY_KEY + TKStringHelpers.SPACE + mStats[4] + System.lineSeparator());
		br.write(BOW_SKILL_KEY + TKStringHelpers.SPACE + mStats[5] + System.lineSeparator());
		br.write(CHARISMA_KEY + TKStringHelpers.SPACE + mStats[6] + System.lineSeparator());
		br.write(PERSONAL_APPEARANCE_KEY + TKStringHelpers.SPACE + mStats[7] + System.lineSeparator());
		br.write(WILLPOWER_KEY + TKStringHelpers.SPACE + mStats[8] + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
		updateOldRecords();
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		int stat = TKStringHelpers.getIntValue(value, 0);
		if (key.equals(STRENGTH_KEY)) {
			mStats[0] = stat;
		} else if (key.equals(CONSTITUTION_KEY)) {
			mStats[1] = stat;
		} else if (key.equals(INTELLIGENCE_KEY)) {
			mStats[2] = stat;
		} else if (key.equals(WISDOM_KEY)) {
			mStats[3] = stat;
		} else if (key.equals(DEXTERITY_KEY)) {
			mStats[4] = stat;
		} else if (key.equals(BOW_SKILL_KEY)) {
			mStats[5] = stat;
		} else if (key.equals(CHARISMA_KEY)) {
			mStats[6] = stat;
		} else if (key.equals(PERSONAL_APPEARANCE_KEY)) {
			mStats[7] = stat;
		} else if (key.equals(WILLPOWER_KEY)) {
			mStats[8] = stat;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

	/**
	 *
	 */
	public void finalizeCreation(boolean manual) {
		if (manual) {
			mStats = mModifiedStats.clone();
		} else {
			mModifiedStats = mStats.clone();
		}
		mStatsOld = mModifiedStats.clone();
		mModifiedStatsOld = mModifiedStats.clone();
	}

	/**
	 *
	 */
	public void clearRecords() {
		mStats = new int[9];
		mStatsOld = new int[9];
		mModifiedStats = new int[9];
		mModifiedStatsOld = new int[9];
	}
}
