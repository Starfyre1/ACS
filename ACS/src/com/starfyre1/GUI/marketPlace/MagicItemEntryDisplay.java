/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.purchasedGear.magicItems.MagicItemsDisplay;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataset.MagicItemList;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
