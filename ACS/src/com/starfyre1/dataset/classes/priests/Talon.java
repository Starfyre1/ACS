/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Talon extends PriestsBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Priest
	Second Requisite = Wisdom
		The Priest of Athri have many different magic's and law's, each are strict
		in their own ways.  Some factions are intolerant of other's like Narese the
		Goddess of Pain, or very tolerant like the Priest's of Narius the God of
		Knowledge.  Here are the Gods and their Priest's Spells.
	
		Talon
		Second Requisite - Constitution

			Talon is the god of Beast's and Predator's.  He wants nothing more than to
			hunt at all times.  He and Wynd are the two Guardians of the Animal Kingdom.
			His clerics are closer to Ranger's than any other class, they have the same
			advantages as the Natural Lorist’s, but they have to take part in the Great Hunt at
			least once per year.
			They can only have three magic items on their person at any one time, anything
			else they will either give away or sell.  Their primary weapons are their hands
			and teeth, however they are allowed to use other weapons in the beginning.
			They are limited to 30% armor, and cannot use any two-handed weapons
			other than a bow or spear.
	
			Power Zero: 					Power One:
			1)Protection I (-5)F            1)Protection II (-10)F
			2)Heal Self (-30)               2)Talons I (-1)F
			3)Star Sight (-12)               3)Tracking (-8)
			4)Summon Animals I (-10)F       4)Protection : Fire (-3)
			5)Alertness I (-30)F            5)Protection : Cold (-4)
			6)Speak with  Animals (-1)         6)Healing (-60)F
	
			Power Two: 						Power Three:
			1)Protection III (-3)F          1)Protection IV (-4)F
			2)Night Sight (-30)              2)Alter Shape (-3)
			3)Talons II (-2)F               3)Talons III (-3)F
			4)Silence (-6)                  4)Summon Animals II (-9)F
			5)Protection : Lightning (-0) 	5)Protection : Undead (-6)
			6)Alertness II (-60)F           6)Speed II (-20)
			7)Speed I (-15)                 7)Alertness III (-75)F
			8)Strength (-120)F              8)Cure Illness (-120)
			9)Heal Animals (-100)           9)Gaze of Fear (-2)
	
			Power Four:  					Power Five:
			1)Command Insects (-10)         1)Blend with Shadows (-5)
			2)Protection V (-4)F            2)Commune (-30)
			3)Speed III (-25)               3)Deathword I (-0)
			4)Talons IV (-4)F               4)Fly (-1)
			5)Protection : Lycanthrope 		5)Summon Storm (-30)
	
			Power Six:
			1)The Hunt (-300)
			2)Deathword II (-0)
			3)Return (-30)F

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Talon() {
		// Talon
		mSecondRequisite = AttributesRecord.CONSTITUTION;
		// Talon is the god of Beast's and Predator's.  He wants nothing more than to
		// hunt at all times.  He and Wynd are the two Guardians of the Animal Kingdom.
		// His clerics are closer to Ranger's than any other class, they have the same
		// advantages as the Natural Lorist's, but they have to take part in the Great Hunt at
		// least once per year.
		// They can only have three magic items on their person at any one time, anything
		// else they will either give away or sell.  Their primary weapons are their hands
		// and teeth, however they are allowed to use other weapons in the beginning.
		// They are limited to 30% armor, and cannot use any two-handed weapons
		// other than a bow or spear.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Star Sight", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness I", 1, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speak with  Animals", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection II", 2, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Talons I", 1, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Tracking", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Cold", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 60, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection III", 3, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Talons II", 2, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness II", 2, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Lycanthrope", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing : Animals", 0, 100, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection IV", 5, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alter Shape", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Talons III", 3, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals II", 2, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Undead", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed II", 2, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness III", 3, 75, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gaze of Fear", 0, 2, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$
		// DW Verify "Alertness III" doesn't exist Sarn Power 3 & Tarn Power 3
		// DW Verify "Gaze of Fear" doesn't exist

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Command Insects", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Talons IV", 5, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Slow", 0, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Blend with Shadows", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deathword I", 1, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fly", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("The Hunt", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deathword II", 2, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Return", 0, 30, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$
		// DW Verify "The Hunt" doesn't exist

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	public void AdvanceLevel() {
		/*
		Advancing Levels:

			All classes modify their characters in the following ways when they advance a
			level of experience:

			1)	Add +4% per level past (1st) to their Saving Throws (except Surprise).

			2)	Add +5% per level, including (1st) to their Belief Rating.

			3)	Add +1 every Odd level past (1st) to their Attack, Missile and Bow Speeds,
				providing the characters actually use this skill at least once per level.

			4)	Add +3 Determination points per level past (1st).

			5)	Add +2% to Perception every level past (1st).

		Mages / Priests:

			1)	Add +4% per level past (1st) to be divided between their Hit Bonus, Bow
				Bonus and their Casting Speed.  They may split the 4% as they see fit, but
				for every +4% added to their Casting Speed it goes up +1.

			2)	Add +2% to their Missile Bonus.

			3)	Add +3 Stamina per level past (1st) until 10th level.  After 10th level add
				+1 Stamina per level.

			4)	Add +1 Hit Point per level till the 10th level.

			5)	Add +1% per level to their Save Vs. Surprise.

			6)	Their Defense rises the same as their Hit Bonus, and their Free Attack
				Rises +1% per level past (1st).
		*/
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public int getHerbalLore() {
		return 15;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
