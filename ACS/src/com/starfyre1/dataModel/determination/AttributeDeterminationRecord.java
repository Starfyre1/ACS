/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.determination.AttributesTab;
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

public class AttributeDeterminationRecord extends DeterminationRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTION_START_KEY			= "ATTRIBUTES_DETERMINATION_SECTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY			= "ATTRIBUTES_DETERMINATION_SECTION_END";	//$NON-NLS-1$

	private static final String	ATTRIBUTE_KEY					= "ATTRIBUTE_KEY";							//$NON-NLS-1$

	private static final int	MAX_STAT_VALUE					= 18;
	private static final int	MAX_NUMBER_OF_IMPROVEMENTS		= 3;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	int							mAttribute						= 0;

	private int					currentNumberOfImprovements[]	= new int[] { 0, 0, 0, 0, 0 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link AttributeDeterminationRecord}.
	 */
	public AttributeDeterminationRecord() {
		/**
		 * Creates a new {@link AttributeDeterminationRecord}.
		 */
	}

	public AttributeDeterminationRecord(int attrib, int dpPerWeek, int cost, String startDate, String lastUpdate) {
		mAttribute = attrib;
		mDPPerWeek = dpPerWeek;
		mDPCost = cost;
		mMaintenance = true;
		mStartDate = startDate;
		setLastUpdate(lastUpdate);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("Attribute: " + AttributesTab.ATTRIBUTE_NAMES[mAttribute]); //$NON-NLS-1$
		sb.append("\nDP Per Week: " + mDPPerWeek); //$NON-NLS-1$
		sb.append("\nDP Total Spent: " + mDPTotalSpent + " / " + mDPCost); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\nMaintenance cost: " + (mMaintenance ? 1 : 0)); //$NON-NLS-1$
		sb.append("\nSuccessful: " + mSuccessful); //$NON-NLS-1$
		sb.append("\nStart Date: " + mStartDate); //$NON-NLS-1$
		sb.append("\nLast Update: " + mLastUpdate); //$NON-NLS-1$
		sb.append("\nEnd Date: " + (mEndDate.isBlank() ? "Not Complete" : mEndDate)); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("\n"); //$NON-NLS-1$

		return sb.toString();
	}

	@Override
	public boolean successRoll() {
		// DW This should move to the AttributesTab
		// success roll
		// "1D20 >= stat - 1/2 level"

		CharacterSheet characterSheet = ACS.getInstance().getCharacterSheet();
		int target = characterSheet.getAttributesRecord().getModifiedStat(mAttribute) - characterSheet.getHeaderRecord().getLevel() / 2;
		int roll = 0;

		if (PreferenceStore.getInstance().isAppRollsDice()) {
			roll = TKDice.roll(20);
		} else {
			do {
				String result = JOptionPane.showInputDialog(characterSheet.getFrame(), "Enter 1D20 roll", "Roll for " + AttributesTab.ATTRIBUTE_NAMES[getAttribute()] + " Success (roll >= " + target + " )", JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				if (result != null) {
					roll = TKStringHelpers.getIntValue(result, 0);
				}
			} while (roll == 0);
		}
		return roll >= target;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public String getName() {
		return AttributesTab.ATTRIBUTE_NAMES[getAttribute()];
	}

	public boolean isStatMaxed(int which) {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getStat(which) >= MAX_STAT_VALUE || currentNumberOfImprovements[which] >= MAX_NUMBER_OF_IMPROVEMENTS;
	}

	/** @return The attribute. */
	public int getAttribute() {
		return mAttribute;
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

		br.write(TKStringHelpers.TAB + ATTRIBUTE_KEY + TKStringHelpers.SPACE + mAttribute + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_PER_WEEK_KEY + TKStringHelpers.SPACE + mDPPerWeek + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_TOTAL_SPENT_KEY + TKStringHelpers.SPACE + mDPTotalSpent + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DP_COST_KEY + TKStringHelpers.SPACE + mDPCost + System.lineSeparator());
		br.write(TKStringHelpers.TAB + MAINTENANCE_KEY + TKStringHelpers.SPACE + mMaintenance + System.lineSeparator());
		br.write(TKStringHelpers.TAB + SUCCESSFUL_KEY + TKStringHelpers.SPACE + mSuccessful + System.lineSeparator());
		br.write(TKStringHelpers.TAB + START_DATE_KEY + TKStringHelpers.SPACE + mStartDate + System.lineSeparator());
		br.write(TKStringHelpers.TAB + LAST_UPDATE_KEY + TKStringHelpers.SPACE + mLastUpdate + System.lineSeparator());
		br.write(TKStringHelpers.TAB + END_DATE_KEY + TKStringHelpers.SPACE + mEndDate + System.lineSeparator());

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
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
		} else if (MAINTENANCE_KEY.equals(key)) {
			mMaintenance = TKStringHelpers.getBoolValue(value, false);
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
