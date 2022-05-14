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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class WeaponOwnedDisplay extends WeaponDisplay implements TableModelListener {
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
	public WeaponOwnedDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is on the Equipment Sheet and is loaded from the character file
		WeaponList list = ((CharacterSheet) getOwner()).getWeaponList();
		if (list != null) {
			ArrayList<WeaponRecord> records = list.getRecords();
			Object[] master = new Object[records.size()];
			Object[][] data = new Object[master.length][16];

			for (int i = 0; i < master.length; i++) {
				WeaponRecord record = records.get(i);
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

		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		mTable.getColumnModel().getColumn(1).setMinWidth(55); // Equipped
		mTable.getColumnModel().getColumn(2).setMinWidth(100); // Name
		mTable.getColumnModel().getColumn(3).setMinWidth(70); // Metal - give it enough room for popup
		mTable.getColumnModel().getColumn(4).setMinWidth(70); // Type
		mTable.getColumnModel().getColumn(15).setMinWidth(50); // Cost

		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		WeaponList weapons = owner.getWeaponList();
		if (weapons != null) {
			ArrayList<WeaponRecord> records = weapons.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (WeaponRecord record : records) {
				if (((Boolean) record.getRecord(1)).booleanValue()) {
					ACS.getInstance().getCharacterSheet().equipWeapon(record, 0);
				}
				model.addRow(record.getRecord());
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private WeaponRecord getRecord(TableModelEvent e) {
		int firstChangedRow = e.getFirstRow();
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		if (!data.isEmpty() && model.getRowCount() > firstChangedRow) {
			Vector<Object> row = data.get(firstChangedRow);
			if (row != null) {
				WeaponRecord record = new WeaponRecord(row);
				return record;
			}
		}
		return null;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		WeaponRecord record = getRecord(e);
		if (e.getColumn() == 1) {
			boolean equipped = ((Boolean) mTable.getValueAt(row, e.getColumn())).booleanValue();
			CharacterSheet characterSheet = ACS.getInstance().getCharacterSheet();
			if (equipped) {
				characterSheet.equipWeapon(record, row);
			} else {
				characterSheet.unEquipWeapon(record, row);
			}
			characterSheet.updateForEncubrance();
			characterSheet.getAttackTotalsDisplay().loadDisplay();
		}
	}

	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
