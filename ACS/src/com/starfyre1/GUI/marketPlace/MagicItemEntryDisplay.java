/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.purchasedGear.magicItems.MagicItemsDisplay;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.MagicItemRecord;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

public class MagicItemEntryDisplay extends MagicItemsDisplay implements TableModelListener {

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
	 * Creates a new {@link MagicItemEntryDisplay}.
	 *
	 * @param owner
	 */
	public MagicItemEntryDisplay(Object owner) {
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

		mTable.removeColumn(mTable.getColumnModel().getColumn(1));
		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

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
			//			String charges = (String) model.getValueAt(row, 3);
			//			float cost = TKStringHelpers.getFloatValue((String) model.getValueAt(row, 4), 0.0f);

			//			ArmorRecord record = new ArmorRecord(0, false, name, MetalList.getMetalID(metal), protectionType, protectionAmount, encumbrance, absorption, bonus, missileAbsorption, strengthRequirement, 0, 0, cost);
			// 			System.out.println(row + " :: " + record);
			if (!hasEmptyRow()) {
				model.addRow(new Object[6]);
			}

			model.addTableModelListener(this);

		}
	}

	private boolean isNullOrEmpty(Object value) {
		return value == null || ((String) value).isEmpty();
	}

	private boolean hasEmptyRow() {
		TableModel model = mTable.getModel();
		int rows = model.getRowCount();
		for (int i = 0; i < rows; i++) {
			if (isNullOrEmpty(model.getValueAt(i, 2)) || isNullOrEmpty(model.getValueAt(i, 3)) || isNullOrEmpty(model.getValueAt(i, 4))) {
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
		return true;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	ArrayList<MagicItemRecord> getRecordsToAdd() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount() - 1;
		ArrayList<MagicItemRecord> records = new ArrayList<>(rows);
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				MagicItemRecord record = new MagicItemRecord(row);
				records.add(record);
			}
		}

		return records;
	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
