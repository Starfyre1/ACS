/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.common;

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

	// Skills
	public abstract int getBandaging();

	public abstract int getHerbalLore();

	public abstract int getHunting();

	public abstract int getTracking();

	public abstract int getDetectMagic();

	public abstract int getDetectMetals();

	public abstract int getDetectSecretDoors();

	public abstract int getDetectTraps();

	public abstract int getAppraise();

	public abstract int getDepthSense();

	public abstract int getBerserk();

	public abstract int getConceal();

	public abstract int getStealth();

	public abstract int getHear();

	public abstract int getClimb();

	public abstract int getPerception();

	public abstract int getUnallocatedSkills();

	// CombatInfo
	public abstract int getDefenseBonus();

	public abstract int getHitBonus();

	public abstract int getMissileBonus();

	public abstract int getBowBonus();

	public abstract int getMovement();

	public abstract int getUnallocated();

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
