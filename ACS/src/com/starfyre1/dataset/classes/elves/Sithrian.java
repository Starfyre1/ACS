/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.startup.ACS;

public class Sithrian extends ElvesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Minimum Stat’s Required:
		ST=”10” CON=”12” IN=”12” WS=”13” DX=”14” BS=”13” CH=7” PA=”10” WP=”11”
	
				Sithrian Elves are Nature lovers, you rarely find one outside the
				forest, or on the oceans.  They are the Green or Blue Elves, or Wood
				and Water Elves.  Both are very Secretive and don't have much to
				do with most other races.  They Gain the Thieving abilities of
				Stealth, Conceal, Hear and Herbal Lore at the Basic percentages.
				They are both Excellent Archers, gaining a +5% with any Bow.
	
				They are a Tall and thin in appearance, with hair of Honey or Silver,
				eyes of Blues, Greens, Gray's, Silver, Gold's, and Browns. Their
				complexions are deeply tanned by the elements.  The gain +7% per
				level past first, 1% of which must go into Hit bonus, 3% of
				it must go towards Bow bonus.
	
				The other 3% can be split between these two at the players discretion.
				Their Missile bonus goes up 2% per level.  They, like their cousins are Magical in
				nature and may cast spells.  They can choose from eight area's of  Magic,
				Natural Lore, The Elements-Earth-Air-Fire-Water, or the Priest's of Sautarian,
				Chauntil or Lorrell.  Sithrian Elves, like Tellorian Elves are restricted as to
				how far they can progress with their Combat and Magical skills.
	
	
	
	
	
	
			1)	Sithrian Elves may have a maximum Hit bonus of 5 X Strength.
			2)	Sithrian Elves may have a maximum Missile bonus of 5 X Dexterity.
			3)	Their Bow bonus and their Defense have No restrictions on them.
			4)	Elves are Restricted in their magical studies as the Tellorian
				Elves. (See Tellorian Elves).
	
	Other Facts about Sithrian Elves:
	
			1)	Sithrian Elves receive +4 per Stamina level past 1st, and +1 Hit point.
				After 12th level, they stop adding +4 Stamina, instead they add +2 Stamina.
				After 15th level they stop receiving additional Hit points.
			2)	Sithrian Elves may Not have a Strength above "18".
			3)	Sithrian Elves have a +5% to their Defense and +5% to fighting with
				Swords or knives.
			4)	Sithrian Elves may make ( 1) Magic Sword, ( 10 ) Magic Arrows, or ( 5 )
				Magic Knives at a +1 bonus.  (See Rune of Combat I)  The Bonus only
				works when used by an Elf.  Any of the Three choices will take the Elf
				(3) weeks to complete and cost him 700 Experience points.  They will
				Not cost them any Mana points when making these weapons.
			5)	They do Not need any books to either Cast or Remember their spells
				from.
			6)	When Researching spells, Sithrian Elves spend Experience points
				instead of silver to gain their spells.  They still spend the Research
				time, but instead of being in a stuffy library, they are out in nature,
				thinking on the mysteries of the Planes.
			7)	Sithrian's gain +5% to their abilities of Stealth, Conceal and Hear per
				level of Experience.
			8)	Sithrian's also gain "Tracking" as the Spell, as a Innate Ability.
	
			9)	After reaching their Maximum Hit bonus, they will still receive +7%
				per level.  They may split this between Defense and their Bow bonus,
				1% must go into Hit bonus to Raise Defense.
			10)	They regain 1 Stamina point per 5 minutes of rest instead of the 10
				minute standard.
			11)	They may "Detect Morals I" when they have talked to someone for
				5 minutes or more, as per spell, as a Innate Ability.
			12)	They do Not require a Personal Focus, however, if a spell requires
				a Special Focus, they are required to make it and use it.
			13)	They may "Speak with  Animals" as the spell, as a Innate Ability.
			14)	They possess "Star Sight" as the spell, as a Innate Ability
			15)	They are Immortal, although they can be slain in combat.
			16)	They may "Detect Magic" as the spell, as a Innate Ability.  (50%+5%
				per level)
			17)	They do Not sleep, rather they meditate under a light Self-Hypnosis.
				In this state they are surprised +25% of the time, instead of +50% of
				the time.
			18)	The Sithrian Elves have a 50% Magic Resistance to Sleep spells.
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean[]	mInnateSkills		= { true, false, true, true, true, false, false, false, false, false, true, true, true, false, false, false, false, false, true, false };
	// DW Verify - Should tracking be herbal lore?
	private String[]	mInnateDisplayList	= { "Spell Casting", "Herbal Lore", "Stealth", "Conceal", "Hear", "Tracking", "Detect Morals I", "Speak with Animals", "Star Sight", "Detect Magic", "Resist Magical Sleep 40%" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
	private int[]		mMinimumStats		= { 10, 12, 12, 13, 14, 13, 7, 10, 11 };

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
	public int getTracking() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 3;
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

	// CombatInfo
	@Override
	public int getDefenseBonus() {
		return 5;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
