/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Sarn extends PriestsBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*
	The Priest
	Second Requisite = Wisdom
		The Priest of Athri have many different magic's and law's, each are strict
		in their own ways.  Some factions are intolerant of other's like Narese the
		Goddess of Pain, or very tolerant like the Priest's of Narius the God of
		Knowledge.  Here are the Gods and their Priest's Spells.

		Sarn
		Second Requisite-Dexterity/Wisdom

			Sarn is the God of Murder, a God of such pure evil that he is second only to
			Rysh himself.  Clerics of Sarn must make at least one kill per level of a
			humanoid creature.

			The actual list goes one kill at 1st level, two kills at 2nd level, and so on.
			His churches require at least 30% of all earnings, gotten through assassination.
			Sarn takes neutral people into his ranks as long as they follow his rule, usually
			his clerics start out evil though.

			His clerics have a maximum of 40% armor to work any of their spells.  Also
			they cannot use the metal Mithril at all, it works against Sarn's magic gift to
			his followers.
			Here are their spells :
	
			Power Zero:						Power One:
			1)Alertness I (-35)F			1)Silence (-10)
			2)Heal Self (-60)				2)Alertness II (-60)F
			3)Vault (-0)					3)Images (-50)F
			4)Sheath (-60)					4)Sphere of Air I (-6)
			5)Rune of Combat I (-lots)		5)Freedom (-8)
			6)Protection I (-6)F			6)Rune of Combat II (-lots)
			7)Star Sight (-8)				7)Protection II (-8)f
			8)Darkness (-6)					8)Climb (-10)
	                      					9)Area of Gloom (-30)
	
			Power Two:						Power Three:
			1)Haste (-6)H					1)Protection IV (-12)f
			2)Protection III (-10)F			2)Familiar (-300)f
			3)Invisibility I (-17)			3)Agility (-5)
			4)Rune of Combat III (-lots)	4)Stop Poison (-15)
			5)Night Sight (-15)				5)Alertness III (-90)F

			Power Four:						Power Five:
			1)Blend with Shadows (-5)		1)Touch of Death (-5)F
			2)Transform (-30)F				2)Protection V (-14)F
			3)Fly (-12)						3)Venom Vapors (-6)
			4)Damnation (-120)				4)Summon Storm (-35)
	
			Power Six:						Power Seven:
			1)Deathword I (-1)				1)Walk in Shadows (-10)
			2)Protection VI (-16)F			2)Symbol I (-120)
			3)Commune (-40)
	
			Power Eight:
			1)Symbol II (-140)
			2)Deathword II (-5)


	*/

	private static final String DEXTERITY_WISDOM = "Dexterity/Wisdom"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Sarn() {
		// Sarn
		mSecondRequisite = DEXTERITY_WISDOM;
		// Sarn is the God of Murder, a God of such pure evil that he is second only to
		// Rysh himself.  Clerics of Sarn must make at least one kill per level of a
		// humanoid creature.
		// The actual list goes one kill at 1st level, two kills at 2nd level, and so on.
		// His churches require at least 30% of all earnings, gotten through assassination.
		// Sarn takes neutral people into his ranks as long as they follow his rule, usually
		// his clerics start out evil though.
		// His clerics have a maximum of 40% armor to work any of their spells.  Also
		// they cannot use the metal Mithril at all, it works against Sarn's magic gift to
		// his followers.  Here are their spells :

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Alertness I", 1, 35, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal Self", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vault", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sheath", 0, 60, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat I", 1, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection I", 1, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Star Sight", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Darkness", 0, 6, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Silence", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness II", 2, 60, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Images", 0, 50, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Sphere of Air I", 1, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Freedom", 0, 8, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat II", 2, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection II", 2, 8, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Climb", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Area of Gloom", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$
		// DW Verify "Climb" doesn't exist Sarn Power 1
		// DW Verify "Images" doesn't exist while Image I - Image V does Sarn Power 1 & Thaer Power 2

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Haste", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection III", 3, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Invisibility I", 1, 17, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Rune of Combat III", 3, -1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Night Sight", 0, 15, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Protection IV", 5, 12, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Familiar", 0, 300, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Agility", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stop Poison", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Alertness III", 3, 90, 1, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$
		// DW Verify "Alertness III" doesn't exist Sarn Power 3 & Tarn Power 3

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Blend with Shadows", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Transform", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Fly", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Damnation", 0, 120, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Touch of Death", 0, 5, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection V", 5, 14, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Venom Vapors", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storm", 0, 35, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Deathword I", 1, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection VI", 6, 16, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 40, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Walk in Shadows", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Symbol I", 1, 120, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Symbol II", 2, 140, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Deathword II", 2, 5, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
