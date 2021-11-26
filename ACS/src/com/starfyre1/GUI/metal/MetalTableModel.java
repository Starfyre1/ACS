/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.metal;

import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.MetalRecord;

public class MetalTableModel extends TKTableModel {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MetalTableModel(String[] columnHeaderNames, String[] columnHeaderTooltips, int size) {
		super(columnHeaderNames, columnHeaderTooltips, size);
	}

	public MetalTableModel(Object[][] data, String[] columnHeaderNames, String[] columnHeaderTooltips) {
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
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col == 3) {
			if (getColumnClass(col) == MetalRecord.class) {
				return true;
			}
		}
		return super.isCellEditable(row, col);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
