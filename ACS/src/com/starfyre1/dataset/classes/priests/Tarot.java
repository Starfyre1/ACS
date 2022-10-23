/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Tarot extends PriestsBase {
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
	
		Tarot
		Second Requisite - Dexterity

			Tarot is the God of Chance, or if you ask Tartot, the God of absolute
			Chaos.  His only goal is to have every creature on Athri understand that fate is
			unreal, and chance is Master.  His only real enemy among the Gods is his Sarn.
			Tarot's clerics come in all shapes and sizes, they are limited to 45% armor
			maximum for any use of spells and are limited to single handed weapons, although
			they can use a single hand weapon, two-handed.

			Power Zero:					Power One:
			1)Curse (-1)                1)Gamble (-1)
			2)Heal Self (-20)           2)IDarts (-1)
			3)Protection I (-5)         3)Protection II (-10)
			4)Sleight of Hand (-2)      4)Hiding (-15)
			5)Protection : Fire (-0)      5)Protection : Lightning (-2)
			6)Protection : Cold (-0)      6)Invisibility I (-25)
			7)Vault (-2)                7)Stamina I (-15)F
			8)Protection : Charm (-5)     8)Speed I (-10)F
	
			Power Two: 					Power Three:
			1)Healing (-120)            1)Agility (-5)
			2)Protection III (-15)      2)Protection IV (-20)
			3)Silence (-6)              3)Invisibility II (-30)F
			4)Know Direction (-1)       4)Charm I (-5)F
			5)Stamina II (-20)F         5)Cats Eye (-10)
			6)Speed II (-15)F           6)Protection : Dark (-5)
	
			Power Four: 				Power Five:
			1)Luck (-5)F                1)Blend with Shadows (-5)F
			2)Bad Luck (-5)F            2)Charm II (-5)F
			3)Stamina III (-25)F        3)Speed III (-20)F
			4)Hide Location (-15)       4)Protection : Undead (-0)
			5)Protection V (-25)        5)Protection VI (-30)
	
			Power Six:					Power Seven:
			1)Death Word I (-0)			1)Death Word II (-0)
			2)Protection VII (-35)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Tarot() {
		// Tarot
		mSecondRequisite = AttributesRecord.DEXTERITY;
		// Tarot is the God of Chance, or if you ask Tartot, the God of absolute
		// Chaos.  His only goal is to have every creature on Athri understand that fate is
		// unreal, and chance is Master.  His only real enemy among the Gods is his Sarn.
		// Tarot's clerics come in all shapes and sizes, they are limited to 45% armor
		// maximum for any use of spells and are limited to single handed weapons, although
		// they can use a single hand weapon, two-handed.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Curse", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Heal Self", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection I", 1, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Sleight of Hand", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Cold", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Vault", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Charm", 0, 5, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Gamble", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Darts", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Hiding", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Lightning", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(1, "Invisibility I", 1, 25, 0), //$NON-NLS-1$
						new SpellRecord(1, "Stamina I", 1, 15, 1), //$NON-NLS-1$
						new SpellRecord(1, "Speed I", 1, 10, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Healing", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection III", 3, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Silence", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(2, "Know Direction", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Stamina II", 2, 20, 1), //$NON-NLS-1$
						new SpellRecord(2, "Speed II", 2, 15, 1), //$NON-NLS-1$
						new SpellRecord(2, "Pleasure", 0, 12, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Agility", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection IV", 4, 20, 0), //$NON-NLS-1$
						new SpellRecord(3, "Invisibility II", 2, 30, 1), //$NON-NLS-1$
						new SpellRecord(3, "Charm I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Cats Eye", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection : Dark", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(3, "Anarchy I", 1, 6, 0)))); //$NON-NLS-1$
		// DW Verify "Cats Eye" doesn't exist Tarot Power 3

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Luck", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(4, "Bad Luck", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(4, "Stamina III", 3, 25, 1), //$NON-NLS-1$
						new SpellRecord(4, "Hide Location", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(4, "Protection V", 5, 25, 0), //$NON-NLS-1$
						new SpellRecord(4, "Polymorph I", 1, 10, 0)))); //$NON-NLS-1$
		// DW Verify "Luck" doesn't exist Tarot Power 4
		// DW Verify "Bad Luck" doesn't exist Tarot Power 4

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Blend with Shadows", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(5, "Charm II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(5, "Speed III", 3, 20, 1), //$NON-NLS-1$
						new SpellRecord(5, "Protection : Undead", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(5, "Protection VI", 6, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Anarchy II", 2, 12, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Death Word I", 1, 0, 0), //$NON-NLS-1$
						new SpellRecord(6, "Return", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(6, "ReBirth", 0, 75, 1)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Death Word II", 2, 0, 0), //$NON-NLS-1$
						new SpellRecord(6, "Protection VII", 7, 65, 1), //$NON-NLS-1$
						new SpellRecord(7, "Resurrection", 0, -1, 0)))); //$NON-NLS-1$

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
