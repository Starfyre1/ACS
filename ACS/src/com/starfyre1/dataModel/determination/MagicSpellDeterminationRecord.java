/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class MagicSpellDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "MAGIC_SPELL_DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "MAGIC_SPELL_DETERMINATION_SECTTION_END";		//$NON-NLS-1$

	private static final String	SPELL_KEY				= "SPELL_KEY";									//$NON-NLS-1$
	private static final String	SCHOOL_KEY				= "SCHOOL_KEY";									//$NON-NLS-1$
	private static final String	DP_PER_WEEK_KEY			= "DP_PER_WEEK_KEY";							//$NON-NLS-1$
	private static final String	DP_TOTAL_SPENT_KEY		= "DP_TOTAL_SPENT_KEY";							//$NON-NLS-1$
	private static final String	DP_COST_KEY				= "DP_COST_KEY";								//$NON-NLS-1$
	private static final String	SUCCESSFUL_KEY			= "SUCCESSFUL_KEY";								//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	String						mSpell					= TKStringHelpers.EMPTY_STRING;
	String						mSchool					= TKStringHelpers.EMPTY_STRING;
	int							mDPPerWeek				= 0;
	int							mDPTotalSpent			= 0;
	int							mDPCost					= 0;
	boolean						mSuccessful				= false;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MagicSpellDeterminationRecord}.
	 */
	public MagicSpellDeterminationRecord() {
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
		br.write(SPELL_KEY + TKStringHelpers.SPACE + mSpell + System.lineSeparator());
		br.write(SCHOOL_KEY + TKStringHelpers.SPACE + mSchool + System.lineSeparator());
		br.write(DP_PER_WEEK_KEY + TKStringHelpers.SPACE + mDPPerWeek + System.lineSeparator());
		br.write(DP_TOTAL_SPENT_KEY + TKStringHelpers.SPACE + mDPTotalSpent + System.lineSeparator());
		br.write(DP_COST_KEY + TKStringHelpers.SPACE + mDPCost + System.lineSeparator());
		br.write(SUCCESSFUL_KEY + TKStringHelpers.SPACE + mSuccessful + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (SPELL_KEY.equals(key)) {
			mSpell = value;
		} else if (SCHOOL_KEY.equals(key)) {
			mSchool = value;
		} else if (DP_PER_WEEK_KEY.equals(key)) {
			mDPPerWeek = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_TOTAL_SPENT_KEY.equals(key)) {
			mDPTotalSpent = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_COST_KEY.equals(key)) {
			mDPCost = TKStringHelpers.getIntValue(value, 0);
		} else if (SUCCESSFUL_KEY.equals(key)) {
			mSuccessful = TKStringHelpers.getBoolValue(value, false);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}
}
