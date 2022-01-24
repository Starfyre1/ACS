/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.spells;

import com.starfyre1.ToolKit.TKTableRecord;
import com.starfyre1.startup.ACS;

public class SpellRecord extends TKTableRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int		mLevel;
	private String	mName;
	private int		mPower;

	// -lots = lots of time
	private int		mCastingTime;

	/*
	 *  F = Require a Focus, * = Maybe cast simultaneously, H = Requires Hypnotism or Charm cast at the same time
	 *  1 = Focus, 2 = simultaneously, 4 = Hypnotism/Charm, 0-7 results compounded... 3 = 1 & 2, 5 = 4 & 1, etc
	 */
	private byte	mExtras;
	private String	mDescription;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellRecord(int level, String name, int power, int castingTime, int extras) {
		mLevel = level;
		mName = name;
		mPower = power;
		mCastingTime = castingTime;
		mExtras = (byte) extras;
		mDescription = ACS.getSpellDescriptions().getDescription(mName);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The level. */
	public int getLevel() {
		return mLevel;
	}

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The power. */
	public int getPower() {
		return mPower;
	}

	/** @return The manaCost. */
	public int getCastingTime() {
		return mCastingTime;
	}

	/** @return The extras. */
	public short getExtras() {
		return mExtras;
	}

	/** @return The description. */
	public String getDescription() {
		return mDescription;
	}

	@Override
	public Object[] getRecord() {
		return new Object[] { Integer.valueOf(mLevel), mName, Integer.valueOf(mCastingTime), "" }; //$NON-NLS-1$
	}

	@Override
	public Object getRecord(int column) {
		Object output = switch (column) {
			case 0:
				yield mLevel > 0 ? Integer.valueOf(mLevel) : " "; //$NON-NLS-1$
			case 1:
				yield mName;
			case 2:
				yield mCastingTime > 0 ? Integer.valueOf(mCastingTime) : " "; //$NON-NLS-1$
			case 3:
				// DW implement detailed descriptions for extras
				yield " "; //$NON-NLS-1$
			default:
				throw new IllegalArgumentException("Unexpected value: " + column); //$NON-NLS-1$
		};
		return output;
	}

	@Override
	public void setRecord(int column, Object value) {
		// DW may not need this
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
