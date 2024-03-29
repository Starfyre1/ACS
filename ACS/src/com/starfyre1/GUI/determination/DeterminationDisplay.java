/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.determination.AttributeDeterminationRecord;
import com.starfyre1.dataModel.determination.LanguageDeterminationRecord;
import com.starfyre1.dataModel.determination.MagicSpellDeterminationRecord;
import com.starfyre1.dataModel.determination.SkillDeterminationRecord;
import com.starfyre1.dataModel.determination.TeacherDeterminationRecord;
import com.starfyre1.dataModel.determination.WeaponDeterminationRecord;
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

public class DeterminationDisplay extends TKTitledDisplay implements LevelListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		DETERMINATION_TITLE		= "Determination Points";		//$NON-NLS-1$

	private static final String		TOTAL_POINTS_WEEK_LABEL			= "Total:";						//$NON-NLS-1$
	private static final String		PERM_USED_POINTS_WEEK_LABEL		= "Perm:";						//$NON-NLS-1$
	private static final String		USED_POINTS_WEEK_LABEL			= "Used:";						//$NON-NLS-1$
	private static final String		REMAINING_POINTS_WEEK_LABEL		= "Left:";						//$NON-NLS-1$

	private static final String		TOTAL_POINTS_WEEK_TOOLTIP		= "Total Points per Week";		//$NON-NLS-1$
	private static final String		PERM_USED_POINTS_WEEK_TOOLTIP	= "Perm Used Points per Week";	//$NON-NLS-1$
	private static final String		USED_POINTS_WEEK_TOOLTIP		= "Used Points per Week";		//$NON-NLS-1$
	private static final String		REMAINING_POINTS_WEEK_TOOLTIP	= "Remaining Points per Week";	//$NON-NLS-1$

	//	private static final String		ASSIGN_POINTS				= "Assign Points";				//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE					= new Dimension(600, 285);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int						mTotalDPPoints;
	private int						mPermUsedDPPoints;
	private int						mUsedDPPoints;
	private int						mRemainingDPPoints;
	private JLabel					mTotalDPPointsLabel;
	private JLabel					mPermUsedDPPointsLabel;
	private JLabel					mUsedDPPointsLabel;
	private JLabel					mRemainingDPPointsLabel;
	private JTabbedPane				mTabbedPane;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public DeterminationDisplay(CharacterSheet owner) {
		super(owner, DETERMINATION_TITLE);

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
		mPermUsedDPPointsLabel = new JLabel(String.valueOf(mPermUsedDPPoints));
		mPermUsedDPPointsLabel.setToolTipText(PERM_USED_POINTS_WEEK_TOOLTIP);
		JLabel PermUsedTitle = new JLabel(PERM_USED_POINTS_WEEK_LABEL);
		PermUsedTitle.setToolTipText(PERM_USED_POINTS_WEEK_TOOLTIP);
		innerWrapper.add(PermUsedTitle);
		innerWrapper.add(mPermUsedDPPointsLabel);
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
		JComponent weaponProficiencyTab = new WeaponTab(this);
		JComponent skillTab = new SkillTab(this);
		JComponent teacherTab = new TeacherTab(this);

		mTabbedPane.addTab(AttributesTab.ATTRIBUTES_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, attributesTab, AttributesTab.ATTRIBUTES_TAB_TOOLTIP);
		mTabbedPane.addTab(LanguageTab.LANGUAGE_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, languageTab, LanguageTab.LANGUAGE_TAB_TOOLTIP);
		mTabbedPane.addTab(MagicSpellTab.MAGIC_SPELL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, magicSpellTab, MagicSpellTab.MAGIC_SPELL_TAB_TOOLTIP);
		mTabbedPane.addTab(WeaponTab.WEAPON_PROFICIENCY_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, weaponProficiencyTab, WeaponTab.WEAPON_PROFICIENCY_TAB_TOOLTIP);
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
		//		mPermUsedDPPoints = ???
		mTotalDPPoints = getDeterminationPoints();
		mRemainingDPPoints = mTotalDPPoints - mPermUsedDPPoints - mUsedDPPoints;
		// DW ___Need to update labels
		mUsedDPPointsLabel.setText(String.valueOf(mUsedDPPoints));
		mPermUsedDPPointsLabel.setText(String.valueOf(mPermUsedDPPoints));
		mTotalDPPointsLabel.setText(String.valueOf(mTotalDPPoints));
		mRemainingDPPointsLabel.setText(String.valueOf(mRemainingDPPoints));

	}

	public void addRecords(boolean clear) {
		if (clear) {
			clearRecords();
		}

		for (AttributeDeterminationRecord record : DeterminationList.getAttribRecords()) {
			((AttributesTab) mTabbedPane.getComponent(0)).addRecord(record);
		}

		for (LanguageDeterminationRecord record : DeterminationList.getLanguageRecords()) {
			((LanguageTab) mTabbedPane.getComponent(1)).addRecord(record);
		}

		for (MagicSpellDeterminationRecord record : DeterminationList.getMagicSpellRecords()) {
			((MagicSpellTab) mTabbedPane.getComponent(2)).addRecord(record);
		}

		for (WeaponDeterminationRecord record : DeterminationList.getWeaponRecords()) {
			((WeaponTab) mTabbedPane.getComponent(3)).addRecord(record);
		}

		for (SkillDeterminationRecord record : DeterminationList.getSkillRecords()) {
			((SkillTab) mTabbedPane.getComponent(4)).addRecord(record);
		}

		for (TeacherDeterminationRecord record : DeterminationList.getTeachersRecords()) {
			((TeacherTab) mTabbedPane.getComponent(5)).addRecord(record);
		}

		for (AttributeDeterminationRecord record : DeterminationList.getAttribRecordsCompleted()) {
			((AttributesTab) mTabbedPane.getComponent(0)).addRecord(record);
		}

		for (LanguageDeterminationRecord record : DeterminationList.getLanguageRecordsCompleted()) {
			((LanguageTab) mTabbedPane.getComponent(1)).addRecord(record);
		}

		for (MagicSpellDeterminationRecord record : DeterminationList.getMagicSpellRecordsCompleted()) {
			((MagicSpellTab) mTabbedPane.getComponent(2)).addRecord(record);
		}

		for (WeaponDeterminationRecord record : DeterminationList.getWeaponRecordsCompleted()) {
			((WeaponTab) mTabbedPane.getComponent(3)).addRecord(record);
		}

		for (SkillDeterminationRecord record : DeterminationList.getSkillRecordsCompleted()) {
			((SkillTab) mTabbedPane.getComponent(4)).addRecord(record);
		}

		ACS.getInstance().getCharacterSheet().getDeterminationPointsDisplay().updateValues();

	}

	public void clearRecords() {
		((AttributesTab) mTabbedPane.getComponent(0)).clearTab();
		((LanguageTab) mTabbedPane.getComponent(1)).clearTab();
		((MagicSpellTab) mTabbedPane.getComponent(2)).clearTab();
		((WeaponTab) mTabbedPane.getComponent(3)).clearTab();
		((SkillTab) mTabbedPane.getComponent(4)).clearTab();
		((TeacherTab) mTabbedPane.getComponent(5)).clearTab();
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

	public int getPermUsedDPPoints() {
		return mPermUsedDPPoints;
	}

	public void setPermUsedDPPoints(int permUsed) {
		mPermUsedDPPoints = permUsed;
		updateRecord();
	}

	public void adjustPermUsedDPPoints(int adjustment) {
		mPermUsedDPPoints += adjustment;
		updateRecord();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
