/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.marketPlace;

import com.starfyre1.ToolKit.TKTableModel;

public class MarketPlaceEntryTableModel extends TKTableModel {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MarketPlaceEntryTableModel}.
	 *
	 * @param columnHeaderNames
	 * @param columnHeaderTooltips
	 * @param size
	 */
	public MarketPlaceEntryTableModel(String[] columnHeaderNames, String[] columnHeaderTooltips, int size) {
		super(columnHeaderNames, columnHeaderTooltips, size);
	}

	/**
	 * Creates a new {@link MarketPlaceEntryTableModel}.
	 *
	 * @param data
	 * @param columnHeaderNames
	 * @param columnHeaderTooltips
	 */
	public MarketPlaceEntryTableModel(Object[][] data, String[] columnHeaderNames, String[] columnHeaderTooltips) {
		super(data, columnHeaderNames, columnHeaderTooltips);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
