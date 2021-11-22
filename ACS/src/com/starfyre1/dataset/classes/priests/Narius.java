/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Narius extends PriestsBase {
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
	
		Narius
		Second Requisite = Intelligence

			The Priests of Narius gather knowledge and preserve it, that is their sacred
			calling.  However, they also serve as a check to the priests of Sarn & Narese,
			who destroy most everything that they cannot use to cause pain, suffering or
			death to others.  They are also on the most effective groups of people striving
			for good in all of Athri.

			They are limited to 40% armor if any spells are to be cast, 30% if anything
			above a third level spell are to be cast.  They are also limited on the weapons
			that they can use, their selection are as follows :  Crossbows, Long & Short Bows,
			Short swords, Maces, Clubs, Staff, Rod, Scepters are their choices.

			Power zero:					Power one:
			1)Read  (-1)				1)Copy (-50)
			2)Write (-10)				2)Silence (-20)
			3)Breakfall (-0)			3)Create Magic Item I(-lots)
			4)Protection I (-5)			4)Protection II (-10)
			5)Protection : Fire (-0)		5)Vault (-2)
			6)Heal Self (-20)			6)Stone Darts (-5)
			7)Stamina I (-15)			7)Stamina II (-20)
			8)Forecast (-10)			8)Shield I (-15)
			9)Enchant Locks (-30)		9)Familiar (-600)
			10)Light (-1)				10)Open (-6)
			11)Staff I (-1)F			11)Summons II (-1)
			12)Summons I (-1)			12)Night Sight (-30)
			13)Protection : Cold (-2)		13)Word of Warning I (-35)
			14)Protection : Fear (-0)		14)Sleep I (-3)F
			15)Antidote (-lots)			15)See Invisibility (-6)
			16)Invisibility I (-10)F
	
			Power Two					Power Three
			1)Restore (-300)			1)Create Magic Item II (-lots)
			2)Suggestion (-1)			2)Protection IV (-30)
			3)Protection III (-25)		3)Shield III (-25)
			4)Shield II (-20)			4)Sleep II (-6)F
			5)Mind Link (-5)F			5)Strength (-120)F
			6)Protection : Fire/Item (-0)	6)Farsee (-10)F
			7)Protection : Lightning(-0)	7)Group Link (-15)
			8)Turn Curse (-1)			8)Invisibility II (-15)F
			9)Healing (-120)			9)Clairaudience (-10)
			10)Remove Curse (-30)		10)Teleport I (-3)F
			11)Staff II (-2)F			11)Word of Warning III (-45)
			12)Protection : Undead (-5)
			13)Word of Warning II (-40)

			Power Four					Power Five
			1)Protection V (-25)		1)Know History (-300)
			2)Stamina III (-25)			2)Secret Room (-500)
			3)Secret Door (-500)		3)Protection VI (-30)
			4)Fly (-1)					4)Wall of Force (-8)
			5)Telekinesis (-7)F			5)Create Magic Item III (-lots)
			6)Exorcism (-120)			6)Legend Lore (-300)
			7)Teleport II (-6)F			7)Safe Teleport (-120)F
			8)Escape (-1)F				8)Staff III (-3)F
			9)Word of Warning IV (-50)
	
			Power Six					Power Seven
			1)Geas (-3)					1)Rune : Slay Undead (-lots)
			2)Symbol I (-90)			2)Symbol II (-180)
			3)Staff IV (-4)F			3)Rune : Slay Lycanthropes (-lots)
			4)Commune (-30)				4)The Mottled Hand (-4)
	
			Power Eight
			1)Gate (-90)F
			2)Gift of the Gods (-120)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Narius() {
		// Narius
		mSecondRequisite = AttributesRecord.INTELLIGENCE;
		// The Priests of Narius gather knowledge and preserve it, that is their sacred
		// calling.  However, they also serve as a check to the priests of Sarn & Narese,
		// who destroy most everything that they cannot use to cause pain, suffering or
		// death to others.  They are also on the most effective groups of people striving
		// for good in all of Athri.
		// They are limited to 40% armor if any spells are to be cast, 30% if anything
		// above a third level spell are to be cast.  They are also limited on the weapons
		// that they can use, their selection are as follows :  Crossbows, Long & Short Bows,
		// Short swords, Maces, Clubs, Staff, Rod, Scepters are their choices.

		// Power zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Read", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Write", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Breakfall", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Forecast", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Enchant Locks", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Light", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff I", 1, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summons I", 1, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Cold", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fear", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Antidote", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility I", 1, 10, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power one:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Copy", 0, 50, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Create Magic Item I", 1, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vault", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stone Darts", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina II", 2, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Open", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summons II", 2, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Word of Warning I", 1, 35, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("See Invisibility", 0, 6, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Restore", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Suggestion", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield II", 2, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Link", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire : Item", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Turn Curse", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Remove Curse", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff II", 2, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Undead", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Word of Warning II", 2, 40, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Create Magic Item II", 2, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 4, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep II", 2, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strength", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Farsee", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Group Link", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility II", 2, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Clairaudience", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Teleport I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Word of Warning III", 3, 45, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection V", 5, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Secret Door", 0, 500, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fly", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Telekinesis", 0, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Exorcism", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Teleport II", 2, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Escape", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Word of Warning IV", 4, 50, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Know History", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Secret Room", 0, 500, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Force", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Create Magic Item III", 3, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Legend Lore", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Safe Teleport", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff III", 3, 3, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Geas", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol I", 1, 90, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff IV", 4, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Rune : Slay Undead", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol II", 2, 180, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune : Slay Lycanthropes", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("The Mottled Hand", 0, 4, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Eight
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Gate", 0, 90, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gift of the Gods", 0, 120, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
