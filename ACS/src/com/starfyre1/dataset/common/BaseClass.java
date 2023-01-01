/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.common;

import java.util.ArrayList;

public abstract class BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getFocus() {
		return "None"; //$NON-NLS-1$
	}

	public String getFocusToolTip() {
		return null;
	}

	public abstract int[] getMinimumStats();

	public abstract String[] getInnateDisplayList();

	// Regular Skills
	public abstract boolean[] getInnateSkills();

	public abstract int getAppraise();

	public abstract int getBandaging();

	public abstract int getDepthSense();

	public abstract int getDetectMagic();

	public abstract int getDetectMetals();

	public abstract int getDetectTraps();

	public abstract int getHerbalLore();

	public abstract int getHunting();

	public abstract int getPerception();

	public abstract int getDetectSecretDoors();

	public abstract int getTracking();

	public abstract int getBerserk();

	// Thieving Skills
	public abstract int getClimb();

	public abstract int getConceal();

	public abstract int getHear();

	public abstract int getStealth();

	public abstract int getUnallocatedSkills();

	// CombatInfo
	public abstract int getDefenseBonus();

	public abstract int getHitBonus();

	public abstract int getHitBonusMax();

	public abstract int getMissileBonus();

	public abstract int getMissileBonusMax();

	public abstract int getBowBonus();

	public abstract int getBowBonusMax();

	public abstract int getMovement();

	public abstract int getUnallocated();

	// Saving Throws
	public abstract int getBleeding();

	public abstract int getMagic();

	public abstract int getPoison();

	public abstract int getShock();

	public abstract int getStress();

	public abstract int getUnconscious();

	public abstract int getSurprise();

	public abstract int getBelief();

	// Defensive Information
	public abstract int getStamina();

	public abstract int getHitPoints();

	// Languages
	public abstract ArrayList<String> getLanguages();
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
