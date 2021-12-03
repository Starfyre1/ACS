/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKTableRecord;

public class EquipmentRecord extends TKTableRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private int		mCount;
	private boolean	mEquipped;
	private String	mName;
	private float	mEncumbrance;
	private float	mCost;			// in silver
	private String	mNotes;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public EquipmentRecord(int count, boolean equipped, String name, float encumbrance, float cost, String notes) {

		mCount = count;
		mEquipped = equipped;
		mName = name;
		mEncumbrance = encumbrance;
		mCost = cost;
		mNotes = notes;

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mCount + " " + mEquipped + " " + mName + " " + mEncumbrance + " " + mCost + " " + mNotes; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	@Override
	public EquipmentRecord clone() {
		return new EquipmentRecord(mCount, mEquipped, mName, mEncumbrance, mCost, mNotes);
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

		EquipmentRecord record = (EquipmentRecord) obj;
		if (mName.equals(record.mName) && //
		// mEncumbrance == record.mEncumbrance && //
		// mCost == record.mCost && //
						mNotes.equals(record.mNotes)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (mName == null ? 0 : mName.hashCode());
		result = prime * result + (mNotes == null ? 0 : mNotes.hashCode());

		return result;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public Object[] getRecord() {
		return new Object[] { mCount > 0 ? Integer.valueOf(mCount) : " ", Boolean.valueOf(mEquipped), mName, Float.valueOf(mEncumbrance), Float.valueOf(mCost), mNotes }; //$NON-NLS-1$
	}

	@Override
	public Object getRecord(int id) {
		Object output = switch (id) {
			case 0:
				yield mCount > 0 ? Integer.valueOf(mCount) : " "; //$NON-NLS-1$
			case 1:
				yield Boolean.valueOf(mEquipped);
			case 2:
				yield mName;
			case 3:
				yield Float.valueOf(mEncumbrance);
			case 4:
				yield Float.valueOf(mCost);
			case 5:
				yield mNotes;
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

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The encumbrance. */
	public float getEncumbrance() {
		return mEncumbrance;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	/** @return The notes. */
	public String getNotes() {
		return mNotes;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
