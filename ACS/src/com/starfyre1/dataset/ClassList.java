/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataset.classes.Dwarrow;
import com.starfyre1.dataset.classes.Dwarves;
import com.starfyre1.dataset.classes.Human;
import com.starfyre1.dataset.classes.Thief;
import com.starfyre1.dataset.classes.elves.Sailor;
import com.starfyre1.dataset.classes.elves.Sithrian;
import com.starfyre1.dataset.classes.elves.Tellorian;
import com.starfyre1.dataset.classes.elves.Tsiri;
import com.starfyre1.dataset.classes.warriors.Ranger;
import com.starfyre1.dataset.classes.warriors.Warrior;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

import java.util.ArrayList;

public class ClassList {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	private String	mType;			// null, priest, Mage
	//	private String	mName;
	//	private int		mArmor;			// 0 = none, 1 = light, 2 = medium, 3 = heavy
	//	private int[]	mMinimumStats;	// null means no minimum stats

	public static final String					WARRIOR						= "Warrior";																																	//$NON-NLS-1$
	public static final String					RANGER						= "Ranger";																																		//$NON-NLS-1$
	public static final String					BARBARIAN					= "Barbarian";																																	//$NON-NLS-1$

	public static final String					MAGE						= "Mage";																																		//$NON-NLS-1$
	public static final String					SHADOW_MAGIC				= "Shadow Magic";																																//$NON-NLS-1$
	public static final String					NECROMANCER					= "Necromancer";																																//$NON-NLS-1$
	public static final String					WATER_ELEMENTALIST			= "Water Elementalist";																															//$NON-NLS-1$
	public static final String					FIRE_ELEMENTALIST			= "Fire Elementalist";																															//$NON-NLS-1$
	public static final String					AIR_ELEMENTALIST			= "Air Elementalist";																															//$NON-NLS-1$
	public static final String					EARTH_ELEMENTALIST			= "Earth Elementalist";																															//$NON-NLS-1$
	public static final String					ILLUSION					= "Illusion";																																	//$NON-NLS-1$
	public static final String					NATURAL_LORE				= "Natural Lore";																																//$NON-NLS-1$
	public static final String					ARCANE_LORE					= "Arcane Lore";																																//$NON-NLS-1$
	public static final String					CONTROL						= "Control";																																	//$NON-NLS-1$

	public static final String					PRIEST						= "Priest";																																		//$NON-NLS-1$
	public static final String					SARN						= "Sarn";																																		//$NON-NLS-1$
	public static final String					ORN							= "Orn";																																		//$NON-NLS-1$
	public static final String					NARESE						= "Narese";																																		//$NON-NLS-1$
	public static final String					GRAUN						= "Graun";																																		//$NON-NLS-1$
	public static final String					RYSH						= "Rysh";																																		//$NON-NLS-1$
	public static final String					MISTRESS_NIGHT				= "Mistress Night";																																//$NON-NLS-1$
	public static final String					WYND						= "Wynd";																																		//$NON-NLS-1$
	public static final String					TALON						= "Talon";																																		//$NON-NLS-1$
	public static final String					TAROT						= "Tarot";																																		//$NON-NLS-1$
	public static final String					CHAUNTIL					= "Chauntil";																																	//$NON-NLS-1$
	public static final String					LORRELL						= "Lorrell";																																	//$NON-NLS-1$
	public static final String					TARN						= "Tarn";																																		//$NON-NLS-1$
	public static final String					THAER						= "Thaer";																																		//$NON-NLS-1$
	public static final String					NARIUS						= "Narius";																																		//$NON-NLS-1$
	public static final String					SAUTRIAN					= "Sautrian";																																	//$NON-NLS-1$
	public static final String					THANTOS						= "Thantos";																																	//$NON-NLS-1$

	public static final String					ELVEN						= "Elven";																																		//$NON-NLS-1$
	public static final String					TSIRI						= "Tsiri";																																		//$NON-NLS-1$
	public static final String					TELLORIAN					= "Tellorian";																																	//$NON-NLS-1$
	public static final String					SITHRIAN					= "Sithrian";																																	//$NON-NLS-1$
	public static final String					SAILOR						= "Sailor";																																		//$NON-NLS-1$

