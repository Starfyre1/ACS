/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.storage.PreferenceStore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class AttributesRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTION_START_KEY			= "ATTRIBUTES_SECTION_START";																		//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY			= "ATTRIBUTES_SECTION_END";																			//$NON-NLS-1$

	public static final String	STRENGTH_KEY					= "STRENGTH_KEY";																					//$NON-NLS-1$
	public static final String	CONSTITUTION_KEY				= "CONSTITUTION_KEY";																				//$NON-NLS-1$
	public static final String	INTELLIGENCE_KEY				= "INTELLIGENCE_KEY";																				//$NON-NLS-1$
	public static final String	WISDOM_KEY						= "WISDOM_KEY";																						//$NON-NLS-1$
	public static final String	DEXTERITY_KEY					= "DEXTERITY_KEY";																					//$NON-NLS-1$
	public static final String	BOW_SKILL_KEY					= "BOW_SKILL_KEY";																					//$NON-NLS-1$
	public static final String	CHARISMA_KEY					= "CHARISMA_KEY";																					//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE_KEY			= "PERSONAL_APPEARANCE_KEY";																		//$NON-NLS-1$
	public static final String	WILLPOWER_KEY					= "WILLPOWER_KEY";																					//$NON-NLS-1$

	public static final String	STRENGTH_DP_KEY					= "STRENGTH_DP_KEY";																				//$NON-NLS-1$
	public static final String	CONSTITUTION_DP_KEY				= "CONSTITUTION_DP_KEY";																			//$NON-NLS-1$
	public static final String	INTELLIGENCE_DP_KEY				= "INTELLIGENCE_DP_KEY";																			//$NON-NLS-1$
	public static final String	WISDOM_DP_KEY					= "WISDOM_DP_KEY";																					//$NON-NLS-1$
	public static final String	DEXTERITY_DP_KEY				= "DEXTERITY_DP_KEY";																				//$NON-NLS-1$
	public static final String	BOW_SKILL_DP_KEY				= "BOW_SKILL_DP_KEY";																				//$NON-NLS-1$
	public static final String	CHARISMA_DP_KEY					= "CHARISMA_DP_KEY";																				//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE_DP_KEY		= "PERSONAL_APPEARANCE_DP_KEY";																		//$NON-NLS-1$
	public static final String	WILLPOWER_DP_KEY				= "WILLPOWER_DP_KEY";																				//$NON-NLS-1$

	public static final String	STRENGTH_MOD_KEY				= "STRENGTH_MOD_KEY";																				//$NON-NLS-1$
	public static final String	CONSTITUTION_MOD_KEY			= "CONSTITUTION_MOD_KEY";																			//$NON-NLS-1$
	public static final String	INTELLIGENCE_MOD_KEY			= "INTELLIGENCE_MOD_KEY";																			//$NON-NLS-1$
	public static final String	WISDOM_MOD_KEY					= "WISDOM_MOD_KEY";																					//$NON-NLS-1$
	public static final String	DEXTERITY_MOD_KEY				= "DEXTERITY_MOD_KEY";																				//$NON-NLS-1$
	public static final String	BOW_SKILL_MOD_KEY				= "BOW_SKILL_MOD_KEY";																				//$NON-NLS-1$
	public static final String	CHARISMA_MOD_KEY				= "CHARISMA_MOD_KEY";																				//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE_MOD_KEY		= "PERSONAL_APPEARANCE_MOD_KEY";																	//$NON-NLS-1$
	public static final String	WILLPOWER_MOD_KEY				= "WILLPOWER_MOD_KEY";																				//$NON-NLS-1$

	public static final String	STRENGTH						= "Strength";																						//$NON-NLS-1$
	public static final String	CONSTITUTION					= "Constitution";																					//$NON-NLS-1$
	public static final String	INTELLIGENCE					= "Intelligence";																					//$NON-NLS-1$
	public static final String	WISDOM							= "Wisdom";																							//$NON-NLS-1$
	public static final String	DEXTERITY						= "Dexterity";																						//$NON-NLS-1$
	public static final String	BOW_SKILL						= "Bow Skill";																						//$NON-NLS-1$
	public static final String	CHARISMA						= "Charisma";																						//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE				= "Appearance";																						//$NON-NLS-1$
	public static final String	WILLPOWER						= "Willpower";																						//$NON-NLS-1$

	public static final String	STRENGTH_DESCRIPTION			= "<html>Strength is the measure of your characters physical power. It tells<br>"					// //$NON-NLS-1$
					+ "you whether or not you do additional damage during a fight or if<br>"																		// //$NON-NLS-1$
					+ "you can breakdown a door to save a friend. It also helps tell you<br>"																		// //$NON-NLS-1$
					+ "how much you can carry. \"ST\"</html>";																										//$NON-NLS-1$
	public static final String	CONSTITUTION_DESCRIPTION		= "<html>Constitution measures the health of you character also his<br>"							// //$NON-NLS-1$
					+ "resistance to disease, poisons and some spells. \"CON\"</html>";																				//$NON-NLS-1$
	public static final String	INTELLIGENCE_DESCRIPTION		= "<html>Intelligence measures you Intellect, and in a Mage's case how<br>"							// //$NON-NLS-1$
					+ "many mana points they will start with, and how many they will<br>"																			// //$NON-NLS-1$
					+ "receive as they gain levels. \"INT\"</html>";																								//$NON-NLS-1$
	public static final String	WISDOM_DESCRIPTION				= "<html>Wisdom stands for your memory, it helps you remember things<br>"							// //$NON-NLS-1$
					+ "that as a player you may not know, but that your character should<br>"																		// //$NON-NLS-1$
					+ "know. It also helps decide how many spells you start with and how<br>"																		// //$NON-NLS-1$
					+ "many you can remember at one time. \"WIS\"</html>";																							//$NON-NLS-1$
	public static final String	DEXTERITY_DESCRIPTION			= "<html>Dexterity measures your actual agility, and manual dexterity, it is<br>"					// //$NON-NLS-1$
					+ "not only used by thieves to pick pockets, but by fighters to dodge<br>"																		// //$NON-NLS-1$
					+ "blows. It also helps you hit the targets that you are aiming at.<br>"																		// //$NON-NLS-1$
					+ "\"DEX\"</html>";																																//$NON-NLS-1$
	public static final String	BOW_SKILL_DESCRIPTION			= "<html>Bow skill is self-explanatory, it measures your natural skill with a<br>"					// //$NON-NLS-1$
					+ "Bow. Some people can use them very well naturally, while some<br>"																			// //$NON-NLS-1$
					+ "must go through extensive training. \"BS\"</html>";																							//$NON-NLS-1$
	public static final String	CHARISMA_DESCRIPTION			= "<html>This statistic is underrated, this measures the physical presence of<br>"					// //$NON-NLS-1$
					+ "a person, their ability to instill confidence and trust blindly. It allows<br>"																// //$NON-NLS-1$
					+ "you to get someone's attention or make someone believe you without<br>"																		// //$NON-NLS-1$
					+ "any evidence to support you. \"CH\"</html>";																									//$NON-NLS-1$
	public static final String	PERSONAL_APPEARANCE_DESCRIPTION	= "<html>This measures your actual physical appearance.\"PA\"</html>";								//$NON-NLS-1$
	public static final String	WILLPOWER_DESCRIPTION			= "<html>Willpower measures how Tenacious your character is, or can be, and<br>"					// //$NON-NLS-1$
					+ "how strong your convictions are. It is Very important to most of your<br>"																	// //$NON-NLS-1$
					+ "saving throws and in some of the magic areas where you must maintain<br>"																	// //$NON-NLS-1$
					+ "control of creatures that you summon. \"WP\"</html>";																						//$NON-NLS-1$

	public static final int		STR								= 0;
	public static final int		CON								= 1;
	public static final int		INT								= 2;
	public static final int		WIS								= 3;
	public static final int		DEX								= 4;
	public static final int		BOW								= 5;
	public static final int		CHA								= 6;
	public static final int		PA								= 7;
	public static final int		WP								= 8;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	public static final String	mStatNames[]					= new String[] {																					//
					STRENGTH,																																		//
					CONSTITUTION,																																	//
					INTELLIGENCE,																																	//
					WISDOM,																																			//
					DEXTERITY,																																		//
					BOW_SKILL,																																		//
					CHARISMA,																																		//
					PERSONAL_APPEARANCE,																															//
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
	private int					mStats[]						= new int[9];
	private int					mStatsOld[]						= new int[9];
	private int					mDPStats[]						= new int[9];
	private int					mDPStatsOld[]					= new int[9];
	private int					mModifiedStats[]				= new int[9];
	private int					mModifiedStatsOld[]				= new int[9];
	private boolean				mGenerateOwnStats				= true;

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
		mDPStatsOld = mDPStats.clone();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public int getArrayPosition(String which) {
		int position = switch (which) {
			case STRENGTH:
				yield 0;
			case CONSTITUTION:
				yield 1;
			case INTELLIGENCE:
				yield 2;
			case WISDOM:
				yield 3;
			case DEXTERITY:
				yield 4;
			case BOW_SKILL:
				yield 5;
			case CHARISMA:
				yield 6;
			case PERSONAL_APPEARANCE:
				yield 7;
			case WILLPOWER:
				yield 8;
			default:
				throw new IllegalArgumentException("Unexpected value: " + which); //$NON-NLS-1$
		};
		return position;
	}

	public int getStat(int which) {
		return mStats[which];
	}

	public void setStat(int which, int value) {
		mStats[which] = value;
	}

	public int getDPStat(int which) {
		return mDPStats[which];
	}

	public void setDPStatBonus(int which, int value) {
		mDPStats[which] = value;
		mModifiedStats[which] += value;
	}

	public int[] getModifiedStats() {
		return mModifiedStats;
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

	public int getDPStatOld(int which) {
		return mDPStatsOld[which];
	}

	public void setDPStatOld(int which, int value) {
		mDPStatsOld[which] = value;
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
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
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

		br.write(FILE_SECTION_START_KEY + System.lineSeparator());

		br.write(TKStringHelpers.TAB + STRENGTH_KEY + TKStringHelpers.SPACE + mStats[0] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CONSTITUTION_KEY + TKStringHelpers.SPACE + mStats[1] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + INTELLIGENCE_KEY + TKStringHelpers.SPACE + mStats[2] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WISDOM_KEY + TKStringHelpers.SPACE + mStats[3] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DEXTERITY_KEY + TKStringHelpers.SPACE + mStats[4] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + BOW_SKILL_KEY + TKStringHelpers.SPACE + mStats[5] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CHARISMA_KEY + TKStringHelpers.SPACE + mStats[6] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + PERSONAL_APPEARANCE_KEY + TKStringHelpers.SPACE + mStats[7] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WILLPOWER_KEY + TKStringHelpers.SPACE + mStats[8] + System.lineSeparator());

		br.write(TKStringHelpers.TAB + STRENGTH_DP_KEY + TKStringHelpers.SPACE + mDPStats[0] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CONSTITUTION_DP_KEY + TKStringHelpers.SPACE + mDPStats[1] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + INTELLIGENCE_DP_KEY + TKStringHelpers.SPACE + mDPStats[2] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WISDOM_DP_KEY + TKStringHelpers.SPACE + mDPStats[3] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DEXTERITY_DP_KEY + TKStringHelpers.SPACE + mDPStats[4] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + BOW_SKILL_DP_KEY + TKStringHelpers.SPACE + mDPStats[5] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CHARISMA_DP_KEY + TKStringHelpers.SPACE + mDPStats[6] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + PERSONAL_APPEARANCE_DP_KEY + TKStringHelpers.SPACE + mDPStats[7] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WILLPOWER_DP_KEY + TKStringHelpers.SPACE + mDPStats[8] + System.lineSeparator());

		br.write(TKStringHelpers.TAB + STRENGTH_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[0] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CONSTITUTION_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[1] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + INTELLIGENCE_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[2] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WISDOM_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[3] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DEXTERITY_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[4] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + BOW_SKILL_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[5] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CHARISMA_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[6] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + PERSONAL_APPEARANCE_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[7] + System.lineSeparator());
		br.write(TKStringHelpers.TAB + WILLPOWER_MOD_KEY + TKStringHelpers.SPACE + mModifiedStats[8] + System.lineSeparator());

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
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
		} else if (key.equals(STRENGTH_DP_KEY)) {
			mDPStats[0] = stat;
		} else if (key.equals(CONSTITUTION_DP_KEY)) {
			mDPStats[1] = stat;
		} else if (key.equals(INTELLIGENCE_DP_KEY)) {
			mDPStats[2] = stat;
		} else if (key.equals(WISDOM_DP_KEY)) {
			mDPStats[3] = stat;
		} else if (key.equals(DEXTERITY_DP_KEY)) {
			mDPStats[4] = stat;
		} else if (key.equals(BOW_SKILL_DP_KEY)) {
			mDPStats[5] = stat;
		} else if (key.equals(CHARISMA_DP_KEY)) {
			mDPStats[6] = stat;
		} else if (key.equals(PERSONAL_APPEARANCE_DP_KEY)) {
			mDPStats[7] = stat;
		} else if (key.equals(WILLPOWER_DP_KEY)) {
			mDPStats[8] = stat;
		} else if (key.equals(STRENGTH_MOD_KEY)) {
			mModifiedStats[0] = stat;
		} else if (key.equals(CONSTITUTION_MOD_KEY)) {
			mModifiedStats[1] = stat;
		} else if (key.equals(INTELLIGENCE_MOD_KEY)) {
			mModifiedStats[2] = stat;
		} else if (key.equals(WISDOM_MOD_KEY)) {
			mModifiedStats[3] = stat;
		} else if (key.equals(DEXTERITY_MOD_KEY)) {
			mModifiedStats[4] = stat;
		} else if (key.equals(BOW_SKILL_MOD_KEY)) {
			mModifiedStats[5] = stat;
		} else if (key.equals(CHARISMA_MOD_KEY)) {
			mModifiedStats[6] = stat;
		} else if (key.equals(PERSONAL_APPEARANCE_MOD_KEY)) {
			mModifiedStats[7] = stat;
		} else if (key.equals(WILLPOWER_MOD_KEY)) {
			mModifiedStats[8] = stat;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 *
	 */
	public void finalizeCreation(boolean manual) {
		if (manual) {
			mStats = mModifiedStats.clone();
		} else if (mModifiedStats[0] == 0) {
			mModifiedStats = mStats.clone();
		}
		mStatsOld = mStats.clone();
		mModifiedStatsOld = mModifiedStats.clone();
	}

	/**
	 *
	 */
	public void clearRecords() {
		mStats = new int[9];
		mStatsOld = new int[9];
		mDPStats = new int[9];
		mDPStatsOld = new int[9];
		mModifiedStats = new int[9];
		mModifiedStatsOld = new int[9];
	}
}
