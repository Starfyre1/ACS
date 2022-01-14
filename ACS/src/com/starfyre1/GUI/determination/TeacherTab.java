/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

public class TeacherTab extends DeterminationTab implements ActionListener, MouseListener {
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
	private JLabel[]			mExpertiseLabel;
	private JTextField[]		mCostLabel;
	private JTextField[]		mBonusLabel;

	private Color[]				mOldColor;

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
	private String selectExpertise(int index) {
		ExpertiseSelectionDialog selector = new ExpertiseSelectionDialog((CharacterSheet) ((DeterminationPointsDisplay) getOwner()).getOwner(), mTeacherNameField[index].getText().trim());
		String expertise = selector.getExpertise();
		if (expertise != null) {
			mExpertiseLabel[index].setText(expertise);
			return expertise;
		}
		return null;
	}

	private int getIndexInPanel(JPanel panel, Object which) {
		for (int i = 0; i < panel.getComponentCount(); i++) {
			Component comp = panel.getComponent(i);
			if (comp == which) {
				// i == 0 is label;
				return i - 1;
			}
		}
		return -1;
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
		Document doc = e.getDocument();
		int index = getIndex(doc);
		if (index == -1) {
			super.changedUpdate(e);
		} else {
			if (mTeacherNameField[index].getText().isBlank()) {
				mExpertiseLabel[index].setText(CHOOSE_TEACHER);
				mExpertiseLabel[index].removeMouseListener(this);
				if (getOldColor(index) != null) {
					mExpertiseLabel[index].setForeground(mOldColor[index]);
					setOldColor(index, null);
				}
			} else if (getOldColor(index) == null) {
				mExpertiseLabel[index].setText(CHOOSE_EXPERTISE);
				mExpertiseLabel[index].addMouseListener(this);
				if (getOldColor(index) == null) {
					setOldColor(index, mExpertiseLabel[index].getForeground());
				}
				mExpertiseLabel[index].setForeground(Color.BLUE);
			}
		}
	}

	public int getIndex(Document doc) {
		for (int i = 0; i < mTeacherNameField.length; i++) {
			if (mTeacherNameField[i].getDocument() == doc) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();

		if (source instanceof JLabel) {
			JLabel label = (JLabel) source;
			label.getParent();

			int index = getIndexInPanel((JPanel) label.getParent(), source);
			// DW handle -1 as failure to find index

			String expertise = selectExpertise(index);
			if (expertise != null) {
				((JLabel) source).removeMouseListener(this);
				mExpertiseLabel[index].setForeground(getOldColor(index));
				setOldColor(index, null);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing to do
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
		mExpertiseLabel = new JLabel[ROWS];
		mOldColor = new Color[ROWS];
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

			mExpertiseLabel[i] = TKComponentHelpers.createLabel(CHOOSE_EXPERTISE);
			mOldColor[i] = null;
			expertisePanel.add(mExpertiseLabel[i]);
			Dimension size = new Dimension(mExpertiseLabel[i].getPreferredSize().width, TEXT_FIELD_HEIGHT);
			mExpertiseLabel[i].setMinimumSize(size);
			mExpertiseLabel[i].setPreferredSize(size);
			mExpertiseLabel[i].setText(CHOOSE_TEACHER);

			mBonusLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, TEXT_FIELD_HEIGHT, this, filter);
			bonusAmountPanel.add(mBonusLabel[i]);

			mCostLabel[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, TEXT_FIELD_HEIGHT, this, filter);
			costPanel.add(mCostLabel[i]);
		}

		expertisePanel.add(Box.createVerticalGlue());

		outerWrapper.add(teacherPanel);
		outerWrapper.add(expertisePanel);
		outerWrapper.add(bonusAmountPanel);
		outerWrapper.add(costPanel);

		return outerWrapper;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public ArrayList<TeacherDeterminationRecord> getRecordsToLearn() {
		ArrayList<TeacherDeterminationRecord> list = new ArrayList<>();
		// DW _finish
		for (int i = 0; i < ROWS; i++) {
			if (!(mTeacherNameField[i].getText().isBlank() || mExpertiseLabel[i].getText().isBlank() || mCostLabel[i].getText().isBlank() || mBonusLabel[i].getText().isBlank())) {
				String campaignDate = CampaignDateChooser.getCampaignDate();
				list.add(new TeacherDeterminationRecord(mTeacherNameField[i].getText().trim(), mExpertiseLabel[i].getText().trim(), TKStringHelpers.getFloatValue(mCostLabel[i].getText(), 0f), TKStringHelpers.getIntValue(mBonusLabel[i].getText(), 0), campaignDate));
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

	/** @return The oldColor. */
	public Color getOldColor(int which) {
		return mOldColor[which];
	}

	/** @param oldColor The value to set for oldColor. */
	public void setOldColor(int which, Color oldColor) {
		mOldColor[which] = oldColor;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
