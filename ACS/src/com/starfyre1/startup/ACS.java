/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.startup;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.WorldDateChooser;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.spells.SpellDescriptionList;

import java.awt.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingUtilities;

public class ACS {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final int				DEBUG_LEVEL			= 0;

	public static final String			TITLE				= "ACS (Athri Character Sheet)";																//$NON-NLS-1$

	private static int					MAJOR				= 0;																							// Incompatible changes
	private static int					MINOR				= 1;																							// Compatible changes
	private static int					PATCH				= 2;																							// Bug fixes

	private static String				RELEASE_DATE		= "December 06, 2021";																			//$NON-NLS-1$
	private static String				RELEASE_TIME		= "8:42 UTC/GMT";																				//$NON-NLS-1$
	public static String				COPYRIGHT			= "Copyright:\t2021 Starfyre Enterprises, LLC. All rights reserved.";							//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static ACS					mInstance;

	private static ClassList			mClasses;
	private static MageList				mMages;
	private static PriestList			mPriests;
	private static MetalList			mMetal;
	private static SpellDescriptionList	mSpellDescriptions;

	private int							mCurrentWorldYear	= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	private int							mCurrentWorldMonth	= java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	private int							mCurrentWorldDay	= java.util.Calendar.getInstance().get(java.util.Calendar.DATE);
	private String						mWorldDate			= new String(new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime()));	//$NON-NLS-1$

	private CharacterSheet				mCharacterSheet;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createTables();
		new ACS();
	}

	private ACS() {
		mInstance = this;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mCharacterSheet = new CharacterSheet();
				if (PreferenceStore.getInstance().isAutoLoad()) {
					String lastCharacter = PreferenceStore.getInstance().getCurrentLastCharacter();
					if (lastCharacter != null) {
						File character = new File(lastCharacter);
						if (character.exists()) {
							mCharacterSheet.loadAndUpdate(character);
						}
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

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static ACS getInstance() {
		return mInstance;
	}

	public CharacterSheet getCharacterSheet() {
		return mCharacterSheet;
	}

	/** @return The classes. */
	public ClassList getClasses() {
		return mClasses;
	}

	/** @return The mages. */
	public MageList getMages() {
		return mMages;
	}

	/** @return The priests. */
	public PriestList getPriests() {
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

	/** @return The version. */
	public static String getVersion() {
		return "Version:\t" + getUpdate(); //$NON-NLS-1$
	}

	/** @return The version. */
	public static String getBuildDate() {
		return "Built on:\t" + RELEASE_DATE + " at " + RELEASE_TIME; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** @return The currentWorldYear. */
	public int getCurrentWorldYear() {
		return mCurrentWorldYear;
	}

	/** @param currentWorldYear The value to set for currentWorldYear. */
	public void setCurrentWorldYear(int currentWorldYear) {
		mCurrentWorldYear = currentWorldYear;
	}

	/** @return The currentWorldMonth. */
	public int getCurrentWorldMonth() {
		return mCurrentWorldMonth;
	}

	/** @param currentWorldMonth The value to set for currentWorldMonth. */
	public void setCurrentWorldMonth(int currentWorldMonth) {
		mCurrentWorldMonth = currentWorldMonth;
	}

	/** @return The currentWorldDate. */
	public int getCurrentWorldDay() {
		return mCurrentWorldDay;
	}

	/** @param currentWorldDay The value to set for currentWorldDay. */
	public void setCurrentWorldDay(int currentWorldDay) {
		mCurrentWorldDay = currentWorldDay;
	}

	public static int parseWorldYear(String worldDate) {
		return TKStringHelpers.getIntValue(worldDate.substring(8), 0);
	}

	public static int parseWorldMonth(String worldDate) {
		return WorldDateChooser.getMonthIndex(worldDate.substring(0, 3));
	}

	public static int parseWorldDay(String worldDate) {
		return TKStringHelpers.getIntValue(worldDate.substring(4, 6), 0);
	}

	/** @return The worldDate. */
	public String getWorldDate() {
		return mWorldDate;
	}

	/** @param worldDate The value to set for worldDate. */
	public void setWorldDate(String worldDate) {
		mWorldDate = worldDate;
		setCurrentWorldDay(parseWorldDay(mWorldDate));
		setCurrentWorldMonth(parseWorldMonth(mWorldDate));
		setCurrentWorldYear(parseWorldYear(mWorldDate));
	}

	public static void printSizes(Component comp) {
		System.out.println("Size: " + comp.getSize()); //$NON-NLS-1$
		System.out.println("Pref: " + comp.getPreferredSize()); //$NON-NLS-1$
		System.out.println("Mini: " + comp.getMinimumSize()); //$NON-NLS-1$
		System.out.println("Maxi: " + comp.getMaximumSize()); //$NON-NLS-1$
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
