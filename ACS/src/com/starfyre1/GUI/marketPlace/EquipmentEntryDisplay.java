/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.purchasedGear.equipment.EquipmentDisplay;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.dataset.EquipmentList;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

public class EquipmentEntryDisplay extends EquipmentDisplay implements TableModelListener {

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
	 * Creates a new {@link EquipmentEntryDisplay}.
	 *
	 * @param owner
	 */
	public EquipmentEntryDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is the user created equipment list in the create Equipment dialog
		Object[] master = EquipmentList.getEquipmentUserList();
		Object[][] data = new Object[master.length][6];

		for (int i = 0; i < master.length; i++) {
			EquipmentRecord record = (EquipmentRecord) master[i];
			if (record == null) {
				continue;
			}
			for (int index = 0; index < data[i].length; index++) {
				data[i][index] = record.getRecord(index);
			}
		}
		mTable = new TKTable(new MarketPlaceEntryTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);

		//		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		//		mTable.getColumnModel().getColumn(1).setMinWidth(55); // Equipped
		mTable.getColumnModel().getColumn(2).setMinWidth(150); // Name
		mTable.getColumnModel().getColumn(4).setMinWidth(50); // Cost
		mTable.getColumnModel().getColumn(5).setMinWidth(350); // Notes

		mTable.removeColumn(mTable.getColumnModel().getColumn(1));
		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		if (!hasEmptyRow()) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.addRow(new Object[6]);
		}

		mTable.getModel().addTableModelListener(this);
		return scrollPane;
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

			//			String name = (String) model.getValueAt(row, 2);
			//			float encumbrance = TKStringHelpers.getFloatValue((String) model.getValueAt(row, 3), 0.0f);
			//			float cost = TKStringHelpers.getFloatValue((String) model.getValueAt(row, 4), 0.0f);
			//			String notes = (String) model.getValueAt(row, 5);
			//			EquipmentRecord record = new EquipmentRecord(0, false, name, encumbrance, cost, notes);
			//			System.out.println(row + " :: " + record);
			if (!hasEmptyRow()) {
				model.addRow(new Object[6]);
			}

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
			if (isNullOrEmpty(model.getValueAt(i, 2)) || isNullOrEmpty(model.getValueAt(i, 3)) || isNullOrEmpty(model.getValueAt(i, 4)) || isNullOrEmpty(model.getValueAt(i, 5))) {
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
		return true;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	ArrayList<EquipmentRecord> getRecordsToAdd() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount() - 1;
		ArrayList<EquipmentRecord> records = new ArrayList<>(rows);
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				EquipmentRecord record = new EquipmentRecord(row);
				records.add(record);
			}
		}

		return records;
	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
