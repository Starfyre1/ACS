/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Pelon extends PriestsBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Priest
	Second Requisite = Wisdom
		The Priest of Athri have many different magic's and law's, each are strict
		in their own ways.  Some factions are intolerant of other's like Narese the
		Goddess of Pain, or very tolerant like the Priest's of Narius the God of
		Knowledge.  Here are the Gods and their Priest's Spells.
	
		Sautrian
		Second Requisite - Charisma

			Sautarian is the Goddess of Light, needless to say she is in all respects
			a Good Goddess.  Priest of Sautarian are allowed to begin playing wearing some
			armor and fighting with some weapons, however, they must Renounce all
			Physical armor and weapons when they cast their 1st Power Five Spell.

			They may still carry a staff and may use it upon occasion, but that is it.  They
			Must relinquish ALL material possessions, except 3 non-weapon Magic items before
			casting their 1st Power Six Spell.  To cast their 1st Power Seven spell, they must go on
			Holy Quest for the Church and Renounce their 3 magic items.  Their morals must be at
			Least “50”, at this point the character is a “Chosen” of Sautrarian.

			Power Zero:						Power One:
			1)Golden Touch (-3)F			1)Blinding Eyes (-1)
			2)Judgment  (-5)				2)Healing (-15)F
			3)Light (-1)					3)Perpetual Light (-5)
			4)Protection I (-3)				4)Protection II (-6)
			5)Protection : Dark (-5)		5)Protection : Undead (-3)F
			6)Turn Undead (-3)F				6)See Invisibility (-10)
											7)Shield of Light (-5)
			Power Two:						8)Star Sight (-10)
			1)Blessing of Light (-30)		9)Turn Curse (-0)
			2)Familiar (-lots)
			3)Glowing Mists (-3)			Power Three:
			4)Healing Glow (-10)F			1)Blinding Light (-3)
			5)Light Flash (-3)F				2)Illusions (-30)F
			6)Protection III (-9)			3)Night Sight (-30)
			7)Remove Curse (-30)			4)Protection IV (-12)
			8)Turn Demons (-6)F				5)Spears of Light I (-3)F
	
			Power Four:						Power Five:
			1)Commune (-lots)				1)Call from Shadows (-600)
			2)Gift of Sight (-30)			2)Protection VI (-18)
			3)Healing Rays (-15)			3)Spears of Light II (-4)F
			4)Pass Darkness (-6)F			4)Temple of Light (-120)
			5)Protection V (-15)			5)Wall of Brilliance (-10)
			6)Protection Aura (-30)
			7)Vitality (-30)F				Power Seven:
											1)Call Daylight (-6)
			Power Six:						2)Call to Law (-30)
			1)Aura of Sautarius (-360)		3)Transformation (-lots)
			2)Gift of Sautarius (-120)
			3)Hold sun (-120)
			4)Holy Sword (-lots)
			5)Protection VII (-21)
			6)Raise Army (-lots)
			7)Rune : Slay Undead (-lots)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Pelon() {
		// Sautrian
		mSecondRequisite = AttributesRecord.CHARISMA;
		// Sautarian is the Goddess of Light, needless to say she is in all respects
		// a Good Goddess.  Priest of Sautarian are allowed to begin playing wearing some
		// armor and fighting with some weapons, however, they must Renounce all
		// Physical armor and weapons when they cast their 1st Power Five Spell.
		// They may still carry a staff and may use it upon occasion, but that is it.  They
		// Must relinquish ALL material possessions, except 3 non-weapon Magic items before
		// casting their 1st Power Six Spell.  To cast their 1st Power Seven spell, they must go on
		// Holy Quest for the Church and Renounce their 3 magic items.  Their morals must be at
		// Least "50"?, at this point the character is a "Chosen" of Sautrarian.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Fire Hands", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(0, "Flash Heal", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(0, "Golden Touch", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(0, "Judgment", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Light", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Neutralize Poison", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection I", 1, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Dark", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Star Sight", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(0, "Turn Undead", 0, 3, 1)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Blinding Eyes", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Healing", 0, 15, 1), //$NON-NLS-1$
						new SpellRecord(1, "Moderate Heal", 0, 9, 0), //$NON-NLS-1$
						new SpellRecord(1, "Perpetual Light", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection II", 2, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Undead", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(1, "See Invisibility", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Shield of Light", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Thermal Lining", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Turn Curse", 0, 0, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Blessing of Light", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Glowing Mists", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Healing Glow", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(2, "Hearth", 0, 35, 0), //$NON-NLS-1$
						new SpellRecord(2, "Light Flash", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Protection III", 3, 9, 0), //$NON-NLS-1$
						new SpellRecord(2, "Remove Curse", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Turn Demons", 0, 6, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Blinding Light", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Illusions", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(3, "Night Sight", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection IV", 4, 12, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection Aura", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(3, "Spears of Light I", 1, 3, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Commune", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(4, "Gift of Sight", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(4, "Healing Rays", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(4, "Pass Darkness", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Protection V", 5, 15, 0), //$NON-NLS-1$
						new SpellRecord(4, "Vitality", 0, 30, 1)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Call from Shadows", 0, 600, 0), //$NON-NLS-1$
						new SpellRecord(5, "Protection VI", 6, 18, 0), //$NON-NLS-1$
						new SpellRecord(5, "ReBirth", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Spears of Light II", 2, 4, 1), //$NON-NLS-1$
						new SpellRecord(5, "Temple of Light", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(5, "Wall of Brilliance", 0, 10, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Aura of Pelon", 0, 360, 0), //$NON-NLS-1$
						new SpellRecord(6, "Gift of Pelon", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(6, "Holy Sword", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Protection VII", 7, 21, 0), //$NON-NLS-1$
						new SpellRecord(6, "Raise Army", 0, -1, 0), //$NON-NLS-1$
						//						new SpellRecord(6, "Regeneration", 0, 200, 0), //$NON-NLS-1$
						new SpellRecord(6, "Resurrection", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Rune : Slay Undead", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Vitality", 0, 30, 1)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Call Daylight", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(7, "Call to Law", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(7, "Hold Sun", 0, 120, 0)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Gift of the Gods", 0, 60, 1), //$NON-NLS-1$
						new SpellRecord(8, "Transformation", 0, -1, 0)))); //$NON-NLS-1$

	}

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
		
		Mages / Priests:
		
			1)	Add +4% per level past (1st) to be divided between their Hit Bonus, Bow
				Bonus and their Casting Speed.  They may split the 4% as they see fit, but
				for every +4% added to their Casting Speed it goes up +1.
		
			2)	Add +2% to their Missile Bonus.
		
			3)	Add +3 Stamina per level past (1st) until 10th level.  After 10th level add
				+1 Stamina per level.
		
			4)	Add +1 Hit Point per level till the 10th level.
		
			5)	Add +1% per level to their Save Vs. Surprise.
		
			6)	Their Defense rises the same as their Hit Bonus, and their Free Attack
				Rises +1% per level past (1st).
		*/
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	@Override
	public int getBandaging() {
		return 10;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
