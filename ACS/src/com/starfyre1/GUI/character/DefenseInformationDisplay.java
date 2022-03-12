/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.LevelListener;
import com.starfyre1.interfaces.Savable;

import java.awt.Component;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DefenseInformationDisplay extends TKTitledDisplay implements Savable, LevelListener, DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	/*
		There are many ways to figure your characters Base Armor Rating,
		the two that have worked best for me in my games are as follows:
	
		1)	Start your character at a base of 50%, then add the Protection
			Percentage from the armor you are wearing, this will give you
			your Armor Rating.
	
		2)	Start your character with (2 X Dex) + 50% = Base Armor Rating,
			then add the Protection Percentage from the armor you are
			wearing, this will give you your Armor Rating.  This rule heavily
			favors those characters with high Dexterity, so I've added an
			optional rule for the Game Master.  Those players that choose
			to use this option should not be allowed to carry more than
			1/2 their allotted carry capacity, at the time of combat.
			(Remember this is a Optional rule, this way they can't take
			Total advantage of a 18 Dexterity, and wear Field Plate!)
	
	*/

	public static final String	FILE_SECTTION_START_KEY		= "DEFENSE_INFORMATION_SECTTION_START";	//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY		= "DEFENSE_INFORMATION_SECTTION_END";	//$NON-NLS-1$

	private static final String	STAMINA_LEFT_KEY			= "STAMINA_LEFT_KEY";					//$NON-NLS-1$
	private static final String	HIT_POINTS_LEFT_KEY			= "HIT_POINTS_LEFT_KEY";				//$NON-NLS-1$
	private static final String	DEATH_KEY					= "DEATH_KEY";							//$NON-NLS-1$

	private static final String	DEFENSE_INFORMATION_TITLE	= "Defense Information";				//$NON-NLS-1$
	private static final String	ABSORBS_TITLE				= "Absorbs";							//$NON-NLS-1$

	//DW need to add "Max allowable for spells"
	private static final String	WITH_ARMOR_LABEL			= "With Armor";							//$NON-NLS-1$
	private static final String	WITHOUT_ARMOR_LABEL			= "Without Armor";						//$NON-NLS-1$
	private static final String	ARMOR_RATIMG_LABEL			= "Armor Rating";						//$NON-NLS-1$
	private static final String	HEAD_LABEL					= "Head";								//$NON-NLS-1$
	private static final String	TOP_LABEL					= "Top";								//$NON-NLS-1$
	private static final String	SIDE_LABEL					= "Side";								//$NON-NLS-1$
	private static final String	FACE_LABEL					= "Face";								//$NON-NLS-1$
	private static final String	NECK_LABEL					= "Neck";								//$NON-NLS-1$
	private static final String	TORSO_LABEL					= "Torso";								//$NON-NLS-1$
	private static final String	ARMS_LABEL					= "Arms";								//$NON-NLS-1$
	private static final String	HANDS_LABEL					= "Hands";								//$NON-NLS-1$
	private static final String	LEGS_LABEL					= "Legs";								//$NON-NLS-1$
	private static final String	FEET_LABEL					= "Feet";								//$NON-NLS-1$
	private static final String	STAMINA_LABEL				= "Stamina";							//$NON-NLS-1$
	private static final String	HIT_POINTS_LABEL			= "Hit Points";							//$NON-NLS-1$
	private static final String	FULL_LABEL					= "Full";								//$NON-NLS-1$
	private static final String	LEFT_LABEL					= "Left";								//$NON-NLS-1$
	private static final String	DEATH_LABEL					= "Death";								//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mArmorField;
	private JTextField			mArmorField2;

	private int[]				mArmorCoverage;
	private int[]				mArmorBonusCoverage;
	private int[]				mArmorMissileCoverage;

	private JTextField			mHeadTopField;
	private JTextField			mHeadSideField;
	private JTextField			mHeadFaceField;
	private JTextField			mNeckField;
	private JTextField			mTorsoField;
	private JTextField			mArmsField;
	private JTextField			mHandsField;
	private JTextField			mLegsField;
	private JTextField			mFeetField;

	private JTextField			mStaminaFullField;
	private JTextField			mHitPointsFullField;
	private JTextField			mStaminaLeftField;
	private JTextField			mHitPointsLeftField;
	private JTextField			mDeathField;

	private int					mStaminaLeft				= 0;
	private int					mHitPointsLeft				= 0;
	private int					mDeath						= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public DefenseInformationDisplay(CharacterSheet owner) {
		super(owner, DEFENSE_INFORMATION_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public void updateRecord() {
		updateValues();
	}

	private void updateValues() {
		loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setBorder(new EmptyBorder(0, 0, 5, 10));
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		// DW fix up Armor so information is clearer
		JLabel armorLabel = new JLabel(ARMOR_RATIMG_LABEL, SwingConstants.RIGHT);
		JLabel withArmorLabel = new JLabel(WITH_ARMOR_LABEL, SwingConstants.CENTER);
		JLabel withoutArmorLabel = new JLabel(WITHOUT_ARMOR_LABEL, SwingConstants.CENTER);
		mArmorField = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mArmorField.setEditable(false);
		mArmorField2 = new JTextField(CharacterSheet.FIELD_SIZE_SMALL);
		mArmorField2.setEditable(false);

		JLabel headLabel = new JLabel(HEAD_LABEL, SwingConstants.RIGHT);

		JLabel topLabel = new JLabel(TOP_LABEL, SwingConstants.RIGHT);
		mHeadTopField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHeadTopField.setEditable(false);

		JLabel sideLabel = new JLabel(SIDE_LABEL, SwingConstants.RIGHT);
		mHeadSideField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHeadSideField.setEditable(false);

		JLabel faceLabel = new JLabel(FACE_LABEL, SwingConstants.RIGHT);
		mHeadFaceField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHeadFaceField.setEditable(false);

		JLabel neckLabel = new JLabel(NECK_LABEL, SwingConstants.RIGHT);
		mNeckField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mNeckField.setEditable(false);

		JLabel torsoLabel = new JLabel(TORSO_LABEL, SwingConstants.RIGHT);
		mTorsoField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mTorsoField.setEditable(false);

		JLabel armsLabel = new JLabel(ARMS_LABEL, SwingConstants.RIGHT);
		mArmsField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mArmsField.setEditable(false);

		JLabel handsLabel = new JLabel(HANDS_LABEL, SwingConstants.RIGHT);
		mHandsField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHandsField.setEditable(false);

		JLabel legsLabel = new JLabel(LEGS_LABEL, SwingConstants.RIGHT);
		mLegsField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mLegsField.setEditable(false);

		JLabel feetLabel = new JLabel(FEET_LABEL, SwingConstants.RIGHT);
		mFeetField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mFeetField.setEditable(false);

		mStaminaFullField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mStaminaFullField.setEditable(false);
		mHitPointsFullField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHitPointsFullField.setEditable(false);

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mStaminaLeftField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);
		mHitPointsLeftField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JLabel deathLabel = new JLabel(DEATH_LABEL, SwingConstants.RIGHT);
		mDeathField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		JPanel wrapper = new JPanel(new GridLayout(2, 4, 5, 0));

		wrapper.add(new JLabel());
		wrapper.add(withArmorLabel);
		wrapper.add(withoutArmorLabel);
		wrapper.add(Box.createHorizontalStrut(20));

		wrapper.add(armorLabel);
		wrapper.add(mArmorField);
		wrapper.add(mArmorField2);

		JPanel wrapper2 = new JPanel(new GridLayout(9, 6, 5, 0));

		wrapper2.add(headLabel);
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));

		wrapper2.add(topLabel);
		wrapper2.add(mHeadTopField);
		wrapper2.add(neckLabel);
		wrapper2.add(mNeckField);
		wrapper2.add(handsLabel);
		wrapper2.add(mHandsField);

		wrapper2.add(sideLabel);
		wrapper2.add(mHeadSideField);
		wrapper2.add(torsoLabel);
		wrapper2.add(mTorsoField);
		wrapper2.add(legsLabel);
		wrapper2.add(mLegsField);

		wrapper2.add(faceLabel);
		wrapper2.add(mHeadFaceField);
		wrapper2.add(armsLabel);
		wrapper2.add(mArmsField);
		wrapper2.add(feetLabel);
		wrapper2.add(mFeetField);

		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(STAMINA_LABEL, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(HIT_POINTS_LABEL, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		wrapper2.add(new JLabel(FULL_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mStaminaFullField);
		wrapper2.add(new JLabel(FULL_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mHitPointsFullField);
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		wrapper2.add(new JLabel(LEFT_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mStaminaLeftField);
		wrapper2.add(new JLabel(LEFT_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mHitPointsLeftField);
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(deathLabel);
		wrapper2.add(mDeathField);
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		outerWrapper.add(wrapper);
		outerWrapper.add(Box.createVerticalStrut(20));
		outerWrapper.add(wrapper2);

		return outerWrapper;
	}

	@Override
	public void loadDisplay() {

		mArmorField.setText(TKStringHelpers.EMPTY_STRING + generateArmorRating(true));
		mArmorField2.setText(TKStringHelpers.EMPTY_STRING + generateArmorRating(false));

		generateArmorCoverage();
		mHeadTopField.setText(mArmorCoverage[0] + " / " + mArmorBonusCoverage[0] + " (" + mArmorMissileCoverage[0] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHeadSideField.setText(mArmorCoverage[1] + " / " + mArmorBonusCoverage[1] + " (" + mArmorMissileCoverage[1] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHeadFaceField.setText(mArmorCoverage[2] + " / " + mArmorBonusCoverage[2] + " (" + mArmorMissileCoverage[2] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mNeckField.setText(mArmorCoverage[3] + " / " + mArmorBonusCoverage[3] + " (" + mArmorMissileCoverage[3] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mTorsoField.setText(mArmorCoverage[4] + " / " + mArmorBonusCoverage[4] + " (" + mArmorMissileCoverage[4] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mArmsField.setText(mArmorCoverage[5] + " / " + mArmorBonusCoverage[5] + " (" + mArmorMissileCoverage[5] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHandsField.setText(mArmorCoverage[6] + " / " + mArmorBonusCoverage[6] + " (" + mArmorMissileCoverage[6] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mLegsField.setText(mArmorCoverage[7] + " / " + mArmorBonusCoverage[7] + " (" + mArmorMissileCoverage[7] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mFeetField.setText(mArmorCoverage[8] + " / " + mArmorBonusCoverage[8] + " (" + mArmorMissileCoverage[8] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		mHitPointsFullField.setText(TKStringHelpers.EMPTY_STRING + generateFullHitPoints());
		mStaminaFullField.setText(TKStringHelpers.EMPTY_STRING + generateFullStamina());

		// loaded from file
		mStaminaLeftField.setText(TKStringHelpers.EMPTY_STRING + mStaminaLeft);
		mHitPointsLeftField.setText(TKStringHelpers.EMPTY_STRING + mHitPointsLeft);
		mDeathField.setText(TKStringHelpers.EMPTY_STRING + mDeath);
	}

	private int generateArmorRating(boolean withArmor) {
		CharacterSheet owner = (CharacterSheet) getOwner();

		int armorRating = 0;
		int protectionAmount = 0;

		if (owner.getAttributesRecord() != null) {
			if (owner.getPercentEncumbrance() > 50) {
				armorRating = 50;
			} else {
				int dex = owner.getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
				armorRating = dex * 2 + 50;
			}
			ArrayList<ArmorRecord> records = owner.getEquippedArmorRecords();
			if (records != null && withArmor) {
				for (ArmorRecord record : records) {
					protectionAmount += record.getProtectionAmount();
				}
			}
		}
		return armorRating + protectionAmount;
	}

	/**
	 * An array of the amount of absorption for each location (0=Head-Top, 1=Head-Side, 2=Head-Face,
	 * 3=Neck, 4=Torso, 5=Arms, 6=Hands, 7=Legs, 8=Feet, 9=Shield)
	 */
	private void generateArmorCoverage() {
		mArmorCoverage = new int[9];
		mArmorBonusCoverage = new int[9];
		mArmorMissileCoverage = new int[9];
		CharacterSheet owner = (CharacterSheet) getOwner();
		ArrayList<ArmorRecord> records = owner.getEquippedArmorRecords();
		if (records != null) {
			for (ArmorRecord record : records) {
				int[] location = record.getProtectionType();
				for (int i = 0; i < location.length; i++) {
					if (location[i] >= mArmorCoverage.length) {
						continue;
					}
					mArmorCoverage[location[i]] += record.getAbsorption();
					mArmorMissileCoverage[location[i]] += record.getMissileAbsorption();
					mArmorBonusCoverage[location[i]] += record.getBonus();
				}
			}
		}
	}

	private int getAttributeBonus(int stat) {
		int bonus = 0;
		if (stat < 6) {
			bonus = stat - 6;
		} else if (stat > 12) {
			bonus = stat - 12;
		}
		return bonus;
	}

	private int generateFullStamina() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		BaseClass characterClass = owner.getHeaderRecord().getCharacterClass();

		if (characterClass == null) {
			return 0;
		}

		int stamina = 1 + characterClass.getStamina() + getAttributeBonus(owner.getAttributesRecord().getModifiedStat(AttributesRecord.STR));

		return stamina < 1 ? 1 : stamina;

	}

	private int generateFullHitPoints() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		BaseClass characterClass = owner.getHeaderRecord().getCharacterClass();

		// DW should be able to use... owner.isCharacterLoaded(); need to verify
		if (characterClass == null) {
			return 0;
		}

		int hp = 7 + characterClass.getHitPoints() + getAttributeBonus(owner.getAttributesRecord().getModifiedStat(AttributesRecord.CON));

		return hp;

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

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mStaminaLeftField)) {
				mStaminaLeft = TKStringHelpers.getIntValue(mStaminaLeftField.getText(), 0);
			} else if (((JTextField) source).equals(mHitPointsLeftField)) {
				mHitPointsLeft = TKStringHelpers.getIntValue(mHitPointsLeftField.getText(), 0);
			} else if (((JTextField) source).equals(mDeathField)) {
				mDeath = TKStringHelpers.getIntValue(mDeathField.getText(), 0);
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						String value = ""; //$NON-NLS-1$
						setKeyValuePair(key, value);
					} else {
						String value = tokenizer.nextToken();
						while (tokenizer.hasMoreTokens()) {
							value = value + " " + tokenizer.nextToken(); //$NON-NLS-1$
						}
						setKeyValuePair(key, value);
					}
				}
			}
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());
		br.write(STAMINA_LEFT_KEY + TKStringHelpers.SPACE + mStaminaLeft + System.lineSeparator());
		br.write(HIT_POINTS_LEFT_KEY + TKStringHelpers.SPACE + mHitPointsLeft + System.lineSeparator());
		br.write(DEATH_KEY + TKStringHelpers.SPACE + mDeath + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (STAMINA_LEFT_KEY.equals(key)) {
			mStaminaLeft = TKStringHelpers.getIntValue(value, 0);
		} else if (HIT_POINTS_LEFT_KEY.equals(key)) {
			mHitPointsLeft = TKStringHelpers.getIntValue(value, 0);
		} else if (DEATH_KEY.equals(key)) {
			mDeath = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void clearRecords() {
		mStaminaLeft = 0;
		mHitPointsLeft = 0;
		mDeath = 0;
		mArmorField.setText(TKStringHelpers.EMPTY_STRING);
		mArmorField2.setText(TKStringHelpers.EMPTY_STRING);

	}
}
