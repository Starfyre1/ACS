
/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.spells;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.component.MagicAreaPopup;
import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataset.spells.SpellRecord;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SpellListDisplay extends TKTitledDisplay implements ActionListener, ItemListener, Savable, DocumentListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SPELL_LIST_TITLE						= "Spell List";								//$NON-NLS-1$

	public static final String	FILE_SECTION_START_KEY					= "SPELL_LIST_SECTION_START";				//$NON-NLS-1$
	public static final String	FILE_SECTION_END_KEY					= "SPELL_LIST_SECTION_END";					//$NON-NLS-1$
	public static final String	SELECTED_MAGICAL_AREA_KEY				= "SELECTED_MAGICAL_AREA_KEY";				//$NON-NLS-1$
	public static final String	SELECTED_MAGICAL_AREA_EXPERIENCE_KEY	= "SELECTED_MAGICAL_AREA_EXPERIENCE_KEY";	//$NON-NLS-1$

	private static final String	MAGIC_AREA_LABEL						= "Magic Area:";							//$NON-NLS-1$
	private static final String	EXPERIENCE_IN_AREA_LABEL				= "Experience in Area:";					//$NON-NLS-1$
	private static final String	LEVEL_IN_AREA_LABEL						= "Level in Area:";							//$NON-NLS-1$
	private static final String	LEARN_SPELL								= "Learn Spell:";							//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu			mAreaPopup;
	//	private JPanel					mFilterPanel;
	private JButton				mNewSpellButton;

	private JPanel				mCards;
	private SpellList			mCurrentList;

	JTextField					mExperienceField;
	JTextField					mLevelField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellListDisplay(CharacterSheet owner) {
		super(owner, null);

		add(createHeader(), BorderLayout.PAGE_START);
		//		add(mCards, BorderLayout.CENTER);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	private Component createHeader() {
		JPanel headerWrapper = new JPanel();
		//		headerWrapper.setLayout(new BoxLayout(headerWrapper, BoxLayout.Y_AXIS));
		headerWrapper.setLayout(new BorderLayout());

		JPanel wrapper = new JPanel();

		JLabel areaLabel = new JLabel(MAGIC_AREA_LABEL, SwingConstants.RIGHT);
		mAreaPopup = new TKPopupMenu(MagicAreaPopup.generateMagicAreaPopup(this, this));

		JLabel experienceLabel = new JLabel(EXPERIENCE_IN_AREA_LABEL, SwingConstants.RIGHT);
		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();
		mExperienceField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20, this, filter);

		JLabel levelLabel = new JLabel(LEVEL_IN_AREA_LABEL, SwingConstants.RIGHT);
		mLevelField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);
		mLevelField.setEditable(false);

		JLabel newSpell = new JLabel(LEARN_SPELL, SwingConstants.RIGHT);
		mNewSpellButton = new TKButtonRollover(this, ACS.IMAGE_PLUS_ICON, false);
		mNewSpellButton.setOpaque(true);
		mNewSpellButton.setPreferredSize(new Dimension(25, 25));
		mNewSpellButton.setFocusable(false);
		mNewSpellButton.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String magicArea = getMagicArea();
				if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(magicArea)) {
					SpellSelector selector = new SpellSelector((CharacterSheet) getOwner(), magicArea);
					SpellRecord record = selector.getSpellToLearn();
					if (record != null) {
						if (mCurrentList == null) {
							SpellList list = new SpellList(magicArea);
							mCards.add(magicArea, list);
							mCurrentList = list;
							((CardLayout) mCards.getLayout()).show(mCards, magicArea);
						}
						mCurrentList.addToKnownSpells(record);
					}
				}
			}
		});

		enableFields(false);

		wrapper.add(areaLabel);
		wrapper.add(mAreaPopup);
		wrapper.add(experienceLabel);
		wrapper.add(mExperienceField);
		wrapper.add(levelLabel);
		wrapper.add(mLevelField);
		wrapper.add(newSpell);
		wrapper.add(mNewSpellButton);

		headerWrapper.add(new TKPageTitleLabel(SPELL_LIST_TITLE), BorderLayout.PAGE_START);
		headerWrapper.add(wrapper);

		return headerWrapper;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		Object source = e.getDocument().getProperty(TKComponentHelpers.DOCUMENT_OWNER);

		AttributesRecord record = ((CharacterSheet) getOwner()).getAttributesRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mExperienceField)) {
				mLevelField.setText(String.valueOf(ACS.getLevel(TKStringHelpers.getIntValue(mExperienceField.getText(), 0))));
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (mCurrentList != null) {
			updateSpellList(((ComboMenu) e.getItem()).getText());
		}
	}

	private void updateSpellList(String name) {
		JMenuItem item = mAreaPopup.findPopupMenuItem(name);
		if (item != null) {
			if (mCurrentList.isKnownSpellsEmpty()) {
				item.setForeground(Color.BLACK);
				if (!MagicAreaPopup.SELECT_MAGIC_AREA.equals(name)) {
					mCards.remove(mCurrentList);
					mCurrentList = null;
					((CardLayout) mCards.getLayout()).show(mCards, MagicAreaPopup.SELECT_MAGIC_AREA);
				}
			} else {
				item.setForeground(Color.BLUE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof JMenuItem) {
			String text = ((JMenuItem) source).getText();
			swapPanels(text);
			if (ACS.getClasses().getClassesNamesList().contains(text)) {
				((CharacterSheet) getOwner()).updateRecords();
			}
			enableFields(!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text));
		}
	}

	public void swapPanels(String text) {
		boolean found = false;
		Component comp[] = mCards.getComponents();
		for (Component element : comp) {
			if (element.getName().equals(text)) {
				mCurrentList = MagicAreaPopup.SELECT_MAGIC_AREA.equals(text) ? null : (SpellList) element;
				found = true;
				break;
			}
		}
		if (!found) {
			SpellList list = new SpellList(text);
			mCards.add(text, list);
			mCurrentList = list;
		}
		((CardLayout) mCards.getLayout()).show(mCards, text);
		enableFields(!MagicAreaPopup.SELECT_MAGIC_AREA.equals(text));
	}

	@Override
	protected Component createDisplay() {
		mCards = new JPanel(new CardLayout());

		JPanel panel = new JPanel();
		panel.setName(MagicAreaPopup.SELECT_MAGIC_AREA);
		mCards.add(MagicAreaPopup.SELECT_MAGIC_AREA, panel);

		return mCards;
	}

	@Override
	public void loadDisplay() {
		enableFields(!MagicAreaPopup.SELECT_MAGIC_AREA.equals(getMagicArea()));

	}

	public void clearRecords() {
		for (int i = 0; i < mCards.getComponentCount(); i++) {
			Component comp = mCards.getComponent(i);
			if (comp instanceof SpellList) {
				SpellList spellList = (SpellList) comp;
				spellList.clearRecords();
				String spellArea = spellList.getName();
				updateSpellList(spellArea);

			}
		}
		mCards.removeAll();
		mCurrentList = null;
		mAreaPopup.selectPopupMenuItem(MagicAreaPopup.SELECT_MAGIC_AREA);
		enableFields(false);
	}

	public void enableFields(boolean enabled) {
		mNewSpellButton.setEnabled(enabled);
		mExperienceField.setEditable(enabled);
		mAreaPopup.getMenu().setEnabled(((CharacterSheet) getOwner()).isCharacterLoaded());
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getMagicArea() {
		return mAreaPopup.getSelectedItem();
	}

	/** @return The currentList. */
	public SpellList getCurrentList() {
		return mCurrentList;
	}

	public Component[] getCards() {
		return mCards.getComponents();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

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
					} else if (key.equals(SpellList.FILE_SECTION_START_KEY)) {
						//						swapPanels(mAreaPopup.getSelectedItem());
						tokenizer = mCurrentList.readValues(br);
						updateSpellList(mCurrentList.getName());
						continue;
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

		Component comp[] = mCards.getComponents();
		for (Component element : comp) {
			if (element instanceof SpellList) {
				if (!((SpellList) element).getKnownSpells().isEmpty()) {
					br.write(TKStringHelpers.TAB + SELECTED_MAGICAL_AREA_KEY + TKStringHelpers.SPACE + ((SpellList) element).getName() + System.lineSeparator());
					br.write(TKStringHelpers.TAB + SELECTED_MAGICAL_AREA_EXPERIENCE_KEY + TKStringHelpers.SPACE + mExperienceField.getText() + System.lineSeparator());
					((SpellList) element).saveValues(br);
				}
			}
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (key.equals(SELECTED_MAGICAL_AREA_KEY)) {
			mAreaPopup.selectPopupMenuItem(value);
			swapPanels(mAreaPopup.getSelectedItem());
		} else if (key.equals(SELECTED_MAGICAL_AREA_EXPERIENCE_KEY)) {
			mExperienceField.setText(value);
			mLevelField.setText(String.valueOf(ACS.getLevel(TKStringHelpers.getIntValue(value, 0))));
			enableFields(!MagicAreaPopup.SELECT_MAGIC_AREA.equals(getMagicArea()));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
