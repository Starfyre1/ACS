/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.animal;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataset.AnimalList;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AnimalsOwnedDisplay extends AnimalsDisplay {
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
	public AnimalsOwnedDisplay(Object owner) {
		super(owner);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		AnimalList list = ((CharacterSheet) getOwner()).getAnimalList();
		if (list != null) {
			ArrayList<AnimalRecord> records = list.getRecords();
			if (!records.isEmpty()) {
				mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, records.size()));
				mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
			}
		} else {
			mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
			mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
		}

		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		mTable.getColumnModel().getColumn(1).setMinWidth(150); // Name
		mTable.getColumnModel().getColumn(5).setMinWidth(60); // Hits
		mTable.getColumnModel().getColumn(10).setMinWidth(200); // Notes

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		AnimalList animals = ((CharacterSheet) getOwner()).getAnimalList();
		if (animals != null) {
			ArrayList<AnimalRecord> records = animals.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (AnimalRecord record : records) {
				model.addRow(record.getRecord());
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The table. */
	public TKTable getTable() {
		return mTable;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
