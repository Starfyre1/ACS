/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages.elemental;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.MagesBase;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Water extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Elements
	Second Requisite = Willpower

		There are Four distinct areas in the Elemental Magic, Earth, Air, Fire and
		Water.  Each will have a small explanation of the area and a spell list as
		follows:

		Water Elementalists control the force of Water, in all it's forms, Mists, Fogs
		and Ice to mention a few.  They are capable of Creating Whirlpools, that can
		sink small ships. They can summon Fogs and Fire bolts of freezing Cold at their
		enemies.  Their focus Can be anything the Ref. or player chooses.

		Power Zero:					Power One:
		1)Clear Water (-8)			1)Area of Fog (-10)
		2)Control Cloud (-1)		2)Calm Waters (-20)F
		3)Control Element (-20)F	3)Freedom (-3)
		4)Freeze Water (-5)F		4)Kill Normal Fires (-6)
		5) Darts (-3)				5)Protection : Fire (-0)
		6)Melt Ice (-5)				6)Scattered Showers (-30)
		7)Produce Water (-20)		7)Water Bolt (-3)F
		8)Protection : Cold (-0)
		9)Summon Fog (-4)			Power Three:
		10)Summon Shower (-8)		1)Cold Spell II (-5)F
									2)Control Water Beasts (-3)
		Power Two:					3)Hail Storm (-6)f
		1)Cold Spell I (-4)F		4)Summon Water Beasts (-3)
		2)Sea Safety (-30)
		3)Wall of Ice (-6)			Power Five:
		4)Water Breathing (-25)		1)Cold Spell IV (-7)F
									2)Day of Fog (-30)
		Power Four:					3)Part Water (-20)
		1)Cold Spell III (-6)F		4)Start Rain (-30)F
		2)Lower Water )-15)
		3)Water Walk (-5)			Power Seven:
									1)Cold Spell VI (-9)F
		Power Six:					2)Spell of Water Breathing (-35)
		1)Cold Spell V (-8)F		3)Transport Via Water (-6)
		2)Summon Storm (-30)		4)Pressure (-25)
		3)Water Wave (-7)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Water() {
		// TheElementsWater
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Water Elementalists control the force of Water, in all it's forms, Mists, Fogs
		// and Ice to mention a few.  They are capable of Creating Whirlpools, that can
		// sink small ships. They can summon Fogs and Fire bolts of freezing Cold at their
		// enemies.  Their focus Can be anything the Ref. or player chooses.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Clear Water", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Cloud", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Element", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freeze Water", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darts", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Melt Ice", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Produce Water", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Cold", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fog", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Shower", 0, 8, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Fog", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Calm Waters", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freedom", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Kill Normal Fires", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Scattered Showers", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Bolt", 0, 3, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell I", 1, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sea Safety", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Ice", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Breathing", 0, 25, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell II", 2, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Water Beasts", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hail Storm", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Water Beasts", 0, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell III", 3, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lower Water", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Walk", 0, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell IV", 5, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Day of Fog", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Part Water", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Start Rain", 0, 30, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell V", 5, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Wave", 0, 7, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cold Spell VI", 6, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Spell of Water Breathing", 0, 35, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Transport Via Water", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pressure", 0, 25, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
