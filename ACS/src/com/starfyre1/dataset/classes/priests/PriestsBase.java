/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.startup.ACS;

public class PriestsBase extends SpellUser {

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
	public int generateUnallocated() {
		return 4;
	}
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
