/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.dataModel.LanguageRecord;
import com.starfyre1.dataset.classes.common.BaseClass;
import com.starfyre1.startup.ACS;

import java.util.ArrayList;

public abstract class ElvesBase extends BaseClass {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	// SKILLS
	@Override
	public int getBandaging() {
		return 10;
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
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
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
		// DW Verify - herbal healing says all elves, elf type says only sailor and sithrian
		return 15;
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
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl * 2;
	}

	@Override
	public int getBowBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 5 + lvl * 3;
	}

	@Override
	public int getMovement() {
		return 15;
	}

	@Override
	public int getUnallocated() {
		return 3;
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
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl;
	}

	@Override
	public int getBelief() {
		return 0;
	}

	// Languages
	@Override
	public ArrayList<String> getLanguages() {
		ArrayList<String> languages = new ArrayList<>();

		languages.add(LanguageRecord.LANGUAGES[1]);

		return languages;

	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
