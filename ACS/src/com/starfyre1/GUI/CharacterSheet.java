/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.GUI.character.AttackTotalsDisplay;
import com.starfyre1.GUI.character.AttributesDisplay;
import com.starfyre1.GUI.character.CombatInformationDisplay;
import com.starfyre1.GUI.character.DefenseInformationDisplay;
import com.starfyre1.GUI.character.InnateAbilitiesDisplay;
import com.starfyre1.GUI.character.LanguageDisplay;
import com.starfyre1.GUI.character.MoneyDisplay;
import com.starfyre1.GUI.character.PersonalInformationDisplay;
import com.starfyre1.GUI.character.SavingThowsDisplay;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.GUI.determination.DeterminationDisplay;
import com.starfyre1.GUI.journal.JournalDisplay;
import com.starfyre1.GUI.marketPlace.MarketPlace;
import com.starfyre1.GUI.purchasedGear.animal.AnimalsOwnedDisplay;
import com.starfyre1.GUI.purchasedGear.armor.ArmorEquippedDisplay;
import com.starfyre1.GUI.purchasedGear.armor.ArmorOwnedDisplay;
import com.starfyre1.GUI.purchasedGear.equipment.EquipmentOwnedDisplay;
import com.starfyre1.GUI.purchasedGear.magicItems.MagicItemsOwnedDisplay;
import com.starfyre1.GUI.purchasedGear.misc.NotesCommentsDisplay;
import com.starfyre1.GUI.purchasedGear.misc.TitlesLandsPropertiesDisplay;
import com.starfyre1.GUI.purchasedGear.weapon.WeaponEquippedDisplay;
import com.starfyre1.GUI.purchasedGear.weapon.WeaponOwnedDisplay;
import com.starfyre1.GUI.spells.SpellList;
import com.starfyre1.GUI.spells.SpellListDisplay;
import com.starfyre1.GUI.treasure.TreasureDisplay2;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.dataModel.AnimalRecord;
import com.starfyre1.dataModel.ArmorRecord;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.dataModel.CombatInformationRecord;
import com.starfyre1.dataModel.EquipmentRecord;
import com.starfyre1.dataModel.HeaderRecord;
import com.starfyre1.dataModel.LanguageRecord;
import com.starfyre1.dataModel.MagicItemRecord;
import com.starfyre1.dataModel.MoneyRecord;
import com.starfyre1.dataModel.PersonalInformationRecord;
import com.starfyre1.dataModel.SavingThrowsRecord;
import com.starfyre1.dataModel.SkillsRecord;
import com.starfyre1.dataModel.WeaponRecord;
import com.starfyre1.dataset.AnimalList;
import com.starfyre1.dataset.ArmorList;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.DeterminationList;
import com.starfyre1.dataset.EquipmentList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.MagicItemList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.WeaponList;
import com.starfyre1.interfaces.CampaignDateListener;
import com.starfyre1.startup.ACS;
import com.starfyre1.startup.SystemInfo;
import com.starfyre1.storage.HistoryManager;
import com.starfyre1.storage.PreferenceStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.util.Hashtable;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

