/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.startup.ACS;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SkillTab extends DeterminationTab implements ItemListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_DESCRIPTION	= "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";	//$NON-NLS-1$

	static final String			SKILL_TAB_TITLE		= "Skills";																					//$NON-NLS-1$
	static final String			SKILL_TAB_TOOLTIP	= "To learn or improve a skill:";															//$NON-NLS-1$
	private static final String	COST_TEXT			= "Cost: 40-60";																			//$NON-NLS-1$
	private static final String	MAINTAINENCE_TEXT	= "Maintain: 1 DP / week";																	//$NON-NLS-1$
	private static final String	SKILL_TEXT			= SKILL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP		= "1D20 < (Intelligence)";																	//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1		= "Success: 1D20 < ";																		//$NON-NLS-1$
	private static final String	SELECT_SKILL		= "Select Skill";																			//$NON-NLS-1$

	private static final int	ROWS				= 5;
	private static final int	COST				= 40;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKPopupMenu[]		mSkillsPopup;
	private JLabel[]			mTeacherLabel;
	private JTextField[]		mDPPerWeekField;
	private JLabel[]			mUsedLabel;
	private JLabel[]			mBonusLabel;
	private JLabel[]			mMaintLabel;
	private JLabel[]			mSuccessfulLabel;
	private JLabel[]			mStartDateLabel;
	private JLabel[]			mCompletionDateLabel;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link SkillTab}.
	 *
	 * @param owner
	 */
	public SkillTab(Object owner) {
		super(owner, SKILL_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mLearnButton)) {
				ArrayList<SkillDeterminationRecord> list = getRecordsToLearn();
				for (SkillDeterminationRecord record : list) {
					DeterminationList.addSkillRecord(record);
				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mSkillsPopup[i].getMenu().setEnabled(enable);

			mDPPerWeekField[i].setEnabled(enable);
			mDPPerWeekField[i].setEditable(enable);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<SkillDeterminationRecord> list = DeterminationList.getSkillRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SkillDeterminationRecord record = list.get(i);

				mSkillsPopup[i].selectPopupMenuItem(record.getSkill());
				mTeacherLabel[i].setText(String.valueOf(record.getTeacher()));
				mBonusLabel[i].setText(String.valueOf(record.getBonus()));
				mDPPerWeekField[i].setText(String.valueOf(record.getDPPerWeek()));
				mUsedLabel[i].setText(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
				// DW _Count successful vs attempted
				mMaintLabel[i].setText(String.valueOf(record.hasMaintainence()));
				mSuccessfulLabel[i].setText(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
				mStartDateLabel[i].setText(record.getStartDate());
				mCompletionDateLabel[i].setText(record.getCompletionDate());

			}
		}
		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), SKILLS_DESCRIPTION, SKILL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTAINENCE_TEXT);
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		int currentTeacher = 0;
		int currentBonus = 0;
		int currentlySpent = 0;
		int currentMaintenance = 0;
		int completed = 0;
		int attempted = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mSkillsPopup = new TKPopupMenu[ROWS];
		mTeacherLabel = new JLabel[ROWS];
		mDPPerWeekField = new JTextField[ROWS];
		mUsedLabel = new JLabel[ROWS];
		mBonusLabel = new JLabel[ROWS];
		mMaintLabel = new JLabel[ROWS];
		mSuccessfulLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel skillsPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dpPerWeekPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dpSpentPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		skillsPanel.add(new JLabel(SKILL_TAB_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("Teacher", SwingConstants.CENTER); //$NON-NLS-1$
		teacherPanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dpPerWeekPanel.add(new JLabel("DP/Week", SwingConstants.CENTER)); //$NON-NLS-1$
		dpSpentPanel.add(new JLabel("Used:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$
		maintPanel.add(new JLabel("Maint:", SwingConstants.CENTER)); //$NON-NLS-1$
		successfulPanel.add(new JLabel("Successful:", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mSkillsPopup[i] = new TKPopupMenu(getSkillsMenu());
			mSkillsPopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mSkillsPopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mSkillsPopup[i].setMinimumSize(size2);
			mSkillsPopup[i].setPreferredSize(size2);
			skillsPanel.add(mSkillsPopup[i]);

			mTeacherLabel[i] = new JLabel(String.valueOf(currentTeacher));
			mTeacherLabel[i].setMinimumSize(size);
			mTeacherLabel[i].setPreferredSize(size);
			teacherPanel.add(mTeacherLabel[i]);

			mBonusLabel[i] = new JLabel(String.valueOf(currentBonus));
			mBonusLabel[i].setMinimumSize(size);
			mBonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(mBonusLabel[i]);

			mDPPerWeekField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			dpPerWeekPanel.add(mDPPerWeekField[i]);

			mUsedLabel[i] = new JLabel(currentlySpent + " / " + COST); //$NON-NLS-1$
			mUsedLabel[i].setMinimumSize(size);
			mUsedLabel[i].setPreferredSize(size);
			dpSpentPanel.add(mUsedLabel[i]);

			mMaintLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mMaintLabel[i].setMinimumSize(size);
			mMaintLabel[i].setPreferredSize(size);
			maintPanel.add(mMaintLabel[i]);

			mSuccessfulLabel[i] = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
			mSuccessfulLabel[i].setMinimumSize(size);
			mSuccessfulLabel[i].setPreferredSize(size);
			successfulPanel.add(mSuccessfulLabel[i]);

		}
		teacherPanel.add(Box.createVerticalGlue());
		bonusAmountPanel.add(Box.createVerticalGlue());
		dpSpentPanel.add(Box.createVerticalGlue());
		maintPanel.add(Box.createVerticalGlue());
		successfulPanel.add(Box.createVerticalGlue());

		outerWrapper.add(skillsPanel);
		outerWrapper.add(teacherPanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(dpPerWeekPanel);
		outerWrapper.add(dpSpentPanel);
		outerWrapper.add(maintPanel);
		outerWrapper.add(successfulPanel);

		updateEnabledState();

		return outerWrapper;
	}

	private JMenu getSkillsMenu() {
		String[] basicSkillsNames = SkillsDisplay.getBasicSkillsLabels();
		String[] thiefSkillsNames = SkillsDisplay.getThiefSkillsLabels();

		JMenu popupMenu = TKPopupMenu.createMenu(SELECT_SKILL);

		String[] groups = { "Basic Skills", "Thief Skills" }; //$NON-NLS-1$ //$NON-NLS-2$

		ArrayList<JMenu> menus = new ArrayList<>();
		popupMenu.addSeparator();
		for (String group : groups) {
			JMenu menu = new JMenu(group);
			menus.add(menu);
			popupMenu.add(menu);
		}

		for (String name : basicSkillsNames) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(0).add(menuItem);
		}

		for (String name : thiefSkillsNames) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(1).add(menuItem);
		}

		JMenuItem menuItem = new JMenuItem(SELECT_SKILL);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem, 0);
		popupMenu.addItemListener(this);

		return popupMenu;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mSkillsPopup[i].getSelectedItem().equals(SELECT_SKILL) || mTeacherLabel[i].getText().isBlank() || mDPPerWeekField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<SkillDeterminationRecord> getRecordsToLearn() {
		ArrayList<SkillDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(mSkillsPopup[i].getSelectedItem().equals(SELECT_SKILL) || mTeacherLabel[i].getText().isBlank() || mDPPerWeekField[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new SkillDeterminationRecord(mSkillsPopup[i].getSelectedItem(), TKStringHelpers.getIntValue(mTeacherLabel[i].getText().trim(), 0), TKStringHelpers.getIntValue(mDPPerWeekField[i].getText().trim(), 0), COST, campaignDate));
			}
		}
		return list;
	}

	@Override
	protected String getSuccessText() {
		AttributesRecord record = ACS.getInstance().getCharacterSheet().getAttributesRecord();
		if (record == null) {
			return "Success: ?"; //$NON-NLS-1$
		}
		int success = record.getModifiedStat(AttributesRecord.INT);
		return SUCCESS_TEXT1 + success;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// DW do something or remove
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
