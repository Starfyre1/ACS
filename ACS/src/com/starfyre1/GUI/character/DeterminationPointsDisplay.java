/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.interfaces.LevelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class DeterminationPointsDisplay extends TKTitledDisplay implements LevelListener, ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		DETERMINATION_POINTS_TITLE		= "Determination Points";																												//$NON-NLS-1$

	private static final String		PHYSICAL_TAB_TITLE				= "Attributes";																															//$NON-NLS-1$
	private static final String		LANGUAGE_TAB_TITLE				= "Languages";																															//$NON-NLS-1$
	private static final String		MAGIC_SPELL_TAB_TITLE			= "Magic Spells";																														//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																												//$NON-NLS-1$
	private static final String		SKILL_TAB_TITLE					= "Skills";																																//$NON-NLS-1$
	private static final String		TEACHER_TAB_TITLE				= "Teacher";																															//$NON-NLS-1$

	private static final String		PHYSICAL_TAB_TOOLTIP			= "Attributes";																															//$NON-NLS-1$
	private static final String		LANGUAGE_TAB_TOOLTIP			= "Languages";																															//$NON-NLS-1$
	private static final String		MAGIC_SPELL_TAB_TOOLTIP			= "Magic";																																//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_TAB_TOOLTIP	= "Weapon";																																//$NON-NLS-1$
	private static final String		SKILL_TAB_TOOLTIP				= "Skills";																																//$NON-NLS-1$
	private static final String		TEACHER_TAB_TOOLTIP				= "Teacher";																															//$NON-NLS-1$

	private static final String		PHYSICAL_COST					= "Cost: 50";																															//$NON-NLS-1$
	private static final String		LANGUAGE_COST					= "Cost: 40 (immersive) or 80 (Tutor)";																									//$NON-NLS-1$
	private static final String		MAGIC_SPELL_COST				= "Cost: 6 X (spell power + 1/squared)";																								//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_COST			= "Cost: 40";																															//$NON-NLS-1$
	private static final String		SKILL_COST						= "Cost: 40-60";																														//$NON-NLS-1$

	private static final String		PHYSICAL_COST2					= "Maintain: 1 DP / week";																												//$NON-NLS-1$
	private static final String		LANGUAGE_COST2					= "Maintain: For fluent is 1 DP / week";																								//$NON-NLS-1$
	private static final String		MAGIC_SPELL_COST2				= "";																																	//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_COST2		= "";																																	//$NON-NLS-1$
	private static final String		SKILL_COST2						= "Maintain: 1 DP / week";																												//$NON-NLS-1$

	private static final String		PHYSICAL_TITLE					= "To raise your physical statistics:";																									//$NON-NLS-1$
	private static final String		LANGUAGE_TITLE					= "To learn a new language:";																											//$NON-NLS-1$
	private static final String		MAGIC_SPELL_TITLE				= "To research a new magical spell:";																									//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_TITLE		= "To learn a new weapon proficiency:";																									//$NON-NLS-1$
	private static final String		SKILL_TITLE						= "To learn a skill:";																													//$NON-NLS-1$

	private static final String		PHYSICAL_TITLE2					= "Success: 1D20 + 1/2 level > stat";																									//$NON-NLS-1$
	private static final String		LANGUAGE_TITLE2					= "Success: 1D20 - 1/4 level < Wisdom";																									//$NON-NLS-1$
	private static final String		MAGIC_SPELL_TITLE2				= "Success: TBD";																														//$NON-NLS-1$
	private static final String		WEAPON_PROFICIENCY_TITLE2		= "Success: 1D20 < Dexerity";																											//$NON-NLS-1$
	private static final String		SKILL_TITLE2					= "Success: 1D20 < Intelligence";																										//$NON-NLS-1$

	private static final String		POINTS_WEEK_LABEL				= "Remaining Points / Week";																											//$NON-NLS-1$
	//	private static final String		ATTRIBUTES_LABEL				= "Attributes";																															//$NON-NLS-1$
	//	private static final String		LANGUAGES_LABEL					= "Languages";																															//$NON-NLS-1$
	//	private static final String		WEAPONS_LABEL					= "Weapons";																															//$NON-NLS-1$

	private static final String		ASSIGN_POINTS					= "Assign Points";																														//$NON-NLS-1$

	private static final Dimension	PREFERRED_SIZE					= new Dimension(600, 1000);

	private static final String		PHYSICAL_DESCRIPTION			= "A stat cannot be raised more than (3) points, or above (18).\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "There is a 10% chance per week that the stat will drop down to the original number if not maintained.  This chance is cumulative.";													//$NON-NLS-1$

	private static final String		LANGUAGE_DESCRIPTION			= "To learn the language fluently, the character must learn the language again, and again successfully roll below their Wisdom stat";	// //$NON-NLS-1$

	private static final String		MAGIC_SPELL_DESCRIPTION			= "A power 3 spell would cost 96 D.P.'s ((3+1) Squared, times 6).\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "For most Mages there is also a monitory investment.\r\n"																																// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The success rate is outlined in the Research Section of the Magic System.";																											//$NON-NLS-1$

	private static final String		WEAPON_PROFICIENCY_DESCRIPTION	= "The character must first find a teacher with a higher Hit Bonus than the their own.\r\n"												// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The character gains 1/4 the difference between the teachers Hit Bonus and their own.\r\n"																							// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The bonus will apply only when the character is using that particular weapon.";																										//$NON-NLS-1$

	private static final String		SKILLS_DESCRIPTION				= "The character must find a teacher with the skill they want at the Highest Skill level they can find.\r\n"							// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "To further increase the skill it will require further training and D.P. assignment";																									//$NON-NLS-1$

	private static final String		TEACHERS_DESCRIPTION			= "The payment of Teachers will be up to the Game Masters running that particular campaign.\r\n"										// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "Remember that these people will not be very cheap, they are usually very special people whose time is very precious.\r\n"															// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "But these are up to you, if a character starts selling there own time to pupils for weapons training, don't let it worry you unless it starts to get in the way of your campaign.";	//$NON-NLS-1$

	//	private static final int		ROWS							= 9;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField				mPointsPerWeekField;
	private JButton					mSpendPoints;
	private JTabbedPane				mTabbedPane;

	JCheckBox						mStrCheckBox;
	JCheckBox						mConCheckBox;
	JCheckBox						mWisCheckBox;
	JCheckBox						mDexCheckBox;
	JCheckBox						mBowCheckBox;

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
		} else if (source instanceof JCheckBox) {
			System.out.println(((JCheckBox) source).getText());
		}
	}

	/**
	 *
	 */
	private void assignPoints() {
		JFrame frame = ((CharacterSheet) getOwner()).getFrame();
		JDialog dialog = new JDialog(frame, true);
		dialog.setPreferredSize(PREFERRED_SIZE);

		mTabbedPane = new JTabbedPane();

		JComponent physicalTab = makePhysicalTab();
		JComponent languageTab = makeLanguageTab();
		JComponent magicSpellTab = makeMagicSpellTab();
		JComponent weaponProficiencyTab = makeWeaponProficiencyTab();
		JComponent skillTab = makeSkillTab();
		JComponent teacherTab = makeTeacherTab();

		mTabbedPane.addTab(PHYSICAL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, physicalTab, PHYSICAL_TAB_TOOLTIP);
		mTabbedPane.addTab(LANGUAGE_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, languageTab, LANGUAGE_TAB_TOOLTIP);
		mTabbedPane.addTab(MAGIC_SPELL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, magicSpellTab, MAGIC_SPELL_TAB_TOOLTIP);
		mTabbedPane.addTab(WEAPON_PROFICIENCY_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, weaponProficiencyTab, WEAPON_PROFICIENCY_TAB_TOOLTIP);
		mTabbedPane.addTab(SKILL_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, skillTab, SKILL_TAB_TOOLTIP);
		mTabbedPane.addTab(TEACHER_TAB_TITLE, CharacterSheet.DETERMINATION_ICON, teacherTab, TEACHER_TAB_TOOLTIP);

		mTabbedPane.setMnemonicAt(0, KeyEvent.VK_P);
		mTabbedPane.setMnemonicAt(1, KeyEvent.VK_L);
		mTabbedPane.setMnemonicAt(2, KeyEvent.VK_M);
		mTabbedPane.setMnemonicAt(3, KeyEvent.VK_W);
		mTabbedPane.setMnemonicAt(4, KeyEvent.VK_S);
		mTabbedPane.setMnemonicAt(5, KeyEvent.VK_T);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mTabbedPane, BorderLayout.CENTER);

		dialog.add(wrapper);

		//		Container pane = mFrame.getContentPane();
		//		pane.add(wrapper, BorderLayout.CENTER);

		dialog.pack();
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}

	private JComponent createPage(JPanel lowerPanel, String description, String title1, String title2, String cost1, String cost2) {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel titleLabel1 = new JLabel(title1);
		JLabel titleLabel2 = new JLabel(title2);
		titleWrapper.add(titleLabel1);
		titleWrapper.add(titleLabel2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel costLabel1 = new JLabel(cost1);
		JLabel costLabel2 = new JLabel(cost2);
		costWrapper.add(costLabel1);
		costWrapper.add(costLabel2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea descriptionTextArea = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		descriptionTextArea.setBorder(cb);
		descriptionTextArea.getInsets(new Insets(5, 5, 5, 5));
		descriptionTextArea.setBackground(CharacterSheet.LABEL_BACKGROUND);
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setText(description);

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(descriptionTextArea, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		if (lowerPanel != null) {
			page.add(lowerPanel, BorderLayout.CENTER);
		}

		return page;

	}

	private JComponent makeTeacherTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, TEACHER_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(null, TEACHERS_DESCRIPTION, "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		};

		return display;
	}

	private JComponent makeSkillTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, SKILL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(null, SKILLS_DESCRIPTION, SKILL_TITLE, SKILL_TITLE2, SKILL_COST, SKILL_COST2);
			}
		};

		return display;
	}

	private JComponent makeWeaponProficiencyTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, WEAPON_PROFICIENCY_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(null, WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TITLE, WEAPON_PROFICIENCY_TITLE2, WEAPON_PROFICIENCY_COST, WEAPON_PROFICIENCY_COST2);
			}
		};

		return display;
	}

	private JComponent makeMagicSpellTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, MAGIC_SPELL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(null, MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TITLE, MAGIC_SPELL_TITLE2, MAGIC_SPELL_COST, MAGIC_SPELL_COST2);
			}
		};

		return display;
	}

	private JComponent makeLanguageTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, LANGUAGE_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(null, LANGUAGE_DESCRIPTION, LANGUAGE_TITLE, LANGUAGE_TITLE2, LANGUAGE_COST, LANGUAGE_COST2);
			}
		};

		return display;
	}

	private JComponent makePhysicalTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, PHYSICAL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return createPage(createLowerPhysicalPanel(), PHYSICAL_DESCRIPTION, PHYSICAL_TITLE, PHYSICAL_TITLE2, PHYSICAL_COST, PHYSICAL_COST2);
			}
		};

		return display;
	}

	private JPanel createLowerPhysicalPanel() {
		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		mStrCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.STRENGTH, false, this);
		mConCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.CONSTITUTION, false, this);
		mWisCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.WISDOM, false, this);
		mDexCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.DEXTERITY, false, this);
		mBowCheckBox = TKComponentHelpers.createCheckBox(AttributesRecord.BOW_SKILL, false, this);

		center.add(mStrCheckBox);
		center.add(mConCheckBox);
		center.add(mWisCheckBox);
		center.add(mDexCheckBox);
		center.add(mBowCheckBox);

		updateButtonState();

		return center;

	}

	private void updateButtonState() {
		AttributesRecord attribs = ((CharacterSheet) getOwner()).getAttributesRecord();
		// DW Also need to make sure we can't increase it more than 3 times... will need to check against DeterminationPointsRecord when created
		mStrCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.STR) < 18);
		mConCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.CON) < 18);
		mWisCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.WIS) < 18);
		mDexCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.DEX) < 18);
		mBowCheckBox.setEnabled(attribs.getModifiedStat(AttributesRecord.BOW) < 18);
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

	//	private JPanel createAttributesPanel() {
	//		JPanel panel = new JPanel(new BorderLayout(5, 5));
	//
	//		JPanel wrapper = new JPanel();
	//		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
	//		wrapper.setLayout(bl);
	//
	//		//		JTextField[] field = new JTextField[ROWS];
	//		JLabel[] field = new JLabel[ROWS];
	//		JTextField[] bonus = new JTextField[ROWS];
	//
	//		for (int i = 0; i < ROWS; i++) {
	//			JPanel inner = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
	//			field[i] = new JLabel(AttributesRecord.mStatNames[i]);
	//			bonus[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
	//			inner.add(field[i]);
	//			inner.add(new JLabel("+")); //$NON-NLS-1$
	//			inner.add(bonus[i]);
	//
	//			wrapper.add(inner);
	//		}
	//
	//		panel.add(new JLabel(ATTRIBUTES_LABEL, SwingConstants.RIGHT), BorderLayout.PAGE_START);
	//		panel.add(wrapper, BorderLayout.CENTER);
	//
	//		return panel;
	//	}
	//
	//	private JPanel createLanguagesPanel() {
	//		JPanel panel = new JPanel(new BorderLayout(0, 5));
	//
	//		JPanel wrapper = new JPanel();
	//		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
	//
	//		JTextField[] field = new JTextField[ROWS];
	//		for (int i = 0; i < ROWS; i++) {
	//			field[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20);
	//			wrapper.add(field[i]);
	//		}
	//
	//		panel.add(new JLabel(LANGUAGES_LABEL, SwingConstants.CENTER), BorderLayout.PAGE_START);
	//		panel.add(wrapper, BorderLayout.CENTER);
	//
	//		return panel;
	//	}
	//
	//	private JPanel createWeaponsPanel() {
	//		JPanel panel = new JPanel(new BorderLayout(5, 5));
	//
	//		JPanel wrapper = new JPanel();
	//		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
	//		wrapper.setLayout(bl);
	//
	//		JTextField[] field = new JTextField[ROWS];
	//		JTextField[] bonus = new JTextField[ROWS];
	//
	//		for (int i = 0; i < ROWS; i++) {
	//			JPanel inner = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	//			field[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
	//			bonus[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
	//			inner.add(field[i]);
	//			inner.add(new JLabel("+")); //$NON-NLS-1$
	//			inner.add(bonus[i]);
	//
	//			wrapper.add(inner);
	//		}
	//
	//		panel.add(new JLabel(WEAPONS_LABEL, SwingConstants.CENTER), BorderLayout.PAGE_START);
	//		panel.add(wrapper, BorderLayout.CENTER);
	//
	//		return panel;
	//	}

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
