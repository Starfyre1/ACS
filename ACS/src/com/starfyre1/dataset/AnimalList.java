/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AnimalRecord;
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

public class AnimalList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
		All War-horses have an ASP = +0 and a WL = 9.

		Carry =		Amount in Pounds including the rider, that the animal can
				carry before reducing it's speed by 1/4.  An animal may not carry
				more than twice it's allotted Carry.  Animals may drag a weight
				equal to their Carry X 2, if the load is on wheels, increase it to
				Carry X 5.

		Move =		Standard movement per round.

		Travel =	Number of miles that may be covered in clear terrain in one day.
				in wilderness areas the maximum movement would be 25 miles
				per day.

			All Horses and mules have an Armor Rating of 55% + whatever armor that you
			buy for them.

	*/

	public static final String				FILE_SECTION_START_KEY	= "ANIMAL_SECTION_START";		//$NON-NLS-1$
	public static final String				FILE_SECTION_END_KEY	= "ANIMAL_SECTION_END";			//$NON-NLS-1$

	private static final String				COUNT_KEY				= "COUNT_KEY";					//$NON-NLS-1$
	private static final String				NAME_KEY				= "NAME_KEY";					//$NON-NLS-1$
	private static final String				CARRY_KEY				= "CARRY_KEY";					//$NON-NLS-1$
	private static final String				MOVE_KEY				= "MOVE_KEY";					//$NON-NLS-1$
	private static final String				TRAVEL_KEY				= "TRAVEL_KEY";					//$NON-NLS-1$
	private static final String				HITS_KEY				= "HITS_KEY";					//$NON-NLS-1$
	private static final String				HIT_BONUS_KEY			= "HIT_BONUS_KEY";				//$NON-NLS-1$
	private static final String				KICK_DAMAGE_KEY			= "KICK_DAMAGE_KEY";			//$NON-NLS-1$
	private static final String				ARMOR_KEY				= "ARMOR_KEY";					//$NON-NLS-1$
	private static final String				COST_KEY				= "COST_KEY";					//$NON-NLS-1$
	private static final String				NOTES_KEY				= "NOTES_KEY";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int						mArraySize				= 26;
	private static AnimalRecord[]			mAnimalCombinedList;
	private static AnimalRecord[]			mAnimalMasterList;
	private static AnimalRecord[]			mAnimalUserList;
	private static ArrayList<AnimalRecord>	mRecords				= new ArrayList<>(mArraySize);

	private int								mCount;
	private String							mName;
	private int								mCarry;
	private int								mMove;
	private int								mTravel;
	private String							mHits;
	private int								mHitBonus;
	private int								mKickDamage;
	private int								mArmor;
	private float							mCost;
	private String							mNotes;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	// Verify stats for Falcon
	// Yes the "kick" damage is 1D3 (unique for animals)
	public AnimalList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public float addAnimals(ArrayList<AnimalRecord> items, boolean calculateCost) {
		float cost = 0f;
		for (AnimalRecord record : items) {
			boolean complete = false;
			// DW Think about stacking and unstacking like items
			for (AnimalRecord owned : mRecords) {
				if (owned.getName().equals(record.getName())) {
					owned.setCount(owned.getCount() + record.getCount());
					complete = true;
				}
			}
			if (!complete) {
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
	public float removeAnimals(ArrayList<AnimalRecord> soldItems, boolean calculateCost) {
		float cost = 0f;
		for (AnimalRecord record : soldItems) {
			boolean completed = false;
			for (AnimalRecord owned : mRecords) {
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

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static AnimalRecord getAnimalRecord(int which) {
		return mAnimalMasterList[which];
	}

	public static AnimalRecord getAnimalRecord(String name) {
		if (mAnimalCombinedList == null) {
			getAnimalCombinedList();
		}

		for (AnimalRecord record : mAnimalCombinedList) {
			if (record.getName().equals(name)) {
				return record;
			}
		}
		return null;
	}

	public ArrayList<AnimalRecord> getRecords() {
		return mRecords;
	}

	public static void addAnimalToFile(ArrayList<AnimalRecord> recordsToAdd) {
		try (FileWriter fw = new FileWriter(SystemInfo.getAnimalUserPath(), false);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
			for (AnimalRecord record : recordsToAdd) {
				out.println(record.toRecordFile());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static AnimalRecord[] readAnimal(Scanner scanner) {
		ArrayList<AnimalRecord> list = new ArrayList<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();

			if (line.startsWith("//") || line.isBlank()) { //$NON-NLS-1$
				continue;
			}

			String[] splitLine = line.split(", "); //$NON-NLS-1$
			//					System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (splitLine.length > 11) {
				System.err.println(splitLine[1]);
			}
			AnimalRecord record = new AnimalRecord(TKStringHelpers.getIntValue(splitLine[0], 0), //
							splitLine[1].replaceAll("\"", ""), // //$NON-NLS-1$ //$NON-NLS-2$
							TKStringHelpers.getIntValue(splitLine[2], 0), //
							TKStringHelpers.getIntValue(splitLine[3], 0), //
							TKStringHelpers.getIntValue(splitLine[4], 0), //
							splitLine[5].replaceAll("\"", ""), //, // //$NON-NLS-1$ //$NON-NLS-2$
							TKStringHelpers.getIntValue(splitLine[6], 0), //
							TKStringHelpers.getIntValue(splitLine[7], 0), //
							TKStringHelpers.getIntValue(splitLine[8], 0), //
							TKStringHelpers.getFloatValue(splitLine[9], 0f), //
							splitLine[10].replaceAll("\"", "")); //$NON-NLS-1$ //$NON-NLS-2$
			list.add(record);
		}
		return list.toArray(new AnimalRecord[list.size()]);
	}

	public static AnimalRecord[] getAnimalCombinedList() {
		if (mAnimalCombinedList == null) {
			if (mAnimalMasterList == null) {
				getAnimalMasterList();
			}
			if (mAnimalUserList == null) {
				getAnimalUserList();
			}
			mAnimalCombinedList = Stream.concat(Arrays.stream(mAnimalMasterList), Arrays.stream(mAnimalUserList)).toArray(AnimalRecord[]::new);
			Arrays.sort(mAnimalCombinedList);
		}
		return mAnimalCombinedList;
	}

	public static void updateAnimalUserList() {
		mAnimalUserList = null;
		mAnimalCombinedList = null;
		getAnimalCombinedList();
	}

	public static AnimalRecord[] getAnimalUserList() {
		if (mAnimalUserList == null) {

			Scanner scanner = null;
			try {
				//				is = new InputStream//ACS.class.getModule().getResourceAsStream(SystemInfo.getAnimalUserPath());
				scanner = new Scanner(new File(SystemInfo.getAnimalUserPath()), "UTF-8"); //$NON-NLS-1$

				mAnimalUserList = readAnimal(scanner);

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}

		}
		return mAnimalUserList;
	}

	public static Object[] getAnimalMasterList() {
		// DW Implement 1D3 for kick damage bonus for falcon
		if (mAnimalMasterList == null) {

			Scanner scanner = null;
			InputStream is = null;
			try {
				is = ACS.class.getModule().getResourceAsStream("resources/Animal.txt"); //$NON-NLS-1$
				scanner = new Scanner(is, "UTF-8"); //$NON-NLS-1$

				mAnimalMasterList = readAnimal(scanner);
				mArraySize = mAnimalMasterList.length;

			} catch (NoSuchElementException nsee) {
				// End of file, nothing to do except exit
			} catch (IOException ioe) {
				ioe.printStackTrace();
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
		return mAnimalMasterList;
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

		for (AnimalRecord record : mRecords) {
			br.write(TKStringHelpers.TAB + COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + NAME_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + CARRY_KEY + TKStringHelpers.SPACE + record.getCarry() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + MOVE_KEY + TKStringHelpers.SPACE + record.getMove() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + TRAVEL_KEY + TKStringHelpers.SPACE + record.getTravel() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + HITS_KEY + TKStringHelpers.SPACE + record.getHits() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + HIT_BONUS_KEY + TKStringHelpers.SPACE + record.getHitBonus() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + KICK_DAMAGE_KEY + TKStringHelpers.SPACE + record.getKickDamage() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + ARMOR_KEY + TKStringHelpers.SPACE + record.getArmor() + System.lineSeparator());
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
		} else if (NAME_KEY.equals(key)) {
			mName = value;
		} else if (CARRY_KEY.equals(key)) {
			mCarry = TKStringHelpers.getIntValue(value, 0);
		} else if (MOVE_KEY.equals(key)) {
			mMove = TKStringHelpers.getIntValue(value, 0);
		} else if (TRAVEL_KEY.equals(key)) {
			mTravel = TKStringHelpers.getIntValue(value, 0);
		} else if (HITS_KEY.equals(key)) {
			mHits = value;
		} else if (HIT_BONUS_KEY.equals(key)) {
			mHitBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (KICK_DAMAGE_KEY.equals(key)) {
			mKickDamage = TKStringHelpers.getIntValue(value, 0);
		} else if (ARMOR_KEY.equals(key)) {
			mArmor = TKStringHelpers.getIntValue(value, 0);
		} else if (COST_KEY.equals(key)) {
			mCost = TKStringHelpers.getFloatValue(value, 0);
		} else if (NOTES_KEY.equals(key)) {
			mNotes = value;
			mRecords.add(new AnimalRecord(mCount, mName, mCarry, mMove, mTravel, mHits, mHitBonus, mKickDamage, mArmor, mCost, mNotes));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