public class CharacterSheet implements ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String				CHARACTER_SHEET_TITLE		= "Character";													//$NON-NLS-1$
	private static final String				EQUIPMENT_SHEET_TITLE		= "Equipment";													//$NON-NLS-1$
	private static final String				SPELL_SHEET_TITLE			= "Spells";														//$NON-NLS-1$
	private static final String				JOURNAL_SHEET_TITLE			= "Journal";													//$NON-NLS-1$
	private static final String				DETERMINATION_SHEET_TITLE	= "Determination";												//$NON-NLS-1$
	private static final String				TREASURE_SHEET_TITLE		= "Treasure";													//$NON-NLS-1$

	// DW add something useful for the tooltips or remove them
	private static final String				CHARACTER_SHEET_TOOLTIP		= "Character Sheet";											//$NON-NLS-1$
	private static final String				EQUIPMENT_SHEET_TOOLTIP		= "Equipment Sheet";											//$NON-NLS-1$
	private static final String				SPELL_SHEET_TOOLTIP			= "Spell Sheet";												//$NON-NLS-1$
	private static final String				JOURNAL_SHEET_TOOLTIP		= "Journal Sheet";												//$NON-NLS-1$
	private static final String				DETERMINATION_SHEET_TOOLTIP	= "Determination Points Sheet";									//$NON-NLS-1$
	private static final String				TREASURE_SHEET_TOOLTIP		= "Treasure Sheet";												//$NON-NLS-1$

	static final String						ABOUT						= "About";														//$NON-NLS-1$
	static final String						LOG							= "Log File";													//$NON-NLS-1$
	static final String						HELP						= "Help";														//$NON-NLS-1$
	private static final String				PREFERENCES					= "Preferences";												//$NON-NLS-1$
	private static final String				OPTIONS						= "Options";													//$NON-NLS-1$
	private static final String				TOOLS						= "Tools";														//$NON-NLS-1$
	private static final String				MARKET_PLACE				= "Market Place";												//$NON-NLS-1$
	private static final String				EXIT						= "Exit";														//$NON-NLS-1$
	private static final String				SAVE_AS						= "Save As...";													//$NON-NLS-1$
	private static final String				SAVE						= "Save";														//$NON-NLS-1$
	private static final String				DONT_SAVE					= "Don't Save";													//$NON-NLS-1$
	private static final String				CLOSE						= "Close";														//$NON-NLS-1$
	private static final String				OPEN						= "Open...";													//$NON-NLS-1$
	private static final String				NEW							= "New";														//$NON-NLS-1$
	private static final String				FILE						= "File";														//$NON-NLS-1$
	private static final String				CREATE						= "Create";														//$NON-NLS-1$
	private static final String				CANCEL						= "Cancel";														//$NON-NLS-1$

	public static final Dimension			CHARACTER_TAB_TABLE_SIZE	= new Dimension(375, 75);
	public static final Dimension			EQUIPMENT_TAB_TABLE_SIZE	= new Dimension(750, 150);
	public static final Dimension			ARMOR_TAB_TABLE_SIZE		= new Dimension(750, 160);
	public static final Dimension			WEAPONS_TAB_TABLE_SIZE		= new Dimension(750, 50);
	public static final Dimension			MARKET_PLACE_TAB_TABLE_SIZE	= new Dimension(750, 750);
	public static final Insets				BORDER_INSETS				= new Insets(5, 5, 5, 5);
	public static final int					FIELD_SIZE_SMALL			= 2;
	public static final int					FIELD_SIZE_MEDIUM			= 5;
	public static final int					FIELD_SIZE_MEDIUM_LARGE		= 10;
	public static final int					FIELD_SIZE_LARGE			= 14;
	public static final int					FIELD_SIZE_EXLARGE			= 50;
	public static final int					FIELD_SIZE_EXLARGE1			= 25;
	public static final int					CELL_SMALL_MAX_WIDTH		= 45;
	public static final int					CELL_LARGE_MAX_WIDTH		= 75;

	public static Color						SELECTED_COLOR				= new ColorUIResource(UIManager.getColor("Button.focus"));		//$NON-NLS-1$
	public static Color						LABEL_BACKGROUND			= new ColorUIResource(UIManager.getColor("Label.background"));	//$NON-NLS-1$
	public static Font						MONOSPACED_FONT				= new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static Icon						CHARACTER_ICON				= null;
	public static Icon						DETERMINATION_ICON			= null;
	public static Icon						MERCHANT_ICON				= null;

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
	private boolean							mIsCharacterLoaded			= false;
	private boolean							mIsGameDayStarted			= false;

	private JMenuItem						mCloseMenuItem;
	private JMenuItem						mSaveMenuItem;
	private JMenuItem						mSaveAsMenuItem;

	private JMenuItem						mMarketPlaceMenuItem;
	private JMenuItem						mToHitMenuItem;

	private ArrayList<CampaignDateListener>	mCampaignDateListeners;
	private Hashtable<String, Integer>		mWeaponDPTable				= new Hashtable<>();

	// Data Files
	private AttributesRecord				mAttributesRecord;
	private CombatInformationRecord			mCombatInformationRecord;
	private HeaderRecord					mHeaderRecord;
	private PersonalInformationRecord		mPersonalInformationRecord;
	private LanguageRecord					mLanguageRecord;
	private MoneyRecord						mMoneyRecord;
	private SavingThrowsRecord				mSavingThrowsRecord;
	private SkillsRecord					mSkillsRecord;
	private EquipmentList					mEquipmentList;
	private ArmorList						mArmorList;
	private WeaponList						mWeaponList;
	private AnimalList						mAnimalList;
	private MagicItemList					mMagicItemList;
	private DeterminationList				mDeterminationList;

	// Header
	private HeaderDisplay					mHeaderDisplay;

	// Character Tab
	private AttributesDisplay				mAttributesDisplay;
	private SavingThowsDisplay				mSavingThrowsDisplay;
	private CombatInformationDisplay		mCombatInformationDisplay;

	private SkillsDisplay					mSkillsDisplay;
	//	private DeterminationPointsDisplay		mDeterminationPointsDisplay;

	private PersonalInformationDisplay		mPersonalInformationDisplay;
	private LanguageDisplay					mLanguageDisplay;
	private MoneyDisplay					mMoneyDisplay;
	private InnateAbilitiesDisplay			mInnateAbilitiesDisplay;

	private ArmorEquippedDisplay			mArmorEquippedDisplay;
	private DefenseInformationDisplay		mDefenseInformationDisplay;

	private WeaponEquippedDisplay			mWeaponEquippedDisplay;
	private AttackTotalsDisplay				mAttackTotalsDisplay;

	// Equipment Tab
	private EquipmentOwnedDisplay			mEquipmentOwnedDisplay;

	private AnimalsOwnedDisplay				mAnimalsOwnedDisplay;
	private TitlesLandsPropertiesDisplay	mTitlesLandsPropertiesDisplay;
	private NotesCommentsDisplay			mNotesCommentsDisplay;
	private MagicItemsOwnedDisplay			mMagicItemsOwnedDisplay;
	private ArmorOwnedDisplay				mArmorOwnedDisplay;
	private WeaponOwnedDisplay				mWeaponOwnedDisplay;

	// Spell Tab
	private SpellListDisplay				mSpellTab;

	// Journal Tab
	private JournalDisplay					mJournalTab;

	// Determination Tab
	private DeterminationDisplay			mDeterminationTab;

	// Treasure Tab
	private TreasureDisplay2				mTreasureTab;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@code CharacterSheet}.
	 */
	public CharacterSheet() {

		ACS.getInstance().setCharacterSheet(this);
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
		mFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				saveOption(true);
			}
		});

		mCampaignDateListeners = new ArrayList<CampaignDateListener>();

		createMainMenu();

		mHeaderDisplay = new HeaderDisplay(this);

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent characterTab = makeCharacerTab();
		JComponent equipmentTab = makeEquipmentTab();
		mSpellTab = new SpellListDisplay(this);
		mJournalTab = new JournalDisplay(this);
		mDeterminationTab = new DeterminationDisplay(this);
		mTreasureTab = new TreasureDisplay2(this);

		tabbedPane.addTab(CHARACTER_SHEET_TITLE, CHARACTER_ICON, characterTab, CHARACTER_SHEET_TOOLTIP);
		tabbedPane.addTab(EQUIPMENT_SHEET_TITLE, CHARACTER_ICON, equipmentTab, EQUIPMENT_SHEET_TOOLTIP);
		tabbedPane.addTab(SPELL_SHEET_TITLE, CHARACTER_ICON, mSpellTab, SPELL_SHEET_TOOLTIP);
		tabbedPane.addTab(JOURNAL_SHEET_TITLE, CHARACTER_ICON, mJournalTab, JOURNAL_SHEET_TOOLTIP);
		tabbedPane.addTab(DETERMINATION_SHEET_TITLE, CHARACTER_ICON, mDeterminationTab, DETERMINATION_SHEET_TOOLTIP);
		tabbedPane.addTab(TREASURE_SHEET_TITLE, CHARACTER_ICON, mTreasureTab, TREASURE_SHEET_TOOLTIP);

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_E);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_S);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_J);
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_D);
		tabbedPane.setMnemonicAt(5, KeyEvent.VK_T);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(mHeaderDisplay, BorderLayout.NORTH);
		wrapper.add(tabbedPane, BorderLayout.CENTER);

		Container pane = mFrame.getContentPane();
		pane.add(wrapper, BorderLayout.CENTER);

		mFrame.pack();

		PreferenceStore prefs = PreferenceStore.getInstance();
		mFrame.setBounds(prefs.getWindowBounds());

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
		mDefenseInformationDisplay = new DefenseInformationDisplay(this);
		mMoneyDisplay = new MoneyDisplay(this);
		mPersonalInformationDisplay = new PersonalInformationDisplay(this);
		mLanguageDisplay = new LanguageDisplay(this);
		mArmorEquippedDisplay = new ArmorEquippedDisplay(null);
		mInnateAbilitiesDisplay = new InnateAbilitiesDisplay(this);
		mWeaponEquippedDisplay = new WeaponEquippedDisplay(null);
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
		wrapper2.add(mDefenseInformationDisplay);

		JPanel wrapper3 = new JPanel();
		BoxLayout blw3 = new BoxLayout(wrapper3, BoxLayout.X_AXIS);
		wrapper3.setLayout(blw3);
		wrapper3.add(mMoneyDisplay);
		wrapper3.add(mPersonalInformationDisplay);
		wrapper3.add(mLanguageDisplay);

		JPanel wrapper4 = new JPanel();
		BoxLayout blw4 = new BoxLayout(wrapper4, BoxLayout.X_AXIS);
		wrapper4.setLayout(blw4);
		wrapper4.add(mArmorEquippedDisplay);
		wrapper4.add(mInnateAbilitiesDisplay);

		JPanel wrapper5 = new JPanel();
		BoxLayout blw5 = new BoxLayout(wrapper5, BoxLayout.X_AXIS);
		wrapper5.setLayout(blw5);
		wrapper5.add(mWeaponEquippedDisplay);
		wrapper5.add(mAttackTotalsDisplay);

		page.add(wrapper1);
		page.add(wrapper2);
		page.add(wrapper5);
		page.add(wrapper4);
		page.add(wrapper3);

		wrapper5.setPreferredSize(new Dimension(wrapper4.getParent().getSize().width, wrapper5.getPreferredSize().height));

		JScrollPane scrollPane = new JScrollPane(page);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		mInnateAbilitiesDisplay.setPreferredSize(new Dimension(250, wrapper4.getPreferredSize().height + 20));
		mAttackTotalsDisplay.setPreferredSize(new Dimension(250, wrapper4.getPreferredSize().height + 20));

		return scrollPane;
	}

	/**
	 * @return the EquipmentTab
	 */
	private JComponent makeEquipmentTab() {

		JPanel page = new JPanel();
		BoxLayout boxLayout = new BoxLayout(page, BoxLayout.Y_AXIS);
		page.setLayout(boxLayout);

		mEquipmentOwnedDisplay = new EquipmentOwnedDisplay(this);
		mAnimalsOwnedDisplay = new AnimalsOwnedDisplay(this);
		mTitlesLandsPropertiesDisplay = new TitlesLandsPropertiesDisplay(this);
		mNotesCommentsDisplay = new NotesCommentsDisplay(this);
		mMagicItemsOwnedDisplay = new MagicItemsOwnedDisplay(this);
		mWeaponOwnedDisplay = new WeaponOwnedDisplay(this);
		mArmorOwnedDisplay = new ArmorOwnedDisplay(this);

		JPanel wrapper = new JPanel();
		BoxLayout blw = new BoxLayout(wrapper, BoxLayout.X_AXIS);
		wrapper.setLayout(blw);

		wrapper.add(mTitlesLandsPropertiesDisplay);
		wrapper.add(mNotesCommentsDisplay);

		JPanel sizer = new JPanel();
		BoxLayout bl = new BoxLayout(sizer, BoxLayout.Y_AXIS);
		sizer.setLayout(bl);

		sizer.add(mAnimalsOwnedDisplay);
		sizer.add(wrapper);
		sizer.add(mMagicItemsOwnedDisplay);

		page.add(mEquipmentOwnedDisplay);
		page.add(mWeaponOwnedDisplay);
		page.add(mArmorOwnedDisplay);
		page.add(sizer);

		JScrollPane scrollPane = new JScrollPane(page);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		return scrollPane;
	}

	private void enableFileMenuItems() {
		mCloseMenuItem.setEnabled(mIsCharacterLoaded);
		mSaveMenuItem.setEnabled(mIsCharacterLoaded);
		mSaveAsMenuItem.setEnabled(mIsCharacterLoaded);
	}

	private void enableToolsMenuItems() {
		mMarketPlaceMenuItem.setEnabled(mIsCharacterLoaded);
		mToHitMenuItem.setEnabled(mIsCharacterLoaded);
	}

	private void createMainMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu fileMenu = new JMenu(FILE);
		fileMenu.add(TKComponentHelpers.createMenuItem(NEW, this));
		fileMenu.add(TKComponentHelpers.createMenuItem(OPEN, this));
		mCloseMenuItem = TKComponentHelpers.createMenuItem(CLOSE, this);
		fileMenu.add(mCloseMenuItem);
		fileMenu.addSeparator();
		mSaveMenuItem = TKComponentHelpers.createMenuItem(SAVE, this);
		fileMenu.add(mSaveMenuItem);
		mSaveAsMenuItem = TKComponentHelpers.createMenuItem(SAVE_AS, this);
		fileMenu.add(mSaveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(TKComponentHelpers.createMenuItem(EXIT, this));
		// DW find better way to do this... maybe intercept processMouse Event ?
		fileMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				enableFileMenuItems();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Do nothing
			}
		});

		JMenu optionsMenu = new JMenu(OPTIONS);
		optionsMenu.add(TKComponentHelpers.createMenuItem(PREFERENCES, this));

		JMenu toolsMenu = new JMenu(TOOLS);
		mMarketPlaceMenuItem = TKComponentHelpers.createMenuItem(MARKET_PLACE, this);
		toolsMenu.add(mMarketPlaceMenuItem);
		mToHitMenuItem = TKComponentHelpers.createMenuItem(CombatChart.COMBAT_CHART, this);
		toolsMenu.add(mToHitMenuItem);

		// DW find better way to do this... maybe intercept processMouse Event ?
		toolsMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				enableToolsMenuItems();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Do nothing
			}
		});

		JMenu helpMenu = new JMenu(HELP);
		helpMenu.add(TKComponentHelpers.createMenuItem(HELP, this));
		helpMenu.addSeparator();
		helpMenu.add(TKComponentHelpers.createMenuItem(LOG, this));
		helpMenu.addSeparator();
		helpMenu.add(TKComponentHelpers.createMenuItem(ABOUT, this));

		bar.add(fileMenu);
		bar.add(optionsMenu);
		bar.add(toolsMenu);
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
		display.enableFields(true);

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
			//			updateRecords();
		}
	}

	/**
	 * @param lastCharacter
	 */
	public void setCharacter(String lastCharacter) {
		mCharacterFile = lastCharacter;
	}

	private void createAndUpdate() {
		int result = JOptionPane.showConfirmDialog(mFrame, "Do you want to roll your own stats?", "New Character", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		if (result == JOptionPane.YES_OPTION) {
			manualEntry();
			// cancelled out of manualEntry
			if (mAttributesRecord == null) {
				return;
			}
			mIsLoadingData = true;
		} else if (result == JOptionPane.NO_OPTION) {
			mIsLoadingData = true;
			mAttributesRecord = new AttributesRecord(true);
			mAttributesRecord.finalizeCreation(false);
		} else {
			return;
		}

		mSavingThrowsRecord = new SavingThrowsRecord(this, true);
		mCombatInformationRecord = new CombatInformationRecord(this, true);
		mPersonalInformationRecord = new PersonalInformationRecord(this, false); // this will be generated during class creation
		mLanguageRecord = new LanguageRecord(this, true);
		mMoneyRecord = new MoneyRecord(this, false); // this will be generated during class creation
		mSkillsRecord = new SkillsRecord(this, true);
		mEquipmentList = new EquipmentList();
		mArmorList = new ArmorList();
		mWeaponList = new WeaponList();
		mAnimalList = new AnimalList();
		mMagicItemList = new MagicItemList();
		mDeterminationList = new DeterminationList(this);

		loadDisplay();
		mJournalTab.characterCreated();
		mJournalTab.characterLevelUp(mHeaderRecord.getLevel());
		mIsCharacterLoaded = true;
		mIsLoadingData = false;

	}

	public void loadAndUpdate(File file) {
		mIsLoadingData = true;
		if (file == null) {
			file = getCharacterFile();
			if (file == null) {
				mIsLoadingData = false;
				return;
			}
		}

		mAttributesRecord = new AttributesRecord(false);
		mHeaderRecord = new HeaderRecord();
		mMoneyRecord = new MoneyRecord(this, false);
		mPersonalInformationRecord = new PersonalInformationRecord(this, false);
		mLanguageRecord = new LanguageRecord(this, false);
		mCombatInformationRecord = new CombatInformationRecord(this, false);
		mSkillsRecord = new SkillsRecord(this, false);
		mEquipmentList = new EquipmentList();
		mArmorList = new ArmorList();
		mWeaponList = new WeaponList();
		mAnimalList = new AnimalList();
		mMagicItemList = new MagicItemList();
		mDeterminationList = new DeterminationList(this);

		openFile(file);
		mTreasureTab.readPartyTreasure();

		mAttributesRecord.finalizeCreation(false);
		mPersonalInformationRecord.generateCarry();
		mLanguageRecord.updateValues();
		mCombatInformationRecord.updateRecord();
		mSkillsRecord.updateRecord();
		mSavingThrowsRecord = new SavingThrowsRecord(this, true);

		updateForEncubrance();

		loadDisplay();

		mIsCharacterLoaded = true;
		mIsLoadingData = false;

	}

	public String getCharacterFilename(boolean withExtension) {
		if (mCharacterFile == null) {
			return ""; //$NON-NLS-1$
		}
		String filename = mCharacterFile;
		if (!withExtension) {
			int extensionStart = filename.indexOf("."); //$NON-NLS-1$
			if (extensionStart == -1) {
				return filename;
			}
			filename = filename.substring(0, extensionStart);
		}

		return filename;
	}

	private File getCharacterFile() {
		File file = null;
		String os = System.getProperty("os.name"); //$NON-NLS-1$
		boolean osIsMac = os.contains("Mac"); //$NON-NLS-1$
		if (osIsMac) {
			FileDialog fd = new FileDialog(mFrame, "Choose Character", FileDialog.LOAD); //$NON-NLS-1$
			fd.setDirectory(PreferenceStore.getInstance().getCurrentFileLocation());
			fd.setFile("*.acs"); //$NON-NLS-1$
			fd.setVisible(true);
			String child = fd.getFile();
			String parent = fd.getDirectory();
			if (child != null) {
				file = new File(parent, child);
				PreferenceStore.getInstance().setCurrentFileLocation(file.getParentFile());
				mCharacterFile = file.getAbsolutePath();
				PreferenceStore.getInstance().setCurrentLastCharacter(mCharacterFile);
				mFrame.setTitle(ACS.TITLE + " " + file.getName()); //$NON-NLS-1$
			}
		} else {
			final JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(true);
			String[] exts = { "acs" }; //$NON-NLS-1$
			String description = "supported files: " + Arrays.toString(exts).replace('[', '(').replace(']', ')'); //$NON-NLS-1$
			fc.setFileFilter(new FileNameExtensionFilter(description, exts));
			fc.setCurrentDirectory(new File(PreferenceStore.getInstance().getCurrentFileLocation()));
			if (fc.showOpenDialog(mFrame) == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				mCharacterFile = file.getAbsolutePath();
			}
		}
		return file;
	}

	public void loadDisplay() {
		// Primary Character Tab
		mAttributesDisplay.loadDisplay();
		mHeaderDisplay.loadDisplay();
		mSavingThrowsDisplay.loadDisplay();
		mCombatInformationDisplay.loadDisplay();
		mSkillsDisplay.loadDisplay();
		//		mDeterminationPointsDisplay.loadDisplay();
		mPersonalInformationDisplay.loadDisplay();
		mLanguageDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
		mInnateAbilitiesDisplay.loadDisplay();

		// Spell Tab
		mSpellTab.loadDisplay();

		// Equipment Tab
		mEquipmentOwnedDisplay.loadDisplay();
		mAnimalsOwnedDisplay.loadDisplay();
		mTitlesLandsPropertiesDisplay.loadDisplay();
		mNotesCommentsDisplay.loadDisplay();
		mMagicItemsOwnedDisplay.loadDisplay();
		mArmorOwnedDisplay.loadDisplay();
		mWeaponOwnedDisplay.loadDisplay();

		// Secondary Character Tab
		mArmorEquippedDisplay.loadDisplay();
		mWeaponEquippedDisplay.loadDisplay();
		mDefenseInformationDisplay.loadDisplay();
		mAttackTotalsDisplay.loadDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(NEW)) {
			if (saveOption(false) != -1) {
				clearRecords();
				loadDisplay();
				createAndUpdate();
			}
		} else if (cmd.equals(OPEN)) {
			if (saveOption(false) != -1) {
				clearRecords();
				//				loadDisplay();
				loadAndUpdate(null);
			}
		} else if (cmd.equals(CLOSE)) {
			if (saveOption(false) != -1) {
				clearRecords();
				loadDisplay();
			}
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
		} else if (cmd.equals(CombatChart.COMBAT_CHART)) {
			new CombatChart(this);
		} else if (cmd.equals(ABOUT)) {
			new AboutDialog(mFrame);
		} else if (cmd.equals(LOG)) {
			new LogFileDialog(mFrame);
		} else if (cmd.equals(HELP)) {
			// DW open doc's
			new HelpDialog(mFrame);
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
		mIsCharacterLoaded = false;
		if (mAttributesRecord != null) {
			mAttributesRecord.clearRecords();
			mAttributesRecord = null;
		}
		if (mCombatInformationRecord != null) {
			mCombatInformationRecord.clearRecords();
			mCombatInformationRecord = null;
		}
		if (mHeaderRecord != null) {
			mHeaderRecord.clearRecords();
		}
		if (mPersonalInformationRecord != null) {
			mPersonalInformationRecord.clearRecords();
			mPersonalInformationRecord = null;
		}
		if (mLanguageRecord != null) {
			mLanguageRecord.clearRecords();
			mLanguageRecord = null;
		}
		if (mMoneyRecord != null) {
			mMoneyRecord.clearRecords();
			mMoneyRecord = null;
		}
		if (mSavingThrowsRecord != null) {
			mSavingThrowsRecord.clearRecords();
			mSavingThrowsRecord = null;
		}
		if (mSkillsRecord != null) {
			mSkillsRecord.clearRecords();
			mSkillsRecord = null;
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
		if (mDeterminationList != null) {
			mDeterminationList.clearRecords();
		}

		mInnateAbilitiesDisplay.clearRecords();
		mSpellTab.clearRecords();
		mJournalTab.clearRecords();
		mDeterminationTab.clearRecords();
		mTreasureTab.clearRecords();
		mDefenseInformationDisplay.clearRecords();
		HistoryManager historyManager = HistoryManager.getInstance();
		historyManager.clearRecords();
		mHeaderDisplay.setCurrentExperienceToolTip(null);
		mHeaderDisplay.setLevelToolTip(null);
		mCharacterFile = null;
		mFrame.setTitle(ACS.TITLE);
	}

	private int saveOption(boolean exit) {
		if (mIsCharacterLoaded) {
			Object[] options = { SAVE, SAVE_AS, DONT_SAVE };
			int results = JOptionPane.showOptionDialog(mFrame, //
							"Do you want to save?", "Save Character?", //$NON-NLS-1$ //$NON-NLS-2$
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, //
							null, options, options[2]);
			if (results == JOptionPane.YES_OPTION) {
				if (mCharacterFile == null) {
					saveAs();
				} else {
					saveFile(new File(mCharacterFile));
				}
				if (exit) {
					prepForExit();
					System.exit(0);
				}
			} else if (results == JOptionPane.NO_OPTION) {
				saveAs();
				if (exit) {
					prepForExit();
					System.exit(0);
				}
			} else if (results == JOptionPane.CANCEL_OPTION) {
				if (exit) {
					prepForExit();
					System.exit(0);
				}
			} else if (results == -1) {
				return results;
			}
		} else {
			if (exit) {
				prepForExit();
				System.exit(0);
			}
		}
		return 0;
	}

	private void cleanUp() {
		SELECTED_COLOR = null;
		LABEL_BACKGROUND = null;
		MONOSPACED_FONT = null;
		CHARACTER_ICON = null;
		DETERMINATION_ICON = null;
		MERCHANT_ICON = null;
		ACS.IMAGE_PLUS_ICON = null;
	}

	private void prepForExit() {
		cleanUp();
		PreferenceStore prefs = PreferenceStore.getInstance();
		prefs.setWindowBounds(mFrame.getBounds());
		prefs.saveValues();
	}

	public void updateRecords() {
		updateRecords(false);
	}

	public void updateRecords(boolean generate) {
		if (mSavingThrowsRecord != null) {
			mSavingThrowsRecord.updateRecord();
			mCombatInformationRecord.updateRecord();
			mSkillsRecord.updateRecord();
			if (generate) {
				mPersonalInformationRecord.generate();
			} else {
				mPersonalInformationRecord.generateCarry();
			}

			// DW this is probably loading the display twice on character load.
			mSavingThrowsDisplay.loadDisplay();
			//			mDeterminationPointsDisplay.loadDisplay();
			mDefenseInformationDisplay.loadDisplay();
			mSkillsDisplay.loadDisplay();

			updateForEncubrance();
			mCombatInformationDisplay.loadDisplay();
			mHeaderDisplay.updateClassPopup();
			mInnateAbilitiesDisplay.loadDisplay();
			mPersonalInformationDisplay.loadDisplay();
			mLanguageDisplay.loadDisplay();
			mSpellTab.loadDisplay();
		}
	}

	public void updateAttributeRecords(boolean generate) {
		if (mSavingThrowsRecord != null) {
			mSavingThrowsRecord.updateRecord();
			mCombatInformationRecord.updateRecord();
			mSkillsRecord.updateRecord();
			if (generate) {
				mPersonalInformationRecord.generate();
			} else {
				mPersonalInformationRecord.generateCarry();
			}

			// DW this is probably loading the display twice on character load.
			mSavingThrowsDisplay.loadDisplay();
			//mDeterminationPointsDisplay.loadDisplay();
			mDefenseInformationDisplay.loadDisplay();
			mSkillsDisplay.loadDisplay();

			updateForEncubrance();
			mCombatInformationDisplay.loadDisplay();
			//mHeaderDisplay.updateClassPopup();
			//mInnateAbilitiesDisplay.loadDisplay();
			mPersonalInformationDisplay.loadDisplay();
			mLanguageDisplay.loadDisplay();
		}
	}

	/**
	 * @param purchasedItems
	 */
	public void addEquipment(ArrayList<EquipmentRecord> purchasedItems) {
		float cost = mEquipmentList.addEquipment(purchasedItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.spend(cost);
		mEquipmentOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addAnimals(ArrayList<AnimalRecord> purchasedItems) {
		float cost = mAnimalList.addAnimals(purchasedItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.spend(cost);
		mAnimalsOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addArmor(ArrayList<ArmorRecord> purchasedItems) {
		float cost = mArmorList.addArmor(purchasedItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.spend(cost);
		mArmorOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addMagicItems(ArrayList<MagicItemRecord> purchasedItems) {
		float cost = mMagicItemList.addMagicItems(purchasedItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.spend(cost);
		mMagicItemsOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param purchasedItems
	 */
	public void addWeapons(ArrayList<WeaponRecord> purchasedItems) {
		float cost = mWeaponList.addWeapons(purchasedItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.spend(cost);
		mWeaponOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param soldItems
	 */
	public void removeEquipment(ArrayList<EquipmentRecord> soldItems) {
		float cost = mEquipmentList.removeEquipment(soldItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.receive(cost);
		mEquipmentOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param soldItems
	 */
	public void removeAnimals(ArrayList<AnimalRecord> soldItems) {
		float cost = mAnimalList.removeAnimals(soldItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.receive(cost);
		mAnimalsOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param soldItems
	 */
	public void removeArmor(ArrayList<ArmorRecord> soldItems) {
		float cost = mArmorList.removeArmor(soldItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.receive(cost);
		mArmorOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param soldItems
	 */
	public void removeMagicItems(ArrayList<MagicItemRecord> soldItems) {
		float cost = mMagicItemList.removeMagicItems(soldItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.receive(cost);
		mMagicItemsOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * @param soldItems
	 */
	public void removeWeapons(ArrayList<WeaponRecord> soldItems) {
		float cost = mWeaponList.removeWeapons(soldItems, !MarketPlace.getInstance().isFreeChecked());
		cost = cost * MarketPlace.getInstance().getPercentCost() / 100;
		mMoneyRecord.receive(cost);
		mWeaponOwnedDisplay.loadDisplay();
		mMoneyDisplay.loadDisplay();
	}

	/**
	 * Calls all who want to know when the campaign date has changed.
	 */
	public void fireDateUpdated(String date) {
		for (CampaignDateListener listener : mCampaignDateListeners) {
			listener.dateUpdated(date);
		}
	}

	/**
	 * add listener to list.
	 */
	public void addCampaignDateListener(CampaignDateListener listener) {
		mCampaignDateListeners.add(listener);
	}

	/**
	 * remove listener from list.
	 */
	public void removeCampaignDateListener(CampaignDateListener listener) {
		mCampaignDateListeners.remove(listener);
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

	public void setLoadingData(boolean loading) {
		mIsLoadingData = loading;
	}

	/**
	 * @return Percentage, as an int, of carry vs encumbrance;
	 */
	public int getPercentEncumbrance() {
		PersonalInformationRecord record = getPersonalInformationRecord();

		if (record == null) {
			return 0;
		}

		int carry = record.getCarry();
		if (carry == 0) {
			return 0;
		}
		float encumbrance = getTotalEncumbrance();
		return (int) encumbrance / carry * 100;
	}

	public float getTotalEncumbrance() {
		float encumbrance = 0;

		ArrayList<ArmorRecord> armors = getEquippedArmorRecords();
		ArrayList<WeaponRecord> weapons = getEquippedWeaponRecords();
		ArrayList<EquipmentRecord> equipments = getEquipmentList().getRecords();
		ArrayList<MagicItemRecord> magicItems = getMagicItemList().getRecords();

		if (armors != null) {
			for (ArmorRecord armor : armors) {
				if (armor.isEquipped()) {
					encumbrance += armor.getEncumbrance();
				}
			}
		}

		if (weapons != null) {
			for (WeaponRecord weapon : weapons) {
				if (weapon.isEquipped()) {
					encumbrance += weapon.getEncumbrance();
				}
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

		encumbrance += mMoneyRecord.getEncumbrance();

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

		// DW fix - the LevelBonuses need to be modified but not here... this should all be done on the display and not on the records
		if (value >= 4) {
			pir.setMovement(0);
			cir.setAttackSpeed(0);
			cir.setHitBonus(0);
			cir.setMissileSpeed(0);
			cir.setMissileBonus(0);
			cir.setBowSpeed(0);
			cir.setBowBonus(0);
		} else if (value == 3) {
			pir.setMovement((int) (pir.getMovement() * .25));
			cir.setAttackSpeed(cir.getAttackSpeed() - 5);
			cir.setHitBonus((int) ((cir.getHitBonus() + cir.getHitLevelBonus()) * .75));
			cir.setMissileSpeed(cir.getMissileSpeed() - 5);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .75));
			cir.setBowSpeed(cir.getBowSpeed() - 5);
			cir.setBowBonus((int) ((cir.getBowBonus() + cir.getBowLevelBonus()) * .75));
			str.setSurprise(str.getSurprise() - 20);
		} else if (value == 2) {
			pir.setMovement((int) (pir.getMovement() * .50));
			cir.setAttackSpeed(cir.getAttackSpeed() - 3);
			cir.setHitBonus((int) ((cir.getHitBonus() + cir.getHitLevelBonus()) * .85));
			cir.setMissileSpeed(cir.getMissileSpeed() - 3);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .85));
			cir.setBowSpeed(cir.getBowSpeed() - 3);
			cir.setBowBonus((int) ((cir.getBowBonus() + cir.getBowLevelBonus()) * .85));
			str.setSurprise(str.getSurprise() - 10);
		} else if (value == 1) {
			pir.setMovement((int) (pir.getMovement() * .75));
			cir.setAttackSpeed(cir.getAttackSpeed() - 1);
			cir.setHitBonus((int) ((cir.getHitBonus() + cir.getHitLevelBonus()) * .95));
			cir.setMissileSpeed(cir.getMissileSpeed() - 1);
			cir.setMissileBonus((int) (cir.getMissileBonus() * .95));
			cir.setBowSpeed(cir.getBowSpeed() - 1);
			cir.setBowBonus((int) ((cir.getBowBonus() + cir.getBowLevelBonus()) * .95));
		} else {
			cir.updateRecord();
			//			cir.setMovement(cir);
			//				cir.setAttackSpeed(0);
			//				cir.setHitBonus(0);
			//				cir.setMissileSpeed(0);
			//				cir.setMissileBonus(0);
			//				cir.setBowSpeed(0);
			//				cir.setBowBonus(0);
		}
		mCombatInformationDisplay.loadDisplay();
		mPersonalInformationDisplay.loadDisplay();
	}

	/** @return The animalRecord. */
	public AnimalList getAnimalList() {
		return mAnimalList;
	}

	/** @return The armorRecord. */
	public ArmorList getArmorList() {
		return mArmorList;
	}

	/** @return The weaponRecord. */
	public WeaponList getWeaponList() {
		return mWeaponList;
	}

	/** @return The weaponRecord. */
	public WeaponRecord getWeapon(String name) {
		return mWeaponList.getRecord(name);
	}

	/** @return The equipmentRecord. */
	public EquipmentList getEquipmentList() {
		return mEquipmentList;
	}

	public MagicItemList getMagicItemList() {
		return mMagicItemList;
	}

	public DeterminationList getDeterminationList() {
		return mDeterminationList;
	}

	/** @return The attributesRecord. */
	public AttributesRecord getAttributesRecord() {
		return mAttributesRecord;
	}

	public AttackTotalsDisplay getAttackTotalsDisplay() {
		return mAttackTotalsDisplay;
	}

	public SkillsDisplay getSkillsDisplay() {
		return mSkillsDisplay;
	}

	public AttributesDisplay getAttributesDisplay() {
		return mAttributesDisplay;
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

	public void updateMoneyRecord() {
		mMoneyRecord = new MoneyRecord(this, true);
		mMoneyDisplay.loadDisplay();
	}

	/** @return The personalInformationRecord. */
	public PersonalInformationRecord getPersonalInformationRecord() {
		return mPersonalInformationRecord;
	}

	public LanguageRecord getLanguageRecord() {
		return mLanguageRecord;
	}

	public LanguageDisplay getLanguageDisplay() {
		return mLanguageDisplay;
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
		return mArmorEquippedDisplay.getEquippedArmor();
	}

	public void equipArmor(ArmorRecord equipment, int index) {
		mArmorEquippedDisplay.equipArmor(equipment, index);
		mDefenseInformationDisplay.loadDisplay();
		mPersonalInformationDisplay.loadDisplay();
	}

	public void unEquipArmor(ArmorRecord equipment) {
		mArmorEquippedDisplay.unEquipArmor(equipment);
		mDefenseInformationDisplay.loadDisplay();
		mPersonalInformationDisplay.loadDisplay();
	}

	public WeaponOwnedDisplay getWeaponOwnedDiplay() {
		return mWeaponOwnedDisplay;
	}

	public WeaponEquippedDisplay getEquippedWeaponDisplay() {
		return mWeaponEquippedDisplay;
	}

	public ArrayList<WeaponRecord> getEquippedWeaponRecords() {
		return getEquippedWeaponDisplay().getEquippedWeapons();
	}

	public void equipWeapon(WeaponRecord equipment, int index) {
		mWeaponEquippedDisplay.equipWeapon(equipment, index);
	}

	public void unEquipWeapon(WeaponRecord equipment) {
		mWeaponEquippedDisplay.unEquipWeapon(equipment);
	}

	public void addWeaponDP(String key, int value) {
		mWeaponDPTable.put(key, Integer.valueOf(value));
	}

	public int getWeaponDPValue(String key) {
		return mWeaponDPTable.containsKey(key) ? mWeaponDPTable.get(key).intValue() : 0;
	}

	public ClassList getClasses() {
		return ACS.getClasses();
	}

	public MageList getMages() {
		return ACS.getMages();
	}

	public PriestList getPriests() {
		return ACS.getPriests();
	}

	public JFrame getFrame() {
		return mFrame;
	}

	public boolean hasItemsToSell() {
		try {
			if (mEquipmentList.getRecords().isEmpty() && mArmorList.getRecords().isEmpty() && mWeaponList.getRecords().isEmpty() && mAnimalList.getRecords().isEmpty() && mMagicItemList.getRecords().isEmpty()) {
				return false;
			}
		} catch (NullPointerException npe) {
			return false;
		}
		return true;
	}

	public DeterminationDisplay getDeterminationPointsDisplay() {
		return mDeterminationTab;
	}

	public void loadDeterminationPointsDisplay() {
		mDeterminationTab.loadDisplay();
	}

	public TKTable getArmorOwnedTable() {
		return mArmorOwnedDisplay.getTable();
	}

	public TKTable getWeaponOwnedTable() {
		return mWeaponOwnedDisplay.getTable();
	}

	public TKTable getAnimalsOwnedTable() {
		return mAnimalsOwnedDisplay.getTable();
	}

	public TKTable getEquipmentOwnedTable() {
		return mEquipmentOwnedDisplay.getTable();
	}

	public TKTable getMagicItemsOwnedTable() {
		return mMagicItemsOwnedDisplay.getTable();
	}

	public DefenseInformationDisplay getDefenseInformationDisplay() {
		return mDefenseInformationDisplay;
	}

	/** @return The journalTab. */
	public JournalDisplay getJournalTab() {
		return mJournalTab;
	}

	/** @return The determinationTab. */
	public DeterminationDisplay getDeterminationTab() {
		return mDeterminationTab;
	}

	/**
	 * @param date
	 */
	public void setGameDay(String date) {
		mHeaderDisplay.setCampaignButton(date);
	}

	/**
	 * @param started
	 */
	public void setGameDayStarted(boolean started) {
		mIsGameDayStarted = started;
	}

	public boolean hasGameDayStarted() {
		return mIsGameDayStarted;
	}

	/** @return The isCharacterLoaded. */
	public boolean isCharacterLoaded() {
		return mIsCharacterLoaded;
	}

	public ArrayList<String> getAllKnownSchoolNames() {
		if (mSpellTab == null) {
			return null;
		}
		Component comp[] = mSpellTab.getCards();
		ArrayList<String> knownSchools = new ArrayList<>();
		for (Component element : comp) {
			if (element instanceof SpellList) {
				knownSchools.add(((SpellList) element).getName());
			}
		}
		return knownSchools;
	}

	public File verifyDataFileVersion(File file) throws IOException, FileNotFoundException {
		BufferedReader br = null;
		String in;

		try {
			br = new BufferedReader(new FileReader(file));
			in = br.readLine();
			if (in.equals(ACS.getDataFileVersion())) {
				// file version up to date
				return file;
			}
			if (in.startsWith(ACS.DATA_FILE_VERSION_TITLE)) {
				// has invalid file version -- not up to date
				// not used yet.
			} else {
				// no file version -- fix SECTTION -> SECTION
				String originalFile = file.getAbsolutePath();
				File tempFile = new File(file.getParent() + SystemInfo.PATH_SEPARATOR + "temp.temp"); //$NON-NLS-1$
				String transitionFile = originalFile + "-old"; //$NON-NLS-1$

				BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
				bw.write(ACS.getDataFileVersion() + SystemInfo.getLineSeparator());

				bw.write(in.replace("SECTTION", "SECTION") + SystemInfo.getLineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
				while ((in = br.readLine()) != null) {
					if (in.startsWith("DEATH_KEY")) { //$NON-NLS-1$
						continue;
					}
					bw.write(in.replace("SECTTION", "SECTION") + SystemInfo.getLineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
				}
				bw.close();
				br.close();
				File trans = new File(transitionFile);
				if (trans.exists()) {
					trans.delete();
				}

				file.renameTo(trans);
				tempFile.renameTo(new File(originalFile));
			}
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException exception) {
				// nothing we can do...
			}
		}
		return file;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	private void openFile(File file) {
		BufferedReader br = null;
		String in;

		try {
			file = verifyDataFileVersion(file);
			br = new BufferedReader(new FileReader(file));
			while ((in = br.readLine()) != null) {
				if (in.equals(ACS.getDataFileVersion())) {
					in = br.readLine();
				}
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					switch (in) {
						case AttributesRecord.FILE_SECTION_START_KEY: {
							tokenizer = mAttributesRecord.readValues(br);
							break;
						}
						case HeaderRecord.FILE_SECTION_START_KEY: {
							tokenizer = mHeaderRecord.readValues(br);
							break;
						}
						case MoneyRecord.FILE_SECTION_START_KEY: {
							tokenizer = mMoneyRecord.readValues(br);
							break;
						}
						case PersonalInformationRecord.FILE_SECTION_START_KEY: {
							tokenizer = mPersonalInformationRecord.readValues(br);
							break;
						}
						case LanguageRecord.FILE_SECTION_START_KEY: {
							tokenizer = mLanguageRecord.readValues(br);
							break;
						}
						case SpellListDisplay.FILE_SECTION_START_KEY: {
							tokenizer = mSpellTab.readValues(br);
							break;
						}
						case CombatInformationRecord.FILE_SECTION_START_KEY: {
							tokenizer = mCombatInformationRecord.readValues(br);
							break;
						}
						case DefenseInformationDisplay.FILE_SECTION_START_KEY: {
							tokenizer = mDefenseInformationDisplay.readValues(br);
							break;
						}
						case SkillsRecord.FILE_SECTION_START_KEY: {
							tokenizer = mSkillsRecord.readValues(br);
							break;
						}
						case ArmorList.FILE_SECTION_START_KEY: {
							tokenizer = mArmorList.readValues(br);
							break;
						}
						case WeaponList.FILE_SECTION_START_KEY: {
							tokenizer = mWeaponList.readValues(br);
							break;
						}
						case EquipmentList.FILE_SECTION_START_KEY: {
							tokenizer = mEquipmentList.readValues(br);
							break;
						}
						case MagicItemList.FILE_SECTION_START_KEY: {
							tokenizer = mMagicItemList.readValues(br);
							break;
						}
						case AnimalList.FILE_SECTION_START_KEY: {
							tokenizer = mAnimalList.readValues(br);
							break;
						}
						case DeterminationList.FILE_SECTION_START_KEY: {
							tokenizer = mDeterminationList.readValues(br);
							break;
						}
						case JournalDisplay.FILE_SECTION_START_KEY: {
							tokenizer = mJournalTab.readValues(br);
							break;
						}
						case HistoryManager.FILE_SECTION_START_KEY: {
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
			mFrame.setTitle(ACS.TITLE + " " + file.getName()); //$NON-NLS-1$
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
			mFrame.setTitle(ACS.TITLE + " " + file.getName()); //$NON-NLS-1$

		}
	}

	private void saveFile(File file) {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(file));
			br.write(ACS.getDataFileVersion() + SystemInfo.getLineSeparator());
			mHeaderRecord.saveValues(br);
			mAttributesRecord.saveValues(br);
			mMoneyRecord.saveValues(br);
			mPersonalInformationRecord.saveValues(br);
			mLanguageRecord.saveValues(br);
			mSpellTab.saveValues(br);
			mCombatInformationRecord.saveValues(br);
			mDefenseInformationDisplay.saveValues(br);
			mSkillsRecord.saveValues(br);
			mArmorList.saveValues(br);
			mWeaponList.saveValues(br);
			mEquipmentList.saveValues(br);
			mMagicItemList.saveValues(br);
			mAnimalList.saveValues(br);
			mDeterminationList.saveValues(br);
			mJournalTab.saveValues(br);
			HistoryManager.getInstance().saveValues(br);
			JOptionPane.showMessageDialog(mFrame, "File Saved: " + file.getName()); //$NON-NLS-1$
			PreferenceStore.getInstance().setCurrentLastCharacter(mCharacterFile);
			mFrame.setTitle(ACS.TITLE + " " + file.getName()); //$NON-NLS-1$
			mTreasureTab.savePartyTreasure();
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

	/**
	 * This updates SavingThrowDisplay, SkillsDisplay, CombatInformationDisplay ToolTips only
	 */
	public void updateToolTips() {
		if (mIsCharacterLoaded) {
			mSavingThrowsDisplay.updateToolTips();
			mSkillsDisplay.updateToolTips(getHeaderRecord().getCharacterClass() == null);
			mCombatInformationDisplay.updateToolTips();
		}
	}

}
