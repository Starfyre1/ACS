/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TeacherTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "The payment will be up to the Game Masters running that particular campaign.\r\n"					// //$NON-NLS-1$
					+ "These people will not be very cheap, they are usually very special people whose time is very precious.";									// //$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																							//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "A record of teachers used, for what, and how much";													//$NON-NLS-1$
	static final String			TEACHER_TITLE			= "Teacher's Name";																						//$NON-NLS-1$

	private static final int	ROWS					= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JTextField[]				mTeacherNameField;
	JTextField[]				mExpertiseField;
	JTextField[]				mCostLabel;
	JLabel[]					mBonusLabel;

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
		int currentMaintenance = 0;

		mTeacherNameField = new JTextField[ROWS];
		mExpertiseField = new JTextField[ROWS];
		mCostLabel = new JTextField[ROWS];
		mBonusLabel = new JLabel[ROWS];

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));
		JPanel teacherPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel expertisePanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 15, 0, 5));
		JPanel costPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));
		JPanel bonusAmountPanel = getPanel(BoxLayout.Y_AXIS, new EmptyBorder(0, 5, 0, 5));

		teacherPanel.add(new JLabel(TEACHER_TITLE + ":", SwingConstants.CENTER)); //$NON-NLS-1$
		JLabel header = new JLabel("Expertise:", SwingConstants.CENTER); //$NON-NLS-1$
		expertisePanel.add(header);
		Dimension size = new Dimension(header.getPreferredSize().width, TEXT_FIELD_HEIGHT);
		costPanel.add(new JLabel("Cost:", SwingConstants.CENTER)); //$NON-NLS-1$
		bonusAmountPanel.add(new JLabel("Bonus", SwingConstants.CENTER)); //$NON-NLS-1$

		for (int i = 0; i < ROWS; i++) {
			mTeacherNameField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_EXLARGE, TEXT_FIELD_HEIGHT, this);
			teacherPanel.add(mTeacherNameField[i]);

			mExpertiseField[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this);
			expertisePanel.add(mExpertiseField[i]);

			mCostLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this);
			costPanel.add(mCostLabel[i]);

			mBonusLabel[i] = new JLabel(String.valueOf(currentMaintenance));
			mBonusLabel[i].setMinimumSize(size);
			mBonusLabel[i].setPreferredSize(size);
			bonusAmountPanel.add(mBonusLabel[i]);
		}
		bonusAmountPanel.add(Box.createVerticalGlue());

		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(costPanel);
		outerWrapper.add(bonusAmountPanel);

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public ArrayList<TeacherDeterminationRecord> getRecordsToLearn() {
		ArrayList<TeacherDeterminationRecord> list = new ArrayList<>();
		// DW _finish
		for (int i = 0; i < ROWS; i++) {
			if (!(mTeacherNameField[i].getText().isBlank() || mExpertiseField[i].getText().isBlank() || mCostLabel[i].getText().isBlank() || mBonusLabel[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new TeacherDeterminationRecord(mTeacherNameField[i].getText().trim(), mExpertiseField[i].getText().trim(), TKStringHelpers.getFloatValue(mCostLabel[i].getText(), 0f), TKStringHelpers.getIntValue(mBonusLabel[i].getText(), 0), campaignDate));
			}
		}
		return list;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
