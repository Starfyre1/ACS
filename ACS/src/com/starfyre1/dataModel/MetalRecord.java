/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

public class MetalRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	Name		Encumbrance	Damage	Break	ASP		Hit Bonus	Price		Availability	Magical

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	// DW Will need to change this to MaterialRecord so leather/cloth etc make sense
	private String	mName;
	private float	mEncumbrance;
	private int		mDamage;
	private int		mBreak;
	private int		mASP;
	private int		mHitBonus;
	private int		mARBonus;
	private int		mAbsorb;
	private int		mCost;
	private String	mAvailability;
	private String	mMagical;

	private int[]	mBreakValues	= new int[] { +0, +2, +4, +6, +8, -2, -4 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MetalRecord(String name, float encumbrance, int damage, int brk, //
					int asp, int hb, int arBonus, int absorb, int cost, String availability, String magical) {
		mName = name;
		mEncumbrance = encumbrance;
		mDamage = damage;
		mBreak = brk; // { +0, +2, +4, +6, +8, *2, *4 }
		mASP = asp;
		mHitBonus = hb;
		mARBonus = arBonus;
		mAbsorb = absorb;
		mCost = cost;
		mAvailability = availability;
		mMagical = magical;

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mName + " " + mEncumbrance + " " + mDamage + " " + mBreak + " " + mASP + " " + mHitBonus + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						mARBonus + " " + mAbsorb + " " + mCost + " " + mAvailability + " " + mMagical; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	@Override
	public MetalRecord clone() {
		return new MetalRecord(mName, mEncumbrance, mDamage, mBreak, mASP, mHitBonus, //
						mARBonus, mAbsorb, mCost, mAvailability, mMagical);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The weight. */
	public float getEnumbrance() {
		return mEncumbrance;
	}

	/** @return The damage. */
	public int getDamage() {
		return mDamage;
	}

	/** @return The break. */
	// { +0, +2, +4, +6, +8, *2, *4 }
	// this either add a value to the normal break value or
	// it will multiply the value by the normal break value
	public int getBreak(int normalBreak) {
		int value = mBreakValues[mBreak];
		if (value < 0) {
			return normalBreak * -1 * value;
		}
		return normalBreak + value;

	}

	/** @return The aRBonus. */
	public int getARBonus() {
		return mARBonus;
	}

	/** @return The absorb. */
	public int getAbsorb() {
		return mAbsorb;
	}

	/** @return The aSP. */
	public int getASP() {
		return mASP;
	}

	/** @return The hitBonus. */
	public int getHitBonus() {
		return mHitBonus;
	}

	/** @return The cost. */
	public int getCost() {
		return mCost;
	}

	/** @return The availability. */
	public String getAvailability() {
		return mAvailability;
	}

	/** @return The magical. */
	public String getMagical() {
		return mMagical;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
