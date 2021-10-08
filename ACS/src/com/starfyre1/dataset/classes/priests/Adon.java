/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Adon extends PriestsBase {
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
	
		Thantos
		Second Requisite – Strength

			Thantos is the Father of the Gods, as Mistress Night is their Mother.
			He lives on the plane Stratos, presiding over the other Gods of Law and Order.
			Thantos is the Lord of Courage and Chivalry.  He stands Second only to
			Mistress Night in power among the Gods of Athri.

			Followers of Thantos believe in the clean killing of all things Evil, such
			as Trolls, Goblins, Vampires or Priest's of any of the Evil Gods -Rysh, Graun,
			Narese to name a Few.  In combat a Priest of Thantos will not yield to any Evil
			foe as long as he/she still breaths.  Yielding to save another life, is a possibility,
			but not very likely, when playing a Priest of Thantos, don't forget their Chivalry or the
			Laws by which they live, Nothing is beyond the Law.

			A Priest of Thantos begins play with a Morals rating of no lower than "0".
			They must gain +2 per level to gain their new levels spells.  The focus for this
			priest is the symbol of Thantos (really imaginative, I know) a Circle of Silver
			with a Sword upraised in the center on a medallion.

			Power Zero:						Power One:
			1)Bless Blade I (-10)			1)Alertness I (-20)F
			2)Call to Courage (-3)			2)Bless Journey (-3)
			3)False Fire (-10)				3)Detect Enemies (-15)
			4)Flaming Circle (-6)F			4)Endurance (-15)
			5)Protection I (-5)F			5)Fearless Steed (-30)
			6)Protection / Fire (-0)		6)Healing (-30)F
			7)Protection / Missiles (-0)	7)Increase Fire (-10)
			8)Sheath (-1)					8)Protection II (-15)F
			9)Shield I (-15)				9)Protection / Demons (-15)F
			10)Turn Demons (-3)F			10)Speed I (-10)
											11)Stamina I (-15)
			Power Two:						12)Strength (-120)F
			1)Familiar (-600)				13)Touch of Fire (-5)
			2)Parry (-0)
			3)Protect Steed (-15)			Power Three:
			4)Protection III (-25)F			1)Agility (-15)
			5)Recruitment (-10)				2)Bless Blade II (-10)
			6)Shield II (-30)				3)Dome of fire (-4)F
			7)Speed II (-30)				4)Exorcism (-120)F
			8)Stamina II (-20)				5)Fear (-10)
			9)Turn Undead (-6)F				6)Firestorm I (-10)F
											7)Protection IV (-35)F
			Power Four:						8)Rally (-10)
			1)Alertness II (-20)F			9)Speed III (-20)
			2)Champion (-10)F				10)Stamina III (-25)
			3)Protection V (-45)F			11)Wall of Fire I (-5)F
			4)Quest (-10)
			5)Regeneration (-15)			Power Five:
			6)Resurrection (-Lots)			1)Bless Blade III (-15)
			7)Shield III (-30)				2)Commune (-120)
			8)Speed IV (-25)				3)Eternal Flame (-5)
			9)Stamina IV (-30)				4)Fire Shield (-0)F
			10)Vengeance (-5)				5)Protection VI (-55)F
											6)Raise Army (-600)
			Power Six:						7)Speed V (-30)
			1)Firestorm II (-20)F			8)Vitality (-30)F
			2)Holy Quest (-300)
			3)Opening the Way (-60)F			Power Seven:
			4)Protection VII (-65)F			1)Call from Beyond (-240)
			5)Shield IV (-45)				2)Holy Sword (-Lots)
											3)Protection / Magic (-20)F
			Power Eight:					4)Rune / Slay Lycanthropes (-lots)
			1)Call to Law (-15)				5)Rune / Slay Undead (-lots)
			2)Gate (-30)F					6)Wrath of Exonerous (-6)F
			3)Gift of the Gods (-60)F
			4)Shield V (-60)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Adon() {
		// Thantos
		mSecondRequisite = AttributesRecord.STRENGTH;

		// Thantos is the Father of the Gods, as Mistress Night is their Mother.
		// He lives on the plane Stratos, presiding over the other Gods of Law and Order.
		// Thantos is the Lord of Courage and Chivalry.  He stands Second only to
		// Mistress Night in power among the Gods of Athri.

		// Followers of Thantos believe in the clean killing of all things Evil, such
		// as Trolls, Goblins, Vampires or Priest's of any of the Evil Gods -Rysh, Graun,
		// Narese to name a Few.  In combat a Priest of Thantos will not yield to any Evil
		// foe as long as he/she still breaths.  Yielding to save another life, is a possibility,
		// but not very likely, when playing a Priest of Thantos, don't forget their Chivalry or the
		// Laws by which they live, Nothing is beyond the Law.

		// A Priest of Thantos begins play with a Morals rating of no lower than "0".
		// They must gain +2 per level to gain their new levels spells.  The focus for this
		// priest is the symbol of Thantos, really imaginative - I know, a Circle of Silver
		// with a Sword upraised in the center on a medallion.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Bless Blade I", 1, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call to Courage", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("False Fire", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Flaming Circle", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Missiles", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sheath", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Turn Demons", 0, 3, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness I", 1, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Bless Journey", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Enemies", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Endurance", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fearless Steed", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Increase Fire", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Demons", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed I", 1, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strength", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Touch of Fire", 0, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Parry", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protect Steed", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Recruitment", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield II", 2, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed II", 2, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina II", 2, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Turn Undead", 0, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Agility", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Bless Blade II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dome of fire", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Exorcism", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fear", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Firestorm I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 35, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rally", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed III", 3, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina III", 3, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Fire I", 1, 5, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness II", 2, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Champion", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 45, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Quest", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Regeneration", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Resurrection", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield III", 3, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed IV", 5, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina IV", 5, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vengeance", 0, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Bless Blade III", 3, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Eternal Flame", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fire Shield", 0, 0, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 55, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Raise Army", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speed V", 5, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vitality", 0, 30, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Firestorm II", 2, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Holy Quest", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Opening the Way", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VII", 7, 65, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield IV", 5, 45, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Call from Beyond", 0, 240, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Holy Sword", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Magic", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune / Slay Lycanthropes", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune / Slay Undead", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wrath of Exonerous", 0, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Call to Law", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gate", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gift of the Gods", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield V", 5, 60, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
