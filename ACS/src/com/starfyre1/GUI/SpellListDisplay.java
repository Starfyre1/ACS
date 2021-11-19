
/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SpellListDisplay extends TKTitledDisplay implements ActionListener, Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		SPELL_LIST_TITLE			= "Athri Spell List";												//$NON-NLS-1$

	public static final String		FILE_SECTTION_START_KEY		= "SPELL_LIST_SECTTION_START";										//$NON-NLS-1$
	public static final String		FILE_SECTTION_END_KEY		= "SPELL_LIST_SECTTION_END";										//$NON-NLS-1$
	public static final String		MAGICAL_AREA_KEY			= "MAGICAL_AREA_KEY";												//$NON-NLS-1$

	private static final String		MAGIC_AREA_LABEL			= "Magic Area";														//$NON-NLS-1$
	private static final String		EXPERIENCE_IN_AREA_LABEL	= "Experience in Area";												//$NON-NLS-1$
	private static final String		LEVEL_IN_AREA_LABEL			= "Level in Area";													//$NON-NLS-1$
	private static final String		LEARN_SPELL					= "Learn Spell";													//$NON-NLS-1$

	private static final String		SELECT_MAGIC_AREA			= "Select Magic Area";												//$NON-NLS-1$

	private static final String		POWER_LABEL					= "Power";															//$NON-NLS-1$
	private static final String		SPELL_LABEL					= "Spell";															//$NON-NLS-1$
	private static final String		CASTING_SPEED_LABEL			= "Csp";															//$NON-NLS-1$
	private static final String		NOTES_LABEL					= "Notes";															//$NON-NLS-1$

	private static final ImageIcon	icon						= new ImageIcon("../ACS/src/com/starfyre1/Images/ImagePlus.png");	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu				mAreaPopup;
	private JTable					mTable;
	private JButton					mNewSpellButton				= new JButton(icon);

	private Color					mOldColor					= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellListDisplay(CharacterSheet owner) {
		super(owner, null);

		add(createHeader(), BorderLayout.PAGE_START);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	private Component createHeader() {
		JPanel headerWrapper = new JPanel();
		headerWrapper.setLayout(new BoxLayout(headerWrapper, BoxLayout.Y_AXIS));

		JPanel wrapper = new JPanel();

		JLabel areaLabel = new JLabel(MAGIC_AREA_LABEL, SwingConstants.RIGHT);
		mAreaPopup = new TKPopupMenu(generateMagicAreaPopup());

		JLabel experienceLabel = new JLabel(EXPERIENCE_IN_AREA_LABEL, SwingConstants.RIGHT);
		JTextField experienceField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);

		JLabel levelLabel = new JLabel(LEVEL_IN_AREA_LABEL, SwingConstants.RIGHT);
		JTextField levelField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);

		JLabel newSpell = new JLabel(LEARN_SPELL, SwingConstants.RIGHT);
		mNewSpellButton.setOpaque(true);
		mNewSpellButton.setPreferredSize(new Dimension(25, 25));
		//		mNewSpellButton.setBorder(new Border(Color.BLACK, 1));
		mNewSpellButton.setFocusable(false);
		mNewSpellButton.setEnabled(!SELECT_MAGIC_AREA.equals(getMagicArea()));
		mNewSpellButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				if (mNewSpellButton.isEnabled()) {
					mOldColor = mNewSpellButton.getBackground();
					mNewSpellButton.setBackground(Color.GRAY);
				}
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mNewSpellButton.isEnabled()) {
					if (mOldColor != null) {
						mNewSpellButton.setBackground(mOldColor);
					}
				}
			}
		});
		mNewSpellButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String magicArea = getMagicArea();
				if (!SELECT_MAGIC_AREA.equals(magicArea)) {
					new SpellSelector(((CharacterSheet) getOwner()).getFrame(), magicArea);
				}
			}
		});

		wrapper.add(areaLabel);
		wrapper.add(mAreaPopup);
		wrapper.add(experienceLabel);
		wrapper.add(experienceField);
		wrapper.add(levelLabel);
		wrapper.add(levelField);
		wrapper.add(newSpell);
		wrapper.add(mNewSpellButton);

		headerWrapper.add(new TKPageTitleLabel(SPELL_LIST_TITLE));
		headerWrapper.add(wrapper);

		return headerWrapper;
	}

	private JMenu generateMagicAreaPopup() {
		JMenu popupMenu = TKPopupMenu.createMenu(SELECT_MAGIC_AREA);

		MageList mages = ACS.getInstance().getMages();
		PriestList priests = ACS.getInstance().getPriests();

		ArrayList<String> groups = mages.getMagesGroupsList();
		groups.addAll(priests.getPriestsGroupsList());

		ArrayList<String> names = mages.getMagesNamesList();
		names.addAll(priests.getPriestsNamesList());

		ArrayList<ClassesRecord> records = mages.getRecordsList();
		records.addAll(priests.getRecordsList());

		ArrayList<JMenu> menus = new ArrayList<>();
		for (int i = 0; i < groups.size(); i++) {
			JMenu menu = new JMenu(groups.get(i));
			menus.add(menu);
			popupMenu.add(menu);
		}

		for (JMenu element : menus) {
			for (int i = 0; i < names.size(); i++) {
				if (element.getText().equals(records.get(i).getGroup())) {
					JMenuItem menuItem = new JMenuItem(names.get(i));
					menuItem.addActionListener(this);
					element.add(menuItem);
				}
			}
		}

		for (int i = 0; i < names.size(); i++) {
			if (records.get(i).getGroup() == null) {
				JMenuItem menuItem = new JMenuItem(names.get(i));
				menuItem.addActionListener(this);
				popupMenu.add(menuItem, 0);
			}
		}

		JMenuItem menuItem = new JMenuItem(SELECT_MAGIC_AREA);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem, 0);

		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof JMenuItem) {
			String text = ((JMenuItem) source).getText();

			if (ACS.getInstance().getClasses().getClassesNamesList().contains(text)) {
				((CharacterSheet) getOwner()).updateRecords();
			}
			boolean enable = !SELECT_MAGIC_AREA.equals(text);
			mTable.setEnabled(enable);
			mNewSpellButton.setEnabled(enable);
		}
	}

	@Override
	protected Component createDisplay() {

		mTable = new JTable(64, 9);

		JTableHeader tableHeader = mTable.getTableHeader();
		TableColumnModel tcm = tableHeader.getColumnModel();

		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue(POWER_LABEL);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc = tcm.getColumn(1);
		tc.setHeaderValue(SPELL_LABEL);

		tc = tcm.getColumn(2);
		tc.setHeaderValue(CASTING_SPEED_LABEL);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc = tcm.getColumn(3);
		tc.setHeaderValue(NOTES_LABEL);

		tc = tcm.getColumn(4);
		tc.setHeaderValue(null);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc = tcm.getColumn(5);
		tc.setHeaderValue(POWER_LABEL);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc = tcm.getColumn(6);
		tc.setHeaderValue(SPELL_LABEL);

		tc = tcm.getColumn(7);
		tc.setHeaderValue(CASTING_SPEED_LABEL);
		tc.setMaxWidth(CharacterSheet.CELL_SMALL_MAX_WIDTH);

		tc = tcm.getColumn(8);
		tc.setHeaderValue(NOTES_LABEL);

		mTable.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	protected void loadDisplay() {
		// DW Read from file
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getMagicArea() {
		return mAreaPopup.getSelectedItem();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
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
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());
		br.write(MAGICAL_AREA_KEY + TKStringHelpers.SPACE + getMagicArea().replace(" ", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		value = value.replace("~", " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (key.equals(MAGICAL_AREA_KEY)) {
			mAreaPopup.selectPopupMenuItem(value);
			mTable.setEnabled(!SELECT_MAGIC_AREA.equals(value));
		}
	}
}
