/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;
import com.starfyre1.startup.SystemInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class MagicItemList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTION_START_KEY	= "MAGIC_ITEMS_SECTION_START";	//$NON-NLS-1$
	public static final String			FILE_SECTION_END_KEY	= "MAGIC_ITEMS_SECTION_END";	//$NON-NLS-1$

	private static final String			COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String			EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String			NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String			CHARGES_KEY				= "CHARGES_KEY";				//$NON-NLS-1$
	private static final String			COST_KEY				= "COST_KEY";					//$NON-NLS-1$
	private static final int			ARRAY_SIZE				= 32;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static MagicItemRecord[]	mMagicItemCombinedList	= null;
	private static MagicItemRecord[]	mMagicItemMasterList	= null;
	private static MagicItemRecord[]	mMagicItemUserList		= null;
	private ArrayList<MagicItemRecord>	mRecords				= new ArrayList<>(ARRAY_SIZE);

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
		mRecords = new ArrayList<>(ARRAY_SIZE);
	}

	public void updateMagicItemRecord(MagicItemRecord record) {
		for (int i = 0; i < mRecords.size(); i++) {
			MagicItemRecord owned = mRecords.get(i);
			if (owned.getName().equals(record.getName())) {
				mRecords.set(i, record);
				return;
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static MagicItemRecord getMagicItemRecord(int which) {
		if (mMagicItemCombinedList == null) {
			getMagicItemCombinedList();
		}
		return mMagicItemMasterList[which];
	}

	public static MagicItemRecord getMagicItemRecord(String name) {
		if (mMagicItemCombinedList == null) {
			getMagicItemCombinedList();
		}

		for (MagicItemRecord record : mMagicItemCombinedList) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
	}

	public ArrayList<MagicItemRecord> getRecords() {
		return mRecords;
	}

	public static void addMagicItemToFile(ArrayList<MagicItemRecord> recordsToAdd) {
		try (FileWriter fw = new FileWriter(SystemInfo.getMagicItemUserPath(), true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
			for (MagicItemRecord record : recordsToAdd) {
				out.println(record.toRecordFile());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static void readMagicItems(Scanner scanner, MagicItemRecord[] list) {
		int count = 0;
		for (String line; (line = scanner.nextLine()) != null;) {
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
			list[count++] = record;
		}
	}

	public static MagicItemRecord[] getMagicItemCombinedList() {
		if (mMagicItemCombinedList == null) {
			if (mMagicItemMasterList == null) {
				getMagicItemMasterList();
			}
			if (mMagicItemUserList == null) {
				getMagicItemUserList();
			}
			mMagicItemCombinedList = Stream.concat(Arrays.stream(mMagicItemMasterList), Arrays.stream(mMagicItemUserList)).toArray(MagicItemRecord[]::new);
		}
		return mMagicItemCombinedList;
	}

	public static MagicItemRecord[] getMagicItemUserList() {
		if (mMagicItemUserList == null) {
			mMagicItemUserList = new MagicItemRecord[ARRAY_SIZE];

			Scanner scanner = null;
			try {
				//				is = new InputStream//ACS.class.getModule().getResourceAsStream(SystemInfo.getMagicItemUserPath());
				scanner = new Scanner(new File(SystemInfo.getMagicItemUserPath()), "UTF-8"); //$NON-NLS-1$

				readMagicItems(scanner, mMagicItemUserList);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}

		}
		return mMagicItemUserList;
	}

	public static Object[] getMagicItemMasterList() {
		if (mMagicItemMasterList == null) {
			mMagicItemMasterList = new MagicItemRecord[2];

			Scanner scanner = null;
			InputStream is = null;
			try {
				is = ACS.class.getModule().getResourceAsStream("resources/MagicItem.txt"); //$NON-NLS-1$
				scanner = new Scanner(is, "UTF-8"); //$NON-NLS-1$

				readMagicItems(scanner, mMagicItemMasterList);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			if (scanner != null) {
				scanner.close();
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
					if (key.equals(FILE_SECTION_END_KEY)) {
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
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());
		for (MagicItemRecord record : mRecords) {
			br.write(COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(CHARGES_KEY + TKStringHelpers.SPACE + record.getCharges() + System.lineSeparator());
			br.write(COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
		}
		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
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
