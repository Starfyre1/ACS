/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.dataset.classes.Dwarves;
import com.starfyre1.dataset.classes.Thief;
import com.starfyre1.dataset.classes.warriors.Ranger;
import com.starfyre1.dataset.classes.warriors.Warrior;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.LevelListener;

public class SavingThrowsRecord implements LevelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet	mCharacterSheet;

	private int				mAgility		= 0;
	private int				mBleeding		= 0;
	private int				mMagic			= 0;
	private int				mPoison			= 0;
	private int				mShock			= 0;
	private int				mStress			= 0;
	private int				mUnconscious	= 0;
	private int				mSurprise		= 0;
	private int				mBelief			= 0;
	private int				mPerception		= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public SavingThrowsRecord(CharacterSheet characterSheet, boolean generate) {
		mCharacterSheet = characterSheet;

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
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		BaseClass classInfo = headerRecord.getCharacterClass();
		int level = headerRecord.getLevel();
		int levelBonus = (level - 1) * 4;

		int classBonus = 0;
		int dwarvenBonus = 0;
		int classPerceptionBonus = 0;
		int levelSupriseBonus = 1;

		if (classInfo instanceof Thief) {
			classBonus = 10;
			classPerceptionBonus = 5;
			levelSupriseBonus = 2;
		} else if (classInfo instanceof Ranger) {
			classBonus = 10;
			classPerceptionBonus = 3;
		} else if (classInfo instanceof Warrior) {
			classBonus = 5;
			levelSupriseBonus = -5;
		} else if (classInfo instanceof Dwarves) {
			dwarvenBonus = stats.getModifiedStat(AttributesRecord.WP);
		}

		mAgility = stats.getModifiedStat(AttributesRecord.DEX) * 3 + 10 + levelBonus;
		mBleeding = stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.CON) * 2 + stats.getModifiedStat(AttributesRecord.WP) + levelBonus + classBonus + dwarvenBonus;
		mMagic = stats.getModifiedStat(AttributesRecord.INT) * 2 + stats.getModifiedStat(AttributesRecord.WIS) + levelBonus + dwarvenBonus;
		mPoison = stats.getModifiedStat(AttributesRecord.CON) * 3 + 10 + levelBonus + dwarvenBonus;
		mShock = stats.getModifiedStat(AttributesRecord.CON) * 2 + stats.getModifiedStat(AttributesRecord.WP) + 30 + levelBonus + classBonus + dwarvenBonus;
		mStress = stats.getModifiedStat(AttributesRecord.WP) * 3 + levelBonus + dwarvenBonus;
		mUnconscious = stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.CON) + stats.getModifiedStat(AttributesRecord.WP) * 2 + levelBonus + dwarvenBonus;

		mSurprise = stats.getModifiedStat(AttributesRecord.INT) + stats.getModifiedStat(AttributesRecord.DEX) + stats.getModifiedStat(AttributesRecord.WP) + 35 + level * levelSupriseBonus + classBonus;
		mPerception = stats.getModifiedStat(AttributesRecord.INT) + stats.getModifiedStat(AttributesRecord.WIS) + 15 + (level - 1) * 2 + classPerceptionBonus + dwarvenBonus;
		mBelief = stats.getModifiedStat(AttributesRecord.INT) + stats.getModifiedStat(AttributesRecord.WIS) + 35 + level * 5 + dwarvenBonus;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The agility. */
	public int getAgility() {
		return mAgility;
	}

	/** @param agility The value to set for agility. */
	public void setAgility(int agility) {
		mAgility = agility;
	}

	/** @return The bleeding. */
	public int getBleeding() {
		return mBleeding;
	}

	/** @param bleeding The value to set for bleeding. */
	public void setBleeding(int bleeding) {
		mBleeding = bleeding;
	}

	/** @return The magic. */
	public int getMagic() {
		return mMagic;
	}

	/** @param magic The value to set for magic. */
	public void setMagic(int magic) {
		mMagic = magic;
	}

	/** @return The poison. */
	public int getPoison() {
		return mPoison;
	}

	/** @param poison The value to set for poison. */
	public void setPoison(int poison) {
		mPoison = poison;
	}

	/** @return The shock. */
	public int getShock() {
		return mShock;
	}

	/** @param shock The value to set for shock. */
	public void setShock(int shock) {
		mShock = shock;
	}

	/** @return The stress. */
	public int getStress() {
		return mStress;
	}

	/** @param stress The value to set for stress. */
	public void setStress(int stress) {
		mStress = stress;
	}

	/** @return The unconscious. */
	public int getUnconscious() {
		return mUnconscious;
	}

	/** @param unconscious The value to set for unconscious. */
	public void setUnconcious(int unconscious) {
		mUnconscious = unconscious;
	}

	/** @return The surprise. */
	public int getSurprise() {
		return mSurprise;
	}

	/** @param surprise The value to set for surprise. */
	public void setSurprise(int surprise) {
		mSurprise = surprise;
	}

	/** @return The belief. */
	public int getBelief() {
		return mBelief;
	}

	/** @param belief The value to set for belief. */
	public void setBelief(int belief) {
		mBelief = belief;
	}

	/** @return The perception. */
	public int getPerception() {
		return mPerception;
	}

	/** @param perception The value to set for perception. */
	public void setPerception(int perception) {
		mPerception = perception;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
