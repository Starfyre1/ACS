/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.MetalRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetalList {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String		FILE_SECTTION_START_KEY	= "MAGIC_ITEMS_SECTTION_START";	//$NON-NLS-1$
	public static final String		FILE_SECTTION_END_KEY	= "MAGIC_ITEMS_SECTTION_END";	//$NON-NLS-1$

	private static MetalRecord[]	mMetalMasterList;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// DW Will need to change this to MaterialList so leather/cloth etc make sense
	private MetalList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private static void createMetalMasterList() {
		mMetalMasterList = new MetalRecord[10];

		File in = new File("./src/com/starfyre1/dataset/Metal.txt"); //$NON-NLS-1$
		try {
			BufferedReader brIn = new BufferedReader(new FileReader(in));
			int count = 0;
			for (String line; (line = brIn.readLine()) != null;) {
				line = line.trim();

				if (line.startsWith("//") || line.isBlank()) { //$NON-NLS-1$
					continue;
				}

				String[] splitLine = line.split(","); //$NON-NLS-1$
				//					System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				if (splitLine.length > 11) {
					System.err.println(splitLine[0]);
				}
				MetalRecord record = new MetalRecord(splitLine[0].trim(), //
								TKStringHelpers.getFloatValue(splitLine[1].trim(), 0f), //
								TKStringHelpers.getIntValue(splitLine[2].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[3].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[4].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[5].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[6].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[7].trim(), 0), //
								TKStringHelpers.getIntValue(splitLine[8].trim(), 0), //
								splitLine[9].trim(), //
								splitLine[10].trim());
				mMetalMasterList[count++] = record;
			}

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static Object[] getMetalMasterList() {
		if (mMetalMasterList == null) {
			createMetalMasterList();
		}
		return mMetalMasterList;
	}

	public static List<MetalRecord> getRecords() {
		MetalRecord[] metalList = (MetalRecord[]) MetalList.getMetalMasterList();
		List<MetalRecord> list = new ArrayList<>();
		for (MetalRecord record : metalList) {
			list.add(record);
		}
		return list;
	}

	public static MetalRecord getMetal(int which) {
		return (MetalRecord) MetalList.getMetalMasterList()[which];
	}

	public static int getMetalID(String which) {
		for (int i = 0; i < mMetalMasterList.length; i++) {
			if (mMetalMasterList[i].getName().equals(which)) {
				return i;
			}
		}
		return -1;
	}

	public static MetalRecord getMetalRecord(String which) {
		for (MetalRecord record : mMetalMasterList) {
			if (record.getName().equals(which)) {
				return record;
			}
		}
		return null;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
