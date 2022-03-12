/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.interfaces.LevelListener;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class CombatInformationRecord implements LevelListener, Savable {
	public static final String	FILE_SECTTION_START_KEY			= "COMBAT_INFORMATION_SECTTION_START";													//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY			= "COMBAT_INFORMATION_SECTTION_END";													//$NON-NLS-1$

	public static final String	HIT_LEVEL_BONUS_KEY				= "HIT_LEVEL_BONUS_KEY";																//$NON-NLS-1$
	public static final String	BOW_LEVEL_BONUS_KEY				= "BOW_LEVEL_BONUS_KEY";																//$NON-NLS-1$
	public static final String	CASTING_SPEED_LEVEL_BONUS_KEY	= "CASTING_SPEED_LEVEL_BONUS_KEY";														//$NON-NLS-1$
	public static final String	FOCUS_KEY						= "FOCUS_KEY";																			//$NON-NLS-1$

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private int					manaValues[][]					= { { 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 50 },		//
					{ 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 100 },													//
					{ 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 200 },												//
					{ 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 300 },												//
					{ 5, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 400 } };

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	CharacterSheet				mCharacterSheet;

	private int					mHitBonus						= 0;
	private int					mHitLevelBonus					= 0;
	private int					mHitBonusMax					= 0;
	private int					mAttackSpeed					= 0;
	private int					mMissileBonus					= 0;
	private int					mMissileBonusMax				= 0;
	private int					mMissileSpeed					= 0;
	private int					mBowBonus						= 0;
	private int					mBowLevelBonus					= 0;
	private int					mBowBonusMax					= 0;
	private int					mBowSpeed						= 0;
	private int					mDamageBonus					= 0;
	private int					mCastingSpeed					= 0;
	private int					mCastingSpeedLevelBonus			= 0;
	private int					mDefense						= 0;
	private int					mMana							= 0;
	private int					mFreeAttack						= 0;
	private String				mFocus							= "";																					//$NON-NLS-1$
	private int					mMovement						= 0;
	private int					mUnallocated					= 0;
	private boolean				mUserFocus						= false;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public CombatInformationRecord(CharacterSheet sheet, boolean generate) {
		mCharacterSheet = sheet;

		if (generate) {
			updateValues();
		}

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void updateRecord() {
		updateValues();
	}

	private void updateValues() {
		if (mCharacterSheet.getHeaderRecord().getCharacterClass() != null) {
			generateHitBonus();
			generateHitBonusMax();
			generateMissileBonus();
			generateMissileBonusMax();
			generateBowBonus();
			generateBowBonusMax();
			generateDamageBonus();
			generateDefenseAndFreeAttack();
			generateMovement();
			generateUnallocated();

			generateAttackSpeed();
			generateMissileSpeed();
			generateBowSpeed();
			generateCastingSpeed();
			generateMana();
			generateFocus();
		} else {
			clearRecords();
		}
	}

	private void generateFocus() {
		if (!mUserFocus) {
			BaseClass charClass = mCharacterSheet.getHeaderRecord().getCharacterClass();
			mFocus = charClass.getFocus();
			mCharacterSheet.updateToolTips();
		}
	}

	private void generateMana() {
		int level = mCharacterSheet.getHeaderRecord().getLevel();
		int bonusMana = 0;
		int intValue = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.INT);
		intValue = (intValue - 10) / 3;

		if (level > 18) {
			bonusMana = manaValues[intValue][18] * (level - 18);
			level = 18;
		}

		mMana = manaValues[intValue][level - 1] + bonusMana;
	}

	private void generateHitBonus() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();

		int classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getHitBonus();
		int strBonus = stats.getModifiedStat(AttributesRecord.STR) - 10;
		int dex = stats.getModifiedStat(AttributesRecord.DEX);

		int dexBonus = 0;
		if (dex < 9) {
			dexBonus = dex - 9;
		} else if (dex > 12 && dex < 23) {
			dexBonus = (dex - 13) * 2 + 1;
		} else if (dex > 22) {
			dexBonus = (dex - 14) * 2 + 2;
		}
		mHitBonus = classBonus + strBonus + dexBonus;
	}

	private void generateHitBonusMax() {
		mHitBonusMax = mCharacterSheet.getHeaderRecord().getCharacterClass().getHitBonusMax();
	}

	private void generateMissileBonus() {
		int classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getMissileBonus();

		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int str = stats.getModifiedStat(AttributesRecord.STR);
		int dex = stats.getModifiedStat(AttributesRecord.DEX);

		int strBonus = 0;
		if (str < 9) {
			strBonus = -1;
		} else if (str > 12) {
			strBonus = str - 12;
		}

		int dexBonus = 0;
		if (dex < 10) {
			dexBonus = (dex - 10) * 3;
		} else if (dex > 11 && dex < 16) {
			dexBonus = (dex - 11) * 2 + 1;
		} else if (dex > 15 && dex < 18) {
			dexBonus = (dex - 12) * 3;
		} else if (dex < 24 && dex > 17) {
			dexBonus = (dex - 9) * 2;
		} else if (dex > 23) {
			dexBonus = (dex - 10) * 2 + 3;
		}

		mMissileBonus = classBonus + strBonus + dexBonus;
	}

	private void generateMissileBonusMax() {
		mMissileBonusMax = mCharacterSheet.getHeaderRecord().getCharacterClass().getMissileBonusMax();
	}

	// Email to Glenn on 7/9/21
	// Character class and race bonuses... do they stack?
	// No... Race bonus always trumps class bonus...
	// actually race and class are one
	private void generateBowBonus() {
		int classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getBowBonus();

		int bs = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.BOW);

		int BowBonus = 0;
		if (bs < 8) {
			BowBonus = (bs - 12) * 3;
		} else if (bs > 7 && bs < 12) { // 8 - 11
			BowBonus = (bs - 14) * 2;
		} else if (bs > 11 && bs < 19) { // 12 - 18
			BowBonus = (bs - 13) * 3;
		} else if (bs > 18) { // > 18
			BowBonus = (bs - 10) * 2;
		}
		mBowBonus = classBonus + BowBonus;
	}

	private void generateBowBonusMax() {
		mBowBonusMax = mCharacterSheet.getHeaderRecord().getCharacterClass().getBowBonusMax();
	}

	private int speedTable(int value) {
		int speed = (mCharacterSheet.getHeaderRecord().getLevel() - 1) / 2;

		if (value < 9) {
			speed += (value - 10) / 2;
		} else if (value > 12) {
			speed += (value - 11) / 2;
		}

		return speed;
	}

	private void generateAttackSpeed() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int value = (int) Math.ceil((stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.DEX)) / 2);

		mAttackSpeed = speedTable(value);
	}

	private void generateMissileSpeed() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int value = stats.getModifiedStat(AttributesRecord.DEX);

		mMissileSpeed = speedTable(value);
	}

	private void generateBowSpeed() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		int value = stats.getModifiedStat(AttributesRecord.BOW);

		mBowSpeed = speedTable(value);
	}

	// DW need to make this available to other classes that just use magic
	private void generateCastingSpeed() {
		AttributesRecord record = mCharacterSheet.getAttributesRecord();
		BaseClass base = ClassList.getCharacterClass(mCharacterSheet.getSpellListDisplay().getMagicArea());
		String secondary = base instanceof SpellUser ? ((SpellUser) base).getSecondRequisite() : null;
		if (secondary == null) {
			mCastingSpeed = 0;
		} else if (secondary.equals("Dexterity/Wisdom")) { //$NON-NLS-1$
			int value = (record.getModifiedStat(record.getArrayPosition("Dexterity")) + record.getModifiedStat(record.getArrayPosition("Wisdom"))) / 2; //$NON-NLS-1$ //$NON-NLS-2$
			mCastingSpeed = speedTable(value);
		} else {
			int value = record.getModifiedStat(record.getArrayPosition(secondary));
			mCastingSpeed = speedTable(value);
		}
	}

	private void generateMovement() {
		mMovement = mCharacterSheet.getHeaderRecord().getCharacterClass().getMovement();
	}

	public void generateUnallocated() {
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();
		BaseClass characterClass = headerRecord.getCharacterClass();
		if (characterClass != null) {
			int classBonus = characterClass.getUnallocated();
			int lvl = headerRecord.getLevel() - 1;

			mUnallocated = classBonus * lvl - mHitLevelBonus - mBowLevelBonus - mCastingSpeedLevelBonus;
		}
	}

	private void generateDamageBonus() {
		int str = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.STR);

		mDamageBonus = str < 10 ? str - 10 : str > 14 ? str - 14 : 0;
	}

	public void generateDefenseAndFreeAttack() {
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int dex = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		int dexBonus = dex > 14 ? (dex - 14) * 5 : dex < 6 ? (dex - 6) * 5 : 0;

		mDefense = getHitBonus() + getHitLevelBonus() + 30 + headerRecord.getCharacterClass().getDefenseBonus() + dexBonus;
		mFreeAttack = 30 + headerRecord.getLevel() + dexBonus;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The hitBonus. */
	public int getHitBonus() {
		return mHitBonus;
	}

	/** @param hitBonus The value to set for hitBonus. */
	public void setHitBonus(int hitBonus) {
		mHitBonus = hitBonus;
	}

	/** @return The hitLevelBonus. */
	public int getHitLevelBonus() {
		return mHitLevelBonus;
	}

	/** @param hitLevelBonus The value to set for hitBonusMax. */
	public void setHitLevelBonus(int hitLevelBonus) {
		mHitLevelBonus = hitLevelBonus;
	}

	/** @return The hitBonusMax. */
	public int getHitBonusMax() {
		return mHitBonusMax;
	}

	/** @return The missileBonusMax. */
	public int getMissileBonusMax() {
		return mMissileBonusMax;
	}

	/** @return The bowBonusMax. */
	public int getBowBonusMax() {
		return mBowBonusMax;
	}

	/** @return The attackSpeed. */
	public int getAttackSpeed() {
		return mAttackSpeed;
	}

	/** @param attackSpeed The value to set for attackSpeed. */
	public void setAttackSpeed(int attackSpeed) {
		mAttackSpeed = attackSpeed;
	}

	/** @return The missileBonus. */
	public int getMissileBonus() {
		return mMissileBonus;
	}

	/** @param missileBonus The value to set for missileBonus. */
	public void setMissileBonus(int missileBonus) {
		mMissileBonus = missileBonus;
	}

	/** @return The missileBonusMax. */
	public int getCastingLevelBonus() {
		return mCastingSpeedLevelBonus;
	}

	/** @param castingLevelBonus The value to set for missileBonusMax. */
	public void setCastingLevelBonus(int castingLevelBonus) {
		mCastingSpeedLevelBonus = castingLevelBonus;
	}

	/** @return The missileSpeed. */
	public int getMissileSpeed() {
		return mMissileSpeed;
	}

	/** @param missileSpeed The value to set for missileSpeed. */
	public void setMissileSpeed(int missileSpeed) {
		mMissileSpeed = missileSpeed;
	}

	/** @return The bowBonus. */
	public int getBowBonus() {
		return mBowBonus;
	}

	/** @param bowBonus The value to set for bowBonus. */
	public void setBowBonus(int bowBonus) {
		mBowBonus = bowBonus;
	}

	/** @return The bowBonusMax. */
	public int getBowLevelBonus() {
		return mBowLevelBonus;
	}

	/** @param bowLevelBonus The value to set for bowBonusMax. */
	public void setBowLevelBonus(int bowLevelBonus) {
		mBowLevelBonus = bowLevelBonus;
	}

	/** @return The bowSpeed. */
	public int getBowSpeed() {
		return mBowSpeed;
	}

	/** @param bowSpeed The value to set for bowSpeed. */
	public void setBowSpeed(int bowSpeed) {
		mBowSpeed = bowSpeed;
	}

	/** @return The damageBonus. */
	public int getDamageBonus() {
		return mDamageBonus;
	}

	/** @param damageBonus The value to set for damageBonus. */
	public void setDamageBonus(int damageBonus) {
		mDamageBonus = damageBonus;
	}

	/** @return The castingSpeed. */
	public int getCastingSpeed() {
		return mCastingSpeed;
	}

	/** @param castingSpeed The value to set for castingSpeed. */
	public void setCastingSpeed(int castingSpeed) {
		mCastingSpeed = castingSpeed;
	}

	/** @return The defense. */
	public int getDefense() {
		return mDefense;
	}

	/** @param defense The value to set for defense. */
	public void setDefense(int defense) {
		mDefense = defense;
	}

	/** @return The mana. */
	public int getMana() {
		return mMana;
	}

	/** @param mana The value to set for mana. */
	public void setMana(int mana) {
		mMana = mana;
	}

	/** @return The free. */
	public int getFree() {
		return mFreeAttack;
	}

	/** @param free The value to set for free. */
	public void setFree(int free) {
		mFreeAttack = free;
	}

	/** @return The focus. */
	public String getFocus() {
		return mFocus;
	}

	/** @param focus The value to set for focus. */
	public void setFocus(String focus) {
		mFocus = focus;
	}

	/** @return The movement. */
	public int getMovement() {
		return mMovement;
	}

	/** @param movement The value to set for movement. */
	public void setMovement(int movement) {
		mMovement = movement;
	}

	/** @return The unallocated points. */
	public int getUnallocated() {
		return mUnallocated;
	}

	/** @param unallocated The value to set for unallocated points. */
	public void setUnallocated(int unallocated) {
		mUnallocated = unallocated;
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
						generateUnallocated();
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
				}
			}
			generateUnallocated();
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
		br.write(HIT_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mHitLevelBonus + System.lineSeparator());
		br.write(BOW_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mBowLevelBonus + System.lineSeparator());
		br.write(CASTING_SPEED_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mCastingSpeedLevelBonus + System.lineSeparator());
		br.write(FOCUS_KEY + TKStringHelpers.SPACE + mFocus + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (HIT_LEVEL_BONUS_KEY.equals(key)) {
			mHitLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (BOW_LEVEL_BONUS_KEY.equals(key)) {
			mBowLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (CASTING_SPEED_LEVEL_BONUS_KEY.equals(key)) {
			mCastingSpeedLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (FOCUS_KEY.equals(key)) {
			mFocus = value;
			String baseFocus = mCharacterSheet.getHeaderRecord().getCharacterClass().getFocus();
			if (!baseFocus.equals(mFocus)) {
				mUserFocus = true;
			}
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

	public void clearRecords() {
		mHitLevelBonus = 0;
		mBowLevelBonus = 0;
		mCastingSpeedLevelBonus = 0;
		mHitBonus = 0;
		mHitBonusMax = 0;
		mHitLevelBonus = 0;
		mAttackSpeed = 0;
		mMissileBonus = 0;
		mMissileBonusMax = 0;
		mMissileSpeed = 0;
		mBowBonus = 0;
		mBowLevelBonus = 0;
		mBowBonusMax = 0;
		mBowSpeed = 0;
		mDamageBonus = 0;
		mCastingSpeed = 0;
		mCastingSpeedLevelBonus = 0;
		mDefense = 0;
		mMana = 0;
		mFreeAttack = 0;
		mFocus = TKStringHelpers.EMPTY_STRING;
		mMovement = 0;
		mUnallocated = 0;
		mUserFocus = false;
	}

}
