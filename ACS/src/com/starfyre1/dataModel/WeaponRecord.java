/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTableRecord;
import com.starfyre1.dataset.MetalList;

import java.util.Vector;

public class WeaponRecord extends TKTableRecord implements Comparable<WeaponRecord> {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		UNEXPECTED_VALUE	= "Unexpected value: ";								//$NON-NLS-1$
	/*
	 * The Weapon() class creates the different weapons using WeaponRecord().
	 * The WeaponInformationDisplay() Shows equipped weapons using WeaponRecord().
	 * The EquipmentDisplay() shows owned equipment such as WeaponRecord().
	 */
	private static final String[]	mTypeLabel			= { "Bladed", "Blunt", "Misc.", "Thrown", "Bows" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	private static final String[]	mHandedLabel		= { "1", "1, 2", "2", "Charging" };					//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	// DW Implement Tooltips or get rid of them
	// private static final String[]	mTypeTooltip		= { "Hand to Hand Bladed Weapons", "Hand to Hand Blunt Weapons", "Hand to Hand Miscellaneous Weapons", "Thrown Weapons", "Bows" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	// private static final String[]	mHandedTooltip		= { "1 handed only", "Either 1 or 2 handed", "2 handed only", "mounted & charging only" };											//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int						mCount;
	private boolean					mEquipped;
	private String					mName;
	private int						mMetal;
	private int						mType;																	// 0 = H2H Bladed, 1 = H2H Blunt, 2 = H2H Misc, 3 = Thrown, 4 = Bows
	private int						mHanded;																// 0 = 1 handed only, 1 = either 1 or 2 handed, 2 = 2 handed only, 3 = mounted & charging only
	private int						mStrength;
	private int						mDexterity;
	private float					mEncumbrance;
	private int						mWeaponLength;															// -1 = N/A
	private int						mAttackSpeed;
	private int						mWeaponBreak;															// -1 = N/A (doesn't break)
	private int						mHitBonus;
	private int						mDPHitBonus;
	private int						mDamageOneHanded;
	private int						mDamageTwoHanded;
	private float					mCost;																	// in Silver

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public WeaponRecord(int count, boolean equipped, String name, int metal, int type, int handed, int strength, int dexterity, float encumbrance, int weaponLength, int attackSpeed, int weaponBreak, int hitBonus, int dpHitBonus, int damageOneHanded, int damageTwoHanded, float cost) {

		mCount = count;
		mEquipped = equipped;
		mName = name;
		mMetal = metal;
		mType = type;
		mHanded = handed;
		mStrength = strength;
		mDexterity = dexterity;
		mEncumbrance = encumbrance;
		mWeaponLength = weaponLength;
		mAttackSpeed = attackSpeed;
		mWeaponBreak = weaponBreak;
		mHitBonus = hitBonus;
		mDPHitBonus = dpHitBonus;
		mDamageOneHanded = damageOneHanded;
		mDamageTwoHanded = damageTwoHanded;
		mCost = cost;

	}

	// DW find out why some are Strings and some are Integers or Boolean?
	// see where we update the values because of the selected metal
	public WeaponRecord(Vector<Object> obj) {
		mCount = obj.get(0) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(0), 0) : ((Integer) obj.get(0)).intValue();
		mEquipped = ((Boolean) obj.get(1)).booleanValue();
		mName = (String) obj.get(2);
		mMetal = MetalList.getMetalID(obj.get(3) instanceof String ? (String) obj.get(3) : ((MetalRecord) obj.get(3)).getName());
		mType = TKStringHelpers.getIntValue((String) obj.get(4), 0);
		String temp = (String) obj.get(5);
		mHanded = temp.equals(mHandedLabel[0]) ? 0 : temp.equals(mHandedLabel[1]) ? 1 : temp.equals(mHandedLabel[2]) ? 2 : 3;
		mStrength = obj.get(6) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(6), 0) : ((Integer) obj.get(6)).intValue();
		mDexterity = obj.get(7) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(7), 0) : ((Integer) obj.get(7)).intValue();
		mEncumbrance = obj.get(8) instanceof String ? TKStringHelpers.getFloatValue((String) obj.get(8), 0) : ((Float) obj.get(8)).floatValue();
		mWeaponLength = obj.get(9) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(9), 0) : ((Integer) obj.get(9)).intValue();
		mAttackSpeed = obj.get(10) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(10), 0) : ((Integer) obj.get(10)).intValue();
		mWeaponBreak = obj.get(11) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(11), 0) : ((Integer) obj.get(11)).intValue();
		mHitBonus = obj.get(12) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(12), 0) : ((Integer) obj.get(12)).intValue();
		mDPHitBonus = obj.get(13) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(13), 0) : ((Integer) obj.get(13)).intValue();
		mDamageOneHanded = obj.get(14) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(14), 0) : ((Integer) obj.get(14)).intValue();
		mDamageTwoHanded = obj.get(15) instanceof String ? TKStringHelpers.getIntValue((String) obj.get(15), 0) : ((Integer) obj.get(15)).intValue();
		mCost = obj.get(16) instanceof String ? TKStringHelpers.getFloatValue((String) obj.get(16), 0) : ((Float) obj.get(16)).floatValue();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {
		return mCount + " " + mEquipped + " " + mName + " " + mMetal + " " + mType + " " + mHanded + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						mStrength + " " + mDexterity + " " + mEncumbrance + " " + mWeaponLength + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						mAttackSpeed + " " + mWeaponBreak + " " + mHitBonus + " " + mDPHitBonus + " " + mDamageOneHanded + " " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						mDamageTwoHanded + " " + mCost; //$NON-NLS-1$
	}

	@Override
	public WeaponRecord clone() {
		return new WeaponRecord(mCount, mEquipped, mName, mMetal, mType, mHanded, mStrength, mDexterity, //
						mEncumbrance, mWeaponLength, mAttackSpeed, mWeaponBreak, mHitBonus, mDPHitBonus, mDamageOneHanded, //
						mDamageTwoHanded, mCost);
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

		WeaponRecord record = (WeaponRecord) obj;
		if (mName.equals(record.mName) && //
						mMetal == record.mMetal && //
						mType == record.mType && //
						mHanded == record.mHanded && //
						mStrength == record.mStrength && //
						mDexterity == record.mDexterity && //
						// mEncumbrance == record.mEncumbrance && //
						mWeaponLength == record.mWeaponLength && //
						mAttackSpeed == record.mAttackSpeed && //
						mWeaponBreak == record.mWeaponBreak && //
						mHitBonus == record.mHitBonus && //
						mDPHitBonus == record.mDPHitBonus && //
						mDamageOneHanded == record.mDamageOneHanded && //
						mDamageTwoHanded == record.mDamageTwoHanded) { // && //
			// mCost == record.mCost) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (mName == null ? 0 : mName.hashCode());
		result = prime * result + (mMetal ^ mMetal >>> 32);
		result = prime * result + (mType ^ mType >>> 32);
		result = prime * result + (mHanded ^ mHanded >>> 32);
		result = prime * result + (mStrength ^ mStrength >>> 32);
		result = prime * result + (mDexterity ^ mDexterity >>> 32);
		result = prime * result + (mWeaponLength ^ mWeaponLength >>> 32);
		result = prime * result + (mAttackSpeed ^ mAttackSpeed >>> 32);
		result = prime * result + (mWeaponBreak ^ mWeaponBreak >>> 32);
		result = prime * result + (mHitBonus ^ mHitBonus >>> 32);
		result = prime * result + (mDPHitBonus ^ mDPHitBonus >>> 32);
		result = prime * result + (mDamageOneHanded ^ mDamageOneHanded >>> 32);
		result = prime * result + (mDamageTwoHanded ^ mDamageTwoHanded >>> 32);

		return result;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public void update(WeaponRecord record) {

		mCount = record.getCount();
		mEquipped = record.isEquipped();
		mName = record.getName();
		mMetal = record.getMetalID();
		mType = record.getType();
		mHanded = record.getHanded();
		mStrength = record.getStrength();
		mDexterity = record.getDexterity();
		mEncumbrance = record.getEncumbrance();
		mWeaponLength = record.getWeaponLength();
		mAttackSpeed = record.getAttackSpeed();
		mWeaponBreak = record.getWeaponBreak();
		mHitBonus = record.getHitBonus();
		mDPHitBonus = record.getDPHitBonus();
		mDamageOneHanded = record.getDamageOneHanded();
		mDamageTwoHanded = record.getDamageTwoHanded();
		mCost = record.getCost();

	}

	@Override
	public Object[] getRecord() {
		return new Object[] { mCount > 0 ? Integer.valueOf(mCount) : Integer.valueOf(0), Boolean.valueOf(mEquipped), //
						mName, getMetalName(mMetal), mTypeLabel[mType], mHandedLabel[mHanded], //
						Integer.valueOf(mStrength), Integer.valueOf(mDexterity), Float.valueOf(mEncumbrance), //
						Integer.valueOf(mWeaponLength), Integer.valueOf(mAttackSpeed), Integer.valueOf(mWeaponBreak), //
						Integer.valueOf(mHitBonus), Integer.valueOf(mDPHitBonus), mDamageOneHanded > 0 ? Integer.valueOf(mDamageOneHanded) : Integer.valueOf(0), //
						mDamageTwoHanded > 0 ? Integer.valueOf(mDamageTwoHanded) : Integer.valueOf(0), Float.valueOf(mCost) };
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
				yield mTypeLabel[mType];
			case 5:
				yield mHandedLabel[mHanded];
			case 6:
				yield Integer.valueOf(mStrength);
			case 7:
				yield Integer.valueOf(mDexterity);
			case 8:
				yield Float.valueOf(mEncumbrance);
			case 9:
				yield Integer.valueOf(mWeaponLength);
			case 10:
				yield Integer.valueOf(mAttackSpeed);
			case 11:
				yield Integer.valueOf(mWeaponBreak);
			case 12:
				yield Integer.valueOf(mHitBonus);
			case 13:
				yield Integer.valueOf(mDPHitBonus);
			case 14:
				yield mDamageOneHanded > 0 ? Integer.valueOf(mDamageOneHanded) : " "; //$NON-NLS-1$
			case 15:
				yield mDamageTwoHanded > 0 ? Integer.valueOf(mDamageTwoHanded) : " "; //$NON-NLS-1$
			case 16:
				yield Float.valueOf(mCost);
			default:
				throw new IllegalArgumentException(UNEXPECTED_VALUE + id);
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

	/** @return The type. */
	public int getType() {
		return mType;
	}

	/** @return The handed. */
	public int getHanded() {
		return mHanded;
	}

	/** @return The strength. */
	public int getStrength() {
		return mStrength;
	}

	/** @return The dexterity. */
	public int getDexterity() {
		return mDexterity;
	}

	/** @return The encumbrance. */
	public float getEncumbrance() {
		return mEncumbrance;
	}

	/** @return The weaponLength. */
	public int getWeaponLength() {
		return mWeaponLength;
	}

	/** @return The attackSpeed. */
	public int getAttackSpeed() {
		return mAttackSpeed;
	}

	/** @return The weaponBreak. */
	public int getWeaponBreak() {
		return mWeaponBreak;
	}

	/** @return The hitBonus. */
	public int getHitBonus() {
		return mHitBonus;
	}

	/** @return The hitBonus. */
	public int getDPHitBonus() {
		return mDPHitBonus;
	}

	public void setDPHitBonus(int bonus) {
		mDPHitBonus = bonus;
	}

	/** @return The damageOneHanded. */
	public int getDamageOneHanded() {
		return mDamageOneHanded;
	}

	/** @return The damageTwoHanded. */
	public int getDamageTwoHanded() {
		return mDamageTwoHanded;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	public String toRecordFile() {
		return mCount + ", " + mEquipped + ", \"" + mName + "\", " + mMetal + ", " + mType + ", " + mHanded + ", " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						mStrength + ", " + mDexterity + ", " + mEncumbrance + ", " + mWeaponLength + ", " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						mAttackSpeed + ", " + mWeaponBreak + ", " + mHitBonus + ", " + mDPHitBonus + ", " + mDamageOneHanded + ", " + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						mDamageTwoHanded + ", " + mCost; //$NON-NLS-1$
	}

	@Override
	public int compareTo(WeaponRecord o) {
		if (this == o) {
			return 0;
		}
		if (equals(o)) {
			return 0;
		}
		if (mType < o.mType) {
			return -1;
		}
		if (mType > o.mType) {
			return 1;
		}
		return mName.compareTo(o.mName);

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
