/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.dataset.classes.common.BaseClass;

public class ClassesRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private BaseClass	mBaseClass;
	private String		mGroup;		// null, priest, Mage
	private String		mName;
	private int			mArmor;		// 0 = none, 1 = light, 2 = medium, 3 = heavy

	/*****************************************************************************
	 * Constructors
	 *
	 * @param name
	 * @param armor
	 ****************************************************************************/
	public ClassesRecord(BaseClass baseClass, String group, String name, int armor) {
		mBaseClass = baseClass;
		mGroup = group;
		mName = name;
		mArmor = armor;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/** @return The baseClass. */
	public BaseClass getBaseClass() {
		return mBaseClass;
	}

	/** @return The group. */
	public String getGroup() {
		return mGroup;
	}

	/** @return The name. */
	public String getName() {
		return mName;
	}

	/** @return The armor. */
	public int getArmor() {
		return mArmor;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
