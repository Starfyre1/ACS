/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponProficiencyDeterminationRecord;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.interfaces.LevelListener;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DeterminationPointsDisplay extends TKTitledDisplay implements LevelListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		DETERMINATION_POINTS_TITLE		= "Determination Points";		//$NON-NLS-1$

	private static final String		TOTAL_POINTS_WEEK_LABEL			= "Total:";						//$NON-NLS-1$
	private static final String		USED_POINTS_WEEK_LABEL			= "Used:";						//$NON-NLS-1$
	private static final String		REMAINING_POINTS_WEEK_LABEL		= "Left:";						//$NON-NLS-1$

	private static final String		TOTAL_POINTS_WEEK_TOOLTIP		= "Total Points per Week";		//$NON-NLS-1$
	private static final String		USED_POINTS_WEEK_TOOLTIP		= "Used Points per Week";		//$NON-NLS-1$
	private static final String		REMAINING_POINTS_WEEK_TOOLTIP	= "Remaining Points per Week";	//$NON-NLS-1$

	//	private static final String		ASSIGN_POINTS				= "Assign Points";				//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE					= new Dimension(600, 285);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int						mTotalDPPoints;
	private int						mUsedDPPoints;
	private int						mRemainingDPPoints;
	private JLabel					mTotalDPPointsLabel;
	private JLabel					mUsedDPPointsLabel;
	private JLabel					mRemainingDPPointsLabel;
	private JTabbedPane				mTabbedPane;

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
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		outerWrapper.add(generatePointsPanel());
		outerWrapper.add(getDeterminationDisplay());

		return outerWrapper;
	}

	private JPanel generatePointsPanel() {
		JPanel pointsPanel = new JPanel();
		pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));

		JPanel innerWrapper = new JPanel();
		mTotalDPPointsLabel = new JLabel(String.valueOf(mTotalDPPoints));
		mTotalDPPointsLabel.setToolTipText(TOTAL_POINTS_WEEK_TOOLTIP);
		JLabel totalTitle = new JLabel(TOTAL_POINTS_WEEK_LABEL);
		totalTitle.setToolTipText(TOTAL_POINTS_WEEK_TOOLTIP);
		innerWrapper.add(totalTitle);
		innerWrapper.add(mTotalDPPointsLabel);
		pointsPanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mUsedDPPointsLabel = new JLabel(String.valueOf(mUsedDPPoints));
		mUsedDPPointsLabel.setToolTipText(USED_POINTS_WEEK_TOOLTIP);
		JLabel usedTitle = new JLabel(USED_POINTS_WEEK_LABEL);
		usedTitle.setToolTipText(USED_POINTS_WEEK_TOOLTIP);
		innerWrapper.add(usedTitle);
		innerWrapper.add(mUsedDPPointsLabel);
		pointsPanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mRemainingDPPointsLabel = new JLabel(String.valueOf(mRemainingDPPoints));
		mRemainingDPPointsLabel.setToolTipText(REMAINING_POINTS_WEEK_TOOLTIP);
		JLabel remainingTitle = new JLabel(REMAINING_POINTS_WEEK_LABEL);
		remainingTitle.setToolTipText(REMAINING_POINTS_WEEK_TOOLTIP);
		innerWrapper.add(remainingTitle);
		innerWrapper.add(mRemainingDPPointsLabel);
		pointsPanel.add(innerWrapper);
		return pointsPanel;
	}

	private JPanel getDeterminationDisplay() {
		mTabbedPane = new JTabbedPane();

		JComponent attributesTab = new AttributesTab(this);
		JComponent languageTab = new LanguageTab(this);
		JComponent magicSpellTab = new MagicSpellTab(this);
		JComponent weaponProficiencyTab = new WeaponProficiencyTab(this);
		JComponent skillTab = new SkillTab(this);
		JComponent teacherTab = new TeacherTab(this);

		mTabbedPane.addTab(AttributesTab.ATTRIBUTES_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, attributesTab, AttributesTab.ATTRIBUTES_TAB_TOOLTIP);
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

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mTabbedPane, BorderLayout.CENTER);

		wrapper.setPreferredSize(PREFERRED_SIZE);

		return wrapper;
	}

	@Override
	public void updateRecord() {
		updateValues();
	}

	@Override
	public void loadDisplay() {

		updateValues();
		addRecords(false);
		// DW load used points from file

	}

	public void updateValues() {

		int count = mTabbedPane.getTabCount();
		int pointsSpent = 0;

		for (int i = 0; i < count; i++) {
			Component comp = mTabbedPane.getComponent(i);
			if (comp != null) {
				DeterminationTab tab = (DeterminationTab) comp;
				tab.loadDisplay();
				pointsSpent += tab.getDPPerWeekTabTotal();
			}
		}
		mUsedDPPoints = pointsSpent;
		mTotalDPPoints = getDeterminationPoints();
		mRemainingDPPoints = mTotalDPPoints - mUsedDPPoints;
		// DW ___Need to update labels
		mUsedDPPointsLabel.setText(String.valueOf(mUsedDPPoints));
		mTotalDPPointsLabel.setText(String.valueOf(mTotalDPPoints));
		mRemainingDPPointsLabel.setText(String.valueOf(mRemainingDPPoints));

	}

	public void addRecords(boolean clear) {
		for (AttributeDeterminationRecord record : DeterminationList.getAttribRecords()) {
			AttributesTab tab = (AttributesTab) mTabbedPane.getComponent(0);
			if (clear) {
				clear = false;
				tab.clearTab();
			}
			tab.addRecord(record);
		}

		for (LanguageDeterminationRecord record : DeterminationList.getLanguageRecords()) {
			//			LanguageTab tab = (LanguageTab) mTabbedPane.getComponent(1);
			//			if (clear) {
			//				clear = false;
			//				tab.clearTab();
			//			}
			//			tab.addRecord(record);
		}

		for (MagicSpellDeterminationRecord record : DeterminationList.getMagicSpellRecords()) {
			//			MagicSpellTab tab = (MagicSpellTab) mTabbedPane.getComponent(2);
			//			if (clear) {
			//				clear = false;
			//				tab.clearTab();
			//			}
			//			tab.addRecord(record);
		}

		for (WeaponProficiencyDeterminationRecord record : DeterminationList.getWeaponRecords()) {
			WeaponProficiencyTab tab = (WeaponProficiencyTab) mTabbedPane.getComponent(3);
			if (clear) {
				clear = false;
				tab.clearTab();
			}
			tab.addRecord(record);
		}

		for (SkillDeterminationRecord record : DeterminationList.getSkillRecords()) {
			//			SkillTab tab = (SkillTab) mTabbedPane.getComponent(4);
			//			if (clear) {
			//				clear = false;
			//				tab.clearTab();
			//			}
			//			tab.addRecord(record);
		}

		ACS.getInstance().getCharacterSheet().getDeterminationPointsDisplay().updateValues();

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
