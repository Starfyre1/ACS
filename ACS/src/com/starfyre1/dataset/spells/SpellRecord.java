/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.spells;

import com.starfyre1.startup.ACS;

public class SpellRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
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
	public SpellRecord(String name, int power, int castingTime, int extras, String description) {
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
