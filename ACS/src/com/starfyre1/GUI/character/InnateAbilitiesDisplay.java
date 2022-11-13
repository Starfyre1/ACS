/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataset.common.BaseClass;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class InnateAbilitiesDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String				INNATE_ABILITIES_TITLE	= "Innate Abilities";							//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "Not Displayed", "Name" };					//$NON-NLS-1$ //$NON-NLS-2$
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Not Displayed", "Innate Abilities Name" };	//$NON-NLS-1$ //$NON-NLS-2$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKTable					mTable;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public InnateAbilitiesDisplay(CharacterSheet owner) {
		super(owner, INNATE_ABILITIES_TITLE);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.ARMOR_TAB_TABLE_SIZE);

		mTable.removeColumn(mTable.getColumnModel().getColumn(0));

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// DW need to add success values

		HeaderRecord header = ((CharacterSheet) getOwner()).getHeaderRecord();
		if (header != null) {
			BaseClass classInfo = header.getCharacterClass();
			if (classInfo != null) {
				String[] master = classInfo.getInnateDisplayList();
				Arrays.sort(master);
				TKTableModel model = (TKTableModel) mTable.getModel();
				model.setRowCount(0);
				for (Object element : master) {
					Object[] temp = new Object[2];
					temp[1] = element;
					model.addRow(temp);
				}
			} else {
				clearRecords();
			}
		}

	}

	/**
	 *
	 */
	public void clearRecords() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		model.setRowCount(0);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
