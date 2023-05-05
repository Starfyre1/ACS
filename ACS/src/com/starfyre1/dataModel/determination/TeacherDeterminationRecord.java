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
	public static final String	FILE_SECTION_START_KEY	= "TEACHER_DETERMINATION_SECTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY	= "TEACHER_DETERMINATION_SECTION_END";		//$NON-NLS-1$

	private static final String	TEACHER_ID_KEY			= "TEACHER_ID_KEY";							//$NON-NLS-1$
	private static final String	TEACHER_KEY				= "TEACHER_KEY";							//$NON-NLS-1$
	private static final String	EXPERTISE_KEY			= "EXPERTISE_KEY";							//$NON-NLS-1$
	private static final String	COST_KEY				= "COST_KEY";								//$NON-NLS-1$

	public static int			ID_NUMBER				= 0;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	int							mID						= 0;
	String						mTeacher				= TKStringHelpers.EMPTY_STRING;
	String						mExpertise				= TKStringHelpers.EMPTY_STRING;
	float						mCost					= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link TeacherDeterminationRecord}.
	 */
	public TeacherDeterminationRecord() {

	}

	/**
	 * Creates a new {@link TeacherDeterminationRecord}.
	 */
	public TeacherDeterminationRecord(int id, String teacher, String expertise, float cost, int bonus) {
		mID = id;
		mTeacher = teacher;
		mExpertise = expertise;
		mCost = cost;
		mBonus = bonus;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("ID Number: " + mID); //$NON-NLS-1$
		sb.append("\nTeacher: " + mTeacher); //$NON-NLS-1$
		sb.append("\nExpertise: " + mExpertise); //$NON-NLS-1$
		sb.append("\nBonus: " + mBonus); //$NON-NLS-1$
		sb.append("\nCost: " + mCost); //$NON-NLS-1$
		sb.append("\n"); //$NON-NLS-1$

		return sb.toString();
	}

	@Override
	public boolean successRoll() {
		// there is no success roll for teachers
		return false;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static int getNextIdNumber() {
		return ++ID_NUMBER;
	}

	public String getExpertise() {
		return mExpertise;
	}

	/** @return The iD. */
	public int getID() {
		return mID;
	}

	/** @return The teacher. */
	public String getTeacher() {
		return mTeacher;
	}

	/** @return The cost. */
	public float getCost() {
		return mCost;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					while (tokenizer.hasMoreTokens()) {
						value += " " + tokenizer.nextToken(); //$NON-NLS-1$
					}
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
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());

		br.write(TKStringHelpers.TAB + TEACHER_ID_KEY + TKStringHelpers.SPACE + mID + System.lineSeparator());
		br.write(TKStringHelpers.TAB + TEACHER_KEY + TKStringHelpers.SPACE + mTeacher + System.lineSeparator());
		br.write(TKStringHelpers.TAB + EXPERTISE_KEY + TKStringHelpers.SPACE + mExpertise + System.lineSeparator());
		br.write(TKStringHelpers.TAB + COST_KEY + TKStringHelpers.SPACE + mCost + System.lineSeparator());
		br.write(TKStringHelpers.TAB + BONUS_KEY + TKStringHelpers.SPACE + mBonus + System.lineSeparator());

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (TEACHER_ID_KEY.equals(key)) {
			mID = Integer.parseInt(value);
			if (mID >= ID_NUMBER) {
				ID_NUMBER = mID + 1;
			}
		} else if (TEACHER_KEY.equals(key)) {
			mTeacher = value;
		} else if (EXPERTISE_KEY.equals(key)) {
			mExpertise = value;
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0);
		} else if (BONUS_KEY.equals(key)) {
			mBonus = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
