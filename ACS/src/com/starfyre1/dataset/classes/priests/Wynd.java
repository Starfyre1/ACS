/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.NaturalLore;

public class Wynd extends PriestsBase {
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

		Wynd
		Second Requisite - Wisdom

		Wynd is the Essence of Nature, neither male or female its domain is
		all things natural, Trees, Grass, Rivers and all things that Live.  Priests of Wynd
		are Druids, they live in or around great forest's or travel the land helping and growing
		things in need of help.  They kill only when they need to eat, or in defense of themselves.

		They are very in tune with nature, for Research on their spells you would
		treat them as Elven.  To see their spells, see Natural Lore, earlier in this Book
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Wynd() {
		// Wynd
		mSecondRequisite = AttributesRecord.WISDOM;
		// Wynd is the Essence of Nature, neither male or female its domain is
		// all things natural, Trees, Grass, Rivers and all things that Live.  Priests of Wynd
		// are Druids, they live in or around great forest's or travel the land helping and growing
		// things in need of help.  They kill only when they need to eat, or in defense of themselves.
		// They are very in tune with nature, for Research on their spells you would
		// treat them as Elven.  To see their spells, see Natural Lore, earlier in this Book

		mSpells = NaturalLore.getInstance().mSpells;

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	/*
	 * For spell list @see com.starfyre1.dataset.mages.NatureLore
	 */

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
