/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.GUI.character.AttackTotalsDisplay;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.startup.SystemInfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CombatChart extends JDialog implements DocumentListener, ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	COMBAT_CHART		= "Combat Chart";		//$NON-NLS-1$

	public static final String	RIGHT				= "Primary";			//$NON-NLS-1$
	public static final String	LEFT				= "Secondary";			//$NON-NLS-1$

	public static final String	RIGHT_OTHER			= "Primary_Other";		//$NON-NLS-1$
	public static final String	LEFT_OTHER			= "Secondary_Other";	//$NON-NLS-1$

	public static final String	NONE				= "None";				//$NON-NLS-1$
	public static final String	WEAPON				= "Weapon";				//$NON-NLS-1$
	public static final String	SPELL				= "Spell";				//$NON-NLS-1$

	public static final String	RIGHT_HAND			= "Primary Hand";		//$NON-NLS-1$
	public static final String	LEFT_HAND			= "Secondary Hand";		//$NON-NLS-1$
	public static final String	INIT_BASE			= "Init. Base";			//$NON-NLS-1$
	public static final String	INITITIVE			= "Inititive";			//$NON-NLS-1$
	public static final String	INIT_TOTAL			= "Init. Total";		//$NON-NLS-1$
	public static final String	NEEDED_TO_HIT		= "Needed To Hit";		//$NON-NLS-1$
	public static final String	RIGHT_HIT_BONUS		= "P. Hit Bonus";		//$NON-NLS-1$
	public static final String	LEFT_HIT_BONUS		= "S. Hit Bonus";		//$NON-NLS-1$
	public static final String	RIGHT_OTHER_BONUSES	= "P. Other Bonus";		//$NON-NLS-1$
	public static final String	LEFT_OTHER_BONUSES	= "S. Other Bonus";		//$NON-NLS-1$
	public static final String	ROLL_TO_HIT			= "Roll To Hit";		//$NON-NLS-1$
	public static final String	MINIMUM_NEEDED		= "Minimum Needed";		//$NON-NLS-1$
	public static final String	TOTAL_TO_HIT		= "Total To HIt";		//$NON-NLS-1$
	public static final String	ADDITIONAL_D6		= "Additional #D6's";	//$NON-NLS-1$
	public static final String	CLOSE				= "Close";				//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mCharacter;
	private JFrame				mFrame;

	private TKPopupMenu			mRightHandPopupMenu;						// None, Weapon, Spell
	private TKPopupMenu			mLeftHandPopupMenu;
	private JTextField			mInitBaseField;
	private JTextField			mInitField;
	private JTextField			mInitTotalField;

	private JTextField			mToHitField;
	private JTextField			mRightHitBonusField;
	private JTextField			mLeftHitBonusField;
	private JTextField			mRightOtherBonusField;
	private JTextField			mLeftOtherBonusField;
	private JTextField			mRollField;
	private JTextField			mMinimumField;
	private JTextField			mTotalField;
	private JTextField			mAdditionalD6Field;

	private JRadioButton		mPrimaryWeapon;
	private JRadioButton		mSecondaryWeapon;

	private JButton				mCloseButton;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public CombatChart(CharacterSheet parent) {
		super(parent.getFrame(), COMBAT_CHART, false);

		mCharacter = parent;
		mFrame = parent.getFrame();

		((JPanel) getContentPane()).setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createDisplay();
		readValues();
		updateChart();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void createDisplay() {
		JLabel rightHandLabel = new JLabel(RIGHT_HAND, SwingConstants.RIGHT);
		JLabel leftHandLabel = new JLabel(LEFT_HAND, SwingConstants.RIGHT);
		JLabel initBaseLabel = new JLabel(INIT_BASE, SwingConstants.RIGHT);
		JLabel inititiveLabel = new JLabel(INITITIVE, SwingConstants.RIGHT);
		JLabel initTotalLabel = new JLabel(INIT_TOTAL, SwingConstants.RIGHT);
		JLabel neededToHitLabel = new JLabel(NEEDED_TO_HIT, SwingConstants.RIGHT);
		JLabel rightHitBonusLabel = new JLabel(RIGHT_HIT_BONUS, SwingConstants.RIGHT);
		JLabel leftHitBonusLabel = new JLabel(LEFT_HIT_BONUS, SwingConstants.RIGHT);
		JLabel rightOtherBonusLabel = new JLabel(RIGHT_OTHER_BONUSES, SwingConstants.RIGHT);
		JLabel leftOtherBonusLabel = new JLabel(LEFT_OTHER_BONUSES, SwingConstants.RIGHT);
		JLabel rollToHitLabel = new JLabel(ROLL_TO_HIT, SwingConstants.RIGHT);
		JLabel minimumNeededLabel = new JLabel(MINIMUM_NEEDED, SwingConstants.RIGHT);
		JLabel totalToHitLabel = new JLabel(TOTAL_TO_HIT, SwingConstants.RIGHT);
		JLabel additionalD6Label = new JLabel(ADDITIONAL_D6, SwingConstants.RIGHT);

		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();
		mRightHandPopupMenu = new TKPopupMenu(getMenu()); // None, Weapon, Spell
		mRightHandPopupMenu.setPreferredSize(new Dimension(mRightHandPopupMenu.getPreferredSize().width, 20));
		mLeftHandPopupMenu = new TKPopupMenu(getMenu()); // None, Weapon, Spell
		mLeftHandPopupMenu.setPreferredSize(new Dimension(mLeftHandPopupMenu.getPreferredSize().width, 20));
		mInitBaseField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mInitBaseField.setEditable(false);
		mInitField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, intFilter);
		mInitTotalField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mInitTotalField.setEditable(false);

		mToHitField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, intFilter);
		mRightHitBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mRightHitBonusField.setEditable(false);
		mLeftHitBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mLeftHitBonusField.setEditable(false);
		mRightOtherBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, intFilter);
		mLeftOtherBonusField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, intFilter);
		mRollField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20, this, intFilter);
		mMinimumField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mMinimumField.setEditable(false);
		mTotalField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mTotalField.setEditable(false);
		mAdditionalD6Field = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		mAdditionalD6Field.setEditable(false);

		JPanel initPanel = new JPanel(new GridLayout(0, 6, 5, 5));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JPanel weaponSelectionPanel = new JPanel(new FlowLayout());
		mPrimaryWeapon = new JRadioButton(RIGHT, true);
		mPrimaryWeapon.addActionListener(this);
		mSecondaryWeapon = new JRadioButton(LEFT);
		mSecondaryWeapon.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(mPrimaryWeapon);
		group.add(mSecondaryWeapon);

		initPanel.add(initBaseLabel);
		initPanel.add(mInitBaseField);
		initPanel.add(inititiveLabel);
		initPanel.add(mInitField);
		initPanel.add(initTotalLabel);
		initPanel.add(mInitTotalField);

		initPanel.add(neededToHitLabel);
		initPanel.add(mToHitField);
		initPanel.add(rightHandLabel);
		initPanel.add(mRightHandPopupMenu);
		initPanel.add(leftHandLabel);
		initPanel.add(mLeftHandPopupMenu);

		initPanel.add(rollToHitLabel);
		initPanel.add(mRollField);
		initPanel.add(rightHitBonusLabel);
		initPanel.add(mRightHitBonusField);
		initPanel.add(leftHitBonusLabel);
		initPanel.add(mLeftHitBonusField);

		initPanel.add(minimumNeededLabel);
		initPanel.add(mMinimumField);
		initPanel.add(rightOtherBonusLabel);
		initPanel.add(mRightOtherBonusField);
		initPanel.add(leftOtherBonusLabel);
		initPanel.add(mLeftOtherBonusField);

		initPanel.add(totalToHitLabel);
		initPanel.add(mTotalField);

		initPanel.add(additionalD6Label);
		initPanel.add(mAdditionalD6Field);

		weaponSelectionPanel.add(mPrimaryWeapon);
		weaponSelectionPanel.add(mSecondaryWeapon);

		mCloseButton = TKComponentHelpers.createButton(CLOSE, this);
		buttonPanel.add(weaponSelectionPanel);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mCloseButton);

		add(initPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		mRightHandPopupMenu.setPreferredSize(new Dimension(mRightHandPopupMenu.getPreferredSize().width, 20));

		pack();
		setLocationRelativeTo(mFrame);
		setVisible(true);
	}

	private JMenu getMenu() {
		// None, Weapon, Spell
		JMenu popupMenu = TKPopupMenu.createMenu(NONE);

		JMenuItem menuItem = new JMenuItem(NONE);
		menuItem.addActionListener(this);
		menuItem.setPreferredSize(new Dimension(95, 20));
		popupMenu.add(menuItem);

		ArrayList<WeaponRecord> weaponList = mCharacter.getEquippedWeaponRecords();
		if (weaponList.size() > 1) {
			JMenu weaponMenu = new JMenu(WEAPON);
			popupMenu.add(weaponMenu);
			for (WeaponRecord record : weaponList) {
				JMenuItem weapon = new JMenuItem(record.getName());
				weapon.addActionListener(this);
				weaponMenu.add(weapon);
			}
		} else if (weaponList.size() > 0) {
			JMenuItem weapon = new JMenuItem(weaponList.get(0).getName());
			weapon.addActionListener(this);
			popupMenu.add(weapon);
		}

		// DW ___Work on next
		// DW add spells to list
		//		ArrayList<SpellRecord> spellList = mCharacter.getAllKnownSpells();
		JMenu spellMenu = new JMenu(SPELL);
		spellMenu.setEnabled(false);
		popupMenu.add(spellMenu);

		return popupMenu;
	}

	/**
	 *
	 */
	private void updateChart() {

		int rightHitBonus = 0;
		int leftHitBonus = 0;

		int rightInit = 0;
		int leftInit = 0;

		String right = mRightHandPopupMenu.getSelectedItem();
		String left = mLeftHandPopupMenu.getSelectedItem();

		boolean primary = false;
		boolean secondary = false;

		ArrayList<WeaponRecord> weaponList = mCharacter.getEquippedWeaponRecords();

		AttackTotalsDisplay attackTotalsDisplay = mCharacter.getAttackTotalsDisplay();

		// Primary (right) Hand
		if (!right.equals(NONE)) {
			for (WeaponRecord record : weaponList) {
				if (record.getName().equals(mRightHandPopupMenu.getSelectedItem())) {
					rightHitBonus = attackTotalsDisplay.getHitBonus(record);
					mRightHitBonusField.setText(String.valueOf(rightHitBonus));
					rightInit = attackTotalsDisplay.getSpeed(record);
					primary = true;
					break;
				}
			}
		}
		if (!primary) {
			rightHitBonus = 0;
			mRightHitBonusField.setText("0"); //$NON-NLS-1$
			mRightOtherBonusField.getDocument().removeDocumentListener(this);
			mRightOtherBonusField.setText("0"); //$NON-NLS-1$
			mRightOtherBonusField.getDocument().addDocumentListener(this);
			rightInit = 0;
		}

		// Secondary (left) Hand
		if (!left.equals(NONE)) {
			for (WeaponRecord record : weaponList) {
				if (record.getName().equals(mLeftHandPopupMenu.getSelectedItem())) {
					leftHitBonus = attackTotalsDisplay.getHitBonus(record);
					mLeftHitBonusField.setText(String.valueOf(leftHitBonus));
					leftInit = attackTotalsDisplay.getSpeed(record);
					secondary = true;
					break;
				}
			}
		}
		if (!secondary) {
			leftHitBonus = 0;
			mLeftHitBonusField.setText("0"); //$NON-NLS-1$
			mLeftOtherBonusField.getDocument().removeDocumentListener(this);
			mLeftOtherBonusField.setText("0"); //$NON-NLS-1$
			mLeftOtherBonusField.getDocument().addDocumentListener(this);
			leftInit = 0;
		}

		if (rightInit == 0 && leftInit == 0) {
			mInitBaseField.setText("Use Type Speed"); //$NON-NLS-1$
			int init = TKStringHelpers.getIntValue(mInitField.getText(), 0);
			mInitTotalField.setText(getTotalAttacks(init, primary && secondary));
		} else {
			// use lowest value for multiple weapons
			int initBase = 0;
			if (primary && secondary) {
				initBase = leftInit > rightInit ? rightInit : leftInit;
			} else {
				initBase = primary ? rightInit : leftInit;
			}
			mInitBaseField.setText(String.valueOf(initBase));
			int init = initBase + TKStringHelpers.getIntValue(mInitField.getText(), 0);
			mInitTotalField.setText(getTotalAttacks(init, primary && secondary));
		}

		boolean isPrimaryWeapon = mPrimaryWeapon.isSelected();
		int subTotal = TKStringHelpers.getIntValue(isPrimaryWeapon ? mRightHitBonusField.getText() : mLeftHitBonusField.getText(), 0);
		subTotal += TKStringHelpers.getIntValue(isPrimaryWeapon ? mRightOtherBonusField.getText() : mLeftOtherBonusField.getText(), 0);
		int total = subTotal + TKStringHelpers.getIntValue(mRollField.getText(), 0);
		mTotalField.setText(String.valueOf(total));

		int neededToHit = TKStringHelpers.getIntValue(mToHitField.getText(), 0);
		mMinimumField.setText(String.valueOf(neededToHit - subTotal));

		int addDie = (total - neededToHit) / 30;
		mAdditionalD6Field.setText(String.valueOf(addDie > 0 ? addDie : 0));

		if (neededToHit > total) {
			setTitle(COMBAT_CHART + "    " + "Missed!"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			setTitle(COMBAT_CHART + "    " + "Hit!"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	private String getTotalAttacks(int init, boolean twoWeapons) {
		String numAttacks = String.valueOf(init);
		int span = twoWeapons ? 12 : 15;

		if (init > span) {
			numAttacks += "   (" + init; //$NON-NLS-1$
			init -= span;
			while (init > 0) {
				numAttacks += ", " + init; //$NON-NLS-1$
				init -= span;
			}
			numAttacks += ")"; //$NON-NLS-1$
		}

		return numAttacks;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(mCloseButton)) {
			writeValues();
			dispose();
		} else if (source instanceof JRadioButton) {
			updateChart();
		} else if (source instanceof JMenuItem) {
			updateChart();
		}
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
		Object source = e.getDocument().getProperty(TKComponentHelpers.DOCUMENT_OWNER);

		AttributesRecord record = mCharacter.getAttributesRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			updateChart();
		}
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

	private void writeValues() {
		String pathString = SystemInfo.getCombatChartPath();
		Path path = Paths.get(pathString);
		File file = new File(pathString);
		if (!Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			// path doesn't exist... create it
			Path filePath = path.getParent();
			try {
				Files.createDirectories(filePath);
				if (!file.createNewFile()) {
					System.err.println("File cannot be created"); //$NON-NLS-1$
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(file));
			br.write(RIGHT + TKStringHelpers.TAB + mRightHandPopupMenu.getSelectedItem() + System.lineSeparator());
			br.write(LEFT + TKStringHelpers.TAB + mLeftHandPopupMenu.getSelectedItem() + System.lineSeparator());
			br.write(RIGHT_OTHER + TKStringHelpers.TAB + mRightOtherBonusField.getText() + System.lineSeparator());
			br.write(LEFT_OTHER + TKStringHelpers.TAB + mLeftOtherBonusField.getText() + System.lineSeparator());

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	private void readValues() {
		File combatChart = new File(SystemInfo.getCombatChartPath());
		if (!(combatChart.exists() && combatChart.canRead())) {
			return;
		}
		BufferedReader br = null;
		String in;

		try {
			br = new BufferedReader(new FileReader(combatChart));

			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in, "\t\n\r\f", false); //$NON-NLS-1$
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
				}
			}
		} catch (FileNotFoundException fnfe) {
			//DW9:: Log this
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException exception) {
				// nothing we can do...
			}
		}
	}

	private void setKeyValuePair(String key, String value) {
		if (key.equals(RIGHT)) {
			mRightHandPopupMenu.selectPopupMenuItem(value);
		} else if (key.equals(LEFT)) {
			mLeftHandPopupMenu.selectPopupMenuItem(value);
		} else if (key.equals(RIGHT_OTHER)) {
			mRightOtherBonusField.setText(value);
		} else if (key.equals(LEFT_OTHER)) {
			mLeftOtherBonusField.setText(value);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

}
