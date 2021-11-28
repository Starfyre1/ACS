/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages.elemental;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.MagesBase;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Earth extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		The Elements
		Second Requisite = Willpower

			There are Four distinct areas in the Elemental Magic, Earth, Air, Fire and
			Water.  Each will have a small explanation of the area and a spell list as
			follows:

			Earth Elementalists are usually large, bulky individuals, not very fast,
			But Very powerful.  They are capable of Harnessing the power of Gravity,
			making the living earth rise, fall or crack at your feet.  They can make a
			Heavy load light, or a Light load Heavy, with their powers over Mass and
			Density.  The Focus for this area is a Stone or Rock of some kind, it doesn't
			have to be valuable, set in a Ring or Amulet.

			Power Zero:					Power One:
			1)Control Cloud (-1)		1)Breakfall (-0)
			2)Control Element (-20)F	2)Control Weight (-10)
			3)Detect Gems (-20)F		3)Levitation (-15)
			4)Detect Metal (-20)F		4)Slow (-4)F
			5)Darts (-3)				5)Summon Sand (-8)
			6)Speak with Stones (-15)		6)Vault (-3)
			7)Earth Womb (-5)			7)X-Ray Eyes (-30)F
			8)Stone Staff (-5)			8)Stone Meld (-6)

			Power Two:					Power Three:
			1)Crack Rock (-4)			1)Hurl Rock II (-5)F
			2)Hurl Rock I (-3)F			2)Remove Earth (-30)
			3)Sand Bolt (-4)			3)Stone Storm (-10)
			4)Summon Rock (-8)			4)Wall of Stone (-8)
			5)Weight (-6)F				5)Temperate Earth (-10)
			6)Acid Sphere (-3)F

			Power Four:					Power Five :
			1)Earth Force (-5)			1)Alter Terrain (-60)
			2)Hurl Rock III (-7)F		2)Disintegrate (-5)F
			3)Liquefy Earth (-15)		3)Endurance (-1)
			4)Part Rock I (-20)			4)Flesh to Stone (-30)
			5)Telekinesis (-7)F			5)Melt Rock (-60)

			Power Six:					Power Seven :
			1)Animate Rock (-5)			1)Sink (-1)F
			2)Negate Gravity (-8)		2)Escape (-3)
			3)Part Rock II (-10)
			// DW verify Part Rock III should be Part Rock II (Earth : power 6)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Earth() {
		// TheElementsEarth
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Earth Elementalists are usually large, bulky individuals, not very fast,
		// But Very powerful.  They are capable of Harnessing the power of Gravity,
		// making the living earth rise, fall or crack at your feet.  They can make a
		// Heavy load light, or a Light load Heavy, with their powers over Mass and
		// Density.  The Focus for this area is a Stone or Rock of some kind, it doesn't
		// have to be valuable, set in a Ring or Amulet.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Control Cloud", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Control Element", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Detect Gems", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Detect Metal", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Speak with  Stones", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(0, "Earth Womb", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Stone Staff", 0, 5, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Breakfall", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Control Weight", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Levitation", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Slow", 0, 4, 1), //$NON-NLS-1$
						new SpellRecord(1, "Summon Sand", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(1, "Vault", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "X-Ray Eyes", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(1, "Stone Meld", 0, 6, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Crack Rock", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(2, "Hurl Rock I", 1, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Sand Bolt", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(2, "Summon Rock", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(2, "Weight", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(2, "Acid Sphere", 0, 3, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Hurl Rock II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Remove Earth", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(3, "Stone Storm", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Wall of Stone", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(3, "Temperate Earth", 0, 10, 0)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Earth Force", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(4, "Hurl Rock III", 3, 7, 1), //$NON-NLS-1$
						new SpellRecord(4, "Liquefy Earth", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(4, "Part Rock I", 1, 20, 0), //$NON-NLS-1$
						new SpellRecord(4, "Telekinesis", 0, 7, 1)))); //$NON-NLS-1$

		// Power Five :
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Alter Terrain", 0, 60, 0), //$NON-NLS-1$
						new SpellRecord(5, "Disintegrate", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(5, "Endurance", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Flesh to Stone", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Melt Rock", 0, 60, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Animate Rock", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(6, "Negate Gravity", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(6, "Part Rock II", 3, 10, 0)))); //$NON-NLS-1$

		// Power Seven :
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Sink", 0, 1, 1), //$NON-NLS-1$
						new SpellRecord(7, "Escape", 0, 3, 0)))); //$NON-NLS-1$

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
