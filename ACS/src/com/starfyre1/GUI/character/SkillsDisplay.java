/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.SkillsRecord;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SkillsDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		SKILLS_TITLE				= "Skills";																		//$NON-NLS-1$

	private static final String		LEVEL_BONUS_LABEL			= "Level Bonus";																//$NON-NLS-1$
	private static final String		UNALLOCATED_LABEL			= "Unallocated";																//$NON-NLS-1$

	private static final String		APPRAISE_LABEL				= "Appraise";																	//$NON-NLS-1$
	private static final String		BANDAGING_LABEL				= "Bandaging";																	//$NON-NLS-1$
	private static final String		DEPTH_SENSE_LABEL			= "Depth Sense";																//$NON-NLS-1$
	private static final String		DETECT_MAGIC_LABEL			= "Detect Magic";																//$NON-NLS-1$
	private static final String		DETECT_METALS_LABEL			= "Detect Metals";																//$NON-NLS-1$
	private static final String		DETECT_MORALS_LABEL			= "Detect Morals";																//$NON-NLS-1$
	private static final String		DETECT_SECRET_DOORS_LABEL	= "Secret Doors";																//$NON-NLS-1$
	private static final String		DETECT_TRAPS_LABEL			= "Detect Traps";																//$NON-NLS-1$
	private static final String		HERBAL_LORE_LABEL			= "Herbal Lore";																//$NON-NLS-1$
	private static final String		HUNTING_LABEL				= "Hunting";																	//$NON-NLS-1$
	private static final String		PERCEPTION_LABEL			= "Perception";																	//$NON-NLS-1$
	private static final String		TRACKING_LABEL				= "Tracking";																	//$NON-NLS-1$

	private static final String		CONCEAL_LABEL				= "Conceal";																	//$NON-NLS-1$
	private static final String		STEALTH_LABEL				= "Stealth";																	//$NON-NLS-1$
	private static final String		HEAR_LABEL					= "Hear";																		//$NON-NLS-1$
	private static final String		LOCK_PICK_LABEL				= "Lock Pick";																	//$NON-NLS-1$
	private static final String		PICK_POCKET_LABEL			= "Pick Pocket";																//$NON-NLS-1$
	private static final String		CLIMB_LABEL					= "Climb";																		//$NON-NLS-1$
	private static final String		FIND_TRAP_LABEL				= "Find Trap";																	//$NON-NLS-1$
	private static final String		REMOVE_TRAP_LABEL			= "Remove Trap";																//$NON-NLS-1$

	private static final String[]	BASIC_SKILLS_LABELS			= { APPRAISE_LABEL, BANDAGING_LABEL, DEPTH_SENSE_LABEL, DETECT_MAGIC_LABEL,		//
					DETECT_METALS_LABEL, DETECT_MORALS_LABEL, DETECT_TRAPS_LABEL, HERBAL_LORE_LABEL, HUNTING_LABEL, PERCEPTION_LABEL,			//
					DETECT_SECRET_DOORS_LABEL, TRACKING_LABEL };

	private static final String[]	THIEF_SKILLS_LABELS			= { CLIMB_LABEL, CONCEAL_LABEL, FIND_TRAP_LABEL, HEAR_LABEL, LOCK_PICK_LABEL,	//
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
		mAppraiseField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mAppraiseField.setEditable(false);

		JLabel bandagingLabel = new JLabel(BANDAGING_LABEL, SwingConstants.RIGHT);
		mBandagingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBandagingField.setEditable(false);

		JLabel depthSenseLabel = new JLabel(DEPTH_SENSE_LABEL, SwingConstants.RIGHT);
		mDepthSenseField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDepthSenseField.setEditable(false);

		JLabel detectMagicLabel = new JLabel(DETECT_MAGIC_LABEL, SwingConstants.RIGHT);
		mDetectMagicField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMagicField.setEditable(false);

		JLabel detectMetalsLabel = new JLabel(DETECT_METALS_LABEL, SwingConstants.RIGHT);
		mDetectMetalsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMetalsField.setEditable(false);

		JLabel detectMoralsLabel = new JLabel(DETECT_MORALS_LABEL, SwingConstants.RIGHT);
		mDetectMoralsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectMoralsField.setToolTipText("<html>With this spell the caster may attempt to determine which of the three basic types of morals his subject has.<br>" //$NON-NLS-1$
						+ "The caster must state prior to casting this spell which of the three types of morals (Good, Fair or Evil)<br>" //$NON-NLS-1$
						+ "he is attempting to detect. Success = 30% + 5% x the difference in levels (so if a 4th lvl mage threw Detect Morals I<br>" //$NON-NLS-1$
						+ "on a 7th lvl warrior, he would have a 15% chance of detecting). If the creature is above 6th lvl, he is allowed to<br>" //$NON-NLS-1$
						+ "save vs magic -10%. Range = 30 feet. Duration = 1 round.<html>"); // //$NON-NLS-1$
		mDetectMoralsField.setEditable(false);

		JLabel detectSecretDoorsLabel = new JLabel(DETECT_SECRET_DOORS_LABEL, SwingConstants.RIGHT);
		mDetectSecretDoorsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectSecretDoorsField.setEditable(false);

		JLabel detectTrapsLabel = new JLabel(DETECT_TRAPS_LABEL, SwingConstants.RIGHT);
		mDetectTrapsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDetectTrapsField.setEditable(false);

		JLabel herbalLoreLabel = new JLabel(HERBAL_LORE_LABEL, SwingConstants.RIGHT);
		mHerbalLoreField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHerbalLoreField.setEditable(false);

		JLabel huntingLabel = new JLabel(HUNTING_LABEL, SwingConstants.RIGHT);
		mHuntingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHuntingField.setEditable(false);

		JLabel perceptionLabel = new JLabel(PERCEPTION_LABEL, SwingConstants.RIGHT);
		mPerceptionField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mPerceptionField.setEditable(false);

		JLabel trackingLabel = new JLabel(TRACKING_LABEL, SwingConstants.RIGHT);
		mTrackingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mTrackingField.setEditable(false);

		JLabel concealLabel = new JLabel(CONCEAL_LABEL, SwingConstants.RIGHT);
		mConcealField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mConcealField.setEditable(false);
		mConcealLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel stealthLabel = new JLabel(STEALTH_LABEL, SwingConstants.RIGHT);
		mStealthField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mStealthField.setEditable(false);
		mStealthLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel hearLabel = new JLabel(HEAR_LABEL, SwingConstants.RIGHT);
		mHearField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHearField.setEditable(false);
		mHearLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel lockPickLabel = new JLabel(LOCK_PICK_LABEL, SwingConstants.RIGHT);
		mLockPickField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mLockPickField.setEditable(false);
		mLockPickLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel pickPocketLabel = new JLabel(PICK_POCKET_LABEL, SwingConstants.RIGHT);
		mPickPocketField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mPickPocketField.setEditable(false);
		mPickPocketLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel climbLabel = new JLabel(CLIMB_LABEL, SwingConstants.RIGHT);
		mClimbField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mClimbField.setEditable(false);
		mClimbLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel findTrapLabel = new JLabel(FIND_TRAP_LABEL, SwingConstants.RIGHT);
		mFindTrapField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mFindTrapField.setEditable(false);
		mFindTrapLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel removeTrapLabel = new JLabel(REMOVE_TRAP_LABEL, SwingConstants.RIGHT);
		mRemoveTrapField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mRemoveTrapField.setEditable(false);
		mRemoveTrapLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

		JLabel levelBonusLabel = new JLabel(LEVEL_BONUS_LABEL, SwingConstants.CENTER);

		JLabel unallocatedLabel = new JLabel(UNALLOCATED_LABEL, SwingConstants.CENTER);
		mUnallocatedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mUnallocatedField.setEditable(false);

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
		}
		sheet.setLoadingData(false);

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
			loadDisplay();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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
