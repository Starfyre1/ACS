/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

public class Elves extends BaseClass {

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 2;
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
	public int generateUnallocated() {
		return 3;
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
