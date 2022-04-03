/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.ArmorRecord;
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

public class ArmorList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String				FILE_SECTION_START_KEY	= "ARMOR_SECTION_START";		//$NON-NLS-1$
	public static final String				FILE_SECTION_END_KEY	= "ARMOR_SECTION_END";			//$NON-NLS-1$

	private static final String				COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String				EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String				NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String				METAL_KEY				= "METAL_KEY";					//$NON-NLS-1$
	private static final String				TYPE_KEY				= "TYPE_KEY";					//$NON-NLS-1$
	private static final String				PROT_KEY				= "PROT_KEY";					//$NON-NLS-1$
	private static final String				ENCUM_KEY				= "ENCUM_KEY";					//$NON-NLS-1$
	private static final String				ABSORB_KEY				= "ABSORB_KEY";					//$NON-NLS-1$
	private static final String				BONUS_KEY				= "BONUS_KEY";					//$NON-NLS-1$
	private static final String				MISSILE_KEY				= "MISSILE_KEY";				//$NON-NLS-1$
	private static final String				STR_KEY					= "STR_KEY";					//$NON-NLS-1$
	private static final String				PARRY_KEY				= "PARRY_KEY";					//$NON-NLS-1$
	private static final String				BREAK_KEY				= "BREAK_KEY";					//$NON-NLS-1$
	private static final String				COST_KEY				= "COST_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int						mArraySize				= 62;
	private static ArmorRecord[]			mArmorCombinedList		= null;
	private static ArmorRecord[]			mArmorMasterList		= null;
	private static ArmorRecord[]			mArmorUserList			= null;
	private static ArrayList<ArmorRecord>	mRecords				= new ArrayList<>(mArraySize);

	private int								mCount;
	private boolean							mEquipped;
	private String							mName;
	private int								mMetal;													// 		[ Iron, Ang, Borang, Ardacer, Ethru, Ithilnur, Mithril, Laen, Eog, Tasarung ]
	private int[]							mProtectionType;										// 0=Head-Top, 1=Head-Side, 2=Head-Face, 3=Neck, 4=Torso, 5=Arms, 6=Hands, 7=Legs, 8=Feet, 9=Shield
	private int								mProtectionAmount;
	private float							mEncumbrance;
	private int								mAbsorption;
	private int								mBonus;													// 50% chance to increase absorption by 1
	private int								mMissileAbsorption;
	private int								mStrengthRequirement;
	private int								mParry;
	private int								mBreak;
	private float							mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public ArmorList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public float addArmor(ArrayList<ArmorRecord> items, boolean calculateCost) {
		float cost = 0f;
		// DW Think about stacking and unstacking like items
		for (ArmorRecord record : items) {
			// this needs to be added one line each for equipping
			mRecords.add(record);
			if (calculateCost) {
				cost += record.getCost() * record.getCount();
			}
		}
		return cost;
	}

	public float removeArmor(ArrayList<ArmorRecord> items, boolean calculateCost) {
		float cost = 0f;
		// DW Think about stacking and unstacking like items
		for (ArmorRecord record : items) {
			// this needs to be added one line each for equipping
			mRecords.remove(record);
			if (calculateCost) {
				cost += record.getCost() * record.getCount();
			}
		}
		return cost;
	}

	public void clearRecords() {
		mRecords = new ArrayList<>(mArraySize);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static ArmorRecord getArmorRecord(int which) {
		if (mArmorCombinedList == null) {
			getArmorCombinedList();
		}
		return mArmorCombinedList[which];
	}

	public static ArmorRecord getArmorRecord(String name) {
		if (mArmorCombinedList == null) {
			getArmorCombinedList();
		}

		for (ArmorRecord record : mArmorCombinedList) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
	}

	/** @return The armorList. */
	public ArrayList<ArmorRecord> getRecords() {
		return mRecords;
	}

	public static void addArmorToFile(ArrayList<ArmorRecord> recordsToAdd) {
		try (FileWriter fw = new FileWriter(SystemInfo.getArmorUserPath(), true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
			for (ArmorRecord record : recordsToAdd) {
				out.println(record.toRecordFile());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static ArmorRecord[] readArmor(Scanner scanner) {
		ArrayList<ArmorRecord> list = new ArrayList<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();

			if (line.startsWith("//") || line.isBlank()) { //$NON-NLS-1$
				continue;
			}

			String[] splitLine = line.split(", "); //$NON-NLS-1$
			//		System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			int arrayAdjustment = 0;
			if (splitLine.length > 14) {
				arrayAdjustment = splitLine.length - 14;
				for (int i = 1; i <= arrayAdjustment; i++) {
					splitLine[4] += ", " + splitLine[4 + i]; //$NON-NLS-1$
				}
				//						System.err.println(splitLine[1] + " :: " + splitLine[2 + arrayAdjustment]);
			}
			ArmorRecord record = new ArmorRecord(TKStringHelpers.getIntValue(splitLine[0], 0), //
							TKStringHelpers.getBoolValue(splitLine[1], false), //
							splitLine[2].replaceAll("\"", ""), // //$NON-NLS-1$ //$NON-NLS-2$
							TKStringHelpers.getIntValue(splitLine[3], 0), //
							TKStringHelpers.getIntArray(splitLine[4], new int[] {}), //
							TKStringHelpers.getIntValue(splitLine[5 + arrayAdjustment], 0), //
							TKStringHelpers.getFloatValue(splitLine[6 + arrayAdjustment], 0f), //
							TKStringHelpers.getIntValue(splitLine[7 + arrayAdjustment], 0), //
							TKStringHelpers.getIntValue(splitLine[8 + arrayAdjustment], 0), //
							TKStringHelpers.getIntValue(splitLine[9 + arrayAdjustment], 0), //
							TKStringHelpers.getIntValue(splitLine[10 + arrayAdjustment], 0), //
							TKStringHelpers.getIntValue(splitLine[11 + arrayAdjustment], 0), //
							TKStringHelpers.getIntValue(splitLine[12 + arrayAdjustment], 0), //
							TKStringHelpers.getFloatValue(splitLine[13 + arrayAdjustment], 0f));

			list.add(record);
		}
		return list.toArray(new ArmorRecord[list.size()]);
	}

	public static ArmorRecord[] getArmorCombinedList() {
		if (mArmorCombinedList == null) {
			if (mArmorMasterList == null) {
				getArmorMasterList();
			}
			if (mArmorUserList == null) {
				getArmorUserList();
			}
			mArmorCombinedList = Stream.concat(Arrays.stream(mArmorMasterList), Arrays.stream(mArmorUserList)).toArray(ArmorRecord[]::new);
		}
		return mArmorCombinedList;
	}

	public static ArmorRecord[] getArmorUserList() {
		if (mArmorUserList == null) {

			Scanner scanner = null;
			try {
				//				is = new InputStream//ACS.class.getModule().getResourceAsStream(SystemInfo.getArmorUserPath());
				scanner = new Scanner(new File(SystemInfo.getArmorUserPath()), "UTF-8"); //$NON-NLS-1$

				mArmorUserList = readArmor(scanner);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}

		}
		return mArmorUserList;
	}

	public static ArmorRecord[] getArmorMasterList() {
		if (mArmorMasterList == null) {

			Scanner scanner = null;
			InputStream is = null;
			try {
				is = ACS.class.getModule().getResourceAsStream("resources/Armor.txt"); //$NON-NLS-1$
				scanner = new Scanner(is, "UTF-8"); //$NON-NLS-1$

				mArmorMasterList = readArmor(scanner);
				mArraySize = mArmorMasterList.length;

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
		return mArmorMasterList;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, " ", false); //$NON-NLS-1$
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
		for (ArmorRecord record : mRecords) {

			int[] type = record.getProtectionType();
			StringBuffer typeString = new StringBuffer("{ "); //$NON-NLS-1$
			for (int i = 0; i < type.length; i++) {
				typeString.append(type[i]);
				if (i < type.length - 1) {
					typeString.append(", "); //$NON-NLS-1$
				}
			}
			typeString.append(" }"); //$NON-NLS-1$

			br.write(COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(METAL_KEY + TKStringHelpers.SPACE + record.getMetalID() + System.lineSeparator());
			br.write(TYPE_KEY + TKStringHelpers.SPACE + typeString + System.lineSeparator());
			br.write(PROT_KEY + TKStringHelpers.SPACE + record.getProtectionAmount() + System.lineSeparator());
			br.write(ENCUM_KEY + TKStringHelpers.SPACE + record.getEncumbrance() + System.lineSeparator());
			br.write(ABSORB_KEY + TKStringHelpers.SPACE + record.getAbsorption() + System.lineSeparator());
			br.write(BONUS_KEY + TKStringHelpers.SPACE + record.getBonus() + System.lineSeparator());
			br.write(MISSILE_KEY + TKStringHelpers.SPACE + record.getMissileAbsorption() + System.lineSeparator());
			br.write(STR_KEY + TKStringHelpers.SPACE + record.getStrengthRequirement() + System.lineSeparator());
			br.write(PARRY_KEY + TKStringHelpers.SPACE + record.getParry() + System.lineSeparator());
			br.write(BREAK_KEY + TKStringHelpers.SPACE + record.getBreak() + System.lineSeparator());
			br.write(COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
		}
		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	// DW need to add magic info to Armor
	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (COUNT_KEY.equals(key)) {
			mCount = TKStringHelpers.getIntValue(value, 0);
		} else if (EQUIPPED_KEY.equals(key)) {
			mEquipped = TKStringHelpers.getBoolValue(value, false);
		} else if (NAME_KEY.equals(key)) {
			mName = value;
		} else if (METAL_KEY.equals(key)) {
			mMetal = TKStringHelpers.getIntValue(value, 0);
		} else if (TYPE_KEY.equals(key)) {
			mProtectionType = TKStringHelpers.getIntArray(value, new int[] {});
		} else if (PROT_KEY.equals(key)) {
			mProtectionAmount = TKStringHelpers.getIntValue(value, 0);
		} else if (ENCUM_KEY.equals(key)) {
			mEncumbrance = TKStringHelpers.getFloatValue(value, 0f);
		} else if (ABSORB_KEY.equals(key)) {
			mAbsorption = TKStringHelpers.getIntValue(value, 0);
		} else if (BONUS_KEY.equals(key)) {
			mBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (MISSILE_KEY.equals(key)) {
			mMissileAbsorption = TKStringHelpers.getIntValue(value, 0);
		} else if (STR_KEY.equals(key)) {
			mStrengthRequirement = TKStringHelpers.getIntValue(value, 0);
		} else if (PARRY_KEY.equals(key)) {
			mParry = TKStringHelpers.getIntValue(value, 0);
		} else if (BREAK_KEY.equals(key)) {
			mBreak = TKStringHelpers.getIntValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0);
			mRecords.add(new ArmorRecord(mCount, mEquipped, mName, mMetal, mProtectionType, mProtectionAmount, mEncumbrance, mAbsorption, mBonus, mMissileAbsorption, mStrengthRequirement, mParry, mBreak, mCost));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
