/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKFloatFilter;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TeacherTab extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "These people will not be very cheap, they are usually very special people whose time is very precious.";	// //$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																								//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "A record of teachers used, for what, and how much";														//$NON-NLS-1$
	static final String			TEACHER_TITLE			= "Teacher's Name";																							//$NON-NLS-1$
	static final String			CHOOSE_EXPERTISE		= "Choose Expertise";																						//$NON-NLS-1$
	static final String			SELECT_TEACHER			= "Select Teacher";																							//$NON-NLS-1$

	private static final String	ID						= "ID:";																									//$NON-NLS-1$
	private static final String	BONUS					= "Bonus:";																									//$NON-NLS-1$
	private static final String	COST					= "$ Cost:";																								//$NON-NLS-1$
	private static final String	EXPERTISE				= "Expertise:";																								//$NON-NLS-1$
	private static final String	TEACHERS_NAME			= "Teacher's Name:";																						//$NON-NLS-1$

	private static final int	ROWS					= 5;

	private static final String	SAVE					= "Save";																									//$NON-NLS-1$
	private static final String	CANCEL					= "Cancel";																									//$NON-NLS-1$

	static final int			WEAPONS					= 0;
	static final int			SKILLS					= 1;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOkButton;
	private JButton				mCancelButton;

	private JLabel				mIDLabel;
	private JTextField			mTeacherNameField;
	private TKPopupMenu			mExpertisePopup;
	private JTextField			mCostField;
	private JTextField			mBonusField;

	private JPanel				mIDColumn;
	private JPanel				mTeacherNameColumn;
	private JPanel				mExpertiseColumn;
	private JPanel				mCostColumn;
	private JPanel				mBonusColumn;

	private JDialog				mNewEntryDialog;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link TeacherTab}.
	 *
	 * @param owner
	 */
	public TeacherTab(Object owner) {
		super(owner, TEACHER_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mNewButton)) {
				mNewEntryDialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), TEACHER_TAB_TITLE, true);
				mNewEntryDialog.setSize(800, 400);

				mNewEntryDialog.add(createDialogPanel());
				mNewEntryDialog.setVisible(true);
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			} else if (source.equals(mOkButton)) {
				TeacherDeterminationRecord record = new TeacherDeterminationRecord(TKStringHelpers.getIntValue(mIDLabel.getText(), -1), mTeacherNameField.getText(), mExpertisePopup.getSelectedItem(), TKStringHelpers.getFloatValue(mCostField.getText(), 0), TKStringHelpers.getIntValue(mBonusField.getText(), 0));
				DeterminationList.addTeacherRecord(record);
				((DeterminationPointsDisplay) getOwner()).addRecords(true);
				mNewEntryDialog.dispose();
			} else if (source.equals(mCancelButton)) {
				mNewEntryDialog.dispose();
			}
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
			mTeacherNameField.setEnabled(enable);
			mTeacherNameField.setEditable(enable);

			mExpertisePopup.getMenu().setEnabled(enable);

			mBonusField.setEnabled(enable);
			mBonusField.setEditable(enable);

			mCostField.setEnabled(enable);
			mCostField.setEditable(enable);
		}
	}

	public void updateDisplay() {
		loadDisplay();
	}

	@Override
	protected void loadDisplay() {
		ArrayList<TeacherDeterminationRecord> list = DeterminationList.getTeachersRecords();
		JPanel wrapper = new JPanel();
		if (list.size() > 0) {
			for (TeacherDeterminationRecord record : list) {
				JLabel idLabel = new JLabel(String.valueOf(record.getID()));
				JLabel teacherNameLabel = new JLabel(String.valueOf(record.getTeacher()));
				JLabel expertiseLabel = new JLabel(record.getExpertise());
				JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
				JLabel costLabel = new JLabel(String.valueOf(record.getCost()));

				wrapper.add(idLabel);
				wrapper.add(teacherNameLabel);
				wrapper.add(expertiseLabel);
				wrapper.add(bonusLabel);
				wrapper.add(costLabel);
			}
		}
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), TEACHERS_DESCRIPTION, "", "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	private JPanel createCenterPanel() {
		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		mIDColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mTeacherNameColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mExpertiseColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		mBonusColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		mCostColumn = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		generateHeaders();

		outerWrapper.add(mIDColumn);
		outerWrapper.add(mTeacherNameColumn);
		outerWrapper.add(mExpertiseColumn);
		outerWrapper.add(mBonusColumn);
		outerWrapper.add(mCostColumn);

		//		updateEnabledState();
		//		updateDialogButtons();
		return outerWrapper;
	}

	private void generateHeaders() {
		mIDColumn.add(new JLabel(ID));
		mTeacherNameColumn.add(new JLabel(TEACHERS_NAME));
		mExpertiseColumn.add(new JLabel(EXPERTISE));
		mBonusColumn.add(new JLabel(BONUS));
		mCostColumn.add(new JLabel(COST));
	}

	void clearTab() {
		mIDColumn.removeAll();
		mTeacherNameColumn.removeAll();
		mExpertiseColumn.removeAll();
		mBonusColumn.removeAll();
		mCostColumn.removeAll();
		generateHeaders();
	}

	void addRecord(TeacherDeterminationRecord record) {
		if (record != null) {
			JLabel idLabel = new JLabel(String.valueOf(record.getID()));
			JLabel teacherLabel = new JLabel(record.getTeacher());
			JLabel expertiseLabel = new JLabel(record.getExpertise());
			JLabel bonusLabel = new JLabel(String.valueOf(record.getBonus()));
			JLabel costLabel = new JLabel(String.valueOf(record.getCost()));

			mIDColumn.add(idLabel);
			mTeacherNameColumn.add(teacherLabel);
			mExpertiseColumn.add(expertiseLabel);
			mBonusColumn.add(bonusLabel);
			mCostColumn.add(costLabel);
		}
	}

	private JPanel createDialogPanel() {
		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();
		TKFloatFilter floatFilter = TKFloatFilter.getFilterInstance();

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel idPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel expertisePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		JLabel header = new JLabel(ID);
		idPanel.add(header);
		teacherPanel.add(new JLabel(TEACHERS_NAME));
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		expertisePanel.add(new JLabel(EXPERTISE));
		bonusAmountPanel.add(new JLabel(BONUS));
		costPanel.add(new JLabel(COST));

		mIDLabel = TKComponentHelpers.createLabel("0"); //$NON-NLS-1$
		mIDLabel.setMinimumSize(size);
		mIDLabel.setPreferredSize(size);
		idPanel.add(mIDLabel);

		mTeacherNameField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
		mTeacherNameField.getDocument().addDocumentListener(this);
		mTeacherNameField.setEnabled(false);
		mTeacherNameField.setEditable(false);
		teacherPanel.add(mTeacherNameField);

		mExpertisePopup = new TKPopupMenu(getExpertisePopup());
		mExpertisePopup.setAlignmentX(Component.LEFT_ALIGNMENT);
		Dimension size2 = new Dimension(mExpertisePopup.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		mExpertisePopup.setMaximumSize(size2);
		mExpertisePopup.setPreferredSize(size2);
		mExpertisePopup.getMenu().setEnabled(false);
		expertisePanel.add(mExpertisePopup);

		mBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT, this, intFilter);
		mBonusField.getDocument().addDocumentListener(this);
		mBonusField.setEnabled(false);
		mBonusField.setEditable(false);
		bonusAmountPanel.add(mBonusField);

		mCostField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, floatFilter);
		mCostField.getDocument().addDocumentListener(this);
		mCostField.setEnabled(false);
		mCostField.setEditable(false);
		costPanel.add(mCostField);

		idPanel.add(Box.createVerticalGlue());
		teacherPanel.add(Box.createVerticalGlue());
		expertisePanel.add(Box.createVerticalGlue());
		bonusAmountPanel.add(Box.createVerticalGlue());
		costPanel.add(Box.createVerticalGlue());

		outerWrapper.add(idPanel);
		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(costPanel);

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
		return 0;
	}

	private JMenu getExpertisePopup() {
		JMenu expertisePopupMenu = TKPopupMenu.createMenu(CHOOSE_EXPERTISE);

		String weaponList[] = WeaponList.getProficiencyList();
		String skillBasicList[] = SkillsDisplay.getBasicSkillsLabels();
		String skillThiefList[] = SkillsDisplay.getThiefSkillsLabels();

		String[] groups = { "Weapon Proficiency", "Basic Skills", "Thief Skills" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		ArrayList<JMenu> menus = new ArrayList<>();
		expertisePopupMenu.addSeparator();
		for (String group : groups) {
			JMenu menu = new JMenu(group);
			menus.add(menu);
			expertisePopupMenu.add(menu);
		}

		for (String name : weaponList) {
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.addActionListener(this);
				menus.get(0).add(menuItem);
			}
		}

		for (String name : skillBasicList) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(1).add(menuItem);
		}

		for (String name : skillThiefList) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(2).add(menuItem);
		}

		JMenuItem menuItem = new JMenuItem(CHOOSE_EXPERTISE);
		menuItem.addActionListener(this);
		expertisePopupMenu.add(menuItem, 0);
		//		expertisePopupMenu.addItemListener(this);

		return expertisePopupMenu;
	}

	public static JMenu getTeacherPopup(ActionListener listener, int profession) {
		JMenu teacherPopupMenu = TKPopupMenu.createMenu(SELECT_TEACHER);
		ArrayList<String> teacherList;

		if (profession == WEAPONS) {
			teacherList = DeterminationList.getWeaponsTeachersNames();
		} else { // (profession = SKILLS)
			teacherList = DeterminationList.getSkillsTeachersNames();
		}

		teacherPopupMenu.addSeparator();
		for (String name : teacherList) {
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.addActionListener(listener);
				teacherPopupMenu.add(menuItem);
			}
		}

		JMenuItem menuItem = new JMenuItem(SELECT_TEACHER);
		menuItem.addActionListener(listener);
		teacherPopupMenu.add(menuItem, 0);

		return teacherPopupMenu;
	}

	public ArrayList<TeacherDeterminationRecord> getRecordsToLearn() {
		ArrayList<TeacherDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(TKStringHelpers.getIntValue(mIDLabel.getText(), 0) != 0 || mTeacherNameField.getText().isBlank() || mExpertisePopup.getSelectedItem().equals(CHOOSE_EXPERTISE) || mCostField.getText().isBlank() || mBonusField.getText().isBlank())) {
				int id = TeacherDeterminationRecord.getNextIdNumber();
				mIDLabel.setText(Integer.toString(id));
				list.add(new TeacherDeterminationRecord(id, mTeacherNameField.getText().trim(), mExpertisePopup.getSelectedItem(), TKStringHelpers.getFloatValue(mCostField.getText(), 0f), TKStringHelpers.getIntValue(mBonusField.getText(), 0)));
			}
		}
		return list;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		//		for (int i = 0; i < ROWS; i++) {
		//			if (!(mTeacherNameField.getText().isBlank() || mExpertisePopup.getSelectedItem().equals(CHOOSE_EXPERTISE) || mCostField.getText().isBlank() || mBonusField.getText().isBlank())) {
		return true;
		//			}
		//		}
		//		return false;
	}

	@Override
	protected String getSuccessText() {
		return null;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
