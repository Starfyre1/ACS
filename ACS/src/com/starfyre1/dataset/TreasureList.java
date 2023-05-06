/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.dataModel.TreasureRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreasureList implements Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String					FILE_SECTION_START_KEY	= "WEAPONS_SECTION_START";		//$NON-NLS-1$
	public static final String					FILE_SECTION_END_KEY	= "WEAPONS_SECTION_END";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int							mArraySize				= 50;
	private static ArrayList<TreasureRecord>	mRecords				= new ArrayList<>(mArraySize);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TreasureList() {

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void clearRecords() {
		mRecords = new ArrayList<>(mArraySize);
	}

	/** @return The armorList. */
	public ArrayList<TreasureRecord> getRecords() {
		return mRecords;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void setKeyValuePair(String key, Object value) {
		// DW not implemented yet - old treasure display
	}
}
