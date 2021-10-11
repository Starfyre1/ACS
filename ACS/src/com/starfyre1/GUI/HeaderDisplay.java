/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HeaderDisplay extends TKTitledDisplay implements FocusListener, ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	ATHRI_CHARACTER_SHEET_TITLE		= "Athri Character Sheet";	//$NON-NLS-1$

	private static final String	PLAYER_NAME_LABEL				= "Player Name";			//$NON-NLS-1$
	private static final String	CHARACTER_NAME_LABEL			= "Character Name";			//$NON-NLS-1$
	private static final String	CLASS_LABEL						= "Class";					//$NON-NLS-1$
	private static final String	LEVEL_LABEL						= "Level";					//$NON-NLS-1$
	private static final String	CURRENT_EXPERIENCE_LABEL		= "Current Experience";		//$NON-NLS-1$
	private static final String	EXPERIENCE_FOR_NEXT_LEVEL_LABEL	= "Next Level";				//$NON-NLS-1$

	private static final String	SELECT_CLASS					= "<Select Class>";			//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mPlayerNameField;
	private JTextField			mCharacterNameField;
	private TKPopupMenu			mClassPopup;
	private JTextField			mLevelField;
	private JTextField			mCurrentExperienceField;
	private JTextField			mNextLevelField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link HeaderDisplay}.
	 */
	public HeaderDisplay(CharacterSheet owner) {
		super(owner, ATHRI_CHARACTER_SHEET_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		//		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 10, 0), new LineBorder(Color.BLACK)));
		setBorder(new EmptyBorder(0, 0, 10, 0));

		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));
		JPanel wrapper = new JPanel();

		JLabel characterNameLabel = new JLabel(CHARACTER_NAME_LABEL, SwingConstants.RIGHT);
		mCharacterNameField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);
		mCharacterNameField.addFocusListener(this);

		JLabel playerNameLabel = new JLabel(PLAYER_NAME_LABEL, SwingConstants.RIGHT);
		mPlayerNameField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);
		mPlayerNameField.addFocusListener(this);

		JLabel classLabel = new JLabel(CLASS_LABEL, SwingConstants.RIGHT);
		mClassPopup = new TKPopupMenu(generateClassPopup());

		JLabel levelLabel = new JLabel(LEVEL_LABEL, SwingConstants.RIGHT);
		mLevelField = new JTextField(CharacterSheet.FIELD_SIZE_LARGE);
		mLevelField.setEditable(false);

		JLabel currentExperienceLabel = new JLabel(CURRENT_EXPERIENCE_LABEL);
		mCurrentExperienceField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mCurrentExperienceField.addFocusListener(this);

		JLabel nextLevelLabel = new JLabel(EXPERIENCE_FOR_NEXT_LEVEL_LABEL);
		mNextLevelField = new JTextField(CharacterSheet.FIELD_SIZE_MEDIUM);
		mNextLevelField.setEditable(false);

		wrapper.add(characterNameLabel);
		wrapper.add(mCharacterNameField);
		wrapper.add(playerNameLabel);
		wrapper.add(mPlayerNameField);
		wrapper.add(levelLabel);
		wrapper.add(mLevelField);

		JPanel wrapper2 = new JPanel();

		wrapper2.add(classLabel);
		wrapper2.add(mClassPopup);
		wrapper2.add(currentExperienceLabel);
		wrapper2.add(mCurrentExperienceField);
		wrapper2.add(nextLevelLabel);
		wrapper2.add(mNextLevelField);

		outerWrapper.add(wrapper);
		outerWrapper.add(wrapper2);

		return outerWrapper;
	}

	private JMenu generateClassPopup() {

		JMenu popupMenu = TKPopupMenu.createMenu(SELECT_CLASS);

		ClassList classes = ACS.getInstance().getClasses();
		MageList mages = ACS.getInstance().getMages();
		PriestList priests = ACS.getInstance().getPriests();

		ArrayList<String> groups = classes.getClassGroupsList();
		groups.addAll(mages.getMagesGroupsList());
		groups.addAll(priests.getPriestsGroupsList());

		ArrayList<String> names = classes.getClassesNamesList();
		names.addAll(mages.getMagesNamesList());
		names.addAll(priests.getPriestsNamesList());

		ArrayList<ClassesRecord> records = classes.getRecordsList();
		records.addAll(mages.getRecordsList());
		records.addAll(priests.getRecordsList());

		ArrayList<JMenu> menus = new ArrayList<>();
		popupMenu.addSeparator();
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
		JMenuItem item = new JMenuItem(SELECT_CLASS);
		item.addActionListener(this);
		popupMenu.add(item, 0);

		return popupMenu;
	}

	@Override
	protected void loadDisplay() {
		HeaderRecord record = ((CharacterSheet) getOwner()).getHeaderRecord();
		mPlayerNameField.setText(TKStringHelpers.EMPTY_STRING + record.getPlayerName());
		mCharacterNameField.setText(TKStringHelpers.EMPTY_STRING + record.getCharacterName());
		if (record.getCharacterClassName().equals(TKStringHelpers.EMPTY_STRING)) {
			mClassPopup.selectPopupMenuItem(SELECT_CLASS);
		} else {
			mClassPopup.selectPopupMenuItem(record.getCharacterClassName());
		}
		mLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getLevel());
		mCurrentExperienceField.setText(TKStringHelpers.EMPTY_STRING + record.getCurrentExperience());
		mNextLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getNextLevel());

	}

	@Override
	public void focusGained(FocusEvent e) {
		// do nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();

		HeaderRecord record = ((CharacterSheet) getOwner()).getHeaderRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mPlayerNameField)) {
				record.setPlayerName(mPlayerNameField.getText());
			} else if (((JTextField) source).equals(mCharacterNameField)) {
				record.setCharacterName(mCharacterNameField.getText());
			} else if (((JTextField) source).equals(mCurrentExperienceField)) {
				record.setCurrentExperience(TKStringHelpers.getIntValue(mCurrentExperienceField.getText(), record.getOldExperience()));
				mLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getLevel());
				mNextLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getNextLevel());
				((CharacterSheet) getOwner()).levelChanged();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		HeaderRecord record = ((CharacterSheet) getOwner()).getHeaderRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JMenuItem) {
			String text = ((JMenuItem) source).getText();

			if (ACS.getInstance().getClasses().getClassesNamesList().contains(text)) {
				record.setClass(text);
				((CharacterSheet) getOwner()).levelChanged();
			} else {
				mClassPopup.selectPopupMenuItem(SELECT_CLASS);
				((CharacterSheet) getOwner()).levelChanged();
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
