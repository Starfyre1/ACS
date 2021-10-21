/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.classes.Dwarrow;
import com.starfyre1.dataset.classes.Dwarves;
import com.starfyre1.dataset.classes.HalfElf;
import com.starfyre1.dataset.classes.Human;
import com.starfyre1.dataset.classes.elves.ElvesBase;
import com.starfyre1.dataset.classes.warriors.Warrior;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class PersonalInformationRecord implements Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "PERSONAL_INFORMATNION_SECTTION_START";			//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "PERSONAL_INFORMATNION_SECTTION_END";				//$NON-NLS-1$

	public static final String	HEIGHT_KEY				= "HEIGHT_KEY";										//$NON-NLS-1$
	public static final String	WEIGHT_KEY				= "WEIGHT_KEY";										//$NON-NLS-1$
	public static final String	SEX_KEY					= "SEX_KEY";										//$NON-NLS-1$
	public static final String	HAIR_KEY				= "HAIR_KEY";										//$NON-NLS-1$
	public static final String	EYES_KEY				= "EYES_KEY";										//$NON-NLS-1$
	public static final String	AGE_KEY					= "AGE_KEY";										//$NON-NLS-1$
	public static final String	SOCIAL_CLASS_KEY		= "SOCIAL_CLASS_KEY";								//$NON-NLS-1$

	private static final String	FEMALE					= "Female";											//$NON-NLS-1$

	private static final int	WEIGHT_ARRAY[]			= new int[] { 30, 35, 35, 40, 40, 45, 50, 50, 55,	//
					60, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 120, 125, 130, 135, 140, 150, 155,	//
					165, 175, 180, 190, 200, 210, 220, 230, 245, 265, 290, 310, 330, 350, 370, 390, 415,	//
					435, 460, 485, 500, 525, 550, 570, 595, 625, 660 };

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mCharacterSheet;

	int							mHeight					= 0;
	int							mWeight					= 0;
	String						mSex					= TKStringHelpers.EMPTY_STRING;
	String						mHair					= TKStringHelpers.EMPTY_STRING;
	String						mEyes					= TKStringHelpers.EMPTY_STRING;
	int							mAge					= 0;
	SocialClassRecord			mSocialClass;
	int							mCarry					= 0;
	float						mEncumbrance			= 0f;

	int							mOldHeight				= 0;
	int							mOldWeight				= 0;
	String						mOldSex					= TKStringHelpers.EMPTY_STRING;
	String						mOldHair				= TKStringHelpers.EMPTY_STRING;
	String						mOldEyes				= TKStringHelpers.EMPTY_STRING;
	int							mOldAge					= 0;
	SocialClassRecord			mOldSocialClass;
	int							mOldCarry				= 0;
	float						mOldEncumbrance			= 0f;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public PersonalInformationRecord(CharacterSheet sheet, boolean generate) {
		mCharacterSheet = sheet;

		if (generate) {
			generateCarry();
			generateHeightAndWeight();
			mSocialClass = new SocialClassRecord(true);
			updateOldRecords();
		}

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	/**
	 *
	 */
	private void updateOldRecords() {
		mOldHeight = mHeight;
		mOldWeight = mWeight;
		mOldSex = mSex;
		mOldHair = mHair;
		mOldEyes = mEyes;
		mOldAge = mAge;
		mOldSocialClass = mSocialClass.clone();
		mOldCarry = mCarry;
		mOldEncumbrance = mEncumbrance;
	}

	public void generateCarry() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();

		mCarry = (stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.CON) - 12) / 2 * 5 + 35;
	}

	private void generateHeightAndWeight() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		BaseClass characterClass = mCharacterSheet.getHeaderRecord().getCharacterClass();

		// random 3D6 (step 1)
		int heightValue = TKDice.generateDie(3, false, 0, 0);

		// Race and Class bonus (step 2 & 3)
		if (characterClass instanceof Dwarves || characterClass instanceof Dwarrow) {
			heightValue = (heightValue + 10) / 2;
		} else if (characterClass instanceof Human && characterClass instanceof Warrior) { //check to see if there are multiple warrior classes (ranger, barbarian, etc) Emailed Glen 09/08/2021 - yes/ implemented...
			heightValue++;
		}

		// Strength bonus (step 4)
		heightValue = generateStrengthBonus();

		// Up to this point the height and weight values are the same;
		int weightValue = heightValue;

		// Constitution bonus (step 5)
		heightValue += generateConHeightBonus();
		if (stats.getStat(1) < 13) {
			weightValue += generateConWeightBonus();
		}

		// Race Modifier (step 6)
		int classHeightBonus = 0;
		int classWeightBonus = 0;

		int str = stats.getStat(0);
		if (characterClass instanceof ElvesBase) {
			if (str < 15) {
				classHeightBonus = -2;
				classWeightBonus = -7;
			} else if (str < 17) {
				classHeightBonus = 1;
				classWeightBonus = -5;
			} else if (str < 19) {
				classHeightBonus = 3;
				classWeightBonus = -3;
			} else if (str < 21) {
				classHeightBonus = 5;
				classWeightBonus = 0;
			} else if (str < 23) {
				classHeightBonus = 6;
				classWeightBonus = 3;
			} else {
				classHeightBonus = 7;
				classWeightBonus = 3;
			}
		} else if (characterClass instanceof HalfElf) {
			if (str < 9) {
				classHeightBonus = 0;
				classWeightBonus = -3;
			} else if (str < 13) {
				classHeightBonus = 1;
				classWeightBonus = 0;
			} else if (str < 15) {
				classHeightBonus = 2;
				classWeightBonus = 1;
			} else if (str < 17) {
				classHeightBonus = 3;
				classWeightBonus = 2;
			} else if (str < 19) {
				classHeightBonus = 4;
				classWeightBonus = 3;
			} else {
				classHeightBonus = 5;
				classWeightBonus = 5;
			}
		} else if (characterClass instanceof Dwarves) {
			if (str < 13) {
				classHeightBonus = -8;
				classWeightBonus = -6;
			} else if (str < 15) {
				classHeightBonus = -6;
				classWeightBonus = -6;
			} else if (str < 17) {
				classHeightBonus = -4;
				classWeightBonus = -5;
			} else if (str < 19) {
				classHeightBonus = -3;
				classWeightBonus = -3;
			} else if (str < 22) {
				classHeightBonus = -2;
				classWeightBonus = -3;
			} else {
				classHeightBonus = -1;
				classWeightBonus = 0;
			}
		} else if (characterClass instanceof Dwarrow) {
			if (str < 5) {
				classHeightBonus = -10;
				classWeightBonus = -10;
			} else if (str < 7) {
				classHeightBonus = -10;
				classWeightBonus = -8;
			} else if (str < 10) {
				classHeightBonus = -8;
				classWeightBonus = -8;
			} else if (str < 13) {
				classHeightBonus = -6;
				classWeightBonus = -6;
			} else if (str < 15) {
				classHeightBonus = -4;
				classWeightBonus = -4;
			} else {
				classHeightBonus = -2;
				classWeightBonus = -2;
			}
		}
		heightValue += classHeightBonus;
		weightValue += classWeightBonus;

		// Sex modifier (step 7)
		int sexWeightBonus = 0;
		int sexHeightBonus = 0;
		// DW Need to enter players sex before doing this or update after entry
		if (FEMALE.equals(mSex)) {
			if (characterClass instanceof Human) {
				sexWeightBonus = -5;
				sexHeightBonus = -3;
			} else if (characterClass instanceof ElvesBase || characterClass instanceof HalfElf) {
				sexWeightBonus = -3;
				sexHeightBonus = -2;
			} else if (characterClass instanceof Dwarves || characterClass instanceof Dwarrow) {
				sexWeightBonus = -1;
				sexHeightBonus = -1;
			}
		}
		heightValue += sexHeightBonus;
		weightValue += sexWeightBonus;

		int damageWeightBonus = mCharacterSheet.getCombatInformationRecord().getDamageBonus() * 5;

		// set the results
		mHeight = generateHeight(heightValue);
		mWeight = generateWeight(weightValue) + damageWeightBonus;
	}

	private int generateHeight(int value) {
		int height = 49 + value;

		return height;
	}

	private int generateWeight(int value) {
		return WEIGHT_ARRAY[value + 7];
	}

	private int generateStrengthBonus() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int str = stats.getModifiedStat(AttributesRecord.STR);
		//		for (str = 3; str < 26; str++) {
		int strBonus = 0;
		if (str < 11 || str > 22) {
			strBonus = (str - 3) / 2 * 2;
		} else if (str < 13) {
			strBonus = 6;
		} else if (str < 23) {
			strBonus = (str - 5) / 2 * 2;
		}
		//			System.out.println("str: " + str + " bonus: " + strBonus);
		//		}

		return strBonus;
	}

	private int generateConHeightBonus() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int con = stats.getStat(1);

		int conHeightBonus = 0;

		if (con < 6) {
			conHeightBonus = -1;
		} else if (con < 13) {
			conHeightBonus = 0;
		} else if (con < 16) {
			conHeightBonus = 1;
		} else if (con < 19) {
			conHeightBonus = 2;
		} else if (con < 22) {
			conHeightBonus = 3;
		} else {
			conHeightBonus = 5;
		}

		return conHeightBonus;
	}

	private int generateConWeightBonus() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int con = stats.getStat(1);

		int conWeightBonus = 0;

		if (con < 6) {
			int percent = (int) (TKDice.getNext() * 100 + 1);
			if (percent < 51) {
				conWeightBonus -= TKDice.getNext() * 6 + 1;
			} else if (percent < 76) {
				conWeightBonus += TKDice.getNext() * 6 + 1;
			} else {
				conWeightBonus += TKDice.getNext() * 6 + 1 + (TKDice.getNext() * 6 + 1);
			}
		} else if (con < 13) {
			int percent = (int) (TKDice.getNext() * 100 + 1);
			if (percent < 51) {
				conWeightBonus -= TKDice.getNext() * 3 + 1;
			} else if (percent < 76) {
				conWeightBonus += TKDice.getNext() * 3 + 1;
			} else {
				conWeightBonus += TKDice.getNext() * 6 + 1;
			}
		}
		return conWeightBonus;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getHeight() {
		int feet = mHeight / 12;
		int inches = mHeight % 12;

		return TKStringHelpers.EMPTY_STRING + feet + "\'" + inches + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** @return The sex. */
	public String getSex() {
		return mSex;
	}

	/** @param sex The value to set for sex. */
	public void setSex(String sex) {
		mSex = sex;
	}

	/** @return The hair. */
	public String getHair() {
		return mHair;
	}

	/** @param hair The value to set for hair. */
	public void setHair(String hair) {
		mHair = hair;
	}

	/** @return The eyes. */
	public String getEyes() {
		return mEyes;
	}

	/** @param eyes The value to set for eyes. */
	public void setEyes(String eyes) {
		mEyes = eyes;
	}

	/** @return The age. */
	public int getAge() {
		return mAge;
	}

	/** @param age The value to set for age. */
	public void setAge(int age) {
		mAge = age;
	}

	/** @return The weight. */
	public int getWeight() {
		return mWeight;
	}

	/** @return The socialClass. */
	public String getSocialClassTitle() {
		if (mSocialClass == null) {
			return TKStringHelpers.EMPTY_STRING;
		}
		return mSocialClass.getSocialClass();
	}

	/** @return The socialClass. */
	public SocialClassRecord getSocialClass() {
		return mSocialClass;
	}

	/** @return The carry. */
	public int getCarry() {
		return mCarry;
	}

	/** @return The carry. */
	public float getEncumbrance() {
		return mEncumbrance;
	}

	public void setEncumbrance(float encumbrance) {
		mEncumbrance = encumbrance;
	}

	public String getCarryTooltip() {
		String tooltip;
		int value = (int) (mEncumbrance / (mCarry + .001f));

		if (value >= 4) {
			tooltip = "You are carrying more than 4X your encumbrance and cannot move or fight"; //$NON-NLS-1$
		} else if (value == 3) {
			tooltip = "<html>You are carrying more than 3X your encumbrance<br>" // //$NON-NLS-1$
							+ "Movement is cut by 75%.<br>" // //$NON-NLS-1$
							+ "Combat speeds are at -5.<br>" // //$NON-NLS-1$
							+ "Combat bonus' are at -25%.<br>" // //$NON-NLS-1$
							+ "Character has a -20% on their save Vs. surprise,<br>" // //$NON-NLS-1$
							+ "those that they try to surprise get a +20% added to theirs.<br>" // //$NON-NLS-1$
							+ "Loses 1 stamina point per 10 min.of movement or 3 rounds of combat.</htmp>"; //$NON-NLS-1$

		} else if (value == 2) {
			tooltip = "<html>You are carrying more than 2X your encumbrance<br>" // //$NON-NLS-1$
							+ "Movement is cut by 50%.<br>" // //$NON-NLS-1$
							+ "Combat speeds are at -3.<br>" // //$NON-NLS-1$
							+ "Combat bonus' are at -15%.<br>" // //$NON-NLS-1$
							+ "Character has a -10% on their save Vs. surprise,<br>" // //$NON-NLS-1$
							+ "those that they try to surprise get a +10% added to theirs.</htmp>"; //$NON-NLS-1$

		} else if (value == 1) {
			tooltip = "<html>You are carrying more than 1X your encumbrance<br>" // //$NON-NLS-1$
							+ "Movement is cut by 25%.<br>" // //$NON-NLS-1$
							+ "Combat speeds are at -1.<br>" // //$NON-NLS-1$
							+ "Combat bonus' are at -5%.</htmp>"; //$NON-NLS-1$

		} else {
			tooltip = "<html>You are carrying under your encumbrance</html>"; //$NON-NLS-1$
		}

		return tooltip;
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
						generateCarry();
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
				}
			}
			generateCarry();
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
		br.write(HEIGHT_KEY + TKStringHelpers.SPACE + mHeight + System.lineSeparator());
		br.write(WEIGHT_KEY + TKStringHelpers.SPACE + mWeight + System.lineSeparator());
		br.write(SEX_KEY + TKStringHelpers.SPACE + mSex + System.lineSeparator());
		br.write(HAIR_KEY + TKStringHelpers.SPACE + mHair + System.lineSeparator());
		br.write(EYES_KEY + TKStringHelpers.SPACE + mEyes + System.lineSeparator());
		br.write(AGE_KEY + TKStringHelpers.SPACE + mAge + System.lineSeparator());
		br.write(SOCIAL_CLASS_KEY + TKStringHelpers.SPACE + mSocialClass.getSocialClass().replace(" ", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
		updateOldRecords();
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (HEIGHT_KEY.equals(key)) {
			mHeight = TKStringHelpers.getIntValue(value, 0);
		} else if (WEIGHT_KEY.equals(key)) {
			mWeight = TKStringHelpers.getIntValue(value, 0);
		} else if (SEX_KEY.equals(key)) {
			mSex = value;
		} else if (HAIR_KEY.equals(key)) {
			mHair = value;
		} else if (EYES_KEY.equals(key)) {
			mEyes = value;
		} else if (AGE_KEY.equals(key)) {
			mAge = TKStringHelpers.getIntValue(value, 0);
		} else if (SOCIAL_CLASS_KEY.equals(key)) {
			mSocialClass = new SocialClassRecord(value.replace("~", " ")); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

	/**
	 *
	 */
	public void clearRecords() {
		mHeight = 0;
		mWeight = 0;
		mSex = TKStringHelpers.EMPTY_STRING;
		mHair = TKStringHelpers.EMPTY_STRING;
		mEyes = TKStringHelpers.EMPTY_STRING;
		mAge = 0;
		mSocialClass = null;
	}
}
