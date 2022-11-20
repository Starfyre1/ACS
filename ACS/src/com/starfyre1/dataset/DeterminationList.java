/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataModel.determination.DeterminationRecord;
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
import com.starfyre1.interfaces.CampaignDateListener;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class DeterminationList implements Savable, CampaignDateListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String										FILE_SECTION_START_KEY		= "DETERMINATION_SECTION_START";	//$NON-NLS-1$
	public static final String										FILE_SECTION_END_KEY		= "DETERMINATION_SECTION_END";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static CharacterSheet									mOwner;

	private static ArrayList<AttributeDeterminationRecord>			mAttribRecords				= new ArrayList<>(16);
	private static ArrayList<LanguageDeterminationRecord>			mLanguageRecords			= new ArrayList<>(16);
	private static ArrayList<MagicSpellDeterminationRecord>			mMagicSpellRecords			= new ArrayList<>(32);
	private static ArrayList<SkillDeterminationRecord>				mSkillRecords				= new ArrayList<>(16);
	private static ArrayList<WeaponProficiencyDeterminationRecord>	mWeaponRecords				= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mWeaponsTeachersRecords		= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mSkillsTeachersRecords		= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mThiefTeachersRecords		= new ArrayList<>(16);

	private static ArrayList<AttributeDeterminationRecord>			mAttribRecordsCompleted		= new ArrayList<>(16);
	private static ArrayList<LanguageDeterminationRecord>			mLanguageRecordsCompleted	= new ArrayList<>(16);
	private static ArrayList<MagicSpellDeterminationRecord>			mMagicSpellRecordsCompleted	= new ArrayList<>(512);
	private static ArrayList<SkillDeterminationRecord>				mSkillRecordsCompleted		= new ArrayList<>(16);
	private static ArrayList<WeaponProficiencyDeterminationRecord>	mWeaponRecordsCompleted		= new ArrayList<>(16);

	private static ArrayList<DeterminationRecord>					mCompletedRecords			= new ArrayList<>(16);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link DeterminationList}.
	 */
	public DeterminationList(CharacterSheet owner) {
		mOwner = owner;
		owner.addCampaignDateListener(this);
	}

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

	public static void addAttribRecordCompleted(AttributeDeterminationRecord record) {
		mAttribRecordsCompleted.add(record);
	}

	public static void addLanguageRecordCompleted(LanguageDeterminationRecord record) {
		mLanguageRecordsCompleted.add(record);
	}

	public static void addMagicSpellRecordCompleted(MagicSpellDeterminationRecord record) {
		mMagicSpellRecordsCompleted.add(record);
	}

	public static void addSkillRecordCompleted(SkillDeterminationRecord record) {
		mSkillRecordsCompleted.add(record);
	}

	public static void addWeaponRecordCompleted(WeaponProficiencyDeterminationRecord record) {
		mWeaponRecordsCompleted.add(record);
	}

	public static void moveCompletedRecords() {
		for (DeterminationRecord record : mCompletedRecords) {
			if (record instanceof AttributeDeterminationRecord) {
				mAttribRecords.remove(record);
				mAttribRecordsCompleted.add((AttributeDeterminationRecord) record);
			} else if (record instanceof LanguageDeterminationRecord) {
				mLanguageRecords.remove(record);
				mLanguageRecordsCompleted.add((LanguageDeterminationRecord) record);
			} else if (record instanceof MagicSpellDeterminationRecord) {
				mMagicSpellRecords.remove(record);
				mMagicSpellRecordsCompleted.add((MagicSpellDeterminationRecord) record);
			} else if (record instanceof SkillDeterminationRecord) {
				mSkillRecords.remove(record);
				mSkillRecordsCompleted.add((SkillDeterminationRecord) record);
			} else if (record instanceof WeaponProficiencyDeterminationRecord) {
				mWeaponRecords.remove(record);
				mWeaponRecordsCompleted.add((WeaponProficiencyDeterminationRecord) record);
			}
		}
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
		mMagicSpellRecords = new ArrayList<>(32);
		mSkillRecords = new ArrayList<>(16);
		mWeaponRecords = new ArrayList<>(16);
		mWeaponsTeachersRecords = new ArrayList<>(16);
		mSkillsTeachersRecords = new ArrayList<>(16);
		mThiefTeachersRecords = new ArrayList<>(16);

		mAttribRecordsCompleted = new ArrayList<>(16);
		mLanguageRecordsCompleted = new ArrayList<>(16);
		mMagicSpellRecordsCompleted = new ArrayList<>(512);
		mSkillRecordsCompleted = new ArrayList<>(16);
		mWeaponRecordsCompleted = new ArrayList<>(16);
	}

	@Override
	public void dateUpdated(String date) {
		// DW should move completed records to a separate ArrayList
		for (AttributeDeterminationRecord record : mAttribRecords) {
			if (!record.getEndDate().isBlank()) {
				continue;
			}
			int numOfWeeks = weekUp(record, date);
			if (numOfWeeks > 0) {
				updateRecord(record, date, numOfWeeks);
			}
			System.out.println(record.toString());
		}
		for (LanguageDeterminationRecord record : mLanguageRecords) {
			if (!record.getEndDate().isBlank()) {
				continue;
			}
			int numOfWeeks = weekUp(record, date);
			if (numOfWeeks > 0) {
				updateRecord(record, date, numOfWeeks);
			}
			System.out.println(record.toString());
		}
		for (MagicSpellDeterminationRecord record : mMagicSpellRecords) {
			if (!record.getEndDate().isBlank()) {
				continue;
			}
			int numOfWeeks = weekUp(record, date);
			if (numOfWeeks > 0) {
				updateRecord(record, date, numOfWeeks);
			}
			System.out.println(record.toString());
		}
		for (SkillDeterminationRecord record : mSkillRecords) {
			if (!record.getEndDate().isBlank()) {
				continue;
			}
			int numOfWeeks = weekUp(record, date);
			if (numOfWeeks > 0) {
				updateRecord(record, date, numOfWeeks);
			}
			System.out.println(record.toString());
		}
		for (WeaponProficiencyDeterminationRecord record : mWeaponRecords) {
			if (!record.getEndDate().isBlank()) {
				continue;
			}
			int numOfWeeks = weekUp(record, date);
			if (numOfWeeks > 0) {
				updateRecord(record, date, numOfWeeks);
			}
			System.out.println(record.toString());
		}

		moveCompletedRecords();
		mOwner.getDeterminationPointsDisplay().addRecords(true);
		mOwner.getDeterminationPointsDisplay().updateValues();
	}

	private void updateRecord(DeterminationRecord record, String date, int numOfWeeks) {
		record.setLastUpdate(date);
		for (int i = 0; i < numOfWeeks; i++) {
			record.setDPTotalSpent(record.getDPTotalSpent() + record.getDPPerWeek());
			// The UI is updated from calling method - dateUpdated(String date)
			if (record.getDPTotalSpent() >= record.getDPCost()) {
				record.setEndDate(date);
				record.setDPPerWeek(0);
				determinationComplete(record);
				mCompletedRecords.add(record);
				break;
			}
			if (record.getDPCost() - (record.getDPTotalSpent() + record.getDPPerWeek()) < 0) {
				int extraDPPoints = record.getDPPerWeek() - (record.getDPCost() - record.getDPTotalSpent());
				record.setDPPerWeek(record.getDPCost() - record.getDPTotalSpent());
				// DW Need to allow the player to assign extra points before going on to the next week.
				JOptionPane.showMessageDialog(ACS.getInstance().getCharacterSheet().getFrame(), "You have " + extraDPPoints + " unused determination points\n\n" + record.getName() + " had more points assigned to it\nthan it needed for it to complete.", "Unused Determination Points", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		}
	}

	private void determinationComplete(DeterminationRecord record) {
		record.setSuccessful(record.successRoll());
		// update affected areas
	}

	private int weekUp(DeterminationRecord record, String date) {
		int currentIndex = CampaignDateChooser.getCampaignDateIndex(date);
		String offsetDate = record.getLastUpdate().equals("null") ? record.getStartDate() : record.getLastUpdate();
		int startIndex = CampaignDateChooser.getCampaignDateIndex(offsetDate);
		if (currentIndex - startIndex > 6) {
			return (currentIndex - startIndex) / 7;
		}
		return 0;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static int getCompletedWeaponBonus(String weapon) {
		int total = 0;
		for (WeaponProficiencyDeterminationRecord record : mWeaponRecords) {
			if (weapon.equals(record.getWeapon()) && record.isSuccessful()) {
				total += record.getBonus();
			}
		}
		return total;
	}

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

	/** @return The mAttribRecords. */
	public static ArrayList<AttributeDeterminationRecord> getAttribRecordsCompleted() {
		return mAttribRecordsCompleted;
	}

	/** @return The mLanguageRecords. */
	public static ArrayList<LanguageDeterminationRecord> getLanguageRecordsCompleted() {
		return mLanguageRecordsCompleted;
	}

	/** @return The mMagicSpellRecords. */
	public static ArrayList<MagicSpellDeterminationRecord> getMagicSpellRecordsCompleted() {
		return mMagicSpellRecordsCompleted;
	}

	/** @return The mSkillRecords. */
	public static ArrayList<SkillDeterminationRecord> getSkillRecordsCompleted() {
		return mSkillRecordsCompleted;
	}

	/** @return The mWeaponRecords. */
	public static ArrayList<WeaponProficiencyDeterminationRecord> getWeaponRecordsCompleted() {
		return mWeaponRecordsCompleted;
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
	public static ArrayList<String> getSkillsTeachersNames() {
		ArrayList<String> teacherNames = new ArrayList<>();

		for (TeacherDeterminationRecord record : mSkillsTeachersRecords) {
			teacherNames.add(record.getTeacher());
		}

		return teacherNames;
	}

	/** @return The mTeachersRecords. */
	//DW need to use ID in case teachers with same name
	public static TeacherDeterminationRecord getSkillsTeacherRecord(String name) {
		for (TeacherDeterminationRecord record : mSkillsTeachersRecords) {
			if (record.getTeacher().equals(name)) {
				return record;
			}
		}
		for (TeacherDeterminationRecord record : mThiefTeachersRecords) {
			if (record.getTeacher().equals(name)) {
				return record;
			}
		}
		// This shouldn't be able to happen since the parameter name is passed in from a known list
		return null;
	}

	/** @return The mTeachersRecords. */
	//DW need to use ID in case teachers with same name
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
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, " ", false); //$NON-NLS-1$
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						if (AttributeDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							AttributeDeterminationRecord record = new AttributeDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getAttribute() >= 0) {
								if (record.getEndDate().isBlank()) {
									addAttribRecord(record);
								} else {
									addAttribRecordCompleted(record);
								}
							}
						} else if (LanguageDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							LanguageDeterminationRecord record = new LanguageDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getLanguage() != TKStringHelpers.EMPTY_STRING) {
								if (record.getEndDate().isBlank()) {
									addLanguageRecord(record);
								} else {
									addLanguageRecordCompleted(record);
								}
							}
						} else if (MagicSpellDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							MagicSpellDeterminationRecord record = new MagicSpellDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getSpell() != TKStringHelpers.EMPTY_STRING) {
								if (record.getEndDate().isBlank()) {
									addMagicSpellRecord(record);
								} else {
									addMagicSpellRecordCompleted(record);
								}
							}
						} else if (SkillDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							SkillDeterminationRecord record = new SkillDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getSkill() != TKStringHelpers.EMPTY_STRING) {
								if (record.getEndDate().isBlank()) {
									addSkillRecord(record);
								} else {
									addSkillRecordCompleted(record);
								}
							}
						} else if (WeaponProficiencyDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							WeaponProficiencyDeterminationRecord record = new WeaponProficiencyDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getWeapon() != TKStringHelpers.EMPTY_STRING) {
								if (record.getEndDate().isBlank()) {
									addWeaponRecord(record);
								} else {
									addWeaponRecordCompleted(record);
								}
							}
						} else if (TeacherDeterminationRecord.FILE_SECTION_START_KEY.equals(key)) {
							TeacherDeterminationRecord record = new TeacherDeterminationRecord();
							tokenizer = record.readValues(br);
							if (record.getTeacher() != TKStringHelpers.EMPTY_STRING) {
								addTeacherRecord(record);
							}
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
		for (AttributeDeterminationRecord record : mAttribRecordsCompleted) {
			record.saveValues(br);
		}
		for (LanguageDeterminationRecord record : mLanguageRecordsCompleted) {
			record.saveValues(br);
		}
		for (MagicSpellDeterminationRecord record : mMagicSpellRecordsCompleted) {
			record.saveValues(br);
		}
		for (SkillDeterminationRecord record : mSkillRecordsCompleted) {
			record.saveValues(br);
		}
		for (WeaponProficiencyDeterminationRecord record : mWeaponRecordsCompleted) {
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
