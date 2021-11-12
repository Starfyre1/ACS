/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.spells;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellDescriptionRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int	sCount	= 0;

	private int			mIndex;
	String				mName;
	String				mDescription;
	ArrayList<Pair>		mEffects;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellDescriptionRecord(String name, String description, Pair... effects) {
		mIndex = sCount++;
		mName = name;
		mDescription = description;
		mEffects = new ArrayList<>(Arrays.asList(effects));
		//		System.out.println(mIndex + " " + mName);
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
	/** @return The name. */
	public static int getCount() {
		return sCount;
	}

	/** @return The name. */
	public int getIndex() {
		return mIndex;
	}

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The description. */
	public String getDescription() {
		return mDescription;
	}

	/** @return The effects. */
	public ArrayList<Pair> getEffects() {
		return mEffects;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
