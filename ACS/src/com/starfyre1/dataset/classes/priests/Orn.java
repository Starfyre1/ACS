/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

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
			6)Summon Fog (-4)				6)Summon Shower (-8)
			7)Melt Ice (-5)					7)Protection : Fire (-0)
			8)Freeze Water (-7)F			8)Sphere of Air I (-5)
	
			Power Two:						Power Three:
			1)Summon Water Beast I (-3)		1)Summon Water Beast II (-6)
			2)Protection III (-12)F			2)Protection IV (-14)F
			3)Control Waterbeast  (-3)		3)Sphere of Air II (-100)
			4)Calm Wind (-15)				4)Ice Storm I (-6)F
	
			Power Four:						Power Five:
			1)Summon Water Beast III (-9)	1)Summon Water Beast IV (-12)
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
						new SpellRecord(0, "Heal Self", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(0, "Water Breathing", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(0, "Curse", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(0, "Control Element", 0, 25, 1), //$NON-NLS-1$
						new SpellRecord(0, "Summon Fog", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Melt Ice", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Freeze Water", 0, 7, 1), //$NON-NLS-1$
						new SpellRecord(0, "Absorb I", 1, 20, 1)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Darts", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Freedom", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Locate Living", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Area of Gloom", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Summon Shower", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Sphere of Air I", 1, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Dryness", 0, 5, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Summon Water Beast I", 1, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection III", 3, 12, 1), //$NON-NLS-1$
						new SpellRecord(2, "Control Water Beasts", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Calm Wind", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Absorb II", 2, 20, 1), //$NON-NLS-1$
						new SpellRecord(2, "Propel : Orn", 0, 10, 0)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Summon Water Beast II", 2, 6, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection IV", 4, 14, 1), //$NON-NLS-1$
						new SpellRecord(3, "Sphere of Air II", 2, 100, 0), //$NON-NLS-1$
						new SpellRecord(3, "Ice Storm I", 1, 6, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Summon Water Beast III", 3, 9, 0), //$NON-NLS-1$
						new SpellRecord(4, "Summon Storm", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(4, "Control Currents", 0, 50, 0), //$NON-NLS-1$
						new SpellRecord(4, "Protection V", 5, 16, 1), //$NON-NLS-1$
						new SpellRecord(4, "Damnation", 0, 120, 0)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Summon Water Beast IV", 4, 12, 0), //$NON-NLS-1$
						new SpellRecord(5, "Tidal Wave", 0, 300, 0), //$NON-NLS-1$
						new SpellRecord(5, "Protection VI", 6, 18, 1), //$NON-NLS-1$
						new SpellRecord(5, "Ice Storm II", 2, 9, 1)))); //$NON-NLS-1$
		// DW Verify Summon Water Monster(Beast) IV doesn't exist Orn Power 5

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Summon Kraken", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(6, "Pressure", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(6, "Protection VII", 7, 20, 1), //$NON-NLS-1$
						new SpellRecord(6, "Control Weather", 0, 350, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Protection VIII", 8, 25, 1), //$NON-NLS-1$
						new SpellRecord(7, "ReBirth", 0, 80, 1)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Maelstrom", 0, 35, 1)))); //$NON-NLS-1$

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
