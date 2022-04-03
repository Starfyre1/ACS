/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTableRecord;

import java.util.Vector;

public class AnimalRecord extends TKTableRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int		mCount;			// id 0
	private String	mName;			// id 1
	private int		mCarry;			// id 2
	private int		mMove;			// id 3
	private int		mTravel;		// id 4
	private String	mHits;			// id 5
	private int		mHitBonus;		// id 6
	private int		mKickDamage;	// id 7
	private int		mArmor;			// id 8
	private float	mCost;			// id 9
	private String	mNotes;			// id 10

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public AnimalRecord(int count, String name, int carry, int move, int travel, String hits, int hitBonus, int kickDamage, int armor, float cost, String notes) {
		mCount = count;
		mName = name;
		mCarry = carry;
		mMove = move;
		mTravel = travel;
		mHits = hits;
		mHitBonus = hitBonus;
		mKickDamage = kickDamage;
		mArmor = armor;
		mCost = cost;
		mNotes = notes;
	}

	public AnimalRecord(Vector<Object> obj) {
		mCount = obj.get(0) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(0), 0) : ((Integer) obj.get(0)).intValue();
		mName = (String) obj.get(1);
		mCarry = obj.get(2) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(2), 0) : ((Integer) obj.get(2)).intValue();
		mMove = obj.get(3) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(3), 0) : ((Integer) obj.get(3)).intValue();
		mTravel = obj.get(4) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(4), 0) : ((Integer) obj.get(4)).intValue();
		mHits = (String) obj.get(5);
		mHitBonus = obj.get(6) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(6), 0) : ((Integer) obj.get(6)).intValue();
		mKickDamage = obj.get(7) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(7), 0) : ((Integer) obj.get(7)).intValue();
		mArmor = obj.get(8) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(8), 0) : ((Integer) obj.get(8)).intValue();
		mCost = obj.get(9) instanceof String ? TKStringHelpers.getFloatValue((String) obj.get(9), 0) : ((Float) obj.get(9)).floatValue();
		mNotes = (String) obj.get(10);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mCount + " " + mName + " " + mCarry + " " + mMove + " " + mTravel + " " + mHits + " " + // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						mHitBonus + " " + mKickDamage + " " + mArmor + " " + mCost + " " + mNotes; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	}

	@Override
	public AnimalRecord clone() {
		return new AnimalRecord(mCount, mName, mCarry, mMove, mTravel, mHits, //
						mHitBonus, mKickDamage, mArmor, mCost, mNotes);
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

		AnimalRecord record = (AnimalRecord) obj;
		if (mName.equals(record.mName) && //
						mCarry == record.mCarry && //
						mMove == record.mMove && //
						mTravel == record.mTravel && //
						mHits.equals(record.mHits) && //
						mHitBonus == record.mHitBonus && //
						mKickDamage == record.mKickDamage && //
						mArmor == record.mArmor && //
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
		result = prime * result + (mCarry ^ mCarry >>> 32);
		result = prime * result + (mMove ^ mMove >>> 32);
		result = prime * result + (mTravel ^ mTravel >>> 32);
		result = prime * result + (mHits == null ? 0 : mHits.hashCode());
		result = prime * result + (mHitBonus ^ mHitBonus >>> 32);
		result = prime * result + (mKickDamage ^ mKickDamage >>> 32);
		result = prime * result + (mArmor ^ mArmor >>> 32);
		result = prime * result + (mNotes == null ? 0 : mNotes.hashCode());

		return result;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public Object[] getRecord() {
		return new Object[] { mCount > 0 ? Integer.valueOf(mCount) : " ", mName, Integer.valueOf(mCarry), // //$NON-NLS-1$
						Integer.valueOf(mMove), Integer.valueOf(mTravel), mHits, Integer.valueOf(mHitBonus), //
						Integer.valueOf(mKickDamage), Integer.valueOf(mArmor), Float.valueOf(mCost), mNotes };
	}

	@Override
	public Object getRecord(int id) {
		Object output = switch (id) {
			case 0:
				yield mCount > 0 ? Integer.valueOf(mCount) : " "; //$NON-NLS-1$
			case 1:
				yield mName;
			case 2:
				yield Integer.valueOf(mCarry);
			case 3:
				yield Integer.valueOf(mMove); // id 3
			case 4:
				yield Integer.valueOf(mTravel); // id 4
			case 5:
				yield mHits; // id 5
			case 6:
				yield Integer.valueOf(mHitBonus); // id 6 // 50% chance to increase absorption by 1
			case 7:
				yield Integer.valueOf(mKickDamage); // id 7
			case 8:
				yield Integer.valueOf(mArmor); // id 8
			case 9:
				yield Float.valueOf(mCost); // id 9
			case 10:
				yield mNotes; // id 9
			default:
				throw new IllegalArgumentException("Unexpected value: " + id); //$NON-NLS-1$
		};

		return output;

	}

	@Override
	public void setRecord(int id, Object value) {
		// DW may not need this
	}

	/** @return The count. */
	public int getCount() {
		return mCount;
	}

	public void setCount(int count) {
		mCount = count;
	}

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The carry. */
	public int getCarry() {
		return mCarry;
	}

	/** @return The move. */
	public int getMove() {
		return mMove;
	}

	/** @return The travel. */
	public int getTravel() {
		return mTravel;
	}

	/** @return The hits. */
	public String getHits() {
		return mHits;
	}

	/** @return The hitBonus. */
	public int getHitBonus() {
		return mHitBonus;
	}

	/** @return The kickDamage. */
	public int getKickDamage() {
		return mKickDamage;
	}

	/** @return The armor. */
	public int getArmor() {
		return mArmor;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	/** @return The notes. */
	public String getNotes() {
		return mNotes;
	}

	public String toRecordFile() {
		return mCount + ", \"" + mName + "\", " + mCarry + ", " + mMove + ", " + mTravel + ", \"" + mHits + "\", " + // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						mHitBonus + ", " + mKickDamage + ", " + mArmor + ", " + mCost + ", \"" + mNotes + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
