/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataModel.MageRecord;
import com.starfyre1.dataset.classes.mages.ArcaneLore;
import com.starfyre1.dataset.classes.mages.Control;
import com.starfyre1.dataset.classes.mages.Illusion;
import com.starfyre1.dataset.classes.mages.NaturalLore;
import com.starfyre1.dataset.classes.mages.Necromancer;
import com.starfyre1.dataset.classes.mages.ShadowMagic;
import com.starfyre1.dataset.classes.mages.elemental.Air;
import com.starfyre1.dataset.classes.mages.elemental.Earth;
import com.starfyre1.dataset.classes.mages.elemental.Fire;
import com.starfyre1.dataset.classes.mages.elemental.Water;

import java.util.ArrayList;

public class MageList extends ClassList {

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
	protected ArrayList<ClassesRecord> mMagesList = new ArrayList<>();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MageList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public void generateList() {
		if (mMagesList.isEmpty()) {
			mMagesList.add(new MageRecord(new ArcaneLore(), MAGE, ARCANE_LORE, 3));
			mMagesList.add(new MageRecord(new Control(), MAGE, CONTROL, 3));
			mMagesList.add(new MageRecord(new Air(), MAGE, AIR_ELEMENTALIST, 3));
			mMagesList.add(new MageRecord(new Earth(), MAGE, EARTH_ELEMENTALIST, 3));
			mMagesList.add(new MageRecord(new Fire(), MAGE, FIRE_ELEMENTALIST, 3));
			mMagesList.add(new MageRecord(new Water(), MAGE, WATER_ELEMENTALIST, 3));
			mMagesList.add(new MageRecord(new Illusion(), MAGE, ILLUSION, 3));
			mMagesList.add(new MageRecord(new NaturalLore(), MAGE, NATURAL_LORE, 3));
			mMagesList.add(new MageRecord(new Necromancer(), MAGE, NECROMANCER, 3));
			mMagesList.add(new MageRecord(new ShadowMagic(), MAGE, SHADOW_MAGIC, 3));
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static boolean isMage(String characterClass) {
		return switch (characterClass) {
			case MAGE:
			case CONTROL:
			case ARCANE_LORE:
			case NATURAL_LORE:
			case ILLUSION:
			case EARTH_ELEMENTALIST:
			case AIR_ELEMENTALIST:
			case FIRE_ELEMENTALIST:
			case WATER_ELEMENTALIST:
			case NECROMANCER:
			case SHADOW_MAGIC: {

				yield true;
			}
			default:
				yield false;
		};
	}

	public ClassesRecord getRecord(String name) {
		for (ClassesRecord element : mMagesList) {
			if (element.getName().equals(name)) {
				return element;
			}
		}

		return null;
	}

	public ArrayList<String> getMagesNamesList() {
		ArrayList<String> list = new ArrayList<>(mMagesList.size());

		for (Object element : mMagesList) {
			list.add(((MageRecord) element).getName());
		}

		return list;
	}

	public ArrayList<String> getMagesGroupsList() {
		ArrayList<String> list = new ArrayList<>();

		for (ClassesRecord element : mMagesList) {
			if (list.isEmpty() || !list.contains(element.getGroup())) {
				if (element.getGroup() != null) {
					list.add(element.getGroup());
				}
			}
		}

		return list;
	}

	/** @return The magesList. */
	@Override
	public ArrayList<ClassesRecord> getRecordsList() {
		return mMagesList;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
