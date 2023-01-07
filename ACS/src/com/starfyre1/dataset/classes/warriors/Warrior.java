/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.warriors;

import com.starfyre1.dataModel.LanguageRecord;
import com.starfyre1.dataset.classes.common.BaseClass;
import com.starfyre1.startup.ACS;

import java.util.ArrayList;

public class Warrior extends BaseClass {
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
	private boolean	mInnateSkills[]			= { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true };
	private String	mInnateDisplayList[]	= { "Berserk" };																																//$NON-NLS-1$
	private int		mMinimumStats[]			= new int[] { 10, 10, 0, 0, 0, 0, 0, 0, 0 };

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
		return 0;
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
	public int getBerserk() {
		// DW todo
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
		return 0;
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
		return 0;
	}

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
		// DW verify that warriors don't have a level bonus
		return 3;
	}

	@Override
	public int getMovement() {
		return 12;
	}

	@Override
	public int getUnallocated() {
		return 4;
	}

	// Saving Throws
	@Override
	public int getBleeding() {
		return 10;
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
		return 10;
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
		return lvl;
	}

	@Override
	public int getBelief() {
		return 0;
	}

	// Defensive Information
	@Override
	public int getStamina() {
		int stamina;
		int lvl = ACS.getInstance().getCharacterSheet().getHeaderRecord().getLevel();
		if (lvl <= 10) {
			stamina = (lvl - 1) * 5;
		} else {
			stamina = 45 + (lvl - 10) * 2;
		}
		return stamina;
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

	// CombatInfo
	@Override
	public int getHitBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.STR);
		//		return value * 3;
		// No Max;
		return -1;
	}

	@Override
	public int getMissileBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		//		return value * 3;
		// No Max;
		return -1;
	}

	@Override
	public int getBowBonusMax() {
		//		int value = ACS.getInstance().getCharacterSheet().getAttributesRecord().getModifiedStat(AttributesRecord.BOW);
		//		return value * 3;
		// No Max;
		return -1;
	}

	// Languages
	@Override
	public ArrayList<String> getLanguages() {
		ArrayList<String> languages = new ArrayList<>();

		languages.add(LanguageRecord.LANGUAGES[0]);

		return languages;

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
