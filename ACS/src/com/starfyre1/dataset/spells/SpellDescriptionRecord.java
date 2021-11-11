/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.spells;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellDescriptionRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	String			mName;
	String			mDescription;
	ArrayList<Pair>	mEffects;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellDescriptionRecord(String name, String description, Pair... effects) {
		mName = name;
		mDescription = description;
		mEffects = new ArrayList<>(Arrays.asList(effects));
	}

	static class Pair {
		String	mPairName;
		String	mPairDescription;

		public Pair(String name, String desc) {
			mPairName = name;
			mPairDescription = desc;
		}

		/** @return The pairName. */
		public String getPairName() {
			return mPairName;
		}

		/** @return The pairDescription. */
		public String getPairDescription() {
			return mPairDescription;
		}

	}
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
