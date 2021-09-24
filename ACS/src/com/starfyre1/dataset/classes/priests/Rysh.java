/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Rysh extends PriestsBase {
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

		Rysh
		Second Requisite – Willpower
	
			Rysh is the Demon Lord of the Plane of Fire called Sylkree, or the
			Nether world.  As in all biblical stories, Rysh once lived with the other
			Gods of Law on Stratos, but his lust for power led to a mighty war among
			the Gods.  Seeing his legions were losing, he retreated out of Stratos to the
			Void.  There he found his way to the Nether world, he built his Empire of
			Evil on the largest plane there called Sylkree.
	
			His minions were warped and maimed by powerful enchantments
			laid upon them by Thantos and the other Gods of Light.  With Rysh
			went Graun, Orn, Sarn and Narese.  Mistress Night stayed in the Void.  His
			Symbol is a Silver Dragon's Claw.
	
	
			Power Zero:							Power One:
			1)Cloak of Darkness (-5)			1)Call Imps (-6)F
			2)Curse (-1)						2)Darkness (-1)
			3)False Fire (-6)					3)Familiar (-600)
			4)Flaming Circle (-0)				4)Firebolt (-3)
			5)Invisible Aid (-1)				5)Protection / Fire (-0)
			6)Protection / Demons (-15)			6)Heal Self (-10)F
			7)Rune of Combat I (-lots)			7)Summon Fire (-0)
			8)Summon Flame (-5)					8)Summon Smoke (-5)
			9)Summon Imps (-60)F				9)The Black Mass (-lots)
			10)Protection I (-3) 				10)Touch of Fire (-1)
												11)Protection II (-6)
			Power Two:
			1)Cloak of Fire (-3)				Power Three:
			2)Darkwall (-5)						1)Control Evil (-5)F
			3)Demonic Spy (-60)					2)Curse of the Werewolf (-5)
			4)Fearful Gloom (-10)				3)Day of Gloom (-90)
			5)Protection / Lycanthropes (-5)	4)Demonic Portal (-15)F
			6)Invoke Spirit (-120)				5)Entity of Evil (-300)
			7)Rune of Combat II (-lots)			6)Firestorm I (-6)F
			8)Protection III (-9)				7)Gaze of Command (-2)
												8)Protection IV (-12)
			Power Four:							9)Summon Demon I (-120)F
			1)Commune (-lots)					10)Wall of Fire I (-10)
			2)Damnation (-120)
			3)Dome of Fire (-5)					Power Five:
			4)Hellfire (-5)						1)Burning Winds (-5)F
			5)Rune of Combat III (-lots)		2)Command Chaos (-7)F
			6)Summon Demon II (-360)F			3)Eternal Flame (-5)
			7)Protection V (-15)				4)Fire Shield (-0)
												5)Protection VI (-18)
			Power Six:							6)Realm of Darkness (-120)F
			1)Burning Plane (-10)F				7)Resurrection (-600)F
			2)Call from Beyond (-Lots)			8)Summon Demon III (-600)F
			3)Call to Chaos (-5)				9)Wall of Fire II (-60)
			4)Firestorm II (-20)F				10)Warriors of the Damned (-100)F
			5)Legions of the Damned (-300)F
			6)Protection VII (-21)				Power Seven:
			7)Rune of Combat IV (-lots)			1)Demon Guard (-1)F
			8)Summon Demon IV (-900)F			2)Gateway to Gorth (-120)
												3)UnHoly Sword (-lots)
												4)Protection VIII (-27)
												5)Summon Demon V (-1200)F
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Rysh() {
		// Rysh
		mSecondRequisite = AttributesRecord.WILLPOWER;
		// Rysh is the Demon Lord of the Plane of Fire called Sylkree, or the
		// Nether world.  As in all biblical stories, Rysh once lived with the other
		// Gods of Law on Stratos, but his lust for power led to a mighty war among
		// the Gods.  Seeing his legions were losing, he retreated out of Stratos to the
		// Void.  There he found his way to the Nether world, he built his Empire of
		// Evil on the largest plane there called Sylkree.
		// His minions were warped and maimed by powerful enchantments
		// laid upon them by Thantos and the other Gods of Light.  With Rysh
		// went Graun, Orn, Sarn and Narese.  Mistress Night stayed in the Void.  His
		// Symbol is a Silver Dragon's Claw.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cloak of Darkness", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Curse", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("False Fire", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Flaming Circle", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisible Aid", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Demons", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat I", 1, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Flame", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Imps", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Call Imps", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darkness", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Firebolt", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Smoke", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("The Black Mass", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Touch of Fire", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 6, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Cloak of Fire", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darkwall", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Demonic Spy", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fearful Gloom", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Lycanthropes", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invoke Spirit", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat II", 2, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 9, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Control Evil", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Curse of the Werewolf", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Day of Gloom", 0, 90, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Demonic Portal", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Entity of Evil", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Firestorm I", 1, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gaze of Command", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Demon I", 1, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Fire I", 1, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Commune", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Damnation", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dome of Fire", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hellfire", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat III", 3, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Demon II", 2, 360, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Burning Winds", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Command Chaos", 0, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Eternal Flame", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fire Shield", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 18, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Realm of Darkness", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Resurrection", 0, 600, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Demon III", 3, 600, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Fire II", 2, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Warriors of the Damned", 0, 100, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Burning Plane", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call from Beyond", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call to Chaos", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Firestorm II", 2, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Legions of the Damned", 0, 300, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VII", 7, 21, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat IV", 5, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Demon IV", 5, 900, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Demon Guard", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gateway to Gorth", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("UnHoly Sword", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VIII", 8, 27, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Demon V", 5, 1200, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
