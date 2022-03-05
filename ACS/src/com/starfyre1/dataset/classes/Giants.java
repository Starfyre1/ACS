/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.dataset.classes;

import com.starfyre1.dataset.common.BaseClass;

public class Giants extends BaseClass {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
			E’Sprey (Giants):
	
			Minimum Required Stats:
			ST=“18” CN=“18” IN=“10” WS=“12” DX=“15” BS=“*” CH=“*” PA=“*” WP=“12”
	
			The E’Sprey are a race of Giants, they are Athri’s greatest sailors. Traveling on their stone Giant-
			Ships is the safest way to travel the Oceans of the world, even the Kraken think twice about
			attacking those ships. They appear as extra large humans standing 11’-18’ tall (10’ + 1D8),
			weighing about 57.41 lbs per foot of height or 572-1028 lbs.
	
			The E’sprey get along with most neutral and good races, although they don’t especially like some
			of the Elven races. Their favorites are the wee folk, the D’evri, their greatest racial enemies are
			the Ogris & Tch-Quetch along with most goblin kind. Outside of port cities, the E’sprey are not
			seen often, thus their large size creates terror when they are seen. They will either be run from or
			attacked on sight 50% of the time. As long as they only defend, there is a 75% chance their
			attackers will listen to reason.
	
			They mostly sail between N’Tal and T’sal, however they do sometimes sail as far as Athri and
			Tetsuru. Their ships are feared world wide by pirates and raiders, they will Always help ships
			being attacked or stranded. Being known to hunt down those raiders after helping their victims
			and returning the goods or prisoners that were taken. They leave no survivors among those they
			hunt.
	
			They can be Warriors, Rangers or study these areas of magic - Natural Lore, any of the
			Elements or the Priesthoods of Adon, Tarn, Chauntil, Wynd or on a very rare occasion Orn.
	
			1) Max Hit Bonus = 5 X ST E’sprey Innate Abilities:
			2) Max Missile Bonus = 3 X DX Star Sight ( per spell )
			3) Max Bow Bonus = 3 X BS Swimming ( 75% + 5% per lvl )
			4) At creation add 1D8 Hit Points Sleep/Charm ( 50% resistant )
			5) They add a +5% to Swords and Maces at character creation.
			6) All materials purchased outside of the Giant Hold will cost 3.5 X the SP cost.
			8) Due to their thick skin they have a natural absorb or ( 1 ) against piercing / slashing
			attacks. But ( 3 ) against crushing attacks due to their bone and flesh density.
			9) Due to their great size add 6 to any weapon length they use, almost ensuring the first
			attack of the combat.
	
			(After meeting the minimum stat’s requirement the player must understand both the bonus’s
			and limitations they have. The character is so large, it’s impossible to sneak up on anyone
			and they are a large target for missile weapons. They do not fit into most Inn’s or Hostel’s
			not to mention Caves or Dungeon complexes. Their appearance will scare the locals until
			they are recognized as not a threat. These are just a few of the drawbacks, your DM will
			find many more.)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean	mInnateSkills[]			= { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };
	private String	mInnateDisplayList[]	= { "Star Sight", "Swimming", "Resist Sleep/Charm 50%" };																							//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	private int[]	mMinimumStats			= { 18, 18, 10, 12, 15, 0, 0, 0, 12 };

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

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
		return 0;
	}

	@Override
	public int getBandaging() {
		return 0;
	}

	@Override
	public int getDepthSense() {
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
	public int getDetectTraps() {
		return 0;
	}

	@Override
	public int getHerbalLore() {
		return 0;
	}

	@Override
	public int getHunting() {
		return 0;
	}

	@Override
	public int getPerception() {
		return 0;
	}

	@Override
	public int getDetectSecretDoors() {
		return 0;
	}

	@Override
	public int getTracking() {
		return 0;
	}

	@Override
	public int getBerserk() {
		return 0;
	}

	@Override
	public int getClimb() {
		return 0;
	}

	@Override
	public int getConceal() {
		return 0;
	}

	@Override
	public int getHear() {
		return 0;
	}

	@Override
	public int getStealth() {
		return 0;
	}

	@Override
	public int getUnallocatedSkills() {
		return 0;
	}

	@Override
	public int getDefenseBonus() {
		return 0;
	}

	@Override
	public int getHitBonus() {
		return 0;
	}

	@Override
	public int getOriginalHitBonus() {
		return 0;
	}

	@Override
	public int getMissileBonus() {
		return 0;
	}

	@Override
	public int getBowBonus() {
		return 0;
	}

	@Override
	public int getMovement() {
		return 0;
	}

	@Override
	public int getUnallocated() {
		return 0;
	}

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
		return 0;
	}

	@Override
	public int getBelief() {
		return 0;
	}

	@Override
	public int getStamina() {
		return 0;
	}

	@Override
	public int getHitPoints() {
		return 0;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
