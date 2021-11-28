/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages.elemental;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.MagesBase;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Air extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Elements
	Second Requisite = Willpower

		There are Four distinct areas in the Elemental Magic, Earth, Air, Fire and
		Water.  Each will have a small explanation of the area and a spell list as
		follows:

		Air Elementalist usually Tall and Slender, very sure of themselves &
		what they can do.  The can control Whirlwinds, Poisonous Vapors and
		Lightning among other things.  Air Elementalist Focus would usually
		have some sort of inscriptions on it denoting his Magical field, like a
		lightning bolt, or whirlwind.  It could be almost anything.

			Power Zero:						Power One:
		1)Control Cloud (-1)			1)Breakfall (-0)
		2)Control Element (-20)F		2)Increase Fire (-2)
		3)Darkness (-3)					3)Perpetual Dark (-10)
		4)Light (-3)					4)Perpetual Light (-10)
		5)Mage Wind (-5)				5)Remove Air I (-4)F
		6)Darts (-2)					6)Slight Odors (-3)
		7)Summon Vapors (-8)			7)Wind Bolt (-3)

		Power Two:						Power Three:
		1)Calm Wind (-15)				1)Control Flyers (-3)
		2)Deflection (-0)				2)Control Winds (-10)F
		3)Lightning I (-4)F				3)Fly (-15)
		4)Protection : Lightning (-0)	4)Lightning II (-5)F
		5)Remove Air II (-4)F			5)Strong Odors (-3)
		6)Sphere of Air I (-5)			6)Summon Flyers (-3)

		Power Four:						Power Five:
		1)Chlorine Cloud (-8)			1)Increase Winds (-30)F
		2)Gaseous State (-3)F			2)Lightning IV (-7)F
		3)Lightning III (-6)F			3)Sphere of Air II (-100)F
		4)Remove Air III (-5)F			4)Wall of Brilliance (-5)
		5)Wall of Wind (-6)				5)Glittershield (-1)

		Power Six:						Power Seven:
		1)Lightning V (-8)F				1)Lightning VI (-8)F
		2)Remove Air IV (-6)			2)Suffocation (-4)
		3)Summon Twister (-30)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Air() {
		// TheElementsAir
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Air Elementalist usually Tall and Slender, very sure of themselves &
		// what they can do.  The can control Whirlwinds, Poisonous Vapors and
		// Lightning among other things.  Air Elementalist Focus would usually
		// have some sort of inscriptions on it denoting his Magical field, like a
		// lightning bolt, or whirlwind.  It could be almost anything.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Control Cloud", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Control Element", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Darkness", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Light", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Mage Wind", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Summon Vapors", 0, 8, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Breakfall", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Increase Fire", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(1, "Perpetual Dark", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Perpetual Light", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Remove Air I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(1, "Slight Odors", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "Wind Bolt", 0, 3, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Calm Wind", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Deflection", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(2, "Lightning I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Lightning", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(2, "Remove Air II", 2, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Sphere of Air I", 1, 5, 0)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Control Flyers", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Control Winds", 0, 10, 1), //$NON-NLS-1$
						new SpellRecord(3, "Fly", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(3, "Lightning II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Strong Odors", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Summon Flyers", 0, 3, 0)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Chlorine Cloud", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(4, "Gaseous State", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(4, "Lightning III", 3, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Remove Air III", 3, 5, 1), //$NON-NLS-1$
						new SpellRecord(4, "Wall of Wind", 0, 6, 0)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Increase Winds", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(5, "Lightning IV", 4, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Sphere of Air II", 2, 100, 1), //$NON-NLS-1$
						new SpellRecord(5, "Wall of Brilliance", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(5, "Glittershield", 0, 1, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Lightning V", 5, 8, 1), //$NON-NLS-1$
						new SpellRecord(6, "Remove Air IV", 4, 6, 0), //$NON-NLS-1$
						new SpellRecord(6, "Summon Twister", 0, 30, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Lightning VI", 6, 8, 1), //$NON-NLS-1$
						new SpellRecord(7, "Suffocation", 0, 4, 0)))); //$NON-NLS-1$

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void AdvanceLevel() {
		//		int level = Startup.getInstance().getCharacterSheet().getHeaderRecord().getLevel();

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
