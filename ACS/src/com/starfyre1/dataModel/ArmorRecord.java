/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTableRecord;
import com.starfyre1.dataset.MetalList;

import java.util.Arrays;
import java.util.Vector;

public class ArmorRecord extends TKTableRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int		mCount;					// id 0
	private boolean	mEquipped;				// id 1
	private String	mName;					// id 2
	private int		mMetal;					// id 3
	private int[]	mProtectionType;		// id 4 // 0=Head-Top, 1=Head-Side, 2=Head-Face, 3=Neck, 4=Torso, 5=Arms, 6=Hands, 7=Legs, 8=Feet/Calves, 9=Shield
	private int		mProtectionAmount;		// id 5
	private float	mEncumbrance;			// id 6
	private int		mAbsorption;			// id 7
	private int		mBonus;					// id 8 // 50% chance to increase absorption by 1
	private int		mMissileAbsorption;		// id 9
	private int		mStrengthRequirement;	// id 10
	private int		mParry;					// id 11
	private int		mBreak;					// id 12
	private float	mCost;					// id 13

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public ArmorRecord(int count, boolean equipped, String name, int metal, int[] protectionType, int protectionAmount, float encumbrance, int absorption, int bonus, int missileAbsorption, int strengthRequirement, int parry, int breakage, float cost) {
		mCount = count;
		mEquipped = equipped;
		mName = name;
		mMetal = metal;
		mProtectionType = protectionType;
		mProtectionAmount = protectionAmount;
		mEncumbrance = encumbrance;
		mAbsorption = absorption;
		mBonus = bonus;
		mMissileAbsorption = missileAbsorption;
		mStrengthRequirement = strengthRequirement;
		mParry = parry;
		mBreak = breakage;
		mCost = cost;
	}

	public ArmorRecord(Vector<Object> obj) {
		mCount = obj.get(0) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(0), 0) : ((Integer) obj.get(0)).intValue();
		mEquipped = ((Boolean) obj.get(1)).booleanValue();
		mName = (String) obj.get(2);
		mMetal = MetalList.getMetalID(obj.get(3) instanceof String ? (String) obj.get(3) : ((MetalRecord) obj.get(3)).getName());
		mProtectionType = TKStringHelpers.getIntArray((String) obj.get(4), new int[0]);
		mProtectionAmount = ((Integer) obj.get(5)).intValue();
		mEncumbrance = ((Float) obj.get(6)).floatValue();
		mAbsorption = obj.get(7) instanceof String ? 0 : ((Integer) obj.get(7)).intValue();
		mBonus = obj.get(8) instanceof String ? 0 : ((Integer) obj.get(8)).intValue();
		mMissileAbsorption = ((Integer) obj.get(9)).intValue();
		mStrengthRequirement = ((Integer) obj.get(10)).intValue();
		mParry = obj.get(11) instanceof String ? 0 : ((Integer) obj.get(11)).intValue();
		mBreak = obj.get(12) instanceof String ? 0 : ((Integer) obj.get(12)).intValue();
		mCost = ((Float) obj.get(13)).floatValue();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mCount + " " + mEquipped + " " + mName + " " + mMetal + " " + mProtectionType + " " + // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						mProtectionAmount + " " + mEncumbrance + " " + mAbsorption + " " + mBonus + " " + // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						mMissileAbsorption + " " + mStrengthRequirement + " " + mParry + " " + //  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						mBreak + " " + mCost; //$NON-NLS-1$

	}

	@Override
	public ArmorRecord clone() {
		return new ArmorRecord(mCount, mEquipped, mName, mMetal, mProtectionType, mProtectionAmount, //
						mEncumbrance, mAbsorption, mBonus, mMissileAbsorption, mStrengthRequirement, //
						mParry, mBreak, mCost);
	}

	@Override
	public boolean equals(Object obj) {
		ArmorRecord record = (ArmorRecord) obj;
		if (mName == record.mName && //
						mMetal == record.mMetal && //
						//						mProtectionType == record.mProtectionType && //
						mProtectionAmount == record.mProtectionAmount && //
						mEncumbrance == record.mEncumbrance && //
						mAbsorption == record.mAbsorption && //
						mBonus == record.mBonus && //
						mMissileAbsorption == record.mMissileAbsorption && //
						mStrengthRequirement == record.mStrengthRequirement && //
						mParry == record.mParry && //
						mBreak == record.mBreak && //
						mCost == record.mCost) {
			return true;
		}

		return false;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public Object[] getRecord() {
		return new Object[] { mCount > 0 ? Integer.valueOf(mCount) : " ", Boolean.valueOf(mEquipped), mName, //$NON-NLS-1$
						getMetalName(mMetal), Arrays.toString(mProtectionType), Integer.valueOf(mProtectionAmount), //
						Float.valueOf(mEncumbrance), mAbsorption > 0 ? Integer.valueOf(mAbsorption) : Integer.valueOf(0), //
						mBonus > 0 ? Integer.valueOf(mBonus) : Integer.valueOf(0), Integer.valueOf(mMissileAbsorption), //
						Integer.valueOf(mStrengthRequirement), mParry > 0 ? Integer.valueOf(mParry) : Integer.valueOf(0), //
						mBreak > 0 ? Integer.valueOf(mBreak) : Integer.valueOf(0), Float.valueOf(mCost) };
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
				yield getMetal();
			case 4:
				yield Arrays.toString(mProtectionType);
			case 5:
				yield Integer.valueOf(mProtectionAmount); // id 3
			case 6:
				yield Float.valueOf(mEncumbrance); // id 4
			case 7:
				yield mAbsorption > 0 ? Integer.valueOf(mAbsorption) : " "; // id 5 //$NON-NLS-1$
			case 8:
				yield mBonus > 0 ? Integer.valueOf(mBonus) : Integer.valueOf(0); // id 6 // 50% chance to increase absorption by 1
			case 9:
				yield Integer.valueOf(mMissileAbsorption); // id 7
			case 10:
				yield Integer.valueOf(mStrengthRequirement); // id 8
			case 11:
				yield mParry > 0 ? Integer.valueOf(mParry) : " "; // id 10 //$NON-NLS-1$
			case 12:
				yield mBreak > 0 ? Integer.valueOf(mBreak) : " "; // id 11 //$NON-NLS-1$
			case 13:
				yield Float.valueOf(mCost); // id 9
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

	/** @return The equipped. */
	public boolean isEquipped() {
		return mEquipped;
	}

	/** @param equipped The value to set for equipped. */
	public void setEquipped(boolean equipped) {
		mEquipped = equipped;
	}

	/** @return The protectionType. */
	public int[] getProtectionType() {
		return mProtectionType;
	}

	/** @return The name. */
	public String getName() {
		return mName;
	}

	public MetalRecord getMetal() {
		return MetalList.getMetal(mMetal);
	}

	/**
	 * @param metal
	 */
	public void setMetal(int metal) {
		mMetal = metal;
	}

	public int getMetalID() {
		return mMetal;
	}

	public String getMetalName(int metal) {
		return MetalList.getMetal(metal).getName();
	}

	/** @return The protectionAmount. */
	public int getProtectionAmount() {
		return mProtectionAmount;
	}

	/** @return The encumbrance. */
	public float getEncumbrance() {
		return mEncumbrance;
	}

	/** @return The absorption. */
	public int getAbsorption() {
		return mAbsorption;
	}

	/** @return The bonus. */
	public int getBonus() {
		return mBonus;
	}

	/** @return The missileAbsorption. */
	public int getMissileAbsorption() {
		return mMissileAbsorption;
	}

	/** @return The strengthRequirement. */
	public int getStrengthRequirement() {
		return mStrengthRequirement;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	/** @return The parry. */
	public int getParry() {
		return mParry;
	}

	/** @return The break. */
	public int getBreak() {
		return mBreak;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
