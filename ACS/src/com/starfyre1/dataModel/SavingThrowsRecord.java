/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.LevelListener;
import com.starfyre1.startup.ACS;

public class SavingThrowsRecord implements LevelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	// DW add level info to tooltip for real level (+4%/lvl-1)
	public static final String	AGILITY_TOOLTIP		= "(DEX * 3) + 10 + (lvl - 1) * 4";						//$NON-NLS-1$
	public static final String	BLEEDING_TOOLTIP	= "STR + (CON * 2) + WP + (lvl - 1) * 4 + Class Bonus";	//$NON-NLS-1$
	public static final String	MAGIC_TOOLTIP		= "(INT * 2) + WIS + (lvl - 1) * 4 + Class Bonus";		//$NON-NLS-1$
	public static final String	POISON_TOOLTIP		= "(CON * 3) + 10 + (lvl - 1) * 4 + Class Bonus";		//$NON-NLS-1$
	public static final String	SHOCK_TOOLTIP		= "(CON * 2) + WP + 30 + (lvl - 1) * 4 + Class Bonus";	//$NON-NLS-1$
	public static final String	STRESS_TOOLTIP		= "(WP * 3) + (lvl - 1) * 4 + Class Bonus";				//$NON-NLS-1$
	public static final String	UNCONSCIOUS_TOOLTIP	= "STR + CON + (WP * 2) + (lvl - 1) * 4 + Class Bonus";	//$NON-NLS-1$
	public static final String	SURPRISE_TOOLTIP	= "INT + DEX + WP + 35 + Level + Class Bonus";			//$NON-NLS-1$
	public static final String	BELIEF_TOOLTIP		= "INT + WIS + 35 + (lvl * 5) + Class Bonus";			//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mCharacterSheet;

	private int					mAgility			= 0;
	private int					mBleeding			= 0;
	private int					mMagic				= 0;
	private int					mPoison				= 0;
	private int					mShock				= 0;
	private int					mStress				= 0;
	private int					mUnconscious		= 0;
	private int					mSurprise			= 0;
	private int					mBelief				= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public SavingThrowsRecord(CharacterSheet characterSheet, boolean generate) {
		mCharacterSheet = characterSheet;

		if (generate) {
			updateValues();
		}
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void updateRecord() {
		updateValues();
	}

	private void updateValues() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();
		BaseClass classInfo = headerRecord.getCharacterClass();

		int level = headerRecord.getLevel();
		int levelBonus = (level - 1) * 4;

		mAgility = stats.getModifiedStat(AttributesRecord.DEX) * 3 + 10 + levelBonus;
		mBleeding = stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.CON) * 2 + stats.getModifiedStat(AttributesRecord.WP) + levelBonus + classInfo.getBleeding();
		mMagic = stats.getModifiedStat(AttributesRecord.INT) * 2 + stats.getModifiedStat(AttributesRecord.WIS) + levelBonus + classInfo.getMagic();
		mPoison = stats.getModifiedStat(AttributesRecord.CON) * 3 + 10 + levelBonus + classInfo.getPoison();
		mShock = stats.getModifiedStat(AttributesRecord.CON) * 2 + stats.getModifiedStat(AttributesRecord.WP) + 30 + levelBonus + classInfo.getShock();
		mStress = stats.getModifiedStat(AttributesRecord.WP) * 3 + levelBonus + classInfo.getStress();
		mUnconscious = stats.getModifiedStat(AttributesRecord.STR) + stats.getModifiedStat(AttributesRecord.CON) + stats.getModifiedStat(AttributesRecord.WP) * 2 + levelBonus + classInfo.getUnconscious();
		mSurprise = stats.getModifiedStat(AttributesRecord.INT) + stats.getModifiedStat(AttributesRecord.DEX) + stats.getModifiedStat(AttributesRecord.WP) + 35 + level + classInfo.getSurprise();
		mBelief = stats.getModifiedStat(AttributesRecord.INT) + stats.getModifiedStat(AttributesRecord.WIS) + 35 + level * 5 + classInfo.getBelief();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The agility. */
	public int getAgility() {
		return mAgility;
	}

	/** @param agility The value to set for agility. */
	public void setAgility(int agility) {
		mAgility = agility;
	}

	/** @return The bleeding. */
	public int getBleeding() {
		return mBleeding;
	}

	/** @param bleeding The value to set for bleeding. */
	public void setBleeding(int bleeding) {
		mBleeding = bleeding;
	}

	/** @return The magic. */
	public int getMagic() {
		return mMagic;
	}

	/** @param magic The value to set for magic. */
	public void setMagic(int magic) {
		mMagic = magic;
	}

	/** @return The poison. */
	public int getPoison() {
		return mPoison;
	}

	/** @param poison The value to set for poison. */
	public void setPoison(int poison) {
		mPoison = poison;
	}

	/** @return The shock. */
	public int getShock() {
		return mShock;
	}

	/** @param shock The value to set for shock. */
	public void setShock(int shock) {
		mShock = shock;
	}

	/** @return The stress. */
	public int getStress() {
		return mStress;
	}

	/** @param stress The value to set for stress. */
	public void setStress(int stress) {
		mStress = stress;
	}

	/** @return The unconscious. */
	public int getUnconscious() {
		return mUnconscious;
	}

	/** @param unconscious The value to set for unconscious. */
	public void setUnconcious(int unconscious) {
		mUnconscious = unconscious;
	}

	/** @return The surprise. */
	public int getSurprise() {
		return mSurprise;
	}

	/** @param surprise The value to set for surprise. */
	public void setSurprise(int surprise) {
		mSurprise = surprise;
	}

	/** @return The belief. */
	public int getBelief() {
		return mBelief;
	}

	/** @param belief The value to set for belief. */
	public void setBelief(int belief) {
		mBelief = belief;
	}

	/** @return The agility. */
	public String getAgilityToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		int level = mCharacterSheet.getHeaderRecord().getLevel();

		if (ACS.showCalculations()) {
			sb.append(AGILITY_TOOLTIP);
			sb.append("<br>(" + attr.getModifiedStat(AttributesRecord.DEX) + " * 3) + 10 + (" + (level - 1) + " * 4) = " + getAgility() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		sb.append("(" + attr.getModifiedStat(AttributesRecord.DEX) * 3 + ") + 10 + (" + (level - 1) * 4 + ") = " + getAgility() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The bleeding. */
	public String getBleedingToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getBleeding();

		if (ACS.showCalculations()) {
			sb.append(BLEEDING_TOOLTIP);
			sb.append("<br>" + attr.getModifiedStat(AttributesRecord.STR) + " + (" + attr.getModifiedStat(AttributesRecord.CON) + " * 2) + " + attr.getModifiedStat(AttributesRecord.WP) + " + (" + (level - 1) + " * 4) + " + classBonus + " = " + getBleeding() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		}
		sb.append(attr.getModifiedStat(AttributesRecord.STR) + " + (" + attr.getModifiedStat(AttributesRecord.CON) * 2 + ") + " + attr.getModifiedStat(AttributesRecord.WP) + " + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getBleeding() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The magic. */
	public String getMagicToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getMagic();

		if (ACS.showCalculations()) {
			sb.append(MAGIC_TOOLTIP);
			sb.append("<br>(" + attr.getModifiedStat(AttributesRecord.INT) + " * 2) + " + attr.getModifiedStat(AttributesRecord.WIS) + " + (" + (level - 1) + " * 4) + " + classBonus + " = " + getMagic() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
		sb.append("(" + attr.getModifiedStat(AttributesRecord.INT) * 2 + ") + " + attr.getModifiedStat(AttributesRecord.WIS) + " + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getMagic() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The poison. */
	public String getPoisonToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getPoison();

		if (ACS.showCalculations()) {
			sb.append(POISON_TOOLTIP);
			sb.append("<br>(" + attr.getModifiedStat(AttributesRecord.CON) + " * 3) + 10 + (" + (level - 1) + " * 4) + " + classBonus + " = " + getPoison() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		sb.append("(" + attr.getModifiedStat(AttributesRecord.CON) * 3 + ") + 10 + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getPoison() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The shock. */
	public String getShockToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getShock();

		if (ACS.showCalculations()) {
			sb.append(SHOCK_TOOLTIP);
			sb.append("<br>(" + attr.getModifiedStat(AttributesRecord.CON) + " * 2) + " + attr.getModifiedStat(AttributesRecord.WP) + " + 30 + (" + (level - 1) + " * 4) + " + classBonus + " = " + getShock() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
		sb.append("(" + attr.getModifiedStat(AttributesRecord.CON) * 2 + ") + " + attr.getModifiedStat(AttributesRecord.WP) + " + 30 + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getShock() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The stress. */
	public String getStressToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getStress();

		if (ACS.showCalculations()) {
			sb.append(STRESS_TOOLTIP);
			sb.append("<br>(" + attr.getModifiedStat(AttributesRecord.WP) + " * 3) + (" + (level - 1) + " * 4) + " + classBonus + " = " + getStress() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		sb.append("(" + attr.getModifiedStat(AttributesRecord.WP) * 3 + ") + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getStress() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The unconscious. */
	public String getUnconsciousToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getUnconscious();

		if (ACS.showCalculations()) {
			sb.append(UNCONSCIOUS_TOOLTIP);
			sb.append("<br>" + attr.getModifiedStat(AttributesRecord.STR) + " + " + attr.getModifiedStat(AttributesRecord.CON) + " + (" + attr.getModifiedStat(AttributesRecord.WP) + " * 2) + (" + (level - 1) + " * 4) + " + classBonus + " = " + getUnconscious() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		}
		sb.append(attr.getModifiedStat(AttributesRecord.STR) + " + " + attr.getModifiedStat(AttributesRecord.CON) + " + (" + attr.getModifiedStat(AttributesRecord.WP) * 2 + ") + (" + (level - 1) * 4 + ") + " + classBonus + " = " + getUnconscious() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The surprise. */
	public String getSurpriseToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getSurprise();

		if (ACS.showCalculations()) {
			sb.append(SURPRISE_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(attr.getModifiedStat(AttributesRecord.INT) + " + " + attr.getModifiedStat(AttributesRecord.DEX) + " + " + attr.getModifiedStat(AttributesRecord.WP) + " + 35 + " + level + " + " + classBonus + " = " + getSurprise() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The belief. */
	public String getBeliefToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = headerRecord.getLevel();
		int classBonus = headerRecord.getCharacterClass().getBelief();

		if (ACS.showCalculations()) {
			sb.append(BELIEF_TOOLTIP);
			sb.append("<br>" + attr.getModifiedStat(AttributesRecord.INT) + " + " + attr.getModifiedStat(AttributesRecord.WIS) + " + 35 + (" + level + " * 5) + " + classBonus + " = " + getBelief() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
		sb.append(attr.getModifiedStat(AttributesRecord.INT) + " + " + attr.getModifiedStat(AttributesRecord.WIS) + " + 35 + (" + level * 5 + ") + " + classBonus + " = " + getBelief() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
