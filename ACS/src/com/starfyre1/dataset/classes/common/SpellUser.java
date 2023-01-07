/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.common;

import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

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
	public static ArrayList<SpellRecord> getCommonSpells() {
		// Power Common:
		ArrayList<SpellRecord> commonSpells = new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(-1, "Anti-Magic", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(-1, "Detect Aura", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(-1, "Detect Magic", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(-1, "Dispel Magic", 0, 25, 0))); //$NON-NLS-1$
		return commonSpells;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public String getFocus() {
		return "User/DM"; //$NON-NLS-1$
	}

	@Override
	public String getFocusToolTip() {
		return "<html>Their focus can be anything the DM or player chooses.</html>"; //$NON-NLS-1$
	}

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

	public SpellRecord getSpellRecord(int level, String name) {
		for (SpellRecord record : getSpellList(level)) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
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
