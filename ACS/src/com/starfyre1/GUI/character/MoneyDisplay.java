/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.MoneyRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MoneyDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	MONEY_TITLE		= "Money";		//$NON-NLS-1$

	private static final String	GOLD_LABEL		= "Gold";		//$NON-NLS-1$
	private static final String	SILVER_LABEL	= "Silver";		//$NON-NLS-1$
	private static final String	COPPER_LABEL	= "Copper";		//$NON-NLS-1$
	private static final String	GEMS_LABEL		= "Gems";		//$NON-NLS-1$
	private static final String	JEWELRY_LABEL	= "Jewelry";	//$NON-NLS-1$
	private static final String	OTHER_LABEL		= "Other";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JTextField					mGoldField;
	JTextField					mSilverField;
	JTextField					mCopperField;
	JTextArea					mGemsArea;
	JTextArea					mJewelryArea;
	JTextArea					mOtherArea;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MoneyDisplay(CharacterSheet owner) {
		super(owner, MONEY_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setBorder(new EmptyBorder(0, 5, 5, 10));
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel wrapper = new JPanel();

		JLabel goldLabel = new JLabel(GOLD_LABEL, SwingConstants.RIGHT);
		mGoldField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel silverLabel = new JLabel(SILVER_LABEL, SwingConstants.RIGHT);
		mSilverField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel copperLabel = new JLabel(COPPER_LABEL, SwingConstants.RIGHT);
		mCopperField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel gemsLabel = new JLabel(GEMS_LABEL, SwingConstants.RIGHT);
		mGemsArea = new JTextArea(3, 20);
		mGemsArea.setBorder(new LineBorder(Color.black));

		JLabel jewelryLabel = new JLabel(JEWELRY_LABEL, SwingConstants.RIGHT);
		mJewelryArea = new JTextArea(3, 20);
		mJewelryArea.setBorder(new LineBorder(Color.black));

		JLabel otherLabel = new JLabel(OTHER_LABEL, SwingConstants.RIGHT);
		mOtherArea = new JTextArea(3, 20);
		mOtherArea.setBorder(new LineBorder(Color.black));

		wrapper.add(goldLabel);
		wrapper.add(mGoldField);
		wrapper.add(silverLabel);
		wrapper.add(mSilverField);
		wrapper.add(copperLabel);
		wrapper.add(mCopperField);

		JPanel wrapper2 = new JPanel();
		wrapper2.setLayout(new BoxLayout(wrapper2, BoxLayout.Y_AXIS));

		wrapper2.add(gemsLabel);
		wrapper2.add(mGemsArea);
		wrapper2.add(jewelryLabel);
		wrapper2.add(mJewelryArea);
		wrapper2.add(otherLabel);
		wrapper2.add(mOtherArea);

		outerWrapper.add(wrapper);
		outerWrapper.add(wrapper2);

		outerWrapper.setPreferredSize(new Dimension(300, 100));
		return outerWrapper;

	}

	@Override
	public void loadDisplay() {
		MoneyRecord record = ((CharacterSheet) getOwner()).getMoneyRecord();

		if (record != null) {
			mGoldField.setText(TKStringHelpers.EMPTY_STRING + record.getGold());
			mSilverField.setText(TKStringHelpers.EMPTY_STRING + record.getSilver());
			mCopperField.setText(TKStringHelpers.EMPTY_STRING + record.getCopper());
			mGemsArea.setText(record.getGemsArea().toString());
			mJewelryArea.setText(TKStringHelpers.EMPTY_STRING + record.getJewelryArea());
			mOtherArea.setText(TKStringHelpers.EMPTY_STRING + record.getOtherArea());
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

		MoneyRecord record = ((CharacterSheet) getOwner()).getMoneyRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mGoldField)) {
				record.setGold(TKStringHelpers.getIntValue(mGoldField.getText(), record.getGoldOld()));
			} else if (((JTextField) source).equals(mSilverField)) {
				record.setSilver(TKStringHelpers.getIntValue(mSilverField.getText(), record.getSilverOld()));
			} else if (((JTextField) source).equals(mCopperField)) {
				record.setCopper(TKStringHelpers.getIntValue(mCopperField.getText(), record.getCopperOld()));
			}
		}
	}
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
