/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class LanguageDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "LANGUAGE_DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "LANGUAGE_DETERMINATION_SECTTION_END";	//$NON-NLS-1$

	private static final String	LANGUAGE_KEY			= "LANGUAGE_KEY";							//$NON-NLS-1$
	private static final String	SOURCE_KEY				= "SOURCE_KEY";								//$NON-NLS-1$
	private static final String	DP_PER_WEEK_KEY			= "DP_PER_WEEK_KEY";						//$NON-NLS-1$
	private static final String	DP_TOTAL_SPENT_KEY		= "DP_TOTAL_SPENT_KEY";						//$NON-NLS-1$
	private static final String	DP_COST_KEY				= "DP_COST_KEY";							//$NON-NLS-1$
	private static final String	MAINTAINENCE_KEY		= "MAINTAINENCE_KEY";						//$NON-NLS-1$
	private static final String	SUCCESSFUL_KEY			= "SUCCESSFUL_KEY";							//$NON-NLS-1$
	private static final String	START_DATE_KEY			= "START_DATE_KEY";							//$NON-NLS-1$
	private static final String	COMPLETION_DATE_KEY		= "COMPLETION_DATE_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	String						mLanguage				= TKStringHelpers.EMPTY_STRING;
	String						mSource					= TKStringHelpers.EMPTY_STRING;
	int							mDPPerWeek				= 0;
	int							mDPTotalSpent			= 0;
	int							mDPCost					= 0;
	boolean						mMaintainence			= false;
	boolean						mSuccessful				= false;
	String						mStartDate				= "";										//$NON-NLS-1$
	String						mCompletionDate			= "";										//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link LanguageDeterminationRecord}.
	 */
	public LanguageDeterminationRecord(String language, String source, int dpPerWeek, int cost, String startDate) {
		mLanguage = language;
		mSource = source;
		mDPPerWeek = dpPerWeek;
		mDPCost = cost;
		mStartDate = startDate;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("Language: " + mLanguage); //$NON-NLS-1$
		sb.append("\nSource: " + mSource); //$NON-NLS-1$
		sb.append("\nDP Per Week: " + mDPPerWeek); //$NON-NLS-1$
		sb.append("\nDP Total Spent: " + mDPTotalSpent + " / " + mDPCost); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\nMaintainence cost: " + (mMaintainence ? 1 : 0)); //$NON-NLS-1$
		sb.append("\nSuccessful: " + mSuccessful); //$NON-NLS-1$
		sb.append("\nStart Date: " + mStartDate); //$NON-NLS-1$
		sb.append("\nCompletion Date: " + (mCompletionDate.isBlank() ? "Not Complete" : mCompletionDate)); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\n"); //$NON-NLS-1$

		return sb.toString();
	}

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

		br.write(LANGUAGE_KEY + TKStringHelpers.SPACE + mLanguage + System.lineSeparator());
		br.write(SOURCE_KEY + TKStringHelpers.SPACE + mSource + System.lineSeparator());
		br.write(DP_PER_WEEK_KEY + TKStringHelpers.SPACE + mDPPerWeek + System.lineSeparator());
		br.write(DP_TOTAL_SPENT_KEY + TKStringHelpers.SPACE + mDPTotalSpent + System.lineSeparator());
		br.write(DP_COST_KEY + TKStringHelpers.SPACE + mDPCost + System.lineSeparator());
		br.write(MAINTAINENCE_KEY + TKStringHelpers.SPACE + mMaintainence + System.lineSeparator());
		br.write(SUCCESSFUL_KEY + TKStringHelpers.SPACE + mSuccessful + System.lineSeparator());
		br.write(START_DATE_KEY + TKStringHelpers.SPACE + mStartDate + System.lineSeparator());
		br.write(COMPLETION_DATE_KEY + TKStringHelpers.SPACE + mCompletionDate + System.lineSeparator());

		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (LANGUAGE_KEY.equals(key)) {
			mLanguage = value;
		} else if (SOURCE_KEY.equals(key)) {
			mSource = value;
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
		} else if (START_DATE_KEY.equals(key)) {
			mStartDate = value;
		} else if (COMPLETION_DATE_KEY.equals(key)) {
			mCompletionDate = value;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}
}
