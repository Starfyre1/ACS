/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.GUI.metal.MetalCellEditor;
import com.starfyre1.GUI.metal.MetalCellRenderer;
import com.starfyre1.GUI.metal.MetalTableModel;
import com.starfyre1.ToolKit.TKRowFilter;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.MetalRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.WeaponList;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class TreasureDisplay extends TKTitledDisplay implements ActionListener, TableModelListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		TREASURE_TITLE			= "Party Treasure";																																																																																																																		//$NON-NLS-1$
	private static final String		FILTER					= "Filter";																																																																																																																				//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "#", "Name", "Material", "Type", "Handed", "STR", "DEX", "lbs", "Length", "Asp", "Break", "HB", "1HD", "2HD", "Cost" };																																																																																								//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Name", "<html>Material:<br>Iron<br>Ang<br>Borang<br>Ardacer<br>Ethru<br>Ithilnur<br>Mithril<br>Laen<br>Eog<br>Tasarung</html>", "<html>Type:<br>Bladed<br>Blunt<br>Miscellaneous<br>Thrown<br>Bows</html>", "<html>Handed:<br>1=one handed<br>1, 2=One or Two handed<br>2=Two handed only<br>3=Mounted and Charging only", "Strength", "Dexterity", "Encumbrance", "Weapon Length", "Attack Speed", "Break", "Hit Bonus", "1 Handed Damage", "2 Handed Damage", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JPanel					mFilterPanel;
	private TKTable					mTable;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TreasureDisplay(CharacterSheet owner) {
		super(owner, TREASURE_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	protected Component createDisplay() {
		// This is the full equipment list in the Market Place
		Object[] master = WeaponList.getWeaponCombinedList();
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

		mTable.getColumnModel().getColumn(0).setMinWidth(30); // Count
		mTable.getColumnModel().getColumn(1).setMinWidth(55); // Equipped
		mTable.getColumnModel().getColumn(2).setMinWidth(100); // Name
		mTable.getColumnModel().getColumn(3).setMinWidth(70); // Metal - give it enough room for popup
		mTable.getColumnModel().getColumn(4).setMinWidth(70); // Type
		mTable.getColumnModel().getColumn(15).setMinWidth(50); // Cost

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
	protected void loadDisplay() {
	}

	public void clearRecords() {
		// mEntries = new ArrayList<>();
		//		updatePreviewPanel();

	}

	@Override
	public void tableChanged(TableModelEvent e) {
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
