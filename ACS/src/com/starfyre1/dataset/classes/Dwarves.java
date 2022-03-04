/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

public class Dwarves extends BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Dwarves:

		Minimum Stat's Required:
		ST="12"   CN="13"  IN="10"  WS="10" DX="12"  BS="*" CH="**"  PA="**"  WP="12"

		* = NO Minimum Requirement  ** = NOT greater than 17

				Dwarves are a Stout and Hardy race, very stern and ominous.  Unless you
				get to know them well, then you will learn that they can be a very play-
				full and joking people.  They live mostly in Underground Citadels, although
				there are a few Dwarven communities above ground.  Each Dwarven family
				belongs to a clan, and each clan owes allegiance to a Devri, or Clan Chief.

				The Clan Chief's elect a War Chief, or Chancellor who for a term of 100 years
				rules the Council of Clan's.  Dwarves love everything that comes out of the
				ground, (Inanimate things anyway) Especially Gold, Silver, Jewels and
				their favorite, Mithril, or True Silver as some call it.  They are a Race that
				does Not change overnight, they have thousands of years of customs to
				abide by.

				The Dwarven race is respected by all races, including their enemies
				the Yrch & Dwg-Yrch or Orcs & Goblins.  There aren’t many things on this
				plane more Terrifying than a Dwarven Square marching on you, singing
				their Death songs. (For more information on the Seven Clans of Devris,
				See "The Book of Races" soon to be out, (Hopefully)).

		Other facts about Dwarves:

			1) 	Their Maximum Hit Bonus = 5 X ST
			2) 	Their Maximum Bow bonus = 2 X BS
			3) 	Their Missile Bonus doesn’t max out.
			4)	Dwarves gain +5% per level past first to their Combat skills.   A
				minimum of +1% must go towards their Hit Bonus, the other +4%
				may be split between the Hit Bonus and the Bow Bonus.
			5)	Dwarves have the ability to "Sense" when they are within 50' of
				precious metals.  They cannot pin-point exact locations, they just
				know that they are with-in 50'.  Chance = 50% + 5% per level.
			6)	Dwarves have a "Depth Sense", they always know how far under-
				ground they are.  Chance = 60% + 3% per level.
			7)	Dwarves can "Detect Traps" as the spell, as a innate ability.
			8)	Dwarves can "Detect Secret Doors" as the spell, as a innate ability.
			9)	Dwarves add their Willpower to all saves, with the Exception of
				Agility and Surprise.
			10)	They possess the ability of Appraisal, they can estimate the value
				of gems and jewelry accurately.  Chance = 90% + 1% per level.
			11)	Dwarves possess "Night Sight" as the spell, as a innate ability.
			12)	Dwarves receive a +5% with Axes, Warhammers & Maces.
			13)	Dwarves do have limitations as to how far they can progress in
				Combat Bonus', See following chart:

			14)	Dwarves are Uneasy around magic, they do NOT like displays of
				unnecessary magic.  The only mages that they seem to be comfortable
				with are the Druids, or Priest's of Wynd, Lorrell or Thantos.
			15)	Dwarves always know where they have been.  They keep a mental
				map of every turn, twist or curve in every passage they have ever
				been in.  Most Dwarves do not put a map on paper for that reason,
				their never lost.
			16)	Dwarves have several races that they dislike, Sithrian & Tsiri Elves.
				They also have several races that they Detest, Yrch, Dwg-Yrch and
				Tch-qetch. (Orcs, Goblins and Cave Trolls)
			17)	Dwarves are not a Flashy race, they are not likely to be carrying
				a staff firing fireballs or meteor swarms.  All of their magic is deep,
				rooted in the earth, nothing flashy, but powerful still.
			18)	At 5th level a player character should be between 70 & 100 years old,
				at this time you have the option of trying to create a magical weapon
				of choice, you may only do this ONCE, so do not waste the opportunity.
				This will require very expensive materials, (the better the material
				the better the weapon in most cases) See the chart in Magic Item
				area for more information.

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean[]	mInnateSkills		= { false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false };
	private String[]	mInnateDisplayList	= { "Detect Metal", "Depth Sense", "Detect Traps", "Detect Secret Doors", "Appraisal", "Night Sight", "Direction Sense" };					//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	private int[]		mMinimumStats		= { 12, 13, 10, 10, 12, 0, 0, 0, 12 };

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

			Dwarves:

				1)	Add +5% per level past (1st) to their Hit and Bow Bonuses.  They must add
					+1% to their Hit Bonus, the rest may be split up as they see fit.

				2)	Add +3% per level past (1st) to their Missile Bonus.

				3)	Add +4 Stamina per level until 10th level.  Add +2 Stamina points after 10th.

				4)	Add +1 Hit Point until 10th level, after 10th add +1 Hit Point every ODD level.

				5)	Add +1% to their Save Vs. Surprise.

				6)	Their Defense rises as their Hit Bonus does, but does NOT max out.  Free
					Attack goes up +1% per level.

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
	public int getBandaging() {
		return 0;
	}

	@Override
	public int getHerbalLore() {
		return 10;
	}

	@Override
	public int getPerception() {
		return 0;
	}

	@Override
	public int getHunting() {
		return 0;
	}

	@Override
	public int getTracking() {
		return 0;
	}

	@Override
	public int getDetectMagic() {
		return 0;
	}

	@Override
	public int getDetectMetals() {
		int base = 50;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return base + lvl * 5;
	}

	@Override
	public int getDetectSecretDoors() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getDetectTraps() {
		return 0;
	}

	@Override
	public int getAppraise() {
		return ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
	}

	@Override
	public int getDepthSense() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return 3 * lvl;
	}

	@Override
	public int getBerserk() {
		return 0;
	}

	@Override
	public int getConceal() {
		return 0;
	}

	@Override
	public int getStealth() {
		return 0;
	}

	@Override
	public int getHear() {
		return 20 + 0;
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
		// DW verify
		return 0;
	}

	@Override
	public int getOriginalHitBonus() {
		return 4;
	}

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 3;
	}

	@Override
	public int getBowBonus() {
		return -5;
	}

	@Override
	public int getMovement() {
		return 9;
	}

	@Override
	public int getUnallocated() {
		return 4;
	}

	// Saving Throws
	@Override
	public int getBleeding() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getMagic() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getPoison() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getShock() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getStress() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getUnconscious() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getBelief() {
		return ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.WP);
	}

	@Override
	public int getSurprise() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl;
	}

	// Defensive Information
	@Override
	public int getStanima() {
		int stanima;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		if (lvl <= 10) {
			stanima = (lvl - 1) * 4;
		} else {
			stanima = 36 + (lvl - 10) * 2;
		}
		return stanima;
	}

	@Override
	public int getHitPoints() {
		int hp;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();

		if (lvl <= 10) {
			hp = lvl - 1;
		} else {
			hp = 9 + (lvl - 9) / 2;
		}

		return hp;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
