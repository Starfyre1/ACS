/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Chauntil extends PriestsBase {
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

		Chauntil
		Second Requisite - Willpower
	
			Chauntil was once a maiden of Orn, God of the Ocean's and Sea's, till she saved
			a child meant to feed one of Orn's Krakens.  That day Orn noticed her, wishing
			to show his anger, he cursed her to immortality, then gave her into the hands
			of Narese, the Lady of Pain.
	
			The deed did not go un-noticed by the other Gods, Thantos, Sautarian and
			Tarn went to Mistress Night and the other gods to argue in her defense.  After
			several years of pain, Thantos and Mistress Night gave her a choice, she
			could die and pass into the afterlife in any of their followers heavens, or they
			would bestow upon her control of the shallower Sea's.
	
			She would be raised above all other humanoids and given Demi-Godhood.
			After 1000 years as a Demi-God, Mistress Night elevated her to a Lesser Power.
			Chauntil's clerics are not allowed to wear heavy armor, or anything they
			cannot get out of fast, most of her priests are sailors of one sort or another.
	
			Armor is restricted as above, having a maximum of 30%.  Their weapons have
			no restrictions, however you usually will not find one swinging a halberd,
			or a two-handed sword / axe.
	
			Power Zero:						Power One:
			1)Speak with Animals (-1)			1)Calm Waters (-20)F
			2)Heal Self (-20)				2)Sphere of Air I (-5)
			3)Light (-3)					3)Alertness I (-20)F
			4)Darkness (-3)					4)Freedom (-3)
			5)Water Bolt (-3)F				5)Stamina I (-15)
			6)Protection : Cold (-0)		6)Protection : Fire (-0)
			7)Clear Water (-8)				7)Star Sight (-12)
			8)Shield I (-15)				8)Shield II (-20)

			Power Two:						Power Three:
			1)Water Breathing (-25)			1)Summon Water Beast I(-30)
			2)Cold Spell I (-4)F			2)Cold Spell II (-5)F
			3)Alertness II (-25)F			3)Cure Illness (-120)
			4)Stamina II (-20)				4)Stamina III (-25)
			5)Familiar (-60)F				5)Control Winds (-12)
			6)Sea Safety (-30)				6)Touch of Death (-4)F

			Power Four:						Power Five:
			1)Summon water Mon.. II (-40)	1)Summon Water Beast III (-60)
			2)Sphere of Air II (-100)F		2)Pressure (-25)
			3)Cold Spell III (-6)F			3)Cold Spell IV (-7)F
			4)Water Walk (-5)				4)Control Weather (-30)
			5)Commune (-30)					5)Summon Storm (-30)
			6)Start Rain (-30)F				6)Summon Aide (-30)

			Power Six:						Power Seven:
			1)Tidal Wave (-300)				1)Transport via Water (-6)
			2)Cold Spell V (-8)F			2)Call Behemoth
			3)Shell of Water Breathing (-35)	3)Transformation (-500)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Chauntil() {
		// Chauntil
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Chauntil was once a maiden of Orn, God of the Ocean's and Sea's, till she saved
		// a child meant to feed one of Orn's Krakens.  That day Orn noticed her, wishing
		// to show his anger, he cursed her to immortality, then gave her into the hands
		// of Narese, the Lady of Pain.
		// The deed did not go un-noticed by the other Gods, Thantos, Sautarian and
		// Tarn went to Mistress Night and the other gods to argue in her defense.  After
		// several years of pain, Thantos and Mistress Night gave her a choice, she
		// could die and pass into the afterlife in any of their followers heavens, or they
		// would bestow upon her control of the shallower Sea's.
		// She would be raised above all other humanoids and given Demi-Godhood.
		// After 1000 years as a Demi-God, Mistress Night elevated her to a Lesser Power.
		// Chauntil's clerics are not allowed to wear heavy armor, or anything they
		// cannot get out of fast, most of her priests are sailors of one sort or another.
		// Armor is restricted as above, having a maximum of 30%.  Their weapons have
		// no restrictions, however you usually will not find one swinging a halberd,
		// or a two-handed sword / axe.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Clear Water", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darkness", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Dryness", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Heal Self", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(0, "Light", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Cold", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Resuscitate", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Speak with Animals", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Water Bolt", 0, 3, 1)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Alertness I", 1, 20, 1), //$NON-NLS-1$
						new SpellRecord(1, "Calm Waters", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Flash Healing", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(1, "Freedom", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Shield I", 1, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Sphere of Air I", 1, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Stamina I", 1, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Star Sight", 0, 12, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Alertness II", 2, 25, 1), //$NON-NLS-1$
						new SpellRecord(2, "Cold Spell I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, 60, 1), //$NON-NLS-1$
						new SpellRecord(2, "Moderate Heal", 0, 9, 0), //$NON-NLS-1$
						new SpellRecord(2, "Propel : Chauntil", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(2, "Sea Safety", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Shield II", 2, 20, 0), //$NON-NLS-1$
						new SpellRecord(2, "Stamina II", 2, 20, 0), //$NON-NLS-1$
						new SpellRecord(2, "Water Breathing", 0, 25, 0)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Cold Spell II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Control Water Beast", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Control Winds", 0, 12, 0), //$NON-NLS-1$
						new SpellRecord(3, "Cure Illness", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(3, "Shield III", 3, 35, 0), //$NON-NLS-1$
						new SpellRecord(3, "Stamina III", 3, 25, 0), //$NON-NLS-1$
						new SpellRecord(3, "Summon Water Beast I", 1, 30, 0), //$NON-NLS-1$
						new SpellRecord(3, "Touch of Death", 0, 4, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Cold Spell III", 3, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Commune", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(4, "Shell of Water Breathing", 0, 35, 0), //$NON-NLS-1$
						new SpellRecord(4, "Sphere of Air II", 2, 100, 1), //$NON-NLS-1$
						new SpellRecord(4, "Start Rain", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(4, "Summon Water Beast II", 2, 40, 0), //$NON-NLS-1$
						new SpellRecord(4, "Water Walk", 0, 5, 0)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Cold Spell IV", 4, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Control Weather", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Shield IV", 4, 60, 0), //$NON-NLS-1$
						new SpellRecord(5, "Summon Aide", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Summon Storm", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Summon Water Beast III", 3, 60, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Cold Spell V", 5, 8, 1), //$NON-NLS-1$
						new SpellRecord(6, "ReBirth", 0, 80, 1), //$NON-NLS-1$
						new SpellRecord(6, "Shield V", 5, 65, 0)))); //$NON-NLS-1$
		//						new SpellRecord(6, "Transport via Water", 0, 6, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Pressure", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(7, "Tidal Wave", 0, 300, 0)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Call Behemoth", 0, 60, 1), //$NON-NLS-1$
						new SpellRecord(8, "Transformation", 0, 500, 0)))); //$NON-NLS-1$

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
