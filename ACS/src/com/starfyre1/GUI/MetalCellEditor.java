/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.dataModel.MetalRecord;
import com.starfyre1.dataset.MetalList;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class MetalCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private MetalRecord			mRecord;
	private List<MetalRecord>	mList;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MetalCellEditor(List<MetalRecord> records) {
		mList = records;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<String> combo = (JComboBox<String>) e.getSource();
		mRecord = MetalList.getMetalRecord((String) combo.getSelectedItem());
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public Object getCellEditorValue() {
		return mRecord;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof MetalRecord) {
			mRecord = (MetalRecord) value;
		}

		JComboBox<String> combo = new JComboBox<String>();

		for (MetalRecord record : mList) {
			combo.addItem(record.getName());
		}

		combo.setSelectedItem(mRecord);
		combo.addActionListener(this);

		if (isSelected) {
			combo.setBackground(table.getSelectionBackground());
		} else {
			combo.setBackground(table.getBackground());
		}

		return combo;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
