/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.startup.ACS;

public class Sailor extends ElvesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
		Minimum Stat's Required:
		ST="10" CON="*" IN="11" WS=”10” DX="12" BS="12" CH="7" PA="11" WP="*"    *=Any

		Elves:		The most common of the Elves in Athri will be the Sailors or Foresters, since
					One of the Elves listed below cover the Foresters.  I’ll list the waters loving
					Sailor’s stats and bonuses below.  They will tend toward tall and willowy with
					Blonde, silver or silver / blue hair.  As with most Elves they gain +5% initially
					with Bows and +3% with Longswords, Sabers or Rapiers.  They gain 5% per
					level past 1st, 2% must go too their Bow bonus, the other 3% to be split between
					Hit & Bow bonuses.  They will gain 2% past 1st level to their Missile Bonus.

					Elves of all varieties are Magical in nature and may cast spells as a Mage or Priest.
					They may choose from these six areas of Magic - Natural Lore, the Elements of
					Air & Water and the Priest’s of Wynd, Lorrell or Chauntil if allowed in the
					campaign.

			1)	They may have a Maximum Hit Bonus of 3 X Strength
			2)	They may have a Maximum Missiles Bonus of 2 X Dexterity
			3)	They may have a Maximum Bow Bonus of 3 X Bow Skill
			4)	They are restricted in Magical area’s as follows:
					In = 11-12	Maximum level of Magic	 = 6th
					In = 13-14	Maximum level of Magic	 = 7th
					In = 15-16	Maximum level of Magic	 = 8th
					In = 17-18	Maximum level of Magic	 = 10th
					In = 19-20	Maximum level of Magic	 = 12th
					In = 21-22	Maximum level of Magic	 = 14th
					In = 23-24	Maximum level of Magic	 = 16th
					In =    25	Maximum level of Magic	 = 18th
			5)	They receive +4 stamina ad +1 hit point per level past 1st, this will stop after 12th level.
			6)	They may make (1) Magic Sword or (3) Magic Knives (see Rune of Combat I),
				This Bonus will be in effect as long as its’s a Elf that’s wielding the weapon.  This
				will cost the character 800 exp and take approximately 6 weeks to finish.  The
				character will not lose or use any Mana in there construction.
			7)	Elves do not need spell books to cast or remember their spells, it is a innate ability.
			8)	They gain 3% per level to their Herbal, Stealth, Conceal and Hear skills.
			9)	After they reach their maximum Hit bonus, they still receive their +5% per level
				Which Can be split between their Bow bonus, Defense or Casting Speed.
			10)	Elves will regain 1 stamina per 5 minutes instead of the standard 10 minutes.
			11)	They can “Detect Morals I” after speaking with someone for 5 minutes or longer.
			12)	The do not require a “Personal” focus as a mage does, however if the spell
				requires a Special Focus, the do have to manufacture one to cast that spell.
			13)	Elves possess “Star Sight” as a innate ability.
			14)	Unless slain, Elves in this world are Immortal.
			15)	Elves Detect Magic as a innate ability (50% + 5% per level)
			16)	They do not sleep like the rest of the races, instead they meditate under a slight
				Self-Hypnosis. In this state they are surprised +25% of the time instead of the
				Standard +50%.
			17)	They are 40% resistant to Magical Sleep spells.
			18)	They add +3% at the characters creation.

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean	mInnateSkills[]			= { true, false, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, true, false };
	// DW verify - 18) They add 3% at the characters creation... to what?
	private String	mInnateDisplayList[]	= { "Spell Casting", "Herbal Lore", "Stealth", "Conceal", "Hear", "Detect Morals I", "Star Sight", "Detect Magic", "Resist Magical Sleep 40%" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
	private int		mMinimumStats[]			= { 10, 0, 11, 10, 12, 12, 7, 11, 0 };

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

	@Override
	public int[] getMinimumStats() {
		return mMinimumStats;
	}

	@Override
	public String[] getInnateDisplayList() {
		return mInnateDisplayList;
	}

	// Skills
	@Override
	public boolean[] getInnateSkills() {
		return mInnateSkills;
	}

	@Override
	public int getConceal() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 3;
	}

	@Override
	public int getStealth() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 3;
	}

	@Override
	public int getHear() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return 30 + lvl * 3;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
