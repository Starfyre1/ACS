/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.weapon;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class WeaponEquippedDisplay extends WeaponDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKTable					mTable;
	private ArrayList<WeaponRecord>	mEquippedWeapons;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public WeaponEquippedDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is the equipped weapons in the information section on the main page
		// This is loaded from Equipment Sheet
		mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.CHARACTER_TAB_TABLE_SIZE);
		// remove in reverse order... as the column numbers will change
		mTable.removeColumn(mTable.getColumnModel().getColumn(15));
		mTable.removeColumn(mTable.getColumnModel().getColumn(7));
		mTable.removeColumn(mTable.getColumnModel().getColumn(6));
		mTable.removeColumn(mTable.getColumnModel().getColumn(1));
		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// this is the Weapon information on the character tab
		// equipped items (on the equipment tab) will show up here
		WeaponList weapons = ACS.getInstance().getCharacterSheet().getWeaponList();
		mEquippedWeapons = new ArrayList<WeaponRecord>();
		if (weapons != null) {
			ArrayList<WeaponRecord> records = weapons.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (WeaponRecord record : records) {
				if (((Boolean) record.getRecord(1)).booleanValue()) {
					mEquippedWeapons.add(record);
					model.addRow(record.getRecord());
				}
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The equippedWeapons. */
	public ArrayList<WeaponRecord> getEquippedWeapons() {
		return mEquippedWeapons;
	}

	public void equipWeapon(WeaponRecord equipment, int index) {
		if (mEquippedWeapons == null) {
			mEquippedWeapons = new ArrayList<WeaponRecord>();
		}
		if (!mEquippedWeapons.contains(equipment)) {
			mEquippedWeapons.add(equipment);
			TKTableModel model = (TKTableModel) mTable.getModel();
			int rowCount = model.getRowCount();
			index = rowCount < index ? rowCount : index;
			model.insertRow(index, equipment.getRecord());
		}
	}

	public void unEquipWeapon(WeaponRecord equipment) {
		mEquippedWeapons.remove(equipment);

		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				WeaponRecord record = new WeaponRecord(row);
				if (record.equals(equipment)) {
					model.removeRow(i);
					break;
				}
			}
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
