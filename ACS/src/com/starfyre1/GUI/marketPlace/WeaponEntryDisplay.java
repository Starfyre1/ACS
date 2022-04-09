/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.metal.MetalCellEditor;
import com.starfyre1.GUI.metal.MetalCellRenderer;
import com.starfyre1.GUI.purchasedGear.weapon.WeaponDisplay;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.MetalRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.WeaponList;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

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
		// This is the user created equipment list in the create weapon dialog
		Object[] master = WeaponList.getWeaponUserList();
		Object[][] data = new Object[master.length][16];

		for (int i = 0; i < master.length; i++) {
			WeaponRecord record = (WeaponRecord) master[i];
			if (record == null) {
				continue;
			}
			for (int index = 0; index < data[i].length; index++) {
				data[i][index] = record.getRecord(index);
			}
		}
		mTable = new TKTable(new MarketPlaceEntryTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);
		mTable.setDefaultRenderer(MetalRecord.class, new MetalCellRenderer());
		mTable.setDefaultEditor(MetalRecord.class, new MetalCellEditor(MetalList.getRecords()));

		//		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		//		mTable.getColumnModel().getColumn(1).setMinWidth(55); // Equipped
		mTable.getColumnModel().getColumn(2).setMinWidth(100); // Name
		mTable.getColumnModel().getColumn(3).setMinWidth(70); // Metal - give it enough room for popup
		mTable.getColumnModel().getColumn(4).setMinWidth(70); // Type
		mTable.getColumnModel().getColumn(15).setMinWidth(50); // Cost

		mTable.removeColumn(mTable.getColumnModel().getColumn(1));
		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		mTable.getColumnModel().getColumn(0).setMinWidth(100); // Name
		mTable.getColumnModel().getColumn(1).setMinWidth(70); // Metal - give it enough room for popup
		mTable.getColumnModel().getColumn(2).setMinWidth(70); // Type
		mTable.getColumnModel().getColumn(10).setMinWidth(50); // Cost

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		addEmptyRowIfNeeded();
		mTable.getModel().addTableModelListener(this);

		return scrollPane;
	}

	private void addEmptyRowIfNeeded() {
		if (!hasEmptyRow()) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			Object[] obj = new Object[16];
			obj[3] = MetalList.getMetal(0);
			model.addRow(obj);
		}
	}

	@Override
	public void loadDisplay() {
		// DW load the user created items
	}

	public void finalizeSelections() {
		TableCellEditor editor = mTable.getCellEditor();
		if (editor != null) {
			editor.stopCellEditing();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		boolean validRow = isRowValid(row);
		((CreateDialog) getOwner()).updateButtons(validRow);
		if (validRow) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.removeTableModelListener(this);

			model.setValueAt(Integer.valueOf(0), row, 0);
			model.setValueAt(Boolean.valueOf(false), row, 1);
			// DW this needs to be made to fit weapon entry and not armor entry
			//			String name = (String) model.getValueAt(row, 2);
			//			String metal = (String) model.getValueAt(row, 3);
			//			int type = TKStringHelpers.getInt((String) model.getValueAt(row, 4), new int(0));
			//			int protectionAmount = TKStringHelpers.getIntValue((String) model.getValueAt(row, 5), 0);
			//			float encumbrance = TKStringHelpers.getFloatValue((String) model.getValueAt(row, 6), 0.0f);
			//			int absorption = TKStringHelpers.getIntValue((String) model.getValueAt(row, 7), 0);
			//			int bonus = TKStringHelpers.getIntValue((String) model.getValueAt(row, 8), 0);
			//			int missileAbsorption = TKStringHelpers.getIntValue((String) model.getValueAt(row, 9), 0);
			//			int strengthRequirement = TKStringHelpers.getIntValue((String) model.getValueAt(row, 10), 0);
			//			int parry = TKStringHelpers.getIntValue((String) model.getValueAt(row, 11), 0);
			//			int breakage = TKStringHelpers.getIntValue((String) model.getValueAt(row, 12), 0);
			//			float cost = TKStringHelpers.getFloatValue((String) model.getValueAt(row, 13), 0.0f);

			//			WeaponRecord record = new WeaponRecord(count, equipped, name, MetalList.getMetalID(metal), type, protectionAmount, encumbrance, absorption, bonus, missileAbsorption, strengthRequirement, parry, breakage, cost);
			// 			System.out.println(row + " :: " + record);
			addEmptyRowIfNeeded();

			model.addTableModelListener(this);

		}
	}

	private boolean isNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof String) {
			return ((String) value).isEmpty();
		}
		return false;
	}

	private boolean hasEmptyRow() {
		TableModel model = mTable.getModel();
		int rows = model.getRowCount();
		for (int i = 0; i < rows; i++) {
			if (isNullOrEmpty(model.getValueAt(i, 2)) || isNullOrEmpty(model.getValueAt(i, 3)) || isNullOrEmpty(model.getValueAt(i, 4)) || //
							isNullOrEmpty(model.getValueAt(i, 5)) || isNullOrEmpty(model.getValueAt(i, 6)) || isNullOrEmpty(model.getValueAt(i, 7)) || //
							isNullOrEmpty(model.getValueAt(i, 8)) || isNullOrEmpty(model.getValueAt(i, 9)) || isNullOrEmpty(model.getValueAt(i, 10)) || //
							isNullOrEmpty(model.getValueAt(i, 11)) || isNullOrEmpty(model.getValueAt(i, 12)) || isNullOrEmpty(model.getValueAt(i, 13)) || //
							isNullOrEmpty(model.getValueAt(i, 14)) || isNullOrEmpty(model.getValueAt(i, 15))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param firstRow
	 */
	private boolean isRowValid(int firstRow) {
		TableModel model = mTable.getModel();
		if (isNullOrEmpty(model.getValueAt(firstRow, 2))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 3))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 4))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 5))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 6))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 7))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 8))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 9))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 10))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 11))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 12))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 13))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 14))) {
			return false;
		}
		if (isNullOrEmpty(model.getValueAt(firstRow, 15))) {
			return false;
		}
		return true;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	//	private WeaponRecord getRecord(TableModelEvent e) {
	//		int firstChangedRow = e.getFirstRow();
	//		TKTableModel model = (TKTableModel) mTable.getModel();
	//		@SuppressWarnings("rawtypes")
	//		Vector<Vector> data = model.getDataVector();
	//		if (!data.isEmpty() && model.getRowCount() > firstChangedRow) {
	//			Vector<Object> row = data.get(firstChangedRow);
	//			if (row != null) {
	//				WeaponRecord record = new WeaponRecord(row);
	//				return record;
	//			}
	//		}
	//		return null;
	//	}

	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	ArrayList<WeaponRecord> getRecordsToAdd() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount() - 1;
		ArrayList<WeaponRecord> records = new ArrayList<>(rows);
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				WeaponRecord record = new WeaponRecord(row);
				records.add(record);
			}
		}

		return records;
	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
