/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.dataModel.determination.DeterminationRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DeterminationList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String						FILE_SECTTION_START_KEY	= "DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String						FILE_SECTTION_END_KEY	= "DETERMINATION_SECTTION_END";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static ArrayList<DeterminationRecord>	mRecords				= new ArrayList<>(15);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, " ", false); //$NON-NLS-1$
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						String value = ""; //$NON-NLS-1$
						setKeyValuePair(key, value);
					} else {
						String value = tokenizer.nextToken();
						while (tokenizer.hasMoreTokens()) {
							value = value + " " + tokenizer.nextToken(); //$NON-NLS-1$
						}
						setKeyValuePair(key, value);
					}
				}
			}
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());
		for (DeterminationRecord record : mRecords) {
			//
			//			int[] type = record.getProtectionType();
			//			StringBuffer typeString = new StringBuffer("{ "); //$NON-NLS-1$
			//			for (int i = 0; i < type.length; i++) {
			//				typeString.append(type[i]);
			//				if (i < type.length - 1) {
			//					typeString.append(", "); //$NON-NLS-1$
			//				}
			//			}
			//			typeString.append(" }"); //$NON-NLS-1$
			//
			//			br.write(COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			//			br.write(COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
		}
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	// DW need to add magic info to Armor
	@Override
	public void setKeyValuePair(String key, Object obj) {
		//		String value = (String) obj;
		//		if (COUNT_KEY.equals(key)) {
		//			mCount = TKStringHelpers.getIntValue(value, 0);
		//		} else if (COST_KEY.equals(key)) {
		//			mCost = TKStringHelpers.getFloatValue(value, 0);
		//			mRecords.add(new DeterminationRecord(mCount,  mCost));
		//		} else {
		//			//DW9:: log this
		//			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		//		}
	}

}
