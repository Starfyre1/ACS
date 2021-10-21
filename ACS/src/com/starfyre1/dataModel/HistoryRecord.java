/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import java.util.Date;

public class HistoryRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private Date	mDate;
	private int		mNewValue;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public HistoryRecord(Date date, int newValue) {
		mDate = date;
		mNewValue = newValue;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The date. */
	public Date getDate() {
		return mDate;
	}

	/** @return The date. */
	public long getRawDate() {
		return mDate.getTime();
	}

	/** @return The newValue. */
	public int getNewValue() {
		return mNewValue;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
