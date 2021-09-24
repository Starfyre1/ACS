/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.MetalRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.WeaponList;
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

public class WeaponDisplay extends TKTitledDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		WEAPON_TITLE			= "Weapons";																																																	//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "#", "Equipped", "Name", "Material", "Type", "Handed", "STR", "DEX", "Weight", "Length", "Asp", "Break", "H.B.", "1HD", "2HD", "Cost" };																	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Material", "Type", "Handed", "Strength", "Dexterity", "Encumbrance", "Weapon Length", "Attack Speed", "Break", "Hit Bonus", "1 Handed Damage", "2 Handed Damage", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$

	private static final String		FILTER					= "Filter";																																																		//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel					mFilterPanel;
	private TKTable					mTable;
	private float					mCost;
	private ArrayList<WeaponRecord>	mEquippedWeapons;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public WeaponDisplay(Object owner) {
		super(owner, WEAPON_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is on the Equipment Sheet and is loaded from the character file
		if (getOwner() instanceof CharacterSheet) {
			WeaponList list = ((CharacterSheet) getOwner()).getWeaponList();
			if (list != null) {
				ArrayList<WeaponRecord> records = list.getRecords();
				if (!records.isEmpty()) {
					Object[] master = new Object[records.size()];
					Object[][] data = new Object[master.length][16];

					for (int i = 0; i < master.length; i++) {
						WeaponRecord record = records.get(i);
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
			Object[] master = WeaponList.getWeaponMasterList();
			Object[][] data = new Object[master.length][16];

			for (int i = 0; i < master.length; i++) {
				WeaponRecord record = (WeaponRecord) master[i];
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

			//			mTable.setFillsViewportHeight(true);
		} else {
			// This is the equipped weapons in the information section on the main page
			// This is loaded from Equipment Sheet
			mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
			mTable.setPreferredScrollableViewportSize(CharacterSheet.CHARACTER_TAB_TABLE_SIZE);
			// remove in reverse order... as the column numbers will change
			mTable.removeColumn(mTable.getColumnModel().getColumn(15));
			mTable.removeColumn(mTable.getColumnModel().getColumn(7));
			mTable.removeColumn(mTable.getColumnModel().getColumn(6));
			mTable.removeColumn(mTable.getColumnModel().getColumn(1));
			mTable.removeColumn(mTable.getColumnModel().getColumn(0));
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
		CharacterSheet owner = (CharacterSheet) getOwner();
		WeaponList weapons;
		if (owner != null) {
			// This is the weapon equipment on the equipment tab
			weapons = owner.getWeaponList();
		} else {
			// this is the Weapon information on the character tab
			// equipped items (on the equipment tab) will show up here
			weapons = ACS.getInstance().getCharacterSheet().getWeaponList();
			mEquippedWeapons = new ArrayList<WeaponRecord>();
		}
		if (weapons != null) {
			ArrayList<WeaponRecord> records = weapons.getRecords();
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (WeaponRecord record : records) {
				if (owner == null) {
					if (((Boolean) record.getRecord(1)).booleanValue()) {
						mEquippedWeapons.add(record);
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
	/** @return The equippedWeapons. */
	public ArrayList<WeaponRecord> getEquippedWeapons() {
		return mEquippedWeapons;
	}

	public JPanel getFilterPanel() {
		return mFilterPanel;
	}

	public ArrayList<WeaponRecord> getPurchasedRows() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		@SuppressWarnings("rawtypes")
		Vector<Vector> data = model.getDataVector();
		int rows = model.getRowCount();
		mCost = 0;
		ArrayList<WeaponRecord> records = new ArrayList<>(rows);
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
					WeaponRecord record = new WeaponRecord(row);
					records.add(record);
				}
			}
		}

		return records;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		WeaponRecord record = WeaponList.getMasterWeaponRecord(row);
		if (e.getColumn() == 3) {
			TableModel model = mTable.getModel();
			MetalRecord metal = (MetalRecord) model.getValueAt(row, e.getColumn());

			model.setValueAt(Float.valueOf(record.getEncumbrance() * metal.getEnumbrance()), row, 8); // Encumbrance
			model.setValueAt(Integer.valueOf(record.getAttackSpeed() + metal.getASP()), row, 10); // Absorption
			model.setValueAt(Integer.valueOf(metal.getBreak(record.getWeaponBreak())), row, 11); // Break
			model.setValueAt(Integer.valueOf(record.getHitBonus() + metal.getHitBonus()), row, 12); // Missile Absorption
			model.setValueAt(Integer.valueOf(record.getDamageOneHanded() > 0 ? record.getDamageOneHanded() + metal.getDamage() : 0), row, 13); // Missile Absorption
			model.setValueAt(Integer.valueOf(record.getDamageTwoHanded() > 0 ? record.getDamageTwoHanded() + metal.getDamage() : 0), row, 14); // Missile Absorption
			model.setValueAt(Float.valueOf(record.getCost() * metal.getCost()), row, 15); // Missile Absorption
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
					//					if (cost != null && count != null) {
					mCost += cost * count;
					//					}
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
