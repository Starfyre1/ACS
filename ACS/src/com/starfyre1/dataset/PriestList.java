/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataModel.PriestRecord;
import com.starfyre1.dataset.classes.priests.Chauntil;
import com.starfyre1.dataset.classes.priests.Graun;
import com.starfyre1.dataset.classes.priests.Lorrell;
import com.starfyre1.dataset.classes.priests.MistressNight;
import com.starfyre1.dataset.classes.priests.Narese;
import com.starfyre1.dataset.classes.priests.Narius;
import com.starfyre1.dataset.classes.priests.Orn;
import com.starfyre1.dataset.classes.priests.Ryelle;
import com.starfyre1.dataset.classes.priests.Sarn;
import com.starfyre1.dataset.classes.priests.Pelon;
import com.starfyre1.dataset.classes.priests.Talon;
import com.starfyre1.dataset.classes.priests.Tarn;
import com.starfyre1.dataset.classes.priests.Tarot;
import com.starfyre1.dataset.classes.priests.Thaer;
import com.starfyre1.dataset.classes.priests.Adon;
import com.starfyre1.dataset.classes.priests.Wynd;

import java.util.ArrayList;

public class PriestList extends ClassList {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	private String	mType;			// null, priest, Mage
	//	private String	mName;
	//	private int		mArmor;			// 0 = none, 1 = light, 2 = medium, 3 = heavy
	//	private int[]	mMinimumStats;	// null means no minimum stats

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	protected ArrayList<ClassesRecord> mPriestsList = new ArrayList<>();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public PriestList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void generateList() {
		if (mPriestsList.isEmpty()) {
			mPriestsList.add(new PriestRecord(new Chauntil(), PRIEST, CHAUNTIL, 3, null));
			mPriestsList.add(new PriestRecord(new Graun(), PRIEST, GRAUN, 3, null));
			mPriestsList.add(new PriestRecord(new Lorrell(), PRIEST, LORRELL, 3, null));
			mPriestsList.add(new PriestRecord(new MistressNight(), PRIEST, MISTRESS_NIGHT, 3, null));
			mPriestsList.add(new PriestRecord(new Narese(), PRIEST, NARESE, 3, null));
			mPriestsList.add(new PriestRecord(new Narius(), PRIEST, NARIUS, 3, null));
			mPriestsList.add(new PriestRecord(new Orn(), PRIEST, ORN, 3, null));
			mPriestsList.add(new PriestRecord(new Ryelle(), PRIEST, RYSH, 3, null));
			mPriestsList.add(new PriestRecord(new Sarn(), PRIEST, SARN, 3, null));
			mPriestsList.add(new PriestRecord(new Pelon(), PRIEST, SAUTRIAN, 3, null));
			mPriestsList.add(new PriestRecord(new Talon(), PRIEST, TALON, 3, null));
			mPriestsList.add(new PriestRecord(new Tarn(), PRIEST, TARN, 3, null));
			mPriestsList.add(new PriestRecord(new Tarot(), PRIEST, TAROT, 3, null));
			mPriestsList.add(new PriestRecord(new Thaer(), PRIEST, THAER, 3, null));
			mPriestsList.add(new PriestRecord(new Adon(), PRIEST, THANTOS, 3, null));
			mPriestsList.add(new PriestRecord(new Wynd(), PRIEST, WYND, 3, null));
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static boolean isPriest(String characterClass) {
		return switch (characterClass) {
			case PRIEST:
			case THANTOS:
			case SAUTRIAN:
			case NARIUS:
			case THAER:
			case TARN:
			case LORRELL:
			case CHAUNTIL:
			case TAROT:
			case TALON:
			case WYND:
			case MISTRESS_NIGHT:
			case RYSH:
			case GRAUN:
			case NARESE:
			case ORN:
			case SARN: {

				yield true;
			}
			default:
				yield false;
		};
	}

	public ClassesRecord getRecord(String name) {
		for (ClassesRecord element : mPriestsList) {
			if (element.getName().equals(name)) {
				return element;
			}
		}

		return null;
	}

	public ArrayList<String> getPriestsNamesList() {
		ArrayList<String> list = new ArrayList<>(mPriestsList.size());

		for (Object element : mPriestsList) {
			list.add(((PriestRecord) element).getName());

		}

		return list;
	}

	public ArrayList<String> getPriestsGroupsList() {
		ArrayList<String> list = new ArrayList<>();

		for (ClassesRecord element : mPriestsList) {
			if (list.isEmpty() || !list.contains(element.getGroup())) {
				if (element.getGroup() != null) {
					list.add(element.getGroup());
				}
			}
		}

		return list;
	}

	/** @return The priestList. */
	@Override
	public ArrayList<ClassesRecord> getRecordsList() {
		return mPriestsList;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
