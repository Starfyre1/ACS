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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ArmorOwnedDisplay extends ArmorDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKTable mTable;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public ArmorOwnedDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is on the Equipment Sheet and is loaded from the character file
		ArmorList list = ((CharacterSheet) getOwner()).getArmorList();
		if (list != null) {
			ArrayList<ArmorRecord> records = list.getRecords();
			Object[] master = new Object[records.size()];
			Object[][] data = new Object[master.length][14];

			for (int i = 0; i < master.length; i++) {
				ArmorRecord record = records.get(i);
				if (record == null) {
					continue;
				}
				for (int index = 0; index < data[i].length; index++) {
					data[i][index] = record.getRecord(index);
				}
			}
			mTable = new TKTable(new TKTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		} else {
			mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
		}
		mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		ArmorList armor = owner.getArmorList();
		if (armor != null) {
			ArrayList<ArmorRecord> records = armor.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (ArmorRecord record : records) {
				if (((Boolean) record.getRecord(1)).booleanValue()) {
					ACS.getInstance().getCharacterSheet().equipArmor(record, 0);
					model.addRow(record.getRecord());
				}
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		ArmorRecord record = getRecord(e);
		if (e.getColumn() == 1) {
			boolean equipped = ((Boolean) mTable.getValueAt(row, e.getColumn())).booleanValue();
			// DW add/remove from Armor table on character sheet
			if (equipped) {
				ACS.getInstance().getCharacterSheet().equipArmor(record, row);
			} else {
				ACS.getInstance().getCharacterSheet().unEquipArmor(record);
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private ArmorRecord getRecord(TableModelEvent e) {
		int firstChangedRow = e.getFirstRow();
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		if (!data.isEmpty() && model.getRowCount() > firstChangedRow) {
			Vector<Object> row = data.get(firstChangedRow);
			if (row != null) {
				ArmorRecord record = new ArmorRecord(row);
				return record;
			}
		}
		return null;
	}

	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
