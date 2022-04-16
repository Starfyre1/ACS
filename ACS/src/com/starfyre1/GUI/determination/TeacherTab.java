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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

public class TeacherTab extends DeterminationTab {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "These people will not be very cheap, they are usually very special people whose time is very precious.";	// //$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																								//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "A record of teachers used, for what, and how much";														//$NON-NLS-1$
	static final String			TEACHER_TITLE			= "Teacher's Name";																							//$NON-NLS-1$
	static final String			CHOOSE_EXPERTISE		= "Choose Expertise";																						//$NON-NLS-1$
	static final String			CHOOSE_TEACHER			= "Choose Teacher  ";																						//$NON-NLS-1$

	private static final String	ID						= "ID:";																									//$NON-NLS-1$
	private static final String	BONUS					= "Bonus:";																									//$NON-NLS-1$
	private static final String	COST					= "$ Cost:";																								//$NON-NLS-1$
	private static final String	EXPERTISE				= "Expertise:";																								//$NON-NLS-1$
	private static final String	TEACHERS_NAME			= "Teacher's Name:";																						//$NON-NLS-1$

	private static final int	ROWS					= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel[]			mIDLabel;
	private JTextField[]		mTeacherNameField;
	private TKPopupMenu[]		mExpertisePopup;
	private JTextField[]		mCostField;
	private JTextField[]		mBonusField;

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
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source.equals(mLearnButton)) {
				ArrayList<TeacherDeterminationRecord> list = getRecordsToLearn();
				for (TeacherDeterminationRecord record : list) {
					DeterminationList.addTeacherRecord(record);
				}
				// DW Create Record
			} else if (source.equals(mGiveUpButton)) {
				// DW Added game date to record
			}
		}
	}

	//	@Override
	//	protected void updateDialogButtons() {
	//		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnyBoxChecked(), false);
	//	}

	private void updateEnabledState() {
		HeaderRecord headerRecord = ACS.getInstance().getCharacterSheet().getHeaderRecord();
		boolean enable = headerRecord == null ? false : headerRecord.getCharacterClass() != null;
		for (int i = 0; i < ROWS; i++) {
			mTeacherNameField[i].setEnabled(enable);
			mTeacherNameField[i].setEditable(enable);

			mExpertisePopup[i].getMenu().setEnabled(enable);

			mBonusField[i].setEnabled(enable);
			mBonusField[i].setEditable(enable);

			mCostField[i].setEnabled(enable);
			mCostField[i].setEditable(enable);
		}
	}

	@Override
	protected void loadDisplay() {
		ArrayList<TeacherDeterminationRecord> list = DeterminationList.getTeachersRecords();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TeacherDeterminationRecord record = list.get(i);

				mIDLabel[i].setText(String.valueOf(record.getID()));
				mTeacherNameField[i].setText(String.valueOf(record.getTeacher()));
				mExpertisePopup[i].selectPopupMenuItem(record.getExpertise());
				mBonusField[i].setText(String.valueOf(record.getBonus()));
				mCostField[i].setText(String.valueOf(record.getCost()));
			}
		}
		updateEnabledState();
		super.loadDisplay();
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), TEACHERS_DESCRIPTION, "", "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	private JPanel createCenterPanel() {
		// DW _add Start and Completion Date (popup?)
		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();
		TKFloatFilter floatFilter = TKFloatFilter.getFilterInstance();

		mIDLabel = new JLabel[ROWS];
		mTeacherNameField = new JTextField[ROWS];
		mExpertisePopup = new TKPopupMenu[ROWS];
		mBonusField = new JTextField[ROWS];
		mCostField = new JTextField[ROWS];

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

		for (int i = 0; i < ROWS; i++) {
			mIDLabel[i] = TKComponentHelpers.createLabel("0"); //$NON-NLS-1$
			mIDLabel[i].setMinimumSize(size);
			mIDLabel[i].setPreferredSize(size);
			idPanel.add(mIDLabel[i]);

			mTeacherNameField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
			mTeacherNameField[i].getDocument().addDocumentListener(this);
			mTeacherNameField[i].setEnabled(false);
			mTeacherNameField[i].setEditable(false);
			teacherPanel.add(mTeacherNameField[i]);

			mExpertisePopup[i] = new TKPopupMenu(getExpertisePopup());
			mExpertisePopup[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			Dimension size2 = new Dimension(mExpertisePopup[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mExpertisePopup[i].setMaximumSize(size2);
			mExpertisePopup[i].setPreferredSize(size2);
			mExpertisePopup[i].getMenu().setEnabled(false);
			expertisePanel.add(mExpertisePopup[i]);

			mBonusField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT, this, intFilter);
			mBonusField[i].getDocument().addDocumentListener(this);
			mBonusField[i].setEnabled(false);
			mBonusField[i].setEditable(false);
			bonusAmountPanel.add(mBonusField[i]);

			mCostField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, floatFilter);
			mCostField[i].getDocument().addDocumentListener(this);
			mCostField[i].setEnabled(false);
			mCostField[i].setEditable(false);
			costPanel.add(mCostField[i]);
		}

		idPanel.add(Box.createVerticalGlue());

		outerWrapper.add(idPanel);
		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(costPanel);

		updateEnabledState();

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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

	public ArrayList<TeacherDeterminationRecord> getRecordsToLearn() {
		ArrayList<TeacherDeterminationRecord> list = new ArrayList<>();
		for (int i = 0; i < ROWS; i++) {
			if (!(TKStringHelpers.getIntValue(mIDLabel[i].getText(), 0) != 0 || mTeacherNameField[i].getText().isBlank() || mExpertisePopup[i].getSelectedItem().equals(CHOOSE_EXPERTISE) || mCostField[i].getText().isBlank() || mBonusField[i].getText().isBlank())) {
				int id = TeacherDeterminationRecord.getNextIdNumber();
				mIDLabel[i].setText(Integer.toString(id));
				list.add(new TeacherDeterminationRecord(id, mTeacherNameField[i].getText().trim(), mExpertisePopup[i].getSelectedItem(), TKStringHelpers.getFloatValue(mCostField[i].getText(), 0f), TKStringHelpers.getIntValue(mBonusField[i].getText(), 0)));
			}
		}
		return list;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
		for (int i = 0; i < ROWS; i++) {
			if (!(mTeacherNameField[i].getText().isBlank() || mExpertisePopup[i].getSelectedItem().equals(CHOOSE_EXPERTISE) || mCostField[i].getText().isBlank() || mBonusField[i].getText().isBlank())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected String getSuccessText() {
		return null;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
