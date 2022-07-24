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
	static final String	MAINTAINENCE_KEY	= "MAINTAINENCE_KEY";		//$NON-NLS-1$
	static final String	SUCCESSFUL_KEY		= "SUCCESSFUL_KEY";			//$NON-NLS-1$
	static final String	START_DATE_KEY		= "START_DATE_KEY";			//$NON-NLS-1$
	static final String	LAST_UPDATE_KEY		= "LAST_UPDATE_KEY";		//$NON-NLS-1$
	static final String	COMPLETION_DATE_KEY	= "COMPLETION_DATE_KEY";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	int					mBonus				= 0;
	int					mDPPerWeek			= 0;
	int					mDPTotalSpent		= 0;
	int					mDPCost				= 0;
	boolean				mMaintainence		= false;
	boolean				mSuccessful			= false;
	String				mStartDate			= "";						//$NON-NLS-1$
	String				mLastUpdate			= "";						//$NON-NLS-1$
	String				mCompletionDate		= "";						//$NON-NLS-1$

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
	/** @return The bonus. */
	public int getBonus() {
		return mBonus;
	}

	/** @return The dPPerWeek. */
	public int getDPPerWeek() {
		return mDPPerWeek;
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

	/** @return The maintainence. */
	public boolean hasMaintainence() {
		return mMaintainence;
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

	/** @return The completionDate. */
	public String getCompletionDate() {
		return mCompletionDate;
	}

	/** @param completionDate The value to set for completionDate. */
	public void setCompletionDate(String completionDate) {
		mCompletionDate = completionDate;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
