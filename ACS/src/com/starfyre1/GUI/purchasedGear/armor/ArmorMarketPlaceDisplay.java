/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.armor;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.MarketPlace;
import com.starfyre1.GUI.metal.MetalCellEditor;
import com.starfyre1.GUI.metal.MetalCellRenderer;
import com.starfyre1.GUI.metal.MetalTableModel;
import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataModel.MetalRecord;
import com.starfyre1.dataset.ArmorList;
import com.starfyre1.dataset.MetalList;
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
import javax.swing.table.TableModel;

public class ArmorMarketPlaceDisplay extends ArmorDisplay implements TableModelListener, ListSelectionListener {
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

	public ArmorMarketPlaceDisplay(Object owner) {
		super(owner);

		mMarketModel = (TKTableModel) mTable.getModel();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is the full equipment list in the Market Place
		Object[] master = ArmorList.getArmorMasterList();
		Object[][] data = new Object[master.length][14];

		for (int i = 0; i < master.length; i++) {
			ArmorRecord record = (ArmorRecord) master[i];
			if (record == null) {
				continue;
			}
			for (int index = 0; index < data[i].length; index++) {
				data[i][index] = record.getRecord(index);
			}
		}
		mTable = new TKTable(new MetalTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.MARKET_PLACE_TAB_TABLE_SIZE);
		mTable.setDefaultRenderer(MetalRecord.class, new MetalCellRenderer());
		mTable.setDefaultEditor(MetalRecord.class, new MetalCellEditor(MetalList.getRecords()));
		mTable.setRowHeight(20);
		mTable.getColumnModel().getColumn(3).setMinWidth(70); // Metal - give it enough room for popup

		mFilterPanel = new JPanel();
		JTextField filterField = TKRowFilter.createRowFilter(mTable);
		mFilterPanel.add(new JLabel(FILTER));
		mFilterPanel.add(filterField);

		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// not used
	}

	public void finalizeSelections() {
		mTable.getCellEditor().stopCellEditing();
	}
	
	
	public void swapTables() {

		TKTableModel ownedModel = (TKTableModel) ACS.getInstance().getCharacterSheet().getArmorOwnedTable().getModel();
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

	public ArrayList<ArmorRecord> getSelectedRows(boolean buying) {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<ArmorRecord> records = new ArrayList<>(rows);
		if (buying) {
			for (int i = 0; i < rows; i++) {
				Vector<Object> row = data.get(i);
				if (row != null) {
					// get rows that have a count > 0...
					Object element = row.get(0);
					if (element == null) {
						continue;
					}

					int count = element instanceof String ? //
									TKStringHelpers.getIntValue((String) element, 0) : //
									((Integer) element).intValue();

					if (count > 0) {
						ArmorRecord record = new ArmorRecord(row);
						records.add(record);
					}
				}
			}
		} else {
			int selectedRows[] = mTable.getSelectedRows();
			for (int selectedRow : selectedRows) {
				Vector<Object> row = data.get(selectedRow);
				ArmorRecord record = new ArmorRecord(row);
				records.add(record);
			}
		}

		return records;
	}

	private ArmorRecord getRecord(TableModelEvent e) {
		int firstChangedRow = e.getFirstRow();
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		if (!data.isEmpty() && model.getRowCount() > firstChangedRow) {
			Vector<Object> row = data.get(firstChangedRow);
			if (row != null) {
				ArmorRecord record = new ArmorRecord(row);
				return record;
			}
		}
		return null;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		ArmorRecord record = getRecord(e);
		if (e.getColumn() == 3) {
			TableModel model = mTable.getModel();
			MetalRecord metal = (MetalRecord) model.getValueAt(row, e.getColumn());

			record = ArmorList.getMasterArmorRecord(record.getName());

			model.setValueAt(Integer.valueOf(record.getProtectionAmount() + metal.getARBonus()), row, 5); // ProtectionAmount
			model.setValueAt(Float.valueOf(record.getEncumbrance() * metal.getEnumbrance()), row, 6); // Encumbrance
			model.setValueAt(Integer.valueOf(record.getAbsorption() + metal.getAbsorb()), row, 7); // Absorption
			model.setValueAt(Integer.valueOf(record.getMissileAbsorption() + metal.getAbsorb()), row, 9); // Missile Absorption
			model.setValueAt(Integer.valueOf(metal.getBreak(record.getBreak())), row, 12); // Break
			model.setValueAt(Float.valueOf(record.getCost() * metal.getCost()), row, 13); // Missile Absorption
		}
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
			market.updateButtons(market.canAfford(mCost));
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
