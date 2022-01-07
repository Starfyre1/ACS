/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class TeacherDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY	= "TEACHER_DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY	= "TEACHER_DETERMINATION_SECTTION_END";		//$NON-NLS-1$

	private static final String	TEACHER_KEY				= "TEACHER_KEY";							//$NON-NLS-1$
	private static final String	EXPERTISE_KEY			= "EXPERTISE_KEY";							//$NON-NLS-1$
	private static final String	COST_KEY				= "COST_KEY";								//$NON-NLS-1$
	private static final String	BONUS_KEY				= "BONUS_KEY";								//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	String						mTeacher				= TKStringHelpers.EMPTY_STRING;
	String						mExpertise				= TKStringHelpers.EMPTY_STRING;
	float						mCost					= 0;
	int							mBonus					= 0;
	String						mStartDate				= "";										//$NON-NLS-1$
	String						mCompletionDate			= "";										//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link TeacherDeterminationRecord}.
	 */
	public TeacherDeterminationRecord(String teacher, String expertise, float cost, int bonus, String startDate) {
		mTeacher = teacher;
		mExpertise = expertise;
		mCost = cost;
		mBonus = bonus;
		mStartDate = startDate;
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
	// DW may need to save out a serial number for the teacher id so it remains consistent (right now they are saved and restored in the same order)
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

	// DW may need to save out a serial number for the teacher id so it remains consistent (right now they are saved and restored in the same order)
	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());

		br.write(TEACHER_KEY + TKStringHelpers.SPACE + mTeacher + System.lineSeparator());
		br.write(EXPERTISE_KEY + TKStringHelpers.SPACE + mExpertise + System.lineSeparator());
		br.write(COST_KEY + TKStringHelpers.SPACE + mCost + System.lineSeparator());
		br.write(BONUS_KEY + TKStringHelpers.SPACE + mBonus + System.lineSeparator());

		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (TEACHER_KEY.equals(key)) {
			mTeacher = value;
		} else if (EXPERTISE_KEY.equals(key)) {
			mExpertise = value;
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0);
		} else if (BONUS_KEY.equals(key)) {
			mBonus = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}
}
