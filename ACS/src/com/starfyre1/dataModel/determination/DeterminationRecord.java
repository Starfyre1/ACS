/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

public class DeterminationRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String	BONUS_KEY			= "BONUS_KEY";				//$NON-NLS-1$
	static final String	DP_PER_WEEK_KEY		= "DP_PER_WEEK_KEY";		//$NON-NLS-1$
	static final String	DP_TOTAL_SPENT_KEY	= "DP_TOTAL_SPENT_KEY";		//$NON-NLS-1$
	static final String	DP_COST_KEY			= "DP_COST_KEY";			//$NON-NLS-1$
	static final String	MAINTENANCE_KEY		= "MAINTENANCE_KEY";		//$NON-NLS-1$
	static final String	SUCCESSFUL_KEY		= "SUCCESSFUL_KEY";			//$NON-NLS-1$
	static final String	START_DATE_KEY		= "START_DATE_KEY";			//$NON-NLS-1$
	static final String	LAST_UPDATE_KEY		= "LAST_UPDATE_KEY";		//$NON-NLS-1$
	static final String	END_DATE_KEY		= "COMPLETION_DATE_KEY";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	int					mBonus				= 0;
	int					mDPPerWeek			= 0;
	int					mDPTotalSpent		= 0;
	int					mDPCost				= 0;
	boolean				mMaintenance		= false;
	boolean				mSuccessful			= false;
	String				mStartDate			= "";						//$NON-NLS-1$
	String				mLastUpdate			= "";						//$NON-NLS-1$
	String				mEndDate			= "";						//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link DeterminationRecord}.
	 */
	public DeterminationRecord() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public boolean successRoll() {
		// success roll

		return false;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getName() {
		return null;
	}

	/** @return The bonus. */
	public int getBonus() {
		return mBonus;
	}

	/** @return The dPPerWeek. */
	public int getDPPerWeek() {
		return mDPPerWeek;
	}

	/** @param dPPerWeek The value to set for dPPerWeek. */
	public void setDPPerWeek(int dPPerWeek) {
		mDPPerWeek = dPPerWeek;
	}

	/** @return The dPTotalSpent. */
	public int getDPTotalSpent() {
		return mDPTotalSpent;
	}

	/** @param dPTotalSpent The value to set for dPTotalSpent. */
	public void setDPTotalSpent(int dPTotalSpent) {
		mDPTotalSpent = dPTotalSpent;
	}

	/** @return The dPCost. */
	public int getDPCost() {
		return mDPCost;
	}

	/** @return The maintenance. */
	public boolean hasMaintenance() {
		return mMaintenance;
	}

	/** @return The successful. */
	public boolean isSuccessful() {
		return mSuccessful;
	}

	/** @param successful The value to set for successful. */
	public void setSuccessful(boolean successful) {
		mSuccessful = successful;
	}

	/** @return The startDate. */
	public String getStartDate() {
		return mStartDate;
	}

	/** @return The lastUpdate. */
	public String getLastUpdate() {
		return mLastUpdate;
	}

	/** @param lastUpdate The value to set for lastUpdate. */
	public void setLastUpdate(String lastUpdate) {
		mLastUpdate = lastUpdate;
	}

	/** @return The endDate. */
	public String getEndDate() {
		return mEndDate;
	}

	/** @param endDate The value to set for endDate. */
	public void setEndDate(String endDate) {
		mEndDate = endDate;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