	public static final String					HALF_ELVEN					= "Half Elven";																																	//$NON-NLS-1$
	public static final String					HUMAN						= "Human";																																		//$NON-NLS-1$
	public static final String					DWARROW						= "Dwarrow";																																	//$NON-NLS-1$
	public static final String					DWARVEN						= "Dwarven";																																	//$NON-NLS-1$
	public static final String					GNOME						= "Gnome";																																		//$NON-NLS-1$
	public static final String					THIEF						= "Thief";																																		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	protected static ArrayList<ClassesRecord>	mClassesList				= new ArrayList<>();

	private boolean								mWarriorInnateSkills[]		= { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true };
	private boolean								mThiefInnateSkills[]		= { false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, false, false };
	private boolean								mRangerInnateSkills[]		= { false, true, true, false, false, false, false, false, false, false, true, true, false, false, false, true, false, false, true, false };

	private boolean								mTsiriInnateSkills[]		= { true, false, false, true, false, false, true, false, false, false, true, true, true, false, false, true, true, true, false, false };
	private boolean								mTellorianInnateSkills[]	= { true, false, false, true, true, false, false, false, true, false, true, true, true, false, false, false, false, false, true, false };
	private boolean								mSithrianInnateSkills[]		= { true, false, true, true, true, false, false, false, false, false, true, true, true, false, false, false, false, false, true, false };
	private boolean								mSailorInnateSkills[]		= { true, false, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, true, false };
	private boolean								mDwarvenInnateSkills[]		= { false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false };
	private boolean								mDwarrowInnateSkills[]		= { true, false, true, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false };

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
			mClassesList.add(new ClassesRecord(new Ranger(), WARRIOR, RANGER, 3, new int[] { 12, 12, 11, 12, 12, 12, 10, 0, 10 }));
			mClassesList.add(new ClassesRecord(new Warrior(), WARRIOR, WARRIOR, 3, null));

			mClassesList.add(new ClassesRecord(new Sailor(), ELVEN, SAILOR, 3, new int[] { 10, 0, 11, 10, 12, 12, 7, 11, 0 }));
			mClassesList.add(new ClassesRecord(new Sithrian(), ELVEN, SITHRIAN, 3, new int[] { 10, 12, 12, 13, 14, 13, 7, 10, 11 }));
			mClassesList.add(new ClassesRecord(new Tellorian(), ELVEN, TELLORIAN, 3, new int[] { 12, 0, 12, 13, 13, 0, 10, 11, 11 }));
			mClassesList.add(new ClassesRecord(new Tsiri(), ELVEN, TSIRI, 3, new int[] { 12, 10, 13, 12, 14, 13, 10, 0, 12 }));

			mClassesList.add(new ClassesRecord(new Thief(), null, THIEF, 3, null));
			mClassesList.add(new ClassesRecord(new Human(), null, HUMAN, 3, null));
			mClassesList.add(new ClassesRecord(new Dwarves(), null, DWARVEN, 3, new int[] { 12, 13, 10, 10, 12, 0, 0, 0, 12 }));
			mClassesList.add(new ClassesRecord(new Dwarrow(), null, DWARROW, 3, new int[] { 10, 12, 12, 12, 15, 15, 0, 0, 12 }));
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	public boolean[] getInnateSkills(String characterClass) {
		boolean innateSkills[];
		innateSkills = switch (characterClass) {
			case WARRIOR: {
				yield mWarriorInnateSkills;
			}
			case RANGER: {
				yield mRangerInnateSkills;
			}
			case THIEF: {
				yield mThiefInnateSkills;
			}
			case TSIRI: {
				yield mTsiriInnateSkills;
			}
			case TELLORIAN: {
				yield mTellorianInnateSkills;
			}
			case SITHRIAN: {
				yield mSithrianInnateSkills;
			}
			case SAILOR: {
				yield mSailorInnateSkills;
			}
			case DWARVEN: {
				yield mDwarvenInnateSkills;
			}
			case DWARROW: {
				yield mDwarrowInnateSkills;
			}
			case HUMAN:
			case GNOME:
			case HALF_ELVEN: {
				yield null;
			}
			default:
				yield null;
		};
		return innateSkills;
	}

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

	public String getSecondary(String which) {
		CharacterSheet character = ACS.getInstance().getCharacterSheet();

		return MageList.isMage(which) ? character.getMages().getSecondary(which) : character.getPriests().getSecondary(which);
	}

	/**
	 * @param class1
	 * @return
	 */
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
