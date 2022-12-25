/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;

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

public class AttributesDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	ATTRIBUTES_TITLE	= "Attributes";	//$NON-NLS-1$

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
		JPanel wrapper = new JPanel(new GridLayout(9, 3, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 0, 5, 10));

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JLabel strLabel = new JLabel(AttributesRecord.STRENGTH, SwingConstants.RIGHT);
		strLabel.setToolTipText(AttributesRecord.STRENGTH_DESCRIPTION);
		mStrField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mStrField.setEditable(false);
		mStrField.setFocusable(false);
		mModStrField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel conLabel = new JLabel(AttributesRecord.CONSTITUTION, SwingConstants.RIGHT);
		conLabel.setToolTipText(AttributesRecord.CONSTITUTION_DESCRIPTION);
		mConField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mConField.setEditable(false);
		mConField.setFocusable(false);
		mModConField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel intLabel = new JLabel(AttributesRecord.INTELLIGENCE, SwingConstants.RIGHT);
		intLabel.setToolTipText(AttributesRecord.INTELLIGENCE_DESCRIPTION);
		mIntField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mIntField.setEditable(false);
		mIntField.setFocusable(false);
		mModIntField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel wisLabel = new JLabel(AttributesRecord.WISDOM, SwingConstants.RIGHT);
		wisLabel.setToolTipText(AttributesRecord.WISDOM_DESCRIPTION);
		mWisField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mWisField.setEditable(false);
		mWisField.setFocusable(false);
		mModWisField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel dexLabel = new JLabel(AttributesRecord.DEXTERITY, SwingConstants.RIGHT);
		dexLabel.setToolTipText(AttributesRecord.DEXTERITY_DESCRIPTION);
		mDexField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mDexField.setEditable(false);
		mDexField.setFocusable(false);
		mModDexField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel bsLabel = new JLabel(AttributesRecord.BOW_SKILL, SwingConstants.RIGHT);
		bsLabel.setToolTipText(AttributesRecord.BOW_SKILL_DESCRIPTION);
		mBSField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mBSField.setEditable(false);
		mBSField.setFocusable(false);
		mModBSField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel chaLabel = new JLabel(AttributesRecord.CHARISMA, SwingConstants.RIGHT);
		chaLabel.setToolTipText(AttributesRecord.CHARISMA_DESCRIPTION);
		mChaField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mChaField.setEditable(false);
		mChaField.setFocusable(false);
		mModChaField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel paLabel = new JLabel(AttributesRecord.PERSONAL_APPEARANCE, SwingConstants.RIGHT);
		paLabel.setToolTipText(AttributesRecord.PERSONAL_APPEARANCE_DESCRIPTION);
		mPAField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mPAField.setEditable(false);
		mPAField.setFocusable(false);
		mModPAField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel wpLabel = new JLabel(AttributesRecord.WILLPOWER, SwingConstants.RIGHT);
		wpLabel.setToolTipText(AttributesRecord.WILLPOWER_DESCRIPTION);
		mWPField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mWPField.setEditable(false);
		mWPField.setFocusable(false);
		mModWPField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		enableFields(false);

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
				record.setModifiedStat(2, TKStringHelpers.getIntValue(mModIntField.getText(), record.getModifiedStatOld(2)));
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
			if (record.isGenerateOwnStats()) {
				((CharacterSheet) getOwner()).enableCreateButton(areAllAttributeFieldsSet());
			}

			// DW fix - consolidate updates
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						((CharacterSheet) getOwner()).updateAttributeRecords(false);
					} catch (Exception ex) {
						System.err.println(ex);
					}
				}
			});
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public void enableFields(boolean enabled) {
		mModStrField.setEditable(enabled);
		mModConField.setEditable(enabled);
		mModIntField.setEditable(enabled);
		mModWisField.setEditable(enabled);
		mModDexField.setEditable(enabled);
		mModBSField.setEditable(enabled);
		mModChaField.setEditable(enabled);
		mModPAField.setEditable(enabled);
		mModWPField.setEditable(enabled);
	}

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
	public void loadDisplay() {
		AttributesRecord stats = ((CharacterSheet) getOwner()).getAttributesRecord();

		if (stats != null) {

			enableFields(true);

			mStrField.setText(String.valueOf(stats.getStat(0)));
			mConField.setText(String.valueOf(stats.getStat(1)));
			mIntField.setText(String.valueOf(stats.getStat(2)));
			mWisField.setText(String.valueOf(stats.getStat(3)));
			mDexField.setText(String.valueOf(stats.getStat(4)));
			mBSField.setText(String.valueOf(stats.getStat(5)));
			mChaField.setText(String.valueOf(stats.getStat(6)));
			mPAField.setText(String.valueOf(stats.getStat(7)));
			mWPField.setText(String.valueOf(stats.getStat(8)));

			mModStrField.setText(String.valueOf(stats.getModifiedStat(0)));
			mModConField.setText(String.valueOf(stats.getModifiedStat(1)));
			mModIntField.setText(String.valueOf(stats.getModifiedStat(2)));
			mModWisField.setText(String.valueOf(stats.getModifiedStat(3)));
			mModDexField.setText(String.valueOf(stats.getModifiedStat(4)));
			mModBSField.setText(String.valueOf(stats.getModifiedStat(5)));
			mModChaField.setText(String.valueOf(stats.getModifiedStat(6)));
			mModPAField.setText(String.valueOf(stats.getModifiedStat(7)));
			mModWPField.setText(String.valueOf(stats.getModifiedStat(8)));
		} else {
			enableFields(false);

			mStrField.setText(TKStringHelpers.EMPTY_STRING);
			mConField.setText(TKStringHelpers.EMPTY_STRING);
			mIntField.setText(TKStringHelpers.EMPTY_STRING);
			mWisField.setText(TKStringHelpers.EMPTY_STRING);
			mDexField.setText(TKStringHelpers.EMPTY_STRING);
			mBSField.setText(TKStringHelpers.EMPTY_STRING);
			mChaField.setText(TKStringHelpers.EMPTY_STRING);
			mPAField.setText(TKStringHelpers.EMPTY_STRING);
			mWPField.setText(TKStringHelpers.EMPTY_STRING);

			mModStrField.setText(TKStringHelpers.EMPTY_STRING);
			mModConField.setText(TKStringHelpers.EMPTY_STRING);
			mModIntField.setText(TKStringHelpers.EMPTY_STRING);
			mModWisField.setText(TKStringHelpers.EMPTY_STRING);
			mModDexField.setText(TKStringHelpers.EMPTY_STRING);
			mModBSField.setText(TKStringHelpers.EMPTY_STRING);
			mModChaField.setText(TKStringHelpers.EMPTY_STRING);
			mModPAField.setText(TKStringHelpers.EMPTY_STRING);
			mModWPField.setText(TKStringHelpers.EMPTY_STRING);
		}
	}
}
