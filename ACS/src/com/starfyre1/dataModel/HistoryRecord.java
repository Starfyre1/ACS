/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

public class HistoryRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String	mWorldDate;
	private String	mCampaignDate;
	private int		mNewValue;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public HistoryRecord(String wDate, String cDate, int newValue) {
		mWorldDate = wDate;
		mCampaignDate = cDate;
		mNewValue = newValue;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The date. */
	public String getWorldDate() {
		return mWorldDate;
	}

	/** @return The date. */
	public String getCampaignDate() {
		return mCampaignDate;
	}

	/** @return The newValue. */
	public int getNewValue() {
		return mNewValue;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
