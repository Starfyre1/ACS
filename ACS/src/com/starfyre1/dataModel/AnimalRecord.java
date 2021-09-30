/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKTableRecord;

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
	public AnimalRecord() {
	}

	public AnimalRecord(int count, String type, int carry, int move, int travel, String hits, int hitBonus, int kickDamage, int armor, float cost, String notes) {
		mCount = count;
		mName = type;
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
