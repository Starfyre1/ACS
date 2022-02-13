/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Necromancer extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		The Necromancer
		Second Requisite = Wisdom
	
			Necromancers work with and command the forces of life and death, let's face
			it, mostly Death.  The Necromancer uses their magic to give dead things the
			parody of life, and are always seeking ways of cheating Death.  The most
			powerful of the Necromancers are rumored to be Immortal, and UN killable.
			Most Necromancers are considered Priests of Graun, however there are some
			who do not worship this Deity.  Their Focus is usually something Unusual,
	
			Power Zero:							Power One:
			1)Animation (-120)					1)Area of Gloom (-30)
			2)Area of Fear (-2)F				2)Control Undead (-3)
			3)Cause Wounds (-3)F				3)Deafness (-6)F
			4)Curse (-1)						4)Heal Self (-20)
			5)Darkness (-5)						5)Hypnotism (-3)F
			6)Night Sight (-1)					6)Locate Living (-15)
			7)Protection : Undead (-0)			7)Perpetual Dark (-15)
			8)Darts (-4)						8)Protection I (-5)F
												9)Rune of Combat I (-260)
			Power Two:							10)Sleep I (-3)F
			1)Blindness (-5)F					11)Speak with  Dead (-90)
			2)Charm I (-3)F
			3)Charm Animals (-3)F 				Power Three:
			4)Close (-1)						1)Acid Sphere (-7)F
			5)Familiar (-600)					2)Day of Gloom (-90)F
			6)Locate Life (-10)					3)Leveloss I (-1)F
			7)Madness (-3)						4)Protection III (-15)F
			8)Muteness (-5)F					5)Rule Undead (-5)
			9)Open (-3)							6)Rune of Combat II (-lots)
			10)Protection II (-10)				7)Summon Wraith II (-180)
			11)Protection : Fear (-0)			8)Touch of Death (-4)F
			12)Seance (-600)					9)Withering (-1)F
			13)Summon Wraith I (-120)
			14)The Black Mass (-lots)			Power Five:
			15)Weakness (-3)F					1)Area of Death (-9)F
												2)Commune (-360)
			Power Four:							3)Escape (-1)
			1)Damnation (-120)					4)Geas (-6)F
			2)Death Ray (-5)F					5)Leveloss II (-2)
			3)Protection IV (-20)F				6)Protection V (-25)F
			4)Soul Store (-300)					7)Rune of Combat III (-lots)
			5)Summon Specter (-240)				8)Sleep II (-6)F
			6)Warriors of the Damned (-90)F		9)Unholy Strength (-30)F
												10)Venom Vapors (-6)
			Power Six:
			1)Call to Chaos (-3)				Power Seven:
			2)Command Chaos (-3)F				1)Command Undead (-6)
			3)Legions of the Damned (-30)F		2)Protection VII (-35)
			4)Protection VI (-30)F				3)Realm of Darkness (-360)F
			5)Resurrection (-2400)				4)Sleep Eternal (-8)
			6)Return (-30)F
												Power Eight:
												1)Gift of the Gods (-900)F
												2)Protection VIII (-60)
	
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static Necromancer sInstance;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Necromancer() {
		// TheNecromancer
		mSecondRequisite = AttributesRecord.WISDOM;
		// Necromancers work with and command the forces of life and death, let's face
		// it, mostly Death.  The Necromancer uses their magic to give dead things the
		// parody of life, and are always seeking ways of cheating Death.  The most
		// powerful of the Necromancers are rumored to be Immortal, and UN killable.
		// Most Necromancers are considered Priests of Graun, however there are some
		// who do not worship this Deity.  Their Focus is usually something Unusual,

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Animation", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(0, "Area of Fear", 0, 2, 1), //$NON-NLS-1$
						new SpellRecord(0, "Cause Wounds", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(0, "Curse", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darkness", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Night Sight", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Undead", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 4, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Area of Gloom", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Control Undead", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(1, "Deafness", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(1, "Heal Self", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Hypnotism", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(1, "Locate Living", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Perpetual Dark", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection I", 1, 5, 1), //$NON-NLS-1$
						new SpellRecord(1, "Rune of Combat I", 1, 260, 0), //$NON-NLS-1$
						new SpellRecord(1, "Sleep I", 1, 3, 1), //$NON-NLS-1$
						new SpellRecord(1, "Speak with  Dead", 0, 90, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Blindness", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Charm I", 1, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Charm Animals", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Close", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Familiar", 0, 600, 0), //$NON-NLS-1$
						new SpellRecord(2, "Locate Life", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(2, "Madness", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Muteness", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(2, "Open", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection II", 2, 10, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Fear", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(2, "Seance", 0, 600, 0), //$NON-NLS-1$
						new SpellRecord(2, "Summon Wraith I", 1, 120, 0), //$NON-NLS-1$
						new SpellRecord(2, "The Black Mass", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Weakness", 0, 3, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Acid Sphere", 0, 7, 1), //$NON-NLS-1$
						new SpellRecord(3, "Day of Gloom", 0, 90, 1), //$NON-NLS-1$
						new SpellRecord(3, "Leveloss I", 1, 1, 1), //$NON-NLS-1$
						new SpellRecord(3, "Protection III", 3, 15, 1), //$NON-NLS-1$
						new SpellRecord(3, "Rule Undead", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(3, "Rune of Combat II", 2, -1, 0), //$NON-NLS-1$
						new SpellRecord(3, "Summon Wraith II", 2, 180, 0), //$NON-NLS-1$
						new SpellRecord(3, "Touch of Death", 0, 4, 1), //$NON-NLS-1$
						new SpellRecord(3, "Withering", 0, 1, 1)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Damnation", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(4, "Death Ray", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(4, "Protection IV", 4, 20, 1), //$NON-NLS-1$
						new SpellRecord(4, "Soul Store", 0, 300, 0), //$NON-NLS-1$
						new SpellRecord(4, "Summon Specter", 0, 240, 0), //$NON-NLS-1$
						new SpellRecord(4, "Warriors of the Damned", 0, 90, 1)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Area of Death", 0, 9, 1), //$NON-NLS-1$
						new SpellRecord(5, "Commune", 0, 360, 0), //$NON-NLS-1$
						new SpellRecord(5, "Escape", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Geas", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(5, "Leveloss II", 2, 2, 0), //$NON-NLS-1$
						new SpellRecord(5, "Protection V", 5, 25, 1), //$NON-NLS-1$
						new SpellRecord(5, "Rune of Combat III", 3, -1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Sleep II", 2, 6, 1), //$NON-NLS-1$
						new SpellRecord(5, "Unholy Strength", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(5, "Venom Vapors", 0, 6, 0)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Call to Chaos", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(6, "Command Chaos", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(6, "Legions of the Damned", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Protection VI", 6, 30, 1), //$NON-NLS-1$
						new SpellRecord(6, "Resurrection", 0, 2400, 0), //$NON-NLS-1$
						new SpellRecord(6, "Return", 0, 30, 1)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Command Undead", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(7, "Protection VII", 7, 35, 0), //$NON-NLS-1$
						new SpellRecord(7, "Realm of Darkness", 0, 360, 1), //$NON-NLS-1$
						new SpellRecord(7, "Sleep Eternal", 0, 8, 0)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Gift of the Gods", 0, 900, 1), //$NON-NLS-1$
						new SpellRecord(8, "Protection VIII", 8, 60, 0)))); //$NON-NLS-1$

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
		return "Unusual"; //$NON-NLS-1$
	}

	@Override
	public String getFocusToolTip() {
		return "<html>Their Focus is usually something Unusual.</html>"; //$NON-NLS-1$
	}

	public static Necromancer getInstance() {
		if (sInstance == null) {
			sInstance = new Necromancer();
		}
		return sInstance;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
