/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.startup.ACS;

public abstract class PriestsBase extends SpellUser {

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
		return 3;
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
