/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.startup;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.HelpDialog;
import com.starfyre1.ToolKit.TKFloatFilter;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.spells.SpellDescriptionList;
import com.starfyre1.storage.PreferenceStore;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class ACS {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final int				DEBUG_LEVEL					= 0;

	public static final String			TITLE						= "ACS (Athri Character Sheet)";										//$NON-NLS-1$

	private static int					MAJOR						= 0;																	// Incompatible changes
	private static int					MINOR						= 2;																	// Compatible changes
	private static int					PATCH						= 1;																	// Bug fixes

	public static String				DATA_FILE_VERSION_TITLE		= "Data file version: ";												//$NON-NLS-1$
	public static int					DATA_FILE_VERSION_NUMBER	= 2;

	private static String				RELEASE_DATE				= "April 09, 2022";														//$NON-NLS-1$
	private static String				RELEASE_TIME				= "14:15 UTC/GMT";														//$NON-NLS-1$
	public static String				COPYRIGHT					= "Copyright:\t2023 Starfyre Enterprises, LLC. All rights reserved.";	//$NON-NLS-1$

	public static ImageIcon				IMAGE_PLUS_ICON;
	public static ImageIcon				IMAGE_MINUS_ICON;

	public static final TKIntegerFilter	INTEGER_FILTER				= TKIntegerFilter.getFilterInstance();
	public static final TKFloatFilter	FLOAT_FILTER				= TKFloatFilter.getFilterInstance();

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static ACS					mInstance;

	private static ClassList			mClasses;
	private static MageList				mMages;
	private static PriestList			mPriests;
	private static MetalList			mMetal;
	private static SpellDescriptionList	mSpellDescriptions;

	private CharacterSheet				mCharacterSheet;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createTables();
		createResouces();
		new ACS();
	}

	private ACS() {
		mInstance = this;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception ex) {
					System.err.println(ex);
				}
				ToolTipManager.sharedInstance().setEnabled(PreferenceStore.getInstance().isShowToolTips());

				IMAGE_PLUS_ICON = new ImageIcon(getClass().getClassLoader().getResource("resources/ImagePlus.png")); //$NON-NLS-1$
				IMAGE_MINUS_ICON = new ImageIcon(getClass().getClassLoader().getResource("resources/ImageMinus.png")); //$NON-NLS-1$
				new CharacterSheet();
				if (PreferenceStore.getInstance().isAutoLoad()) {
					String lastCharacter = PreferenceStore.getInstance().getCurrentLastCharacter();
					if (lastCharacter != null) {
						File character = new File(lastCharacter);
						if (character.exists()) {
							mCharacterSheet.setCharacter(lastCharacter);
							mCharacterSheet.loadAndUpdate(character);
						} else {
							new HelpDialog(mCharacterSheet.getFrame());
						}
					} else {
						new HelpDialog(mCharacterSheet.getFrame());
					}
				}
			}
		});
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private static void createTables() {
		mSpellDescriptions = new SpellDescriptionList();
		mSpellDescriptions.generateList();
		mClasses = new ClassList();
		mClasses.generateList();
		mMages = new MageList();
		mMages.generateList();
		mPriests = new PriestList();
		mPriests.generateList();
	}

	private static void createResouces() {
		Path path = Paths.get(SystemInfo.getDataPath());
		Path animalFile = Paths.get(SystemInfo.getAnimalUserPath());
		Path armorFile = Paths.get(SystemInfo.getArmorUserPath());
		Path equipmentFile = Paths.get(SystemInfo.getEquipmentUserPath());
		Path magicItemFile = Paths.get(SystemInfo.getMagicItemUserPath());
		Path weaponFile = Paths.get(SystemInfo.getWeaponUserPath());

		try {
			if (Files.notExists(path)) {
				Files.createDirectories(path);
			}
			if (Files.notExists(armorFile)) {
				Files.createFile(armorFile);
			}
			if (Files.notExists(animalFile)) {
				Files.createFile(animalFile);
			}
			if (Files.notExists(magicItemFile)) {
				Files.createFile(magicItemFile);
			}
			if (Files.notExists(weaponFile)) {
				Files.createFile(weaponFile);
			}
			if (Files.notExists(equipmentFile)) {
				Files.createFile(equipmentFile);
			}

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static ACS getInstance() {
		return mInstance;
	}

	public CharacterSheet getCharacterSheet() {
		return mCharacterSheet;
	}

	/**
	 * @param characterSheet
	 */
	public void setCharacterSheet(CharacterSheet characterSheet) {
		mCharacterSheet = characterSheet;
	}

	/** @return The classes. */
	public static ClassList getClasses() {
		return mClasses;
	}

	/** @return The mages. */
	public static MageList getMages() {
		return mMages;
	}

	/** @return The priests. */
	public static PriestList getPriests() {
		return mPriests;
	}

	/** @return The mMetal. */
	public static MetalList getmMetal() {
		return mMetal;
	}

	/** @return The mSpellDescriptions. */
	public static SpellDescriptionList getSpellDescriptions() {
		return mSpellDescriptions;
	}

	/** @return The UPDATE value. */
	public static String getUpdate() {
		return MAJOR + "." + MINOR + "." + PATCH; //$NON-NLS-1$//$NON-NLS-2$
	}

	public static final String getDataFileVersion() {
		return DATA_FILE_VERSION_TITLE + DATA_FILE_VERSION_NUMBER;
	}

	/** @return The version. */
	public static String getVersion() {
		return "Version:\t" + getUpdate(); //$NON-NLS-1$
	}

	/** @return The version. */
	public static String getBuildDate() {
		return "Built on:\t" + RELEASE_DATE + " at " + RELEASE_TIME; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static void printSizes(Component comp) {
		System.out.println("Size: " + comp.getSize()); //$NON-NLS-1$
		System.out.println("Pref: " + comp.getPreferredSize()); //$NON-NLS-1$
		System.out.println("Mini: " + comp.getMinimumSize()); //$NON-NLS-1$
		System.out.println("Maxi: " + comp.getMaximumSize()); //$NON-NLS-1$
	}

	/**
	 * @return The level based against the given experience.
	 */
	public static int getLevel(int experience) {
		if (experience < 1000) {
			return 1;
		} else if (experience < 3000) {
			return 2;
		} else if (experience < 7000) {
			return 3;
		} else if (experience < 15000) {
			return 4;
		} else if (experience < 30000) {
			return 5;
		} else if (experience < 60000) {
			return 6;
		} else if (experience < 120000) {
			return 7;
		} else {
			return 8 + (experience - 100000) / 100000;
		}
	}

	/**
	 * @return <code>true</code> if we want to show calculations in the ToolTips <code>false</code>
	 *         if we only want to show the final values in the ToolTips
	 */
	public static boolean showCalculations() {
		return PreferenceStore.getInstance().isDetailedToolTips();
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
