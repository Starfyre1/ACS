/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Narese extends PriestsBase {
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

		Narese
		Second Requisite-Willpower
	
			Narese is a evil Goddess, she devotes most of her time to the pursuit of Pain.
			That is her one pleasure and all of her followers follow that goal.  Creating
			pain and suffering anywhere they can.
	
			She loves a long satisfying torture.  Her cleric's have several limitations, the
			First is their scaring, every priest or priestess of Narese must undergo certain
			torture and ritual scaring that brands them anywhere they go, they stand out.
	
			The second is their armor and weapons limitations.  They can only have a
			maximum of 30% armor on for any spell, and their weapons must be spiked,
			jagged or crushing.  Anything they use must cause extra pain at every use,
			that is their goal in life and their duty to their Mistress.
	
			Power Zero: 					Power One:
			1)Protection I (-5)F			1)Protection II (-7)f
			2)Protection/Fire (-2)			2) Familiar (-300)F
			3)Protection/Cold (-4)			3)Protection/Lightning (-4)
			4)Darts (-6)       				4)Area of Gloom (-30)
			5)Heal Self (-23)				5)Locate Living (-15)
			6)Area of Fear (-3)F			6)Summon Flame (-3)
			7)Darkness (-3)

			Power Two: 						Power Three:
			1)Slow Poison (-125)F			1) Entity of Evil (--300)
			2)Protection III (-15)F			2)Levitate (-12)F
			3)Pleasure (-25)F				3)Howl of Fear (-3)
			4)Sleep I (-5)F					4)Day of Gloom (-95)F
			5)Weakness (-5)F				5)Mind Link (-9)F
			6)Touch of Fire (-7)			6)Sacrifice (-Lots)
			7)Familiar (-400)F				7)Transform (-25)F
			8)Summon Fire (-5)
			9)Prot/Lycanthrope (-6)

			Power Four: 					Power Five:
			1) Sleep II (-8)F				1)Protection V (-20)F
			2)Protection IV (-15)F			2)Venom Vapors(-6)
			3)Fly (-25)F					3)Escape (-5)F
			4)Ritual Torture (400)F			4)Unholy Strength (-35)F
			5)Circle of Pain (-6)F			5)Word of Pain (-65)F
			6)Damnation (-25)

			Power Six:	 					Power Seven:
			1)Protection VI (-25)F			1)Protection VII (-25)F
			2)Return (-30)F					2) Gate (-400)F
			3)Call From Beyond (-300)		3)Call To Chaos (-10)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Narese() {
		// Narese
		mSecondRequisite = AttributesRecord.WILLPOWER;
		// Narese is a evil Goddess, she devotes most of her time to the pursuit of Pain.
		// That is her one pleasure and all of her followers follow that goal.  Creating
		// pain and suffering anywhere they can.
		// She loves a long satisfying torture.  Her cleric's have several limitations, the
		// First is their scaring, every priest or priestess of Narese must undergo certain
		// torture and ritual scaring that brands them anywhere they go, they stand out.
		// The second is their armor and weapons limitations.  They can only have a
		// maximum of 30% armor on for any spell, and their weapons must be spiked,
		// jagged or crushing.  Anything they use must cause extra pain at every use,
		// that is their goal in life and their duty to their Mistress.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Fire", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Cold", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darts", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 23, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Area of Fear", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darkness", 0, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection II", 2, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 300, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Lightning", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Area of Gloom", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Living", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Flame", 0, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Slow Poison", 0, 125, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pleasure", 0, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Weakness", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Touch of Fire", 0, 7, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 400, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fire", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Prot/Lycanthrope", 0, 6, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Entity of Evil", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Levitate", 0, 12, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Howl of Fear", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Day of Gloom", 0, 95, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Link", 0, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sacrifice", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Transform", 0, 25, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Sleep II", 2, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fly", 0, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Ritual Torture", 0, -400, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Circle of Pain", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Damnation", 0, 25, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection V", 5, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Venom Vapors", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Escape", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Unholy Strength", 0, 35, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Word of Pain", 0, 65, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection VI", 6, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Return", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call From Beyond", 0, 300, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection VII", 7, 25, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gate", 0, 400, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call To Chaos", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
