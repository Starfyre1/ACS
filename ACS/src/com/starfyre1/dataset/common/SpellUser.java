/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.common;

import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;

public abstract class SpellUser extends BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	public ArrayList<ArrayList<SpellRecord>>	mSpells	= new ArrayList<>();
	protected String							mSecondRequisite;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public ArrayList<SpellRecord> getSpellList(int level) {
		return mSpells.get(level);
	}

	public ArrayList<String> getSpellNamesList(int level) {
		ArrayList<String> list = new ArrayList<>();

		for (SpellRecord record : getSpellList(level)) {
			list.add(record.getName());
		}

		return list;
	}

	/** @return The secondRequisite. */
	public String getSecondRequisite() {
		return mSecondRequisite;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
