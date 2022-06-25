/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DeterminationList implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String										FILE_SECTION_START_KEY	= "DETERMINATION_SECTION_START";	//$NON-NLS-1$
	public static final String										FILE_SECTION_END_KEY	= "DETERMINATION_SECTION_END";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static ArrayList<AttributeDeterminationRecord>			mAttribRecords			= new ArrayList<>(16);
	private static ArrayList<LanguageDeterminationRecord>			mLanguageRecords		= new ArrayList<>(16);
	private static ArrayList<MagicSpellDeterminationRecord>			mMagicSpellRecords		= new ArrayList<>(64);
	private static ArrayList<SkillDeterminationRecord>				mSkillRecords			= new ArrayList<>(16);
	private static ArrayList<WeaponProficiencyDeterminationRecord>	mWeaponRecords			= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mWeaponsTeachersRecords	= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mSkillsTeachersRecords	= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mThiefTeachersRecords	= new ArrayList<>(16);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public static void addAttribRecord(AttributeDeterminationRecord record) {
		mAttribRecords.add(record);
	}

	public static void addLanguageRecord(LanguageDeterminationRecord record) {
		mLanguageRecords.add(record);
	}

	public static void addMagicSpellRecord(MagicSpellDeterminationRecord record) {
		mMagicSpellRecords.add(record);
	}

	public static void addSkillRecord(SkillDeterminationRecord record) {
		mSkillRecords.add(record);
	}

	public static void addWeaponRecord(WeaponProficiencyDeterminationRecord record) {
		mWeaponRecords.add(record);
	}

	public static void addTeacherRecord(TeacherDeterminationRecord record) {
		String weaponList[] = WeaponList.getProficiencyList();
		String skillBasicList[] = SkillsDisplay.getBasicSkillsLabels();
		String skillThiefList[] = SkillsDisplay.getThiefSkillsLabels();

		String type = null;
		String expertise = record.getExpertise();
		for (String expert : weaponList) {
			if (expertise.equals(expert)) {
				type = "Weapon"; //$NON-NLS-1$
				break;
			}
		}
		if (type == null) {
			for (String expert : skillBasicList) {
				if (expertise.equals(expert)) {
					type = "Skills"; //$NON-NLS-1$
					break;
				}
			}
		}
		if (type == null) {
			for (String expert : skillThiefList) {
				if (expertise.equals(expert)) {
					type = "Thief"; //$NON-NLS-1$
					break;
				}
			}
		}

		if (type != null) {
			if (type.equals("Weapon")) { //$NON-NLS-1$
				mWeaponsTeachersRecords.add(record);
			} else if (type.equals("Skills")) { //$NON-NLS-1$
				mSkillsTeachersRecords.add(record);
			} else {
				mThiefTeachersRecords.add(record);
			}
		}
	}

	public void clearRecords() {
		mAttribRecords = new ArrayList<>(16);
		mLanguageRecords = new ArrayList<>(16);
		mMagicSpellRecords = new ArrayList<>(64);
		mSkillRecords = new ArrayList<>(16);
		mWeaponRecords = new ArrayList<>(16);
		mWeaponsTeachersRecords = new ArrayList<>(16);
		mSkillsTeachersRecords = new ArrayList<>(16);
		mThiefTeachersRecords = new ArrayList<>(16);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static int[] getTeachersIdByExpertise(String expertise) {
		ArrayList<TeacherDeterminationRecord> allTeachers = getTeachersRecords();
		ArrayList<TeacherDeterminationRecord> returnTeachers = new ArrayList<TeacherDeterminationRecord>();
		for (TeacherDeterminationRecord record : allTeachers) {
			if (record.getExpertise().equals(expertise)) {
				returnTeachers.add(record);
			}
		}
		int[] returnList = new int[returnTeachers.size()];
		int i = 0;
		for (TeacherDeterminationRecord record : returnTeachers) {
			returnList[i++] = record.getID();
		}
		return returnList;
	}

	public static TeacherDeterminationRecord getTeacher(int id) {
		ArrayList<TeacherDeterminationRecord> allTeachers = getTeachersRecords();
		for (TeacherDeterminationRecord record : allTeachers) {
			if (record.getID() == id) {
				return record;
			}
		}
		return null;
	}

	public static int getTeacherIdByName(String name) {
		ArrayList<TeacherDeterminationRecord> allTeachers = getTeachersRecords();
		for (TeacherDeterminationRecord record : allTeachers) {
			if (record.getTeacher() == name) {
				return record.getID();
			}
		}
		return 0;
	}

	/** @return The mAttribRecords. */
	public static ArrayList<AttributeDeterminationRecord> getAttribRecords() {
		return mAttribRecords;
	}

	/** @return The mLanguageRecords. */
	public static ArrayList<LanguageDeterminationRecord> getLanguageRecords() {
		return mLanguageRecords;
	}

	/** @return The mMagicSpellRecords. */
	public static ArrayList<MagicSpellDeterminationRecord> getMagicSpellRecords() {
		return mMagicSpellRecords;
	}

	/** @return The mSkillRecords. */
	public static ArrayList<SkillDeterminationRecord> getSkillRecords() {
		return mSkillRecords;
	}

	/** @return The mWeaponRecords. */
	public static ArrayList<WeaponProficiencyDeterminationRecord> getWeaponRecords() {
		return mWeaponRecords;
	}

	/** @return The mTeachersRecords. */
	public static ArrayList<TeacherDeterminationRecord> getSkillsTeachersRecords() {
		return mSkillsTeachersRecords;
	}

	/** @return The mTeachersRecords. */
	public static ArrayList<TeacherDeterminationRecord> getTheifTeachersRecords() {
		return mThiefTeachersRecords;
	}

	/** @return The mTeachersRecords. */
	public static ArrayList<TeacherDeterminationRecord> getWeaponsTeachersRecords() {
		return mWeaponsTeachersRecords;
	}

	/** @return The mTeachersRecords. */
	public static ArrayList<String> getWeaponsTeachersNames() {
		ArrayList<String> teacherNames = new ArrayList<>();

		for (TeacherDeterminationRecord record : mWeaponsTeachersRecords) {
			teacherNames.add(record.getTeacher());
		}

		return teacherNames;
	}

	/** @return The mTeachersRecords. */
	public static TeacherDeterminationRecord getWeaponsTeacherRecord(String name) {
		for (TeacherDeterminationRecord record : mWeaponsTeachersRecords) {
			if (record.getTeacher().equals(name)) {
				return record;
			}
		}
		// This shouldn't be able to happen since the parameter name is passed in from a known list
		return null;
	}

	/** @return The mTeachersRecords. */
	public static ArrayList<TeacherDeterminationRecord> getTeachersRecords() {
		ArrayList<TeacherDeterminationRecord> list = new ArrayList<>();
		list.addAll(mSkillsTeachersRecords);
		list.addAll(mThiefTeachersRecords);
		list.addAll(mWeaponsTeachersRecords);
		return list;
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
						if (AttributeDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							AttributeDeterminationRecord record = new AttributeDeterminationRecord();
							tokenizer = record.readValues(br);
							addAttribRecord(record);
						} else if (LanguageDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							LanguageDeterminationRecord record = new LanguageDeterminationRecord();
							tokenizer = record.readValues(br);
							addLanguageRecord(record);
						} else if (MagicSpellDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							MagicSpellDeterminationRecord record = new MagicSpellDeterminationRecord();
							tokenizer = record.readValues(br);
							addMagicSpellRecord(record);
						} else if (SkillDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							SkillDeterminationRecord record = new SkillDeterminationRecord();
							tokenizer = record.readValues(br);
							addSkillRecord(record);
						} else if (WeaponProficiencyDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							WeaponProficiencyDeterminationRecord record = new WeaponProficiencyDeterminationRecord();
							tokenizer = record.readValues(br);
							addWeaponRecord(record);
						} else if (TeacherDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							TeacherDeterminationRecord record = new TeacherDeterminationRecord();
							tokenizer = record.readValues(br);
							addTeacherRecord(record);
						}
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

		for (AttributeDeterminationRecord record : mAttribRecords) {
			record.saveValues(br);
		}
		for (LanguageDeterminationRecord record : mLanguageRecords) {
			record.saveValues(br);
		}
		for (MagicSpellDeterminationRecord record : mMagicSpellRecords) {
			record.saveValues(br);
		}
		for (SkillDeterminationRecord record : mSkillRecords) {
			record.saveValues(br);
		}
		for (WeaponProficiencyDeterminationRecord record : mWeaponRecords) {
			record.saveValues(br);
		}
		for (TeacherDeterminationRecord record : mSkillsTeachersRecords) {
			record.saveValues(br);
		}
		for (TeacherDeterminationRecord record : mThiefTeachersRecords) {
			record.saveValues(br);
		}
		for (TeacherDeterminationRecord record : mWeaponsTeachersRecords) {
			record.saveValues(br);
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		// not used here
	}

}
