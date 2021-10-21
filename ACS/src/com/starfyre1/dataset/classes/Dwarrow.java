/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

public class Dwarrow extends BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		D’etri (Dwarrow)
	
		Minimum Stat’s Required:
		ST=”10” CN=”12” IN=”12” WS=”12” DX=15” BS=”15” CH=”*” PA=”*” WP=”12”
		Maximum ST=”16”
	
				The Wee folk resemble pint sized elves, as a race they can use any missile weapon,
				Knives, sling, spear or Bow better than most races.  This is for self protection, as
				they are a delicacy to most monster races.  They are excellent at Stealth, Hear and
				Conceal as the thieving abilities +20% outdoors or +10% indoors at 1st level.
	
				They are omnivorous and some of the finest hunters of Athri, also gaining the
				Rangers Tracking Ability.  Their sight is amazing, better even than the elves,
				having Night Sight as spell and being able to see both Detect Magic and Aura’s.
	
				They come in all colors, pale or tan, with hair of blonde, auburn, brown or black.
				Their eyes though are striking, fauceted and jewel tones of amber, emerald,
				sapphire, ruby and diamond.  Average height is 2’8” – 4’8” weighing 41-82 lbs.
	
				Dwarrow’s can learn magic with determination points spent to learn the areas of
				Natural Lore, the Element of Earth, Air & Water or become Priests of Wynd,
				Lorrell or Chauntee.  They are also the Only race to know of and practice Rune Lore.
	
			1)	D’evri have a Maximum Hit bonus of 3 X Strength
			2)	D’evri have a Maximum Missile bonus of 3 X Dexterity
			3)	D’evri have no Maximum Bow Bonus
			4)	They gain +5 stamina and +1 hit points per level.  After 9th level they go down to
					+2 Stamina till 15th level where they stop gaining them.
			5)	D’evri gain +5% with short swords, daggers or rapiers
			6)	They add +7% to their Perception skill at character creation.
	
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

			Elves & Dwarrow:

				1)	Add +7% per level past (1st) to their Hit and Bow Bonuses.  They must
					invest at least +1% into their Hit Bonus and at least +3% into their Bow
					Bonus, the rest may be split as they desire.  Even when they are Maxed
					out on their Hit Bonus, they must still invest +1% to it....

				2)	Add +2% to their Missile Bonus.

				3)	Add +4 Stamina per level until 5th level, after 5th level add +2 Stamina
					per level.

				4)	Add +1 Hit Point per level past (1st) until 10th level, after 10th level
					add +1 hit point for every 3) levels earned.

				5)	Add +1% to their Surprise.

				6)	Add +5% to their Defense.  When their Hit Bonus has maxed out, they must
					still add +1% to their Defense.  Their Free Attack rises +1% per level.
		*/
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	// Skills
	@Override
	public int getBandaging() {
		return 10;
	}

	@Override
	public int getHunting() {
		return 0;
	}

	@Override
	public int getTracking() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 3;
	}

	@Override
	public int getDetectMagic() {
		// DW verify that dwarrow are the same stats as elves
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getDetectMetals() {
		return 0;
	}

	@Override
	public int getDetectSecretDoors() {
		return 0;
	}

	@Override
	public int getDetectTraps() {
		return 0;
	}

	@Override
	public int getAppraise() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 90 + lvl;
	}

	@Override
	public int getDepthSense() {
		return 0;
	}

	@Override
	public int getHerbalLore() {
		return 15;
	}

	@Override
	public int getBerserk() {
		return 0;
	}

	@Override
	public int getConceal() {
		// DW They are excellent at Stealth, Hear and Conceal as the thieving abilities +20% outdoors or +10% indoors at 1st level
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getStealth() {
		// DW They are excellent at Stealth, Hear and Conceal as the thieving abilities +20% outdoors or +10% indoors at 1st level
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getHear() {
		// DW They are excellent at Stealth, Hear and Conceal as the thieving abilities +20% outdoors or +10% indoors at 1st level
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getClimb() {
		return 0;
	}

	@Override
	public int getUnallocatedSkills() {
		return 0;
	}

	// CombatInfo
	@Override
	public int getDefenseBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 5 + lvl;
	}

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 2;
	}

	@Override
	public int getBowBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 5 + lvl * 3;
	}

	@Override
	public int getMovement() {
		return 10;
	}

	@Override
	public int getUnallocated() {
		return 3;
	}

	@Override
	public int getPerception() {
		return 7;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
