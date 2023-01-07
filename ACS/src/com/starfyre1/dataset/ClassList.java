/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataset.classes.Dwarrow;
import com.starfyre1.dataset.classes.Dwarves;
import com.starfyre1.dataset.classes.Thief;
import com.starfyre1.dataset.classes.common.BaseClass;
import com.starfyre1.dataset.classes.elves.Sailor;
import com.starfyre1.dataset.classes.elves.Sithrian;
import com.starfyre1.dataset.classes.elves.Tellorian;
import com.starfyre1.dataset.classes.elves.Tsiri;
import com.starfyre1.dataset.classes.warriors.Ranger;
import com.starfyre1.dataset.classes.warriors.Warrior;

import java.util.ArrayList;

public class ClassList {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	private String	mType;			// null, priest, Mage
	//	private String	mName;
	//	private int		mArmor;			// 0 = none, 1 = light, 2 = medium, 3 = heavy
	//	private int[]	mMinimumStats;	// null means no minimum stats

	public static final String					WARRIOR				= "Warrior";			//$NON-NLS-1$
	public static final String					RANGER				= "Ranger";				//$NON-NLS-1$
	public static final String					BARBARIAN			= "Barbarian";			//$NON-NLS-1$

	public static final String					MAGE				= "Mage";				//$NON-NLS-1$
	public static final String					SHADOW_MAGIC		= "Shadow Magic";		//$NON-NLS-1$
	public static final String					NECROMANCER			= "Necromancer";		//$NON-NLS-1$
	public static final String					WATER_ELEMENTALIST	= "Water Elementalist";	//$NON-NLS-1$
	public static final String					FIRE_ELEMENTALIST	= "Fire Elementalist";	//$NON-NLS-1$
	public static final String					AIR_ELEMENTALIST	= "Air Elementalist";	//$NON-NLS-1$
	public static final String					EARTH_ELEMENTALIST	= "Earth Elementalist";	//$NON-NLS-1$
	public static final String					ILLUSION			= "Illusion";			//$NON-NLS-1$
	public static final String					NATURAL_LORE		= "Natural Lore";		//$NON-NLS-1$
	public static final String					ARCANE_LORE			= "Arcane Lore";		//$NON-NLS-1$
	public static final String					CONTROL				= "Control";			//$NON-NLS-1$

	public static final String					PRIEST				= "Priest";				//$NON-NLS-1$
	public static final String					SARN				= "Sarn";				//$NON-NLS-1$
	public static final String					ORN					= "Orn";				//$NON-NLS-1$
	public static final String					NARESE				= "Narese";				//$NON-NLS-1$
	public static final String					GRAUN				= "Graun";				//$NON-NLS-1$
	public static final String					RYSH				= "Rysh";				//$NON-NLS-1$
	public static final String					MISTRESS_NIGHT		= "Mistress Night";		//$NON-NLS-1$
	public static final String					WYND				= "Wynd";				//$NON-NLS-1$
	public static final String					TALON				= "Talon";				//$NON-NLS-1$
	public static final String					TAROT				= "Tarot";				//$NON-NLS-1$
	public static final String					CHAUNTIL			= "Chauntil";			//$NON-NLS-1$
	public static final String					LORRELL				= "Lorrell";			//$NON-NLS-1$
	public static final String					TARN				= "Tarn";				//$NON-NLS-1$
	public static final String					THAER				= "Thaer";				//$NON-NLS-1$
	public static final String					NARIUS				= "Narius";				//$NON-NLS-1$
	public static final String					SAUTRIAN			= "Sautrian";			//$NON-NLS-1$
	public static final String					THANTOS				= "Thantos";			//$NON-NLS-1$

	public static final String					ELVEN				= "Elven";				//$NON-NLS-1$
	public static final String					TSIRI				= "Tsiri";				//$NON-NLS-1$
	public static final String					TELLORIAN			= "Tellorian";			//$NON-NLS-1$
	public static final String					SITHRIAN			= "Sithrian";			//$NON-NLS-1$
	public static final String					SAILOR				= "Sailor";				//$NON-NLS-1$

	//	public static final String					HALF_ELVEN			= "Half Elven";			//$NON-NLS-1$
	//	public static final String					HUMAN				= "Human";				//$NON-NLS-1$
	public static final String					DWARROW				= "Dwarrow";			//$NON-NLS-1$
	public static final String					DWARVEN				= "Dwarven";			//$NON-NLS-1$
	public static final String					GNOME				= "Gnome";				//$NON-NLS-1$
	public static final String					THIEF				= "Thief";				//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	protected static ArrayList<ClassesRecord>	mClassesList		= new ArrayList<>();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public ClassList() {
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void generateList() {
		if (mClassesList.isEmpty()) {
			mClassesList.add(new ClassesRecord(new Ranger(), WARRIOR, RANGER, 3));
			mClassesList.add(new ClassesRecord(new Warrior(), WARRIOR, WARRIOR, 3));

			mClassesList.add(new ClassesRecord(new Sailor(), ELVEN, SAILOR, 3));
			mClassesList.add(new ClassesRecord(new Sithrian(), ELVEN, SITHRIAN, 3));
			mClassesList.add(new ClassesRecord(new Tellorian(), ELVEN, TELLORIAN, 3));
			mClassesList.add(new ClassesRecord(new Tsiri(), ELVEN, TSIRI, 3));

			mClassesList.add(new ClassesRecord(new Thief(), null, THIEF, 3));
			//			mClassesList.add(new ClassesRecord(new Human(), null, HUMAN, 3));
			mClassesList.add(new ClassesRecord(new Dwarves(), null, DWARVEN, 3));
			mClassesList.add(new ClassesRecord(new Dwarrow(), null, DWARROW, 3));
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	public ArrayList<String> getClassesNamesList() {
		ArrayList<String> list = new ArrayList<>(mClassesList.size());

		for (Object element : mClassesList) {
			list.add(((ClassesRecord) element).getName());

		}

		return list;
	}

	public ArrayList<String> getClassGroupsList() {
		ArrayList<String> list = new ArrayList<>();

		for (ClassesRecord element : mClassesList) {

			if (list.isEmpty() || !list.contains(element.getGroup())) {
				if (element.getGroup() != null) {
					list.add(element.getGroup());
				}
			}
		}

		return list;
	}

	/** @return The classesList. */
	public ArrayList<ClassesRecord> getRecordsList() {
		return mClassesList;
	}

	public static BaseClass getCharacterClass(String class1) {
		for (ClassesRecord element : mClassesList) {
			if (element.getName().equals(class1)) {
				return element.getBaseClass();
			}
		}
		return null;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
