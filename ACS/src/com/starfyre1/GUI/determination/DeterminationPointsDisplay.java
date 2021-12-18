/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.interfaces.LevelListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeterminationPointsDisplay extends TKTitledDisplay implements LevelListener, ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		DETERMINATION_POINTS_TITLE	= "Determination Points";		//$NON-NLS-1$

	private static final String		POINTS_WEEK_LABEL			= "Remaining Points / Week";	//$NON-NLS-1$

	private static final String		GIVE_UP						= "Give Up";					//$NON-NLS-1$
	private static final String		CLOSE						= "Close";						//$NON-NLS-1$
	private static final String		LEARN						= "Learn";						//$NON-NLS-1$

	//	private static final String		LANGUAGES_LABEL					= "Languages";																															//$NON-NLS-1$
	//	private static final String		WEAPONS_LABEL					= "Weapons";																															//$NON-NLS-1$

	private static final String		ASSIGN_POINTS				= "Assign Points";				//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE				= new Dimension(600, 600);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField				mPointsPerWeekField;
	private JButton					mSpendPoints;
	private JTabbedPane				mTabbedPane;

	private JDialog					mDialog;

	private JButton					mLearnButton;
	private JButton					mCloseButton;
	private JButton					mGiveUpButton;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public DeterminationPointsDisplay(CharacterSheet owner) {
		super(owner, DETERMINATION_POINTS_TITLE);

		updateValues();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public void updateRecord() {
		updateValues();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(mSpendPoints)) {
			assignPoints();
		} else if (source instanceof JButton) {
			if (source.equals(mCloseButton)) {
				mDialog.dispose();
			}
		}
	}

	private void assignPoints() {
		JFrame frame = ((CharacterSheet) getOwner()).getFrame();
		mDialog = new JDialog(frame, true);
		mDialog.setPreferredSize(PREFERRED_SIZE);

		mTabbedPane = new JTabbedPane();

		JComponent physicalTab = new PhysicalTab(this);
		JComponent languageTab = new LanguageTab(this);
		JComponent magicSpellTab = new MagicSpellTab(this);
		JComponent weaponProficiencyTab = new WeaponProficiencyTab(this);
		JComponent skillTab = new SkillTab(this);
		JComponent teacherTab = new TeacherTab(this);

		mTabbedPane.addTab(PhysicalTab.PHYSICAL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, physicalTab, PhysicalTab.PHYSICAL_TAB_TOOLTIP);
		mTabbedPane.addTab(LanguageTab.LANGUAGE_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, languageTab, LanguageTab.LANGUAGE_TAB_TOOLTIP);
		mTabbedPane.addTab(MagicSpellTab.MAGIC_SPELL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, magicSpellTab, MagicSpellTab.MAGIC_SPELL_TAB_TOOLTIP);
		mTabbedPane.addTab(WeaponProficiencyTab.WEAPON_PROFICIENCY_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, weaponProficiencyTab, WeaponProficiencyTab.WEAPON_PROFICIENCY_TAB_TOOLTIP);
		mTabbedPane.addTab(SkillTab.SKILL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, skillTab, SkillTab.SKILL_TAB_TOOLTIP);
		mTabbedPane.addTab(TeacherTab.TEACHER_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, teacherTab, TeacherTab.TEACHER_TAB_TOOLTIP);

		mTabbedPane.setMnemonicAt(0, KeyEvent.VK_A);
		mTabbedPane.setMnemonicAt(1, KeyEvent.VK_L);
		mTabbedPane.setMnemonicAt(2, KeyEvent.VK_M);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_W);
		mTabbedPane.setMnemonicAt(4, KeyEvent.VK_S);
		mTabbedPane.setMnemonicAt(5, KeyEvent.VK_T);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		mLearnButton = TKComponentHelpers.createButton(LEARN, this, false);
		mCloseButton = TKComponentHelpers.createButton(CLOSE, this);
		mGiveUpButton = TKComponentHelpers.createButton(GIVE_UP, this, false);

		buttonPanel.add(mGiveUpButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mLearnButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCloseButton);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mTabbedPane, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		mDialog.add(wrapper);

		mDialog.pack();
		mDialog.setLocationRelativeTo(frame);
		mDialog.setVisible(true);
	}

	void updateButtons(boolean hasSelection, boolean hasPointsInvested) {
		mLearnButton.setEnabled(hasSelection && !hasPointsInvested);
		mGiveUpButton.setEnabled(hasSelection && hasPointsInvested);
	}

	private void updateValues() {
		mPointsPerWeekField.setText(TKStringHelpers.EMPTY_STRING + (getDeterminationPoints() - getDeterminationPointsSpent()));
	}

	private int getDeterminationPointsSpent() {
		return 0;
	}

	public int getDeterminationPoints() {
		int value = 0;

		CharacterSheet owner = (CharacterSheet) getOwner();
		if (owner != null) {
			AttributesRecord stats = owner.getAttributesRecord();
			if (stats != null) {
				value = 6;
				value += stats.getStat(8) - 12;
				value += (owner.getHeaderRecord().getLevel() - 1) * 3;
			}
		}
		return value;
	}

	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		JPanel topWrapper = new JPanel();

		JLabel pointsPerWeekLabel = new JLabel(POINTS_WEEK_LABEL);
		mPointsPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mSpendPoints = TKComponentHelpers.createButton(ASSIGN_POINTS, this);

		topWrapper.add(pointsPerWeekLabel);
		topWrapper.add(mPointsPerWeekField);
		topWrapper.add(mSpendPoints);

		JPanel mainWrapper = new JPanel(new GridLayout(1, 3, 5, 0));
		mainWrapper.setBorder(new EmptyBorder(0, 0, 5, 0));

		outerWrapper.add(topWrapper);
		outerWrapper.add(mainWrapper);

		return outerWrapper;
	}

	@Override
	public void loadDisplay() {
		updateValues();
		// DW load used points from file

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
