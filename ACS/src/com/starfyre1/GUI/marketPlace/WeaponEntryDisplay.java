/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.purchasedGear.weapon.WeaponDisplay;
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

public class WeaponEntryDisplay extends WeaponDisplay implements TableModelListener {

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
	/**
	 * Creates a new {@link WeaponEntryDisplay}.
	 *
	 * @param owner
	 */
	public WeaponEntryDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		mTable = new TKTable(new MarketPlaceEntryTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 1));
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
		WeaponList weapons = owner.getWeaponList();
		if (weapons != null) {
			ArrayList<WeaponRecord> records = weapons.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (WeaponRecord record : records) {
				if (((Boolean) record.getRecord(1)).booleanValue()) {
					ACS.getInstance().getCharacterSheet().equipWeapon(record, 0);
					model.addRow(record.getRecord());
				}
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
			if (equipped) {
				ACS.getInstance().getCharacterSheet().equipWeapon(record, row);
			} else {
				ACS.getInstance().getCharacterSheet().unEquipWeapon(record);
			}
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
