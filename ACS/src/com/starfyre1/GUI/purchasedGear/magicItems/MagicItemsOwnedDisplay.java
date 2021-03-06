/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.magicItems;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataset.MagicItemList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class MagicItemsOwnedDisplay extends MagicItemsDisplay implements TableModelListener {
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
	public MagicItemsOwnedDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		MagicItemList list = ((CharacterSheet) getOwner()).getMagicItemList();
		if (list != null) {
			ArrayList<MagicItemRecord> records = list.getRecords();
			if (!records.isEmpty()) {
				mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, records.size()));
			}
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
		MagicItemList magicItems = ((CharacterSheet) getOwner()).getMagicItemList();
		if (magicItems != null) {
			ArrayList<MagicItemRecord> records = magicItems.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (MagicItemRecord record : records) {
				model.addRow(record.getRecord());
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		MagicItemRecord record = getRecord(e);
		if (e.getColumn() == 0 || e.getColumn() == 1) {
			ACS.getInstance().getCharacterSheet().getMagicItemList().updateMagicItemRecord(record);
			ACS.getInstance().getCharacterSheet().getTotalEncumbrance();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private MagicItemRecord getRecord(TableModelEvent e) {
		int firstChangedRow = e.getFirstRow();
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		if (!data.isEmpty() && model.getRowCount() > firstChangedRow) {
			Vector<Object> row = data.get(firstChangedRow);
			if (row != null) {
				MagicItemRecord record = new MagicItemRecord(row);
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
