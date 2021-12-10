/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.startup;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.dataModel.storage.PreferenceStore;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.MetalList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.dataset.spells.SpellDescriptionList;

import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingUtilities;

public class ACS {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final int				DEBUG_LEVEL				= 0;

	public static final String			TITLE					= "ACS (Athri Character Sheet)";																																													//$NON-NLS-1$

	private static int					MAJOR					= 0;																																																				// Incompatible changes
	private static int					MINOR					= 1;																																																				// Compatible changes
	private static int					PATCH					= 2;																																																				// Bug fixes

	private static String				RELEASE_DATE			= "December 06, 2021";																																																//$NON-NLS-1$
	private static String				RELEASE_TIME			= "8:42 UTC/GMT";																																																	//$NON-NLS-1$
	public static String				COPYRIGHT				= "Copyright:\t2021 Starfyre Enterprises, LLC. All rights reserved.";																																				//$NON-NLS-1$

	public static final Font			MONOSPACED_FONT			= new Font(Font.MONOSPACED, Font.BOLD, 12);

	private static final int			YEAR_AL					= 615;																																																				// YEAR_AD			= YEAR_AL - 268;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	static ACS							mInstance;

	private static ClassList			mClasses;
	private static MageList				mMages;
	private static PriestList			mPriests;
	private static MetalList			mMetal;
	private static SpellDescriptionList	mSpellDescriptions;

	public int							mCurrentWorldYear		= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	public int							mCurrentWorldMonth		= java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	public int							mCurrentWorldDate		= java.util.Calendar.getInstance().get(java.util.Calendar.DATE);
	public int							mCurrentCampaignYear	= YEAR_AL;
	public int							mCurrentCampaignMonth	= 4;																																																				// 0=January... 15=Winter
	public int							mCurrentCampaignDate	= 14;
	private String						mWorldDate				= new String(new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime()));																														//$NON-NLS-1$
	private String						mCampaignDate			= new String(CampaignDateChooser.MONTHS_SHORT[mCurrentCampaignMonth] + " " + String.format("%02d", Integer.valueOf(mCurrentCampaignDate)) + ", " + String.format("%04d", Integer.valueOf(mCurrentCampaignYear)));	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$);

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
						mCharacterSheet.loadAndUpdate(new File(lastCharacter));
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
	public int getCurrentWorldDate() {
		return mCurrentWorldDate;
	}

	/** @param currentWorldDate The value to set for currentWorldDate. */
	public void setCurrentWorldDate(int currentWorldDate) {
		mCurrentWorldDate = currentWorldDate;
	}

	/** @return The currentCampaignYear. */
	public int getCurrentCampaignYear() {
		return mCurrentCampaignYear;
	}

	/** @param currentCampaignYear The value to set for currentCampaignYear. */
	public void setCurrentCampaignYear(int currentCampaignYear) {
		mCurrentCampaignYear = currentCampaignYear;
	}

	/** @return The currentCampaignMonth. */
	public int getCurrentCampaignMonth() {
		return mCurrentCampaignMonth;
	}

	/** @param currentCampaignMonth The value to set for currentCampaignMonth. */
	public void setCurrentCampaignMonth(int currentCampaignMonth) {
		mCurrentCampaignMonth = currentCampaignMonth;
	}

	/** @return The currentCampaignDate. */
	public int getCurrentCampaignDate() {
		return mCurrentCampaignDate;
	}

	/** @param currentCampaignDate The value to set for currentCampaignDate. */
	public void setCurrentCampaignDate(int currentCampaignDate) {
		mCurrentCampaignDate = currentCampaignDate;
	}

	/** @return The worldDate. */
	public String getWorldDate() {
		return mWorldDate;
	}

	/** @param worldDate The value to set for worldDate. */
	public void setWorldDate(String worldDate) {
		mWorldDate = worldDate;
	}

	/** @return The campaignDate. */
	public String getCampaignDate() {
		return mCampaignDate;
	}

	/** @param campaignDate The value to set for campaignDate. */
	public void setCampaignDate(String campaignDate) {
		mCampaignDate = campaignDate;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
