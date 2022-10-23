/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataset.classes.priests;

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
			2)Heal Self (-20)				2)Healing (-120)
			3)Detect Morals 1 (-10)			3)Bless Fields (-300)
			4)Clear Water (-2)				4)Stop Poison (-6)
			5)Shield I (-25)				5)Cure Illness (animals) (-20)
			6)Protection : Fire (-0)		6)Silence (-6)
			7)Earth Womb (-5)				7)Speak with Animals (-1)
			8)Protection : Animals (-4)		8)Star Sight (-12)
			9)Locate Life : Animals (-15)	9)Web Stream (-5)
			10)Temperate Earth (-10)		10)Scattered Showers (-30)
	
			Power Two:						Power Three:
			1)Shield II (-30)				1)Shield III (-35)
			2)Protection : Lightning (-0)	2)Weight (-6)F
			3)Protection : Lycanthrope (-5)	3)Passage (-50)
			4)Hide (-15)					4)Telepathy (-15)
			5)Empathy (-5)					5)Summon Animals I (-10)F
			6)Vengeance (-2)F				6)Detect Morals II (-15)F
			7)ESP (-6)F						7)Water Breathing (-10)
			8)Protection : Dark (-5)		8)Cure Illness (-20)
			9)Harvest Ritual (-500)			9)Alter Shape (-3)
	
			Power Four:						Power Five:
			1)Shield IV (-40)				1)Fly (-1)
			2)Control Winds (-12)			2)Summon Storm (-30)
			3)Circle of Life (-500)			3)Circle of Light (-25)
			4)Summon Animals II (-9)F		4)Control Weather (-30)
			5)Lightning Storm I (-6)F		5)Commune (-30)
			6)Liquefy Earth (-10)			6)Part Rock I (-20)
			7)Part Water (-20)				7)Escape (-1)
	
			Power Six:						Power Seven:
			1)Command Insects (-10)			1)Regeneration (-200)
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
						new SpellRecord(0, "Darts", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Heal Self", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(0, "Detect Morals I", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(0, "Clear Water", 0, 2, 0), //$NON-NLS-1$
						new SpellRecord(0, "Shield I", 1, 25, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Fire", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Earth Womb", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(0, "Protection : Animals", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Neutralize Poison", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(0, "Temperate Earth", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(0, "Fire Hands", 0, 7, 0), //$NON-NLS-1$
						new SpellRecord(0, "Resuscitate", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(0, "Produce Water", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(0, "Flash Healing", 0, 5, 0)))); //$NON-NLS-1$

		// Power One:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(1, "Growth : Plant", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(1, "Healing", 0, 120, 0), //$NON-NLS-1$
						new SpellRecord(1, "Bless Fields", 0, 300, 0), //$NON-NLS-1$
						new SpellRecord(1, "Stop Poison", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Cure Illness : Animals", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(1, "Silence", 0, 6, 0), //$NON-NLS-1$
						new SpellRecord(1, "Speak with Animals", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(1, "Star Sight", 0, 12, 0), //$NON-NLS-1$
						new SpellRecord(1, "Web Stream", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(1, "Scattered Showers", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(1, "Staff I", 1, 2, 1), //$NON-NLS-1$
						new SpellRecord(1, "Healing : Animals", 0, 40, 1), //$NON-NLS-1$
						new SpellRecord(1, "Golden Touch", 0, 5, 1)))); //$NON-NLS-1$

		// Power Two:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(2, "Shield II", 2, 30, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Lightning", 0, 0, 0), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Lycanthrope", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Hide", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(2, "Empathy", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Vengeance", 0, 2, 1), //$NON-NLS-1$
						new SpellRecord(2, "ESP", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(2, "Protection : Dark", 0, 5, 0), //$NON-NLS-1$
						new SpellRecord(2, "Harvest Ritual", 0, 500, 0), //$NON-NLS-1$
						new SpellRecord(2, "Hearth", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(2, "Find Water", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(2, "Staff II", 2, 3, 1), //$NON-NLS-1$
						new SpellRecord(2, "Moderate Healing", 0, 7, 0)))); //$NON-NLS-1$

		// Power Three:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(3, "Shield III", 3, 35, 0), //$NON-NLS-1$
						new SpellRecord(3, "Weight", 0, 6, 1), //$NON-NLS-1$
						new SpellRecord(3, "Passage", 0, 50, 0), //$NON-NLS-1$
						new SpellRecord(3, "Telepathy", 0, 15, 0), //$NON-NLS-1$
						new SpellRecord(3, "Summon Animals I", 1, 10, 1), //$NON-NLS-1$
						new SpellRecord(3, "Detect Morals II", 2, 15, 1), //$NON-NLS-1$
						new SpellRecord(3, "Water Breathing", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(3, "Cure Illness", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(3, "Draw Water", 0, -1, 0), //$NON-NLS-1$
						new SpellRecord(3, "Salve of Healing", 0, -1, 0)))); //$NON-NLS-1$

		// Power Four:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(4, "Shield IV", 4, 40, 0), //$NON-NLS-1$
						new SpellRecord(4, "Control Winds", 0, 12, 0), //$NON-NLS-1$
						new SpellRecord(4, "Circle of Life", 0, 500, 0), //$NON-NLS-1$
						new SpellRecord(4, "Summon Animals II", 2, 9, 1), //$NON-NLS-1$
						new SpellRecord(4, "Lightning Storm I", 1, 6, 1), //$NON-NLS-1$
						new SpellRecord(4, "Liquefy Earth", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(4, "Part Water", 0, 20, 0), //$NON-NLS-1$
						new SpellRecord(4, "Anoint", 0, 60, 0), //$NON-NLS-1$
						new SpellRecord(4, "Alter Shape", 0, 3, 0), //$NON-NLS-1$
						new SpellRecord(4, "Staff III", 3, 4, 1)))); //$NON-NLS-1$

		// Power Five:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(5, "Fly", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(5, "Summon Storm", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Circle of Life", 0, 25, 0), //$NON-NLS-1$
						new SpellRecord(5, "Control Weather", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Commune", 0, 30, 0), //$NON-NLS-1$
						new SpellRecord(5, "Part Rock I", 1, 20, 0), //$NON-NLS-1$
						new SpellRecord(5, "Escape", 0, 1, 0), //$NON-NLS-1$
						new SpellRecord(5, "ReBirth", 0, 65, 1), //$NON-NLS-1$
						new SpellRecord(5, "Gift of Sight", 0, 35, 0), //$NON-NLS-1$
						new SpellRecord(5, "Gather Power", 0, 120, 0)))); //$NON-NLS-1$
		// DW Verify "Circle of Light" is supposed to be 'Circle of Life' Lorrell Power 5

		// Power Six:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(6, "Command Insects", 0, 10, 0), //$NON-NLS-1$
						new SpellRecord(6, "Lightning Storm II", 2, 6, 1), //$NON-NLS-1$
						new SpellRecord(6, "Call to Law", 0, 8, 0), //$NON-NLS-1$
						new SpellRecord(6, "Vitality", 0, 35, 1)))); //$NON-NLS-1$

		// Power Seven:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(7, "Regeneration", 0, 200, 0), //$NON-NLS-1$
						new SpellRecord(7, "Resurrection", 0, 30, 1), //$NON-NLS-1$
						new SpellRecord(7, "The Mottled Hand", 0, 4, 0), //$NON-NLS-1$
						new SpellRecord(7, "Shield V", 0, 65, 0)))); //$NON-NLS-1$

		// Power Eight:
		mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //
						new SpellRecord(8, "Gift of the Gods", 0, 65, 1)))); //$NON-NLS-1$

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
	public int getBandaging() {
		return 10;

	}

	@Override
	public int getHerbalLore() {
		return 15;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
