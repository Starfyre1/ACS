/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKTableRecord;

public class MagicItemRecord extends TKTableRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int		mCount;
	private boolean	mEquipped;
	private String	mName;
	private int		mCharges;
	private float	mCost;
	//	private ImageIcon	mIcon	= new ImageIcon("D:\\Programming\\workspace-Athri_Character_Sheet\\ACS\\src\\com\\starfyre1\\Images\\ImagePlus.png");	//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MagicItemRecord(int count, boolean equipped, String item, int charges, float cost) {
		mCount = count;
		mEquipped = equipped;
		mName = item;
		mCharges = charges;
		mCost = cost;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mCount + " " + mEquipped + " " + mName + " " + mCharges + " " + mCost; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	@Override
	public MagicItemRecord clone() {
		return new MagicItemRecord(mCount, mEquipped, mName, mCharges, mCost);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public Object[] getRecord() {
		return new Object[] { mCount > 0 ? Integer.valueOf(mCount) : " ", Boolean.valueOf(mEquipped), mName, Integer.valueOf(mCharges), Float.valueOf(mCost) }; //$NON-NLS-1$
	}

	@Override
	public Object getRecord(int id) {
		Object output = switch (id) {
			case 0:
				yield Integer.valueOf(mCount);
			case 1:
				yield Boolean.valueOf(mEquipped);
			case 2:
				yield mName;
			case 3:
				yield Integer.valueOf(mCharges);
			case 4:
				yield Float.valueOf(mCost);
			default:
				throw new IllegalArgumentException("Unexpected value: " + id); //$NON-NLS-1$
		};

		return output;

	}

	@Override
	public void setRecord(int column, Object value) {
		// DW may not need this
	}

	/** @return The count. */
	public int getCount() {
		return mCount;
	}

	public void setCount(int count) {
		mCount = count;
	}

	/** @return The equipped. */
	public boolean isEquipped() {
		return mEquipped;
	}

	/** @param equipped The value to set for equipped. */
	public void setEquipped(boolean equipped) {
		mEquipped = equipped;
	}

	/** @return The item. */
	public String getName() {
		return mName;
	}

	/** @param item The value to set for item. */
	public void setName(String item) {
		mName = item;
	}

	/** @return The charges. */
	public int getCharges() {
		return mCharges;
	}

	/** @param charges The value to set for charges. */
	public void setCharges(int charges) {
		mCharges = charges;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	/** @param cost The value to set for cost. */
	public void setCost(float cost) {
		mCost = cost;
	}

	// DW need to rejigger Magic Items... need to support weapons, armor, etc and have encumbrance
	// maybe get rid of MagicItems entirely and integrate it into the other records.
	public float getEncumbrance() {
		return 0;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
