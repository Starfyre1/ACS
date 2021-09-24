/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class Lorrell extends PriestsBase {
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
	
		Lorrell
		Second Requisite - Wisdom
	
			Lorrell is the Goddess of the Harvest and of all cultivated things.  Her clerics are
			welcomed into any farming community.  She protects all who ask it of her, with-
			out question, and is especially fond of midwifes and birthing women.
	
			Her clerics rarely wear armor, but when they do have a maximum armor of 40%.
			Their weapons are limited to blunt objects or farming instruments, but they mostly
			carry a staff or cudgel.  Some are proficient with bows, but these are a rarity.
			Lorrell has no enemies in the pantheon of Gods, however she frowns on the
			clerics of Narese.
	
			Most of Lorrell's clerics and priests settle down in small towns and farming communities,
			helping the people tame the land and seeking to set their lives to peace.
	
			Power Zero:						Power One:
			1) Darts (-5)					1)Growth (plant) (-15)
			2)Heal self (-20)				2)Healing (-120)
			3)Detect Morals 1 (-10)			3)Bless Fields (-300)
			4)Clear Water (-2)				4)Stop Poison (-6)
			5)Shield I (-25)				5)Cure Illness (animals) (-20)
			6)Protection / Fire (-0)		6)Silence (-6)
			7)Earth Womb (-5)				7)Speak w/ Animals (-1)
			8)Protection/ Animals (-4)		8)Starsight (-12)
			9)Locate life / Animals (-15)	9)Web Stream (-5)
			10)Temperate Earth (-10)		10)Scattered Showers (-30)

			Power Two:						Power Three:
			1)Shield II (-30)				1)Shield III (-35)
			2)Protection/Lightning (-0)		2)Weight (-6)F
			3)Protection/Lycanthrope (-5)	3)Passage (-50)
			4)Hide (-15)					4)Telepathy (-15)
			5)Empathy (-5)					5)Summon Animals I (-10)F
			6)Vengeance (-2)F				6)Detect Morals II (-15)F
			7)Esp (-6)F						7)Water Breathing (-10)
			8)Protection/Darkness (-5)		8)Cure Illness (-20)
			9)Harvest Ritual (-500)			9)Altershape (-3)

			Power Four:						Power Five:
			1)Shield IV (-40)				1)Fly (-1)
			2)Control Winds (-12)			2)Summon Storms (-30)
			3)Circle of Life (-500)			3)Circle of Light (-25)
			4)Summon Animals II (-9)F		4)Control Weather (-30)
			5)Lightning Storm I (-6)F		5)Commune (-30)
			6)Liquefy Earth (-10)			6)Part Rock I (-20)
			7)Part Water (-20)				7)Escape (-1)

			Power Six:						Power Seven:
			1)Command Insects (-10)			1)Regenerate (-200)
			2)Lightning Storm II (-6)F		2)Resurrection (-30)F
			3)Call to Law (-8)				3)The Mottled Hand (-4)
	*/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public Lorrell() {
		// Lorrell
		mSecondRequisite = AttributesRecord.WISDOM;
		// Lorrell is the Goddess of the Harvest and of all cultivated things.  Her clerics are
		// welcomed into any farming community.  She protects all who ask it of her, with-
		// out question, and is especially fond of midwifes and birthing women.
		// Her clerics rarely wear armor, but when they do have a maximum armor of 40%.
		// Their weapons are limited to blunt objects or farming instruments, but they mostly
		// carry a staff or cudgel.  Some are proficient with bows, but these are a rarity.
		// Lorrell has no enemies in the pantheon of Gods, however she frowns on the
		// clerics of Narese.
		// Most of Lorrell's clerics and priests settle down in small towns and farming communities,
		// helping the people tame the land and seeking to set their lives to peace.

		// Power Zero:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Darts", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Heal self", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals 1", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Clear Water", 0, 2, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Shield I", 1, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection / Fire", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Earth Womb", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/ Animals", 0, 4, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Locate life / Animals", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Temperate Earth", 0, 10, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Growth : plant", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Healing", 0, 120, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Bless Fields", 0, 300, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Stop Poison", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness : animals", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Silence", 0, 6, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Speak w/ Animals", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Starsight", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Web Stream", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Scattered Showers", 0, 30, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Shield II", 2, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Lightning", 0, 0, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Lycanthrope", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Hide", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Empathy", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Vengeance", 0, 2, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Esp", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Protection/Darkness", 0, 5, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Harvest Ritual", 0, 500, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Shield III", 3, 35, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Weight", 0, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Passage", 0, 50, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Telepathy", 0, 15, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals I", 1, 10, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Detect Morals II", 2, 15, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Water Breathing", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Cure Illness", 0, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Altershape", 0, 3, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Shield IV", 5, 40, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Winds", 0, 12, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Circle of Life", 0, 500, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Animals II", 2, 9, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lightning Storm I", 1, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Liquefy Earth", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Part Water", 0, 20, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Fly", 0, 1, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Summon Storms", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Circle of Light", 0, 25, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Control Weather", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Commune", 0, 30, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Part Rock I", 1, 20, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Escape", 0, 1, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Command Insects", 0, 10, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Lightning Storm II", 2, 6, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Call to Law", 0, 8, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord("Regenerate", 0, 200, 0, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("Resurrection", 0, 30, 1, TKStringHelpers.EMPTY_STRING), //$NON-NLS-1$
						new SpellRecord("The Mottled Hand", 0, 4, 0, TKStringHelpers.EMPTY_STRING)))); //$NON-NLS-1$

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
		return 10;

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
