/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.equipment;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.marketPlace.MarketPlace;
import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.dataset.EquipmentList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class EquipmentMarketPlaceDisplay extends EquipmentDisplay implements TableModelListener, ListSelectionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	FILTER	= "Filter";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel				mFilterPanel;
	private TKTable				mTable;
	private TKTableModel		mMarketModel;
	private float				mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public EquipmentMarketPlaceDisplay(Object owner) {
		super(owner);

		mMarketModel = (TKTableModel) mTable.getModel();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is the full equipment list in the Market Place
		Object[] master = EquipmentList.getEquipmentCombinedList();
		Object[][] data = new Object[master.length][6];

		for (int i = 0; i < master.length; i++) {
			EquipmentRecord record = (EquipmentRecord) master[i];
			if (record == null) {
				continue;
			}
			for (int index = 0; index < data[i].length; index++) {
				data[i][index] = record.getRecord(index);
			}
		}
		mTable = new TKTable(new TKTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);
		mTable.getModel().addTableModelListener(this);

		mFilterPanel = new JPanel();
		JTextField filterField = TKRowFilter.createRowFilter(mTable);
		mFilterPanel.add(new JLabel(FILTER));
		mFilterPanel.add(filterField);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// not used
	}

	public void swapTables() {
		TKTableModel ownedModel = (TKTableModel) ACS.getInstance().getCharacterSheet().getEquipmentOwnedTable().getModel();
		ownedModel.addTableModelListener(this);
		mTable.getSelectionModel().addListSelectionListener(this);

		if (((MarketPlace) getOwner()).isCharacterBuying()) {
			mTable.setModel(mMarketModel);
			ownedModel.removeTableModelListener(this);
			mTable.getSelectionModel().removeListSelectionListener(this);
		} else {
			mTable.setModel(ownedModel);
		}
		mTable.invalidate();
		mTable.revalidate();
		mTable.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		mTable.getSelectedRow();

		MarketPlace market = MarketPlace.getInstance();
		market.updateButtons(mTable.getSelectedRow() > -1);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public JPanel getFilterPanel() {
		return mFilterPanel;
	}

	public ArrayList<EquipmentRecord> getSelectedRows(boolean buying) {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<EquipmentRecord> records = new ArrayList<>(rows);
		if (buying) {
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
						EquipmentRecord record = EquipmentList.getEquipmentRecord(i).clone();
						record.setCount(count);
						record.setEquipped(((Boolean) row.get(1)).booleanValue());
						records.add(record);
					}
				}
			}
		} else {
			int selectedRows[] = mTable.getSelectedRows();
			for (int selectedRow : selectedRows) {
				Vector<Object> row = data.get(selectedRow);
				EquipmentRecord record = new EquipmentRecord(row);
				records.add(record);
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
					Object element = row.get(row.size() - 2);
					if (element == null) {
						continue;
					}
					float cost;
					if (element instanceof String) {
						cost = TKStringHelpers.getFloatValue((String) element, 0f);
					} else {
						cost = ((Float) element).floatValue();
					}

					element = row.get(0);
					if (element == null) {
						continue;
					}
					int count;
					if (element instanceof String) {
						count = TKStringHelpers.getIntValue((String) element, 0);
					} else {
						count = ((Integer) element).intValue();
					}
					mCost += cost * count;
				}
			}
			market.setDisplayableCost(market.getDisplayableCost(mCost, true));
			market.updateButtons(market.canAfford(mCost));
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
