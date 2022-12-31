/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.WeaponRecord;
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

public class WeaponList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String				FILE_SECTION_START_KEY	= "WEAPONS_SECTION_START";		//$NON-NLS-1$
	public static final String				FILE_SECTION_END_KEY	= "WEAPONS_SECTION_END";		//$NON-NLS-1$

	private static final String				COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String				EQUIPPED_KEY			= "EQUIPPED_KEY";				//$NON-NLS-1$
	private static final String				NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String				METAL_KEY				= "METAL_KEY";					//$NON-NLS-1$
	private static final String				TYPE_KEY				= "TYPE_KEY";					//$NON-NLS-1$
	private static final String				HANDED_KEY				= "HANDED_KEY";					//$NON-NLS-1$
	private static final String				STR_KEY					= "STR_KEY";					//$NON-NLS-1$
	private static final String				DEX_KEY					= "DEX_KEY";					//$NON-NLS-1$
	private static final String				ENCUMBER_KEY			= "ENCUMBER_KEY";				//$NON-NLS-1$
	private static final String				LENGTH_KEY				= "LENGTH_KEY";					//$NON-NLS-1$
	private static final String				SPEED_KEY				= "SPEED_KEY";					//$NON-NLS-1$
	private static final String				BREAK_KEY				= "BREAK_KEY";					//$NON-NLS-1$
	private static final String				HIT_BONUS_KEY			= "HIT_BONUS_KEY";				//$NON-NLS-1$
	private static final String				HIT_BONUS_DP_KEY		= "HIT_BONUS_DP_KEY";			//$NON-NLS-1$
	private static final String				DAMAGE1_KEY				= "DAMAGE1_KEY";				//$NON-NLS-1$
	private static final String				DAMAGE2_KEY				= "DAMAGE2_KEY";				//$NON-NLS-1$
	private static final String				COST_KEY				= "COST_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int						mArraySize				= 50;
	private static WeaponRecord[]			mWeaponCombinedList;
	private static WeaponRecord[]			mWeaponMasterList;
	private static WeaponRecord[]			mWeaponUserList;
	private static String[]					mWeaponProficiencyList;
	private static ArrayList<WeaponRecord>	mRecords				= new ArrayList<>(mArraySize);

	private int								mCount;
	private boolean							mEquipped;
	private String							mName;
	private int								mMetal;													// 		[ Iron, Ang, Borang, Ardacer, Ethru, Ithilnur, Mithril, Laen, Eog, Tasarung ]
	private int								mType;													// 0 = H2H Bladed, 1 = H2H Blunt, 2 = H2H Misc, 3 = Thrown, 4 = Bows
	private int								mHanded;												// 0 = 1 handed only, 1 = either 1 or 2 handed, 2 = 2 handed only, 3 = mounted & charging only
	private int								mStrength;
	private int								mDexterity;
	private float							mEncumbrance;
	private int								mWeaponLength;											// -1 = N/A
	private int								mAttackSpeed;
	private int								mWeaponBreak;											// -1 = N/A (doesn't break)
	private int								mHitBonus;
	private int								mDPHitBonus;
	private int								mDamageOneHanded;
	private int								mDamageTwoHanded;
	private float							mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public WeaponList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public float addWeapons(ArrayList<WeaponRecord> items, boolean calculateCost) {
		float cost = 0f;
		// DW Think about stacking and unstacking like items
		for (WeaponRecord record : items) {
			// needs to be added one line each for equipping
			mRecords.add(record);
			if (calculateCost) {
				cost += record.getCost() * record.getCount();
			}
		}
		return cost;
	}

	public void updateWeapon(WeaponRecord record, int index) {
		WeaponRecord original = mRecords.get(index);
		if (original != null && original.equals(record)) {
			original.update(record);
		}
	}

	public float removeWeapons(ArrayList<WeaponRecord> items, boolean calculateCost) {
		float cost = 0f;
		// DW Think about stacking and unstacking like items
		for (WeaponRecord record : items) {
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
	public static WeaponRecord getWeaponRecord(int which) {
		if (mWeaponCombinedList == null) {
			getWeaponCombinedList();
		}
		return mWeaponCombinedList[which];
	}

	public static WeaponRecord getWeaponRecord(String name) {
		if (mWeaponCombinedList == null) {
			getWeaponCombinedList();
		}

		for (WeaponRecord record : mWeaponCombinedList) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
	}

	public ArrayList<WeaponRecord> getRecords() {
		return mRecords;
	}

	public WeaponRecord getRecord(String weapon) {
		for (WeaponRecord record : mRecords) {
			if (record.getName().equals(weapon)) {
				return record;
			}
		}
		return null;
	}

	public static void addWeaponToFile(ArrayList<WeaponRecord> recordsToAdd) {
		try (FileWriter fw = new FileWriter(SystemInfo.getWeaponUserPath(), false);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
			for (WeaponRecord record : recordsToAdd) {
				out.println(record.toRecordFile());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static WeaponRecord[] readWeapons(Scanner scanner) {
		ArrayList<WeaponRecord> list = new ArrayList<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();

			if (line.startsWith("//") || line.isBlank()) { //$NON-NLS-1$
				continue;
			}

			String[] splitLine = line.split(", "); //$NON-NLS-1$
			//					System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (splitLine.length > 17) {
				System.err.println(splitLine[2]);
			}
			WeaponRecord record = new WeaponRecord(TKStringHelpers.getIntValue(splitLine[0], 0), //
							TKStringHelpers.getBoolValue(splitLine[1], false), //
							splitLine[2].replaceAll("\"", ""), // //$NON-NLS-1$ //$NON-NLS-2$
							TKStringHelpers.getIntValue(splitLine[3], 0), //
							TKStringHelpers.getIntValue(splitLine[4], 0), //
							TKStringHelpers.getIntValue(splitLine[5], 0), //
							TKStringHelpers.getIntValue(splitLine[6], 0), //
							TKStringHelpers.getIntValue(splitLine[7], 0), //
							TKStringHelpers.getFloatValue(splitLine[8], 0f), //
							TKStringHelpers.getIntValue(splitLine[9], 0), //
							TKStringHelpers.getIntValue(splitLine[10], 0), //
							TKStringHelpers.getIntValue(splitLine[11], 0), //
							TKStringHelpers.getIntValue(splitLine[12], 0), //
							TKStringHelpers.getIntValue(splitLine[13], 0), //
							TKStringHelpers.getIntValue(splitLine[14], 0), //
							TKStringHelpers.getIntValue(splitLine[15], 0), //
							TKStringHelpers.getFloatValue(splitLine[16], 0f));
			list.add(record);
		}

		return list.toArray(new WeaponRecord[list.size()]);
	}

	public static WeaponRecord[] getWeaponCombinedList() {
		if (mWeaponCombinedList == null) {
			if (mWeaponMasterList == null) {
				getWeaponMasterList();
			}
			if (mWeaponUserList == null) {
				getWeaponUserList();
			}
			mWeaponCombinedList = Stream.concat(Arrays.stream(mWeaponMasterList), Arrays.stream(mWeaponUserList)).toArray(WeaponRecord[]::new);
			Arrays.sort(mWeaponCombinedList);
		}
		return mWeaponCombinedList;
	}

	public static void updateWeaponUserList() {
		mWeaponUserList = null;
		mWeaponCombinedList = null;
		getWeaponCombinedList();
	}

	public static WeaponRecord[] getWeaponUserList() {
		if (mWeaponUserList == null) {

			Scanner scanner = null;
			try {
				//				is = new InputStream//ACS.class.getModule().getResourceAsStream(SystemInfo.getWeaponUserPath());
				scanner = new Scanner(new File(SystemInfo.getWeaponUserPath()), "UTF-8"); //$NON-NLS-1$

				mWeaponUserList = readWeapons(scanner);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}

		}
		return mWeaponUserList;
	}

	public static WeaponRecord[] getWeaponMasterList() {
		if (mWeaponMasterList == null) {

			Scanner scanner = null;
			InputStream is = null;
			try {
				is = ACS.class.getModule().getResourceAsStream("resources/Weapon.txt"); //$NON-NLS-1$
				scanner = new Scanner(is, "UTF-8"); //$NON-NLS-1$

				mWeaponMasterList = readWeapons(scanner);

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
		return mWeaponMasterList;

	}

	public static String[] getProficiencyList() {
		if (mWeaponCombinedList == null) {
			getWeaponCombinedList();
		}
		if (mWeaponProficiencyList == null) {
			loadProficiencies();
		}
		return mWeaponProficiencyList;
	}

	private static void loadProficiencies() {
		int count = 0;
		mArraySize = mWeaponCombinedList.length;
		mWeaponProficiencyList = new String[mArraySize];

		for (WeaponRecord record : mWeaponCombinedList) {
			String name = record.getName();
			if (!name.isBlank()) {
				mWeaponProficiencyList[count++] = name;
			}
		}
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
						String value = ""; //$NON-NLS-1$
						setKeyValuePair(key, value);
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

		for (WeaponRecord record : mRecords) {
			br.write(TKStringHelpers.TAB + COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + EQUIPPED_KEY + TKStringHelpers.SPACE + record.isEquipped() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + METAL_KEY + TKStringHelpers.SPACE + record.getMetalID() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + TYPE_KEY + TKStringHelpers.SPACE + record.getType() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + HANDED_KEY + TKStringHelpers.SPACE + record.getHanded() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + STR_KEY + TKStringHelpers.SPACE + record.getStrength() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + DEX_KEY + TKStringHelpers.SPACE + record.getDexterity() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + ENCUMBER_KEY + TKStringHelpers.SPACE + record.getEncumbrance() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + LENGTH_KEY + TKStringHelpers.SPACE + record.getWeaponLength() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + SPEED_KEY + TKStringHelpers.SPACE + record.getAttackSpeed() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + BREAK_KEY + TKStringHelpers.SPACE + record.getWeaponBreak() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + HIT_BONUS_KEY + TKStringHelpers.SPACE + record.getHitBonus() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + HIT_BONUS_DP_KEY + TKStringHelpers.SPACE + record.getDPHitBonus() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + DAMAGE1_KEY + TKStringHelpers.SPACE + record.getDamageOneHanded() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + DAMAGE2_KEY + TKStringHelpers.SPACE + record.getDamageTwoHanded() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + COST_KEY + TKStringHelpers.SPACE + record.getCost() + System.lineSeparator());
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
			mType = TKStringHelpers.getIntValue(value, 0);
		} else if (HANDED_KEY.equals(key)) {
			mHanded = TKStringHelpers.getIntValue(value, 0);
		} else if (STR_KEY.equals(key)) {
			mStrength = TKStringHelpers.getIntValue(value, 0);
		} else if (DEX_KEY.equals(key)) {
			mDexterity = TKStringHelpers.getIntValue(value, 0);
		} else if (ENCUMBER_KEY.equals(key)) {
			mEncumbrance = TKStringHelpers.getFloatValue(value, 0);
		} else if (LENGTH_KEY.equals(key)) {
			mWeaponLength = TKStringHelpers.getIntValue(value, 0);
		} else if (SPEED_KEY.equals(key)) {
			mAttackSpeed = TKStringHelpers.getIntValue(value, 0);
		} else if (BREAK_KEY.equals(key)) {
			mWeaponBreak = TKStringHelpers.getIntValue(value, 0);
		} else if (HIT_BONUS_KEY.equals(key)) {
			mHitBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (HIT_BONUS_DP_KEY.equals(key)) {
			mDPHitBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (DAMAGE1_KEY.equals(key)) {
			mDamageOneHanded = TKStringHelpers.getIntValue(value, 0);
		} else if (DAMAGE2_KEY.equals(key)) {
			mDamageTwoHanded = TKStringHelpers.getIntValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0f);
			mRecords.add(new WeaponRecord(mCount, mEquipped, mName, mMetal, mType, mHanded, mStrength, mDexterity, mEncumbrance, mWeaponLength, mAttackSpeed, mWeaponBreak, mHitBonus, mDPHitBonus, mDamageOneHanded, mDamageTwoHanded, mCost));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
