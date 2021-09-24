/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.mages;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Illusion extends MagesBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
		Illusion
		Second Requisite = Dexterity

			Illusionist summon material from other planes too mold into their
			Illusions.  The following are the forces that the Illusionist must master
			too create His or Her Illusions:

			1) Image- 	This force is strictly visual and is Very delicate.  Any object can
						pass through it without resistance, however if it is actually struck
						and takes damage the spell will fail.

			2) Sound-	This force is strictly audible, it will appear to originate from
						wherever the caster desires, within the spell range. (Unless
						Performance is cast.)
			3) Taste-	Simulates taste, otherwise like sound.

			4) Odor-	Simulates the sense of smell, otherwise like sound.

			5) Pain-(Pleasure)This simulates the sense of Pain, and is capable of doing real damage,
						if it is believed.  (an explanation of Belief Vs. Illusion follows)
	
		Belief Vs. Illusion:
			A)	Belief in a illusion is assumed to be Automatic unless specified
				bellow.

			B)	On any round, a character may choose to stand and Disbelieve an
				Illusion (or what they think is an illusion).  The character then rolls
				2D6 adding these to his Asp, this is when they will Disbelieve the
				illusion (if it is an illusion and he makes his Belief roll).  Any damage
				taken after the character has successfully rolled their Disbelief is
				ignored, if it was an illusion that is.....

			C)	When a character attempts to Disbelieve an illusion, the mage casting
				the spell rolls 1D100.  He adds 5% X his level, and then adds +5% for
				each point in the illusion spent on aspects other than Pain/Pleasure.
				If the modified D100 roll is BELOW the character's Belief Rating, then
				the character has successfully Disbelieved the illusion.  If the roll is
				Greater than the character's Belief Rating, then they Believe the
				illusion this round and any damage taken by the character will be
				real.

			D)	Pain is Not automatically believed.  In order to cause pain and damage
				to living things, the mage must roll 1D100 (following the steps in "C")
				as though the character had decided to Disbelieve, however , if the
				Pain attack fails, the character has successfully Disbelieved the illusion
				for That Round.  If a Pain attack fails two rounds in a row in this way,
				the character is allowed a Save Vs. Magic, if the character successfully
				saves Vs. magic he has Successfully Disbelieved the Illusion, without
				going through "C" above.  If a Performance spell has not been cast with
				the Pain spell, then the mage must Concentrate to cause pain.  "See
				Performance for more details".

		Creating Illusions:
			Illusions spells are a Unique Magic, because of the Multiple spells that can be
			cast in the same round.  The (*) spells in the following lists may be cast with
			other (*) spells in order to create illusions with more than one Aspect.  Such
			as Image with Sound, or Image, Taste and Substance.  the mage loses only one
			Stamina point, even if he casts more than one spell at a time.  To figure out the
			casting speed on multiple spells in a single round, take the longest delay, and
			-1 for each additional spell cast.  If a "Image" spell fails, all other aspects fail.

			Power Zero:						Power One:
			1)Detect Illusion (-1)			1)Area of Fear (-3)
			2)Dispel Illusion I (-3)		2)Area of Fog (-4)*
			3)Image I (-3)*					3)Complex Sounds (-3)*
			4)Pain I (-3)*					4)Deafness (-6)
			5)Reflection (-3)				5)Disguise (-15)
			6)See Invisibility (-5)			6)Dispel Illusion II (-4)
			7)Simple Sounds (-2)*			7)Hypnotic Image (-3)
			8)Slight Odors (-2)*			8)Image II (-4)*
			9)Slight Tastes (-2)*			9)Multiple Images (-2)*
			10)Substance I (-4)*			10)Pain II (-4)*
											11)Performance (-2)*
			Power Two:						12)Strong Odors (-3)*
			1)Blindness (-8)				13)Strong Tastes (-3)*
			2)Dispel Illusion III (-5)		14)Substance II (-5)*
			3)Familiar (-600)
			4)Image III (-5)*				Power Three:
			5)Image IV (-5)*				1)Anarchy I (-5)
			6)Invisibility I (-15)			2)Dispel Illusion IV (-6)
			7)Pain III (-5)*				3)Image V (-6)*
			8)Pleasure (-10)*				4)Substance IV (-7)*
			9)Perpetual Dark (-10)			5)Pain IV (-7)*
			10)Substance III (-6)*
											Power Five:
			Power Four:						1)Dispel Illusion VI (-8)
			1)Anarchy II (-10)				2)Pain VI (-8)*
			2)Dispel Illusion V (-7)		3)Substance VI (-9)
			3)Polymorph I (-8)				4)Summon Shadows (-10)
			4)Pain V (-7)*
			5)Substance V (-8)*				Power Seven:
											1)Pain VIII (-10)*
			Power Six:						2)Polymorph III (-10)
			1)Dispel Illusion VII (-9)		3)Substance VIII (-11)*
			2)Duration (-30)*
			3)Invisibility II (-15)			Power Eight:
			4)Pain VII (-9)*				1)Pain IX (-11)*
			5)Polymorph II (-10)			2)Permanent Illusion (-60)*
			6)Substance VII (-10)*			3)Substance IX (-12)*

	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Illusion() {
		// Illusion
		mSecondRequisite = AttributesRecord.DEXTERITY;

		// Creating Illusions:
		// Illusions spells are a Unique Magic, because of the Multiple spells that can be
		// cast in the same round.  The '*' spells in the following lists may be cast with
		// other '*' spells in order to create illusions with more than one Aspect.  Such
		// as Image with Sound, or Image, Taste and Substance.  the mage loses only one
		// Stamina point, even if he casts more than one spell at a time.  To figure out the
		// casting speed on multiple spells in a single round, take the longest delay, and
		// -1 for each additional spell cast.  If a "Image" spell fails, all other aspects fail.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Detect Illusion", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dispel Illusion I", 1, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Image I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain I", 1, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Reflection", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("See Invisibility", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Simple Sounds", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Slight Odors", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Slight Tastes", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance I", 1, 4, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Area of Fear", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Area of Fog", 0, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Complex Sounds", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deafness", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Disguise", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dispel Illusion II", 2, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hypnotic Image", 0, 3, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Image II", 2, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Multiple Images", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain II", 2, 4, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Performance", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strong Odors", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Strong Tastes", 0, 3, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance II", 2, 5, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Blindness", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dispel Illusion III", 3, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 600, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Image III", 3, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Image IV", 5, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility I", 1, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain III", 3, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pleasure", 0, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Perpetual Dark", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance III", 3, 6, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Anarchy I", 1, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dispel Illusion IV", 5, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Image V", 5, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance IV", 5, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain IV", 5, 7, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Anarchy II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Dispel Illusion V", 5, 7, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Polymorph I", 1, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain V", 5, 7, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance V", 5, 8, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Dispel Illusion VI", 6, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain VI", 6, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance VI", 6, 9, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Shadows", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Dispel Illusion VII", 7, 9, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Duration", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility II", 2, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Pain VII", 7, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Polymorph II", 2, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance VII", 7, 10, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Pain VIII", 8, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Polymorph III", 3, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance VIII", 8, 11, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Pain IX", 9, 11, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Permanent Illusion", 0, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Substance IX", 9, 12, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
