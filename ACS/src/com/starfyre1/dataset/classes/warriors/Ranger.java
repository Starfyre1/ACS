/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.warriors;

import com.starfyre1.startup.ACS;

public class Ranger extends Warrior {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
		The Ranger:

			Minimum Stat's Required for the King's Rangers:
			ST="12" CON="12" IN="11" WS="12" DX="12" BS="12" CH="10" PA="*" WP="10"
			* = Any

				There are two different Rangers in this game.  The King's Rangers
				who patrol the roads of Athri, dispensing the King's justice and helping
				any that need help.  They are all part of the Knights of Athri and
				have a very rigid code of honor and conduct.  These people are one step
				away from Paladins, they roam the roads and wilderness of the continent.


			Minimum Stat's Required for the Wilderness Ranger:
			ST="10" CON="10" IN="12" WS="11" DX="10" BS="12" CH="*" PA="*" WP="10"
			* = Any

				The other Rangers are a Wilderness type Warrior/Mage, they are excellent
				Hunters and Scouts.  They also have at least a small amount of Herb Lore,
				some are Herb Lore masters able to tell you the benefit or danger of any
				plant found in their Woodland Homes. They are the Helpers of Druid's and
				the Friends of Elves and are usually of Neutral or Good Morals.

			Other facts about Rangers:

				1)	Each level past 1st they receive +6% to their Combat Bonus'(+1%
					must go towards their Hit Bonus, and +1% must go towards their
					Bow Bonus, the other +4% may be split between the two as the
					player sees fit.)
				2)	They gain the Thieving Abilities of Stealth, Conceal and Climb at
					the base percentages.  They advance 5% per level.  They add +20%
					to these abilities when in a Wilderness setting, but they subtract
					-20% from Conceal when in a City setting in Daylight.
				3)	They may cast Natural Lore spells as a Mage 1/2 their level after
					4th level (Round Down).  They don't burn a stamina point when
					casting spells and they don't chant but merely concentrate and
					gesture.  When they are able to Acquire spells they Expend
					Experience points, as do Elves, to research them.
				4)	Rangers do Not require a personal focus (See Elves).
				5)	They add +10% to their Bleeding, Shock and Suprise saves.
				6)	They have "Tracking" as the spell, as a innate ability.
				7)	They have the skill, "Hunting" which works as the following:
					Base chance = Wisdom score as a percentage.
					+5% per level, including first.  Roll every three hours that you
					spend hunting.  Add +20% if they are hunting with a Spear or
					a Bow.  If they are successful, they have 1D6-1 days of food.
				8)	They receive +1D6 Stamina at first level.
				9)	They have the skill "Herb Lore", this adds to the food supply
					and gives the possibility of finding different useful Herbs,
					Healing and otherwise.
				10)	In all other aspects they are rated as Warriors (I.E. when
					figuring out their Hit Bonus add +5% to their Hit Bonus).
				11)	They add +3% to their Perception skill.

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

	@Override
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
	// Skills
	@Override
	public int getHerbalLore() {
		return 20;
	}

	@Override
	public int getHunting() {
		// DW Add +20% if they are hunting with a Spear or a Bow.
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getTracking() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 3;
	}

	@Override
	public int getBerserk() {
		return 0;
	}

	@Override
	public int getConceal() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 5;
	}

	@Override
	public int getStealth() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 5;
	}

	@Override
	public int getHear() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 5;
	}

	@Override
	public int getClimb() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 5;
	}

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 4 + lvl;
	}

	@Override
	public int getBowBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 3 + lvl;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
