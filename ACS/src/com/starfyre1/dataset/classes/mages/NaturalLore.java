/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class NaturalLore extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Natural Lore
		Second Requisite = Wisdom
	
			This area of Magic is a combination between Mages & Priests.  The Natural
			Lorist's are basically Druid's, they worship Wynd, Lorrell or Talon.  Most
			worship Wynd, they very Essence of Nature, neither Male or
			Female Wynd has no true form.  The Natural Lorist has very strict guidelines,
			First and Foremost, They Kill only for food or to preserve their own life.
	
			When researching spells treat the Natural Lorist as a Elf, they spend
			Experience points instead of Silver, but they must spend their research
			time in the wilderness meditating.   The Natural Lorist's personal focus can
			very from Mage to Mage, however it is usually a Staff.
			No matter what item is their focus it will have symbols of the natural elements
			on it, such as Holly, Oak, or Mistletoe.  Their Spells are as Follows:
	
			Power Zero:						Power One:
			1)Clear Water (-2)				1)Calm Waters (-10)F
			2)Detect Morals I (-10)F		2)Charm I (-6)F
			3)Detect Traps (-10)			3)Familiar (-600)
			4)Forecast (-10)				4)Growth (Plant) (-15)
			5)Heal Self (-30)				5)Healing (-60)F
			6)Know Direction (-10)			6)Increase Fire (-4)F
			7)Knowledge (-1)				7)Protection : Animals (-4)
			8)Light (-3)					8)Scattered Showers (-30)
			9)Locate Living (-10)			9)Silence (-6)
			10)Mage Wind (-1)				10)Speak with  Animals (-1)
			11)Darts (-2)					11)Star Sight (-12)
			12)Produce Water (-8)			12)Stop Poison (-6)
			13)Summon Flame (-1)F			13)Summon Fog (-4)
			14)Tracking (-8)				14)Web Stream (-3)F
			15)Translate (-1)				15)Healing (Animal) (-40)F
			16)Locate Life (Animal) (-15)	16)Cure Illness (Animals) (-20)
	
			Power Two:						Power Three:
			1)Area of Fog (-10)				1)Alter Shape (-3)
			2)Charm Animals (-4)			2)Anoint (-60)
			3)Concealment (-1)				3)Charm Plants (-7)
			4)Cure Illness (Humanoid) (-20)	4)Create Food (-12)
			5)ESP (-6)F						5)Dancing Fire (-6)
			6)Mind Link (-15)				6)Detect Morals II (-15)F
			7)Night Sight (-12)				7)Passage (-4)
			8)Protection : Fire (-0)		8)Protection : Lightning (-0)
			9)Salve of Healing (-1 Day)		9)Rune of Protection : Animals (-60)
			10)Vengeance (-2)F				10)Summon Animals I (-10)F
			11)Protection : Dark (-5)		11)Summon Fire (-3)F
			12)Earth Womb (-5)				12)Water Breathing (-10)
	
			Power Four:						Power Five:
			1)Control Element (-20)F		1)Commune (-30)
			2)Control Winds (-12)			2)Control Weather (-30)
			3)Deflection (-0)				3)Escape (-1)
			4)Lightning Storm I (-6)F		4)Fly (-1)
			5)Growth (-10)					5)Lower Water (-10)
			6)Liquefy Earth (-10)			6)Mind Wipe (-6)
			7)Paralyzation Touch (-4)		7)Part Rock I (-30)
			8)Protection : Lycanthrope (-4)	8)Part Water (-20)
			9)Summon Animals II (-9)F		9)Smoke Servant (-10)
			10)Wall of Lightning I (-4)F	10)Summon Storm (-30)
			11)Wall of Ice (-4)F			11)Wall of Lightning II (-30)
			12)Wall of Stone (-15)F
											Power Seven:
			Power Six:						1)Call to Law (-8)
			1)Alter Terrain (-60)F			2)Resurrection (-30)F
			2)Animate Rock (-20)			3)The Mottled Hand (-4)
			3)Command Insects (-10)
			4)Lightning Storm II (-6)F
			5)Symbol I (-60)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static NaturalLore sInstance;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public NaturalLore() {
		// NaturalLore
		mSecondRequisite = AttributesRecord.WISDOM;

		// This area of Magic is a combination between Mages & Priests.  The Natural
		// Lorist's are basically Druid's, they worship Wynd, Lorrell or Talon.  Most
		// worship Wynd, they very Essence of Nature, neither Male or
		// Female Wynd has no true form.  The Natural Lorist has very strict guidelines,
		// First and Foremost, They Kill only for food or to preserve their own life.
		// When researching spells treat the Natural Lorist as a Elf, they spend
		// Experience points instead of Silver, but they must spend their research
		// time in the wilderness meditating.   The Natural Lorist's personal focus can
		// very from Mage to Mage, however it is usually a Staff.
		// No matter what item is their focus it will have symbols of the natural elements
		// on it, such as Holly, Oak, or Mistletoe.  Their Spells are as Follows:

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Clear Water", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Traps", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Forecast", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Know Direction", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Knowledge", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Light", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Living", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mage Wind", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darts", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Produce Water", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Flame", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Tracking", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Translate", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Life : Animals", 0, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Calm Waters", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm I", 1, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Growth : Plant", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Increase Fire", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Animals", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Scattered Showers", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speak with  Animals", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Star Sight", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stop Poison", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fog", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Web Stream", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing : Animals", 0, 40, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness : Animals", 0, 20, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Fog", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm Animals", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Concealment", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("ESP", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Link", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Salve of Healing", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vengeance", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Dark", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Earth Womb", 0, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alter Shape", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Anoint", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm Plants", 0, 7, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Create Food", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dancing Fire", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals II", 2, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Passage", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Protection : Animals", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fire", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Breathing", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Control Element", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Winds", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deflection", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lightning Storm I", 1, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Growth", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Liquefy Earth", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Paralyzation Touch", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Lycanthrope", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals II", 2, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Lightning I", 1, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Ice", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Stone", 0, 15, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$
		// DW Verify Wall of Ligntning I & II don't exist and Wall of Fire is missing here (Natural Lore Power 4 & 5)

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Commune", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Weather", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Escape", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fly", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lower Water", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Wipe", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Part Rock I", 1, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Part Water", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Smoke Servant", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Lightning II", 2, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alter Terrain", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Animate Rock", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Command Insects", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lightning Storm II", 2, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol I", 1, 60, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Call to Law", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Resurrection", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("The Mottled Hand", 0, 4, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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

	public static NaturalLore getInstance() {
		if (sInstance == null) {
			sInstance = new NaturalLore();
		}
		return sInstance;
	}

	@Override
	public int getBandaging() {
		return 20;

	}

	@Override
	public int getHerbalLore() {
		return 20;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
