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

	private static final String		PHYSICAL						= "A stat cannot be raised more than (3) points, or above (18).\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "There is a 10% chance per week that the stat will drop down to the original number if not maintained.  This chance is cumulative.";													//$NON-NLS-1$

	private static final String		LANGUAGES						= "To learn the language fluently, the character must learn the language again, and again successfully roll below their Wisdom stat";	// //$NON-NLS-1$

	private static final String		MAGIC_SPELL						= "A power 3 spell would cost 96 D.P.'s ((3+1) Squared, times 6).\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "For most Mages there is also a monitory investment.\r\n"																																// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The success rate is outlined in the Research Section of the Magic System.";																											//$NON-NLS-1$

	private static final String		WEAPON_PROFICIENCY				= "The character must first find a teacher with a higher Hit Bonus than the their own.\r\n"												// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The character gains 1/4 the difference between the teachers Hit Bonus and their own.\r\n"																							// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "The bonus will apply only when the character is using that particular weapon.";																										//$NON-NLS-1$

	private static final String		SKILLS							= "The character must find a teacher with the skill they want at the Highest Skill level they can find.\r\n"							// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "To further increase the skill it will require further training and D.P. assignment";																									//$NON-NLS-1$

	private static final String		TEACHERS						= "The payment of Teachers will be up to the Game Masters running that particular campaign.\r\n"										// //$NON-NLS-1$
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

	private JComponent makeTeacherTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, TEACHER_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return teachersPage();
			}
		};

		return display;
	}

	private JComponent teachersPage() {
		JPanel page = new JPanel(new BorderLayout());

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(TEACHERS);

		page.add(description, BorderLayout.NORTH);

		return page;

	}

	private JComponent makeSkillTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, SKILL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return skillsPage();
			}
		};

		return display;
	}

	private JComponent skillsPage() {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel title = new JLabel(SKILL_TITLE);
		JLabel title2 = new JLabel(SKILL_TITLE2);
		titleWrapper.add(title);
		titleWrapper.add(title2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);
		JLabel cost = new JLabel(SKILL_COST);
		JLabel cost2 = new JLabel(SKILL_COST2);
		costWrapper.add(cost);
		costWrapper.add(cost2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(SKILLS);

		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(description, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		page.add(center, BorderLayout.CENTER);

		return page;

	}

	private JComponent makeWeaponProficiencyTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, WEAPON_PROFICIENCY_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return weaponProficiencyPage();
			}
		};

		return display;
	}

	private JComponent weaponProficiencyPage() {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel title = new JLabel(WEAPON_PROFICIENCY_TITLE);
		JLabel title2 = new JLabel(WEAPON_PROFICIENCY_TITLE2);
		titleWrapper.add(title);
		titleWrapper.add(title2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel cost = new JLabel(WEAPON_PROFICIENCY_COST);
		JLabel cost2 = new JLabel(WEAPON_PROFICIENCY_COST2);
		costWrapper.add(cost);
		costWrapper.add(cost2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(WEAPON_PROFICIENCY);

		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(description, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		page.add(center, BorderLayout.CENTER);

		return page;

	}

	private JComponent makeMagicSpellTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, MAGIC_SPELL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return magicSpellsPage();
			}
		};

		return display;
	}

	private JComponent magicSpellsPage() {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel title = new JLabel(MAGIC_SPELL_TITLE);
		JLabel title2 = new JLabel(MAGIC_SPELL_TITLE2);
		titleWrapper.add(title);
		titleWrapper.add(title2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel cost = new JLabel(MAGIC_SPELL_COST);
		JLabel cost2 = new JLabel(MAGIC_SPELL_COST2);
		costWrapper.add(cost);
		costWrapper.add(cost2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(MAGIC_SPELL);

		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(description, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		page.add(center, BorderLayout.CENTER);

		return page;

	}

	private JComponent makeLanguageTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, LANGUAGE_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return languagesPage();
			}
		};

		return display;
	}

	private JComponent languagesPage() {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel title = new JLabel(LANGUAGE_TITLE);
		JLabel title2 = new JLabel(LANGUAGE_TITLE2);
		titleWrapper.add(title);
		titleWrapper.add(title2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel cost = new JLabel(LANGUAGE_COST);
		JLabel cost2 = new JLabel(LANGUAGE_COST2);
		costWrapper.add(cost);
		costWrapper.add(cost2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(LANGUAGES);

		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(description, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		page.add(center, BorderLayout.CENTER);

		return page;

	}

	private JComponent makePhysicalTab() {
		TKTitledDisplay display = new TKTitledDisplay(this, PHYSICAL_TAB_TITLE) {

			@Override
			protected void loadDisplay() {
				//DW to do
			}

			@Override
			protected Component createDisplay() {
				return physicalPage();
			}
		};

		return display;
	}

	private Component physicalPage() {
		JPanel page = new JPanel(new BorderLayout());

		JPanel topWrapper = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel title = new JLabel(PHYSICAL_TITLE);
		JLabel title2 = new JLabel(PHYSICAL_TITLE2);
		titleWrapper.add(title);
		titleWrapper.add(title2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel cost = new JLabel(PHYSICAL_COST);
		JLabel cost2 = new JLabel(PHYSICAL_COST2);
		costWrapper.add(cost);
		costWrapper.add(cost2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea description = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		description.setBorder(cb);
		description.getInsets(new Insets(5, 5, 5, 5));
		description.setBackground(CharacterSheet.LABEL_BACKGROUND);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(PHYSICAL);

		JPanel center = new JPanel();
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		center.setBorder(new EmptyBorder(5, 15, 5, 5));

		JCheckBox str = TKComponentHelpers.createCheckBox(AttributesRecord.STRENGTH, false, this);
		JCheckBox con = TKComponentHelpers.createCheckBox(AttributesRecord.CONSTITUTION, false, this);
		JCheckBox wis = TKComponentHelpers.createCheckBox(AttributesRecord.WISDOM, false, this);
		JCheckBox dex = TKComponentHelpers.createCheckBox(AttributesRecord.DEXTERITY, false, this);
		JCheckBox bow = TKComponentHelpers.createCheckBox(AttributesRecord.BOW_SKILL, false, this);

		center.add(str);
		center.add(con);
		center.add(wis);
		center.add(dex);
		center.add(bow);

		topWrapper.add(top, BorderLayout.NORTH);
		topWrapper.add(description, BorderLayout.CENTER);

		page.add(topWrapper, BorderLayout.NORTH);
		page.add(center, BorderLayout.CENTER);

		return page;

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
