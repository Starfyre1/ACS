/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Control extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Control
		Second Requisite = Charisma

			Sorcerers use the spells of Control to Hypnotize and Control people and
				creatures.  Causing them to do things that they would not normally do.
			Masters of Control are rumored to know Words that can Stun or even Kill you.
				Spells with a "H" requires a "Hypnotism"  or "Charm" spell cast simultaneously,
				without any added speed modifier or stamina cost, but with the cost of mana
			for that spell.  Make sure that all the spells restrictions are followed, this is a
				very tough area to play, but Masters of Control are Very Powerful Mages.
			Here are their Spells:

			Power Zero:					Power Two:
			1)Area of Fear (-5)			1)Agility (-10)H
			2)Binding Spell I (-3)		2)Alertness II (-60)H
			3)Detect Lie (-2)			3)Binding Spell II (-5)
			4)Detect Morals I (-10)F	4)Blindness (-6)
			5)Forget (-1)H				5)Charm II (-5)
			6)Hypnotism (-3)			6)Charm Animals (-3)
			7)Mind Stray (-1)			7)Clairaudience (-10)
			8)Protection : Charm (-0)	8)Clairvoyance (-10)
			9)Protection : Sleep (-0)	9)Familiar (-600)
			10)Sleep I (-3)				10)Haste (-4)H
			11)Stamina I (-15)HF		11)Madness (-1)H
			12)Suggestion (-1)H			12)Mind Link (-5)F
										13)Stamina II (-20)HF
										14)Stun (-2)
										15)Telepathy (-15)
	
			Power One:					Power Three:
			1)Alertness I (-30)H		1)Anarchy I (-5)
			2)Alter Memory (-5)H		2)Dancing Fire (-5)
			3)Charm I (-4)				3)Detect Morals II (-15)F
			4)Curse (-1)				4)Gaze of Command (-3)
			5)Deafness (-4)				5)Hypnotize Monster (-5)
			6)ESP (-15)					6)Paralyzation (-4)
			7)Locate Life (-15)F		7)Sleep II (-5)
			8)Protection : Fear (-0)	8)Slow (-4)HF
			9)Strength (-15)H			9)Stutter (-5)
			10)Translate (-1)
	
			Power Four:
			1)Anarchy II (-10)			Power Six:
			2)Charm Monsters (-5)		1)Call to Chaos (-15)F
			3)Control Foes (-360)F		2)Call to Law (-15)F
			4)Invulnerability (-10)H	3)Deathword I (-0)
			5)Mass Hypnotism I (-5)		4)Sleep Eternal (-10)
			6)Mind Wipe (-5)			5)Stamina IV (-30)HF
			7)Stamina III (-25)HF
										Power Seven:
			Power Five:					1)Deathword II (-0)
			1)Charm Plant (-10)			2)Mass Hypnotism II (-8)
			2)Geas (-6)					3)Wake (-1)
			3)Rally (-10

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Control() {
		// Control
		mSecondRequisite = AttributesRecord.CHARISMA;

		// Sorcerers use the spells of Control to Hypnotize and Control people and
		// creatures.  Causing them to do things that they would not normally do.
		// Masters of Control are rumored to know Words that can Stun or even Kill you.
		// Spells with a "H" requires a "Hypnotism"  or "Charm" spell cast simultaneously,
		// without any added speed modifier or stamina cost, but with the cost of mana
		// for that spell.  Make sure that all the spells restrictions are followed, this is a
		// very tough area to play, but Masters of Control are Very Powerful Mages.
		// Here are their Spells:

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Fear", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Binding Spell I", 1, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Lie", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Forget", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hypnotism", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Stray", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Charm", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Sleep", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep I", 1, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina I", 1, 15, 5, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Suggestion", 0, 1, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness I", 1, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alter Memory", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm I", 1, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Curse", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deafness", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("ESP", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate Life", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection : Fear", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strength", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Translate", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Agility", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness II", 2, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Binding Spell II", 2, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Blindness", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm II", 2, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm Animals", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Clairaudience", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Clairvoyance", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Haste", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Madness", 0, 1, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Link", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina II", 2, 20, 5, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stun", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Telepathy", 0, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Anarchy I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dancing Fire", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals II", 2, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Gaze of Command", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hypnotize Monster", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Paralyzation", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep II", 2, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Slow", 0, 4, 5, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stutter", 0, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Anarchy II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Charm Monsters", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Foes", 0, 360, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invulnerability", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mass Hypnotism I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mind Wipe", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina III", 3, 25, 5, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Charm Plants", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Geas", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rally", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Call to Chaos", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call to Law", 0, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deathword I", 1, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sleep Eternal", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stamina IV", 5, 30, 5, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Deathword II", 2, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Mass Hypnotism II", 2, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Wake", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
