/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.SavingThrowsRecord;
import com.starfyre1.dataset.common.BaseClass;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// DW 	Investigate implementation...
// 		would probably need to implement a turn counter...
//		or might be able to use status flags that user can turn on and off
/*	Explanation of Saving Throws:

	Agility-		This save is used by the GM. whenever the character is
			doing anything that requires a high degree of dexterity
			or coordination, or whenever the GM. feels one is required.
	Magic-		Most of the magical spells used by the mages or priests will
			allow a save vs. magic for half or no damage.

	Shock-		This save is used when major physical trauma happens to
			a player character.  Some of the reasons a character would
			have to save vs. shock would be being raised from the dead,
			being turned to stoned, or changing shape (unless they are
			the casting mage).  Most of the time a failed shock roll will
			mean the characters death, or taking 1D100 points of damage
			which in most cases is the same.  A save vs. shock is
			also required whenever a character goes into negative hits,
			you will find more information on that subject in the section
			on Healing and Death.

	Stress-		This is the saving throw for the mind, whenever someone
			needs to do something extremely brave, or when you want
			to try something that requires a test of willpower.
		Example:
			(I once had a character that actually drove a knife into his
			own heart, after a Necromancer had cast a Return spell on
			him, this spell allows you to not only come back from the
			dead in 7 days, but also raises all of your statistics (1) point.
			I had to roll my saving throw vs. stress at a -50%.  Needless
			to say my character at that point was not only power hungry
			but a fairly high ranking priest of Rysh.)

	Poison-		If a character saves vs. poison they will only take half damage.

	Bleeding-	Whenever a character has taken more than 3 actual Hit Points
			of damage, they must save vs. bleeding that round and all
			future rounds until the wound can be bandaged.
		1) 	 If a character is successful in his save, he is considered to
			be Slightly Bleeding.
		2)  	Slightly Bleeding characters lose 1 H.P. per 2 rounds of active
			movement of any type.
		3)  	If a character fails his save, he is considered to be Seriously
			Bleeding, and loses 1 H.P. per round of active movement.
		4)  	Parrying is not considered Active movement, however using
			your Free Attack is.
		5)  	Bandaging will stop the bleeding after combat or lessen it
			during combat.  See the section on Bandaging in the Healing
			and Death section.

Unconsciousness-	This is rolled whenever a character is hit in the head,
			solar plexus, or tender area, or whenever a character has less than (3)
			Hit Points or Stamina.  If the character fail’s he is unconscious
			for 2D10 rounds (30 seconds to 5 minutes).  When that time is up
			the character will repeat the process until he wakes up, or passes
			away, depending on the citation
*/

