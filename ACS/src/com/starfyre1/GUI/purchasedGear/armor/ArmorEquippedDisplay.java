/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.armor;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataset.ArmorList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ArmorEquippedDisplay extends ArmorDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKTable					mTable;
	private ArrayList<ArmorRecord>	mEquippedArmor;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public ArmorEquippedDisplay(Object owner) {
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
		mTable.removeColumn(mTable.getColumnModel().getColumn(13));
		mTable.removeColumn(mTable.getColumnModel().getColumn(10));
		mTable.removeColumn(mTable.getColumnModel().getColumn(1));
		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// this is the Armor information on the character tab
		// equipped items (on the equipment tab) will show up here
		ArmorList armor = ACS.getInstance().getCharacterSheet().getArmorList();
		mEquippedArmor = new ArrayList<ArmorRecord>();
		if (armor != null) {
			ArrayList<ArmorRecord> records = armor.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (ArmorRecord record : records) {
				if (((Boolean) record.getRecord(1)).booleanValue()) {
					mEquippedArmor.add(record);
					model.addRow(record.getRecord());
				}
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The equippedArmor. */
	public ArrayList<ArmorRecord> getEquippedArmor() {
		return mEquippedArmor;
	}

	public void equipArmor(ArmorRecord armor, int index) {
		if (mEquippedArmor == null) {
			mEquippedArmor = new ArrayList<ArmorRecord>();
		}

		if (!mEquippedArmor.contains(armor)) {
			mEquippedArmor.add(armor);
			TKTableModel model = (TKTableModel) mTable.getModel();
			int rowCount = model.getRowCount();
			index = rowCount < index ? rowCount : index;
			model.insertRow(index, armor.getRecord());
		}
	}

	public void unEquipArmor(ArmorRecord armor) {
		mEquippedArmor.remove(armor);

		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				ArmorRecord record = new ArmorRecord(row);
				if (record.equals(armor)) {
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
