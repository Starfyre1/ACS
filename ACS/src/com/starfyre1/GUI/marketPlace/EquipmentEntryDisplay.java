/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.purchasedGear.equipment.EquipmentDisplay;
import com.starfyre1.ToolKit.TKTable;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
		// Nothing to do
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
