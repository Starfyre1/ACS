/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.warriors;

import com.starfyre1.startup.ACS;

public class Warrior extends WarriorsBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
	The Warrior:
	
		The Warrior is the easiest class to play in all the RPG systems.  He doesn't have to
		worry over any spells he just sharpens his sword and polishes his armor.  The
		Warriors do have several advantages though, More Hits, More Stam & more:
	
		1)	He may use any armor or weapon that the character can find, although
			the armor may be restricted due to climates.....
		2)	He receives a +5% to combat adds per level past first ( of which 1% MUST
			go to his Hit Bonus with the other 4% divided up between his Hit & Bow
			Bonus as the player decides.)
		3)	Their Missile bonus goes up +3% per level past first.
		4)	They add +10% to their Shock and Bleeding Saving throws at 1st level.
		5)  The Warrior has the Combat option "BERSERK"

		Berserker Options:
	
		To go Berserk - Roll over Intelligence.
		A)	Add +5% to Hit Bonus - Add +1 to Damage Bonus - Add +5% to your Armor
			Rating
			B)	Add +1 to your Attack Speed's.
	
			If the attempt to go Berserk Fails:
			A)	Character is Enraged:  Add +5% to Hit Bonus - Add +1 Damage Bonus
				-10% from your Armor Rating - -2 to your Attack Speeds.
	
		To Stop Berserking:
			Roll Under the Characters Willpower.
	
		After Berserking:
			Must rest for 3D10 Rounds....Meanwhile the character has -20% to Hit bonus,
			-4 to Attack Speeds, and -2 to Damage Bonus....
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

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

				Warriors:

					1)	Add +5% per level to be divided up between their Hit and Bow Bonuses.  At
						least +1% must go into their Hit Bonus, the other +4% can be split as the they
						wish.

					2)	Add +3% to their Missile Bonus.

					3)	Add +5 Stamina per level up to 10th level.  After 10th, add +2 Stamina per
						level.

					4)	Add +1 Hit Point per level until 10th level.  Then add +1 Hit Point every Odd
						level.

					5)	Add +1% per level to their Save Vs. Surprise.

					6)	Warriors Defense rises the same as their Hit Bonus, and their Free Attack
						Rises +1% per level past (1st).

		*/
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 5 + lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 2 + lvl * 3;
	}

	@Override
	public int getBowBonus() {
		return 3;
	}

	@Override
	public int getMovement() {
		return 12;
	}

	@Override
	public int generateUnallocated() {
		return 4;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
