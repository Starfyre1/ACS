/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure;

public class TreasureRecord {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	TreasureDisplay2	mParent;

	private int			mCount			= 0;
	private int			mValue			= 0;
	private String		mDescription	= "";	//$NON-NLS-1$
	private int			mTotalValue		= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link TreasureRecord}.
	 */
	public TreasureRecord(TreasureDisplay2 parent) {
		mParent = parent;
	}

	public TreasureRecord(TreasureDisplay2 parent, int count, int value, String description) {
		mParent = parent;

		mCount = count;
		mValue = value;
		mDescription = description;
		mTotalValue = mCount * mValue;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The count. */
	public int getCount() {
		return mCount;
	}

	/** @return The value. */
	public int getValue() {
		return mValue;
	}

	/** @return The total Value. */
	public int getTotalValue() {
		return mTotalValue;
	}

	/** @return The Description. */
	public String getDescription() {
		return mDescription;
	}

	/** @param count The value to set for count. */
	public void setCount(int count) {
		mCount = count;
	}

	/** @param value The value to set for value. */
	public void setValue(int value) {
		mValue = value;
	}

	/** @param description The value to set for description. */
	public void setDescription(String description) {
		mDescription = description;
	}

	/** @param totalValue The value to set for totalValue. */
	public void setTotalValue(int totalValue) {
		mTotalValue = totalValue;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
