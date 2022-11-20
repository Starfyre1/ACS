/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;
import com.starfyre1.storage.PreferenceStore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class MagicSpellDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTION_START_KEY	= "MAGIC_SPELL_DETERMINATION_SECTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY	= "MAGIC_SPELL_DETERMINATION_SECTION_END";		//$NON-NLS-1$

	private static final String	SPELL_KEY				= "SPELL_KEY";									//$NON-NLS-1$
	private static final String	SCHOOL_KEY				= "SCHOOL_KEY";									//$NON-NLS-1$
	private static final String	COST_KEY				= "COST_KEY";									//$NON-NLS-1$
	private static final String	CHANCE_KEY				= "CHANCE_KEY";									//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	String						mSpell					= TKStringHelpers.EMPTY_STRING;
	String						mSchool					= TKStringHelpers.EMPTY_STRING;
	int							mChance					= 0;
	float						mCost					= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MagicSpellDeterminationRecord}.
	 */
	public MagicSpellDeterminationRecord() {

	}

	/**
	 * Creates a new {@link MagicSpellDeterminationRecord}.
	 */
	public MagicSpellDeterminationRecord(String spell, String school, float cost, int chance, int dpPerWeek, int dpCost, String startDate, String lastUpdate) {
		mSpell = spell;
		mSchool = school;
		mCost = cost;
		mChance = chance;
		mDPPerWeek = dpPerWeek;
		mDPCost = dpCost;
		mStartDate = startDate;
		setLastUpdate(lastUpdate);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("Spell: " + mSpell); //$NON-NLS-1$
		sb.append("\nSchool: " + mSchool); //$NON-NLS-1$
		sb.append("\nCost: " + mCost); //$NON-NLS-1$
		sb.append("\nDP Per Week: " + mDPPerWeek); //$NON-NLS-1$
		sb.append("\nDP Total Spent: " + mDPTotalSpent + " / " + mDPCost); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\nChance of Success: " + mChance); //$NON-NLS-1$
		sb.append("\nSuccessful: " + mSuccessful); //$NON-NLS-1$
		sb.append("\nStart Date: " + mStartDate); //$NON-NLS-1$
		sb.append("\nLast Update: " + mLastUpdate); //$NON-NLS-1$
		sb.append("\nEnd Date: " + (mEndDate.isBlank() ? "Not Complete" : mEndDate)); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\n"); //$NON-NLS-1$

		return sb.toString();
	}

	@Override
	public boolean successRoll() {
		int roll = 0;

		if (PreferenceStore.getInstance().isAppRollsDice()) {
			roll = TKDice.roll(100);
		} else {
			do {
				String result = JOptionPane.showInputDialog(ACS.getInstance().getCharacterSheet().getFrame(), "Enter 1D100 roll", "Roll for " + getSpell() + " Success (roll <= " + getChance() + ")", JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				System.out.println("result = " + result);
				if (result != null) {
					roll = TKStringHelpers.getIntValue(result, 0);
				}
			} while (roll == 0);
		}
		System.out.println(roll);
		return roll <= getChance();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public String getName() {
		return mSpell;
	}

	/** @return The spell. */
	public String getSpell() {
		return mSpell;
	}

	/** @return The school. */
	public String getSchool() {
		return mSchool;
	}

	/** @return The chance. */
	public float getChance() {
		return mChance;
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

		br.write(TKStringHelpers.TAB + SCHOOL_KEY + TKStringHelpers.SPACE + mSchool + System.lineSeparator());
		br.write(TKStringHelpers.TAB + SPELL_KEY + TKStringHelpers.SPACE + mSpell + System.lineSeparator());
		br.write(TKStringHelpers.TAB + COST_KEY + TKStringHelpers.SPACE + mCost + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_PER_WEEK_KEY + TKStringHelpers.SPACE + mDPPerWeek + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_TOTAL_SPENT_KEY + TKStringHelpers.SPACE + mDPTotalSpent + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_COST_KEY + TKStringHelpers.SPACE + mDPCost + System.lineSeparator());
		br.write(TKStringHelpers.TAB + CHANCE_KEY + TKStringHelpers.SPACE + mChance + System.lineSeparator());
		br.write(TKStringHelpers.TAB + SUCCESSFUL_KEY + TKStringHelpers.SPACE + mSuccessful + System.lineSeparator());
		br.write(TKStringHelpers.TAB + START_DATE_KEY + TKStringHelpers.SPACE + mStartDate + System.lineSeparator());
		br.write(TKStringHelpers.TAB + LAST_UPDATE_KEY + TKStringHelpers.SPACE + mLastUpdate + System.lineSeparator());
		br.write(TKStringHelpers.TAB + END_DATE_KEY + TKStringHelpers.SPACE + mEndDate + System.lineSeparator());

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (SCHOOL_KEY.equals(key)) {
			mSchool = value;
		} else if (SPELL_KEY.equals(key)) {
			mSpell = value;
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_PER_WEEK_KEY.equals(key)) {
			mDPPerWeek = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_TOTAL_SPENT_KEY.equals(key)) {
			mDPTotalSpent = TKStringHelpers.getIntValue(value, 0);
		} else if (DP_COST_KEY.equals(key)) {
			mDPCost = TKStringHelpers.getIntValue(value, 0);
		} else if (CHANCE_KEY.equals(key)) {
			mChance = TKStringHelpers.getIntValue(value, 0);
		} else if (SUCCESSFUL_KEY.equals(key)) {
			mSuccessful = TKStringHelpers.getBoolValue(value, false);
		} else if (START_DATE_KEY.equals(key)) {
			mStartDate = value;
		} else if (LAST_UPDATE_KEY.equals(key)) {
			setLastUpdate(value);
		} else if (END_DATE_KEY.equals(key)) {
			mEndDate = value;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
