/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class MistressNight extends PriestsBase {
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
	
		Mistress Night
		Second Requisite - Intelligence

			Mistress Night is the Goddess of Darkness, the Original Night.  She is the
			mother of all the Gods.  She and the other Gods where banished over 1,000 yrs.
			ago.  She is by no means a Evil Goddess, she is whimsical and
			if anything pure neutral.

			Clerics of Mistress Night start out as one of two things, a Shadow, or a Night
			Cleric.  These are the only two classes directly under her control.  Both classes
			have no restrictions for either armor or weapons, however they must abide by
			the armor rules for magic spells in the magic system.

			There are several rituals and sacrifices that are given over to Mistress Night at
			least once per level to show the priest/priestess devotion to their Goddess.
			Mistress Night loves magic, she requires one magic sacrifice for every 3 items
			kept by the cleric or the party they are traveling with.
			Here are their Spells :

			Power Zero: 					Power One:
			1)Darkness (-1)					1)Area of Gloom (-30)
			2)Darts (-4)					2)Shadow Climb (-10)
			3)Night Sight (-3)				3)Heal Self(-20)
			4)Rune : Combat I (-lots)		4)Absorb I (-15)f
			5)Alertness I (-30)F			5)Locate Living (-15)
			6)Stamina I (-15)F				6)Hide Location (-20)
			7)Hiding (-15)					7)Strength (-150)
			8)Vault (-1)F					8)Rune : Combat II (-lots)
	
			Power Two:						Power Three:
			1)Shadow Hold (-5)				1)Magic Siphon (-25)
			2)Cold Spell I (-5)F			2)Cold Spell II (6)F
			3)Familiar (-lots)				3)Transform (-35)F
			4)Telepathy (-5)F				4)Absorb II (-20)F
			5)Mage Wind (15)				5)Alter Shape (-5)
			6)Stamina II (-20)F				6)Stamina III (-25)F
			7)Haste (-7)H					7)Star Ritual (-lots)
			8)Rune : Combat III (-lots)	8)Mana Pool (-lots)
			9)Silence (-8)					9)Glitter shield (-1)F
	
			Power Four:						Power Five:
			1)Cold Spell III (-8)F			1)Magic Detonation (-10)F
			2)Absorb III (-25)F				2)Cold Spell IV (-10)f
			3)Damnation (-lots)				3)Rune : Combat V (-lots)
			4)Rune : Combat IV (-lots)		4)Escape (-0)F
			5)Mind Wipe (-7)				5)Soul Store (-300)F
			6)Absorb Magic (-6)F
	
			Power Six:						Power Seven:
			1)Return (-30)F					1)The Mottled Hand (-4)
			2)Resurrection (-30)F			2)Call to Chaos (-9)
			3)Realm of Darkness (-150)F		3)Zen-Carla (-Lots)FH
	
			Power Eight:
			1)Gift of the Gods (-150)
			2)Sleep Eternal (-10)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MistressNight() {
		// Mistress Night
		mSecondRequisite = AttributesRecord.INTELLIGENCE;
		// Mistress Night is the Goddess of Darkness, the Original Night.  She is the
		// mother of all the Gods.  She and the other Gods where banished over 1,000 yrs.
		// ago.  She is by no means a Evil Goddess, she is whimsical and
		// if anything pure neutral.
		// Clerics of Mistress Night start out as one of two things, a Shadow, or a Night
		// Cleric.  These are the only two classes directly under her control.  Both classes
		// have no restrictions for either armor or weapons, however they must abide by
		// the armor rules for magic spells in the magic system.
		// There are several rituals and sacrifices that are given over to Mistress Night at
		// least once per level to show the priest/priestess devotion to their Goddess.
		// Mistress Night loves magic, she requires one magic sacrifice for every 3 items
		// kept by the cleric or the party they are traveling with.
		// Here are their Spells:

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Alertness I", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(0, "Conceal", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darkness", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Hiding", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(0, "Night Sight", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Rune : Combat I", 1, -1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Stamina I", 0, 15, 1), //$NON-NLS-1$
						new SpellRecord(0, "Vault", 0, 1, 1)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Absorb I", 1, 15, 1), //$NON-NLS-1$
						new SpellRecord(1, "Heal Self", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Hide Location", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Locate Living", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Neutralize Poison", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Night Sight", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Rune : Combat II", 2, -1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Shadow Climb", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Strength", 0, 150, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Cold Spell I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Haste", 0, 7, 1), //$NON-NLS-1$
						new SpellRecord(2, "Mage Wind", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Rune : Combat III", 3, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Shadow Hold", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Silence", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(2, "Stamina II", 2, 20, 1), //$NON-NLS-1$
						new SpellRecord(2, "Telepathy", 0, 5, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Absorb II", 2, 20, 1), //$NON-NLS-1$
						new SpellRecord(3, "Alter Shape", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(3, "Cold Spell II", 2, 6, 1), //$NON-NLS-1$
						new SpellRecord(3, "Forge I", 1, -1, 1), //$NON-NLS-1$
						new SpellRecord(3, "Glitter Shield", 0, 1, 1), //$NON-NLS-1$
						new SpellRecord(3, "Magic Siphon", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(3, "Stamina III", 3, 25, 1), //$NON-NLS-1$
						new SpellRecord(3, "Star Ritual", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(3, "Transform", 0, 35, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Absorb III", 3, 25, 1), //$NON-NLS-1$
						new SpellRecord(4, "Absorb Magic", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Blend with Shadows", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(4, "Cold Spell III", 3, 8, 1), //$NON-NLS-1$
						new SpellRecord(4, "Forge II", 2, -1, 1), //$NON-NLS-1$
						new SpellRecord(4, "Magic Detonation", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(4, "Mind Wipe", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(4, "Rune : Combat IV", 4, -1, 0), //$NON-NLS-1$
						new SpellRecord(4, "Whispers I", 1, 35, 0)))); //$NON-NLS-1$
		// DW Verify : Absorb Magic MistressNight Power 4... no description

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Cold Spell IV", 4, 10, 1), //$NON-NLS-1$
						new SpellRecord(5, "Escape", 0, 0, 1), //$NON-NLS-1$
						new SpellRecord(5, "Forge III", 0, -1, 1), //$NON-NLS-1$
						new SpellRecord(5, "Mana Pool", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Rune : Combat V", 5, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "ReBirth", 0, 80, 1), //$NON-NLS-1$
						new SpellRecord(5, "Shadow Warning", 0, 125, 0), //$NON-NLS-1$
						new SpellRecord(5, "Soul Store", 0, 300, 1)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Realm of Darkness", 0, 150, 1), //$NON-NLS-1$
						new SpellRecord(6, "Resurrection", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Return", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Whispers II", 2, 65, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Call to Chaos", 0, 9, 0), //$NON-NLS-1$
						new SpellRecord(7, "The Mottled Hand", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(7, "Walk in Shadows", 0, 15, 0)))); //$NON-NLS-1$
		// DW Verify : Zen-Carla MistressNight Power 7... no description -- Removed???

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Castle of Shadows", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(8, "Gift of the Gods", 0, 150, 0), //$NON-NLS-1$
						new SpellRecord(8, "Sleep Eternal", 0, 10, 0)))); //$NON-NLS-1$

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
