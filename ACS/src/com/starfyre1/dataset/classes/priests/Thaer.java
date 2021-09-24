/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Thaer extends PriestsBase {
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
	
		Thaer
		Second Requisite - Personal Appearance
	
			Priests & Priestess of Thaer are always very good looking and flirtatious.  They
			don't really look ahead for anything, they always live in the moment.  Their only
			enemy in the pantheon is Narese.  They believe that marring your body in any
			way, be it tattoo's or scars is blasphemous, and deserving of death.

			They are very limited on what armor or weapons they may use.  They are not
			allowed to wear a helm that will completely cover their face, they must wear
			either a set of Glittering Chain mail, Plate or preferably Magical Robes.  Their
			choice of weapons is made when they choose Thaer as their Goddess, for is she
			not known as the Goddess of the Golden Mace?
	
			Power Zero						Power One
			1)Flaming Circle (-5)			1)Comeliness (-120)
			2)Protection I (-5)				2)Protection II (-10)
			3)Cleanliness (-5)              3)Sleep (-3)F
			4)Light (-1)                    4)Absorb I (-15)
			5)Golden Touch (-3)F            5)Silence (-6)
			6)Dryness (-5)                  6)Continual Light (-10)
			7)Protection/Fire (-0)          7)Stamina I (-15)F
			8)Protection/Dark (-5)          8)Remove Scar (-20)
			9)Heal Self (-20)F              9)Sheath (-3)
			10)Summon Fog (-4)
	
			Power Two  						Power Three
			1)Love Potion (-300)            1)Emotion Control (-30)
			2)Protection III (-15)          2)Pheromones (-20)
			3)Shield II (-30)               3)Shield III (-35)
			4)Images (-25)                  4)Protection IV (-20)
			5)Spear of Light (-3)F          5)Strength (-120)
			6)Healing (-120)                6)Stamina II (-20)F
			7)Cure Illness (-20)			7)Protection/Lightning(-0)
	
			Power Four						Power Five
			1)Protection IV (-25)           1)Protection V (-30)
			2)Bless Journey (-3)            2)Holy Quest (-300)
			3)Regenerate (-15)              3)Haste (-4)H
			4)Protection/Lightning (-0)    	4)Sleep II (-6)F
	
			Power Six						Power Seven
			1)Protection VI (-35)           1)Transformation (-60)
			2)Holy Sword (-600)             2)Call Daylight (-6)
			3)Wall of Brilliance (-10)      3)Rune/Slay Undead (-lots)

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Thaer() {
		// Thaer
		mSecondRequisite = AttributesRecord.PERSONAL_APPEARANCE;
		// Priests & Priestess of Thaer are always very good looking and flirtatious.  They
		// don't really look ahead for anything, they always live in the moment.  Their only
		// enemy in the pantheon is Narese.  They believe that marring your body in any
		// way, be it tattoo's or scars is blasphemous, and deserving of death.
		// They are very limited on what armor or weapons they may use.  They are not
		// allowed to wear a helm that will completely cover their face, they must wear
		// either a set of Glittering Chain mail, Plate or preferably Magical Robes.  Their
		// choice of weapons is made when they choose Thaer as their Goddess, for is she
		// not known as the Goddess of the Golden Mace?

		// Power Zero
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Flaming Circle", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cleanliness", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Light", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Golden Touch", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dryness", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Dark", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fog", 0, 4, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Comeliness", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Absorb I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Continual Light", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina I", 1, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Remove Scar", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sheath", 0, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Love Potion", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield II", 2, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Images", 0, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Spear of Light", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness", 0, 20, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Emotion Control", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pheromones", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield III", 3, 35, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strength", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina II", 2, 20, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection IV", 5, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Bless Journey", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Regenerate", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection V", 5, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Holy Quest", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Haste", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep II", 2, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection VI", 6, 35, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Holy Sword", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Brilliance", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Transformation", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call Daylight", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune/Slay Undead", 0, -1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
