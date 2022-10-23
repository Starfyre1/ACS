/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

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
			2)Protection : Fire (-2)		2) Familiar (-300)F
			3)Protection : Cold (-4)		3)Protection : Lightning (-4)
			4)Darts (-6)       				4)Area of Gloom (-30)
			5)Heal Self (-23)				5)Locate Living (-15)
			6)Area of Fear (-3)F			6)Summon Flame (-3)
			7)Darkness (-3)
	
			Power Two: 						Power Three:
			1)Slow Poison (-125)F			1) Entity of Evil (--300)
			2)Protection III (-15)F			2)Levitation (-12)F
			3)Pleasure (-25)F				3)Howl of Fear (-3)
			4)Sleep I (-5)F					4)Day of Gloom (-95)F
			5)Weakness (-5)F				5)Mind Link (-9)F
			6)Touch of Fire (-7)			6)Sacrifice (-Lots)
			7)Familiar (-400)F				7)Transform (-25)F
			8)Summon Fire (-5)
			9)Protection : Lycanthrope (-6)
	
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
						new SpellRecord(0, "Protection I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Fire", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Cold", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(0, "Heal Self", 0, 23, 0), //$NON-NLS-1$
						new SpellRecord(0, "Area of Fear", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(0, "Darkness", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Illusion", 0, 3, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Protection II", 2, 7, 1), //$NON-NLS-1$
						new SpellRecord(1, "Familiar", 0, 300, 1), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Lightning", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Area of Gloom", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Locate Living", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Summon Flame", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "Slow Poison", 0, 125, 1), //$NON-NLS-1$
						new SpellRecord(1, "Pain I", 1, 3, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Protection III", 3, 15, 1), //$NON-NLS-1$
						new SpellRecord(2, "Pleasure", 0, 25, 1), //$NON-NLS-1$
						new SpellRecord(2, "Sleep I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Weakness", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Touch of Fire", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, 400, 1), //$NON-NLS-1$
						new SpellRecord(2, "Summon Fire", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Lycanthrope", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(2, "Pain II", 2, 4, 1)))); //$NON-NLS-1$
		// DW Verify : Slow Poison Narese Power 2... no description

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Entity of Evil", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(3, "Levitation", 0, 12, 1), //$NON-NLS-1$
						new SpellRecord(3, "Howl of Fear", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Day of Gloom", 0, 95, 1), //$NON-NLS-1$
						new SpellRecord(3, "Mind Link", 0, 9, 1), //$NON-NLS-1$
						new SpellRecord(3, "Sacrifice", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(3, "Transform", 0, 25, 1), //$NON-NLS-1$
						new SpellRecord(3, "Pain III", 3, 6, 1)))); //$NON-NLS-1$
		// DW Verify : Howl of Fear Narese Power 3... no description
		// DW Verify : Sacrifice Narese Power 3... no description ( is this supposed to be "Ritual Sacrifice"?

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Sleep II", 2, 8, 1), //$NON-NLS-1$
						new SpellRecord(4, "Protection IV", 4, 15, 1), //$NON-NLS-1$
						new SpellRecord(4, "Fly", 0, 25, 1), //$NON-NLS-1$
						new SpellRecord(4, "Ritual Torture", 0, -400, 1), //$NON-NLS-1$
						new SpellRecord(4, "Circle of Pain", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Damnation", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(4, "Pain IV", 4, 8, 1), //$NON-NLS-1$
						new SpellRecord(4, "Anarchy I", 1, 8, 1)))); //$NON-NLS-1$
		// DW Verify : Circle of Pain Narese Power 4... no description

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Protection V", 5, 20, 1), //$NON-NLS-1$
						new SpellRecord(5, "Venom Vapors", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(5, "Escape", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(5, "Unholy Strength", 0, 35, 1), //$NON-NLS-1$
						new SpellRecord(5, "Word of Pain", 0, 65, 1), //$NON-NLS-1$
						new SpellRecord(5, "Pain V", 5, 8, 1), //$NON-NLS-1$
						new SpellRecord(5, "Anarchy II", 2, 12, 1)))); //$NON-NLS-1$
		// DW Verify : Word of Pain Narese Power 5... no description

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Protection VI", 6, 25, 1), //$NON-NLS-1$
						new SpellRecord(6, "Return", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Call from Beyond", 0, 300, 0), //$NON-NLS-1$
						new SpellRecord(6, "Symbol I", 1, 65, 0), //$NON-NLS-1$
						new SpellRecord(6, "Pain VI", 6, 9, 1)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Protection VII", 7, 25, 1), //$NON-NLS-1$
						new SpellRecord(7, "Gate", 0, 400, 1), //$NON-NLS-1$
						new SpellRecord(7, "Call to Chaos", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(7, "Pain VII", 7, 10, 1)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Symbol II", 2, 70, 0), //$NON-NLS-1$
						new SpellRecord(8, "Pain VIII", 8, 12, 1)))); //$NON-NLS-1$

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
