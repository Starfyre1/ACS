/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class TreasureDisplay extends TKTitledDisplay implements Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		TREASURE_TITLE	= "Party Treasure";			//$NON-NLS-1$

	private static final String		TOTAL_TITLE		= "Total:";					//$NON-NLS-1$

	private static final String		GOLD_TITLE		= "Gold:";					//$NON-NLS-1$
	private static final String		SILVER_TITLE	= "Silver:";				//$NON-NLS-1$
	private static final String		COPPER_TITLE	= "Copper:";				//$NON-NLS-1$

	private static final String		NEW_ENTRY_TITLE	= "New Entry:";				//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE	= new Dimension(600, 285);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel					mTotalLabel;
	private JTextField				mGoldField;
	private JTextField				mSilverField;
	private JTextField				mCopperField;

	private int						mTotal;
	private int						mGold;
	private int						mSilver;
	private int						mCopper;

	// DW not implemented yet - old treasure display
	//	private ArrayList<GemsTriple>	mGemsList;
	//	private ArrayList<JewelryPair>	mJewleryList;
	//	private ArrayList<MagicQuad>	mMagicItemsList;

	private JButton					mNewEntryButton;

	private JTabbedPane				mTabbedPane;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TreasureDisplay(CharacterSheet owner) {
		super(owner, TREASURE_TITLE);

		updateValues();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		outerWrapper.add(generateValuePanel());
		outerWrapper.add(getDeterminationDisplay());

		return outerWrapper;
	}

	private JPanel generateValuePanel() {
		JPanel valuePanel = new JPanel();
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.X_AXIS));

		JPanel innerWrapper = new JPanel();
		mTotalLabel = TKComponentHelpers.createLabel(String.valueOf(mTotal));
		JLabel totalTitle = new JLabel(TOTAL_TITLE);
		innerWrapper.add(totalTitle);
		innerWrapper.add(mTotalLabel);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mGoldField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mGoldField.getDocument()).setDocumentFilter(ACS.INTEGER_FILTER);
		mGoldField.setText(String.valueOf(mGold));
		JLabel goldTitle = new JLabel(GOLD_TITLE);
		innerWrapper.add(goldTitle);
		innerWrapper.add(mGoldField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mSilverField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mSilverField.getDocument()).setDocumentFilter(ACS.INTEGER_FILTER);
		mSilverField.setText(String.valueOf(mSilver));
		JLabel silverTitle = new JLabel(SILVER_TITLE);
		innerWrapper.add(silverTitle);
		innerWrapper.add(mSilverField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mCopperField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mCopperField.getDocument()).setDocumentFilter(ACS.INTEGER_FILTER);
		mCopperField.setText(String.valueOf(mCopper));
		JLabel copperTitle = new JLabel(COPPER_TITLE);
		innerWrapper.add(copperTitle);
		innerWrapper.add(mCopperField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		JLabel newEntryTitle = new JLabel(NEW_ENTRY_TITLE);
		mNewEntryButton = new TKButtonRollover(this, ACS.IMAGE_PLUS_ICON, false);
		mNewEntryButton.setOpaque(true);
		mNewEntryButton.setPreferredSize(new Dimension(25, 25));
		mNewEntryButton.setFocusable(false);
		mNewEntryButton.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TreasureTab tab = (TreasureTab) mTabbedPane.getSelectedComponent();
				tab.addRecord();

				// DW get active table and add record
			}
		});
		innerWrapper.add(newEntryTitle);
		innerWrapper.add(mNewEntryButton);
		valuePanel.add(innerWrapper);

		return valuePanel;
	}

	private JPanel getDeterminationDisplay() {
		mTabbedPane = new JTabbedPane();

		JComponent gemsTab = new GemsTab(this);
		JComponent jewelryTab = new JewelryTab(this);
		JComponent armorTab = new ArmorTab(this);
		JComponent weaponTab = new WeaponsTab(this);
		JComponent animalTab = new AnimalsTab(this);
		JComponent miscellaneousTab = new MiscellaneousTab(this);

		mTabbedPane.addTab(GemsTab.GEMS_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, gemsTab, null);
		mTabbedPane.addTab(JewelryTab.JEWELRY_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, jewelryTab, null);
		mTabbedPane.addTab(ArmorTab.ARMOR_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, armorTab, null);
		mTabbedPane.addTab(WeaponsTab.WEAPON_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, weaponTab, null);
		mTabbedPane.addTab(AnimalsTab.ANIMALS_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, animalTab, null);
		mTabbedPane.addTab(MiscellaneousTab.MISCELLANEOUS_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, miscellaneousTab, null);

		mTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		mTabbedPane.setMnemonicAt(1, KeyEvent.VK_J);
		mTabbedPane.setMnemonicAt(2, KeyEvent.VK_A);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_W);
		mTabbedPane.setMnemonicAt(4, KeyEvent.VK_N);
		mTabbedPane.setMnemonicAt(5, KeyEvent.VK_M);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mTabbedPane, BorderLayout.CENTER);

		wrapper.setPreferredSize(PREFERRED_SIZE);

		return wrapper;
	}

	@Override
	protected void loadDisplay() {
		updateValues();
		addRecords(false);
	}

	public void clearRecords() {
		// mEntries = new ArrayList<>();
		//		updatePreviewPanel();

	}

	public void addRecords(boolean clear) {
		if (clear) {
			clearRecords();
		}

		//		for (AttributeDeterminationRecord record : DeterminationList.getAttribRecords()) {
		//			((AttributesTab) mTabbedPane.getComponent(0)).addRecord(record);
		//		}
		//
		//		for (LanguageDeterminationRecord record : DeterminationList.getLanguageRecords()) {
		//			((LanguageTab) mTabbedPane.getComponent(1)).addRecord(record);
		//		}
		//
		//		for (MagicSpellDeterminationRecord record : DeterminationList.getMagicSpellRecords()) {
		//			((MagicSpellTab) mTabbedPane.getComponent(2)).addRecord(record);
		//		}
		//
		//		for (WeaponDeterminationRecord record : DeterminationList.getWeaponRecords()) {
		//			((WeaponTab) mTabbedPane.getComponent(3)).addRecord(record);
		//		}
		//
		//		for (SkillDeterminationRecord record : DeterminationList.getSkillRecords()) {
		//			((SkillTab) mTabbedPane.getComponent(4)).addRecord(record);
		//		}
		//
		//		for (TeacherDeterminationRecord record : DeterminationList.getTeachersRecords()) {
		//			((TeacherTab) mTabbedPane.getComponent(5)).addRecord(record);
		//		}

		ACS.getInstance().getCharacterSheet().getDeterminationPointsDisplay().updateValues();
	}

	public void updateValues() {

		int count = mTabbedPane.getTabCount();
		int totalValue = 0;

		for (int i = 0; i < count; i++) {
			Component comp = mTabbedPane.getComponent(i);
			if (comp != null) {
				TreasureTab tab = (TreasureTab) comp;
				tab.loadDisplay();
				totalValue += tab.getValueTabTotal();
			}
		}
		mTotal = totalValue;
	}

	@Override
	public StringTokenizer readValues(BufferedReader br) {
		return null;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void setKeyValuePair(String key, Object value) {
		// DW not implemented yet - old treasure display
	}

	public static class GemsTriple {
		int		mCount;
		int		mValue;
		String	mMagic	= "";	//$NON-NLS-1$

		public GemsTriple(int count, int value, String magic) {
			mCount = count;
			mValue = value;
			mMagic = magic;
		}

		/** @return The count. */
		public int getCount() {
			return mCount;
		}

		/** @return The count. */
		public int getValue() {
			return mValue;
		}

		/** @return The pairDescription. */
		public String getMagic() {
			return mMagic;
		}

	}

	public static class JewelryPair {
		int						mValue;
		ArrayList<GemsTriple>	mGems;

		public JewelryPair(int value, ArrayList<GemsTriple> gems) {
			mValue = value;
			mGems = gems;
		}

		/** @return The count. */
		public int getValue() {
			return mValue;
		}

		/** @return The pairDescription. */
		public ArrayList<GemsTriple> getGems() {
			return mGems;
		}

	}

	public static class SpellPair {
		String	mSpellArea;
		String	mSpell;

		public SpellPair(String spellArea, String spell) {
			mSpellArea = spellArea;
			mSpell = spell;
		}

		/** @return The spellArea. */
		public String getSpellArea() {
			return mSpellArea;
		}

		/** @return The spell. */
		public String getSpell() {
			return mSpell;
		}

	}

	public static class MagicQuad {
		String					mEquipmentType;
		String					mChargeType;
		ArrayList<SpellPair>	mSpell;
		int						mValue;

		public MagicQuad(String equipmentType, String chargeType, ArrayList<SpellPair> spell, int value) {
			mEquipmentType = equipmentType;
			mChargeType = chargeType;
			mSpell = spell;
			mValue = value;
		}

		/** @return The equipmentType. */
		public String getEquipmentType() {
			return mEquipmentType;
		}

		/** @return The chargeType. */
		public String getChargeType() {
			return mChargeType;
		}

		/** @return The spell. */
		public ArrayList<SpellPair> getSpell() {
			return mSpell;
		}

		/** @return The value. */
		public int getValue() {
			return mValue;
		}

	}

}
