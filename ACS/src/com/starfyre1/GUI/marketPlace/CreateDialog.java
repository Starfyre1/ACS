/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.AnimalList;
import com.starfyre1.dataset.ArmorList;
import com.starfyre1.dataset.EquipmentList;
import com.starfyre1.dataset.MagicItemList;
import com.starfyre1.dataset.WeaponList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CreateDialog extends JDialog implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	//	private static final String	TITLE[]	= { "Animal", "Armor", "Equipment", "Magic Item", "Weapon" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	private static final String		SAVE	= "Save";	//$NON-NLS-1$
	private static final String		CANCEL	= "Cancel";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JFrame					mFrame;

	private JTabbedPane				mTabbedPane;

	private EquipmentEntryDisplay	mEquipmentShop;
	private ArmorEntryDisplay		mArmorShop;
	private WeaponEntryDisplay		mWeaponShop;
	private AnimalEntryDisplay		mAnimalShop;
	private MagicItemEntryDisplay	mMagicItemsShop;

	JButton							mSaveButton;
	JButton							mCancelButton;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public CreateDialog(JFrame parent) {
		super(parent, SAVE, true);

		mFrame = parent;

		JPanel wrapper = new JPanel(new BorderLayout(10, 10));
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createDisplay();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void createDisplay() {
		mTabbedPane = new JTabbedPane();

		JComponent equipmentTab = makeEquipmentTab();
		JComponent armorTab = makeArmorTab();
		JComponent weaponsTab = makeWeaponsTab();
		JComponent animalsTab = makeAnimalsTab();
		JComponent magicItemsTab = makeMagicItemsTab();

		mTabbedPane.addTab(MarketPlace.EQUIPMENT_TAB_TITLE, CharacterSheet.MERCHANT_ICON, equipmentTab, MarketPlace.EQUIPMENT_TAB_TOOLTIP);
		mTabbedPane.addTab(MarketPlace.ARMOR_TAB_TITLE, CharacterSheet.MERCHANT_ICON, armorTab, MarketPlace.ARMOR_TAB_TOOLTIP);
		mTabbedPane.addTab(MarketPlace.WEAPONS_TAB_TITLE, CharacterSheet.MERCHANT_ICON, weaponsTab, MarketPlace.WEAPONS_TAB_TOOLTIP);
		mTabbedPane.addTab(MarketPlace.ANIMALS_TAB_TITLE, CharacterSheet.MERCHANT_ICON, animalsTab, MarketPlace.ANIMALS_TAB_TOOLTIP);
		mTabbedPane.addTab(MarketPlace.MAGIC_ITEMS_TAB_TITLE, CharacterSheet.MERCHANT_ICON, magicItemsTab, MarketPlace.MAGIC_ITEMS_TAB_TOOLTIP);

		mTabbedPane.setMnemonicAt(0, KeyEvent.VK_E);
		mTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
		mTabbedPane.setMnemonicAt(2, KeyEvent.VK_W);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_N);
		mTabbedPane.setMnemonicAt(4, KeyEvent.VK_M);

		JPanel messagePanel = new JPanel(new FlowLayout());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		mSaveButton = TKComponentHelpers.createButton(SAVE, this, false);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mSaveButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCancelButton);

		add(messagePanel, BorderLayout.NORTH);
		add(mTabbedPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(mFrame);
		updateButtons(false);
		setVisible(true);
	}

	private JComponent makeEquipmentTab() {
		JPanel page = new JPanel(new BorderLayout());

		mEquipmentShop = new EquipmentEntryDisplay(this);

		page.add(mEquipmentShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeArmorTab() {
		JPanel page = new JPanel(new BorderLayout());

		mArmorShop = new ArmorEntryDisplay(this);

		page.add(mArmorShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeWeaponsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mWeaponShop = new WeaponEntryDisplay(this);

		page.add(mWeaponShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeAnimalsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mAnimalShop = new AnimalEntryDisplay(this);

		page.add(mAnimalShop, BorderLayout.CENTER);

		return page;
	}

	private JComponent makeMagicItemsTab() {
		JPanel page = new JPanel(new BorderLayout());

		mMagicItemsShop = new MagicItemEntryDisplay(this);

		page.add(mMagicItemsShop, BorderLayout.CENTER);

		return page;
	}

	void updateButtons(boolean enable) {
		// DW validate that an item is fully described/filled out
		mSaveButton.setEnabled(enable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
			if (mSaveButton.equals(obj)) {
				// DW add record to database
				Component comp = ((JPanel) mTabbedPane.getSelectedComponent()).getComponent(0);
				if (comp instanceof EquipmentEntryDisplay) {
					((EquipmentEntryDisplay) comp).finalizeSelections();
					ArrayList<EquipmentRecord> recordsToAdd = ((EquipmentEntryDisplay) comp).getRecordsToAdd();
					EquipmentList.addEquipmentToFile(recordsToAdd);
				} else if (comp instanceof ArmorEntryDisplay) {
					((ArmorEntryDisplay) comp).finalizeSelections();
					ArrayList<ArmorRecord> recordsToAdd = ((ArmorEntryDisplay) comp).getRecordsToAdd();
					ArmorList.addArmorToFile(recordsToAdd);
				} else if (comp instanceof AnimalEntryDisplay) {
					((AnimalEntryDisplay) comp).finalizeSelections();
					ArrayList<AnimalRecord> recordsToAdd = ((AnimalEntryDisplay) comp).getRecordsToAdd();
					AnimalList.addAnimalToFile(recordsToAdd);
				} else if (comp instanceof MagicItemEntryDisplay) {
					((MagicItemEntryDisplay) comp).finalizeSelections();
					ArrayList<MagicItemRecord> recordsToAdd = ((MagicItemEntryDisplay) comp).getRecordsToAdd();
					MagicItemList.addMagicItemToFile(recordsToAdd);
				} else if (comp instanceof WeaponEntryDisplay) {
					((WeaponEntryDisplay) comp).finalizeSelections();
					ArrayList<WeaponRecord> recordsToAdd = ((WeaponEntryDisplay) comp).getRecordsToAdd();
					WeaponList.addWeaponToFile(recordsToAdd);
				}
			}
			dispose();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
