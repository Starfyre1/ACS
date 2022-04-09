/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.startup.ACS;

public class Tellorian extends ElvesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
		Minimum Stat’s Required:
		ST=”12” CON=”*” IN=”12 WS=”13” DX=”13” BS=”*” CH=”10” PA=”11” WP=”11”
	
				Tellorian’s are the most accessible of Elven kind, they are not
				shy, and trade with humans and other races at any opportunity.
				They tend to be tall, and powerfully built, there are a lot of Smith's,
				and Craftsman among them.  They are the ones that you will here
				the most about.  They gain the Dwarven skill of Appraisal and
				the Thieving skills Stealth, Conceal and Hear at the basic
				percentages. They are also known as White, or Noble Elves, by
				some other races.
	
				Unlike their Cousins, they are not primarily Archers, gaining only +2%
				Initially with a Bow.  They gain a +5% with Long & Broad swords and
				+7% per level past first, 1% of which must go towards their Bow bonus and 3%
				towards their Hit bonus. The other 3% can be split as the character chooses.  They
				gain 2% per level past first towards their Missile bonus. They, like all Elves are
				Magical in nature, and may Cast spells like a Mage or Priest.  They may choose
				from Seven areas of Magic - Arcane Lore, The Elements-Earth, Air & Fire or
				the Priest's of Thantos, Sautarian & Narius.  Elves are restricted on how
				far they can progress in Combat skills and Magical powers.
	
			1)	Tellorian Elves may have a Maximum Hit Bonus of  5 X Strength
			2)	Tellorian Elves may have a Maximum Missile Bonus of  4 X Dexterity
			3)	Tellorian Elves may have a Maximum Bow Bonus of  3 X Bow Skill
			4)	Elves are restricted in Magical area's as follows:
					In = 11-12	Maximum level of Magic	 = 6th
					In = 13-14	Maximum level of Magic  = 8th
					In = 15-16	Maximum level of Magic  = 10th
					In = 17-18	Maximum level of Magic  = 12th
					In = 19-20	Maximum level of Magic  = 14th
					In = 21-22	Maximum level of Magic  = 16th
					In = 23-24	Maximum level of Magic  = 18th
					In = 	25	Maximum level of Magic  = 22th
	
		Other Facts about Tellorian Elves:
	
			1)	They receive +4 stamina per level past 1st and +1 Hit points.  After 10th
				level they stop adding +4 and start adding +2 to Stamina.  After 15th level
				they stop receiving the additional Hit points.
			2)	As shown above they gain a +5% with Longswords & Broadswords.
			3)	They receive a +5% to their Defense and their Save Vs. Magic
			4)	They may make (1) Magic Sword or (5) Magic Knives of +1
				(see Rune of Combat I), the bonus' will only be applicable if they
				are being used by a Elf.  Either 1 sword or 5 knives takes approximately
				3 weeks and cost's the Tellorian 700 Experience points.  They do NOT
				lose any Mana when making these weapons, but remember this is a
				ONE time thing.
			5)	Elves in general do Not need a spell books to cast or remember they’re
				spells.  It is a Innate Ability.
			6)	They Gain +5% per Level to their Appraisal, Stealth, Conceal and Hear Each.
			7)	After they have reached their Maximum Hit bonus they still receive
				their +7% per level, which is split into their Defense and Bow Bonus.
			8)	Elves gain 1 stamina per 5 minutes of rest, instead of 10 minutes.
			9)	Tellorian Elves may "Detect Morals I" after speaking with someone
				for 5 minutes or longer.
			10)	Elves do NOT require a " Personal Focus " as a Mage does, however
				if a spell Requires a Special Focus, they do have to manufacture one.
			11)	Elves possess "Star Sight" as a Innate Ability.
			12)	Elves in my world are Immortal, although they may be slain.
			13)	Elves Detect magic as a Innate Ability. (50% +5% per Level)
			14)	They do not sleep like the rest of the races of Athri, instead they
				Meditate under a slight self-hypnosis.  In this state they are surprised
				+25% of the time instead of the standard +50% of the time.
			15)	They are 50% resistant to Magical Sleep spells.
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean[]	mInnateSkills		= { true, false, false, true, true, false, false, false, true, false, true, true, true, false, false, false, false, false, true, false };
	private String[]	mInnateDisplayList	= { "Appraisal", "Detect Magic", "Hear", "Stealth", "Conceal", "Detect Morals I", "Detect Secret Doors", "Armor Smith", "Star Sight", "Weapon Smith", "Spell Casting", "Resist Magical Sleep 50%" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
	private int[]		mMinimumStats		= { 12, 0, 12, 13, 13, 0, 10, 11, 11 };

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
	public int getAppraise() {
		return ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
	}

	@Override
	public int getConceal() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getStealth() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getHear() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return 30 + lvl * 5;
	}

	// Saving Throws
	@Override
	public int getMagic() {
		return 5;
	}

	// CombatInfo
	@Override
	public int getDefenseBonus() {
		return 5;
	}

	@Override
	public int getHitBonusMax() {
		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.STR);
		return value * 6;
	}

	@Override
	public int getMissileBonusMax() {
		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		return value * 4;
	}

	@Override
	public int getBowBonusMax() {
		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.BOW);
		return value * 3;
	}

	// Defensive Information
	@Override
	public int getStamina() {
		int stamina;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		if (lvl <= 12) {
			stamina = (lvl - 1) * 4;
		} else {
			stamina = 44 + (lvl-12) * 2;
		}

		return stamina;

	}

	/*
	 * They receive +4 stamina per level past 1st and +1 Hit points. After 10th
	 * level they stop adding +4 and start adding +2 to Stamina. After 15th level
	 * they stop receiving the additional Hit points.
	 */
	@Override
	public int getHitPoints() {
		int hp;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();

		if (lvl <= 15) {
			hp = lvl - 1;
		} else {
			hp = 14;
		}

		return hp;

	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
