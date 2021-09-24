/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.storage.PreferenceStore;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class PreferencesDisplay extends JDialog implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	USE_1_COMMON_DICE				= "Use 1 Common Dice";											//$NON-NLS-1$
	private static final String	REROLL_DICE_BELOW_THIS_NUMBER	= "Reroll Dice below this number";								//$NON-NLS-1$
	private static final String	NUMBER_OF_DICE_TO_USE			= "Number of Dice to use";										//$NON-NLS-1$
	private static final String	COMMON_DIE_TOOLTIP				=																//
					"<html>In Athri we eliminate the possibilities of getting a high strength, <br>"							//$NON-NLS-1$
									+ "but a very low constitution.  We pair them, making them share <br>"						//$NON-NLS-1$
									+ "a \"common die\".  That way if you get a \"18\" strength, the lowest <br>"				//$NON-NLS-1$
									+ "constitution you can have is \"8\"</html>";												//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOKButton;
	private JButton				mCancelButton;
	private JButton				mDefaultsButton;

	private TKPopupMenu			mNumDicePopup;
	private TKPopupMenu			mReRollLowestPopup;
	private JCheckBox			mUseCommonDiceCheckBox;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link PreferencesDisplay}.
	 */
	public PreferencesDisplay(JFrame parent) {
		super(parent, true);

		JPanel wrapper = new JPanel();
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));
		wrapper.setLayout(new BorderLayout(10, 10));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);

		mOKButton = TKComponentHelpers.createButton("OK", this, false); //$NON-NLS-1$
		mCancelButton = TKComponentHelpers.createButton("Cancel", this); //$NON-NLS-1$
		mDefaultsButton = TKComponentHelpers.createButton("Restore Defaults", this, !PreferenceStore.getInstance().isDefaults()); //$NON-NLS-1$

		PreferenceStore prefs = PreferenceStore.getInstance();
		JLabel numDiceLabel = new JLabel(NUMBER_OF_DICE_TO_USE);
		mNumDicePopup = new TKPopupMenu(generateNumberDicePopup());
		mNumDicePopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getNumDice());

		JLabel reRollLowestLabel = new JLabel(REROLL_DICE_BELOW_THIS_NUMBER);
		mReRollLowestPopup = new TKPopupMenu(generateRerollDicePopup());
		mReRollLowestPopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getRerollLowest());

		mUseCommonDiceCheckBox = new JCheckBox(USE_1_COMMON_DICE, prefs.useCommonDie());
		mUseCommonDiceCheckBox.setToolTipText(COMMON_DIE_TOOLTIP);
		mUseCommonDiceCheckBox.addActionListener(this);

		JPanel messagePanel = new JPanel(new GridLayout(3, 2, 5, 0));
		messagePanel.add(numDiceLabel);
		messagePanel.add(mNumDicePopup);
		messagePanel.add(reRollLowestLabel);
		messagePanel.add(mReRollLowestPopup);
		messagePanel.add(mUseCommonDiceCheckBox);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		buttonPanel.add(mDefaultsButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mOKButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCancelButton);

		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private JMenu generateNumberDicePopup() {

		JMenu popupMenu = TKPopupMenu.createMenu(TKStringHelpers.EMPTY_STRING + PreferenceStore.getInstance().getNumDice());

		JMenuItem menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 4);
		menuItem.setToolTipText("4 Dice drop lowest"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 3);
		menuItem.setToolTipText("Use 3 dice"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 0);
		menuItem.setToolTipText("Manual Entry"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		return popupMenu;
	}

	private JMenu generateRerollDicePopup() {

		JMenu popupMenu = TKPopupMenu.createMenu(TKStringHelpers.EMPTY_STRING + PreferenceStore.getInstance().getNumDice());

		JMenuItem menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 3);
		menuItem.setToolTipText("Reroll any 3's or less"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 2);
		menuItem.setToolTipText("Reroll any 2's or less"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 1);
		menuItem.setToolTipText("Reroll any 1's"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(TKStringHelpers.EMPTY_STRING + 0);
		menuItem.setToolTipText("Don't reroll any dice"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		return popupMenu;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void loadDefaultsToDialog() {
		PreferenceStore prefs = PreferenceStore.getInstance();
		mNumDicePopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getNumDice());
		mReRollLowestPopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getRerollLowest());
		mUseCommonDiceCheckBox.setSelected(prefs.useCommonDie());
	}

	private void updateButtons() {
		mCancelButton.setEnabled(true);
		mDefaultsButton.setEnabled(!areDefaultsSet());
		mOKButton.setEnabled(!areValuesSaved());
	}

	private boolean areValuesSaved() {
		if (getNumDice() == PreferenceStore.getInstance().getSavedNumDice() && //
						getReRollLowest() == PreferenceStore.getInstance().getSavedRerollLowest() && //
						useCommonDice() == PreferenceStore.getInstance().isSavedUseCommonDie()) {
			return true;
		}
		return false;
	}

	/**
	 * returns true if values are set to defaults values in the PreferenceStore; they are not set
	 * until the ok button is pressed
	 */
	private boolean areDefaultsSet() {
		if (getNumDice() == PreferenceStore.getDefaultNumDice() && //
						getReRollLowest() == PreferenceStore.getDefaultRerollLowest() && //
						useCommonDice() == PreferenceStore.isDefaultUseCommonDie()) {
			return true;
		}
		return false;
	}

	/*****************************************************************************
	 * INHERITED ABSTRACT METHODS
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(mCancelButton)) {
			dispose();
		} else if (source.equals(mOKButton)) {
			PreferenceStore.getInstance().updateValuesInPreferenceStore(this);
			PreferenceStore.getInstance().saveValues();
			dispose();
		} else if (source.equals(mDefaultsButton)) {
			PreferenceStore.getInstance().setDefaults();
			loadDefaultsToDialog();
			updateButtons();
		} else if (source instanceof JMenuItem) {
			updateButtons();
		} else if (source instanceof JCheckBox) {
			updateButtons();
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The numDice. */
	public int getNumDice() {
		return TKStringHelpers.getIntValue(mNumDicePopup.getSelectedItem(), PreferenceStore.getInstance().getNumDice());
	}

	/** @param numDice The value to set for numDice. */
	public void setNumDiceField(int numDice) {
		mNumDicePopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + numDice);
	}

	/** @return The reRollLowest. */
	public int getReRollLowest() {
		return TKStringHelpers.getIntValue(mReRollLowestPopup.getSelectedItem(), PreferenceStore.getInstance().getRerollLowest());
	}

	/** @param reRollLowest The value to set for reRollLowest. */
	public void setReRollLowest(int reRollLowest) {
		mReRollLowestPopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + reRollLowest);
	}

	/** @return The useCommonDice. */
	public boolean useCommonDice() {
		return mUseCommonDiceCheckBox.isSelected();
	}

	/** @param useCommonDice The value to set for useCommonDice. */
	public void setUseCommonDice(boolean useCommonDice) {
		mUseCommonDiceCheckBox.setSelected(useCommonDice);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
