/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataset.MagicItemList;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class MagicItemsDisplay extends TKTitledDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		MAGIC_ITEMS_TITLE		= "Magic Items";										//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "Count", "Equipped", "Name", "Charges", "Cost" };	// , "modify" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Charges", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	private static final String		FILTER					= "Filter";												//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel					mFilterPanel;
	private TKTable					mTable;
	private float					mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MagicItemsDisplay(Object owner) {
		super(owner, MAGIC_ITEMS_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		if (getOwner() instanceof CharacterSheet) {
			MagicItemList list = ((CharacterSheet) getOwner()).getMagicItemList();
			if (list != null) {
				ArrayList<MagicItemRecord> records = list.getRecords();
				if (!records.isEmpty()) {
					mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, records.size()));
					mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
					//				mTable.setFillsViewportHeight(true);
				}
			} else {
				mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
				mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
				//				mTable.setFillsViewportHeight(true);
			}
		} else {
			// This is the full equipment list in the Market Place
			//			new EquipmentList(null);
			Vector<String> header = new Vector<String>(5);
			header.copyInto(COLUMN_HEADER_NAMES);

			Object[] master = MagicItemList.getMagicItemsMasterList();
			Object[][] data = new Object[master.length][5];

			for (int i = 0; i < master.length; i++) {
				MagicItemRecord record = (MagicItemRecord) master[i];
				if (record == null) {
					continue;
				}
				for (int index = 0; index < data[i].length; index++) {
					data[i][index] = record.getRecord(index);
				}
			}
			mTable = new TKTable(new TKTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
			mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);

			mFilterPanel = new JPanel();
			JTextField filterField = TKRowFilter.createRowFilter(mTable);
			mFilterPanel.add(new JLabel(FILTER));
			mFilterPanel.add(filterField);

			//			mTable.setFillsViewportHeight(true);
		}
		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	protected void loadDisplay() {
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

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public JPanel getFilterPanel() {
		return mFilterPanel;
	}

	public ArrayList<MagicItemRecord> getPurchasedRows() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<MagicItemRecord> records = new ArrayList<>(rows);
		for (int i = 0; i < rows; i++) {
			Vector<Object> row = data.get(i);
			if (row != null) {
				// get rows that have a count > 0...
				Object element = row.get(0);
				if (element == null) {
					continue;
				}
				int count;
				if (element instanceof String) {
					count = TKStringHelpers.getIntValue((String) element, 0);
				} else {
					count = ((Integer) element).intValue();
				}
				if (count > 0) {
					MagicItemRecord record = MagicItemList.getMagicItemMasterList(i).clone();
					record.setCount(count);
					record.setEquipped(((Boolean) row.get(1)).booleanValue());
					records.add(record);
				}
			}
		}

		return records;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		updateCost();
	}

	private void updateCost() {
		MarketPlace market = MarketPlace.getInstance();
		if (market != null) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			@SuppressWarnings("rawtypes")
			Vector<Vector> data = model.getDataVector();
			int rows = model.getRowCount();
			mCost = 0;
			for (int i = 0; i < rows; i++) {
				Vector<Object> row = data.get(i);
				if (row != null) {
					Object element = row.get(row.size() - 1);
					if (element == null) {
						continue;
					}
					float cost;
					if (element instanceof String) {
						cost = TKStringHelpers.getFloatValue(((String) element).trim(), 0f);
					} else {
						cost = ((Float) element).floatValue();
					}

					element = row.get(0);
					if (element == null) {
						continue;
					}
					int count;
					if (element instanceof String) {
						count = TKStringHelpers.getIntValue(((String) element).trim(), 0);
					} else {
						count = ((Integer) element).intValue();
					}
					mCost += cost * count;
				}
			}
			market.setDisplayableCost(market.getDisplayableCost(mCost, true));
			market.updateButtons(mCost);
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
