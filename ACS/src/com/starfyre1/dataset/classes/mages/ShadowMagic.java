/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class ShadowMagic extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Shadow Magic
		Second Requisite = Dexterity
	
			Needless to say, Shadow Magic is the Magic of Thieves and Assassins, neither
			of which are often seen.  Unless of course they want to be.  They are Masters
			of Trickery, Stealth and Slight of Hand.  Some of these Mages used to be Priests
			of Knull the Patron God of Thieves.  Sadly, Knull was one of the Gods killed in the
			God War, his assassins have moved to Sarn.  The Thieves tend to worshp Tarot or
			Graun now. The Focuses for Shadow Mages are not a set item.  The Player and the
			D.M. must decide what their focus is to be.
	
			Power Zero:						Power One:
			1)Alertness I (-30)F			1)Alertness II (-60)F
			2)Conceal (-1)					2)Breakfall (-0)
			3)Hiding (-15)					3)Detect Traps (-5)
			4)Open Locks I (-15)			4)Enchant Locks (-30)
			5)Rune of Combat I (-lots)		5)Location (-15)
			6)See Invisibility (-15)F		6)Locate Object (-15)F
			7)Sleight of Hand (-1)			7)Mind Stray (-1)
			8)Summon Fog (-15)F				8)Night Sight (-10)
			9)Vault (-1)F					9)Perpetual Shade (-15)
			10) Darts (-4)					10)Shadow Climb (-10)
											11)Shadow Cure (-60)
			Power Two:						12)Shadow Images (-5)F
			1)Area of Fog (-30)				13)Translate (-1)
			2)Decipher (-lots)
			3)Detect Enemies (-15)F			Power Three:
			4)Disguise (-15)				1)Agility (-5)
			5)Dodge (-0)					2)Know Direction (-1)
			6)Familiar (-600)				3)Muteness (-3)F
			7)Hide Location (-15)			4)Rune of Combat II (-lots)
			8)Open Locks II (-15)			5)Shadow Eye I (-10)
			9)Protection / Traps (-5)		6)Grab (-1)
			10)Shadow Hold (-5)				7)Stop Poison (-15)
			11)Silence (-10)				8)Whispers I (-30)
			12)Sleep I (-5)F
			13)Trail of Trickery (-15)		Power Five:
											1)Hidden Path (-5)
			Power Four:						2)Rune of Combat III (-lots)
			1)Blend W/Shadows (-5)			3)Shadow Animation (-60)
			2)Day of Fog (-120)F			4)Shadow Warning (-120)
			3)Phantasm (-15)				5)Summon Shadows (-10)F
			4)Sleep II (-5)F
											Power Seven:
			Power Six:						1)Castle of Shadows (-lots)
			1)Area of Shadows (-30)			2)Twilight Summoning (-120)
			2)Shadow Eye II (-120)			3)Walk in Shadows (-10)
			3)Whispers II (-60)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public ShadowMagic() {
		// ShadowMagic

		mSecondRequisite = AttributesRecord.DEXTERITY;

		// Needless to say, Shadow Magic is the Magic of Thieves and Assassins, neither
		// of which are often seen.  Unless of course they want to be.  They are Masters
		// of Trickery, Stealth and Slight of Hand.  Some of these Mages used to be Priests
		// of Knull the Patron God of Thieves.  Sadly, Knull was one of the Gods killed in the
		// God War, his assasins have moved to Sarn.  The Thieves tend to worshp Tarot or
		// Graun now. The Focuses for Shadow Mages are not a set item.  The Player and the
		// D.M. must decide what their focus is to be.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness I", 1, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Conceal", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hiding", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Open Locks I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat I", 1, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("See Invisibility", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleight of Hand", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Fog", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vault", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darts", 0, 4, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness II", 2, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Breakfall", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Traps", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Enchant Locks", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Location", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Object", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Stray", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Perpetual Shade", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Climb", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Cure", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Images", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Translate", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Fog", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Decipher", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Enemies", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Disguise", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dodge", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hide Location", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Open Locks II", 2, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Traps", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Hold", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep I", 1, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Trail of Trickery", 0, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Agility", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Know Direction", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Muteness", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat II", 2, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Eye I", 1, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Grab", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stop Poison", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Whispers I", 1, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Blend W/Shadows", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Day of Fog", 0, 120, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Phantasm", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep II", 2, 5, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Hidden Path", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat III", 3, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Animation", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Warning", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Shadows", 0, 10, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Shadows", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shadow Eye II", 2, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Whispers II", 2, 60, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Castle of Shadows", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Twilight Summoning", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Walk in Shadows", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
