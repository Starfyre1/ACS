/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.dataModel.LanguageRecord;
import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.startup.ACS;

import java.util.ArrayList;

public abstract class MagesBase extends SpellUser {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String mInnateDisplayList[] = { "None" }; //$NON-NLS-1$

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
		return null;
	}

	@Override
	public String[] getInnateDisplayList() {
		return mInnateDisplayList;
	}

	// Skills
	@Override
	public int getBandaging() {
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
		return 20 + 0;
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
		return 0;
	}

	@Override
	public int getHitBonus() {
		return 2;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 2;
	}

	@Override
	public int getBowBonus() {
		return -10;
	}

	@Override
	public int getMovement() {
		return 12;
	}

	@Override
	public int getUnallocated() {
		return 4;
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
		return 10;
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
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl;
	}

	@Override
	public int getBelief() {
		return 5;
	}

	// Defensive Information
	@Override
	public int getStamina() {
		int stamina;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		if (lvl <= 10) {
			stamina = (lvl - 1) * 3;
		} else {
			stamina = 27 + lvl - 10;
		}
		return stamina;
	}

	@Override
	public int getHitPoints() {
		int hp;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();

		if (lvl <= 10) {
			hp = lvl - 1;
		} else {
			hp = 9;
		}

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
