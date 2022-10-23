/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages.elemental;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.MagesBase;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Fire extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Elements
	Second Requisite = Willpower
	
		There are Four distinct areas in the Elemental Magic, Earth, Air, Fire and
		Water.  Each will have a small explanation of the area and a spell list as
		follows:
	
		Fire Elementalist use the Forces of Heat to create great walls of flame,
		turn solid rock to molten lava, and to launch mighty fireballs at enemies.
		They are usually a quick-tempered person of any physical type.  Their
		Focus is usually a Red Gem of some sort, either by itself or set into some
		sort of jewelry.
	
		Power Zero:					Power One:
		1)Control Cloud (-1)		1)Fireworks (-4)
		2)Control Element (-20)F	2)Flaming Circle (-6)
		3)Detect Heat (-10)			3)Increase Fire (-1)
		4)Firebolt (-6)				4)Melt Ice (-5)
		5)Fire Dart (-5)			5)Protection : Cold (-0)
		6)Protection : Fire (-0)	6)Summon Fire (-6)
		7)Summon Flame (-1)
		8)Summon Heat (-8)			Power Three:
		9)Summon Smoke (-8)			1)Fire Shield (-0)
									2)Fireball II (-5)F
		Power Two:					3)Firestorm I (-6)F
		1)Dancing Fire (-5)			4)Wall of Fire I (-6)
		2)Fire Rune (-lots)
		3)Fireball I (-4)F			Power Five:
		4)Touch of Fire (-5)		1)Delay Fireball (-2)
									2)Disintegrate (-5)
		Power Four:					3)Eternal Flame (-7)F
		1)Fireball III (-6)F		4)Fireball IV (-7)F
		2)Smoke Servant (-12)		5)Increase Temperature (-30)F
		3)Wall of Fire II (-30)
									Power Seven:
		Power Six:					1)Fireball VI (-9)F
		1)Burning Plane (-10)F		2)Meteors (-6)F
		2)Fireball V (-8)F
		3)Firestorm II (-30)F
		4)Melt Rock (-10)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Fire() {
		// TheElementsFire
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Fire Elementalist use the Forces of Heat to create great walls of flame,
		// turn solid rock to molten lava, and to launch mighty fireballs at enemies.
		// They are usually a quick-tempered person of any physical type.  Their
		// Focus is usually a Red Gem of some sort, either by itself or set into some
		// sort of jewelry.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Control Cloud", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Control Element", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Detect Heat", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(0, "Firebolt", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Summon Flame", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Summon Heat", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(0, "Summon Smoke", 0, 8, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Fireworks", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Flaming Circle", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Increase Fire", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Melt Ice", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Cold", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Summon Fire", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Watchfire", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Thermal Lining", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Fire Hands", 0, 6, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Dancing Fire", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Fire Rune", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Fireball I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Forge I", 1, -1, 1), //$NON-NLS-1$
						new SpellRecord(2, "Touch of Fire", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Hearth", 0, 30, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Fireball II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Fire Shield", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(3, "Firestorm I", 1, 6, 1), //$NON-NLS-1$
						new SpellRecord(3, "Forge II", 2, -1, 1), //$NON-NLS-1$
						new SpellRecord(3, "Wall of Fire I", 1, 6, 0)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Fireball III", 3, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Forge III", 3, -1, 1), //$NON-NLS-1$
						new SpellRecord(4, "Smoke Servant", 0, 12, 0), //$NON-NLS-1$
						new SpellRecord(4, "Wall of Fire II", 2, 30, 0)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Delay Fireball", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(5, "Disintegrate", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(5, "Eternal Flame", 0, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Fireball IV", 4, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Increase Temperature", 0, 30, 1)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Burning Plane", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(6, "Fireball V", 5, 8, 1), //$NON-NLS-1$
						new SpellRecord(6, "Firestorm II", 2, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Melt Rock", 0, 10, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Fireball VI", 6, 9, 1), //$NON-NLS-1$
						new SpellRecord(7, "Meteors", 0, 6, 1)))); //$NON-NLS-1$

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
		return "Red Gem"; //$NON-NLS-1$
	}

	@Override
	public String getFocusToolTip() {
		return "<html>Their Focus is usually a Red Gem of some sort,<br>" //$NON-NLS-1$
						+ "either by itself or set into some sort of jewelry.</html>"; //$NON-NLS-1$
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
