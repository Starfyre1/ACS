/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
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
			1)Speak with  Animals (-1)			1)Calm Waters (-20)F
			2)Heal Self (-20)				2)Sphere of Air I (-5)
			3)Light (-3)					3)Alertness I (-20)F
			4)Darkness (-3)					4)Freedom (-3)
			5)Water Bolt (-3)F				5)Stamina I (-15)
			6)Protection : Cold (-0)		6)Protection : Fire (-0)
			7)Clear Water (-8)				7)Star Sight (-12)
			8)Shield I (-15)				8)Shield II (-20)
	
			Power Two:						Power Three:
			1)Water Breathing (-25)			1)Summon Water Monster I(-30)
			2)Cold Spell I (-4)F			2)Cold Spell II (-5)F
			3)Alertness II (-25)F			3)Cure Illness (-120)
			4)Stamina II (-20)				4)Stamina III (-25)
			5)Familiar (-60)F				5)Control Winds (-12)
			6)Sea Safety (-30)				6)Touch of Death (-4)F
	
			Power Four:						Power Five:
			1)Summon water Mon.. II (-40)	1)Summon Water Monster III (-60)
			2)Sphere of Air II (-100)F		2)Pressure (-25)
			3)Cold Spell III (-6)F			3)Cold Spell IV (-7)F
			4)Water Walk (-5)				4)Control Weather (-30)
			5)Commune (-30)					5)Summon Storm (-30)
			6)Start Rain (-30)F				6)Summon Aide (-30)
	
			Power Six:						Power Seven:
			1)Tidal Wave (-300)				1)Transport via Water (-6)
			2)Sold Spell V (-8)F			2)Call Behemoth
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
						new SpellRecord("Speak with  Animals", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Light", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darkness", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Bolt", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Cold", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Clear Water", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield I", 1, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Calm Waters", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sphere of Air I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness I", 1, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freedom", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Star Sight", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield II", 2, 20, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Water Breathing", 0, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cold Spell I", 1, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness II", 2, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina II", 2, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sea Safety", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Water Monster I", 1, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cold Spell II", 2, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Winds", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Touch of Death", 0, 4, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Water Monster II", 2, 40, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sphere of Air II", 2, 100, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cold Spell III", 3, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Walk", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Start Rain", 0, 30, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Summon Water Monster III", 3, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pressure", 0, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cold Spell IV", 5, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Weather", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Aide", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Tidal Wave", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cold Spell V", 5, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shell of Water Breathing", 0, 35, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Transport via Water", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call Behemoth", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Transformation", 0, 500, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
