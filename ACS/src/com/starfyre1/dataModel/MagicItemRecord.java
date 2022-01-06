/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTableRecord;

import java.util.Vector;

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

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MagicItemRecord(int count, boolean equipped, String name, int charges, float cost) {
		mCount = count;
		mEquipped = equipped;
		mName = name;
		mCharges = charges;
		mCost = cost;
	}

	public MagicItemRecord(Vector<Object> obj) {
		mCount = obj.get(0) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(0), 0) : ((Integer) obj.get(0)).intValue();
		mEquipped = ((Boolean) obj.get(1)).booleanValue();
		mName = (String) obj.get(2);
		mCharges = obj.get(3) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(3), 0) : ((Integer) obj.get(3)).intValue();
		mCost = ((Float) obj.get(4)).floatValue();
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}

		MagicItemRecord record = (MagicItemRecord) obj;
		if (mName.equals(record.mName) && //
						mCharges == record.mCharges) {// && //
			//						mCost == record.mCost) {
			return true;
		}
		return false;
	}

	// DW fix - the charges will be changing... will need a better hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (mName == null ? 0 : mName.hashCode());
		result = prime * result + (mCharges ^ mCharges >>> 32);

		return result;
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
