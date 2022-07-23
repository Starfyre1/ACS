/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.EquipmentRecord;
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

public class EquipmentList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTION_START_KEY	= "EQUIPMENT_SECTION_START";	//$NON-NLS-1$
	public static final String			FILE_SECTION_END_KEY	= "EQUIPMENT_SECTION_END";		//$NON-NLS-1$

	private static final String			COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String			EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String			NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String			ENCUMBRANCE_KEY			= "ENCUMBRANCE_KEY";			//$NON-NLS-1$
	private static final String			COST_KEY				= "COST_KEY";					//$NON-NLS-1$
	private static final String			NOTES_KEY				= "NOTES_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int					mArraySize				= 132;
	private static EquipmentRecord[]	mEquipmentCombinedList	= null;
	private static EquipmentRecord[]	mEquipmentMasterList	= null;
	private static EquipmentRecord[]	mEquipmentUserList		= null;
	private ArrayList<EquipmentRecord>	mRecords				= new ArrayList<>(mArraySize);

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
		mRecords = new ArrayList<>(mArraySize);
	}

	public void updateEquipmentRecord(EquipmentRecord record) {
		for (int i = 0; i < mRecords.size(); i++) {
			EquipmentRecord owned = mRecords.get(i);
			if (owned.getName().equals(record.getName())) {
				mRecords.set(i, record);
				return;
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static EquipmentRecord getEquipmentRecord(int which) {
		if (mEquipmentCombinedList == null) {
			getEquipmentCombinedList();
		}
		return mEquipmentMasterList[which];
	}

	public static EquipmentRecord getEquipmentRecord(String name) {
		if (mEquipmentCombinedList == null) {
			getEquipmentCombinedList();
		}

		for (EquipmentRecord record : mEquipmentCombinedList) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
	}

	public ArrayList<EquipmentRecord> getRecords() {
		return mRecords;
	}

	public static void addEquipmentToFile(ArrayList<EquipmentRecord> recordsToAdd) {
		try (FileWriter fw = new FileWriter(SystemInfo.getEquipmentUserPath(), false);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
			for (EquipmentRecord record : recordsToAdd) {
				out.println(record.toRecordFile());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static EquipmentRecord[] readEquipment(Scanner scanner) {
		ArrayList<EquipmentRecord> list = new ArrayList<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();

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
			list.add(record);
		}
		return list.toArray(new EquipmentRecord[list.size()]);
	}

	public static EquipmentRecord[] getEquipmentCombinedList() {
		if (mEquipmentCombinedList == null) {
			if (mEquipmentMasterList == null) {
				getEquipmentMasterList();
			}
			if (mEquipmentUserList == null) {
				getEquipmentUserList();
			}
			mEquipmentCombinedList = Stream.concat(Arrays.stream(mEquipmentMasterList), Arrays.stream(mEquipmentUserList)).toArray(EquipmentRecord[]::new);
			Arrays.sort(mEquipmentCombinedList);
		}
		return mEquipmentCombinedList;
	}

	public static void updateEquipmentUserList() {
		mEquipmentUserList = null;
		mEquipmentCombinedList = null;
		getEquipmentCombinedList();
	}

	public static EquipmentRecord[] getEquipmentUserList() {
		if (mEquipmentUserList == null) {

			Scanner scanner = null;
			try {
				//				is = new InputStream//ACS.class.getModule().getResourceAsStream(SystemInfo.getEquipmentUserPath());
				scanner = new Scanner(new File(SystemInfo.getEquipmentUserPath()), "UTF-8"); //$NON-NLS-1$

				mEquipmentUserList = readEquipment(scanner);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}

		}
		return mEquipmentUserList;
	}

	public static EquipmentRecord[] getEquipmentMasterList() {
		if (mEquipmentMasterList == null) {

			Scanner scanner = null;
			InputStream is = null;
			try {
				is = ACS.class.getModule().getResourceAsStream("resources/Equipment.txt"); //$NON-NLS-1$
				scanner = new Scanner(is, "UTF-8"); //$NON-NLS-1$

				mEquipmentMasterList = readEquipment(scanner);
				mArraySize = mEquipmentMasterList.length;

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
		return mEquipmentMasterList;

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
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

		for (EquipmentRecord record : mRecords) {
			br.write(TKStringHelpers.TAB + COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + ENCUMBRANCE_KEY + TKStringHelpers.SPACE + record.getEncumbrance() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + NOTES_KEY + TKStringHelpers.SPACE + record.getNotes() + System.lineSeparator());
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
		} else if (ENCUMBRANCE_KEY.equals(key)) {
			mEncumbrance = TKStringHelpers.getFloatValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0f);
		} else if (NOTES_KEY.equals(key)) {
			mNotes = value;
			mRecords.add(new EquipmentRecord(mCount, mEquipped, mName, mEncumbrance, mCost, mNotes));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
