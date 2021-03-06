/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class MoneyRecord implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTION_START_KEY	= "MONEY_SECTION_START";		//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY	= "MONEY_SECTION_END";			//$NON-NLS-1$

	private static final String	GOLD_KEY				= "GOLD_KEY";					//$NON-NLS-1$
	private static final String	SILVER_KEY				= "SILVER_KEY";					//$NON-NLS-1$
	private static final String	COPPER_KEY				= "COPPER_KEY";					//$NON-NLS-1$
	private static final String	GEMS_KEY				= "GEMS_KEY";					//$NON-NLS-1$
	private static final String	JEWELRY_KEY				= "JEWELRY_KEY";				//$NON-NLS-1$
	private static final String	OTHER_KEY				= "OTHER_KEY";					//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mCharacterSheet;

	int							mGold					= 0;
	int							mSilver					= 0;
	int							mCopper					= 0;
	String						mGemsArea				= TKStringHelpers.EMPTY_STRING;
	String						mJewelryArea			= TKStringHelpers.EMPTY_STRING;
	String						mOtherArea				= TKStringHelpers.EMPTY_STRING;

	int							mGoldOld				= 0;
	int							mSilverOld				= 0;
	int							mCopperOld				= 0;
	String						mGemsAreaOld			= TKStringHelpers.EMPTY_STRING;
	String						mJewelryAreaOld			= TKStringHelpers.EMPTY_STRING;
	String						mOtherAreaOld			= TKStringHelpers.EMPTY_STRING;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Creates a record from the character sheet and generates values if requested
	public MoneyRecord(CharacterSheet sheet, boolean generate) {
		mCharacterSheet = sheet;

		if (generate) {
			mSilver = getStartingMoney();
			updateOldRecords();
		}

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	private void updateOldRecords() {
		mGoldOld = mGold;
		mSilverOld = mSilver;
		mCopperOld = mCopper;
		mGemsAreaOld = mGemsArea;
		mJewelryAreaOld = mJewelryArea;
		mOtherAreaOld = mOtherArea;

	}

	/**
	 * @param cost
	 */
	public void spend(float cost) {
		// DW update this to not convert money
		float balance = getAvailableMoney() - cost;
		mGold = (int) balance / 10;
		mSilver = (int) balance - mGold * 10;
		mCopper = (int) ((balance - (int) balance) * 10);
	}

	/**
	 * @param amountReceived
	 */
	public void receive(float amountReceived) {
		int gold = (int) amountReceived / 10;
		mGold += gold;
		mSilver += (int) amountReceived - gold * 10;
		mCopper += (int) ((amountReceived - (int) amountReceived) * 10);

	}

	/**
	 *
	 */
	public void clearRecords() {
		mGold = 0;
		mSilver = 0;
		mCopper = 0;
		mGemsArea = TKStringHelpers.EMPTY_STRING;
		mJewelryArea = TKStringHelpers.EMPTY_STRING;
		mOtherArea = TKStringHelpers.EMPTY_STRING;
		updateOldRecords();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public float getAvailableMoney() {
		return mGold * 10 + mSilver + (float) (mCopper / 10);
	}

	/** @return The gold. */
	public int getGold() {
		return mGold;
	}

	/** @param gold The value to set for gold. */
	public void setGold(int gold) {
		mGold = gold;
	}

	/** @return The silver. */
	public int getSilver() {
		return mSilver;
	}

	/** @param silver The value to set for silver. */
	public void setSilver(int silver) {
		mSilver = silver;
	}

	/** @return The copper. */
	public int getCopper() {
		return mCopper;
	}

	/** @param copper The value to set for copper. */
	public void setCopper(int copper) {
		mCopper = copper;
	}

	/** @return The gemsArea. */
	public String getGemsArea() {
		return mGemsArea;
	}

	/** @param gemsArea The value to set for gemsArea. */
	public void setGemsArea(String gemsArea) {
		mGemsArea = gemsArea;
	}

	/** @return The jewelryArea. */
	public String getJewelryArea() {
		return mJewelryArea;
	}

	/** @param jewelryArea The value to set for jewelryArea. */
	public void setJewelryArea(String jewelryArea) {
		mJewelryArea = jewelryArea;
	}

	/** @return The otherArea. */
	public String getOtherArea() {
		return mOtherArea;
	}

	/** @param otherArea The value to set for otherArea. */
	public void setOtherArea(String otherArea) {
		mOtherArea = otherArea;
	}

	public int getStartingMoney() {
		return mCharacterSheet.getPersonalInformationRecord().getSocialClass().updateCoins(TKDice.roll(3, 8) * 10);

	}

	/** @return The goldOld. */
	public int getGoldOld() {
		return mGoldOld;
	}

	/** @return The silverOld. */
	public int getSilverOld() {
		return mSilverOld;
	}

	/** @return The copperOld. */
	public int getCopperOld() {
		return mCopperOld;
	}

	/** @return The gemsAreaOld. */
	public String getGemsAreaOld() {
		return mGemsAreaOld;
	}

	/** @return The jewelryAreaOld. */
	public String getJewelryAreaOld() {
		return mJewelryAreaOld;
	}

	/** @return The otherAreaOld. */
	public String getOtherAreaOld() {
		return mOtherAreaOld;
	}

	public float getEncumbrance() {
		float encumbrance = 0f;

		encumbrance += mGold * 0.0625;
		encumbrance += mSilver * 0.03125;
		encumbrance += mCopper * 0.015625;
		//		encumbrance += mGemsArea * 0;
		//		encumbrance += mJewelryArea * 0;
		//		encumbrance += mOtherArea * 0;

		return encumbrance;
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
						value = value + " " + tokenizer.nextToken(); //$NON-NLS-1$
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

		br.write(TKStringHelpers.TAB + GOLD_KEY + TKStringHelpers.SPACE + mGold + System.lineSeparator());
		br.write(TKStringHelpers.TAB + SILVER_KEY + TKStringHelpers.SPACE + mSilver + System.lineSeparator());
		br.write(TKStringHelpers.TAB + COPPER_KEY + TKStringHelpers.SPACE + mCopper + System.lineSeparator());
		br.write(TKStringHelpers.TAB + GEMS_KEY + TKStringHelpers.SPACE + mGemsArea.replace("\n", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(TKStringHelpers.TAB + JEWELRY_KEY + TKStringHelpers.SPACE + mJewelryArea.replace("\n", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(TKStringHelpers.TAB + OTHER_KEY + TKStringHelpers.SPACE + mOtherArea.replace("\n", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
		updateOldRecords();
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		value = value.replace("~", "\n "); //$NON-NLS-1$ //$NON-NLS-2$
		if (GOLD_KEY.equals(key)) {
			mGold = TKStringHelpers.getIntValue(value, 0);
		} else if (SILVER_KEY.equals(key)) {
			mSilver = TKStringHelpers.getIntValue(value, 0);
		} else if (COPPER_KEY.equals(key)) {
			mCopper = TKStringHelpers.getIntValue(value, 0);
		} else if (GEMS_KEY.equals(key)) {
			mGemsArea = value;
		} else if (JEWELRY_KEY.equals(key)) {
			mJewelryArea = value;
		} else if (OTHER_KEY.equals(key)) {
			mOtherArea = value;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
