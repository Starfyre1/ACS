/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.CombatInformationRecord;
import com.starfyre1.dataset.common.BaseClass;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CombatInformationDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	COMBAT_INFORMATION_TITLE	= "Combat Information";	//$NON-NLS-1$

	private static final String	HIT_BONUS_LABEL				= "Hit";				//$NON-NLS-1$
	private static final String	MISSILE_BONUS_LABEL			= "Missile";			//$NON-NLS-1$
	private static final String	BOW_BONUS_LABEL				= "Bow";				//$NON-NLS-1$
	private static final String	DAMAGE_BONUS_LABEL			= "Damage";				//$NON-NLS-1$
	private static final String	DEFENSE_LABEL				= "Defense";			//$NON-NLS-1$
	private static final String	FREE_ATTACK_LABEL			= "Free Attack";		//$NON-NLS-1$
	private static final String	MOVEMENT_LABEL				= "Movement";			//$NON-NLS-1$
	private static final String	MORALS_LABEL				= "Morals";				//$NON-NLS-1$
	private static final String	ATTACK_SPEED_LABEL			= "Attack";				//$NON-NLS-1$
	private static final String	MISSILE_SPEED_LABEL			= "Missile";			//$NON-NLS-1$
	private static final String	BOW_SPEED_LABEL				= "Bow";				//$NON-NLS-1$
	private static final String	CASTING_SPEED_LABEL			= "Casting";			//$NON-NLS-1$
	private static final String	MANA_LABEL					= "Mana";				//$NON-NLS-1$
	private static final String	FOCUS_LABEL					= "Focus";				//$NON-NLS-1$
	private static final String	BONUS_LABEL					= "Bonus";				//$NON-NLS-1$
	private static final String	LEVEL_BONUS_LABEL			= "Level Bonus";		//$NON-NLS-1$
	private static final String	UNALLOCATED_LABEL			= "Unallocated";		//$NON-NLS-1$
	private static final String	SPEED_LABEL					= "Speed";				//$NON-NLS-1$

	// May create speed label and bonus label and make them headers to column

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mHitBonusField;
	private JTextField			mHitLevelBonusField;
	private JTextField			mAttackSpeedField;
	private JTextField			mMissileBonusField;
	private JTextField			mMissileSpeedField;
	private JTextField			mBowBonusField;
	private JTextField			mBowLevelBonusField;
	private JTextField			mBowSpeedField;
	private JTextField			mDamageBonusField;
	private JTextField			mCastingSpeedLevelField;
	private JTextField			mCastingSpeedField;
	private JTextField			mDefenseField;
	private JTextField			mManaField;
	private JTextField			mFreeField;
	private JTextField			mUnallocatedField;
	private JTextField			mFocusField;
	private JTextField			mMovementField;
	private JTextField			mMoralsField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public CombatInformationDisplay(CharacterSheet owner) {
		super(owner, COMBAT_INFORMATION_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel wrapper = new JPanel(new GridLayout(10, 5, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		JLabel bonusLabel = new JLabel(BONUS_LABEL, SwingConstants.CENTER);
		JLabel maxLabel = new JLabel(LEVEL_BONUS_LABEL, SwingConstants.CENTER);
		JLabel speedLabel = new JLabel(SPEED_LABEL, SwingConstants.CENTER);

		JLabel hitBonusLabel = new JLabel(HIT_BONUS_LABEL, SwingConstants.RIGHT);
		mHitBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHitBonusField.setEditable(false);
		mHitLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		JLabel attackSpeedLabel = new JLabel(ATTACK_SPEED_LABEL, SwingConstants.RIGHT);
		mAttackSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mAttackSpeedField.setEditable(false);

		JLabel missileLabel = new JLabel(MISSILE_BONUS_LABEL, SwingConstants.RIGHT);
		mMissileBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMissileBonusField.setEditable(false);
		mCastingSpeedLevelField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		JLabel missileSpeedLabel = new JLabel(MISSILE_SPEED_LABEL, SwingConstants.RIGHT);
		mMissileSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMissileSpeedField.setEditable(false);

		JLabel bowLabel = new JLabel(BOW_BONUS_LABEL, SwingConstants.RIGHT);
		mBowBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBowBonusField.setEditable(false);
		mBowLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		JLabel bowSpeedLabel = new JLabel(BOW_SPEED_LABEL, SwingConstants.RIGHT);
		mBowSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBowSpeedField.setEditable(false);

		JLabel damageBonusLabel = new JLabel(DAMAGE_BONUS_LABEL, SwingConstants.RIGHT);
		mDamageBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDamageBonusField.setEditable(false);
		JLabel castingSpeedLabel = new JLabel(CASTING_SPEED_LABEL, SwingConstants.RIGHT);
		mCastingSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mCastingSpeedField.setEditable(false);

		JLabel defenseLabel = new JLabel(DEFENSE_LABEL, SwingConstants.RIGHT);
		mDefenseField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mDefenseField.setEditable(false);
		JLabel manaLabel = new JLabel(MANA_LABEL, SwingConstants.RIGHT);
		mManaField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mManaField.setEditable(false);

		JLabel freeLabel = new JLabel(FREE_ATTACK_LABEL, SwingConstants.RIGHT);
		mFreeField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mFreeField.setEditable(false);
		JLabel focusLabel = new JLabel(FOCUS_LABEL, SwingConstants.RIGHT);
		mFocusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mFocusField.setEditable(false);

		JLabel movementLabel = new JLabel(MOVEMENT_LABEL, SwingConstants.RIGHT);
		mMovementField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMovementField.setEditable(false);
		JLabel unallocatedLabel = new JLabel(UNALLOCATED_LABEL, SwingConstants.CENTER);
		mUnallocatedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mUnallocatedField.setEditable(false);
		JLabel moralsLabel = new JLabel(MORALS_LABEL, SwingConstants.RIGHT);
		mMoralsField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMoralsField.setEditable(false);

		wrapper.add(new JLabel());
		wrapper.add(bonusLabel);
		wrapper.add(maxLabel);
		wrapper.add(new JLabel());
		wrapper.add(speedLabel);

		wrapper.add(hitBonusLabel);
		wrapper.add(mHitBonusField);
		wrapper.add(mHitLevelBonusField);
		wrapper.add(attackSpeedLabel);
		wrapper.add(mAttackSpeedField);

		wrapper.add(missileLabel);
		wrapper.add(mMissileBonusField);
		wrapper.add(new JLabel());
		wrapper.add(missileSpeedLabel);
		wrapper.add(mMissileSpeedField);

		wrapper.add(bowLabel);
		wrapper.add(mBowBonusField);
		wrapper.add(mBowLevelBonusField);
		wrapper.add(bowSpeedLabel);
		wrapper.add(mBowSpeedField);

		wrapper.add(damageBonusLabel);
		wrapper.add(mDamageBonusField);
		wrapper.add(mCastingSpeedLevelField);
		wrapper.add(castingSpeedLabel);
		wrapper.add(mCastingSpeedField);

		wrapper.add(defenseLabel);
		wrapper.add(mDefenseField);
		wrapper.add(new JLabel());
		wrapper.add(manaLabel);
		wrapper.add(mManaField);

		wrapper.add(freeLabel);
		wrapper.add(mFreeField);
		wrapper.add(unallocatedLabel);
		wrapper.add(focusLabel);
		wrapper.add(mFocusField);

		wrapper.add(movementLabel);
		wrapper.add(mMovementField);
		wrapper.add(mUnallocatedField);
		wrapper.add(moralsLabel);
		wrapper.add(mMoralsField);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());

		return wrapper;
	}

	public boolean start = false;

	@Override
	public void loadDisplay() {

		CharacterSheet sheet = (CharacterSheet) getOwner();
		if (!sheet.isLoadingData()) {
			sheet.setLoadingData(true);
			CombatInformationRecord record = sheet.getCombatInformationRecord();
			BaseClass characterClass = sheet.getHeaderRecord().getCharacterClass();

			if (!(record == null || characterClass == null)) {
				mHitBonusField.setText(TKStringHelpers.EMPTY_STRING + (record.getHitBonus() + record.getHitLevelBonus()));
				mHitLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getHitLevelBonus());
				mAttackSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getAttackSpeed());

				mMissileBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getMissileBonus());
				mCastingSpeedLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getCastingLevelBonus());
				mMissileSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getMissileSpeed());

				mBowBonusField.setText(TKStringHelpers.EMPTY_STRING + (record.getBowBonus() + record.getBowLevelBonus()));
				mBowLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getBowLevelBonus());
				mBowSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getBowSpeed());

				mDamageBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getDamageBonus());

				mCastingSpeedField.setText(TKStringHelpers.EMPTY_STRING + (record.getCastingSpeed() + record.getCastingLevelBonus() / 4));
				mManaField.setText(TKStringHelpers.EMPTY_STRING + record.getMana());
				mFocusField.setText(TKStringHelpers.EMPTY_STRING + record.getFocus());

				mDefenseField.setText(TKStringHelpers.EMPTY_STRING + record.getDefense());
				mFreeField.setText(TKStringHelpers.EMPTY_STRING + record.getFree());

				mMovementField.setText(TKStringHelpers.EMPTY_STRING + record.getMovement());
				mUnallocatedField.setText(TKStringHelpers.EMPTY_STRING + record.getUnallocated());

				mMoralsField.setText(TKStringHelpers.EMPTY_STRING + record.getMorals());
			}
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
		System.out.println(source);

		CombatInformationRecord record = ((CharacterSheet) getOwner()).getCombatInformationRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mCastingSpeedLevelField)) {
				record.setCastingLevelBonus(TKStringHelpers.getIntValue(mCastingSpeedLevelField.getText(), record.getCastingLevelBonus()));
			} else if (((JTextField) source).equals(mHitLevelBonusField)) {
				record.setHitLevelBonus(TKStringHelpers.getIntValue(mHitLevelBonusField.getText(), record.getHitLevelBonus()));
				record.updateDefenseRecord();
			} else if (((JTextField) source).equals(mBowLevelBonusField)) {
				record.setBowLevelBonus(TKStringHelpers.getIntValue(mBowLevelBonusField.getText(), record.getBowLevelBonus()));
			}
		}
		record.generateUnallocated();
		if (!((CharacterSheet) getOwner()).isLoadingData()) {
			loadDisplay();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