public class SavingThowsDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SAVING_THROWS_TITLE	= "Saving Throws";																								//$NON-NLS-1$

	private static final String	AGILITY_LABEL		= "Agility";																									//$NON-NLS-1$
	private static final String	BLEEDING_LABEL		= "Bleed";																										//$NON-NLS-1$
	private static final String	MAGIC_LABEL			= "Magic";																										//$NON-NLS-1$
	private static final String	POISON_LABEL		= "Poison";																										//$NON-NLS-1$
	private static final String	SHOCK_LABEL			= "Shock";																										//$NON-NLS-1$
	private static final String	STRESS_LABEL		= "Stress";																										//$NON-NLS-1$
	private static final String	UNCONSCIOUS_LABEL	= "Uncon.";																										//$NON-NLS-1$
	private static final String	SURPRISE_LABEL		= "Surprise";																									//$NON-NLS-1$
	private static final String	BELIEF_LABEL		= "Belief";																										//$NON-NLS-1$

	private static final String	AGILITY_TOOLTIP		= "<html>This save is used by the GM. whenever the character is<br>"											//$NON-NLS-1$
					+ "doing anything that requires a high degree of dexterity<br>"																					//$NON-NLS-1$
					+ "or coordination, or whenever the GM. feels one is required.</html>";																			//$NON-NLS-1$
	private static final String	BLEEDING_TOOLTIP	= "<html>Whenever a character has taken more than 3 actual Hit Points of damage, they must <br>"				//$NON-NLS-1$
					+ "save vs. bleeding that round and all future rounds until the wound can be bandaged.<br><br>"													//$NON-NLS-1$
					+ "&nbsp 1) 	 If a character is successful in his save, he is considered to be Slightly Bleeding.<br>"										//$NON-NLS-1$
					+ "&nbsp 2)  	Slightly Bleeding characters lose 1 H.P. per 2 rounds of active movement of any type.<br>"										//$NON-NLS-1$
					+ "&nbsp 3)  	If a character fails his save, he is considered to be Seriously Bleeding, and<br>"												//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp loses 1 H.P. per round of active movement.<br>"														//$NON-NLS-1$
					+ "&nbsp 4)  	Parrying is not considered Active movement, however usingyour Free Attack is.<br>"												//$NON-NLS-1$
					+ "&nbsp 5)  	Bandaging will stop the bleeding after combat or lessen it during combat.<br><br>"												//$NON-NLS-1$
					+ "See the section on Bandaging in the Healing and Death section.</html>";																		//$NON-NLS-1$
	private static final String	MAGIC_TOOLTIP		= "<html>Most of the magical spells used by the mages or priests will<br>"										//$NON-NLS-1$
					+ "allow a save vs. magic for half or no damage.</html>";																						//$NON-NLS-1$
	private static final String	POISON_TOOLTIP		= "<html>If a character saves vs. poison, they will only take half damage.</html>";								//$NON-NLS-1$
	private static final String	SHOCK_TOOLTIP		= "<html>This save is used when major physical trauma happens to <br>"											//$NON-NLS-1$
					+ "a player character.  Some of the reasons a character would<br>"																				//$NON-NLS-1$
					+ "have to save vs. shock would be being raised from the dead,<br>"																				//$NON-NLS-1$
					+ "being turned to stoned, or changing shape (unless they are<br>"																				//$NON-NLS-1$
					+ "the casting mage).  Most of the time a failed shock roll will<br>"																			//$NON-NLS-1$
					+ "mean the characters death, or taking 1D100 points of damage<br>"																				//$NON-NLS-1$
					+ "which in most cases is the same.  A save vs. shock is<br>"																					//$NON-NLS-1$
					+ "also required whenever a character goes into negative hits,<br>"																				//$NON-NLS-1$
					+ "you will find more information on that subject in the section<br>"																			//$NON-NLS-1$
					+ "on Healing and Death.</html>";																												//$NON-NLS-1$
	private static final String	STRESS_TOOLTIP		= "<html>This is the saving throw for the mind, whenever someone<br>"											//$NON-NLS-1$
					+ "needs to do something extremely brave, or when you want<br>"																					//$NON-NLS-1$
					+ "to try something that requires a test of willpower.</html>";																					//$NON-NLS-1$
	private static final String	UNCONSCIOUS_TOOLTIP	= "<html>This is rolled whenever a character is hit in the head, solar<br>"										//$NON-NLS-1$
					+ "plexus, or tender area, or whenever a character has less than (3)<br>"																		//$NON-NLS-1$
					+ "Hit Points or Stamina.  If the character fail’s he is unconscious<br>"																		//$NON-NLS-1$
					+ "for 2D10 rounds (30 seconds to 5 minutes).  When that time is up<br>"																		//$NON-NLS-1$
					+ "the character will repeat the process until he wakes up, or passes<br>"																		//$NON-NLS-1$
					+ "away, depending on the citation</html>";																										//$NON-NLS-1$
	private static final String	SURPRISE_TOOLTIP	= "<html>This is also a saving throw, it doesn't go up like the other's because it is <br>"						//$NON-NLS-1$
					+ "a little different.  Where the others are geared for each individual character,<br>"															//$NON-NLS-1$
					+ "surprise is for a group or the individual.  If you are traveling with a group of<br>"														//$NON-NLS-1$
					+ "friends through the forest, your scout or party leader might roll surprise to<br>"															//$NON-NLS-1$
					+ "keep a group of Yrch (Orcs) from surprising you by dropping their capture<br>"																//$NON-NLS-1$
					+ "nets on you.  If you are alone you would roll, if you saved you would see the<br>"															//$NON-NLS-1$
					+ "net before it was too late, if not you had best get used to the underworld.<br>"																//$NON-NLS-1$
					+ "This can be modified by special circumstances by the Game Master.</html>";																	//$NON-NLS-1$
	private static final String	BELIEF_TOOLTIP		= "<html>This is what you use to save vs. Illusions, when fighting an illusion<br>"								//$NON-NLS-1$
					+ "this acts as your armor rating.  The illusion will attempt to roll over your<br>"															//$NON-NLS-1$
					+ "belief rating to make you believe you've been hit and have taken actual<br>"																	//$NON-NLS-1$
					+ "damage, and therefore in pain.</html>";																										//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mAgilityField;
	private JTextField			mBleedingField;
	private JTextField			mMagicField;
	private JTextField			mPoisonField;
	private JTextField			mShockField;
	private JTextField			mStressField;
	private JTextField			mUnconciousField;
	private JTextField			mSurpriseField;
	private JTextField			mBeliefField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SavingThowsDisplay(CharacterSheet owner) {
		super(owner, SAVING_THROWS_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		JPanel wrapper = new JPanel(new GridLayout(9, 2, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		JLabel agilityLabel = new JLabel(AGILITY_LABEL, SwingConstants.RIGHT);
		mAgilityField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		agilityLabel.setToolTipText(AGILITY_TOOLTIP);
		mAgilityField.setEditable(false);

		JLabel bleedingLabel = new JLabel(BLEEDING_LABEL, SwingConstants.RIGHT);
		mBleedingField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		bleedingLabel.setToolTipText(BLEEDING_TOOLTIP);
		mBleedingField.setEditable(false);

		JLabel magicLabel = new JLabel(MAGIC_LABEL, SwingConstants.RIGHT);
		mMagicField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		magicLabel.setToolTipText(MAGIC_TOOLTIP);
		mMagicField.setEditable(false);

		JLabel poisonLabel = new JLabel(POISON_LABEL, SwingConstants.RIGHT);
		mPoisonField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		poisonLabel.setToolTipText(POISON_TOOLTIP);
		mPoisonField.setEditable(false);

		JLabel shockLabel = new JLabel(SHOCK_LABEL, SwingConstants.RIGHT);
		mShockField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		shockLabel.setToolTipText(SHOCK_TOOLTIP);
		mShockField.setEditable(false);

		JLabel stressLabel = new JLabel(STRESS_LABEL, SwingConstants.RIGHT);
		mStressField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		stressLabel.setToolTipText(STRESS_TOOLTIP);
		mStressField.setEditable(false);

		JLabel unconciousLabel = new JLabel(UNCONSCIOUS_LABEL, SwingConstants.RIGHT);
		mUnconciousField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		unconciousLabel.setToolTipText(UNCONSCIOUS_TOOLTIP);
		mUnconciousField.setEditable(false);

		JLabel surpriseLabel = new JLabel(SURPRISE_LABEL, SwingConstants.RIGHT);
		mSurpriseField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		surpriseLabel.setToolTipText(SURPRISE_TOOLTIP);
		mSurpriseField.setEditable(false);

		JLabel beliefLabel = new JLabel(BELIEF_LABEL, SwingConstants.RIGHT);
		mBeliefField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		beliefLabel.setToolTipText(BELIEF_TOOLTIP);
		mBeliefField.setEditable(false);

		wrapper.add(agilityLabel);
		wrapper.add(mAgilityField);
		wrapper.add(bleedingLabel);
		wrapper.add(mBleedingField);
		wrapper.add(magicLabel);
		wrapper.add(mMagicField);
		wrapper.add(poisonLabel);
		wrapper.add(mPoisonField);
		wrapper.add(shockLabel);
		wrapper.add(mShockField);
		wrapper.add(stressLabel);
		wrapper.add(mStressField);
		wrapper.add(unconciousLabel);
		wrapper.add(mUnconciousField);
		wrapper.add(surpriseLabel);
		wrapper.add(mSurpriseField);
		wrapper.add(beliefLabel);
		wrapper.add(mBeliefField);

		return wrapper;
	}

	@Override
	public void loadDisplay() {
		CharacterSheet sheet = (CharacterSheet) getOwner();
		SavingThrowsRecord record = sheet.getSavingThrowsRecord();

		if (!(record == null)) {
			mAgilityField.setText(TKStringHelpers.EMPTY_STRING + record.getAgility());
			mBleedingField.setText(TKStringHelpers.EMPTY_STRING + record.getBleeding());
			mMagicField.setText(TKStringHelpers.EMPTY_STRING + record.getMagic());
			mPoisonField.setText(TKStringHelpers.EMPTY_STRING + record.getPoison());
			mShockField.setText(TKStringHelpers.EMPTY_STRING + record.getShock());
			mStressField.setText(TKStringHelpers.EMPTY_STRING + record.getStress());
			mUnconciousField.setText(TKStringHelpers.EMPTY_STRING + record.getUnconscious());
			mSurpriseField.setText(TKStringHelpers.EMPTY_STRING + record.getSurprise());
			mBeliefField.setText(TKStringHelpers.EMPTY_STRING + record.getBelief());

			updateToolTips();
		} else {
			mAgilityField.setText(TKStringHelpers.EMPTY_STRING);
			mBleedingField.setText(TKStringHelpers.EMPTY_STRING);
			mMagicField.setText(TKStringHelpers.EMPTY_STRING);
			mPoisonField.setText(TKStringHelpers.EMPTY_STRING);
			mShockField.setText(TKStringHelpers.EMPTY_STRING);
			mStressField.setText(TKStringHelpers.EMPTY_STRING);
			mUnconciousField.setText(TKStringHelpers.EMPTY_STRING);
			mSurpriseField.setText(TKStringHelpers.EMPTY_STRING);
			mBeliefField.setText(TKStringHelpers.EMPTY_STRING);

		}
		sheet.setLoadingData(false);
	}

	public void updateToolTips() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		SavingThrowsRecord record = owner.getSavingThrowsRecord();
		BaseClass base = owner.getHeaderRecord().getCharacterClass();
		String zero = "0"; //$NON-NLS-1$

		if (record != null) {
			mAgilityField.setToolTipText(base == null ? zero : record.getAgilityToolTip());
			mBleedingField.setToolTipText(base == null ? zero : record.getBleedingToolTip());
			mMagicField.setToolTipText(base == null ? zero : record.getMagicToolTip());
			mPoisonField.setToolTipText(base == null ? zero : record.getPoisonToolTip());
			mShockField.setToolTipText(base == null ? zero : record.getShockToolTip());
			mStressField.setToolTipText(base == null ? zero : record.getStressToolTip());
			mUnconciousField.setToolTipText(base == null ? zero : record.getUnconsciousToolTip());
			mSurpriseField.setToolTipText(base == null ? zero : record.getSurpriseToolTip());
			mBeliefField.setToolTipText(base == null ? zero : record.getBeliefToolTip());
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
