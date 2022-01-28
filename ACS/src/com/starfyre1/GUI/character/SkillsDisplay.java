/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.SkillsRecord;
import com.starfyre1.dataset.common.BaseClass;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SkillsDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		SKILLS_TITLE				= "Skills";																													//$NON-NLS-1$

	private static final String		LEVEL_BONUS_LABEL			= "Level Bonus";																											//$NON-NLS-1$
	private static final String		UNALLOCATED_LABEL			= "Unallocated";																											//$NON-NLS-1$

	private static final String		APPRAISE_LABEL				= "Appraise";																												//$NON-NLS-1$
	private static final String		BANDAGING_LABEL				= "Bandaging";																												//$NON-NLS-1$
	private static final String		DEPTH_SENSE_LABEL			= "Depth Sense";																											//$NON-NLS-1$
	private static final String		DETECT_MAGIC_LABEL			= "Detect Magic";																											//$NON-NLS-1$
	private static final String		DETECT_METALS_LABEL			= "Detect Metals";																											//$NON-NLS-1$
	private static final String		DETECT_MORALS_LABEL			= "Detect Morals";																											//$NON-NLS-1$
	private static final String		DETECT_SECRET_DOORS_LABEL	= "Secret Doors";																											//$NON-NLS-1$
	private static final String		DETECT_TRAPS_LABEL			= "Detect Traps";																											//$NON-NLS-1$
	private static final String		HERBAL_LORE_LABEL			= "Herbal Lore";																											//$NON-NLS-1$
	private static final String		HUNTING_LABEL				= "Hunting";																												//$NON-NLS-1$
	private static final String		PERCEPTION_LABEL			= "Perception";																												//$NON-NLS-1$
	private static final String		TRACKING_LABEL				= "Tracking";																												//$NON-NLS-1$

	private static final String		CONCEAL_LABEL				= "Conceal";																												//$NON-NLS-1$
	private static final String		STEALTH_LABEL				= "Stealth";																												//$NON-NLS-1$
	private static final String		HEAR_LABEL					= "Hear";																													//$NON-NLS-1$
	private static final String		LOCK_PICK_LABEL				= "Lock Pick";																												//$NON-NLS-1$
	private static final String		PICK_POCKET_LABEL			= "Pick Pocket";																											//$NON-NLS-1$
	private static final String		CLIMB_LABEL					= "Climb";																													//$NON-NLS-1$
	private static final String		FIND_TRAP_LABEL				= "Find Trap";																												//$NON-NLS-1$
	private static final String		REMOVE_TRAP_LABEL			= "Remove Trap";																											//$NON-NLS-1$

	private static final String		APPRAISE_TOOLTIP			= "<html>Estimate the value of gems and jewelry accurately.  Chance = 90% + 1% per level.</html>";							//$NON-NLS-1$
	private static final String		BANDAGING_TOOLTIP			= "<html>Bandaging take 1D6 rounds to do, and there are two different types of bandaging.<br><br>"							//$NON-NLS-1$
					+ "Minor Bandaging:	All characters can perform this type of bandaging, this will<br>"																					//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp prevent a character that has taken damage from bleeding further, or take<br>"												//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp a seriously bleeding character to a slightly bleeding state or a slightly<br>"												//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp bleeding to not bleeding at all.<br><br>"																					//$NON-NLS-1$
					+ "Major Bandaging:	All characters can ATTEMPT to perform this type of bandaging.<br>"																					//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp But it is not an automatic success, this type requires certain skills. If<br>"												//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp the character rolling his bandage skill makes his roll, the recipient<br>"													//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp regains 1D3 Hit Points, This will not completely heal a would, there<br>"													//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp will ALWAYS be (1) point of damage left.<br><br>"																			//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp A Major Bandaging roll will automatically stop bleeding, or at least down<br>"												//$NON-NLS-1$
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp grade the bleeding from a Serious Bleed to a Slight Bleed.</html>";															//$NON-NLS-1$
	private static final String		DEPTH_SENSE_TOOLTIP			= "<html>Dwarves have a \"Depth Sense\", they always know how far under-<br>"												//$NON-NLS-1$
					+ "ground they are.  Chance = 60% + 3% per level</html>";																												//$NON-NLS-1$
	private static final String		DETECT_MAGIC_TOOLTIP		= "<html>This costs (1) Mana Point, and will tell the caster if the Item <br>"												//$NON-NLS-1$
					+ "they cast it on is Magical in any sense.  Chance of Success <br>"																									//$NON-NLS-1$
					+ "(50% + 5% X Level) Range = Touch</html>";																															//$NON-NLS-1$
	private static final String		DETECT_METALS_TOOLTIP		= "<html>The ability to \"sense\" the location of various metals.<br>"														//$NON-NLS-1$
					+ "If any are within his area of effect the referee should give him a rough<br>"																						//$NON-NLS-1$
					+ "direction (one of the 4 compass headings: N, S, E, W, that's all).<br>"																								//$NON-NLS-1$
					+ "The referee should not tell how far away they are from the mage.<br>"																								//$NON-NLS-1$
					+ "But the mage must state what type of metal he is trying to detect<br>"																								//$NON-NLS-1$
					+ "before casting this spell. Area of Effect = Sphere of radius<br>"																									//$NON-NLS-1$
					+ "50' + 5' x level. Duration = 20 min.</html>";																														//$NON-NLS-1$
	private static final String		DETECT_MORALS_TOOLTIP		= "<html>The caster may attempt to determine which of the three basic types of morals his subject has.<br>"					//$NON-NLS-1$
					+ "The caster must state prior to casting this spell which of the three types of morals (Good, Fair or Evil)<br>"														//$NON-NLS-1$
					+ "he is attempting to detect. Success = 30% + 5% x the difference in levels (so if a 4th lvl mage threw Detect Morals I<br>"											//$NON-NLS-1$
					+ "on a 7th lvl warrior, he would have a 15% chance of detecting). If the creature is above 6th lvl, he is allowed to<br>"												//$NON-NLS-1$
					+ "save vs magic -10%. Range = 30 feet. Duration = 1 round.</html>";																									//$NON-NLS-1$
	private static final String		DETECT_SECRET_DOORS_TOOLTIP	= "<html>This spell gives the casting mage a 50% + 5% x level chance<br>"													//$NON-NLS-1$
					+ "of detecting a secret door when within 5' of the secret area (he<br>"																								//$NON-NLS-1$
					+ "must search 10 min. for it), and a 20% chance of detecting one<br>"																									//$NON-NLS-1$
					+ "when within 30' of it. Duration = 20 min. + 10 min. x lvl.</html>";																									//$NON-NLS-1$
	private static final String		DETECT_TRAPS_TOOLTIP		= "<html>Just like \"Detect Secret Doors\", but works for traps (wow). This<br>"											//$NON-NLS-1$
					+ "spell also negates the modifiers on the die roll when saving Vs<br>"																									//$NON-NLS-1$
					+ "surprise and the party is ambushed (so if a well, planned ambush<br>"																								//$NON-NLS-1$
					+ "takes place, ignore the modifier).</html>";																															//$NON-NLS-1$
	private static final String		HERBAL_LORE_TOOLTIP			= "<html>adds to the food supply and gives the possibility<br>"																//$NON-NLS-1$
					+ "of finding different useful Herbs, Healing and otherwise.</html>";																									//$NON-NLS-1$
	private static final String		HUNTING_TOOLTIP				= "<html>Roll every three hours that you spend hunting. Add +20% if they are hunting<br>"									//$NON-NLS-1$
					+ "with a Spear ora Bow.  If they are successful, they have 1D6-1 days of food.</html>";																				//$NON-NLS-1$
	private static final String		PERCEPTION_TOOLTIP			= "<html>How well you notice or see something, your chance to see the clue.</html>";										//$NON-NLS-1$
	private static final String		TRACKING_TOOLTIP			= "<html>This spell gives the mage the ability to track or trail his<br>"													//$NON-NLS-1$
					+ "opponents. His Success Rate = 70% + 3% x level. Roll a check<br>"																									//$NON-NLS-1$
					+ "each mile of terrain covered and modify with the rules below:<br><br>"																								//$NON-NLS-1$
					+ "Modifiers to TRACKING<br><br>"																																		//$NON-NLS-1$
					+ "&nbsp 1) 	Muddy Ground, Wet Ground or Snow = +20%<br>"																											//$NON-NLS-1$
					+ "&nbsp 2) 	Wounded Subject = +20%<br>"																																//$NON-NLS-1$
					+ "&nbsp 3) 	Number of subjects is over 5 = +5%<br>"																													//$NON-NLS-1$
					+ "&nbsp 4) 	Number of subjects is over 10 = +10%<br>"																												//$NON-NLS-1$
					+ "&nbsp 5) 	Subjects travel in basically a straight line = +10%<br>"																								//$NON-NLS-1$
					+ "&nbsp 6) 	Subjects make several frequent turns and curves -10%<br>"																								//$NON-NLS-1$
					+ "&nbsp 7) 	Subject is Forester = -30%<br>"																															//$NON-NLS-1$
					+ "&nbsp 8) 	Subject is a druid/Elf/ or other woodland type = -20%<br>"																								//$NON-NLS-1$
					+ "&nbsp 9) 	Hard Ground/Other tracks obscure = -30%<br><br>"																										//$NON-NLS-1$
					+ "Tracking underground in dungeon complexes is possible, but the<br>"																									//$NON-NLS-1$
					+ "mage must always play with the \"Hard Ground\" modifier (9),<br>"																									//$NON-NLS-1$
					+ "which makes it difficult. Underground a check should be made at<br>"																									//$NON-NLS-1$
					+ "each branching path (or alternative course). <br></html>";																											//$NON-NLS-1$

	private static final String		CONCEAL_TOOLTIP				= "<html>To hide behind curtains, around corners, in nooks and crannies,<br>"												//$NON-NLS-1$
					+ "behind pillars or statues, Etc.  This ability does NOT grant<br>"																									//$NON-NLS-1$
					+ "invisibility to the concealed thief.  If a creature is aware of the<br>"																								//$NON-NLS-1$
					+ "possibility of a thief hanging around, Halve the percentage chance<br>"																								//$NON-NLS-1$
					+ "for success, (Suspicious parties must have a reason for suspecting<br>"																								//$NON-NLS-1$
					+ "thieves and may not simply turn this ability \"on\" why they want it).<br></html>";																					//$NON-NLS-1$
	private static final String		STEALTH_TOOLTIP				= "<html>This ability allows the Thief to move with total silence at a rate One<br>"										//$NON-NLS-1$
					+ "class slower than they regularly move (due to their encumbrance). <br>"																								//$NON-NLS-1$
					+ "This means if the thief was human and regularly moved \"12\", they<br>"																								//$NON-NLS-1$
					+ "will move \"9\" when attempting \"Stealth\".  Failure to roll the stated<br>"																						//$NON-NLS-1$
					+ "chance does NOT mean the party heard the thief (see thieving<br>"																									//$NON-NLS-1$
					+ "ability \"Hear\" to find non-thief chances to hear something that<br>"																								//$NON-NLS-1$
					+ "occurs Quietly).  Successfully rolling the percentage means the<br>"																									//$NON-NLS-1$
					+ "thief moved with absolute silence.</html>";																															//$NON-NLS-1$
	private static final String		HEAR_TOOLTIP				= "<html>The percentage chance to hear something is obviously going to be<br>"												//$NON-NLS-1$
					+ "heavily modified by the ref.  Discretion as to the loudness of the <br>"																								//$NON-NLS-1$
					+ "noise (I.E. a Woman screaming 60' away (or even \"out of range at<br>"																								//$NON-NLS-1$
					+ "70') is tough to miss hearing).</html>";																																//$NON-NLS-1$
	private static final String		LOCK_PICK_TOOLTIP			= "<html>Just like it sounds.  This ability requires special lock picking<br>"												//$NON-NLS-1$
					+ "tools (available on the buying lists (only thieves may purchasethem)).<br>"																							//$NON-NLS-1$
					+ "Locks vary in toughness to pick.  If a roll fails to pick the<br>"																									//$NON-NLS-1$
					+ "lock, another attempt may be made in 2D10 minutes (but only if<br>"																									//$NON-NLS-1$
					+ "the thief continues to try for the entire time).</html>";																											//$NON-NLS-1$
	private static final String		PICK_POCKET_TOOLTIP			= "<html>This ability includes purse cutting and jewelry snatching.<br>"													//$NON-NLS-1$
					+ "Failing to roll the percentage (modified by level) by more than<br>"																									//$NON-NLS-1$
					+ "20% means the victim noticed the attempt.  Failure to roll by less<br>"																								//$NON-NLS-1$
					+ "than or equal to 20% means the attempt failed, but the victim didn't<br>"																							//$NON-NLS-1$
					+ "notice.  A thief may \"fail\" in this manner and still take the Item,<br>"																							//$NON-NLS-1$
					+ "but the victim will be totally aware the item was stolen (and by<br>"																								//$NON-NLS-1$
					+ "whom).  Subtract 5% for each point of In or Ws the \"Mark\" has<br>"																									//$NON-NLS-1$
					+ "above \"14\".</html>";																																				//$NON-NLS-1$
	private static final String		CLIMB_TOOLTIP				= "<html>\"No, you're NOT Spiderman!\"  Although, in dungeons with huge<br>"												//$NON-NLS-1$
					+ "carved blocks set in the walls (with lots of handy grooves to put<br>"																								//$NON-NLS-1$
					+ "fingers in) Might only subtract 10% to 20% (Ref.'s Discretion).  A<br>"																								//$NON-NLS-1$
					+ "Thief climbs at a rate of \"3\" and may not carry more than his carry<br>"																							//$NON-NLS-1$
					+ "(I.E. Un-encumbered).  A \"00\" rolled when climbing<br>"																											//$NON-NLS-1$
					+ "means failure (No Matter what the thief’s chances).  If the thief<br>"																								//$NON-NLS-1$
					+ "fails to successfully roll his chance to climb, he must roll a save Vs.<br>"																							//$NON-NLS-1$
					+ "Agility.  If the thief fails his save, he falls to the ground (taking<br>"																							//$NON-NLS-1$
					+ "1D6 pts. of damage per 10' of fall).  If the thief makes his save, he<br>"																							//$NON-NLS-1$
					+ "realizes the climb is too dangerous and must immediately re-trace<br>"																								//$NON-NLS-1$
					+ "their steps.  The thief must roll his Climb for every 10’.<br>"																										//$NON-NLS-1$
					+ "(The Ref. should encourage the use of grappling hooks and Iron Spikes).</html>";																						//$NON-NLS-1$
	private static final String		FIND_TRAP_TOOLTIP			= "<html>Roll 1D100 and if the thief rolls his percentage to Find Traps,<br>"												//$NON-NLS-1$
					+ "then he has found the triggering device.  If he successfully rolls it<br>"																							//$NON-NLS-1$
					+ "Again, then he may add 20% to any attempt to Remove the Trap<br>"																									//$NON-NLS-1$
					+ "(on that specific trap).  Failure to roll the Find trap percentage<br>"																								//$NON-NLS-1$
					+ "has a 30% chance (Ref.'s Discretion) of triggering the trap.</html>";																								//$NON-NLS-1$
	private static final String		REMOVE_TRAP_TOOLTIP			= "<html>Only small sized traps are removable, although the Ref. might<br>"													//$NON-NLS-1$
					+ "consider methods the thief could use to decommission larger traps<br>"																								//$NON-NLS-1$
					+ "(like, finding the lever, the creatures use the passageway pull<br>"																									//$NON-NLS-1$
					+ "to prevent the pit from opening).</html>";																															//$NON-NLS-1$

	private static final String[]	BASIC_SKILLS_LABELS			= { APPRAISE_LABEL, BANDAGING_LABEL, DEPTH_SENSE_LABEL, DETECT_MAGIC_LABEL,													//
					DETECT_METALS_LABEL, DETECT_MORALS_LABEL, DETECT_TRAPS_LABEL, HERBAL_LORE_LABEL, HUNTING_LABEL, PERCEPTION_LABEL,														//
					DETECT_SECRET_DOORS_LABEL, TRACKING_LABEL };

	private static final String[]	THIEF_SKILLS_LABELS			= { CLIMB_LABEL, CONCEAL_LABEL, FIND_TRAP_LABEL, HEAR_LABEL, LOCK_PICK_LABEL,												//
					PICK_POCKET_LABEL, REMOVE_TRAP_LABEL, STEALTH_LABEL };

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField				mAppraiseField;
	private JTextField				mBandagingField;
	private JTextField				mDepthSenseField;
	private JTextField				mDetectMagicField;
	private JTextField				mDetectMetalsField;
	private JTextField				mDetectMoralsField;
	private JTextField				mDetectSecretDoorsField;
	private JTextField				mDetectTrapsField;
	private JTextField				mHerbalLoreField;
	private JTextField				mHuntingField;
	private JTextField				mPerceptionField;
	private JTextField				mTrackingField;

	private JTextField				mClimbField;
	private JTextField				mConcealField;
	private JTextField				mFindTrapField;
	private JTextField				mHearField;
	private JTextField				mLockPickField;
	private JTextField				mPickPocketField;
	private JTextField				mRemoveTrapField;
	private JTextField				mStealthField;

	private JTextField				mClimbLevelBonusField;
	private JTextField				mConcealLevelBonusField;
	private JTextField				mFindTrapLevelBonusField;
	private JTextField				mHearLevelBonusField;
	private JTextField				mLockPickLevelBonusField;
	private JTextField				mPickPocketLevelBonusField;
	private JTextField				mRemoveTrapLevelBonusField;
	private JTextField				mStealthLevelBonusField;

	private JTextField				mUnallocatedField;

	public boolean					mLoadDisplayStarted			= false;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SkillsDisplay(CharacterSheet owner) {
		super(owner, SKILLS_TITLE);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel outer = new JPanel();
		outer.setBorder(new EmptyBorder(0, 5, 5, 10));
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

		JPanel wrapper = new JPanel(new GridLayout(11, 7, 5, 0));

		JLabel appraiseLabel = new JLabel(APPRAISE_LABEL, SwingConstants.RIGHT);
		appraiseLabel.setToolTipText(APPRAISE_TOOLTIP);
		mAppraiseField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mAppraiseField.setEditable(false);

		JLabel bandagingLabel = new JLabel(BANDAGING_LABEL, SwingConstants.RIGHT);
		bandagingLabel.setToolTipText(BANDAGING_TOOLTIP);
		mBandagingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBandagingField.setEditable(false);

		JLabel depthSenseLabel = new JLabel(DEPTH_SENSE_LABEL, SwingConstants.RIGHT);
		depthSenseLabel.setToolTipText(DEPTH_SENSE_TOOLTIP);
		mDepthSenseField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDepthSenseField.setEditable(false);

		JLabel detectMagicLabel = new JLabel(DETECT_MAGIC_LABEL, SwingConstants.RIGHT);
		detectMagicLabel.setToolTipText(DETECT_MAGIC_TOOLTIP);
		mDetectMagicField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMagicField.setEditable(false);

		JLabel detectMetalsLabel = new JLabel(DETECT_METALS_LABEL, SwingConstants.RIGHT);
		detectMetalsLabel.setToolTipText(DETECT_METALS_TOOLTIP);
		mDetectMetalsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMetalsField.setEditable(false);

		JLabel detectMoralsLabel = new JLabel(DETECT_MORALS_LABEL, SwingConstants.RIGHT);
		detectMoralsLabel.setToolTipText(DETECT_MORALS_TOOLTIP);
		mDetectMoralsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMoralsField.setEditable(false);

		JLabel detectSecretDoorsLabel = new JLabel(DETECT_SECRET_DOORS_LABEL, SwingConstants.RIGHT);
		detectSecretDoorsLabel.setToolTipText(DETECT_SECRET_DOORS_TOOLTIP);
		mDetectSecretDoorsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectSecretDoorsField.setEditable(false);

		JLabel detectTrapsLabel = new JLabel(DETECT_TRAPS_LABEL, SwingConstants.RIGHT);
		detectTrapsLabel.setToolTipText(DETECT_TRAPS_TOOLTIP);
		mDetectTrapsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectTrapsField.setEditable(false);

		JLabel herbalLoreLabel = new JLabel(HERBAL_LORE_LABEL, SwingConstants.RIGHT);
		herbalLoreLabel.setToolTipText(HERBAL_LORE_TOOLTIP);
		mHerbalLoreField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHerbalLoreField.setEditable(false);

		JLabel huntingLabel = new JLabel(HUNTING_LABEL, SwingConstants.RIGHT);
		huntingLabel.setToolTipText(HUNTING_TOOLTIP);
		mHuntingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHuntingField.setEditable(false);

		JLabel perceptionLabel = new JLabel(PERCEPTION_LABEL, SwingConstants.RIGHT);
		perceptionLabel.setToolTipText(PERCEPTION_TOOLTIP);
		mPerceptionField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mPerceptionField.setEditable(false);

		JLabel trackingLabel = new JLabel(TRACKING_LABEL, SwingConstants.RIGHT);
		trackingLabel.setToolTipText(TRACKING_TOOLTIP);
		mTrackingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mTrackingField.setEditable(false);

		JLabel concealLabel = new JLabel(CONCEAL_LABEL, SwingConstants.RIGHT);
		concealLabel.setToolTipText(CONCEAL_TOOLTIP);
		mConcealField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mConcealField.setEditable(false);
		mConcealLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel stealthLabel = new JLabel(STEALTH_LABEL, SwingConstants.RIGHT);
		stealthLabel.setToolTipText(STEALTH_TOOLTIP);
		mStealthField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mStealthField.setEditable(false);
		mStealthLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel hearLabel = new JLabel(HEAR_LABEL, SwingConstants.RIGHT);
		hearLabel.setToolTipText(HEAR_TOOLTIP);
		mHearField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHearField.setEditable(false);
		mHearLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel lockPickLabel = new JLabel(LOCK_PICK_LABEL, SwingConstants.RIGHT);
		lockPickLabel.setToolTipText(LOCK_PICK_TOOLTIP);
		mLockPickField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mLockPickField.setEditable(false);
		mLockPickLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel pickPocketLabel = new JLabel(PICK_POCKET_LABEL, SwingConstants.RIGHT);
		pickPocketLabel.setToolTipText(PICK_POCKET_TOOLTIP);
		mPickPocketField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mPickPocketField.setEditable(false);
		mPickPocketLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel climbLabel = new JLabel(CLIMB_LABEL, SwingConstants.RIGHT);
		climbLabel.setToolTipText(CLIMB_TOOLTIP);
		mClimbField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mClimbField.setEditable(false);
		mClimbLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel findTrapLabel = new JLabel(FIND_TRAP_LABEL, SwingConstants.RIGHT);
		findTrapLabel.setToolTipText(FIND_TRAP_TOOLTIP);
		mFindTrapField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mFindTrapField.setEditable(false);
		mFindTrapLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel removeTrapLabel = new JLabel(REMOVE_TRAP_LABEL, SwingConstants.RIGHT);
		removeTrapLabel.setToolTipText(REMOVE_TRAP_TOOLTIP);
		mRemoveTrapField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mRemoveTrapField.setEditable(false);
		mRemoveTrapLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel levelBonusLabel = new JLabel(LEVEL_BONUS_LABEL, SwingConstants.CENTER);

		JLabel unallocatedLabel = new JLabel(UNALLOCATED_LABEL, SwingConstants.CENTER);
		mUnallocatedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mUnallocatedField.setEditable(false);

		enableFields(false);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(levelBonusLabel);

		wrapper.add(appraiseLabel);
		wrapper.add(mAppraiseField);
		wrapper.add(detectTrapsLabel);
		wrapper.add(mDetectTrapsField);
		wrapper.add(climbLabel);
		wrapper.add(mClimbField);
		wrapper.add(mClimbLevelBonusField);

		wrapper.add(bandagingLabel);
		wrapper.add(mBandagingField);
		wrapper.add(herbalLoreLabel);
		wrapper.add(mHerbalLoreField);
		wrapper.add(concealLabel);
		wrapper.add(mConcealField);
		wrapper.add(mConcealLevelBonusField);

		wrapper.add(depthSenseLabel);
		wrapper.add(mDepthSenseField);
		wrapper.add(huntingLabel);
		wrapper.add(mHuntingField);
		wrapper.add(findTrapLabel);
		wrapper.add(mFindTrapField);
		wrapper.add(mFindTrapLevelBonusField);

		wrapper.add(detectMagicLabel);
		wrapper.add(mDetectMagicField);
		wrapper.add(perceptionLabel);
		wrapper.add(mPerceptionField);
		wrapper.add(hearLabel);
		wrapper.add(mHearField);
		wrapper.add(mHearLevelBonusField);

		wrapper.add(detectMetalsLabel);
		wrapper.add(mDetectMetalsField);
		wrapper.add(detectSecretDoorsLabel);
		wrapper.add(mDetectSecretDoorsField);
		wrapper.add(lockPickLabel);
		wrapper.add(mLockPickField);
		wrapper.add(mLockPickLevelBonusField);

		wrapper.add(detectMoralsLabel);
		wrapper.add(mDetectMoralsField);
		wrapper.add(trackingLabel);
		wrapper.add(mTrackingField);
		wrapper.add(pickPocketLabel);
		wrapper.add(mPickPocketField);
		wrapper.add(mPickPocketLevelBonusField);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(removeTrapLabel);
		wrapper.add(mRemoveTrapField);
		wrapper.add(mRemoveTrapLevelBonusField);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(stealthLabel);
		wrapper.add(mStealthField);
		wrapper.add(mStealthLevelBonusField);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(unallocatedLabel);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(mUnallocatedField);

		outer.add(wrapper);
		return outer;
	}

	@Override
	public void loadDisplay() {
		CharacterSheet sheet = (CharacterSheet) getOwner();
		if (!sheet.isLoadingData()) {
			sheet.setLoadingData(true);
			SkillsRecord record = sheet.getSkillsRecord();
			BaseClass characterClass = sheet.getHeaderRecord().getCharacterClass();

			if (!(record == null || characterClass == null)) {
				enableFields(true);
				mAppraiseField.setText(TKStringHelpers.EMPTY_STRING + record.getAppraise());
				mBandagingField.setText(TKStringHelpers.EMPTY_STRING + record.getBandaging());
				mDepthSenseField.setText(TKStringHelpers.EMPTY_STRING + record.getDepthSense());
				mDetectMagicField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMagic());
				mDetectMetalsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMetals());
				mDetectMoralsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMorals());
				mDetectSecretDoorsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectSecretDoors());
				mDetectTrapsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectTraps());
				mHerbalLoreField.setText(TKStringHelpers.EMPTY_STRING + record.getHerbalLore());
				mHuntingField.setText(TKStringHelpers.EMPTY_STRING + record.getHunting());
				mPerceptionField.setText(TKStringHelpers.EMPTY_STRING + record.getPerception());
				mTrackingField.setText(TKStringHelpers.EMPTY_STRING + record.getTracking());

				mClimbField.setText(TKStringHelpers.EMPTY_STRING + (record.getClimb() + record.getClimbLevelBonus()));
				mConcealField.setText(TKStringHelpers.EMPTY_STRING + (record.getConceal() + record.getConcealLevelBonus()));
				mFindTrapField.setText(TKStringHelpers.EMPTY_STRING + (record.getFindTrap() + record.getFindTrapLevelBonus()));
				mHearField.setText(TKStringHelpers.EMPTY_STRING + (record.getHear() + record.getHearLevelBonus()));
				mLockPickField.setText(TKStringHelpers.EMPTY_STRING + (record.getLockPick() + record.getLockPickLevelBonus()));
				mPickPocketField.setText(TKStringHelpers.EMPTY_STRING + (record.getPickPocket() + record.getPickPocketLevelBonus()));
				mRemoveTrapField.setText(TKStringHelpers.EMPTY_STRING + (record.getRemoveTrap() + record.getRemoveTrapLevelBonus()));
				mStealthField.setText(TKStringHelpers.EMPTY_STRING + (record.getStealth() + record.getStealthLevelBonus()));

				mClimbLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getClimbLevelBonus());
				mConcealLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getConcealLevelBonus());
				mFindTrapLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getFindTrapLevelBonus());
				mHearLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getHearLevelBonus());
				mLockPickLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getLockPickLevelBonus());
				mPickPocketLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getPickPocketLevelBonus());
				mRemoveTrapLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getRemoveTrapLevelBonus());
				mStealthLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getStealthLevelBonus());

				mUnallocatedField.setText(TKStringHelpers.EMPTY_STRING + record.getUnallocatedSkills());

				updateToolTips();
			} else {
				enableFields(false);
			}
		}
		sheet.setLoadingData(false);

	}

	public void updateToolTips() {
		SkillsRecord record = ((CharacterSheet) getOwner()).getSkillsRecord();

		if (record != null) {
			mAppraiseField.setToolTipText(record.getAppraiseToolTip());
			mBandagingField.setToolTipText(record.getBandagingToolTip());
			mDepthSenseField.setToolTipText(record.getDepthSenseToolTip());
			mDetectMagicField.setToolTipText(record.getDetectMagicToolTip());
			mDetectMetalsField.setToolTipText(record.getDetectMetalsToolTip());
			mDetectMoralsField.setToolTipText(record.getDetectMoralsToolTip());
			mDetectSecretDoorsField.setToolTipText(record.getDetectSecretDoorsToolTip());
			mDetectTrapsField.setToolTipText(record.getDetectTrapsToolTip());
			mHerbalLoreField.setToolTipText(record.getHerbalLoreToolTip());
			mHuntingField.setToolTipText(record.getHuntingToolTip());
			mPerceptionField.setToolTipText(record.getPerceptionToolTip());
			mTrackingField.setToolTipText(record.getTrackingToolTip());

			mClimbField.setToolTipText(record.getClimbToolTip());
			mConcealField.setToolTipText(record.getConcealToolTip());
			mFindTrapField.setToolTipText(record.getFindTrapToolTip());
			mHearField.setToolTipText(record.getHearToolTip());
			mLockPickField.setToolTipText(record.getLockPickToolTip());
			mPickPocketField.setToolTipText(record.getPickPocketToolTip());
			mRemoveTrapField.setToolTipText(record.getRemoveTrapToolTip());
			mStealthField.setToolTipText(record.getStealthToolTip());

			mClimbLevelBonusField.setToolTipText(record.getClimbLevelBonusToolTip());
			mConcealLevelBonusField.setToolTipText(record.getConcealLevelBonusToolTip());
			mFindTrapLevelBonusField.setToolTipText(record.getFindTrapLevelBonusToolTip());
			mHearLevelBonusField.setToolTipText(record.getHearLevelBonusToolTip());
			mLockPickLevelBonusField.setToolTipText(record.getLockPickLevelBonusToolTip());
			mPickPocketLevelBonusField.setToolTipText(record.getPickPocketLevelBonusToolTip());
			mRemoveTrapLevelBonusField.setToolTipText(record.getRemoveTrapLevelBonusToolTip());
			mStealthLevelBonusField.setToolTipText(record.getStealthLevelBonusToolTip());

			mUnallocatedField.setToolTipText(record.getUnallocatedSkillsToolTip());
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		Object source = e.getDocument().getProperty(TKComponentHelpers.DOCUMENT_OWNER);

		SkillsRecord record = ((CharacterSheet) getOwner()).getSkillsRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mConcealLevelBonusField)) {
				record.setConcealLevelBonus(TKStringHelpers.getIntValue(mConcealLevelBonusField.getText(), record.getConcealLevelBonus()));
			} else if (((JTextField) source).equals(mStealthLevelBonusField)) {
				record.setStealthLevelBonus(TKStringHelpers.getIntValue(mStealthLevelBonusField.getText(), record.getStealthLevelBonus()));
			} else if (((JTextField) source).equals(mHearLevelBonusField)) {
				record.setHearLevelBonus(TKStringHelpers.getIntValue(mHearLevelBonusField.getText(), record.getHearLevelBonus()));
			} else if (((JTextField) source).equals(mLockPickLevelBonusField)) {
				record.setLockPickLevelBonus(TKStringHelpers.getIntValue(mLockPickLevelBonusField.getText(), record.getLockPickLevelBonus()));
			} else if (((JTextField) source).equals(mPickPocketLevelBonusField)) {
				record.setPickPocketLevelBonus(TKStringHelpers.getIntValue(mPickPocketLevelBonusField.getText(), record.getPickPocketLevelBonus()));
			} else if (((JTextField) source).equals(mClimbLevelBonusField)) {
				record.setClimbLevelBonus(TKStringHelpers.getIntValue(mClimbLevelBonusField.getText(), record.getClimbLevelBonus()));
			} else if (((JTextField) source).equals(mFindTrapLevelBonusField)) {
				record.setFindTrapLevelBonus(TKStringHelpers.getIntValue(mFindTrapLevelBonusField.getText(), record.getFindTrapLevelBonus()));
			} else if (((JTextField) source).equals(mRemoveTrapLevelBonusField)) {
				record.setRemoveTrapLevelBonus(TKStringHelpers.getIntValue(mRemoveTrapLevelBonusField.getText(), record.getRemoveTrapLevelBonus()));
			}
		}
		record.generateUnallocatedSkills();
		if (!((CharacterSheet) getOwner()).isLoadingData()) {
			Runnable load = new Runnable() {

				@Override
				public void run() {
					loadDisplay();
				}
			};
			SwingUtilities.invokeLater(load);
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public void enableFields(boolean enabled) {
		mClimbLevelBonusField.setEditable(enabled);
		mConcealLevelBonusField.setEditable(enabled);
		mFindTrapLevelBonusField.setEditable(enabled);
		mHearLevelBonusField.setEditable(enabled);
		mLockPickLevelBonusField.setEditable(enabled);
		mPickPocketLevelBonusField.setEditable(enabled);
		mRemoveTrapLevelBonusField.setEditable(enabled);
		mStealthLevelBonusField.setEditable(enabled);
	}

	public static String[] getBasicSkillsLabels() {
		return BASIC_SKILLS_LABELS;
	}

	public static String[] getThiefSkillsLabels() {
		return THIEF_SKILLS_LABELS;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
