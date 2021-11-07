/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.elves;

import com.starfyre1.startup.ACS;

public class Tsiri extends ElvesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Minimum Required Stats;
		ST=”12 CN=”10” IN=”13” WS=”12” DX=”14” BS=”13” CH=”10” PA=”*” WP=”12”
	
				The Tsiri Elves, or Dark Elves as they are called, are split into 2
				distinct branches.  One lives above ground, often close to a
				Sithrian village or city, they’re are Very Few of these, most of the
				Tsiri live Under-Ground, only coming out in the cover of night
				too raid and pillage those around them.  Needless to say, most
				people's natural reaction to a Tsiri Elf is Distrust, if not out-
				right attacking them on sight.
	
				Any player character wanting to use this race will face allot of hardships,
				along with disabilities.  Tsiri are the Shortest of the Three types of Elves
				I've told you about, standing between 5' to 6' tall. Their skin is Ebony color,
				their hair a pale white, or blond, with the occasional black.  Eye color
				ranges from, silver, purple, blue, gray, gold or Clear.  They receive the
				Thieving skills of Stealth, Conceal, Hear, Climb, and Find & Remove
				Traps at basic percentages.
	
				The Tsiri are very good with Small hand held Crossbows,
				and thrown objects, they have a +5% to their Missile bonus
				and will receive a +5% when they use their Hand Crossbows.
				They gain +7% per level of Experience past 1st, 1% must go
				into Hit bonus and 3% must go into Missile bonus, the other
				3% can be split up as the player chooses.  They receive a +2%
				per level to their Bow Bonus.
	
				They like their cousins are Magical by nature, and may cast
				spells like a Mage.  They may enter Nine Areas of Magic,
				Natural Lore, The Elements of Air & Earth, or the Priest's
				of Wynd, Knull, Graun or Narese,.  They have Restrictions on their
				Combat and Magical powers as to how far they may Rise.
	
			1)	Tsiri may have a Maximum Hit Bonus = 5 X Strength.
			2)	Tsiri may have a Maximum Bow Bonus = 4 X Bow Skill.
			3)	Their Missile and Defense are Not Restricted in anyway.
			4)	The Tsiri have the same limitations as the Tellorians in
					regards to Magic. ( See Tellorian)
	
		Other Facts about the Tsiri:
	
			1)	The Tsiri receive +5 Stamina and +1 Hit point past 1st level. After 10th
				level they stop adding +5 to Stam and start adding +2 per level.  After 16th
				level they stop adding +1 to Hit points.
			2)	The Tsiri may Not have a Strength above "18".
			3)	The Tsiri Elves add +5% to their Defense and have a additional +5% when
				they are using Swords & Knives (as shown above).
			4)	The Tsiri may make ( 1 ) Magic Sword or ( 2 ) Magic Knives of a +2 (See
				Rune of Combat II) Once in their lives.  Their Magic has a limitation
				on it however.  These Weapons only react magically when wielded by a
				Elf, and only act as a +2 at night or under-ground.  Otherwise it will react
				as a +1 weapon in an Elvish hand.  (See Rune of Combat I).
			5)	Elves do Not need books to either cast or remember their spells.
			6)	The Tsiri, like their cousins spend Experience to research their spells.
				They spend time outside at night under the sky, or if your the Underground branch
				of the family, you would spend time in solitude in a	dark cavern, contemplating
				the Planes of Existence.
			7)	The Tsiri gain +20% to be split among their Thieving abilities. A
				maximum of +5% can be added to any 1 ability.
			8)	The Under-ground Tsiri are Never lost in the Under-world, they, like
				the Dwarves, always know where they are.
			9)	After they have reached their Maximum Hit bonus, they continue to
				gain their +7% per level, but now they can only be split between they’re
				Defense and their Missile bonus.  +1% must go towards their Defense,
				+3% must go towards their Missile bonus, the other +3% can be split
				between them.
			10)	Tsiri gain 1 Stamina per 5 minutes of rest, as long as they are in shadows,
				or it is night.  Otherwise they gain 1 Stamina every 10 minutes like the
				other races.
			11)	Tsiri may "Detect Secret Doors" as the spell, as a Innate ability.
			12)	Tsiri do Not require a personal focus, however if a spell requires a
				Special focus, they must manufacture one. (Any spell with a "F" after
				the casting time requires a Special Focus).
			13)	Tsiri may "Speak W/Animals", Or "Speak W/Monster", depending
				on which branch of the family you are in.  As the spell, as a Innate ability.
			14)	The Tsiri possess "Night Sight" as the spell, as a Innate ability. They
				also have a -10% to all Combat adds during Noon-time sun, and a -5%
				during Daylight hours, unless in the shade.
			15)	Their skin is Very sensitive too Sunlight, getting burned very easily,
				actually taking damage if forced out into daylight without protection.
				-1Hp per Hour of Direct Sunlight on bare flesh.
			16)	The Tsiri are Immortal like their cousins, however, they rarely live over
				500 years.
			17)	They may "Detect Magic" as the spell, as a Innate ability.  (50% + 5%
				per level of Experience.)
			18)	They do not sleep, but rather meditate, putting themselves into light
				self-hypnotic states.  In this way they are only surprised +25% of the
				time, instead of the regular +50% of the time.
			19)	The Tsiri are 60% Magic Resistance to Sleep spells.
			20)	Because of the Viscousness of this Race, there is a 50% chance at
				anytime that whatever traveler you meet, will either Run or Attack
				you on site
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean mInnateSkills[] = { true, false, false, true, false, false, true, false, false, false, true, true, true, false, false, true, true, true, false, false };

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
	public boolean[] getInnateSkills() {
		return mInnateSkills;
	}

	@Override
	public int getDetectSecretDoors() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
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
		return 30 + 0;
	}

	@Override
	public int getUnallocatedSkills() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 20;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
