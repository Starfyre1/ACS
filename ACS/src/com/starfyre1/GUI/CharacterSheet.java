/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.CombatInformationRecord;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataModel.MoneyRecord;
import com.starfyre1.dataModel.PersonalInformationRecord;
import com.starfyre1.dataModel.SavingThrowsRecord;
import com.starfyre1.dataModel.SkillsRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.dataset.AnimalList;
import com.starfyre1.dataset.ArmorList;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.EquipmentList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.MagicItemList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.startup.ACS;
import com.starfyre1.startup.SystemInfo;
import com.starfyre1.storage.HistoryManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CharacterSheet implements ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String				CHARACTER_SHEET_TITLE		= "Character Sheet";		//$NON-NLS-1$
	private static final String				EQUIPMENT_SHEET_TITLE		= "Equipment Sheet";		//$NON-NLS-1$
	private static final String				SPELL_SHEET_TITLE			= "Spell Sheet";			//$NON-NLS-1$
	private static final String				JOURNAL_SHEET_TITLE			= "Journal Sheet";			//$NON-NLS-1$

	// DW add something useful for the tooltips or remove them
	private static final String				CHARACTER_SHEET_TOOLTIP		= "Character Sheet";		//$NON-NLS-1$
	private static final String				EQUIPMENT_SHEET_TOOLTIP		= "Equipment Sheet";		//$NON-NLS-1$
	private static final String				SPELL_SHEET_TOOLTIP			= "Spell Sheet";			//$NON-NLS-1$
	private static final String				JOURNAL_SHEET_TOOLTIP		= "Journal Sheet";			//$NON-NLS-1$

	private static final String				ABOUT						= "About";					//$NON-NLS-1$
	private static final String				HELP						= "Help";					//$NON-NLS-1$
	private static final String				PREFERENCES					= "Preferences";			//$NON-NLS-1$
	private static final String				OPTIONS						= "Options";				//$NON-NLS-1$
	private static final String				MARKET_PLACE				= "Market Place";			//$NON-NLS-1$
	private static final String				EXIT						= "Exit";					//$NON-NLS-1$
	private static final String				SAVE_AS						= "Save As...";				//$NON-NLS-1$
	private static final String				SAVE						= "Save";					//$NON-NLS-1$
	private static final String				CLOSE						= "Close";					//$NON-NLS-1$
	private static final String				OPEN						= "Open...";				//$NON-NLS-1$
	private static final String				NEW							= "New";					//$NON-NLS-1$
	private static final String				FILE						= "File";					//$NON-NLS-1$
	private static final String				CREATE						= "Create";					//$NON-NLS-1$
	private static final String				CANCEL						= "Cancel";					//$NON-NLS-1$

	public static final Dimension			CHARACTER_TAB_TABLE_SIZE	= new Dimension(375, 75);
	public static final Dimension			EQUIPMENT_TAB_TABLE_SIZE	= new Dimension(750, 150);
	public static final Dimension			MARKET_PLACE_TAB_TABLE_SIZE	= new Dimension(750, 750);
	public static final Insets				BORDER_INSETS				= new Insets(5, 5, 5, 5);
	public static final int					FIELD_SIZE_SMALL			= 2;
	public static final int					FIELD_SIZE_MEDIUM			= 5;
	public static final int					FIELD_SIZE_LARGE			= 14;
	public static final int					CELL_SMALL_MAX_WIDTH		= 45;
	public static final int					CELL_LARGE_MAX_WIDTH		= 75;

	private static final Icon				ICON						= null;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private boolean							mIsCreatingGUI				= false;
	private boolean							mIsLoadingData				= false;
	private JFrame							mFrame;

	private JDialog							mAttributesEnterDialog;
	private JButton							mCreateButton;
	private JButton							mCancelButton;

	private String							mCharacterFile;

	// Data Files
	private AttributesRecord				mAttributesRecord;
	private CombatInformationRecord			mCombatInformationRecord;
	private HeaderRecord					mHeaderRecord;
	private PersonalInformationRecord		mPersonalInformationRecord;
	private MoneyRecord						mMoneyRecord;
	private SavingThrowsRecord				mSavingThrowsRecord;
	private SkillsRecord					mSkillsRecord;
	private EquipmentList					mEquipmentList;
	private ArmorList						mArmorList;
	private WeaponList						mWeaponList;
	private AnimalList						mAnimalList;
	private MagicItemList					mMagicItemList;

	// Header
	private HeaderDisplay					mHeaderDisplay;

	// Character Tab
	private AttributesDisplay				mAttributesDisplay;
	private SavingThowsDisplay				mSavingThrowsDisplay;
	private CombatInformationDisplay		mCombatInformationDisplay;

	private SkillsDisplay					mSkillsDisplay;
	private DeterminationPointsDisplay		mDeterminationPointsDisplay;

	private PersonalInformationDisplay		mPersonalInformationDisplay;
	private MoneyDisplay					mMoneyDisplay;
	private InnateAbilitiesDisplay			mInnateAbilitiesDisplay;

	private ArmorDisplay					mArmorInformationDisplay;
	private DefenseInformationDisplay		mDefenseInformationDisplay;

	private WeaponDisplay					mWeaponInformationDisplay;
	private AttackTotalsDisplay				mAttackTotalsDisplay;

	// Equipment Tab
	private EquipmentDisplay				mEquipmentDisplay;

	private AnimalsDisplay					mAnimalsDisplay;
	private TitlesLandsPropertiesDisplay	mTitlesLandsPropertiesDisplay;
	private NotesCommentsDisplay			mNotesCommentsDisplay;
	private MagicItemsDisplay				mMagicItemsDisplay;
	private ArmorDisplay					mArmorDisplay;
	private WeaponDisplay					mWeaponDisplay;

	// Spell Tab
	private SpellListDisplay				mSpellTab;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@code CharacterSheet}.
	 */
	public CharacterSheet() {

		mIsCreatingGUI = true;
		create();
		mHeaderRecord = new HeaderRecord();
		mIsCreatingGUI = false;

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void create() {
		mFrame = new JFrame(ACS.TITLE);
		mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//		mFrame.setResizable(false);
		mFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				prepForExit();
			}
		});

		createMainMenu();

		mHeaderDisplay = new HeaderDisplay(this);

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent characterTab = makeCharacerTab();
		JComponent equipmentTab = makeEquipmentTab();
		mSpellTab = new SpellListDisplay(this);
		JComponent journalTab = new JournalDisplay(this);

		tabbedPane.addTab(CHARACTER_SHEET_TITLE, ICON, characterTab, CHARACTER_SHEET_TOOLTIP);
		tabbedPane.addTab(EQUIPMENT_SHEET_TITLE, ICON, equipmentTab, EQUIPMENT_SHEET_TOOLTIP);
		tabbedPane.addTab(SPELL_SHEET_TITLE, ICON, mSpellTab, SPELL_SHEET_TOOLTIP);
		tabbedPane.addTab(JOURNAL_SHEET_TITLE, ICON, journalTab, JOURNAL_SHEET_TOOLTIP);

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_E);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_S);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_J);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mHeaderDisplay, BorderLayout.NORTH);
		wrapper.add(tabbedPane, BorderLayout.CENTER);

		Container pane = mFrame.getContentPane();
		pane.add(wrapper, BorderLayout.CENTER);

		mFrame.pack();
		mFrame.setVisible(true);
	}

	/**
	 * @return the CharacterTab
	 */
	private JComponent makeCharacerTab() {

		JPanel page = new JPanel();
		BoxLayout boxLayout = new BoxLayout(page, BoxLayout.Y_AXIS);
		page.setLayout(boxLayout);

		mAttributesDisplay = new AttributesDisplay(this);
		mSavingThrowsDisplay = new SavingThowsDisplay(this);
		mCombatInformationDisplay = new CombatInformationDisplay(this);
		mSkillsDisplay = new SkillsDisplay(this);
		mDeterminationPointsDisplay = new DeterminationPointsDisplay(this);
		mMoneyDisplay = new MoneyDisplay(this);
		mPersonalInformationDisplay = new PersonalInformationDisplay(this);
		mDefenseInformationDisplay = new DefenseInformationDisplay(this);
		mArmorInformationDisplay = new ArmorDisplay(null);
		mInnateAbilitiesDisplay = new InnateAbilitiesDisplay(this);
		mWeaponInformationDisplay = new WeaponDisplay(null);
		mAttackTotalsDisplay = new AttackTotalsDisplay(this);

		JPanel wrapper1 = new JPanel();
		BoxLayout blw = new BoxLayout(wrapper1, BoxLayout.X_AXIS);
		wrapper1.setLayout(blw);

		wrapper1.add(mAttributesDisplay);
		wrapper1.add(mSavingThrowsDisplay);
		wrapper1.add(mCombatInformationDisplay);

		JPanel wrapper2 = new JPanel();
		BoxLayout blw2 = new BoxLayout(wrapper2, BoxLayout.X_AXIS);
		wrapper2.setLayout(blw2);

		wrapper2.add(mSkillsDisplay);
		wrapper2.add(mDeterminationPointsDisplay);

		JPanel wrapper3 = new JPanel();
		BoxLayout blw3 = new BoxLayout(wrapper3, BoxLayout.X_AXIS);
		wrapper3.setLayout(blw3);

		wrapper3.add(mMoneyDisplay);
		wrapper3.add(mPersonalInformationDisplay);
		wrapper3.add(mDefenseInformationDisplay);

		JPanel wrapper4 = new JPanel();
		BoxLayout blw4 = new BoxLayout(wrapper4, BoxLayout.X_AXIS);
		wrapper4.setLayout(blw4);

		wrapper4.add(mArmorInformationDisplay);
		wrapper4.add(mInnateAbilitiesDisplay);

		JPanel wrapper5 = new JPanel();
		BoxLayout blw5 = new BoxLayout(wrapper5, BoxLayout.X_AXIS);
		wrapper5.setLayout(blw5);

		wrapper5.add(mWeaponInformationDisplay);
		wrapper5.add(mAttackTotalsDisplay);

		page.add(wrapper1);
		page.add(wrapper2);
		page.add(wrapper3);
		page.add(wrapper4);
		page.add(wrapper5);

		JScrollPane scrollPane = new JScrollPane(page);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		return scrollPane;
	}

	/**
	 * @return the EquipmentTab
	 */
	private JComponent makeEquipmentTab() {

		JPanel page = new JPanel();
		BoxLayout boxLayout = new BoxLayout(page, BoxLayout.Y_AXIS);
		page.setLayout(boxLayout);

		mEquipmentDisplay = new EquipmentDisplay(this);
		mAnimalsDisplay = new AnimalsDisplay(this);
		mTitlesLandsPropertiesDisplay = new TitlesLandsPropertiesDisplay(this);
		mNotesCommentsDisplay = new NotesCommentsDisplay(this);
		mMagicItemsDisplay = new MagicItemsDisplay(this);
		mWeaponDisplay = new WeaponDisplay(this);
		mArmorDisplay = new ArmorDisplay(this);

		JPanel wrapper = new JPanel();
		BoxLayout blw = new BoxLayout(wrapper, BoxLayout.X_AXIS);
		wrapper.setLayout(blw);

		wrapper.add(mTitlesLandsPropertiesDisplay);
		wrapper.add(mNotesCommentsDisplay);

		JPanel sizer = new JPanel();
		BoxLayout bl = new BoxLayout(sizer, BoxLayout.Y_AXIS);
		sizer.setLayout(bl);

		sizer.add(mAnimalsDisplay);
		sizer.add(wrapper);
		sizer.add(mMagicItemsDisplay);

		page.add(mEquipmentDisplay);
		page.add(mWeaponDisplay);
		page.add(mArmorDisplay);
		page.add(sizer);

		JScrollPane scrollPane = new JScrollPane(page);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		return scrollPane;
	}

	private void createMainMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu fileMenu = new JMenu(FILE);
		fileMenu.add(TKComponentHelpers.createMenuItem(NEW, this));
		fileMenu.add(TKComponentHelpers.createMenuItem(OPEN, this));
		fileMenu.add(TKComponentHelpers.createMenuItem(CLOSE, this));
		fileMenu.addSeparator();
		fileMenu.add(TKComponentHelpers.createMenuItem(SAVE, this));
		fileMenu.add(TKComponentHelpers.createMenuItem(SAVE_AS, this));
		fileMenu.addSeparator();
		fileMenu.add(TKComponentHelpers.createMenuItem(EXIT, this));

		JMenu optionsMenu = new JMenu(OPTIONS);
		optionsMenu.add(TKComponentHelpers.createMenuItem(PREFERENCES, this));
		optionsMenu.add(TKComponentHelpers.createMenuItem(MARKET_PLACE, this));

		JMenu helpMenu = new JMenu(HELP);
		helpMenu.add(TKComponentHelpers.createMenuItem(HELP, this));
		helpMenu.addSeparator();
		helpMenu.add(TKComponentHelpers.createMenuItem(ABOUT, this));

		bar.add(fileMenu);
		bar.add(optionsMenu);
		bar.add(helpMenu);

		mFrame.setJMenuBar(bar);
	}

	private void manualEntry() {
		mAttributesRecord = new AttributesRecord(false);

		JPanel wrapper = new JPanel();
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));
		wrapper.setLayout(new BorderLayout(10, 10));

		mAttributesEnterDialog = new JDialog(mFrame, "Character Stats Entry", true); //$NON-NLS-1$
		mAttributesEnterDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mAttributesEnterDialog.setUndecorated(true);

		AttributesDisplay display = new AttributesDisplay(this);
		display.setBorder(null);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		mCreateButton = TKComponentHelpers.createButton(CREATE, this, false);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mCreateButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(mCancelButton);

		wrapper.add(display, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		mAttributesEnterDialog.add(wrapper);

		mAttributesEnterDialog.pack();
		mAttributesEnterDialog.setLocationRelativeTo(mFrame);
		mAttributesEnterDialog.setVisible(true);
	}

	public void enableCreateButton(boolean enable) {
		if (mCreateButton != null) {
			mCreateButton.setEnabled(enable);
		} else {
			// DW this is just a hack... needs to be cleaned up
			levelChanged();
		}
	}

	private void createAndUpdate() {
		int result = JOptionPane.showConfirmDialog(mFrame, "Do you want to roll your own stats?", "New Character", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		if (result == JOptionPane.YES_OPTION) {
			manualEntry();
			mIsLoadingData = true;
			if (mAttributesRecord == null) {
				return;
			}
		} else if (result == JOptionPane.NO_OPTION) {
			mIsLoadingData = true;
			mAttributesRecord = new AttributesRecord(true);
			mAttributesRecord.finalizeCreation(false);
		} else {
			return;
		}

		mSavingThrowsRecord = new SavingThrowsRecord(this, true);
		mCombatInformationRecord = new CombatInformationRecord(this, true);
		mPersonalInformationRecord = new PersonalInformationRecord(this, true);
		mMoneyRecord = new MoneyRecord(this, true);
		mSkillsRecord = new SkillsRecord(this, true);
		mEquipmentList = new EquipmentList();
		mArmorList = new ArmorList();
		mWeaponList = new WeaponList();
		mAnimalList = new AnimalList();
		mMagicItemList = new MagicItemList();

		loadDisplay();

		mIsLoadingData = false;
	}

	private void loadAndUpdate() {
		mIsLoadingData = true;

		mAttributesRecord = new AttributesRecord(false);
		mHeaderRecord = new HeaderRecord();
		mMoneyRecord = new MoneyRecord(this, false);
		mPersonalInformationRecord = new PersonalInformationRecord(this, false);
		mCombatInformationRecord = new CombatInformationRecord(this, false);
		mSkillsRecord = new SkillsRecord(this, false);
		mMagicItemList = new MagicItemList();
		mEquipmentList = new EquipmentList();
		mArmorList = new ArmorList();
		mWeaponList = new WeaponList();
		mAnimalList = new AnimalList();

		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(true);
		String[] exts = { "acs" }; //$NON-NLS-1$
		String description = "supported files: " + Arrays.toString(exts).replace('[', '(').replace(']', ')'); //$NON-NLS-1$
		fc.setFileFilter(new FileNameExtensionFilter(description, exts));
		fc.setCurrentDirectory(new File(PreferenceStore.getInstance().getCurrentFileLocation()));
		if (fc.showOpenDialog(mFrame) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			PreferenceStore.getInstance().setCurrentFileLocation(file.getParentFile());
			mCharacterFile = file.getAbsolutePath();
			openFile(file);

			mAttributesRecord.finalizeCreation(false);
			mPersonalInformationRecord.generateCarry();
			mCombatInformationRecord.levelChanged();
			mSkillsRecord.levelChanged();
			mSavingThrowsRecord = new SavingThrowsRecord(this, true);

			updateForEncubrance();

			loadDisplay();

		}

		mIsLoadingData = false;
	}

	void loadDisplay() {
		// Primary Character Tab
		mAttributesDisplay.loadDisplay();
		mHeaderDisplay.loadDisplay();
		mSavingThrowsDisplay.loadDisplay();
		mCombatInformationDisplay.loadDisplay();
		mSkillsDisplay.loadDisplay();
		mDeterminationPointsDisplay.loadDisplay();
		mPersonalInformationDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
		mInnateAbilitiesDisplay.loadDisplay();

		// Equipment Tab
		mEquipmentDisplay.loadDisplay();
		mAnimalsDisplay.loadDisplay();
		mTitlesLandsPropertiesDisplay.loadDisplay();
		mNotesCommentsDisplay.loadDisplay();
		mMagicItemsDisplay.loadDisplay();
		mArmorDisplay.loadDisplay();
		mWeaponDisplay.loadDisplay();

		// Secondary Character Tab
		mArmorInformationDisplay.loadDisplay();
		mWeaponInformationDisplay.loadDisplay();
		mDefenseInformationDisplay.loadDisplay();
		mAttackTotalsDisplay.loadDisplay();
		mDefenseInformationDisplay.loadDisplay();
		mAttackTotalsDisplay.loadDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(NEW)) {
			clearRecords();
			createAndUpdate();
		} else if (cmd.equals(OPEN)) {
			clearRecords();
			loadAndUpdate();
		} else if (cmd.equals(CLOSE)) {
			saveOption(false);
			clearRecords();
		} else if (cmd.equals(SAVE)) {
			if (mCharacterFile == null) {
				saveAs();
			} else {
				saveFile(new File(mCharacterFile));
			}
		} else if (cmd.equals(SAVE_AS)) {
			saveAs();
		} else if (cmd.equals(EXIT)) {
			saveOption(true);
		} else if (cmd.equals(PREFERENCES)) {
			new PreferencesDisplay(mFrame);
		} else if (cmd.equals(MARKET_PLACE)) {
			new MarketPlace(mFrame);
		} else if (cmd.equals(ABOUT)) {
			new AboutDialog(mFrame);
		} else if (cmd.equals(HELP)) {
			// DW open doc's
		} else if (cmd.equals(CANCEL)) {
			mAttributesEnterDialog.dispose();
			mAttributesRecord = null;
		} else if (cmd.equals(CREATE)) {
			mAttributesRecord.finalizeCreation(true);
			mAttributesDisplay.loadDisplay();
			mAttributesEnterDialog.dispose();
		}
	}

	private void clearRecords() {
		if (mAttributesRecord != null) {
			mAttributesRecord.clearRecords();
		}
		if (mCombatInformationRecord != null) {
			mCombatInformationRecord.clearRecords();
		}
		if (mHeaderRecord != null) {
			mHeaderRecord.clearRecords();
		}
		if (mPersonalInformationRecord != null) {
			mPersonalInformationRecord.clearRecords();
		}
		if (mMoneyRecord != null) {
			mMoneyRecord.clearRecords();
		}
		if (mSavingThrowsRecord != null) {
			mSavingThrowsRecord.levelChanged();
		}
		if (mSkillsRecord != null) {
			mSkillsRecord.clearRecords();
		}
		if (mEquipmentList != null) {
			mEquipmentList.clearRecords();
		}
		if (mArmorList != null) {
			mArmorList.clearRecords();
		}
		if (mWeaponList != null) {
			mWeaponList.clearRecords();
		}
		if (mAnimalList != null) {
			mAnimalList.clearRecords();
		}
		if (mMagicItemList != null) {
			mMagicItemList.clearRecords();
		}
		mDefenseInformationDisplay.clearRecords();
		if (mAttributesRecord != null) {
			loadDisplay();
		}
	}

	private void saveOption(boolean exit) {
		int results = JOptionPane.showConfirmDialog(mFrame, "Do you want to save?", "Save Character?", JOptionPane.YES_NO_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
		if (results == JOptionPane.YES_OPTION) {
			if (mCharacterFile == null) {
				saveAs();
			} else {
				saveFile(new File(mCharacterFile));
			}
			prepForExit();
			if (exit) {
				System.exit(0);
			}
		} else if (results == JOptionPane.NO_OPTION) {
			prepForExit();
			if (exit) {
				System.exit(0);
			}
		}
	}

	private void prepForExit() {
		PreferenceStore prefs = PreferenceStore.getInstance();
		prefs.setWindowBounds(mFrame.getBounds());
		prefs.saveValues();
	}

	public void levelChanged() {
		if (mSavingThrowsRecord != null) {
			mSavingThrowsRecord.levelChanged();
			mCombatInformationRecord.levelChanged();
			mSkillsRecord.levelChanged();

			// DW this is probably loading the display twice on character load.
			mSavingThrowsDisplay.loadDisplay();
			mDeterminationPointsDisplay.loadDisplay();
			mDefenseInformationDisplay.loadDisplay();
			mSkillsDisplay.loadDisplay();

			updateForEncubrance();
			mCombatInformationDisplay.loadDisplay();
		}
	}

	/**
	 * @param purchasedItems
	 */
	public void addAllEquipment(ArrayList<EquipmentRecord> purchasedItems) {
		float cost = mEquipmentList.addAllEquipment(purchasedItems, true);
		mMoneyRecord.spend(cost);
		mEquipmentDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addAllAnimals(ArrayList<AnimalRecord> purchasedItems) {
		float cost = mAnimalList.addAllAnimals(purchasedItems, true);
		mMoneyRecord.spend(cost);
		mAnimalsDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addAllArmor(ArrayList<ArmorRecord> purchasedItems) {
		float cost = mArmorList.addAllArmor(purchasedItems, true);
		mMoneyRecord.spend(cost);
		mArmorDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addAllMagicItems(ArrayList<MagicItemRecord> purchasedItems) {
		float cost = mMagicItemList.addAllMagicItems(purchasedItems, true);
		mMoneyRecord.spend(cost);
		mMagicItemsDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addAllWeapons(ArrayList<WeaponRecord> purchasedItems) {
		float cost = mWeaponList.addAllWeapons(purchasedItems, true);
		mMoneyRecord.spend(cost);
		mWeaponDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public boolean isLoading() {
		return mIsCreatingGUI;
	}

	/** @return The isLoadingData. */
	public boolean isLoadingData() {
		return mIsLoadingData;
	}

	/**
	 * @return Percentage, as an int, of carry vs encumbrance;
	 */
	public int getPercentEncumbrance() {
		int carry = getPersonalInformationRecord().getCarry();
		if (carry == 0) {
			return 0;
		}
		float encumbrance = getTotalEncumbrance();
		return (int) encumbrance / carry * 100;
	}

	private float getTotalEncumbrance() {
		float encumbrance = 0;

		ArrayList<ArmorRecord> armors = getEquippedArmorRecords();
		ArrayList<WeaponRecord> weapons = getEquippedWeaponRecords();
		ArrayList<EquipmentRecord> equipments = getEquipmentList().getRecords();
		ArrayList<MagicItemRecord> magicItems = getMagicItemList().getRecords();

		if (armors != null) {
			for (ArmorRecord armor : armors) {
				encumbrance += armor.getEncumbrance();
			}
		}

		if (weapons != null) {
			for (WeaponRecord weapon : weapons) {
				encumbrance += weapon.getEncumbrance();
			}
		}

		if (equipments != null) {
			for (EquipmentRecord equipment : equipments) {
				if (equipment.isEquipped()) {
					encumbrance += equipment.getEncumbrance() * equipment.getCount();
				}
			}
		}

		if (magicItems != null) {
			for (MagicItemRecord magicItem : magicItems) {
				if (magicItem.isEquipped()) {
					encumbrance += magicItem.getEncumbrance() * magicItem.getCount();
				}
			}
		}

		mPersonalInformationRecord.setEncumbrance(encumbrance);
		mPersonalInformationDisplay.loadDisplay();
		return encumbrance;
	}

	public void updateForEncubrance() {
		PersonalInformationRecord pir = getPersonalInformationRecord();
		CombatInformationRecord cir = getCombatInformationRecord();
		SavingThrowsRecord str = getSavingThrowsRecord();

		float encumbrance = getTotalEncumbrance();
		int carry = pir.getCarry();

		int value = (int) (encumbrance / (carry + .001f));

		if (value >= 4) {
			cir.setMovement(0);
			cir.setAttackSpeed(0);
			cir.setHitBonus(0);
			cir.setMissileSpeed(0);
			cir.setMissileBonus(0);
			cir.setBowSpeed(0);
			cir.setBowBonus(0);
		} else if (value == 3) {
			cir.setMovement((int) (cir.getMovement() * .25));
			cir.setAttackSpeed(cir.getAttackSpeed() - 5);
			cir.setHitBonus((int) (cir.getHitBonus() * .75));
			cir.setMissileSpeed(cir.getMissileSpeed() - 5);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .75));
			cir.setBowSpeed(cir.getBowSpeed() - 5);
			cir.setBowBonus((int) (cir.getBowBonus() * .75));
			str.setSuprise(str.getSuprise() - 20);
		} else if (value == 2) {
			cir.setMovement((int) (cir.getMovement() * .50));
			cir.setAttackSpeed(cir.getAttackSpeed() - 3);
			cir.setHitBonus((int) (cir.getHitBonus() * .85));
			cir.setMissileSpeed(cir.getMissileSpeed() - 3);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .85));
			cir.setBowSpeed(cir.getBowSpeed() - 3);
			cir.setBowBonus((int) (cir.getBowBonus() * .85));
			str.setSuprise(str.getSuprise() - 10);
		} else if (value == 1) {
			cir.setMovement((int) (cir.getMovement() * .75));
			cir.setAttackSpeed(cir.getAttackSpeed() - 1);
			cir.setHitBonus((int) (cir.getHitBonus() * .95));
			cir.setMissileSpeed(cir.getMissileSpeed() - 1);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .95));
			cir.setBowSpeed(cir.getBowSpeed() - 1);
			cir.setBowBonus((int) (cir.getBowBonus() * .95));
		}
	}

	/** @return The animalRecord. */
	public AnimalList getAnimalList() {
		return mAnimalList;
	}

	/** @return The armorRecord. */
	public ArmorList getArmorList() {
		return mArmorList;
	}

	/** @return The armorRecord. */
	public WeaponList getWeaponList() {
		return mWeaponList;
	}

	/** @return The armorRecord. */
	public EquipmentList getEquipmentList() {
		return mEquipmentList;
	}

	public MagicItemList getMagicItemList() {
		return mMagicItemList;
	}

	/** @return The attributesRecord. */
	public AttributesRecord getAttributesRecord() {
		return mAttributesRecord;
	}

	/** @return The combatInformationRecord. */
	public CombatInformationRecord getCombatInformationRecord() {
		return mCombatInformationRecord;
	}

	public HeaderRecord getHeaderRecord() {
		return mHeaderRecord;
	}

	/** @return The moneyRecord. */
	public MoneyRecord getMoneyRecord() {
		return mMoneyRecord;
	}

	/** @return The personalInformationRecord. */
	public PersonalInformationRecord getPersonalInformationRecord() {
		return mPersonalInformationRecord;
	}

	public SavingThrowsRecord getSavingThrowsRecord() {
		return mSavingThrowsRecord;
	}

	public SkillsRecord getSkillsRecord() {
		return mSkillsRecord;
	}

	public SpellListDisplay getSpellListDisplay() {
		return mSpellTab;
	}

	public ArrayList<ArmorRecord> getEquippedArmorRecords() {
		return mArmorInformationDisplay.getEquippedArmor();
	}

	public ArrayList<WeaponRecord> getEquippedWeaponRecords() {
		return mWeaponInformationDisplay.getEquippedWeapons();
	}

	public ClassList getClasses() {
		return ACS.getInstance().getClasses();
	}

	public MageList getMages() {
		return ACS.getInstance().getMages();
	}

	public PriestList getPriests() {
		return ACS.getInstance().getPriests();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	private void openFile(File file) {
		BufferedReader br = null;
		String in;

		try {
			br = new BufferedReader(new FileReader(file));
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					switch (in) {
						case AttributesRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mAttributesRecord.readValues(br);
							break;
						}
						case HeaderRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mHeaderRecord.readValues(br);
							break;
						}
						case MoneyRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mMoneyRecord.readValues(br);
							break;
						}
						case PersonalInformationRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mPersonalInformationRecord.readValues(br);
							break;
						}
						case SpellListDisplay.FILE_SECTTION_START_KEY: {
							tokenizer = mSpellTab.readValues(br);
							break;
						}
						case CombatInformationRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mCombatInformationRecord.readValues(br);
							break;
						}
						case DefenseInformationDisplay.FILE_SECTTION_START_KEY: {
							tokenizer = mDefenseInformationDisplay.readValues(br);
							break;
						}
						case SkillsRecord.FILE_SECTTION_START_KEY: {
							tokenizer = mSkillsRecord.readValues(br);
							break;
						}
						case ArmorList.FILE_SECTTION_START_KEY: {
							tokenizer = mArmorList.readValues(br);
							break;
						}
						case WeaponList.FILE_SECTTION_START_KEY: {
							tokenizer = mWeaponList.readValues(br);
							break;
						}
						case EquipmentList.FILE_SECTTION_START_KEY: {
							tokenizer = mEquipmentList.readValues(br);
							break;
						}
						case MagicItemList.FILE_SECTTION_START_KEY: {
							tokenizer = mMagicItemList.readValues(br);
							break;
						}
						case AnimalList.FILE_SECTTION_START_KEY: {
							tokenizer = mAnimalList.readValues(br);
							break;
						}
						case HistoryManager.FILE_SECTTION_START_KEY: {
							HistoryManager historyManager = HistoryManager.getInstance();
							tokenizer = historyManager.readValues(br);
							mHeaderDisplay.setCurrentExperienceToolTip(historyManager.getTooltip(HistoryManager.EXPERIENCE_KEY));
							mHeaderDisplay.setLevelToolTip(historyManager.getTooltip(HistoryManager.LEVEL_KEY));
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + in); //$NON-NLS-1$
					}
				}
			}
		} catch (FileNotFoundException fnfe) {
			//DW9:: Log this
			System.err.println(fnfe.getMessage());
		} catch (IOException exception) {
			exception.printStackTrace();
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

	private void saveAs() {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save As"); //$NON-NLS-1$
		File temp = new File(PreferenceStore.getInstance().getCurrentFileLocation() + SystemInfo.PATH_SEPARATOR + mHeaderRecord.getCharacterName() + ".acs"); //$NON-NLS-1$
		fc.setCurrentDirectory(temp.getParentFile());
		fc.setSelectedFile(temp);
		if (fc.showSaveDialog(mFrame) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			PreferenceStore.getInstance().setCurrentFileLocation(file.getParentFile());
			mCharacterFile = file.getAbsolutePath();
			saveFile(file);
		}
	}

	private void saveFile(File file) {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(file));
			mHeaderRecord.saveValues(br);
			mAttributesRecord.saveValues(br);
			mMoneyRecord.saveValues(br);
			mPersonalInformationRecord.saveValues(br);
			mSpellTab.saveValues(br);
			mCombatInformationRecord.saveValues(br);
			mDefenseInformationDisplay.saveValues(br);
			mSkillsRecord.saveValues(br);
			mArmorList.saveValues(br);
			mWeaponList.saveValues(br);
			mEquipmentList.saveValues(br);
			mMagicItemList.saveValues(br);
			mAnimalList.saveValues(br);
			HistoryManager.getInstance().saveValues(br);
			JOptionPane.showMessageDialog(mFrame, "File Saved: " + file.getName()); //$NON-NLS-1$
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

}
