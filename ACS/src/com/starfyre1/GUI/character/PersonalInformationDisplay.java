/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.PersonalInformationRecord;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PersonalInformationDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String	PERSONAL_INFORMATION_TITLE	= "<html>Personal<br>Information</html>";	//$NON-NLS-1$

	static final String	HEIGHT_LABEL				= "Height";									//$NON-NLS-1$
	static final String	WEIGHT_LABEL				= "Weight";									//$NON-NLS-1$
	static final String	SEX_LABEL					= "Sex";									//$NON-NLS-1$
	static final String	HAIR_LABEL					= "Hair";									//$NON-NLS-1$
	static final String	EYES_LABEL					= "Eyes";									//$NON-NLS-1$
	static final String	AGE_LABEL					= "Age";									//$NON-NLS-1$
	static final String	SOCIAL_CLASS_LABEL			= "Social Class";							//$NON-NLS-1$
	static final String	CARRY_LABEL					= "Carry";									//$NON-NLS-1$
	static final String	ENCUMBRANCE_LABEL			= "Encumbrance";							//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField	mHeightField;
	private JTextField	mWeightField;
	private JTextField	mSexField;
	private JTextField	mHairField;
	private JTextField	mEyesField;
	private JTextField	mAgeField;
	private JTextField	mSocialClassField;
	private JTextField	mCarryField;
	private JTextField	mEncumbranceField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public PersonalInformationDisplay(CharacterSheet owner) {
		super(owner, PERSONAL_INFORMATION_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		JPanel outer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel wrapper = new JPanel(new GridLayout(10, 1, 5, 3));
		//		wrapper.setBorder(new EmptyBorder(0, 0, 0, 10));

		JLabel heightLabel = new JLabel(HEIGHT_LABEL, SwingConstants.RIGHT);
		mHeightField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mHeightField.setEditable(false);

		JLabel weightLabel = new JLabel(WEIGHT_LABEL, SwingConstants.RIGHT);
		mWeightField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mWeightField.setEditable(false);

		JLabel sexLabel = new JLabel(SEX_LABEL, SwingConstants.RIGHT);
		mSexField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this);

		JLabel hairLabel = new JLabel(HAIR_LABEL, SwingConstants.RIGHT);
		mHairField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this);

		JLabel eyesLabel = new JLabel(EYES_LABEL, SwingConstants.RIGHT);
		mEyesField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this);

		JLabel ageLabel = new JLabel(AGE_LABEL, SwingConstants.RIGHT);
		mAgeField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this);

		JLabel socialClassLabel = new JLabel(SOCIAL_CLASS_LABEL, SwingConstants.RIGHT);
		mSocialClassField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mSocialClassField.setEditable(false);

		JLabel carryLabel = new JLabel(CARRY_LABEL, SwingConstants.RIGHT);
		mCarryField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mCarryField.setEditable(false);

		JLabel encumbranceLabel = new JLabel(ENCUMBRANCE_LABEL, SwingConstants.RIGHT);
		mEncumbranceField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
		mEncumbranceField.setEditable(false);

		JPanel temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(heightLabel);
		temp.add(mHeightField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(weightLabel);
		temp.add(mWeightField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(sexLabel);
		temp.add(mSexField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(hairLabel);
		temp.add(mHairField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(eyesLabel);
		temp.add(mEyesField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(ageLabel);
		temp.add(mAgeField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(socialClassLabel);
		temp.add(mSocialClassField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(carryLabel);
		temp.add(mCarryField);
		wrapper.add(temp);

		temp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		temp.add(encumbranceLabel);
		temp.add(mEncumbranceField);
		wrapper.add(temp);

		outer.add(wrapper);
		return outer;
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

		PersonalInformationRecord record = ((CharacterSheet) getOwner()).getPersonalInformationRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (mSexField.equals(source)) {
				record.setSex(mSexField.getText());
			} else if (mHairField.equals(source)) {
				record.setHair(mHairField.getText());
			} else if (mEyesField.equals(source)) {
				record.setEyes(mEyesField.getText());
			} else if (mAgeField.equals(source)) {
				record.setAge(TKStringHelpers.getIntValue(mAgeField.getText(), record.getAge()));
			}
		}
	}

	//DW will need to implement this
	/*	Here's how Carry works:
		1)	Characters may carry up to your encumbrance with out penalty to they’re movement, speeds or combat bonus'.
		2)	Characters can carry up to 2X their encumbrance with these penalties:
			a)	Movement is cut by 25%.
			b)	All combat speeds, ASP, MSP, BSP & CSP are at -1.
			c)	All combat bonus', HB, MB, & BB are at -5%.
		3)	Up to 3X their encumbrance ----
			a)	Movement is cut by 50%.
			b)	Combat speeds are at -3.
			c)	Combat bonus' are at -15%.
			d)	Character Save vs. Surprise is at -10%, anyone they try to surprise will get a +10% added to their Save.
		4)	Up to 4X their Encumbrance---
			a)	Movement is cut by 75%.
			b)	Combat speeds are at -5.
			c)	Combat bonus' are at -25%.
			d)	Character has a -20% on their save Vs. surprise, those that they try to surprise get a +20% added to theirs.
			e)	Loses 1 stamina point per 10 min.of movement or 3 rounds of combat.
	*/
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public void loadDisplay() {
		PersonalInformationRecord record = ((CharacterSheet) getOwner()).getPersonalInformationRecord();

		mHeightField.setText(record.getHeight());
		mWeightField.setText(TKStringHelpers.EMPTY_STRING + record.getWeight());
		mSexField.setText(record.getSex());
		mHairField.setText(record.getHair());
		mEyesField.setText(record.getEyes());
		mAgeField.setText(TKStringHelpers.EMPTY_STRING + record.getAge());
		mSocialClassField.setText(record.getSocialClassTitle());
		String carryTooltip = record.getCarryTooltip();
		mCarryField.setText(TKStringHelpers.EMPTY_STRING + record.getCarry());
		mCarryField.setToolTipText(carryTooltip);
		mEncumbranceField.setText(TKStringHelpers.EMPTY_STRING + record.getEncumbrance());
		mEncumbranceField.setToolTipText(carryTooltip);
	}

}
