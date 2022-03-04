/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.interfaces.LevelListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class DeterminationPointsDisplay extends TKTitledDisplay implements LevelListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		DETERMINATION_POINTS_TITLE	= "Determination Points";		//$NON-NLS-1$

	private static final String		POINTS_WEEK_LABEL			= "Remaining Points this Week";	//$NON-NLS-1$

	//	private static final String		ASSIGN_POINTS				= "Assign Points";				//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE				= new Dimension(600, 285);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel					mPointsPerWeekLabel;
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
	public void updateRecord() {
		updateValues();
	}

	private JPanel getDeterminationDisplay() {
		mTabbedPane = new JTabbedPane();

		JComponent attributesTab = new AttributesTab(this);
		JComponent languageTab = new LanguageTab(this);
		JComponent magicSpellTab = new MagicSpellTab(this);
		JComponent weaponProficiencyTab = new WeaponProficiencyTab(this);
		JComponent skillTab = new SkillTab(this);
		JComponent teacherTab = new TeacherTab(this);

		mTabbedPane.addTab(AttributesTab.PHYSICAL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, attributesTab, AttributesTab.PHYSICAL_TAB_TOOLTIP);
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

	private void updateValues() {
		mPointsPerWeekLabel.setText(TKStringHelpers.EMPTY_STRING + (getDeterminationPoints() - getDeterminationPointsSpent()));

		int count = mTabbedPane.getTabCount();

		for (int i = 0; i < count; i++) {
			Component comp = mTabbedPane.getComponent(i);
			if (comp != null) {
				((DeterminationTab) comp).loadDisplay();
			}
		}
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

		mPointsPerWeekLabel = new JLabel();

		topWrapper.add(mPointsPerWeekLabel);
		topWrapper.add(new JLabel(POINTS_WEEK_LABEL));

		JPanel mainWrapper = new JPanel(new GridLayout(1, 3, 5, 0));
		mainWrapper.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainWrapper.add(getDeterminationDisplay());

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
