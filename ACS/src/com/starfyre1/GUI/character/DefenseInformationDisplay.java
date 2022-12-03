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

	private int					manaValues[][]				= { { 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 50 },		//
					{ 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 100 },												//
					{ 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 200 },											//
					{ 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 300 },											//
					{ 5, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 400 } };

	public static final String	FILE_SECTION_START_KEY		= "DEFENSE_INFORMATION_SECTION_START";													//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY		= "DEFENSE_INFORMATION_SECTION_END";													//$NON-NLS-1$

	private static final String	STAMINA_LEFT_KEY			= "STAMINA_LEFT_KEY";																	//$NON-NLS-1$
	private static final String	HIT_POINTS_LEFT_KEY			= "HIT_POINTS_LEFT_KEY";																//$NON-NLS-1$
	private static final String	MANA_LEFT_KEY				= "MANA_LEFT_KEY";																		//$NON-NLS-1$
	private static final String	MANA_PERM_KEY				= "MANA_PERM_KEY";																		//$NON-NLS-1$

	private static final String	DEFENSE_INFORMATION_TITLE	= "Defense Information";																//$NON-NLS-1$
	private static final String	ABSORBS_TITLE				= "Absorbs";																			//$NON-NLS-1$

	//DW need to add "Max allowable for spells"
	private static final String	WITH_ARMOR_LABEL			= "With Armor";																			//$NON-NLS-1$
	private static final String	WITHOUT_ARMOR_LABEL			= "Without Armor";																		//$NON-NLS-1$
	private static final String	ARMOR_RATIMG_LABEL			= "Armor Rating";																		//$NON-NLS-1$
	private static final String	HEAD_LABEL					= "Head";																				//$NON-NLS-1$
	private static final String	TOP_LABEL					= "Top";																				//$NON-NLS-1$
	private static final String	SIDE_LABEL					= "Side";																				//$NON-NLS-1$
	private static final String	FACE_LABEL					= "Face";																				//$NON-NLS-1$
	private static final String	NECK_LABEL					= "Neck";																				//$NON-NLS-1$
	private static final String	TORSO_LABEL					= "Torso";																				//$NON-NLS-1$
	private static final String	UPPER_ARMS_LABEL			= "U. Arms";																			//$NON-NLS-1$
	private static final String	LOWER_ARMS_LABEL			= "L. Arms";																			//$NON-NLS-1$
	private static final String	HANDS_LABEL					= "Hands";																				//$NON-NLS-1$
	private static final String	LEGS_LABEL					= "Legs";																				//$NON-NLS-1$
	private static final String	FEET_LABEL					= "Feet";																				//$NON-NLS-1$
	private static final String	STAMINA_LABEL				= "Stamina";																			//$NON-NLS-1$
	private static final String	HIT_POINTS_LABEL			= "Hit Points";																			//$NON-NLS-1$
	private static final String	MANA_LABEL					= "Mana";																				//$NON-NLS-1$
	private static final String	USED_LABEL					= "Used";																				//$NON-NLS-1$
	private static final String	PERM_LABEL					= "Perm. Used";																				//$NON-NLS-1$
	private static final String	FULL_LABEL					= "Full";																				//$NON-NLS-1$
	private static final String	DAMAGE_LABEL				= "Damage";																				//$NON-NLS-1$
	private static final String	BALANCE_LABEL				= "Balance";																			//$NON-NLS-1$

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
	private JTextField			mUpperArmsField;
	private JTextField			mLowerArmsField;
	private JTextField			mHandsField;
	private JTextField			mLegsField;
	private JTextField			mFeetField;

	private JTextField			mStaminaFullField;
	private JTextField			mHitPointsFullField;
	private JTextField			mManaFullField;
	private JTextField			mManaPermField;
	private JTextField			mStaminaDamageField;
	private JTextField			mHitPointsDamageField;
	private JTextField			mManaDamageField;
	private JTextField			mStaminaBalanceField;
	private JTextField			mHitPointsBalanceField;
	private JTextField			mManaBalanceField;

	private int					mStaminaFull				= 0;
	private int					mHitPointsFull				= 0;
	private int					mManaFull					= 0;
	private int					mManaPerm					= 0;
	private int					mStaminaDamage				= 0;
	private int					mHitPointsDamage			= 0;
	private int					mManaDamage					= 0;
	private int					mStaminaBalance				= 0;
	private int					mHitPointsBalance			= 0;
	private int					mManaBalance				= 0;

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

		JLabel upperArmsLabel = new JLabel(UPPER_ARMS_LABEL, SwingConstants.RIGHT);
		mUpperArmsField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mUpperArmsField.setEditable(false);

		JLabel lowerArmsLabel = new JLabel(LOWER_ARMS_LABEL, SwingConstants.RIGHT);
		mLowerArmsField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mLowerArmsField.setEditable(false);

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
		mManaFullField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mManaFullField.setEditable(false);

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mStaminaDamageField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);
		mHitPointsDamageField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);
		mManaDamageField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);
		mManaPermField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20, this, filter);

		mStaminaBalanceField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mStaminaBalanceField.setEditable(false);
		mHitPointsBalanceField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mHitPointsBalanceField.setEditable(false);
		mManaBalanceField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mManaBalanceField.setEditable(false);

		JPanel wrapper = new JPanel(new GridLayout(2, 4, 5, 0));

		wrapper.add(new JLabel());
		wrapper.add(withArmorLabel);
		wrapper.add(withoutArmorLabel);
		wrapper.add(Box.createHorizontalStrut(20));

		wrapper.add(armorLabel);
		wrapper.add(mArmorField);
		wrapper.add(mArmorField2);

		JPanel wrapper2 = new JPanel(new GridLayout(10, 6, 5, 0));

		wrapper2.add(headLabel);
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(ABSORBS_TITLE, SwingConstants.CENTER));

		wrapper2.add(topLabel);
		wrapper2.add(mHeadTopField);
		wrapper2.add(torsoLabel);
		wrapper2.add(mTorsoField);
		wrapper2.add(handsLabel);
		wrapper2.add(mHandsField);

		wrapper2.add(sideLabel);
		wrapper2.add(mHeadSideField);
		wrapper2.add(upperArmsLabel);
		wrapper2.add(mUpperArmsField);
		wrapper2.add(legsLabel);
		wrapper2.add(mLegsField);

		wrapper2.add(faceLabel);
		wrapper2.add(mHeadFaceField);
		wrapper2.add(lowerArmsLabel);
		wrapper2.add(mLowerArmsField);
		wrapper2.add(feetLabel);
		wrapper2.add(mFeetField);

		wrapper2.add(neckLabel);
		wrapper2.add(mNeckField);
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());

		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(MANA_LABEL, SwingConstants.CENTER));

		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(STAMINA_LABEL, SwingConstants.CENTER));
		wrapper2.add(new JLabel());
		wrapper2.add(new JLabel(HIT_POINTS_LABEL, SwingConstants.CENTER));
		wrapper2.add(new JLabel(FULL_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mManaFullField);

		wrapper2.add(new JLabel(FULL_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mStaminaFullField);
		wrapper2.add(new JLabel(FULL_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mHitPointsFullField);
		wrapper2.add(new JLabel(PERM_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mManaPermField);

		wrapper2.add(new JLabel(DAMAGE_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mStaminaDamageField);
		wrapper2.add(new JLabel(DAMAGE_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mHitPointsDamageField);
		wrapper2.add(new JLabel(USED_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mManaDamageField);

		wrapper2.add(new JLabel(BALANCE_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mStaminaBalanceField);
		wrapper2.add(new JLabel(BALANCE_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mHitPointsBalanceField);
		wrapper2.add(new JLabel(BALANCE_LABEL, SwingConstants.RIGHT));
		wrapper2.add(mManaBalanceField);

		outerWrapper.add(wrapper);
		outerWrapper.add(Box.createVerticalStrut(20));
		outerWrapper.add(wrapper2);

		return outerWrapper;
	}

	@Override
	public void loadDisplay() {

		mArmorField.setText(String.valueOf(generateArmorRating(true)));
		mArmorField2.setText(String.valueOf(generateArmorRating(false)));

		generateArmorCoverage();
		mHeadTopField.setText(mArmorCoverage[0] + " / " + mArmorBonusCoverage[0] + " (" + mArmorMissileCoverage[0] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHeadSideField.setText(mArmorCoverage[1] + " / " + mArmorBonusCoverage[1] + " (" + mArmorMissileCoverage[1] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHeadFaceField.setText(mArmorCoverage[2] + " / " + mArmorBonusCoverage[2] + " (" + mArmorMissileCoverage[2] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mNeckField.setText(mArmorCoverage[3] + " / " + mArmorBonusCoverage[3] + " (" + mArmorMissileCoverage[3] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mTorsoField.setText(mArmorCoverage[4] + " / " + mArmorBonusCoverage[4] + " (" + mArmorMissileCoverage[4] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mUpperArmsField.setText(mArmorCoverage[5] + " / " + mArmorBonusCoverage[5] + " (" + mArmorMissileCoverage[5] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mLowerArmsField.setText(mArmorCoverage[6] + " / " + mArmorBonusCoverage[6] + " (" + mArmorMissileCoverage[6] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mHandsField.setText(mArmorCoverage[7] + " / " + mArmorBonusCoverage[7] + " (" + mArmorMissileCoverage[7] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mLegsField.setText(mArmorCoverage[8] + " / " + mArmorBonusCoverage[8] + " (" + mArmorMissileCoverage[8] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mFeetField.setText(mArmorCoverage[9] + " / " + mArmorBonusCoverage[9] + " (" + mArmorMissileCoverage[9] + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		mStaminaFull = generateFullStamina();
		mHitPointsFull = generateFullHitPoints();
		mManaFull = generateFullMana();

		mStaminaFullField.setText(String.valueOf(mStaminaFull));
		mHitPointsFullField.setText(String.valueOf(mHitPointsFull));
		mManaFullField.setText(String.valueOf(mManaFull));
		mStaminaBalance = mStaminaFull - mStaminaDamage;
		mHitPointsBalance = mHitPointsFull - mHitPointsDamage;
		mManaBalance = mManaFull - mManaPerm - mManaDamage;

		// loaded from file
		mStaminaDamageField.setText(String.valueOf(mStaminaDamage));
		mHitPointsDamageField.setText(String.valueOf(mHitPointsDamage));
		mManaPermField.setText(String.valueOf(mManaPerm));
		mManaDamageField.setText(String.valueOf(mManaDamage));
		mStaminaBalanceField.setText(String.valueOf(mStaminaBalance));
		mHitPointsBalanceField.setText(String.valueOf(mHitPointsBalance));
		mManaBalanceField.setText(String.valueOf(mManaBalance));
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
	 * 3=Neck, 4=Torso, 5=U. Arms, 6=Hands, 7=Legs, 8=Feet, 9=Shield, 10=L. Arms)
	 */
	private void generateArmorCoverage() {
		mArmorCoverage = new int[11];
		mArmorBonusCoverage = new int[11];
		mArmorMissileCoverage = new int[11];
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

	private int generateFullMana() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		int level = owner.getHeaderRecord().getLevel();
		int bonusMana = 0;
		int intValue = owner.getAttributesRecord().getModifiedStat(AttributesRecord.INT);
		intValue = (intValue - 10) / 3;

		if (level > 18) {
			bonusMana = manaValues[intValue][18] * (level - 18);
			level = 18;
		}

		int mana = manaValues[intValue][level - 1] + bonusMana;

		return mana;
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
			if (((JTextField) source).equals(mStaminaDamageField)) {
				mStaminaDamage = TKStringHelpers.getIntValue(mStaminaDamageField.getText(), 0);
				mStaminaBalance = mStaminaFull - mStaminaDamage;
				mStaminaBalanceField.setText(String.valueOf(mStaminaBalance));
			} else if (((JTextField) source).equals(mHitPointsDamageField)) {
				mHitPointsDamage = TKStringHelpers.getIntValue(mHitPointsDamageField.getText(), 0);
				mHitPointsBalance = mHitPointsFull - mHitPointsDamage;
				mHitPointsBalanceField.setText(String.valueOf(mHitPointsBalance));
			} else if (((JTextField) source).equals(mManaDamageField) || ((JTextField) source).equals(mManaPermField)) {
				mManaDamage = TKStringHelpers.getIntValue(mManaDamageField.getText(), 0);
				mManaPerm = TKStringHelpers.getIntValue(mManaPermField.getText(), 0);
				mManaBalance = mManaFull - mManaPerm - mManaDamage;
				mManaBalanceField.setText(String.valueOf(mManaBalance));
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
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
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
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());

		br.write(TKStringHelpers.TAB + STAMINA_LEFT_KEY + TKStringHelpers.SPACE + mStaminaDamage + System.lineSeparator());
		br.write(TKStringHelpers.TAB + HIT_POINTS_LEFT_KEY + TKStringHelpers.SPACE + mHitPointsDamage + System.lineSeparator());
		br.write(TKStringHelpers.TAB + MANA_PERM_KEY + TKStringHelpers.SPACE + mManaPerm + System.lineSeparator());
		br.write(TKStringHelpers.TAB + MANA_LEFT_KEY + TKStringHelpers.SPACE + mManaDamage + System.lineSeparator());

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (STAMINA_LEFT_KEY.equals(key)) {
			mStaminaDamage = TKStringHelpers.getIntValue(value, 0);
		} else if (HIT_POINTS_LEFT_KEY.equals(key)) {
			mHitPointsDamage = TKStringHelpers.getIntValue(value, 0);
		} else if (MANA_PERM_KEY.equals(key)) {
			mManaPerm = TKStringHelpers.getIntValue(value, 0);
		} else if (MANA_LEFT_KEY.equals(key)) {
			mManaDamage = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void clearRecords() {
		mStaminaDamage = 0;
		mHitPointsDamage = 0;
		mManaPerm = 0;
		mManaDamage = 0;
		mStaminaBalance = 0;
		mHitPointsBalance = 0;
		mManaBalance = 0;
		mArmorField.setText(TKStringHelpers.EMPTY_STRING);
		mArmorField2.setText(TKStringHelpers.EMPTY_STRING);

	}
}
