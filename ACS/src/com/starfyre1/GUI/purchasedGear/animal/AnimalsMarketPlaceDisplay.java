/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.animal;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.marketPlace.MarketPlace;
import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataset.AnimalList;
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

public class AnimalsMarketPlaceDisplay extends AnimalsDisplay implements TableModelListener, ListSelectionListener {
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
	public AnimalsMarketPlaceDisplay(Object owner) {
		super(owner);

		mMarketModel = (TKTableModel) mTable.getModel();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is the full animals list in the Market Place
		Vector<String> header = new Vector<String>(11);
		header.copyInto(COLUMN_HEADER_NAMES);

		Object[] master = AnimalList.getAnimalCombinedList();
		Object[][] data = new Object[master.length][11];

		for (int i = 0; i < master.length; i++) {
			AnimalRecord record = (AnimalRecord) master[i];
			if (record == null) {
				continue;
			}
			for (int index = 0; index < data[i].length; index++) {
				data[i][index] = record.getRecord(index);
			}
		}
		mTable = new TKTable(new TKTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);

		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		mTable.getColumnModel().getColumn(1).setMinWidth(150); // Name
		mTable.getColumnModel().getColumn(5).setMinWidth(60); // Hits
		mTable.getColumnModel().getColumn(10).setMinWidth(200); // Notes

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

		TKTableModel ownedModel = (TKTableModel) ACS.getInstance().getCharacterSheet().getAnimalsOwnedTable().getModel();
		ownedModel.addTableModelListener(this);
		mTable.getSelectionModel().addListSelectionListener(this);

		if (((MarketPlace) getOwner()).isCharacterBuying()) {
			mTable.setModel(mMarketModel);
			ownedModel.removeTableModelListener(this);
			mTable.getSelectionModel().removeListSelectionListener(this);
			mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
			mTable.getColumnModel().getColumn(1).setMinWidth(150); // Name
			mTable.getColumnModel().getColumn(5).setMinWidth(60); // Hits
			mTable.getColumnModel().getColumn(10).setMinWidth(200); // Notes
		} else {
			mTable.setModel(ownedModel);
			mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
			mTable.getColumnModel().getColumn(1).setMinWidth(150); // Name
			mTable.getColumnModel().getColumn(5).setMinWidth(60); // Hits
			mTable.getColumnModel().getColumn(10).setMinWidth(200); // Notes

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

	public ArrayList<AnimalRecord> getSelectedRows(boolean buying) {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<AnimalRecord> records = new ArrayList<>(rows);
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
						AnimalRecord record = new AnimalRecord(row);
						record.setCount(count);
						records.add(record);
					}
				}
			}
		} else {
			int selectedRows[] = mTable.getSelectedRows();
			for (int selectedRow : selectedRows) {
				Vector<Object> row = data.get(selectedRow);
				AnimalRecord record = new AnimalRecord(row);
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
