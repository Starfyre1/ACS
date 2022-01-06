/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

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
	public static final String										FILE_SECTTION_START_KEY	= "DETERMINATION_SECTTION_START";	//$NON-NLS-1$
	public static final String										FILE_SECTTION_END_KEY	= "DETERMINATION_SECTTION_END";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static ArrayList<AttributeDeterminationRecord>			mAttribRecords			= new ArrayList<>(16);
	private static ArrayList<LanguageDeterminationRecord>			mLanguageRecords		= new ArrayList<>(16);
	private static ArrayList<MagicSpellDeterminationRecord>			mMagicSpellRecords		= new ArrayList<>(64);
	private static ArrayList<SkillDeterminationRecord>				mSkillRecords			= new ArrayList<>(16);
	private static ArrayList<WeaponProficiencyDeterminationRecord>	mWeaponRecords			= new ArrayList<>(16);
	private static ArrayList<TeacherDeterminationRecord>			mTeachersRecords		= new ArrayList<>(16);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void addAttribRecord(AttributeDeterminationRecord record) {
		mAttribRecords.add(record);
	}

	public void addLanguageRecord(LanguageDeterminationRecord record) {
		mLanguageRecords.add(record);
	}

	public void addMagicSpellRecord(MagicSpellDeterminationRecord record) {
		mMagicSpellRecords.add(record);
	}

	public void addSkillRecord(SkillDeterminationRecord record) {
		mSkillRecords.add(record);
	}

	public void addWeaponRecord(WeaponProficiencyDeterminationRecord record) {
		mWeaponRecords.add(record);
	}

	public void addTeacherRecord(TeacherDeterminationRecord record) {
		mTeachersRecords.add(record);
	}

	public void clearRecords() {
		mAttribRecords = new ArrayList<>(16);
		mLanguageRecords = new ArrayList<>(16);
		mMagicSpellRecords = new ArrayList<>(64);
		mSkillRecords = new ArrayList<>(16);
		mWeaponRecords = new ArrayList<>(16);
		mTeachersRecords = new ArrayList<>(16);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

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
		for (TeacherDeterminationRecord record : mTeachersRecords) {
			record.saveValues(br);
		}

		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	// DW need to add magic info to Armor
	@Override
	public void setKeyValuePair(String key, Object obj) {
		// DW to do
	}

}
