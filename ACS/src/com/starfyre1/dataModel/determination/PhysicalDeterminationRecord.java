/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.startup.ACS;

public class PhysicalDeterminationRecord extends DeterminationRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final int	MAX_STAT_VALUE					= 18;
	private static final int	MAX_NUMBER_OF_IMPROVEMENTS		= 3;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int					currentNumberOfImprovements[]	= new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public boolean isStatMaxed(int which) {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getStat(which) >= MAX_STAT_VALUE || currentNumberOfImprovements[which] >= MAX_NUMBER_OF_IMPROVEMENTS;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	public class PhysicalRecord {
		private int		mWhichRecord;
		private int		mAmountSpent;
		private boolean	mSuccessful;

		//		public physicalRecord(int which) {
		//			mWhichRecord;
		//			mAmountSpent;
		//			mSuccessful;
		//		}
	}

}
