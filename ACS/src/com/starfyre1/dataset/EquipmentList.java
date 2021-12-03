/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EquipmentList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTTION_START_KEY	= "EQUIPMENT_SECTTION_START";	//$NON-NLS-1$
	public static final String			FILE_SECTTION_END_KEY	= "EQUIPMENT_SECTTION_END";		//$NON-NLS-1$

	private static final String			COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String			EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String			NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String			ENCUMBRANCE_KEY			= "ENCUMBRANCE_KEY";			//$NON-NLS-1$
	private static final String			COST_KEY				= "COST_KEY";					//$NON-NLS-1$
	private static final String			NOTES_KEY				= "NOTES_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static EquipmentRecord[]	mEquipmentMasterList;
	private ArrayList<EquipmentRecord>	mRecords				= new ArrayList<>(132);

	private int							mCount;
	private boolean						mEquipped;
	private String						mName;
	private float						mEncumbrance;
	private float						mCost;
	private String						mNotes;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public EquipmentList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public float addEquipment(ArrayList<EquipmentRecord> boughtItems, boolean calculateCost) {
		float cost = 0f;
		for (EquipmentRecord record : boughtItems) {
			boolean completed = false;
			for (EquipmentRecord owned : mRecords) {
				// DW Think about stacking and unstacking like items
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
	public float removeEquipment(ArrayList<EquipmentRecord> soldItems, boolean calculateCost) {
		float cost = 0f;
		for (EquipmentRecord record : soldItems) {
			boolean completed = false;
			for (EquipmentRecord owned : mRecords) {
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
		mRecords = new ArrayList<>(132);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static EquipmentRecord getMasterEquipmentRecord(int which) {
		return mEquipmentMasterList[which];
	}

	public ArrayList<EquipmentRecord> getRecords() {
		return mRecords;
	}

	public static Object[] getEquipmentMasterList() {
		if (mEquipmentMasterList == null) {
			mEquipmentMasterList = new EquipmentRecord[144];

			File in = new File("./src/com/starfyre1/dataset/rawData/Equipment.txt"); //$NON-NLS-1$
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
					if (splitLine.length > 6) {
						System.err.println(splitLine[2]);
					}
					EquipmentRecord record = new EquipmentRecord(TKStringHelpers.getIntValue(splitLine[0], 0), //
									TKStringHelpers.getBoolValue(splitLine[1], false), //
									splitLine[2].replaceAll("\"", ""), // //$NON-NLS-1$ //$NON-NLS-2$
									TKStringHelpers.getFloatValue(splitLine[3], 0f), //
									TKStringHelpers.getFloatValue(splitLine[4], 0f), //
									splitLine[5].replaceAll("\"", "")); //$NON-NLS-1$ //$NON-NLS-2$
					mEquipmentMasterList[count++] = record;
				}

			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
		return mEquipmentMasterList;
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
		for (EquipmentRecord record : mRecords) {
			br.write(COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(ENCUMBRANCE_KEY + TKStringHelpers.SPACE + record.getEncumbrance() + System.lineSeparator());
			br.write(COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
			br.write(NOTES_KEY + TKStringHelpers.SPACE + record.getNotes() + System.lineSeparator());
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
		} else if (ENCUMBRANCE_KEY.equals(key)) {
			mEncumbrance = TKStringHelpers.getFloatValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0f);
		} else if (NOTES_KEY.equals(key)) {
			mNotes = value;
			mRecords.add(new EquipmentRecord(mCount, mEquipped, mName, mEncumbrance, mCost, mNotes));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
