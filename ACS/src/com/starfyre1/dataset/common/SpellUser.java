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
	public ArrayList<ArrayList<SpellRecord>>	mSpells			= new ArrayList<>();
	protected String							mSecondRequisite;

	private boolean								mInnateSkills[]	= { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	public int getSpellLevels() {
		return mSpells.size();
	}

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

	@Override
	public boolean[] getInnateSkills() {
		return mInnateSkills;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
