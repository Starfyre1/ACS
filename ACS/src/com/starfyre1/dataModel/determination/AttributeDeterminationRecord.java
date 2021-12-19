/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class AttributeDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY			= "ATTRIBUTES_DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY			= "ATTRIBUTES_DETERMINATION_SECTTION_END";		//$NON-NLS-1$

	private static final String	ATTRIBUTE_KEY					= "ATTRIBUTE_KEY";								//$NON-NLS-1$
	private static final String	DP_PER_WEEK_KEY					= "DP_PER_WEEK_KEY";							//$NON-NLS-1$
	private static final String	DP_TOTAL_SPENT_KEY				= "DP_TOTAL_SPENT_KEY";							//$NON-NLS-1$
	private static final String	DP_COST_KEY						= "DP_COST_KEY";								//$NON-NLS-1$
	private static final String	MAINTAINENCE_KEY				= "MAINTAINENCE_KEY";							//$NON-NLS-1$
	private static final String	SUCCESSFUL_KEY					= "SUCCESSFUL_KEY";								//$NON-NLS-1$

	private static final int	MAX_STAT_VALUE					= 18;
	private static final int	MAX_NUMBER_OF_IMPROVEMENTS		= 3;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	int							mAttribute						= 0;
	int							mDPPerWeek						= 0;
	int							mDPTotalSpent					= 0;
	int							mDPCost							= 0;
	boolean						mMaintainence					= false;
	boolean						mSuccessful						= false;

	private int					currentNumberOfImprovements[]	= new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link AttributeDeterminationRecord}.
	 */
	public AttributeDeterminationRecord() {
	}

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
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
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
		br.write(ATTRIBUTE_KEY + TKStringHelpers.SPACE + mAttribute + System.lineSeparator());
		br.write(DP_PER_WEEK_KEY + TKStringHelpers.SPACE + mDPPerWeek + System.lineSeparator());
		br.write(DP_TOTAL_SPENT_KEY + TKStringHelpers.SPACE + mDPTotalSpent + System.lineSeparator());
		br.write(DP_COST_KEY + TKStringHelpers.SPACE + mDPCost + System.lineSeparator());
		br.write(MAINTAINENCE_KEY + TKStringHelpers.SPACE + mMaintainence + System.lineSeparator());
		br.write(SUCCESSFUL_KEY + TKStringHelpers.SPACE + mSuccessful + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (ATTRIBUTE_KEY.equals(key)) {
			mAttribute = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_PER_WEEK_KEY.equals(key)) {
			mDPPerWeek = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_TOTAL_SPENT_KEY.equals(key)) {
			mDPTotalSpent = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_COST_KEY.equals(key)) {
			mDPCost = TKStringHelpers.getIntValue(value, 0);
		} else if (MAINTAINENCE_KEY.equals(key)) {
			mMaintainence = TKStringHelpers.getBoolValue(value, false);
		} else if (SUCCESSFUL_KEY.equals(key)) {
			mSuccessful = TKStringHelpers.getBoolValue(value, false);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}
}
