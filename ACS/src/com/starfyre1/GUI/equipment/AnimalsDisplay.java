/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.equipment;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.MarketPlace;
import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataset.AnimalList;

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

public class AnimalsDisplay extends TKTitledDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		ANIMALS_TITLE			= "Animals";																									//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "Count", "Animal", "Carry", "Move", "Travel", "Hits", "Hit Bonus", "Damage", "Armor", "Cost", "Notes" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Animal", "Carry", "Move", "Travel", "Hits", "Hit Bonus", "Damage", "Armor", "Cost", "Notes" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$

	private static final String		FILTER					= "Filter";																										//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel					mFilterPanel;
	private TKTable					mTable;
	private float					mCost;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public AnimalsDisplay(Object owner) {
		super(owner, ANIMALS_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		if (getOwner() instanceof CharacterSheet) {
			AnimalList list = ((CharacterSheet) getOwner()).getAnimalList();
			if (list != null) {
				ArrayList<AnimalRecord> records = list.getRecords();
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
			// if (getOwner() instanceof MarketPlace) {
			// This is the full equipment list in the Market Place
			//			new EquipmentList(null);
			Vector<String> header = new Vector<String>(11);
			header.copyInto(COLUMN_HEADER_NAMES);

			Object[] master = AnimalList.getAnimalMasterList();
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
	public void loadDisplay() {
		AnimalList animals = ((CharacterSheet) getOwner()).getAnimalList();
		if (animals != null) {
			ArrayList<AnimalRecord> records = animals.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (AnimalRecord record : records) {
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

	public ArrayList<AnimalRecord> getPurchasedRows() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<AnimalRecord> records = new ArrayList<>(rows);
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
					AnimalRecord record = AnimalList.getMasterAnimalRecord(i).clone();
					record.setCount(count);
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
			market.updateButtons(mCost);
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
