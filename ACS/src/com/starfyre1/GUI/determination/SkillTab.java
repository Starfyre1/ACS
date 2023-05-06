/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKPopupMenu.ComboMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SkillTab extends DeterminationTab implements ItemListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_DESCRIPTION	= "The character gains 1/4 the difference between the teachers Hit Bonus and their own.";	//$NON-NLS-1$

	static final String			SKILL_TAB_TITLE		= "Skills";																					//$NON-NLS-1$
	static final String			SKILL_TAB_TOOLTIP	= "To learn or improve a skill:";															//$NON-NLS-1$
	private static final String	COST_TEXT			= "Cost: 40-60";																			//$NON-NLS-1$
	private static final String	MAINTENANCE_TEXT	= "Maintain: 1 DP";																			//$NON-NLS-1$
	private static final String	SKILL_TEXT			= SKILL_TAB_TOOLTIP;
	private static final String	SUCCESS_TOOLTIP		= "1D20 < (Intelligence)";																	//$NON-NLS-1$
	private static final String	SUCCESS_TEXT1		= "Success: 1D20 < ";																		//$NON-NLS-1$
	private static final String	SELECT_SKILL		= "Select Skill";																			//$NON-NLS-1$

	private static final int	ROWS				= 5;

	private static final String	SAVE				= "Save";																					//$NON-NLS-1$
	private static final String	CANCEL				= "Cancel";																					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOkButton;
	private JButton				mCancelButton;

	private TKPopupMenu			mSkillsPopup;
	private TKPopupMenu			mTeacherPopup;
	private JTextField			mDPPerWeekField;
	private JLabel				mDPSpentLabel;
	private int					mDPCost;
	private JLabel				mBonusLabel;
	private JLabel				mMaintLabel;
	private JLabel				mSuccessfulLabel;
	// DW not implemented yet - Determination dates
	//	private JLabel				mStartDateLabel;
	//	private JLabel				mEndDateLabel;

	private JPanel				mSkillsColumn[];
	private JPanel				mTeacherColumn[];
	private JPanel				mDPPerWeekColumn[];
	private JPanel				mDPSpentColumn[];
	private JPanel				mBonusAmountColumn[];
	private JPanel				mMaintColumn[];
	private JPanel				mSuccessfulColumn[];
	private JPanel				mStartDateColumn[];
	private JPanel				mEndDateColumn[];

	private JDialog				mNewEntryDialog;

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
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), SELECT_SKILL, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				TeacherDeterminationRecord teacherRecord = DeterminationList.getSkillsTeacherRecord(mTeacherPopup.getSelectedItem());
				int teacherID = 0;
				if (teacherRecord != null) {
					teacherID = teacherRecord.getID();
				}
				String startDate = CampaignDateChooser.getCampaignDate();
				SkillDeterminationRecord record = new SkillDeterminationRecord(mSkillsPopup.getSelectedItem(), teacherID, TKStringHelpers.getIntValue(mBonusLabel.getText(), 0), TKStringHelpers.getIntValue(mDPPerWeekField.getText(), 0), mDPCost, startDate, startDate);
				DeterminationList.addSkillRecord(record);
				((DeterminationDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
		} else if (source instanceof JMenuItem) {
			// DW update mDPCost value here when skill is selected
			mDPCost = 40;
			JMenuItem menuItem = (JMenuItem) source;
			if (mTeacherPopup == getPopup(menuItem)) {
				updateBonusValueFromTeacher(menuItem);
			} else {
				updateTeacherPopup(menuItem);
			}
			updateEnabledState();
		}
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		mOkButton = TKComponentHelpers.createButton(SAVE, this);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);

		panel.add(mOkButton);
		panel.add(mCancelButton);

		return panel;
	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mSkillsPopup.getMenu().setEnabled(enable);

			mDPPerWeekField.setEnabled(enable);
			mDPPerWeekField.setEditable(enable);
		}
	}

	// This is if the Proficiency has just been set
	private void updateTeacherPopup(JMenuItem menuItem) {
		if (!menuItem.getText().equals(SELECT_SKILL)) {
			// DW probably should remove ActionListener from JMenuItem's in mTeacherPopup's
			//			mTeacherPopup = new TKPopupMenu(TeacherTab.getTeacherPopup(this));
			//			Dimension size3 = new Dimension(mTeacherPopup.getPreferredSize().width, TEXT_FIELD_HEIGHT);
			//			mTeacherPopup.setMinimumSize(size3);
			//			mTeacherPopup.setPreferredSize(size3);
			mTeacherPopup.setEnabled(true);
		}
	}

	// This is if the teacher has just been set
	private void updateBonusValueFromTeacher(JMenuItem menuItem) {
		if (!menuItem.getText().equals(TeacherTab.SELECT_TEACHER)) {
			TeacherDeterminationRecord teacherRecord = DeterminationList.getSkillsTeacherRecord(menuItem.getText());
			if (teacherRecord != null) {
				String bonus;
				if (teacherRecord.getExpertise().equals(mSkillsPopup.getSelectedItem())) {
					bonus = String.valueOf(teacherRecord.getBonus());
				} else {
					bonus = "0"; //$NON-NLS-1$
				}
				mBonusLabel.setText(bonus);
				// DW update Bonus
			}
		}
	}

	private TKPopupMenu getPopup(JMenuItem menuItem) {

		JPopupMenu popup = (JPopupMenu) menuItem.getParent();
		JMenu menu = (JMenu) popup.getInvoker();
		TKPopupMenu popup3;
		if (menu.getParent() instanceof TKPopupMenu) {
			popup3 = (TKPopupMenu) menu.getParent();
		} else {
			JPopupMenu popup2 = (JPopupMenu) menu.getParent();
			ComboMenu menu2 = (ComboMenu) popup2.getInvoker();
			popup3 = (TKPopupMenu) menu2.getParent();
		}

		return popup3;
	}

	public void updateDisplay() {
		loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		mSkillsColumn = new JPanel[2];
		mTeacherColumn = new JPanel[2];
		mDPPerWeekColumn = new JPanel[2];
		mDPSpentColumn = new JPanel[2];
		mBonusAmountColumn = new JPanel[2];
		mMaintColumn = new JPanel[2];
		mSuccessfulColumn = new JPanel[2];
		mStartDateColumn = new JPanel[2];
		mEndDateColumn = new JPanel[2];

		return createPage(createCenterPanel(false), createCenterPanel(true), SKILLS_DESCRIPTION, SKILL_TEXT, getSuccessText(), SUCCESS_TOOLTIP, COST_TEXT, MAINTENANCE_TEXT);
	}

	private JPanel createCenterPanel(boolean complete) {
		int test = complete ? 1 : 0;

		JPanel outerWrapper = new JPanel();
		mSkillsColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mDPPerWeekColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mDPSpentColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mBonusAmountColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mMaintColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mSuccessfulColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mStartDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));
		mEndDateColumn[test] = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		generateHeaders(complete);

		outerWrapper.add(mSkillsColumn[test]);
		outerWrapper.add(mTeacherColumn[test]);
		outerWrapper.add(mBonusAmountColumn[test]);
		outerWrapper.add(mDPPerWeekColumn[test]);
		outerWrapper.add(mDPSpentColumn[test]);
		outerWrapper.add(mMaintColumn[test]);
		outerWrapper.add(mSuccessfulColumn[test]);
		outerWrapper.add(mStartDateColumn[test]);
		outerWrapper.add(mEndDateColumn[test]);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders(boolean complete) {
		int test = complete ? 1 : 0;

		mSkillsColumn[test].add(new JLabel(SKILL_TAB_TITLE));
		mTeacherColumn[test].add(new JLabel("Teacher")); //$NON-NLS-1$
		mDPPerWeekColumn[test].add(new JLabel("DP/Week")); //$NON-NLS-1$
		mDPSpentColumn[test].add(new JLabel("DP Spent")); //$NON-NLS-1$
		mBonusAmountColumn[test].add(new JLabel("Bonus")); //$NON-NLS-1$
		mMaintColumn[test].add(new JLabel("Maint")); //$NON-NLS-1$
		mSuccessfulColumn[test].add(new JLabel("Success")); //$NON-NLS-1$
		mStartDateColumn[test].add(new JLabel("Start Date")); //$NON-NLS-1$
		mEndDateColumn[test].add(new JLabel("End Date")); //$NON-NLS-1$
	}

	void clearTab() {
		mSkillsColumn[0].removeAll();
		mTeacherColumn[0].removeAll();
		mBonusAmountColumn[0].removeAll();
		mDPPerWeekColumn[0].removeAll();
		mDPSpentColumn[0].removeAll();
		mSuccessfulColumn[0].removeAll();
		mMaintColumn[0].removeAll();
		mStartDateColumn[0].removeAll();
		mEndDateColumn[0].removeAll();
		generateHeaders(false);

		mSkillsColumn[1].removeAll();
		mTeacherColumn[1].removeAll();
		mBonusAmountColumn[1].removeAll();
		mDPPerWeekColumn[1].removeAll();
		mDPSpentColumn[1].removeAll();
		mSuccessfulColumn[1].removeAll();
		mMaintColumn[1].removeAll();
		mStartDateColumn[1].removeAll();
		mEndDateColumn[1].removeAll();
		generateHeaders(true);

	}

	void addRecord(SkillDeterminationRecord record) {
		if (record != null) {
			JLabel skillLabel = new JLabel(record.getSkill());
			JLabel teacherLabel = new JLabel(DeterminationList.getTeacher(record.getTeacher()).getTeacher());
			JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
			JLabel DPPerWeekLabel = new JLabel(String.valueOf(record.getDPPerWeek()));
			JLabel usedLabel = new JLabel(record.getDPTotalSpent() + " / " + record.getDPCost()); //$NON-NLS-1$
			JLabel successLabel = new JLabel(record.isSuccessful() + " / " + 0); //$NON-NLS-1$
			JLabel maintLabel = new JLabel(String.valueOf(record.hasMaintenance()));
			JLabel startDateLabel = new JLabel(record.getStartDate());
			String endDate = record.getEndDate();
			JLabel endDateLabel = new JLabel(endDate);

			int which = endDate.isBlank() ? 0 : 1;
			mSkillsColumn[which].add(skillLabel);
			mTeacherColumn[which].add(teacherLabel);
			mBonusAmountColumn[which].add(bonusLabel);
			mDPPerWeekColumn[which].add(DPPerWeekLabel);
			mDPSpentColumn[which].add(usedLabel);
			mMaintColumn[which].add(maintLabel);
			mSuccessfulColumn[which].add(successLabel);
			mStartDateColumn[which].add(startDateLabel);
			mEndDateColumn[which].add(endDateLabel);
		}
	}

	private JPanel createDialogPanel() {
		// DW _add Start and End Date (popup?)
		int completed = 0;
		int attempted = 0;
		int currentMaintenance = 0;
		int currentlySpent = 0;

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel skillColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel dPPerWeekColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel dPSpentColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel maintColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel successfulColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 0));

		JLabel label = new JLabel(SKILL_TAB_TITLE);
		skillColumn.add(label);
		JLabel header = new JLabel("Teacher"); //$NON-NLS-1$
		teacherColumn.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		dPPerWeekColumn.add(new JLabel("DP/Week")); //$NON-NLS-1$
		dPSpentColumn.add(new JLabel("Used")); //$NON-NLS-1$
		bonusAmountColumn.add(new JLabel("Bonus")); //$NON-NLS-1$
		maintColumn.add(new JLabel("Maint")); //$NON-NLS-1$
		successfulColumn.add(new JLabel("Successful")); //$NON-NLS-1$

		mSkillsPopup = new TKPopupMenu(getSkillsMenu());
		mSkillsPopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		mSkillsPopup.getMenu().addActionListener(this);
		Dimension size2 = new Dimension(mSkillsPopup.getPreferredSize().width, POPUP_HEIGHT);
		mSkillsPopup.setMaximumSize(size2);
		skillColumn.add(mSkillsPopup);

		mTeacherPopup = new TKPopupMenu(TeacherTab.getTeacherPopup(this, TeacherTab.SKILLS));
		Dimension size3 = new Dimension(mTeacherPopup.getPreferredSize().width, POPUP_HEIGHT);
		mTeacherPopup.setMaximumSize(size3);
		teacherColumn.add(mTeacherPopup);

		mBonusLabel = new JLabel(String.valueOf(currentMaintenance));
		mBonusLabel.setMinimumSize(size);
		mBonusLabel.setPreferredSize(size);
		bonusAmountColumn.add(mBonusLabel);

		mDPPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
		dPPerWeekColumn.add(mDPPerWeekField);

		mDPSpentLabel = new JLabel(currentlySpent + " / " + mDPCost); //$NON-NLS-1$
		mDPSpentLabel.setMinimumSize(size);
		mDPSpentLabel.setPreferredSize(size);
		dPSpentColumn.add(mDPSpentLabel);

		mMaintLabel = new JLabel(String.valueOf(currentMaintenance));
		mMaintLabel.setMinimumSize(size);
		mMaintLabel.setPreferredSize(size);
		maintColumn.add(mMaintLabel);

		mSuccessfulLabel = new JLabel(completed + " / " + attempted); //$NON-NLS-1$
		mSuccessfulLabel.setMinimumSize(size);
		mSuccessfulLabel.setPreferredSize(size);
		successfulColumn.add(mSuccessfulLabel);

		// DW not implemented yet - Determination dates
		//		mStartDateLabel = new JLabel();
		//		mEndDateLabel = new JLabel();

		teacherColumn.add(Box.createVerticalGlue());
		dPSpentColumn.add(Box.createVerticalGlue());
		bonusAmountColumn.add(Box.createVerticalGlue());
		maintColumn.add(Box.createVerticalGlue());
		successfulColumn.add(Box.createVerticalGlue());

		outerWrapper.add(skillColumn);
		outerWrapper.add(teacherColumn);
		outerWrapper.add(bonusAmountColumn);
		outerWrapper.add(dPPerWeekColumn);
		outerWrapper.add(dPSpentColumn);
		outerWrapper.add(maintColumn);
		outerWrapper.add(successfulColumn);

		updateEnabledState();

		buttonWrapper.add(outerWrapper, BorderLayout.NORTH);
		buttonWrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		return buttonWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	@Override
	public int getDPPerWeekTabTotal() {
		int pointsSpent = 0;
		ArrayList<SkillDeterminationRecord> list = DeterminationList.getSkillRecords();
		if (list.size() > 0) {
			for (SkillDeterminationRecord record : list) {
				pointsSpent += record.getDPPerWeek();
			}
		}
		return pointsSpent;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mSkillsPopup.getSelectedItem().equals(SELECT_SKILL) || mTeacherLabel.getText().isBlank() || mDPPerWeekField.getText().isBlank())) {
		return true;
		//			}
		//		}
		//		return false;
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
