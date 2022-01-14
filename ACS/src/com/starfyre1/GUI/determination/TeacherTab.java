/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataset.WeaponList;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

public class TeacherTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "These people will not be very cheap, they are usually very special people whose time is very precious.";	// //$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																								//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "A record of teachers used, for what, and how much";														//$NON-NLS-1$
	static final String			TEACHER_TITLE			= "Teacher's Name";																							//$NON-NLS-1$
	static final String			CHOOSE_EXPERTISE		= "Choose Expertise";																						//$NON-NLS-1$
	static final String			CHOOSE_TEACHER			= "Choose Teacher  ";																						//$NON-NLS-1$

	private static final String	BONUS					= "Bonus:";																									//$NON-NLS-1$
	private static final String	COST					= "Cost:";																									//$NON-NLS-1$
	private static final String	EXPERTISE				= "Expertise:";																								//$NON-NLS-1$
	private static final String	TEACHERS_NAME			= "Teacher's Name:";																						//$NON-NLS-1$

	private static final int	ROWS					= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField[]		mTeacherNameField;
	private TKPopupMenu[]		mExpertiseLabel;
	private JTextField[]		mCostLabel;
	private JTextField[]		mBonusLabel;

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

	}

	@Override
	protected void updateDialogButtons() {
		//		((DeterminationPointsDisplay) getOwner()).updateButtons(isAnyBoxChecked(), false);
	}

	@Override
	protected void loadDisplay() {
		//DW to do
	}

	@Override
	protected Component createDisplay() {
		return createPage(createCenterPanel(), TEACHERS_DESCRIPTION, "", "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	private JPanel createCenterPanel() {
		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		mTeacherNameField = new JTextField[ROWS];
		mExpertiseLabel = new TKPopupMenu[ROWS];
		mBonusLabel = new JTextField[ROWS];
		mCostLabel = new JTextField[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		//		teacherPanel.setBorder(new LineBorder(Color.RED));
		JPanel expertisePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		//		expertisePanel.setBorder(new LineBorder(Color.RED));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		//		bonusAmountPanel.setBorder(new LineBorder(Color.RED));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		//		costPanel.setBorder(new LineBorder(Color.RED));

		teacherPanel.add(new JLabel(TEACHERS_NAME));
		expertisePanel.add(new JLabel(EXPERTISE));
		bonusAmountPanel.add(new JLabel(BONUS));
		costPanel.add(new JLabel(COST));

		for (int i = 0; i < ROWS; i++) {
			mTeacherNameField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
			teacherPanel.add(mTeacherNameField[i]);

			mExpertiseLabel[i] = new TKPopupMenu(getExpertisePopup());
			Dimension size = new Dimension(mExpertiseLabel[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mExpertiseLabel[i].setMaximumSize(size);
			mExpertiseLabel[i].setPreferredSize(size);
			expertisePanel.add(mExpertiseLabel[i]);

			mBonusLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT, this, filter);
			bonusAmountPanel.add(mBonusLabel[i]);

			mCostLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			costPanel.add(mCostLabel[i]);
		}

		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(costPanel);

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
		// DW _finish
		for (int i = 0; i < ROWS; i++) {
			if (!(mTeacherNameField[i].getText().isBlank() || mExpertiseLabel[i].getSelectedItem().equals(CHOOSE_EXPERTISE) || mCostLabel[i].getText().isBlank() || mBonusLabel[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new TeacherDeterminationRecord(mTeacherNameField[i].getText().trim(), mExpertiseLabel[i].getSelectedItem(), TKStringHelpers.getFloatValue(mCostLabel[i].getText(), 0f), TKStringHelpers.getIntValue(mBonusLabel[i].getText(), 0), campaignDate));
			}
		}
		return list;
	}

	@Override
	protected boolean hasValidEntriesToLearn() {
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
