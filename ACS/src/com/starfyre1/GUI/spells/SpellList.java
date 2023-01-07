
/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.spells;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.classes.common.SpellUser;
import com.starfyre1.dataset.spells.SpellRecord;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SpellList extends JPanel implements TableModelListener, Savable, ListSelectionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String		FILE_SECTION_START_KEY	= "SPELL_LIST_START";												//$NON-NLS-1$
	public static final String		FILE_SECTION_END_KEY	= "SPELL_LIST_END";													//$NON-NLS-1$
	public static final String		SPELL_KEY				= "SPELL_KEY";														//$NON-NLS-1$
	public static final String		LEVEL_KEY				= "LEVEL_KEY";														//$NON-NLS-1$

	private static final String		LEVEL_LABEL				= "Level";															//$NON-NLS-1$
	private static final String		SPELL_LABEL				= "Spell";															//$NON-NLS-1$
	private static final String		CASTING_SPEED_LABEL		= "Csp";															//$NON-NLS-1$
	private static final String		NOTES_LABEL				= "Notes";															//$NON-NLS-1$
	private static final String[]	COLUMN_HEADER_NAMES		= { LEVEL_LABEL, SPELL_LABEL, CASTING_SPEED_LABEL, NOTES_LABEL };
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { LEVEL_LABEL, SPELL_LABEL, CASTING_SPEED_LABEL, NOTES_LABEL };

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	//	private JPanel					mFilterPanel;
	private TKTable					mTable;
	private ArrayList<SpellRecord>	mKnownSpells			= new ArrayList<>(128);

	private SpellDescriptionCard	mSpellDescriptionCard	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellList(String name) {
		super();
		setLayout(new BorderLayout());
		setName(name);

		add(createDisplay(), BorderLayout.CENTER);
		//		if (!name.equals(MagicAreaPopup.SELECT_MAGIC_AREA)) {
		//			addAllCommonSpells();
		//		}
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private TKTable getTable() {

		TKTable table = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
		table.setBorder(new LineBorder(Color.BLACK));

		JTableHeader tableHeader1 = table.getTableHeader();
		TableColumnModel tcm1 = tableHeader1.getColumnModel();

		TableColumn tc1 = tcm1.getColumn(0);
		tc1.setHeaderValue(LEVEL_LABEL);
		tc1.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc1 = tcm1.getColumn(1);
		tc1.setHeaderValue(SPELL_LABEL);

		tc1 = tcm1.getColumn(2);
		tc1.setHeaderValue(CASTING_SPEED_LABEL);
		tc1.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc1 = tcm1.getColumn(3);
		tc1.setHeaderValue(NOTES_LABEL);

		table.setEnabled(true);
		table.setFillsViewportHeight(true);
		table.getModel().addTableModelListener(this);
		table.getSelectionModel().addListSelectionListener(this);

		return table;
	}

	protected Component createDisplay() {
		mTable = getTable();
		mSpellDescriptionCard = new SpellDescriptionCard(null);
		mSpellDescriptionCard.setPreferredSize(mTable.getPreferredScrollableViewportSize());
		mSpellDescriptionCard.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		JScrollPane sp1 = new JScrollPane(mTable);
		sp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(sp1, BorderLayout.LINE_START);
		wrapper.add(mSpellDescriptionCard, BorderLayout.LINE_END);

		JScrollPane scrollPane = new JScrollPane(wrapper);
		scrollPane.setBorder(new EmptyBorder(getInsets()));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		return scrollPane;
	}

	public void addToKnownSpells(SpellRecord record) {
		if (mKnownSpells.isEmpty()) {
			addAllCommonSpells();
		}
		mKnownSpells.add(record);
		TKTableModel model = (TKTableModel) mTable.getModel();
		model.addRow(record.getRecord());
	}

	protected void loadDisplay() {
		// DW Read from file
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// This is called when row is added to table (tableChanged)
		// DW Do something... update tables...
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int viewRow = mTable.getSelectedRow();

		if (!e.getValueIsAdjusting() && viewRow != -1) {
			int nameIndex = 1; // name of spell

			// Access table row using modelRow not viewRow (view rows can change location)
			int modelRow = mTable.convertRowIndexToModel(viewRow);
			String name = (String) mTable.getModel().getValueAt(modelRow, nameIndex);
			mSpellDescriptionCard.setDescriptionText(name);
		} else {
			mSpellDescriptionCard.setDescriptionText(null);
		}

		mSpellDescriptionCard.revalidate();
		mSpellDescriptionCard.repaint();
	}

	public void clearRecords() {
		TKTableModel model = (TKTableModel) mTable.getModel();
		model.setRowCount(0);
		mKnownSpells = new ArrayList<>(128);
	}

	private void addAllCommonSpells() {
		mKnownSpells.addAll(0, SpellUser.getCommonSpells());

		for (SpellRecord record : mKnownSpells) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.addRow(record.getRecord());
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public ArrayList<SpellRecord> getKnownSpells() {
		return mKnownSpells;
	}

	public boolean isSpellKnown(SpellRecord record) {
		return mKnownSpells.contains(record);
	}

	public boolean isKnownSpellsEmpty() {
		ArrayList<SpellRecord> commonList = SpellUser.getCommonSpells();
		return commonList.containsAll(mKnownSpells);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	private int		mLevel;		// this is used for serialization only
	private String	mAreaName;	// this is used for serialization only

	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					while (tokenizer.hasMoreTokens()) {
						value += " " + tokenizer.nextToken(); //$NON-NLS-1$
					}
					setKeyValuePair(key, value);
				}
			}
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());

		for (SpellRecord record : mKnownSpells) {
			if (record.getLevel() != -1) {
				br.write(TKStringHelpers.TAB + LEVEL_KEY + TKStringHelpers.SPACE + record.getLevel() + System.lineSeparator());
				br.write(TKStringHelpers.TAB + SPELL_KEY + TKStringHelpers.SPACE + record.getName() + System.lineSeparator());
			}
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (key.equals(LEVEL_KEY)) {
			mLevel = TKStringHelpers.getIntValue(value, 0);
		} else if (key.equals(SPELL_KEY)) {
			mAreaName = ACS.getInstance().getCharacterSheet().getSpellListDisplay().getMagicArea();
			SpellUser spellUser = (SpellUser) ClassList.getCharacterClass(mAreaName);
			SpellRecord spell = spellUser.getSpellRecord(mLevel, value);
			if (spell != null) {
				addToKnownSpells(spell);
			}
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
