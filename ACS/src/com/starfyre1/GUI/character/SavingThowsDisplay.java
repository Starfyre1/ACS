/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.SavingThrowsRecord;

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
	private static final String	SAVING_THROWS_TITLE	= "Saving Throws";			//$NON-NLS-1$

	private static final String	AGILITY_LABEL		= "Agility";				//$NON-NLS-1$
	private static final String	BLEEDING_LABEL		= "Bleed";					//$NON-NLS-1$
	private static final String	MAGIC_LABEL			= "Magic";					//$NON-NLS-1$
	private static final String	POISON_LABEL		= "Poison";					//$NON-NLS-1$
	private static final String	SHOCK_LABEL			= "Shock";					//$NON-NLS-1$
	private static final String	STRESS_LABEL		= "Stress";					//$NON-NLS-1$
	private static final String	UNCONSCIOUS_LABEL	= "Uncon.";					//$NON-NLS-1$
	private static final String	SUPRISE_LABEL		= "Suprise";				//$NON-NLS-1$
	private static final String	BELIEF_LABEL		= "Belief";					//$NON-NLS-1$
	private static final String	PERCEPTION_LABEL	= "Perception";				//$NON-NLS-1$

	// DW add level info to tooltip for real level (+4%/lvl-1)
	//private static final String	SAVING_THROWS_TOOLTIP	= "Roll Percentile Dice below Saving throw number";	//$NON-NLS-1$
	private static final String	AGILITY_TOOLTIP		= "(DEX * 3) + 10";			//$NON-NLS-1$
	private static final String	BLEEDING_TOOLTIP	= "STR + (CON * 2) + WP";	//$NON-NLS-1$
	private static final String	MAGIC_TOOLTIP		= "(INT * 2) + WIS";		//$NON-NLS-1$
	private static final String	POISON_TOOLTIP		= "(CON * 3) + 10";			//$NON-NLS-1$
	private static final String	SHOCK_TOOLTIP		= "(CON * 2) + WP + 30";	//$NON-NLS-1$
	private static final String	STRESS_TOOLTIP		= "WP * 3";					//$NON-NLS-1$
	private static final String	UNCONSCIOUS_TOOLTIP	= "STR + CON + (WP * 2)";	//$NON-NLS-1$
	private static final String	SUPRISE_TOOLTIP		= "INT + DEX + WP + 35";	//$NON-NLS-1$
	private static final String	BELIEF_TOOLTIP		= "INT + WIS + 35";			//$NON-NLS-1$
	private static final String	PERCEPTION_TOOLTIP	= "INT + WIS + 15";			//$NON-NLS-1$

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
	private JTextField			mSupriseField;
	private JTextField			mBeliefField;
	private JTextField			mPerceptionField;

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
		JPanel wrapper = new JPanel(new GridLayout(10, 2, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		JLabel agilityLabel = new JLabel(AGILITY_LABEL, SwingConstants.RIGHT);
		mAgilityField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		agilityLabel.setToolTipText(AGILITY_TOOLTIP);
		mAgilityField.setToolTipText(AGILITY_TOOLTIP);
		mAgilityField.setEditable(false);

		JLabel bleedingLabel = new JLabel(BLEEDING_LABEL, SwingConstants.RIGHT);
		mBleedingField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		bleedingLabel.setToolTipText(BLEEDING_TOOLTIP);
		mBleedingField.setToolTipText(BLEEDING_TOOLTIP);
		mBleedingField.setEditable(false);

		JLabel magicLabel = new JLabel(MAGIC_LABEL, SwingConstants.RIGHT);
		mMagicField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		magicLabel.setToolTipText(MAGIC_TOOLTIP);
		mMagicField.setToolTipText(MAGIC_TOOLTIP);
		mMagicField.setEditable(false);

		JLabel poisonLabel = new JLabel(POISON_LABEL, SwingConstants.RIGHT);
		mPoisonField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		poisonLabel.setToolTipText(POISON_TOOLTIP);
		mPoisonField.setToolTipText(POISON_TOOLTIP);
		mPoisonField.setEditable(false);

		JLabel shockLabel = new JLabel(SHOCK_LABEL, SwingConstants.RIGHT);
		mShockField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		shockLabel.setToolTipText(SHOCK_TOOLTIP);
		mShockField.setToolTipText(SHOCK_TOOLTIP);
		mShockField.setEditable(false);

		JLabel stressLabel = new JLabel(STRESS_LABEL, SwingConstants.RIGHT);
		mStressField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		stressLabel.setToolTipText(STRESS_TOOLTIP);
		mStressField.setToolTipText(STRESS_TOOLTIP);
		mStressField.setEditable(false);

		JLabel unconciousLabel = new JLabel(UNCONSCIOUS_LABEL, SwingConstants.RIGHT);
		mUnconciousField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		unconciousLabel.setToolTipText(UNCONSCIOUS_TOOLTIP);
		mUnconciousField.setToolTipText(UNCONSCIOUS_TOOLTIP);
		mUnconciousField.setEditable(false);

		JLabel supriseLabel = new JLabel(SUPRISE_LABEL, SwingConstants.RIGHT);
		mSupriseField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		supriseLabel.setToolTipText(SUPRISE_TOOLTIP);
		mSupriseField.setToolTipText(SUPRISE_TOOLTIP);
		mSupriseField.setEditable(false);

		JLabel beliefLabel = new JLabel(BELIEF_LABEL, SwingConstants.RIGHT);
		mBeliefField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		beliefLabel.setToolTipText(BELIEF_TOOLTIP);
		mBeliefField.setToolTipText(BELIEF_TOOLTIP);
		mBeliefField.setEditable(false);

		JLabel perceptionLabel = new JLabel(PERCEPTION_LABEL, SwingConstants.RIGHT);
		mPerceptionField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		perceptionLabel.setToolTipText(PERCEPTION_TOOLTIP);
		mPerceptionField.setToolTipText(BELIEF_TOOLTIP);
		mPerceptionField.setEditable(false);

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
		wrapper.add(supriseLabel);
		wrapper.add(mSupriseField);
		wrapper.add(beliefLabel);
		wrapper.add(mBeliefField);
		wrapper.add(perceptionLabel);
		wrapper.add(mPerceptionField);

		return wrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public void loadDisplay() {
		SavingThrowsRecord record = ((CharacterSheet) getOwner()).getSavingThrowsRecord();

		if (record != null) {
			mAgilityField.setText(TKStringHelpers.EMPTY_STRING + record.getAgility());
			mBleedingField.setText(TKStringHelpers.EMPTY_STRING + record.getBleeding());
			mMagicField.setText(TKStringHelpers.EMPTY_STRING + record.getMagic());
			mPoisonField.setText(TKStringHelpers.EMPTY_STRING + record.getPoison());
			mShockField.setText(TKStringHelpers.EMPTY_STRING + record.getShock());
			mStressField.setText(TKStringHelpers.EMPTY_STRING + record.getStress());
			mUnconciousField.setText(TKStringHelpers.EMPTY_STRING + record.getUnconcious());
			mSupriseField.setText(TKStringHelpers.EMPTY_STRING + record.getSuprise());
			mPerceptionField.setText(TKStringHelpers.EMPTY_STRING + record.getPerception());
			mBeliefField.setText(TKStringHelpers.EMPTY_STRING + record.getBelief());
		}
	}
}
