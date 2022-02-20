/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;

public class Thief extends BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		The Thief
	
			The Thief is quite similar to the Fighters in that they also don't have any spells
			to aid them in the RPG Systems, although you will find in this system there are
			at least 2 other classes tied to the Thief that do have spells and a lot of Magic.
			Unlike the fighter the thief relies on his senses, stealth and a quick mind to
			get him into and out of Trouble.  The thief has the option to join the Thieves
			Guild.  Doing so will give the thief +1% on all his Thieving Abilities each
			experience level.  There is a membership fee of 10% of all the Thief's Earnings.
			"The Guild has a habit of Eliminating non-guild members" Guild thieves are
			loyal and will report non-guild thieves or any thieves shorting the guild of
			their dues, With some exceptions.  The Thieves Guild also runs the Assassins
			Guild. Thieves advantages are as follows:
	
			1)	They may wear any armor, but much of the Bulkier armor types hinder
	  			their abilities.
			2)	They may see 1' X level in total darkness.
			3)	They may use any weapons they can find, although they do receive a -5%
	    		with any 2- handed weapon.
			4)	They receive +4% per level to combat adds, +1% of this must go to they’re
	    		Hit Bonus, the rest can be divided between Hit & Bow Bonus'.
			5)	They receive +3% per level to their Missile Bonus.
			6)	They add +10% to their Defense when choosing to "Dodge".
			7)	They add +2% per level to their save vs. Surprise.
			8)	They add +5% to their Perception at character creation.
	
		Thieving Abilities:
	 		These are the Base percentages for beginning Thieves:
	
			Conceal  Stealth  	Hear	Lock pick	Pick pocket	Climb	Find trap
			20%	20%	30%	20%		20%		70%	20%
			Remove Trap
			20%
	
		Dexterity Modifiers:
	
			1)	Conceal, Stealth, Pick Locks, Pick Pocket, Climb and Remove trap are all
	  				modified with the following tables:
	
	
	
			DX= 		Modifier on Abilities		DX Modifiers due to Armor
			3-7 		-20% to all abilities 		No Armor	+2*
			8-9 		-10% 						H. Cloth	+1*
			10-12 		-5% 		  				S. Cloth	+1
			13-14		+0%   						Leather		+0
			15-16		+5%   						Lacquered	+0
			17-18 		+10% 					 	Studded		-0
			19			+15% 						Ring		-1
			20 			+20% 						Chain		-2
			21			+25% 						Scale 		-4
			22-23		+30% 						Banded 		-6
			24-25 		+35% 						Plate 		-8
	
			2)  	Find trap is Modified as follows:		* =Max. of "24" DX.  If "DX" is less than "3", then NO Thieving
			WS + IN 	Modifier on Abilities		abilities.
			6-12 		-20%
			13-14		-15%
			15-16 		-10%				Note:	DX Modifiers apply ONLY
			17-18		-5%					to Thieving Abilities (Not
			19-23		-0%					combat or anything else.)
			24-26		+5%
			27-29		+10%
			30-32		+15%
			33-35		+20%
			36-above 	+25%
	
		Here are the Definitions of Thieving Abilities:
	
			Conceal:  	To hide behind curtains, around corners, in nooks and crannies,
					behind pillars or statues, Etc.  This ability does NOT grant
					invisibility to the concealed thief.  If a creature is aware of the
					possibility of a thief hanging around, Halve the percentage chance
					for success, (Suspicious parties must have a reason for suspecting
					thieves and may not simply turn this ability "on" why they want
					it).
	
			Stealth:  	This ability allows the Thief to move with total silence at a rate One
					class slower than they regularly move (due to their encumbrance).
					This means if the thief was human and regularly moved "12", they
					will move "9" when attempting "Stealth".  Failure to roll the stated
					chance does NOT mean the party heard the thief (see thieving
					ability "Hear" to find non-thief chances to hear something that
					occurs Quietly).  Successfully rolling the percentage means the
					thief moved with absolute silence.
	
			Hear:  		The percentage chance to hear something is obviously going to be
					heavily modified by the ref.  Discretion as to the loudness of the
					noise (I.E. a Woman screaming 60' away (or even "out of range at
					70') is tough to miss hearing).  Here are the chances....

			Listener is:				Chance to Hear 		Range
			Human, Non-thief with a save		20%			60'
			Vs. Surprise of less than 75%
	
	
			Human, Non-thief with a save		30%			60'
			Vs. Surprise of more than 75%
	
			Elven, Half-Elven and Dwarrow		30% 			120'
	
			Other--(some monsters will have		20%			60'
			high hearing abilities)
	
			Thief					Varies with Level	60' *
									•	Add 5’ per 10% above 100% to hear.
	
									Halve all chances when listening to a door.
	
			Pick Lock:  	Just like it sounds.  This ability requires special lock picking
					tools (available on the buying lists (only thieves may purchase
					them)). Locks vary in toughness to pick.  If a roll fails to pick the
					lock, another attempt may be made in 2D10 minutes (but only if
					the thief continues to try for the entire time).
	
			Pick Pocket:	This ability includes purse cutting and jewelry snatching.
					Failing to roll the percentage (modified by level)  by more than
					20% means the victim noticed the attempt.  Failure to roll by less
					than or equal to 20% means the attempt failed, but the victim didn't
					notice.  A thief may "fail" in this manner and still take the Item,
					but the victim will be totally aware the item was stolen (and by
					whom).  Subtract 5% for each point of In or Ws the "Mark" has
					above "14".
	
			Climb:  		"No, you're NOT Spiderman!"  Although, in dungeons with huge
					carved blocks set in the walls (with lots of handy grooves to put
					fingers in) Might only subtract 10% to 20% (Ref.'s Discretion).  A
					Thief climbs at a rate of "3" and may not carry more than his carry
					(I.E. Un-encumbered).  A "00" rolled when climbing
					means failure (No Matter what the thief’s chances).  If the thief
					fails to successfully roll his chance to climb, he must roll a save Vs.
					Agility.  If the thief fails his save, he falls to the ground (taking
					1D6 pts. of damage per 10' of fall).  If the thief makes his save, he
					realizes the climb is too dangerous and must immediately re-trace
					their steps.  The thief must roll his Climb for every 10’.
					(The Ref. should encourage the use of grappling hooks and Iron
					Spikes).
	
			Find Traps:	Roll 1D100 and if the thief rolls his percentage to Find Traps,
					then he has found the triggering device.  If he successfully rolls it
					Again, then he may add 20% to any attempt to Remove the Trap
					(on that specific trap).  Failure to roll the Find trap percentage
					has a 30% chance (Ref.'s Discretion) of triggering the trap
	
			Remove Traps:  Only small sized traps are removable, although the Ref. might
					consider methods the thief could use to decommission larger traps
					(like finding the lever the creatures that use the passageway pull
					to prevent the pit from opening).
	
	
	
	
		Advancing in Levels of Experience as a Thief:
	
				Other than the usual bonuses for going up a level of experience (I.E. more hits
				and stamina, higher hit bonuses, etc.)  the Thief also gains +25% to his thieving
				abilities (Total).  He may divide this bonus between all of his abilities as he sees
				fit, putting a minimum of 0% on each one and a maximum of 5%.  He gains this
				+25% at each new Level of Experience. (optional:  Give this +25% bonus to level
				one thieves in order to allow the player to develop the thief's own distinct
				characteristics.
	
				Note: 	A "96-00" always indicates Failure to perform any thieving ability
					except Climbing (which is failed on a "00" (See Climb)).
	
	 */

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean[]	mInnateSkills		= { false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, false, false };
	private String[]	mInnateDisplayList	= { "None" };																																//$NON-NLS-1$
	private int[]		mMinimumStats		= { 9, 10, 12, 0, 12, 0, 0, 0, 0 };

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

				Thieves:

					1)	Add +4% per level past (1st) to be divide between their Hit and Bow
						Bonuses.  At least +1% per level MUST go into their Hit Bonus, the other
						+3% may be divide as they wish.

					2)	Add +3% per level to Missile Bonus.

					3)	Add +4 Stamina per level past (1st) until 10th level.  After 10th level add
						+2 Stamina per level.

					4)	Add +1 Hit Point per level until 10th level, after 10th add +1 Hit Point for
						every (3) levels gained.

					5)	Add +2% to their Save Vs. Surprise.

					6)	Thieves Defense rises the same as their Hit Bonus, and their Free Attack
						Rises +1% per level past (1st).
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
		return 0;
	}

	@Override
	public int getDepthSense() {
		return 0;
	}

	@Override
	public int getHerbalLore() {
		return 0;
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
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return lvl * 5;
	}

	@Override
	public int getHear() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return 30 + lvl * 5;
	}

	@Override
	public int getClimb() {
		return 0;
	}

	@Override
	public int getUnallocatedSkills() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl * 25;
	}

	// CombatInfo
	@Override
	public int getDefenseBonus() {
		return 0;
	}

	@Override
	public int getOriginalHitBonus() {
		return 3;
	}

	@Override
	public int getHitBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return lvl;
	}

	@Override
	public int getMissileBonus() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel() - 1;
		return 4 + lvl * 3;
	}

	@Override
	public int getBowBonus() {
		return 0;
	}

	@Override
	public int getMovement() {
		return 12;
	}

	@Override
	public int getUnallocated() {
		return 3;
	}

	@Override
	public int getPerception() {
		return 5;
	}

	// Saving Throws
	@Override
	public int getBleeding() {
		return 0;
	}

	@Override
	public int getMagic() {
		return 0;
	}

	@Override
	public int getPoison() {
		return 0;
	}

	@Override
	public int getShock() {
		return 0;
	}

	@Override
	public int getStress() {
		return 0;
	}

	@Override
	public int getUnconscious() {
		return 0;
	}

	@Override
	public int getSurprise() {
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		return 2 * lvl;
	}

	@Override
	public int getBelief() {
		return 0;
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
