/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.SkillsRecord;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SkillsDisplay extends TKTitledDisplay implements FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_TITLE				= "Skills";					//$NON-NLS-1$

	private static final String	BANDAGING_LABEL				= "Bandaging";				//$NON-NLS-1$
	private static final String	HUNTING_LABEL				= "Hunting";				//$NON-NLS-1$
	private static final String	TRACKING_LABEL				= "Tracking";				//$NON-NLS-1$
	private static final String	DETECT_MAGIC_LABEL			= "Detect Magic";			//$NON-NLS-1$
	private static final String	DETECT_MORALS_LABEL			= "Detect Morals";			//$NON-NLS-1$
	private static final String	DETECT_METALS_LABEL			= "Detect Metals";			//$NON-NLS-1$
	private static final String	DETECT_SECRET_DOORS_LABEL	= "Detect Secret Doors";	//$NON-NLS-1$
	private static final String	DETECT_TRAPS_LABEL			= "Detect Traps";			//$NON-NLS-1$
	private static final String	APPRAISE_LABEL				= "Appraise";				//$NON-NLS-1$
	private static final String	DEPTH_SENSE_LABEL			= "Depth Sense";			//$NON-NLS-1$
	private static final String	CONCEAL_LABEL				= "Conceal";				//$NON-NLS-1$
	private static final String	STEALTH_LABEL				= "Stealth";				//$NON-NLS-1$
	private static final String	HEAR_LABEL					= "Hear";					//$NON-NLS-1$
	private static final String	LOCK_PICK_LABEL				= "Lock Pick";				//$NON-NLS-1$
	private static final String	POCKET_PICK_LABEL			= "Pocket Pick";			//$NON-NLS-1$
	private static final String	CLIMB_LABEL					= "Climb";					//$NON-NLS-1$
	private static final String	FIND_TRAP_LABEL				= "Find Trap";				//$NON-NLS-1$
	private static final String	REMOVE_TRAP_LABEL			= "Remove Trap";			//$NON-NLS-1$
	private static final String	LEVEL_BONUS_LABEL			= "Level Bonus";			//$NON-NLS-1$
	private static final String	UNALLOCATED_LABEL			= "Unallocated";			//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mAppraiseField;
	private JTextField			mBandagingField;
	private JTextField			mDetectMagicField;
	private JTextField			mDetectMoralsField;
	private JTextField			mDetectMetalsField;
	private JTextField			mHuntingField;
	private JTextField			mTrackingField;
	private JTextField			mDetectSecretDoorsField;
	private JTextField			mDetectTrapsField;
	private JTextField			mDepthSenseField;

	private JTextField			mConcealField;
	private JTextField			mStealthField;
	private JTextField			mHearField;
	private JTextField			mLockPickField;
	private JTextField			mPocketPickField;
	private JTextField			mClimbField;
	private JTextField			mFindTrapField;
	private JTextField			mRemoveTrapField;

	private JTextField			mConcealLevelBonusField;
	private JTextField			mStealthLevelBonusField;
	private JTextField			mHearLevelBonusField;
	private JTextField			mLockPickLevelBonusField;
	private JTextField			mPocketPickLevelBonusField;
	private JTextField			mClimbLevelBonusField;
	private JTextField			mFindTrapLevelBonusField;
	private JTextField			mRemoveTrapLevelBonusField;
	private JTextField			mUnallocatedField;

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
		outer.setBorder(new EmptyBorder(0, 0, 5, 10));
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

		JPanel wrapper = new JPanel(new GridLayout(11, 5, 5, 0));

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

		JLabel huntingLabel = new JLabel(HUNTING_LABEL, SwingConstants.RIGHT);
		mHuntingField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHuntingField.setEditable(false);

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

		JLabel pocketPickLabel = new JLabel(POCKET_PICK_LABEL, SwingConstants.RIGHT);
		mPocketPickField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mPocketPickField.setEditable(false);
		mPocketPickLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);

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

		wrapper.add(appraiseLabel);
		wrapper.add(mAppraiseField);
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(levelBonusLabel);

		wrapper.add(bandagingLabel);
		wrapper.add(mBandagingField);
		wrapper.add(climbLabel);
		wrapper.add(mClimbField);
		wrapper.add(mClimbLevelBonusField);

		wrapper.add(depthSenseLabel);
		wrapper.add(mDepthSenseField);
		wrapper.add(concealLabel);
		wrapper.add(mConcealField);
		wrapper.add(mConcealLevelBonusField);

		wrapper.add(detectMagicLabel);
		wrapper.add(mDetectMagicField);
		wrapper.add(findTrapLabel);
		wrapper.add(mFindTrapField);
		wrapper.add(mFindTrapLevelBonusField);

		wrapper.add(detectMetalsLabel);
		wrapper.add(mDetectMetalsField);
		wrapper.add(hearLabel);
		wrapper.add(mHearField);
		wrapper.add(mHearLevelBonusField);

		wrapper.add(detectMoralsLabel);
		wrapper.add(mDetectMoralsField);
		wrapper.add(lockPickLabel);
		wrapper.add(mLockPickField);
		wrapper.add(mLockPickLevelBonusField);

		wrapper.add(detectSecretDoorsLabel);
		wrapper.add(mDetectSecretDoorsField);
		wrapper.add(pocketPickLabel);
		wrapper.add(mPocketPickField);
		wrapper.add(mPocketPickLevelBonusField);

		wrapper.add(detectTrapsLabel);
		wrapper.add(mDetectTrapsField);
		wrapper.add(removeTrapLabel);
		wrapper.add(mRemoveTrapField);
		wrapper.add(mRemoveTrapLevelBonusField);

		wrapper.add(huntingLabel);
		wrapper.add(mHuntingField);
		wrapper.add(stealthLabel);
		wrapper.add(mStealthField);
		wrapper.add(mStealthLevelBonusField);

		wrapper.add(trackingLabel);
		wrapper.add(mTrackingField);
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(unallocatedLabel);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(mUnallocatedField);

		outer.add(wrapper);
		return outer;
	}

	@Override
	protected void loadDisplay() {

		SkillsRecord record = ((CharacterSheet) getOwner()).getSkillsRecord();

		mBandagingField.setText(TKStringHelpers.EMPTY_STRING + record.getBandaging());
		mHuntingField.setText(TKStringHelpers.EMPTY_STRING + record.getHunting());
		mTrackingField.setText(TKStringHelpers.EMPTY_STRING + record.getTracking());
		mDetectMagicField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMagic());
		mDetectMoralsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMorals());
		mDetectMetalsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectMetals());
		mDetectSecretDoorsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectSecretDoors());
		mDetectTrapsField.setText(TKStringHelpers.EMPTY_STRING + record.getDetectTraps());
		mAppraiseField.setText(TKStringHelpers.EMPTY_STRING + record.getAppraise());
		mDepthSenseField.setText(TKStringHelpers.EMPTY_STRING + record.getDepthSense());
		mConcealField.setText(TKStringHelpers.EMPTY_STRING + record.getConceal());
		mStealthField.setText(TKStringHelpers.EMPTY_STRING + record.getStealth());
		mHearField.setText(TKStringHelpers.EMPTY_STRING + record.getHear());
		mLockPickField.setText(TKStringHelpers.EMPTY_STRING + record.getLockPick());
		mPocketPickField.setText(TKStringHelpers.EMPTY_STRING + record.getPocketPick());
		mClimbField.setText(TKStringHelpers.EMPTY_STRING + record.getClimb());
		mFindTrapField.setText(TKStringHelpers.EMPTY_STRING + record.getFindTrap());
		mRemoveTrapField.setText(TKStringHelpers.EMPTY_STRING + record.getRemoveTrap());

		mConcealLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getConcealLevelBonus());
		mStealthLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getStealthLevelBonus());
		mHearLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getHearLevelBonus());
		mLockPickLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getLockPickLevelBonus());
		mPocketPickLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getPocketPickLevelBonus());
		mClimbLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getClimbLevelBonus());
		mFindTrapLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getFindTrapLevelBonus());
		mRemoveTrapLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getRemoveTrapLevelBonus());
		mUnallocatedField.setText(TKStringHelpers.EMPTY_STRING + record.getUnallocated());
	}

	@Override
	public void focusGained(FocusEvent e) {
		// do nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();

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
			} else if (((JTextField) source).equals(mPocketPickLevelBonusField)) {
				record.setPocketPickLevelBonus(TKStringHelpers.getIntValue(mPocketPickLevelBonusField.getText(), record.getPocketPickLevelBonus()));
			} else if (((JTextField) source).equals(mClimbLevelBonusField)) {
				record.setClimbLevelBonus(TKStringHelpers.getIntValue(mClimbLevelBonusField.getText(), record.getClimbLevelBonus()));
			} else if (((JTextField) source).equals(mFindTrapLevelBonusField)) {
				record.setFindTrapLevelBonus(TKStringHelpers.getIntValue(mFindTrapLevelBonusField.getText(), record.getFindTrapLevelBonus()));
			} else if (((JTextField) source).equals(mRemoveTrapLevelBonusField)) {
				record.setRemoveTrapLevelBonus(TKStringHelpers.getIntValue(mRemoveTrapLevelBonusField.getText(), record.getRemoveTrapLevelBonus()));
			}
		}
		record.generateUnallocated();
		loadDisplay();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
