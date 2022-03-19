/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.GUI.journal.JournalDisplay;
import com.starfyre1.GUI.journal.WorldDateChooser;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.HistoryRecord;
import com.starfyre1.dataModel.JournalRecord;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.startup.ACS;
import com.starfyre1.storage.HistoryManager;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private JButton				mCampaignButton;

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

		JTextField worldDateField = new JTextField(JournalDisplay.WORLD_DATE_lABEL + WorldDateChooser.getWorldDate());
		worldDateField.setEditable(false);
		worldDateField.setFocusable(false);

		wrapper.add(characterNameLabel);
		wrapper.add(mCharacterNameField);
		wrapper.add(playerNameLabel);
		wrapper.add(mPlayerNameField);
		wrapper.add(levelLabel);
		wrapper.add(mLevelField);

		JPanel wrapper2 = new JPanel();

		wrapper2.add(new JLabel(JournalDisplay.CAMPAIGN_DATE_LABEL, SwingConstants.LEADING));
		mCampaignButton = JournalRecord.getDateButton(CampaignDateChooser.getCampaignDate(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				CampaignDateChooser cal = new CampaignDateChooser(((CharacterSheet) getOwner()).getFrame(), CampaignDateChooser.parseCampaignDate(CampaignDateChooser.getCampaignDate()));
				String date = cal.getSelectedDate();
				if (!date.isEmpty()) {
					CampaignDateChooser.setCampaignDate(date);
					mCampaignButton.setText(date);
					((CharacterSheet) getOwner()).getJournalTab().gameDayStartQuestion();
				}
			}
		});

		wrapper2.add(mCampaignButton);
		wrapper2.add(classLabel);
		wrapper2.add(mClassPopup);
		wrapper2.add(currentExperienceLabel);
		wrapper2.add(mCurrentExperienceField);
		wrapper2.add(nextLevelLabel);
		wrapper2.add(mNextLevelField);
		wrapper2.add(worldDateField);

		outerWrapper.add(wrapper);
		outerWrapper.add(wrapper2);

		return outerWrapper;
	}

	private JMenu generateClassPopup() {

		JMenu popupMenu = TKPopupMenu.createMenu(SELECT_CLASS);

		ClassList classes = ACS.getClasses();
		MageList mages = ACS.getMages();
		PriestList priests = ACS.getPriests();

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
					menuItem.setEnabled(hasMinimumStats(names.get(i)));
					menuItem.addActionListener(this);
					element.add(menuItem);
				}
			}
		}

		for (int i = 0; i < names.size(); i++) {
			if (records.get(i).getGroup() == null) {
				JMenuItem menuItem = new JMenuItem(names.get(i));
				menuItem.setEnabled(hasMinimumStats(names.get(i)));
				menuItem.addActionListener(this);
				popupMenu.add(menuItem, 0);
			}
		}
		JMenuItem item = new JMenuItem(SELECT_CLASS);
		item.addActionListener(this);
		popupMenu.add(item, 0);

		return popupMenu;
	}

	public void updateClassPopup() {
		JMenu menu = mClassPopup.getMenu();
		updateClassPopup(menu);
	}

	private void updateClassPopup(JMenu menu) {
		int n = menu.getItemCount();
		for (int i = 0; i < n; i++) {
			JMenuItem item = menu.getItem(i);
			if (item instanceof JMenu) {
				updateClassPopup((JMenu) item);
			} else if (item != null) {
				item.setEnabled(hasMinimumStats(item.getText()));
			}
		}
	}

	private boolean hasMinimumStats(String name) {
		if (SELECT_CLASS.equals(name)) {
			return true;
		}
		CharacterSheet sheet = (CharacterSheet) getOwner();
		AttributesRecord attribs = sheet.getAttributesRecord();
		if (attribs != null) {
			int stats[] = attribs.getModifiedStats();

			BaseClass characterClass = ClassList.getCharacterClass(name);

			if (characterClass != null) {
				int minimum[] = characterClass.getMinimumStats();
				if (minimum == null) {
					return true;
				}
				for (int i = 0; i < stats.length; i++) {
					if (stats[i] < minimum[i]) {
						return false;
					}
				}

				return true;
			}
		}
		return false;
	}

	@Override
	protected void loadDisplay() {
		HeaderRecord record = ((CharacterSheet) getOwner()).getHeaderRecord();
		mPlayerNameField.setText(TKStringHelpers.EMPTY_STRING + record.getPlayerName());
		mCharacterNameField.setText(TKStringHelpers.EMPTY_STRING + record.getCharacterName());
		updateClassPopup();
		if (record.getCharacterClassName().equals(TKStringHelpers.EMPTY_STRING)) {
			mClassPopup.selectPopupMenuItem(SELECT_CLASS);
		} else {
			mClassPopup.selectPopupMenuItem(record.getCharacterClassName());
		}
		mLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getLevel());
		mCurrentExperienceField.setText(TKStringHelpers.EMPTY_STRING + record.getCurrentExperience());
		mNextLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getNextLevelsExperience());
		mCampaignButton.setText(CampaignDateChooser.getCampaignDate());

	}

	@Override
	public void focusGained(FocusEvent e) {
		// do nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();

		CharacterSheet characterSheet = (CharacterSheet) getOwner();
		HeaderRecord record = characterSheet.getHeaderRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mPlayerNameField)) {
				record.setPlayerName(mPlayerNameField.getText());
			} else if (((JTextField) source).equals(mCharacterNameField)) {
				record.setCharacterName(mCharacterNameField.getText());
			} else if (((JTextField) source).equals(mCurrentExperienceField)) {
				HistoryManager manager = HistoryManager.getInstance();

				int currentExperience = TKStringHelpers.getIntValue(mCurrentExperienceField.getText(), record.getOldExperience());
				if (currentExperience != record.getOldExperience()) {
					int nextExperience = TKStringHelpers.getIntValue(mNextLevelField.getText(), 0);
					boolean levelUp = false;
					if (currentExperience > nextExperience) {
						levelUp = true;
					}
					manager.addRecord(HistoryManager.EXPERIENCE_KEY, new HistoryRecord(WorldDateChooser.getWorldDate(), CampaignDateChooser.getCampaignDate(), currentExperience));
					record.setCurrentExperience(currentExperience);

					mLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getLevel());
					mCurrentExperienceField.setToolTipText(manager.getTooltip(HistoryManager.EXPERIENCE_KEY));
					mNextLevelField.setText(TKStringHelpers.EMPTY_STRING + record.getNextLevelsExperience());
					characterSheet.updateRecords();
					manager.addRecord(HistoryManager.LEVEL_KEY, new HistoryRecord(WorldDateChooser.getWorldDate(), CampaignDateChooser.getCampaignDate(), record.getLevel()));
					mLevelField.setToolTipText(manager.getTooltip(HistoryManager.LEVEL_KEY));
					if (levelUp) {
						characterSheet.getJournalTab().characterLevelUp(record.getLevel());
					}
				}
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

			if (ACS.getClasses().getClassesNamesList().contains(text)) {
				if (!record.getOldClassName().equals(text)) {
					record.setClass(text);
					((CharacterSheet) getOwner()).updateRecords(true);
					loadDisplay();
				}
			} else {
				mClassPopup.selectPopupMenuItem(SELECT_CLASS);
				record.setClass(TKStringHelpers.EMPTY_STRING);
				((CharacterSheet) getOwner()).updateRecords();
				//				((CharacterSheet) getOwner()).getCombatInformationRecord();
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/**
	 * @param tooltip
	 */
	public void setLevelToolTip(String tooltip) {
		mLevelField.setToolTipText(tooltip);
	}

	/**
	 * @param tooltip
	 */
	public void setCurrentExperienceToolTip(String tooltip) {
		mCurrentExperienceField.setToolTipText(tooltip);
	}

	/** @return The campaignDate. */
	public String getCampaignDate() {
		return mCampaignButton.getText();
	}

	/** @param campaignDate The value to set for campaignDate. */
	public void setCampaignButton(String campaignDate) {
		mCampaignButton.setText(campaignDate);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
