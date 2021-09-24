/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Orn extends PriestsBase {
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

		Orn
		Second Requisite - Willpower
	
			Orn is the God of the Depths, he resents any mortal traveling on his king-
			dom.  Therefore, he usually sends his clerics and his monsters to sink their
			ships and scatter their bones across the bottom of his beloved ocean.
	
			Orn has one enemy he hates above all others, that is Chauntil.  She stole
			the sea's away from his controls, now he only has the depths of the ocean's.
			Orn's clerics are limited to 30% armor and usually use the sword of one type
			or another.  You'll find quite a few Ornish clerics aboard pirate craft.
	
			Power Zero:						Power One:
			1)Heal Self (-120)				1) Darts (-5)
			2)Water Breathing (-20)			2)Freedom (-4)
			3)Curse (-1)					3)Protection II (-10)
			4)Protection I (-5)F			4)Locate Living (-15)
			5)Control Element (-25)F		5)Area of Gloom (-30)
			6)Summon Fog (-4)				6)Summon Showers (-8)
			7)Melt Ice (-5)					7)Protection / Fire (-0)
			8)Freeze Water (-7)F			8)Sphere of Air I (-5)

			Power Two:						Power Three:
			1)Summon Waterbeast I (-3)		1)Summon Waterbeasts II (-6)
			2)Protection III (-12)F			2)Protection IV (-14)F
			3)Control Waterbeast  (-3)		3)Sphere of Air II (-100)
			4)Calm Winds (-15)				4)Ice Storm I (-6)F

			Power Four:						Power Five:
			1)Summon Waterbeast III (-9)	1)Summon Waterbeast IV (-12)
			2)Summon Storm (-30)			2)Tidal Wave (-300)
			3)Control Currents (-50)		3)Protection VI (-18)f
			4)Protection V (-16)F			4)Ice Storm II (-9)F
			5)Damnation (-120)

			Power Six:
			1)Summon Kraken (-120)
			2)Pressure (-10)
			3)Protection VII (-20)F
			4)Control Weather (-350)
	
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Orn() {
		// Orn
		mSecondRequisite = AttributesRecord.WILLPOWER;
		// Orn is the God of the Depths, he resents any mortal traveling on his king-
		// dom.  Therefore, he usually sends his clerics and his monsters to sink their
		// ships and scatter their bones across the bottom of his beloved ocean.
		// Orn has one enemy he hates above all others, that is Chauntil.  She stole
		// the sea's away from his controls, now he only has the depths of the ocean's.
		// Orn's clerics are limited to 30% armor and usually use the sword of one type
		// or another.  You'll find quite a few Ornish clerics aboard pirate craft.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Heal Self", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Breathing", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Curse", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Element", 0, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fog", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Melt Ice", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freeze Water", 0, 7, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Darts", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freedom", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Living", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Area of Gloom", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Showers", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sphere of Air I", 1, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Waterbeast I", 1, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 12, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Waterbeast", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Calm Winds", 0, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Waterbeasts II", 2, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 14, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sphere of Air II", 2, 100, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Ice Storm I", 1, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Waterbeast III", 3, 9, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Currents", 0, 50, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 16, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Damnation", 0, 120, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Waterbeast IV", 5, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Tidal Wave", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 18, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Ice Storm II", 2, 9, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Kraken", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pressure", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VII", 7, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Weather", 0, 350, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
