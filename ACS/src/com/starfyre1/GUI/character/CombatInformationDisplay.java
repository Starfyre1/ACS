/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.CombatInformationRecord;
import com.starfyre1.dataset.classes.elves.Sailor;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.dataset.common.SpellUser;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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

	private static final String	ATTACK_SPEED_LABEL			= "Attack";				//$NON-NLS-1$
	private static final String	MISSILE_SPEED_LABEL			= "Missile";			//$NON-NLS-1$
	private static final String	BOW_SPEED_LABEL				= "Bow";				//$NON-NLS-1$
	private static final String	CASTING_SPEED_LABEL			= "Casting";			//$NON-NLS-1$
	private static final String	MANA_LABEL					= "Mana";				//$NON-NLS-1$
	private static final String	FOCUS_LABEL					= "Focus";				//$NON-NLS-1$

	private static final String	BONUS_HEADER_LABEL			= "Bonus";				//$NON-NLS-1$
	private static final String	LEVEL_BONUS_HEADER_LABEL	= "Level Bonus";		//$NON-NLS-1$
	private static final String	LEVEL_BONUSES_HEADER_LABEL	= "Level Bonuses";		//$NON-NLS-1$
	private static final String	UNALLOCATED_HEADER_LABEL	= "Unallocated";		//$NON-NLS-1$
	private static final String	MAXIMUM_BONUS_HEADER_LABEL	= "Maximum";			//$NON-NLS-1$
	private static final String	NO_MAX						= "No Max";				//$NON-NLS-1$
	private static final String	SPEED_HEADER_LABEL			= "Speed";				//$NON-NLS-1$
	private static final String	CASTING_HEADER_LABEL		= "Casting";			//$NON-NLS-1$

	// May create speed label and bonus label and make them headers to column

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mHitBonusField;
	private JTextField			mHitLevelBonusField;
	private JTextField			mHitBonusMaxField;
	private JTextField			mAttackSpeedField;
	private JTextField			mMissileBonusField;
	private JTextField			mMissileSpeedField;
	private JTextField			mMissileBonusMaxField;
	private JTextField			mBowBonusField;
	private JTextField			mBowLevelBonusField;
	private JTextField			mBowSpeedField;
	private JTextField			mBowBonusMaxField;
	private JTextField			mDamageBonusField;
	private JTextField			mCastingSpeedLevelBonusField;
	private JTextField			mCastingSpeedField;
	private JTextField			mDefenseField;
	private JTextField			mManaField;
	private JTextField			mFreeField;
	private JTextField			mUnallocatedField;
	private JTextField			mFocusField;
	private JTextField			mMovementField;

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

		JPanel wrapper = new JPanel(new GridLayout(9, 7, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		JLabel hitBonusLabel = new JLabel(HIT_BONUS_LABEL, SwingConstants.RIGHT);
		mHitBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHitBonusField.setEditable(false);
		mHitLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		mHitBonusMaxField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mHitBonusMaxField.setEditable(false);
		JLabel attackSpeedLabel = new JLabel(ATTACK_SPEED_LABEL, SwingConstants.RIGHT);
		mAttackSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mAttackSpeedField.setEditable(false);

		JLabel missileLabel = new JLabel(MISSILE_BONUS_LABEL, SwingConstants.RIGHT);
		mMissileBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMissileBonusField.setEditable(false);
		mCastingSpeedLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		mMissileBonusMaxField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMissileBonusMaxField.setEditable(false);
		JLabel missileSpeedLabel = new JLabel(MISSILE_SPEED_LABEL, SwingConstants.RIGHT);
		mMissileSpeedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMissileSpeedField.setEditable(false);

		JLabel bowLabel = new JLabel(BOW_BONUS_LABEL, SwingConstants.RIGHT);
		mBowBonusField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBowBonusField.setEditable(false);
		mBowLevelBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, filter);
		mBowBonusMaxField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mBowBonusMaxField.setEditable(false);
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
		mFocusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this);
		mFocusField.setEditable(false);

		JLabel movementLabel = new JLabel(MOVEMENT_LABEL, SwingConstants.RIGHT);
		mMovementField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mMovementField.setEditable(false);
		mUnallocatedField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mUnallocatedField.setEditable(false);

		enableFields(false);

		wrapper.add(new JLabel());
		wrapper.add(new JLabel(BONUS_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel(LEVEL_BONUS_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel(MAXIMUM_BONUS_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel());
		wrapper.add(new JLabel(SPEED_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel(LEVEL_BONUS_HEADER_LABEL, SwingConstants.CENTER));

		wrapper.add(hitBonusLabel);
		wrapper.add(mHitBonusField);
		wrapper.add(mHitLevelBonusField);
		wrapper.add(mHitBonusMaxField);
		wrapper.add(attackSpeedLabel);
		wrapper.add(mAttackSpeedField);
		wrapper.add(new JLabel());

		wrapper.add(missileLabel);
		wrapper.add(mMissileBonusField);
		wrapper.add(new JLabel());
		wrapper.add(mMissileBonusMaxField);
		wrapper.add(missileSpeedLabel);
		wrapper.add(mMissileSpeedField);
		wrapper.add(new JLabel());

		wrapper.add(bowLabel);
		wrapper.add(mBowBonusField);
		wrapper.add(mBowLevelBonusField);
		wrapper.add(mBowBonusMaxField);
		wrapper.add(bowSpeedLabel);
		wrapper.add(mBowSpeedField);
		wrapper.add(new JLabel());

		wrapper.add(damageBonusLabel);
		wrapper.add(mDamageBonusField);
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(castingSpeedLabel);
		wrapper.add(mCastingSpeedField);
		wrapper.add(mCastingSpeedLevelBonusField);

		wrapper.add(defenseLabel);
		wrapper.add(mDefenseField);
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(new JLabel(CASTING_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel());

		wrapper.add(freeLabel);
		wrapper.add(mFreeField);
		wrapper.add(new JLabel(UNALLOCATED_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel());
		wrapper.add(manaLabel);
		wrapper.add(mManaField);
		wrapper.add(new JLabel());

		wrapper.add(movementLabel);
		wrapper.add(mMovementField);
		wrapper.add(new JLabel(LEVEL_BONUSES_HEADER_LABEL, SwingConstants.CENTER));
		wrapper.add(new JLabel());
		wrapper.add(focusLabel);
		wrapper.add(mFocusField);
		wrapper.add(new JLabel());

		wrapper.add(new JLabel());
		wrapper.add(new JLabel());
		wrapper.add(mUnallocatedField);
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

			if (record != null) {
				enableFields(true);

				mHitBonusField.setText(TKStringHelpers.EMPTY_STRING + (record.getHitBonus() + record.getHitLevelBonus()));
				mHitLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getHitLevelBonus());
				mHitBonusMaxField.setText(record.getHitBonusMax() >= 0 ? TKStringHelpers.EMPTY_STRING + record.getHitBonusMax() : NO_MAX);
				mAttackSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getAttackSpeed());

				mMissileBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getMissileBonus());
				mCastingSpeedLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getCastingLevelBonus());
				mMissileBonusMaxField.setText(record.getMissileBonusMax() >= 0 ? TKStringHelpers.EMPTY_STRING + record.getMissileBonusMax() : NO_MAX);
				mMissileSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getMissileSpeed());

				mBowBonusField.setText(TKStringHelpers.EMPTY_STRING + (record.getBowBonus() + record.getBowLevelBonus()));
				mBowLevelBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getBowLevelBonus());
				mBowBonusMaxField.setText(record.getBowBonusMax() >= 0 ? TKStringHelpers.EMPTY_STRING + record.getBowBonusMax() : NO_MAX);
				mBowSpeedField.setText(TKStringHelpers.EMPTY_STRING + record.getBowSpeed());

				mDamageBonusField.setText(TKStringHelpers.EMPTY_STRING + record.getDamageBonus());

				mCastingSpeedField.setText(TKStringHelpers.EMPTY_STRING + (record.getCastingSpeed() + record.getCastingLevelBonus() / 4));
				mManaField.setText(TKStringHelpers.EMPTY_STRING + record.getMana());
				mFocusField.setText(record.getFocus());

				mDefenseField.setText(TKStringHelpers.EMPTY_STRING + record.getDefense());
				mFreeField.setText(TKStringHelpers.EMPTY_STRING + record.getFree());

				mMovementField.setText(TKStringHelpers.EMPTY_STRING + record.getMovement());
				mUnallocatedField.setText(TKStringHelpers.EMPTY_STRING + record.getUnallocated());
			} else {
				enableFields(false);

				mHitBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mHitLevelBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mAttackSpeedField.setText(TKStringHelpers.EMPTY_STRING);

				mMissileBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mCastingSpeedLevelBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mMissileSpeedField.setText(TKStringHelpers.EMPTY_STRING);

				mBowBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mBowLevelBonusField.setText(TKStringHelpers.EMPTY_STRING);
				mBowSpeedField.setText(TKStringHelpers.EMPTY_STRING);

				mDamageBonusField.setText(TKStringHelpers.EMPTY_STRING);

				mCastingSpeedField.setText(TKStringHelpers.EMPTY_STRING);
				mManaField.setText(TKStringHelpers.EMPTY_STRING);
				mFocusField.setText(TKStringHelpers.EMPTY_STRING);

				mDefenseField.setText(TKStringHelpers.EMPTY_STRING);
				mFreeField.setText(TKStringHelpers.EMPTY_STRING);

				mMovementField.setText(TKStringHelpers.EMPTY_STRING);
				mUnallocatedField.setText(TKStringHelpers.EMPTY_STRING);
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
		CharacterSheet sheet = (CharacterSheet) getOwner();
		if (!sheet.isLoadingData()) {
			Object source = e.getDocument().getProperty(TKComponentHelpers.DOCUMENT_OWNER);

			CombatInformationRecord record = ((CharacterSheet) getOwner()).getCombatInformationRecord();
			if (record == null) {
				return;
			}

			if (source instanceof JTextField) {
				if (((JTextField) source).equals(mCastingSpeedLevelBonusField)) {
					String value = mCastingSpeedLevelBonusField.getText();
					if (value.isEmpty()) {
						record.setCastingLevelBonus(0);
					} else {
						record.setCastingLevelBonus(TKStringHelpers.getIntValue(value, record.getCastingLevelBonus()));
					}
				} else if (((JTextField) source).equals(mHitLevelBonusField)) {
					String value = mHitLevelBonusField.getText();
					if (value.isEmpty()) {
						record.setHitLevelBonus(0);
					} else {
						record.setHitLevelBonus(TKStringHelpers.getIntValue(value, record.getHitLevelBonus()));
					}
					record.generateDefenseAndFreeAttack();
				} else if (((JTextField) source).equals(mBowLevelBonusField)) {
					String value = mBowLevelBonusField.getText();
					if (value.isEmpty()) {
						record.setBowLevelBonus(0);
					} else {
						record.setBowLevelBonus(TKStringHelpers.getIntValue(value, record.getBowLevelBonus()));
					}
				} else if (((JTextField) source).equals(mFocusField)) {
					record.setFocus(mFocusField.getText());
				}
			}
			record.generateUnallocated();
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
	}

	private boolean isUsable() {
		BaseClass charClass = ((CharacterSheet) getOwner()).getHeaderRecord().getCharacterClass();
		if (charClass instanceof SpellUser || charClass instanceof Sailor) {
			return true;
		}
		return false;
	}

	public void updateToolTips() {
		BaseClass charClass = ((CharacterSheet) getOwner()).getHeaderRecord().getCharacterClass();
		mFocusField.setToolTipText(charClass.getFocusToolTip());
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public void enableFields(boolean enabled) {
		mCastingSpeedLevelBonusField.setEditable(enabled && isUsable());
		mHitLevelBonusField.setEditable(enabled);
		mBowLevelBonusField.setEditable(enabled);
		mFocusField.setEditable(enabled);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
