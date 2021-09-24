/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AttributesDisplay extends TKTitledDisplay implements FocusListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	ATTRIBUTES_TITLE			= "Attributes";							//$NON-NLS-1$

	private static final String	STRENGTH_TOOLTIP			= AttributesRecord.STRENGTH;
	private static final String	CONSTITUTION_TOOLTIP		= AttributesRecord.CONSTITUTION;
	private static final String	INTELLIGENCE_TOOLTIP		= AttributesRecord.INTELLIGENCE;
	private static final String	WISDOM_TOOLTIP				= AttributesRecord.WISDOM;
	private static final String	DEXTERITY_TOOLTIP			= AttributesRecord.DEXTERITY;
	private static final String	BOW_SKILL_TOOLTIP			= AttributesRecord.BOW_SKILL;
	private static final String	CHARISMA_TOOLTIP			= AttributesRecord.CHARISMA;
	private static final String	PERSONAL_APPERANCE_TOOLTIP	= AttributesRecord.PERSONAL_APPEARANCE;
	private static final String	WILLPOWER_TOOLTIP			= AttributesRecord.WILLPOWER;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mStrField;
	private JTextField			mConField;
	private JTextField			mIntField;
	private JTextField			mWisField;
	private JTextField			mDexField;
	private JTextField			mBSField;
	private JTextField			mChaField;
	private JTextField			mPAField;
	private JTextField			mWPField;

	private JTextField			mModStrField;
	private JTextField			mModConField;
	private JTextField			mModIntField;
	private JTextField			mModWisField;
	private JTextField			mModDexField;
	private JTextField			mModBSField;
	private JTextField			mModChaField;
	private JTextField			mModPAField;
	private JTextField			mModWPField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public AttributesDisplay(CharacterSheet owner) {
		super(owner, ATTRIBUTES_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		JPanel wrapper = new JPanel(new GridLayout(10, 3, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JLabel strLabel = new JLabel(AttributesRecord.STRENGTH, SwingConstants.RIGHT);
		strLabel.setToolTipText(STRENGTH_TOOLTIP);
		mStrField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mStrField.setEditable(false);
		mModStrField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel conLabel = new JLabel(AttributesRecord.CONSTITUTION, SwingConstants.RIGHT);
		conLabel.setToolTipText(CONSTITUTION_TOOLTIP);
		mConField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mConField.setEditable(false);
		mModConField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel intLabel = new JLabel(AttributesRecord.INTELLIGENCE, SwingConstants.RIGHT);
		intLabel.setToolTipText(INTELLIGENCE_TOOLTIP);
		mIntField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mIntField.setEditable(false);
		mModIntField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel wisLabel = new JLabel(AttributesRecord.WISDOM, SwingConstants.RIGHT);
		wisLabel.setToolTipText(WISDOM_TOOLTIP);
		mWisField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mWisField.setEditable(false);
		mModWisField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel dexLabel = new JLabel(AttributesRecord.DEXTERITY, SwingConstants.RIGHT);
		dexLabel.setToolTipText(DEXTERITY_TOOLTIP);
		mDexField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mDexField.setEditable(false);
		mModDexField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel bsLabel = new JLabel(AttributesRecord.BOW_SKILL, SwingConstants.RIGHT);
		bsLabel.setToolTipText(BOW_SKILL_TOOLTIP);
		mBSField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mBSField.setEditable(false);
		mModBSField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel chaLabel = new JLabel(AttributesRecord.CHARISMA, SwingConstants.RIGHT);
		chaLabel.setToolTipText(CHARISMA_TOOLTIP);
		mChaField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mChaField.setEditable(false);
		mModChaField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel paLabel = new JLabel(AttributesRecord.PERSONAL_APPEARANCE, SwingConstants.RIGHT);
		paLabel.setToolTipText(PERSONAL_APPERANCE_TOOLTIP);
		mPAField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mPAField.setEditable(false);
		mModPAField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel wpLabel = new JLabel(AttributesRecord.WILLPOWER, SwingConstants.RIGHT);
		wpLabel.setToolTipText(WILLPOWER_TOOLTIP);
		mWPField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mWPField.setEditable(false);
		mModWPField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		wrapper.add(strLabel);
		wrapper.add(mStrField);
		wrapper.add(mModStrField);
		wrapper.add(conLabel);
		wrapper.add(mConField);
		wrapper.add(mModConField);
		wrapper.add(intLabel);
		wrapper.add(mIntField);
		wrapper.add(mModIntField);
		wrapper.add(wisLabel);
		wrapper.add(mWisField);
		wrapper.add(mModWisField);
		wrapper.add(dexLabel);
		wrapper.add(mDexField);
		wrapper.add(mModDexField);
		wrapper.add(bsLabel);
		wrapper.add(mBSField);
		wrapper.add(mModBSField);
		wrapper.add(chaLabel);
		wrapper.add(mChaField);
		wrapper.add(mModChaField);
		wrapper.add(paLabel);
		wrapper.add(mPAField);
		wrapper.add(mModPAField);
		wrapper.add(wpLabel);
		wrapper.add(mWPField);
		wrapper.add(mModWPField);

		return wrapper;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// do nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();

		AttributesRecord record = ((CharacterSheet) getOwner()).getAttributesRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mModStrField)) {
				record.setModifiedStat(0, TKStringHelpers.getIntValue(mModStrField.getText(), record.getModifiedStatOld(0)));
			} else if (((JTextField) source).equals(mModConField)) {
				record.setModifiedStat(1, TKStringHelpers.getIntValue(mModConField.getText(), record.getModifiedStatOld(1)));
			} else if (((JTextField) source).equals(mModIntField)) {
				record.setModifiedStat(2, TKStringHelpers.getIntValue(mModStrField.getText(), record.getModifiedStatOld(2)));
			} else if (((JTextField) source).equals(mModWisField)) {
				record.setModifiedStat(3, TKStringHelpers.getIntValue(mModWisField.getText(), record.getModifiedStatOld(3)));
			} else if (((JTextField) source).equals(mModDexField)) {
				record.setModifiedStat(4, TKStringHelpers.getIntValue(mModDexField.getText(), record.getModifiedStatOld(4)));
			} else if (((JTextField) source).equals(mModBSField)) {
				record.setModifiedStat(5, TKStringHelpers.getIntValue(mModBSField.getText(), record.getModifiedStatOld(5)));
			} else if (((JTextField) source).equals(mModChaField)) {
				record.setModifiedStat(6, TKStringHelpers.getIntValue(mModChaField.getText(), record.getModifiedStatOld(6)));
			} else if (((JTextField) source).equals(mModPAField)) {
				record.setModifiedStat(7, TKStringHelpers.getIntValue(mModPAField.getText(), record.getModifiedStatOld(7)));
			} else if (((JTextField) source).equals(mModWPField)) {
				record.setModifiedStat(8, TKStringHelpers.getIntValue(mModWPField.getText(), record.getModifiedStatOld(8)));
			}
			((CharacterSheet) getOwner()).enableCreateButton(areAllAttributeFieldsSet());
		}

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private boolean areAllAttributeFieldsSet() {
		if (mModStrField.getText().isEmpty() || //
						mModConField.getText().isEmpty() || //
						mModIntField.getText().isEmpty() || //
						mModWisField.getText().isEmpty() || //
						mModDexField.getText().isEmpty() || //
						mModBSField.getText().isEmpty() || //
						mModChaField.getText().isEmpty() || //
						mModPAField.getText().isEmpty() || //
						mModWPField.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	protected void loadDisplay() {
		AttributesRecord stats = ((CharacterSheet) getOwner()).getAttributesRecord();

		mStrField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(0));
		mConField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(1));
		mIntField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(2));
		mWisField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(3));
		mDexField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(4));
		mBSField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(5));
		mChaField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(6));
		mPAField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(7));
		mWPField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(8));

		mModStrField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(0));
		mModConField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(1));
		mModIntField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(2));
		mModWisField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(3));
		mModDexField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(4));
		mModBSField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(5));
		mModChaField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(6));
		mModPAField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(7));
		mModWPField.setText(TKStringHelpers.EMPTY_STRING + stats.getStat(8));
	}
}
