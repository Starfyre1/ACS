/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class ArcaneLore extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Arcane Lore
		Second Requisite = Intelligence
	
			The Mages of Arcane Lore are always pushing the fabric of Magic to
			it's limits, they create things.  They have vast knowledge, second only
			to the Priests of Narius.  They have powers over Telekinesis, ESP,
			and Invisibility.  Their Primary Focus is their Staff.
	
			Power Zero:						Power One:
			1)Curse(-1)						1)Antidote (-lots)
			2)Decipher (-lots)				2)Close (-4)
			3)Detect Lie (-2)				3)Detect Secret Doors (-9)
			4)Enchant Locks (-10)			4)Detect Traps (-9)
			5)Forecast (-6)					5)ESP (-5)
			6)Invisible Aid (-1)			6)Familiar (-600)
			7)knowledge (-60)				7)Force I (-3)F
			8)Light (-2)					8)Invisibility I (-10)F
			9)Dart (-3)						9)Locate Life (10)
			10)Protection I (-1)F			10)Locate Object (-10)
			11)Rune of Combat I (-lots)		11)Night Sight (-30)
			12)Staff I (-1)F				12)Open (-6
			13)Summons I (-1)				13)Protection II (-2)F
			14)Translate (-1)				14)Protection : Fear (-0)
											15)See Invisibility (-6)F
			Power Two:						16)Staff II (-2)F
			1)Diminution (-3)				17)Turn Curse (-0)
			2)Force II (-4)F
			3)Growth (-6)					Power Three:
			4)Healing (-60)					1)Deflection (-0)
			5)Protection III (-3)F			2)Farsee (-10)F
			6)Remove Curse (-30)			3)Force III (-5)F
			7)Rune of Combat II (-lots)		4)Protection : Undead (6)
			8)Staff III (-3)F				5)Teleport I (-3)F
			9)Summons II (-1)				6)Wall of Force (-3)F
			10)Telepathy (-10)				7)Stonemeld ( -15 )
	
			Power Four:						Power Five:
			1)Astral Aid (-120)				1)Disintegrate (-10)F
			2)Exorcism (-120)				2)Escape (-1)F
			3)Force IV (-6)F				3)Legend Lore (-lots)
			4)Protection IV (-6)F			4)Force V (-7)F
			5)Rune of Combat III (-lots)	5)Protection V (-5)F
			6)Telekinesis (-5)F				6)Rune of Protection : Magic (-lots)
			7)Teleport II (-6)F				7)Safe Teleport (-120)F
	
			Power Six:						Power Seven:
			1)Force VI (-8)F				1)Force VII (-9)F
			2)Geas (-3)						2)Protection VI (-6)F
			3)Rune of Combat IV (-lots)		3)Protection : Teleport (-100)
			4)Staff IV (-4)F				4)Rune : Slay Lycanthropes (-lots)
			5)Symbol I (-90)				5)Rune : Slay Undead (-lots)
			6)Know History (-lots)			6)Symbol II (-180)
											7)Turn Ethereal (-15)F
			Power Eight:
			1)Force VIII (-10)F
			2)Gate (-90)F
			3)Rune of Combat V (-lots)
			4)Symbol III (-360)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public ArcaneLore() {
		// ArcaneLore
		mSecondRequisite = AttributesRecord.INTELLIGENCE;

		// The Mages of Arcane Lore are always pushing the fabric of Magic to
		// it's limits, they create things.  They have vast knowledge, second only
		// to the Priests of Narius.  They have powers over Telekinesis, ESP,
		// and Invisibility.  Their Primary Focus is their Staff.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Curse", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Decipher", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Lie", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Enchant Locks", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Forecast", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisible Aid", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("knowledge", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Light", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dart", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat I", 1, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff I", 1, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summons I", 1, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Translate", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Antidote", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Close", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Secret Doors", 0, 9, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Traps", 0, 9, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("ESP", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Force I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Life", 0, -10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Object", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Open", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fear", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("See Invisibility", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff II", 2, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Turn Curse", 0, 0, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Diminution", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Force II", 2, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Growth", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Remove Curse", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat II", 2, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff III", 3, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summons II", 2, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Telepathy", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Deflection", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Farsee", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Force III", 3, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Undead", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Teleport I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wall of Force", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stonemeld", 0, -1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Astral Aid", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Exorcism", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Force IV", 5, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection IV", 5, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat III", 3, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Telekinesis", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Teleport II", 2, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Disintegrate", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Escape", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Legend Lore", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Force V", 5, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Protection : Magic", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Safe Teleport", 0, 120, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Force VI", 6, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Geas", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat IV", 5, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Staff IV", 5, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol I", 1, 90, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Know History", 0, -1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Force VII", 7, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Teleport", 0, 100, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune : Slay Lycanthropes", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune : Slay Undead", 0, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol II", 2, 180, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Turn Ethereal", 0, 15, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Force VIII", 8, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gate", 0, 90, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat V", 5, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol III", 3, 360, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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

	@Override
	public int generateBandaging() {
		return 20;

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
