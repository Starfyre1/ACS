/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class InnateAbilitiesDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String	INNATE_ABBILITIES_TITLE	= "Innate Abilities";	//$NON-NLS-1$

	static final String	NAME_LABEL				= "Name";				//$NON-NLS-1$
	static final String	VALUE_LABEL				= "Value";				//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public InnateAbilitiesDisplay(CharacterSheet owner) {
		super(owner, INNATE_ABBILITIES_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		//DW create data model
		JTable table = new JTable(7, 2);

		JTableHeader tableHeader = table.getTableHeader();
		TableColumnModel tcm = tableHeader.getColumnModel();

		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue(NAME_LABEL);

		tc = tcm.getColumn(1);
		tc.setHeaderValue(VALUE_LABEL);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(table.getPreferredSize());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	protected void loadDisplay() {
		// DW Load from disk
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
