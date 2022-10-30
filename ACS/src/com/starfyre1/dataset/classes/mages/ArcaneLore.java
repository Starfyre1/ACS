/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

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
			7)Knowledge (-60)				7)Force I (-3)F
			8)Light (-2)					8)Invisibility I (-10)F
			9)Dart (-3)						9)Locate Life (10)
			10)Protection I (-1)F			10)Locate Object (-10)
			11)Rune : Combat I (-lots)		11)Night Sight (-30)
			12)Staff I (-1)F				12)Open (-6
			13)Summons I (-1)				13)Protection II (-2)F
			14)Translate (-1)				14)Protection : Fear (-0)
											15)See Invisibility (-6)F
			Power Two:						16)Staff II (-2)F
			1)Diminution (-3)				17)Turn Curse (-0)
			2)Force II (-4)F
			3)Growth (-6)					Power Three:
			4)Heal (-60)					1)Deflection (-0)
			5)Protection III (-3)F			2)Farsee (-10)F
			6)Remove Curse (-30)			3)Force III (-5)F
			7)Rune : Combat II (-lots)		4)Protection : Undead (6)
			8)Staff III (-3)F				5)Teleport I (-3)F
			9)Summons II (-1)				6)Wall of Force (-3)F
			10)Telepathy (-10)				7)Stone Meld ( -15 )

			Power Four:						Power Five:
			1)Astral Aid (-120)				1)Disintegrate (-10)F
			2)Exorcism (-120)				2)Escape (-1)F
			3)Force IV (-6)F				3)Legend Lore (-lots)
			4)Protection IV (-6)F			4)Force V (-7)F
			5)Rune : Combat III (-lots)	5)Protection V (-5)F
			6)Telekinesis (-5)F				6)Rune of Protection : Magic (-lots)
			7)Teleport II (-6)F				7)Safe Teleport (-120)F

			Power Six:						Power Seven:
			1)Force VI (-8)F				1)Force VII (-9)F
			2)Geas (-3)						2)Protection VI (-6)F
			3)Rune : Combat IV (-lots)		3)Protection : Teleport (-100)
			4)Staff IV (-4)F				4)Rune : Slay Lycanthropes (-lots)
			5)Symbol I (-90)				5)Rune : Slay Undead (-lots)
			6)Know History (-lots)			6)Symbol II (-180)
											7)Turn Ethereal (-15)F
			Power Eight:
			1)Force VIII (-10)F
			2)Gate (-90)F
			3)Rune : Combat V (-lots)
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
						new SpellRecord(0, "Curse", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Decipher", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Illusion", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Lie", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Enchant Locks", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(0, "Forecast", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(0, "Invisible Aid", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Knowledge", 0, 60, 0), //$NON-NLS-1$
						new SpellRecord(0, "Light", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection I", 1, 1, 1), //$NON-NLS-1$
						new SpellRecord(0, "Rune : Combat I", 1, -1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Simple Sounds", 0, 2, 1), //$NON-NLS-1$
						new SpellRecord(0, "Staff I", 1, 1, 1), //$NON-NLS-1$
						new SpellRecord(0, "Summons I", 1, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Translate", 0, 1, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Antidote", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Close", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Detect Secret Doors", 0, 9, 0), //$NON-NLS-1$
						new SpellRecord(1, "Detect Traps", 0, 9, 0), //$NON-NLS-1$
						new SpellRecord(1, "Dispel Illusion I", 1, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "ESP", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Familiar", 0, 600, 0), //$NON-NLS-1$
						new SpellRecord(1, "Force I", 1, 3, 1), //$NON-NLS-1$
						new SpellRecord(1, "Invisibility I", 1, 10, 1), //$NON-NLS-1$
						new SpellRecord(1, "Locate Life", 0, -10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Locate Object", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Night Sight", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Open", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection II", 2, 2, 1), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Fear", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "See Invisibility", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(1, "Staff II", 2, 2, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Create Magic Item I", 1, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Diminution", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Dispel Illusion II", 2, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Force II", 2, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Growth", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(2, "Heal I", 1, 60, 0), //$NON-NLS-1$
						new SpellRecord(2, "Image I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Multiple Images", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Protection III", 3, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Remove Curse", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Rune : Combat II", 2, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Staff III", 3, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Summons II", 2, 1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Telepathy", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(2, "Thermal Lining", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Turn Curse", 0, 0, 0)//, //$NON-NLS-1$
		//new SpellRecord(2, "Image II", 2, 4, 1) //
		)));

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Deflection", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(3, "Dispel Illusion III", 3, 5, 0), //$NON-NLS-1$
						new SpellRecord(3, "Farsee", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(3, "Forge I", 1, -1, 1), //$NON-NLS-1$
						new SpellRecord(3, "Force III", 3, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Gather Power", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(3, "Protection : Undead", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(3, "Teleport I", 1, 3, 1), //$NON-NLS-1$
						new SpellRecord(3, "Wall of Force", 0, 3, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Astral Aid", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(4, "Create Magic Item II", 2, -1, 0), //$NON-NLS-1$
						new SpellRecord(4, "Force IV", 4, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Forge II", 2, -1, 1), //$NON-NLS-1$
						new SpellRecord(4, "Polymorph I", 1, 8, 0), //$NON-NLS-1$
						new SpellRecord(4, "Protection IV", 4, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Rune : Combat III", 3, -1, 0), //$NON-NLS-1$
						new SpellRecord(4, "Telekinesis", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(4, "Teleport II", 2, 6, 1)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Disintegrate", 0, 8, 1), //$NON-NLS-1$
						new SpellRecord(5, "Dispel Illusion IV", 4, 6, 0), //$NON-NLS-1$
						new SpellRecord(5, "Escape", 0, 1, 1), //$NON-NLS-1$
						new SpellRecord(5, "Forge III", 3, -1, 1), //$NON-NLS-1$
						new SpellRecord(5, "Force V", 5, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Legend Lore", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Protection V", 5, 5, 1), //$NON-NLS-1$
						new SpellRecord(5, "Rune : Protection Magic", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Safe Teleport", 0, 120, 1)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Create Magic Item III", 3, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Force VI", 6, 8, 1), //$NON-NLS-1$
						new SpellRecord(6, "Geas", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(6, "Golem", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Know History", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Polymorph II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(6, "Rune : Combat IV", 4, -1, 0), //$NON-NLS-1$
						new SpellRecord(6, "Staff IV", 4, 4, 1), //$NON-NLS-1$
						new SpellRecord(6, "Symbol I", 1, 90, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Force VII", 7, 9, 1), //$NON-NLS-1$
						new SpellRecord(7, "Protection VI", 6, 6, 1), //$NON-NLS-1$
						new SpellRecord(7, "Protection : Teleport", 0, 100, 0), //$NON-NLS-1$
						new SpellRecord(7, "Rune : Slay Lycanthropes", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(7, "Rune : Slay Undead", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(7, "Symbol II", 2, 180, 0), //$NON-NLS-1$
						new SpellRecord(7, "Turn Ethereal", 0, 15, 1)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Force VIII", 8, 10, 1), //$NON-NLS-1$
						new SpellRecord(8, "Gate", 0, 90, 1), //$NON-NLS-1$
						new SpellRecord(8, "Permanent Illusion", 0, 60, 0), //$NON-NLS-1$
						new SpellRecord(8, "Polymorph III", 3, 10, 0), //$NON-NLS-1$
						new SpellRecord(8, "Rune : Combat V", 5, -1, 0), //$NON-NLS-1$
						new SpellRecord(8, "Symbol III", 3, 360, 0)))); //$NON-NLS-1$

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
	public String getFocus() {
		return "Staff"; //$NON-NLS-1$
	}

	@Override
	public String getFocusToolTip() {
		return "<html>Their Primary Focus is their Staff.</html>"; //$NON-NLS-1$
	}

	@Override
	public int getBandaging() {
		return 20;

	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
