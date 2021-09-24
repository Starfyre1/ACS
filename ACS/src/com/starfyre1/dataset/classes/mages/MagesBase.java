/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.startup.ACS;

public class MagesBase extends SpellUser {

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
	@Override
	public int generateUnallocated() {
		return 4;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
