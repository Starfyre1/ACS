/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ArmorDisplay extends TKTitledDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		ARMOR_TITLE				= "Armor";																																																																																												//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "#", "Equipped", "Name", "Material", "Type", "A.R.", "Weight", "Absorb", "Bonus", "Missile", "Str Required", "Parry", "Break", "Cost" };																																																											//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Material", "<html>Protection Type<br>0=Head-Top<br>1=Head-Side<br>2=Head-Face<br>3=Neck<br>4=Torso<br>5=Arms<br>6=Hands<br>7=Legs<br>8=Feet/Calves<br>9=Shield</html>", "Protection Amount", "Encumbrance", "Absorption", "<html>50% Chance Bonus<br>to Absorption</html>", "Missile Absorption", "Strength Requirement", "Parry", "Break", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$

	private static final String		FILTER					= "Filter";																																																																																												//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel					mFilterPanel;
	private TKTable					mTable;
	private float					mCost;
	private ArrayList<ArmorRecord>	mEquippedArmor;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public ArmorDisplay(Object owner) {
		super(owner, ARMOR_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is on the Equipment Sheet and is loaded from the character file
		if (getOwner() instanceof CharacterSheet) {
			ArmorList list = ((CharacterSheet) getOwner()).getArmorList();
			if (list != null) {
				ArrayList<ArmorRecord> records = list.getRecords();
				if (!records.isEmpty()) {
					Object[] master = new Object[records.size()];
					Object[][] data = new Object[master.length][14];

					for (int i = 0; i < master.length; i++) {
						ArmorRecord record = records.get(i);
						if (record == null) {
							continue;
						}
						for (int index = 0; index < data[i].length; index++) {
							data[i][index] = record.getRecord(index);
						}
					}
					mTable = new TKTable(new TKTableModel(data, COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS));
					mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
					//				mTable.setFillsViewportHeight(true);
				}
			} else {
				mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
				mTable.setPreferredScrollableViewportSize(CharacterSheet.EQUIPMENT_TAB_TABLE_SIZE);
				// mTable.setFillsViewportHeight(true);
			}
		} else if (getOwner() instanceof MarketPlace) {
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

			// mTable.setFillsViewportHeight(true);
		} else {
			// This is the equipped weapons in the information section on the main page
			// This is loaded from Equipment Sheet
			mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
			mTable.setPreferredScrollableViewportSize(CharacterSheet.CHARACTER_TAB_TABLE_SIZE);
			// remove in reverse order... as the column numbers will change
			mTable.removeColumn(mTable.getColumnModel().getColumn(13));
			mTable.removeColumn(mTable.getColumnModel().getColumn(10));
			mTable.removeColumn(mTable.getColumnModel().getColumn(1));
			mTable.removeColumn(mTable.getColumnModel().getColumn(0));

			// mTable.setFillsViewportHeight(true);
		}
		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	protected void loadDisplay() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		ArmorList armor;
		if (owner != null) {
			// This is the armor equipment on the equipment tab
			armor = owner.getArmorList();
		} else {
			// this is the Armor information on the character tab
			// equipped items (on the equipment tab) will show up here
			armor = ACS.getInstance().getCharacterSheet().getArmorList();
			mEquippedArmor = new ArrayList<ArmorRecord>();
		}
		if (armor != null) {
			ArrayList<ArmorRecord> records = armor.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (ArmorRecord record : records) {
				if (owner == null) {
					if (((Boolean) record.getRecord(1)).booleanValue()) {
						mEquippedArmor.add(record);
						model.addRow(record.getRecord());
					}
				} else {
					model.addRow(record.getRecord());
				}
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The equippedArmor. */
	public ArrayList<ArmorRecord> getEquippedArmor() {
		return mEquippedArmor;
	}

	public JPanel getFilterPanel() {
		return mFilterPanel;
	}

	public ArrayList<ArmorRecord> getPurchasedRows() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<ArmorRecord> records = new ArrayList<>(rows);
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
					ArmorRecord record = new ArmorRecord(row);
					records.add(record);
				}
			}
		}

		return records;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		ArmorRecord record = ArmorList.getMasterArmorRecord(row);
		if (e.getColumn() == 3) {
			TableModel model = mTable.getModel();
			MetalRecord metal = (MetalRecord) model.getValueAt(row, e.getColumn());

			model.setValueAt(Integer.valueOf(record.getProtectionAmount() + metal.getARBonus()), row, 5); // ProtectionAmount
			model.setValueAt(Float.valueOf(record.getEncumbrance() * metal.getEnumbrance()), row, 6); // Encumbrance
			model.setValueAt(Integer.valueOf(record.getAbsorption() + metal.getAbsorb()), row, 7); // Absorption
			model.setValueAt(Integer.valueOf(record.getBonus() + metal.getAbsorb()), row, 8); // Bonus
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
			market.updateButtons(mCost);
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
