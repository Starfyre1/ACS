/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataModel.LanguageRecord;
import com.starfyre1.dataset.classes.common.BaseClass;
import com.starfyre1.startup.ACS;

import java.util.ArrayList;

public class HalfElf extends BaseClass {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean[]	mInnateSkills		= { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };
	private String[]	mInnateDisplayList	= { "None" };																																		//$NON-NLS-1$
	private int[]		mMinimumStats		= { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public int[] getMinimumStats() {
		return mMinimumStats;
	}

	@Override
	public String[] getInnateDisplayList() {
		return mInnateDisplayList;
	}

	// Skills
	@Override
	public boolean[] getInnateSkills() {
		return mInnateSkills;
	}

	@Override
	public int getBandaging() {
		return 0;
	}

	@Override
	public int getHunting() {
		return 0;
	}

	@Override
	public int getTracking() {
		return 0;
	}

	@Override
	public int getDetectMagic() {
		return 0;
	}

	@Override
	public int getDetectMetals() {
		return 0;
	}

	@Override
	public int getDetectSecretDoors() {
		return 0;
	}

	@Override
	public int getDetectTraps() {
		return 0;
	}

	@Override
	public int getAppraise() {
		return 0;
	}

	@Override
	public int getDepthSense() {
		return 0;
	}

	@Override
	public int getHerbalLore() {
		return 0;
	}

	@Override
	public int getPerception() {
		return 0;
	}

	@Override
	public int getBerserk() {
		return 0;
	}

	@Override
	public int getConceal() {
		return 0;
	}

	@Override
	public int getStealth() {
		return 0;
	}

	@Override
	public int getHear() {
		return 30 + 0;
	}

	@Override
	public int getClimb() {
		return 0;
	}

	@Override
	public int getUnallocatedSkills() {
		return 0;
	}

	// CombatInfo
	@Override
	public int getDefenseBonus() {
		// DW verify
		return 0;
	}

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		// DW verify
		return 3 + lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 2;
	}

	@Override
	public int getBowBonus() {
		return 0;
	}

	@Override
	public int getMovement() {
		return 12;
	}

	@Override
	public int getUnallocated() {
		// DW verify
		return 0;
	}

	@Override
	public int getHitBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.STR);
		//		return value * 3;
		// No Max;
		return -1;
	}

	@Override
	public int getMissileBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		//		return value * 3;
		// No Max;
		return -1;
	}

	@Override
	public int getBowBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.BOW);
		//		return value * 3;
		// No Max;
		return -1;
	}

	// Saving Throws
	@Override
	public int getBleeding() {
		return 0;
	}

	@Override
	public int getMagic() {
		return 0;
	}

	@Override
	public int getPoison() {
		return 0;
	}

	@Override
	public int getShock() {
		return 0;
	}

	@Override
	public int getStress() {
		return 0;
	}

	@Override
	public int getUnconscious() {
		return 0;
	}

	@Override
	public int getSurprise() {
		return 0;
	}

	@Override
	public int getBelief() {
		return 0;
	}

	// Defensive Information
	@Override
	public int getStamina() {
		int stamina = 0;
		// DW verify
		//		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		//		if (lvl <= 10) {
		//			stamina = (lvl - 1) * 5;
		//		} else {
		//			stamina = 45 + (lvl - 10) * 2;
		//		}
		return stamina;
	}

	@Override
	public int getHitPoints() {
		int hp = 0;
		// DW verify
		//		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		//
		//		if (lvl <= 10) {
		//			hp = lvl - 1;
		//		} else {
		//			hp = 9 + (lvl - 9) / 2;
		//		}

		return hp;
	}

	// Languages
	@Override
	public ArrayList<String> getLanguages() {
		ArrayList<String> languages = new ArrayList<>();

		languages.add(LanguageRecord.LANGUAGES[0]);

		return languages;

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
