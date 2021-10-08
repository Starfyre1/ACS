/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.Necromancer;

public class Graun extends PriestsBase {
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

		Graun
		Second Requisite - Wisdom

			Graun is the God of the Dead, or Undead as it most often turns out.
			He actually is not very Evil, but does usually follow the lead of Rysh,
			his Brother.

			That has gotten him into allot of trouble over the Millennia.
			He is one of the few Gods that do not have temples, Priest's of Graun
			congregate wherever there are Dead, so what use is a Church? His
			Preist’s use the Necromancer spell lists printed earlier in this Book.
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Graun() {
		// Graun
		mSecondRequisite = AttributesRecord.WISDOM;
		// Graun is the God of the Dead, or Undead as it most often turns out.
		// He actually is not very Evil, but does usually follow the lead of Rysh,
		// his Brother.
		// That has gotten him into allot of trouble over the Millennia.
		// He is one of the few Gods that do not have temples, Priest's of Graun
		// congregate wherever there are Dead, so what use is a Church? His
		// Preist's use the Necromancer spell lists printed earlier in this Book.

		mSpells = Necromancer.getInstance().mSpells;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	/*
	 *  For spell list @see com.starfyre1.dataset.mages.Necromancer
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
	@Override
	public int getBandaging() {
		return 10;

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
