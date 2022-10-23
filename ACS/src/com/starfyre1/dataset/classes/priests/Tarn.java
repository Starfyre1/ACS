/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Tarn extends PriestsBase {
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
	
		Tarn
		Second Requisite - Wisdom
	
			Tarn is a very strict God, nothing his priests do can be untruthful or unjust
			in anyway.  This makes them very good judges and they travel the world
			dispensing justice and fighting evil in all it's many forms, anywhere they may
			find it.

			They are the closest to being an actual paladin, they have only on true
			enemy in the pantheon, that is Sarn, the God of Murder, wherever you find
			priests of Sarn, the followers of Tarn will not be far behind.  It is said that Tarn
			and Sarn where the first set of Twin's given birth by Mistress Night, this is
			strongly denied by the priests and clerics of Tarn.

			They may ware up to 50% armor without any chance of spell failure.  They are
			also not limited in their choice of weapons, although they are not allowed to have
			barbed or serrated blades at all.  They are the closest character class to being the
			perfect warrior of Good, but for all their wisdom they seldom if ever retreat, even
			in the face of overwhelming odds, this means that their usually aren't that many
			at any one time.

			Power Zero						Power One
			1)Protection : Fire (-0)          1)Light (-1)
			2)Flaming Circle (-6)F          2)Darts (-4)
			3)Protection I (-5)F            3)Know Truth (-20)
			4)Shield I (-15)                4)Protection II (-10)
			5)Call to Courage (-3)          5)Sphere of Air I (-5)
			6)Sheath (-1)                   6)Protection : Lightning (-15)
			7)Bless Blade I (-5)            7)Shield II (-20)
			8)Heal Self (-20)f              8)Heal (-120)
			9)Turn Demons (-5)              9)Strength (-120)
			10)Warmth (-15)                 10)Stamina I (-15)
			11)Detect Lie (-5)              11)Speed I (-10)
			12)Detect Evil (-5)				12)Protection : Demons (-15)F


			Power Two						Power Three
			1)Protection III (-15)          1)Protection IV (-20)
			2)Shield III (-25)              2)Shield IV (-25)
			3)Judgment (-60)                3)Spell Shield (-0)
			4)Summon Aide (-30)             4)Bless Blade II (-10)
			5)Knighthood (-lots)            5)Protection : Evil (-10)
			6)Blessing of Light (-30)       6)Rally (-10)
			7)Familiar (-lots)              7)Protection : Dark (-10)
			8)Speed II (-15)                8)Speed III (-20)
			9)Word of Warning (-15)         9)Stamina II (-20)
			10)Words of Power (-7)			10)Lighting Storm I (-10)F
	
			Power Four						Power Five
			1)Protection V (-20)            1)Protection VI (-25)
			2)Shield V (-35)                2)Bless Blade III (-15)
			3)Fly (-1)                      3)Stamina III (-25)
			4)Weight (-6)F                  4)Raise Army (-360)
			5)Vengeance (-5)                5)Glitter Shield (-8)F
			6)Commune (-120)                6)Gather Power (-120)
	
			Power Six 						Power Seven
			1)Vitality (-30)F               1)Rune : Slay Undead (-lots)
			2)Symbol I (-60)                2)Symbol II (-65)
			3)Rune : Slay Lycanthrope      	3)Call to Law (-15)
			4)Lightning Storm II (-15)F     4)Opening the Way (-60)
	
			Power Eight
			1)Gate (-30)F
			2)Gift of the Gods (-60)F
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Tarn() {
		// Tarn
		mSecondRequisite = AttributesRecord.WISDOM;
		// Tarn is a very strict God, nothing his priests do can be untruthful or unjust
		// in anyway.  This makes them very good judges and they travel the world
		// dispensing justice and fighting evil in all it's many forms, anywhere they may
		// find it.
		// They are the closest to being an actual paladin, they have only on true
		// enemy in the pantheon, that is Sarn, the God of Murder, wherever you find
		// priests of Sarn, the followers of Tarn will not be far behind.  It is said that Tarn
		// and Sarn where the first set of Twin's given birth by Mistress Night, this is
		// strongly denied by the priests and clerics of Tarn.
		// They may ware up to 50% armor without any chance of spell failure.  They are
		// also not limited in their choice of weapons, although they are not allowed to have
		// barbed or serrated blades at all.  They are the closest character class to being the
		// perfect warrior of Good, but for all their wisdom they seldom if ever retreat, even
		// in the face of overwhelming odds, this means that their usually aren't that many
		// at any one time.

		// Power Zero
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Flaming Circle", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(0, "Protection I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(0, "Shield I", 1, 15, 0), //$NON-NLS-1$
						new SpellRecord(0, "Call to Courage", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Sheath", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Bless Blade I", 1, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Heal Self", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Turn Demons", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Warmth", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Lie", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Evil", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Stamina I", 1, 15, 1)))); //$NON-NLS-1$
		// DW Verify "Warmth" Description doesn't exist Tarn Power 0
		// DW Verify "Detect Evil" Description doesn't exist Tarn Power 0

		// Power One
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Light", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Darts", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Know Truth", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Sphere of Air I", 1, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Lightning", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Shield II", 2, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Healing", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(1, "Strength", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(1, "Stamina I", 1, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Speed I", 1, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Demons", 0, 15, 1), //$NON-NLS-1$
						new SpellRecord(1, "Fire Hands", 0, 8, 0)))); //$NON-NLS-1$

		// Power Two
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Protection III", 3, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Shield III", 3, 25, 0), //$NON-NLS-1$
						new SpellRecord(2, "Judgment", 0, 60, 0), //$NON-NLS-1$
						new SpellRecord(2, "Summon Aide", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Knighthood", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Blessing of Light", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Speed II", 2, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Word of Warning I", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Words of Power", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(2, "Watchfire", 0, 15, 1), //$NON-NLS-1$
						new SpellRecord(2, "Stamina II", 2, 20, 1)))); //$NON-NLS-1$
		// DW Verify "Word of Warning" doesn't exist Tarn Power 2 (is it Word of Warning I... and if so is the casting time correct?)

		// Power Three
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Protection IV", 4, 20, 0), //$NON-NLS-1$
						new SpellRecord(3, "Shield IV", 4, 25, 0), //$NON-NLS-1$
						new SpellRecord(3, "Spell Shield", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(3, "Bless Blade II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection : Evil", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Rally", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection : Dark", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Stamina II", 2, 20, 0)))); //$NON-NLS-1$
		// DW Verify "Protection : Evil" doesn't exist Tarn Power 3

		// Power Four
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Protection V", 5, 20, 0), //$NON-NLS-1$
						new SpellRecord(4, "Shield V", 5, 35, 0), //$NON-NLS-1$
						new SpellRecord(4, "Lightning Storm I", 1, 10, 1), //$NON-NLS-1$
						new SpellRecord(4, "Weight", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Speed III", 3, 20, 0), //$NON-NLS-1$
						new SpellRecord(4, "Commune", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(4, "Invulnerability", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(4, "Stamina III", 3, 25, 1)))); //$NON-NLS-1$

		// Power Five
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Protection VI", 6, 25, 0), //$NON-NLS-1$
						new SpellRecord(5, "Bless Blade III", 3, 15, 0), //$NON-NLS-1$
						new SpellRecord(5, "Stamina IV", 4, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Raise Army", 0, 360, 0), //$NON-NLS-1$
						new SpellRecord(5, "Glitter Shield", 0, 8, 1), //$NON-NLS-1$
						new SpellRecord(5, "Gather Power", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(5, "Geas", 0, 8, 0)))); //$NON-NLS-1$

		// Power Six
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Vitality", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Symbol I", 1, 60, 0), //$NON-NLS-1$
						new SpellRecord(6, "Rune : Slay Lycanthropes", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Lightning Storm II", 2, 15, 1), //$NON-NLS-1$
						new SpellRecord(6, "Wake", 0, 1, 0)))); //$NON-NLS-1$

		// Power Seven
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Rune : Slay Undead", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(7, "Symbol II", 2, 65, 0), //$NON-NLS-1$
						new SpellRecord(7, "Call to Law", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(7, "Opening the Way", 0, 60, 0)))); //$NON-NLS-1$

		// Power Eight
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Gate", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(8, "Gift of the Gods", 0, 60, 1)))); //$NON-NLS-1$

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
