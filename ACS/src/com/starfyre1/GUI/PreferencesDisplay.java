/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKComponentTitledBorder;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ToolTipManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class PreferencesDisplay extends JDialog implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	DICE							= "Dice";																						//$NON-NLS-1$
	private static final String	MISCELLANEOUS					= "Miscellaneous";																				//$NON-NLS-1$
	private static final String	TOOLTIPS						= "Tooltips";																					//$NON-NLS-1$
	private static final String	USE_1_COMMON_DICE				= "Use 1 common dice";																			//$NON-NLS-1$
	private static final String	AUTO_LOAD						= "Auto load last character";																	//$NON-NLS-1$
	private static final String	CALENDAR_AL_SYSTEM				= "AL calendar system";																			//$NON-NLS-1$
	private static final String	CALENDAR_AD_SYSTEM				= "AD calendar system";																			//$NON-NLS-1$
	private static final String	SHOW_TOOLTIPS					= "Enabled to show tooltips";																	//$NON-NLS-1$
	private static final String	DETAILED_TOOLTIPS				= "Enabled to show detailed tooltips";															//$NON-NLS-1$
	private static final String	REROLL_DICE_BELOW_THIS_NUMBER	= "Reroll dice below this number";																//$NON-NLS-1$
	private static final String	NUMBER_OF_DICE_TO_USE			= "Number of dice to use";																		//$NON-NLS-1$
	private static final String	COMMON_DIE_TOOLTIP				=																								//
					"<html>In Athri we eliminate the possibilities of getting a high strength, <br>"															//$NON-NLS-1$
									+ "but a very low constitution.  We pair them, making them share <br>"														//$NON-NLS-1$
									+ "a \"common die\".  That way if you get a \"18\" strength, the lowest <br>"												//$NON-NLS-1$
									+ "constitution you can have is \"8\"</html>";																				//$NON-NLS-1$
	private static final String	AUTO_LOAD_TOOLTIP				= "<html>Reload last character used on startup</html>";											//$NON-NLS-1$
	private static final String	SHOW_TOOLTIPS_TOOLTIP			= "<html>Enable tooltips.</html>";																//$NON-NLS-1$
	private static final String	DETAILED_TOOLTIPS_TOOLTIP		= "<html>Enable detailed tooltips.<br>(show calculations in saving Throws and Skills) </html>";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOKButton;
	private JButton				mCancelButton;
	private JButton				mDefaultsButton;

	private TKPopupMenu			mNumDicePopup;
	private TKPopupMenu			mReRollLowestPopup;
	private JCheckBox			mUseCommonDiceCheckBox;

	private JCheckBox			mAutoLoadCheckBox;
	private ButtonGroup			mCalendarGroup					= new ButtonGroup();
	private JRadioButton		mCalendarAL						= new JRadioButton(CALENDAR_AL_SYSTEM);
	private JRadioButton		mCalendarAD						= new JRadioButton(CALENDAR_AD_SYSTEM);

	private JCheckBox			mShowToolTipsCheckBox;
	private JCheckBox			mDetailedToolTipsCheckBox;

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

		JPanel messagePanel = new JPanel(new GridLayout(3, 1, 5, 0));
		messagePanel.add(createDicePanel());
		messagePanel.add(createMiscPanel());
		messagePanel.add(createTooltipPanel());

		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private JPanel createMiscPanel() {
		PreferenceStore prefs = PreferenceStore.getInstance();

		JPanel wrapper = new JPanel();
		BoxLayout layout = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
		wrapper.setLayout(layout);
		JLabel title = new JLabel(MISCELLANEOUS);
		title.setOpaque(true);
		wrapper.setBorder(new TKComponentTitledBorder(title, wrapper, new EtchedBorder()));

		mAutoLoadCheckBox = new JCheckBox(AUTO_LOAD, prefs.isAutoLoad());
		mAutoLoadCheckBox.setToolTipText(AUTO_LOAD_TOOLTIP + "<br>" + prefs.getCurrentLastCharacter() + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
		mAutoLoadCheckBox.addActionListener(this);

		setCalendarAL(prefs.isCalendarAL());
		mCalendarAD.addActionListener(this);
		mCalendarAL.addActionListener(this);

		mCalendarGroup.add(mCalendarAL);
		mCalendarGroup.add(mCalendarAD);

		wrapper.add(mAutoLoadCheckBox);
		wrapper.add(mCalendarAD);
		wrapper.add(mCalendarAL);
		return wrapper;
	}

	private JPanel createDicePanel() {
		PreferenceStore prefs = PreferenceStore.getInstance();

		JPanel numDiceWrapper = new JPanel();
		BoxLayout layout = new BoxLayout(numDiceWrapper, BoxLayout.Y_AXIS);
		numDiceWrapper.setLayout(layout);
		JLabel title = new JLabel(DICE);
		title.setOpaque(true);
		numDiceWrapper.setBorder(new TKComponentTitledBorder(title, numDiceWrapper, new EtchedBorder()));

		JLabel numDiceLabel = new JLabel(NUMBER_OF_DICE_TO_USE);
		mNumDicePopup = new TKPopupMenu(generateNumberDicePopup());
		mNumDicePopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getNumDice());

		JLabel reRollLowestLabel = new JLabel(REROLL_DICE_BELOW_THIS_NUMBER);
		mReRollLowestPopup = new TKPopupMenu(generateRerollDicePopup());
		mReRollLowestPopup.selectPopupMenuItem(TKStringHelpers.EMPTY_STRING + prefs.getRerollLowest());

		mUseCommonDiceCheckBox = new JCheckBox(USE_1_COMMON_DICE, prefs.useCommonDie());
		mUseCommonDiceCheckBox.setToolTipText(COMMON_DIE_TOOLTIP);
		mUseCommonDiceCheckBox.addActionListener(this);
		mUseCommonDiceCheckBox.setAlignmentX(LEFT_ALIGNMENT);

		JPanel wrapperTop = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JPanel wrapperMid = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JPanel wrapperEnd = new JPanel(new FlowLayout(FlowLayout.LEADING));

		wrapperTop.add(mNumDicePopup);
		wrapperTop.add(numDiceLabel);

		wrapperMid.add(mReRollLowestPopup);
		wrapperMid.add(reRollLowestLabel);

		wrapperEnd.add(mUseCommonDiceCheckBox);

		numDiceWrapper.add(wrapperTop);
		numDiceWrapper.add(wrapperMid);
		numDiceWrapper.add(wrapperEnd);
		return numDiceWrapper;
	}

	private JPanel createTooltipPanel() {
		PreferenceStore prefs = PreferenceStore.getInstance();

		JPanel tooltipWrapper = new JPanel();
		BoxLayout layout2 = new BoxLayout(tooltipWrapper, BoxLayout.Y_AXIS);
		tooltipWrapper.setLayout(layout2);
		JLabel title2 = new JLabel(TOOLTIPS);
		title2.setOpaque(true);
		tooltipWrapper.setBorder(new TKComponentTitledBorder(title2, tooltipWrapper, new EtchedBorder()));

		mShowToolTipsCheckBox = new JCheckBox(SHOW_TOOLTIPS, prefs.isShowToolTips());
		mShowToolTipsCheckBox.setToolTipText(SHOW_TOOLTIPS_TOOLTIP);
		mShowToolTipsCheckBox.addActionListener(this);

		mDetailedToolTipsCheckBox = new JCheckBox(DETAILED_TOOLTIPS, prefs.isDetailedToolTips());
		mDetailedToolTipsCheckBox.setToolTipText(DETAILED_TOOLTIPS_TOOLTIP);
		mDetailedToolTipsCheckBox.addActionListener(this);

		tooltipWrapper.add(mShowToolTipsCheckBox);
		tooltipWrapper.add(mDetailedToolTipsCheckBox);
		tooltipWrapper.add(Box.createGlue());
		return tooltipWrapper;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();

		mOKButton = TKComponentHelpers.createButton("OK", this, false); //$NON-NLS-1$
		mCancelButton = TKComponentHelpers.createButton("Cancel", this); //$NON-NLS-1$
		mDefaultsButton = TKComponentHelpers.createButton("Restore Defaults", this, !PreferenceStore.getInstance().isDefaults()); //$NON-NLS-1$

		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		buttonPanel.add(mDefaultsButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mOKButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCancelButton);
		return buttonPanel;
	}

	private JMenu generateNumberDicePopup() {

		JMenu popupMenu = TKPopupMenu.createMenu(String.valueOf(PreferenceStore.getInstance().getNumDice()));

		JMenuItem menuItem = new JMenuItem(String.valueOf(4));
		menuItem.setToolTipText("4 Dice drop lowest"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(String.valueOf(3));
		menuItem.setToolTipText("Use 3 dice"); //$NON-NLS-1$
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

		menuItem = new JMenuItem(String.valueOf(0));
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
		mAutoLoadCheckBox.setSelected(prefs.isAutoLoad());
		mCalendarAL.setSelected(prefs.isCalendarAL());
		mShowToolTipsCheckBox.setSelected(prefs.isShowToolTips());
		mDetailedToolTipsCheckBox.setSelected(prefs.isDetailedToolTips());
	}

	private void updateButtons() {
		mCancelButton.setEnabled(true);
		mDefaultsButton.setEnabled(!areDefaultsSet());
		mOKButton.setEnabled(!areValuesSaved());
	}

	private boolean areValuesSaved() {
		if (getNumDice() == PreferenceStore.getInstance().getSavedNumDice() && //
						getReRollLowest() == PreferenceStore.getInstance().getSavedRerollLowest() && //
						useCommonDice() == PreferenceStore.getInstance().isSavedUseCommonDie() && //
						isAutoLoad() == PreferenceStore.getInstance().isSavedAutoLoad() && //
						isCalendarAL() == PreferenceStore.getInstance().isSavedCalendarAL() && //
						isShowToolTips() == PreferenceStore.getInstance().isShowToolTips() && //
						isDetailedToolTips() == PreferenceStore.getInstance().isDetailedToolTips()) {
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
						useCommonDice() == PreferenceStore.isDefaultUseCommonDie() && //
						isAutoLoad() == PreferenceStore.isDefaultAutoLoad() && //
						isCalendarAL() == PreferenceStore.isDefaultCalendarAL() && //
						isShowToolTips() == PreferenceStore.isDefaultShowToolTips() && //
						isDetailedToolTips() == PreferenceStore.isDefaultDetailedToolTips()) {
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
			ToolTipManager.sharedInstance().setEnabled(PreferenceStore.getInstance().isShowToolTips());
			ACS.getInstance().getCharacterSheet().updateToolTips();
			dispose();
		} else if (source.equals(mDefaultsButton)) {
			PreferenceStore.getInstance().setDefaults();
			loadDefaultsToDialog();
			updateButtons();
		} else if (source instanceof JMenuItem) {
			updateButtons();
		} else if (source instanceof JCheckBox) {
			updateButtons();
		} else if (source instanceof JRadioButton) {
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

	public boolean isAutoLoad() {
		return mAutoLoadCheckBox.isSelected();
	}

	public void setAutoLoad(boolean autoLoad) {
		mAutoLoadCheckBox.setSelected(autoLoad);
	}

	public boolean isCalendarAL() {
		return mCalendarAL.isSelected();
	}

	public void setCalendarAL(boolean calendarAL) {
		mCalendarAD.setSelected(!calendarAL);
		mCalendarAL.setSelected(calendarAL);
	}

	public boolean isShowToolTips() {
		return mShowToolTipsCheckBox.isSelected();
	}

	public void setShowToolTips(boolean showToolTips) {
		mShowToolTipsCheckBox.setSelected(showToolTips);
	}

	public boolean isDetailedToolTips() {
		return mDetailedToolTipsCheckBox.isSelected();
	}

	public void setDetailedToolTips(boolean detailedToolTips) {
		mDetailedToolTipsCheckBox.setSelected(detailedToolTips);
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
