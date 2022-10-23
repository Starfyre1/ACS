/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages.elemental;

import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.classes.mages.MagesBase;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Water extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Elements
	Second Requisite = Willpower
	
		There are Four distinct areas in the Elemental Magic, Earth, Air, Fire and
		Water.  Each will have a small explanation of the area and a spell list as
		follows:
	
		Water Elementalists control the force of Water, in all it's forms, Mists, Fogs
		and Ice to mention a few.  They are capable of Creating Whirlpools, that can
		sink small ships. They can summon Fogs and Fire bolts of freezing Cold at their
		enemies.  Their focus Can be anything the Ref. or player chooses.
	
		Power Zero:					Power One:
		1)Clear Water (-8)			1)Area of Fog (-10)
		2)Control Cloud (-1)		2)Calm Waters (-20)F
		3)Control Element (-20)F	3)Freedom (-3)
		4)Freeze Water (-5)F		4)Kill Normal Fires (-6)
		5) Darts (-3)				5)Protection : Fire (-0)
		6)Melt Ice (-5)				6)Scattered Showers (-30)
		7)Produce Water (-20)		7)Water Bolt (-3)F
		8)Protection : Cold (-0)
		9)Summon Fog (-4)			Power Three:
		10)Summon Shower (-8)		1)Cold Spell II (-5)F
									2)Control Water Beasts (-3)
		Power Two:					3)Hail Storm (-6)f
		1)Cold Spell I (-4)F		4)Summon Water Beasts (-3)
		2)Sea Safety (-30)
		3)Wall of Ice (-6)			Power Five:
		4)Water Breathing (-25)		1)Cold Spell IV (-7)F
									2)Day of Fog (-30)
		Power Four:					3)Part Water (-20)
		1)Cold Spell III (-6)F		4)Start Rain (-30)F
		2)Lower Water )-15)
		3)Water Walk (-5)			Power Seven:
									1)Cold Spell VI (-9)F
		Power Six:					2)Shell of Water Breathing (-35)
		1)Cold Spell V (-8)F		3)Transport via Water (-6)
		2)Summon Storm (-30)		4)Pressure (-25)
		3)Water Wave (-7)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Water() {
		// TheElementsWater
		mSecondRequisite = AttributesRecord.WILLPOWER;

		// Water Elementalists control the force of Water, in all it's forms, Mists, Fogs
		// and Ice to mention a few.  They are capable of Creating Whirlpools, that can
		// sink small ships. They can summon Fogs and Fire bolts of freezing Cold at their
		// enemies.  Their focus Can be anything the Ref. or player chooses.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(0, "Clear Water", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(0, "Control Cloud", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(0, "Control Element", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(0, "Freeze Water", 0, 5, 1), //$NON-NLS-1$
						new SpellRecord(0, "Darts", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(0, "Melt Ice", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Produce Water", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Cold", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Resuscitate", 0, 2, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Area of Fog", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(1, "Calm Waters", 0, 20, 1), //$NON-NLS-1$
						new SpellRecord(1, "Summon Fog", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(1, "Kill Normal Fires", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(1, "Scattered Showers", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Water Bolt", 0, 3, 1), //$NON-NLS-1$
						new SpellRecord(1, "Dryness", 0, 5, 0)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Cold Spell I", 1, 4, 1), //$NON-NLS-1$
						new SpellRecord(2, "Sea Safety", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Wall of Ice", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(2, "Water Breathing", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(2, "Freedom", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(2, "Scattered Showers", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Propel : Water", 0, 12, 0), //$NON-NLS-1$
						new SpellRecord(2, "Find Water", 0, -1, 1)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Cold Spell II", 2, 5, 1), //$NON-NLS-1$
						new SpellRecord(3, "Control Water Beasts", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Hail Storm", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(3, "Summon Water Beast I", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(3, "Draw Water", 0, -1, 1)))); //$NON-NLS-1$
		//				new SpellRecord(3, "Summon Water Beasts", 0, 3, 0)))); //$NON-NLS-1$

		//DW verify Water Power 3 "Summon Water Beasts" : description is Summon Water Beast I, Summon Water Beast II, Summon Water Beast III (which)

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Cold Spell III", 3, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Lower Water", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(4, "Water Walk", 0, 5, 0)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Cold Spell IV", 4, 7, 1), //$NON-NLS-1$
						new SpellRecord(5, "Day of Fog", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Part Water", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(5, "Start Rain", 0, 30, 1)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Cold Spell V", 5, 8, 1), //$NON-NLS-1$
						new SpellRecord(6, "Summon Storm", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(6, "Water Wave", 0, 7, 0)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Cold Spell VI", 6, 9, 1), //$NON-NLS-1$
						new SpellRecord(7, "Shell of Water Breathing", 0, 35, 0), //$NON-NLS-1$
						new SpellRecord(7, "Transport via Water", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(7, "Pressure", 0, 25, 0)))); //$NON-NLS-1$

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
		return "User/DM"; //$NON-NLS-1$
	}

	@Override
	public String getFocusToolTip() {
		return "<html>Their focus can be anything the DM or player chooses.</html>"; //$NON-NLS-1$
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
