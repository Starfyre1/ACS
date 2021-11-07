/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

public class Human extends BaseClass {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean mInnateSkills[] = { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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
		// DW human, non-thief with save vs surprise > 75% = +10% (30% total)
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
		// DW verify
		return 0;
	}

	@Override
	public int getHitBonus() {
		// DW verify
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl;
	}

	@Override
	public int getMissileBonus() {
		// DW verify
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 2;
	}

	@Override
	public int getBowBonus() {
		// DW verify
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
