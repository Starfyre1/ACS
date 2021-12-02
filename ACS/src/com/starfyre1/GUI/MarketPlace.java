/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.GUI.equipment.AnimalsDisplay;
import com.starfyre1.GUI.equipment.EquipmentDisplay;
import com.starfyre1.GUI.equipment.MagicItemsDisplay;
import com.starfyre1.GUI.equipment.armor.ArmorMarketPlaceDisplay;
import com.starfyre1.GUI.equipment.weapon.WeaponDisplay;
import com.starfyre1.GUI.equipment.weapon.WeaponMarketPlaceDisplay;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.dataModel.MoneyRecord;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MarketPlace extends JDialog implements ActionListener {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MarketPlace(new JFrame(MARKET_PLACE));
				System.exit(0);
			}
		});

	}

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String			MARKET_PLACE			= "Market Place";		//$NON-NLS-1$

	private static final String			EQUIPMENT_TAB_TITLE		= "Equipment Shop";		//$NON-NLS-1$
	private static final String			ARMOR_TAB_TITLE			= "Armor Shop";			//$NON-NLS-1$
	private static final String			WEAPONS_TAB_TITLE		= "Weapons Shop";		//$NON-NLS-1$
	private static final String			ANIMALS_TAB_TITLE		= "Animals Shop";		//$NON-NLS-1$
	private static final String			MAGIC_ITEMS_TAB_TITLE	= "Magic Items Shop";	//$NON-NLS-1$

	// DW add something useful for the tooltips or remove them
	private static final String			EQUIPMENT_TAB_TOOLTIP	= "Equipment Shop";		//$NON-NLS-1$
	private static final String			ARMOR_TAB_TOOLTIP		= "Armor Shop";			//$NON-NLS-1$
	private static final String			WEAPONS_TAB_TOOLTIP		= "Weapons Shop";		//$NON-NLS-1$
	private static final String			ANIMALS_TAB_TOOLTIP		= "Animals Shop";		//$NON-NLS-1$
	private static final String			MAGIC_ITEMS_TAB_TOOLTIP	= "Magic Items Shop";	//$NON-NLS-1$

	private static final String			AVAILABLE				= "Available: ";		//$NON-NLS-1$
	private static final String			COST					= "Cost: ";				//$NON-NLS-1$
	private static final String			mGoldTitle				= " Gold: ";			//$NON-NLS-1$
	private static final String			mSilverTitle			= " Silver: ";			//$NON-NLS-1$
	private static final String			mCopperTitle			= " Copper: ";			//$NON-NLS-1$

	private static final Icon			ICON					= null;

	//	private static EquipmentList	mEquipment;
	//	private static ArmorList		mArmor;
	//	private static WeaponList		mWeapon;
	//	private static AnimalList		mAnimals;
	//	private static MagicItemList	mMagicItems;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static MarketPlace			mInstance;

	private JFrame						mFrame;

	private JTabbedPane					mTabbedPane;
	private EquipmentDisplay			mEquipmentShop;
	private ArmorMarketPlaceDisplay		mArmorShop;
	private WeaponMarketPlaceDisplay	mWeaponShop;
	private AnimalsDisplay				mAnimalShop;
	private MagicItemsDisplay			mMagicItemsShop;

	JButton								mBuyButton;
	JButton								mCancelButton;
	JButton								mSellButton;

	JLabel								mCost;
	JLabel								mAvailable;
	JLabel								mGold;
	JLabel								mSilver;
	JLabel								mCopper;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MarketPlace(JFrame parent) {
		super(parent, MARKET_PLACE, true);

		mInstance = this;
		mFrame = parent;

		JPanel wrapper = new JPanel(new BorderLayout(10, 10));
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//		setUndecorated(true);

		//		createTables();
		create();

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	//	private void createTables() {
	//		mEquipment = new EquipmentList();
	//		mArmor = new ArmorList();
	//		mWeapon = new WeaponList();
	//		mAnimals = new AnimalList();
	//		mMagicItems = new MagicItemList();
	//
	//	}

	private void create() {
		mTabbedPane = new JTabbedPane();

		JComponent equipmentTab = makeEquipmentTab();
		JComponent armorTab = makeArmorTab();
		JComponent weaponsTab = makeWeaponsTab();
		JComponent animalsTab = makeAnimalsTab();
		JComponent magicItemsTab = makeMagicItemsTab();

		mTabbedPane.addTab(EQUIPMENT_TAB_TITLE, ICON, equipmentTab, EQUIPMENT_TAB_TOOLTIP);
		mTabbedPane.addTab(ARMOR_TAB_TITLE, ICON, armorTab, ARMOR_TAB_TOOLTIP);
		mTabbedPane.addTab(WEAPONS_TAB_TITLE, ICON, weaponsTab, WEAPONS_TAB_TOOLTIP);
		mTabbedPane.addTab(ANIMALS_TAB_TITLE, ICON, animalsTab, ANIMALS_TAB_TOOLTIP);
		mTabbedPane.addTab(MAGIC_ITEMS_TAB_TITLE, ICON, magicItemsTab, MAGIC_ITEMS_TAB_TOOLTIP);

		mTabbedPane.setMnemonicAt(0, KeyEvent.VK_E);
		mTabbedPane.setMnemonicAt(1, KeyEvent.VK_R);
		mTabbedPane.setMnemonicAt(2, KeyEvent.VK_W);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_A);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_M);

		JPanel messagePanel = new JPanel(new FlowLayout());
		JLabel money;

		if (ACS.getInstance() != null && ACS.getInstance().getCharacterSheet().getMoneyRecord() != null) {
			MoneyRecord moneyRecord = ACS.getInstance().getCharacterSheet().getMoneyRecord();
			money = new JLabel(getDisplayableCost(moneyRecord.getGold(), moneyRecord.getSilver(), moneyRecord.getCopper(), false));
		} else {
			money = new JLabel(getDisplayableCost(0f, false));
		}

		mCost = new JLabel(getDisplayableCost(0f, true));

		messagePanel.add(money, Box.createHorizontalGlue());
		messagePanel.add(Box.createHorizontalStrut(300));
		messagePanel.add(mCost);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		mBuyButton = TKComponentHelpers.createButton("Buy", this, false); //$NON-NLS-1$
		mCancelButton = TKComponentHelpers.createButton("Cancel", this); //$NON-NLS-1$
		mSellButton = TKComponentHelpers.createButton("Sell", this, !PreferenceStore.getInstance().isDefaults()); //$NON-NLS-1$

		buttonPanel.add(mSellButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mBuyButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCancelButton);

		add(messagePanel, BorderLayout.NORTH);
		add(mTabbedPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(mFrame);
		setVisible(true);
	}

	private JComponent makeEquipmentTab() {
		JPanel page = new JPanel(new BorderLayout());

		mEquipmentShop = new EquipmentDisplay(this);

		page.add(mEquipmentShop.getFilterPanel(), BorderLayout.NORTH);
		page.add(mEquipmentShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeArmorTab() {
		JPanel page = new JPanel(new BorderLayout());

		mArmorShop = new ArmorMarketPlaceDisplay(this);

		page.add(mArmorShop.getFilterPanel(), BorderLayout.NORTH);
		page.add(mArmorShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeWeaponsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mWeaponShop = new WeaponMarketPlaceDisplay(this);

		page.add(mWeaponShop.getFilterPanel(), BorderLayout.NORTH);
		page.add(mWeaponShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeAnimalsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mAnimalShop = new AnimalsDisplay(this);

		page.add(mAnimalShop.getFilterPanel(), BorderLayout.NORTH);
		page.add(mAnimalShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeMagicItemsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mMagicItemsShop = new MagicItemsDisplay(this);

		page.add(mMagicItemsShop.getFilterPanel(), BorderLayout.NORTH);
		page.add(mMagicItemsShop, BorderLayout.CENTER);

		return page;
	}

	public void updateButtons(float cost) {
		mCancelButton.setEnabled(true);
		//		mSellButton.setEnabled(!areDefaultsSet());
		mBuyButton.setEnabled(canAfford(cost));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(mCancelButton)) {
			dispose();
		} else if (source.equals(mBuyButton)) {
			getBoughtItems();
			// DW reduce player money
			dispose();
		} else if (source.equals(mSellButton)) {
			// DW Load Market Place with players equipment, button to include equipped items
			// DW Update Buy Button for Sell Button
			// DW Remove items and add money
		}
	}

	private void getBoughtItems() {
		Component comp = ((JPanel) mTabbedPane.getSelectedComponent()).getComponent(1);
		if (comp instanceof EquipmentDisplay) {
			ACS.getInstance().getCharacterSheet().addAllEquipment(((EquipmentDisplay) comp).getPurchasedRows());
		} else if (comp instanceof AnimalsDisplay) {
			ACS.getInstance().getCharacterSheet().addAllAnimals(((AnimalsDisplay) comp).getPurchasedRows());
		} else if (comp instanceof ArmorMarketPlaceDisplay) {
			ACS.getInstance().getCharacterSheet().addAllArmor(((ArmorMarketPlaceDisplay) comp).getPurchasedRows());
		} else if (comp instanceof MagicItemsDisplay) {
			ACS.getInstance().getCharacterSheet().addAllMagicItems(((MagicItemsDisplay) comp).getPurchasedRows());
		} else if (comp instanceof WeaponDisplay) {
			ACS.getInstance().getCharacterSheet().addAllWeapons(((WeaponMarketPlaceDisplay) comp).getPurchasedRows());
		} else {
			return;
		}

		CharacterSheet character = ACS.getInstance().getCharacterSheet();
		if (character != null) {
			character.updateForEncubrance();
			character.loadDisplay();
		}

	}

	private boolean canAfford(float cost) {
		if (ACS.getInstance() != null && ACS.getInstance().getCharacterSheet().getMoneyRecord() != null) {
			if (cost > 0 && cost <= ACS.getInstance().getCharacterSheet().getMoneyRecord().getAvailableMoney()) {
				return true;
			}
		}
		return false;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The mInstance. */
	public static MarketPlace getInstance() {
		return mInstance;
	}

	/**
	 * @param cost
	 */
	public void setDisplayableCost(String cost) {
		mCost.setText(cost);
	}

	public String getDisplayableCost(float amount, boolean isCost) {
		int copper = Math.round(amount % 1 * 10);
		int gold = (int) (amount / 10);
		int silver = (int) (amount % 10);

		return getDisplayableCost(gold, silver, copper, isCost);
	}

	private String getDisplayableCost(int gold, int silver, int copper, boolean isCost) {
		String type = isCost ? COST : AVAILABLE;

		// DW will want to make the amount red if more than they have
		return new String(type + mGoldTitle + gold + mSilverTitle + silver + mCopperTitle + copper);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
