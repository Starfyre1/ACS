/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicItemList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTTION_START_KEY	= "MAGIC_ITEMS_SECTTION_START";	//$NON-NLS-1$
	public static final String			FILE_SECTTION_END_KEY	= "MAGIC_ITEMS_SECTTION_END";	//$NON-NLS-1$

	private static final String			COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String			EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String			NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String			CHARGES_KEY				= "CHARGES_KEY";				//$NON-NLS-1$
	private static final String			COST_KEY				= "COST_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static MagicItemRecord[]	mMagicItemMasterList;
	private ArrayList<MagicItemRecord>	mRecords				= new ArrayList<>(32);

	private int							mCount;
	private boolean						mEquipped;
	private String						mName;
	private int							mCharges;
	private float						mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MagicItemList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public float addMagicItems(ArrayList<MagicItemRecord> items, boolean calculateCost) {
		float cost = 0f;
		for (MagicItemRecord record : items) {
			boolean completed = false;
			// DW Think about stacking and unstacking like items
			for (MagicItemRecord owned : mRecords) {
				if (owned.getName().equals(record.getName())) {
					owned.setCount(owned.getCount() + record.getCount());
					completed = true;
				}
			}
			if (!completed) {
				mRecords.add(record);
			}
			if (calculateCost) {
				cost += record.getCost() * record.getCount();
			}
		}
		return cost;
	}

	/**
	 * @param soldItems
	 * @param calculateCost
	 * @return cost
	 */
	public float removeMagicItems(ArrayList<MagicItemRecord> soldItems, boolean calculateCost) {
		float cost = 0f;
		for (MagicItemRecord record : soldItems) {
			boolean completed = false;
			for (MagicItemRecord owned : mRecords) {
				// DW Think about stacking and unstacking like items
				if (owned.getName().equals(record.getName())) {
					owned.setCount(owned.getCount() - record.getCount());
					if (owned.getCount() > 0) {
						completed = true;
					}
				}
			}
			if (!completed) {
				mRecords.remove(record);
			}
			if (calculateCost) {
				cost += record.getCost() * record.getCount();
			}
		}
		return cost;
	}

	public void clearRecords() {
		mRecords = new ArrayList<>(32);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static MagicItemRecord getMagicItemMasterList(int which) {
		return mMagicItemMasterList[which];
	}

	public ArrayList<MagicItemRecord> getRecords() {
		return mRecords;
	}

	public static Object[] getMagicItemsMasterList() {
		if (mMagicItemMasterList == null) {
			mMagicItemMasterList = new MagicItemRecord[2];

			File in = new File("./src/com/starfyre1/dataset/rawData/MagicItem.txt"); //$NON-NLS-1$
			try {
				BufferedReader brIn = new BufferedReader(new FileReader(in));
				int count = 0;
				for (String line; (line = brIn.readLine()) != null;) {
					line = line.trim();

					if (line.startsWith("//") || line.isBlank()) { //$NON-NLS-1$
						continue;
					}

					String[] splitLine = line.split(", "); //$NON-NLS-1$
					//					System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					if (splitLine.length > 5) {
						System.err.println(splitLine[2]);
					}
					MagicItemRecord record = new MagicItemRecord(TKStringHelpers.getIntValue(splitLine[0], 0), TKStringHelpers.getBoolValue(splitLine[1], false), splitLine[2], TKStringHelpers.getIntValue(splitLine[3], 0), TKStringHelpers.getFloatValue(splitLine[4], 0f));
					mMagicItemMasterList[count++] = record;
				}

			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
		return mMagicItemMasterList;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, ", ", false); //$NON-NLS-1$
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
		for (MagicItemRecord record : mRecords) {
			br.write(COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(CHARGES_KEY + TKStringHelpers.SPACE + record.getCharges() + System.lineSeparator());
			br.write(COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
		}
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (COUNT_KEY.equals(key)) {
			mCount = TKStringHelpers.getIntValue(value, 0);
		} else if (EQUIPPED_KEY.equals(key)) {
			mEquipped = TKStringHelpers.getBoolValue(value, false);
		} else if (NAME_KEY.equals(key)) {
			mName = value;
		} else if (CHARGES_KEY.equals(key)) {
			mCharges = TKStringHelpers.getIntValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0f);
			mRecords.add(new MagicItemRecord(mCount, mEquipped, mName, mCharges, mCost));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
