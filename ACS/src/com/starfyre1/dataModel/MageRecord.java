/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.dataset.common.BaseClass;

public class MageRecord extends ClassesRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	private String	mType;			// null, priest, Mage
	//	private String	mName;
	//	private int		mArmor;			// 0 = none, 1 = light, 2 = medium, 3 = heavy
	//	private int[]	mMinimumStats;	// null means no minimum stats

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MageRecord(BaseClass baseClass, String group, String name, int armor, int[] stats) {
		super(baseClass, group, name, armor, stats);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
