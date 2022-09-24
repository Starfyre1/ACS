/* Copyright (C) Starfyre Enterprises 2016. All rights reserved. */

package com.starfyre1.storage;

import com.starfyre1.GUI.PreferencesDisplay;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.startup.SystemInfo;

import java.awt.Rectangle;
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
import java.util.StringTokenizer;

public class PreferenceStore {
	/*
	 * 3 dice or 4 dice dropping lowest
	 * reroll 1s' & 2's
	 * 1 common die
	 * if result is 18 roll D6-3 (min 0) and add to both pair results
	 *
	 */

	/*****************************************************************************
	 * CONSTANTS
	 ****************************************************************************/
	private static final String		WINDOW_BOUNDS_KEY			= "WINDOW_BOUNDS_KEY";					//$NON-NLS-1$
	private static final String		FILE_LOCATION_KEY			= "FILE_LOCATION_KEY";					//$NON-NLS-1$
	private static final String		LAST_CHARACTER_KEY			= "LAST_CHARACTER_KEY";					//$NON-NLS-1$

	private static final String		APP_ROLLS_DICE_KEY			= "APP_ROLLS_DICE_KEY";					// Allow ACS to roll you dice; //$NON-NLS-1$
	private static final String		NUM_DICE_KEY				= "NUMBER_OF_DICE_KEY";					// 4 Dice drop lowest; use 3 dice; Manual entry; //$NON-NLS-1$
	private static final String		REROLL_LOWEST_KEY			= "REROLL_LOWEST_KEY";					// Reroll 1's & 2's //$NON-NLS-1$
	private static final String		USE_COMMON_DIE_KEY			= "USE_COMMON_DIE_KEY";					// use 1 common die //$NON-NLS-1$

	private static final String		AUTO_LOAD_KEY				= "AUTO_LOAD_KEY";						// Auto load last character played  //$NON-NLS-1$
	private static final String		CALENDAR_KEY				= "CALENDAR_KEY";						// AL vs AD Calendar  //$NON-NLS-1$

	private static final String		SHOW_TOOLTIPS_KEY			= "SHOW_TOOLTIPS_KEY";					// Auto load last character played  //$NON-NLS-1$
	private static final String		DETAILED_TOOLTIPS_KEY		= "DETAILED_TOOLTIPS_KEY";				// Auto load last character played  //$NON-NLS-1$

	private static final Rectangle	DEFAULT_WINDOW_BOUNDS		= new Rectangle(50, 50, 1150, 1375);
	//DW change this to installation directory
	private static final String		DEFAULT_FILE_LOCATION		= SystemInfo.getApplicationLocalPath();

	private static final int		DEFAULT_NUM_DICE			= 4;									// 4 Dice drop lowest; use 3 dice; Manual entry;
	private static final int		DEFAULT_REROLL_LOWEST		= 2;									// Reroll 1's & 2's
	private static final boolean	DEFAULT_USE_COMMON_DIE		= true;									// use 1 common die

	private static final boolean	DEFAULT_AUTO_LOAD			= true;
	private static final boolean	DEFAULT_CALENDAR			= true;

	private static final boolean	DEFAULT_SHOW_TOOLTIPS		= true;
	private static final boolean	DEFAULT_DETAILED_TOOLTIPS	= true;
	private static final boolean	DEFAULT_APP_ROLLS_DICE		= false;

	/*****************************************************************************
	 * MEMBER VARIABLES
	 ****************************************************************************/
	private static PreferenceStore	sInstance					= null;

	Rectangle						mWindowBounds;
	String							mFileLocation;
	String							mLastCharacter;

	/*
	 * true = ACS rolls all dice
	 * false = Player enters all dice
	 */
	private boolean					mAppRollsDice;

	/*
	 * 0 = player entered
	 * 3 = 3 Dice
	 * 4 = 4 Dice drop lowest
	 */
	private int						mNumDice;

	/*
	 * 0 = none
	 * 1 = 1's
	 * 2 = 1's & 2's
	 */
	private int						mRerollLowest;

	/*
	 * Use one common dice for stat pairs
	 *
	 * Str - Con
	 * Int - Wis
	 * Dex - Bow Skill
	 * Char - Personal Appearance
	 *
	 * Willpower has no common stat
	 */
	private boolean					mUseCommonDie;
	private boolean					mAutoLoad;
	private boolean					mCalendarAL;
	private boolean					mShowToolTips;
	private boolean					mDetailedToolTips;

	private boolean					mSavedAppRollsDice;
	private int						mSavedNumDice;
	private int						mSavedRerollLowest;
	private boolean					mSavedUseCommonDie;
	private boolean					mSavedAutoLoad;
	private boolean					mSavedCalendarAL;
	private boolean					mSavedShowToolTips;
	private boolean					mSavedDetailedToolTips;

	/*****************************************************************************
	 * CONSTRUCTORS
	 ****************************************************************************/
	private PreferenceStore() {
		setDefaults();
		readValues();
		setSavedValues();
	}

	/*****************************************************************************
	 * METHODS
	 ****************************************************************************/

	/**
	 *
	 */
	private void setSavedValues() {
		mSavedAppRollsDice = mAppRollsDice;
		mSavedNumDice = mNumDice;
		mSavedRerollLowest = mRerollLowest;
		mSavedUseCommonDie = mUseCommonDie;
		mSavedAutoLoad = mAutoLoad;
		mSavedCalendarAL = mCalendarAL;
		mSavedShowToolTips = mShowToolTips;
		mSavedDetailedToolTips = mDetailedToolTips;

	}

	public void setDefaults() {
		mWindowBounds = DEFAULT_WINDOW_BOUNDS;
		mFileLocation = DEFAULT_FILE_LOCATION;
		mLastCharacter = null;

		mAppRollsDice = DEFAULT_APP_ROLLS_DICE;
		mNumDice = DEFAULT_NUM_DICE;
		mRerollLowest = DEFAULT_REROLL_LOWEST;
		mUseCommonDie = DEFAULT_USE_COMMON_DIE;

		mAutoLoad = DEFAULT_AUTO_LOAD;
		mCalendarAL = DEFAULT_CALENDAR;
		mShowToolTips = DEFAULT_SHOW_TOOLTIPS;
		mDetailedToolTips = DEFAULT_DETAILED_TOOLTIPS;
	}

	/**
	 * returns true if values are set to defaults
	 */
	public boolean isDefaults() {
		if (mNumDice == DEFAULT_NUM_DICE && //
						mAppRollsDice == DEFAULT_APP_ROLLS_DICE && // 
						mRerollLowest == DEFAULT_REROLL_LOWEST && //
						mUseCommonDie == DEFAULT_USE_COMMON_DIE && //
						mAutoLoad == DEFAULT_AUTO_LOAD && //
						mCalendarAL == DEFAULT_CALENDAR && //
						mShowToolTips == DEFAULT_SHOW_TOOLTIPS && //
						mDetailedToolTips == DEFAULT_DETAILED_TOOLTIPS) {
			return true;
		}
		return false;
	}

	/**
	 * @param preferencesDisplay
	 */
	public void updateValuesInPreferenceStore(PreferencesDisplay preferencesDisplay) {
		mWindowBounds = DEFAULT_WINDOW_BOUNDS;
		mFileLocation = DEFAULT_FILE_LOCATION;
		mLastCharacter = null;

		mAppRollsDice = preferencesDisplay.getAppRollsDice();
		mNumDice = preferencesDisplay.getNumDice();
		mRerollLowest = preferencesDisplay.getReRollLowest();
		mUseCommonDie = preferencesDisplay.useCommonDice();

		mAutoLoad = preferencesDisplay.isAutoLoad();
		mCalendarAL = preferencesDisplay.isCalendarAL();
		mShowToolTips = preferencesDisplay.isShowToolTips();
		mDetailedToolTips = preferencesDisplay.isDetailedToolTips();
	}

	/*****************************************************************************
	 * INHERITED ABSTRACT METHODS
	 ****************************************************************************/

	/*****************************************************************************
	 * ACCESSORS
	 ****************************************************************************/
	public static PreferenceStore getInstance() {
		if (sInstance == null) {
			sInstance = new PreferenceStore();
		}
		return sInstance;
	}

	/** @return The appRollsDice. */
	public boolean isAppRollsDice() {
		return mAppRollsDice;
	}

	/** @param appRollsDice The value to set for appRollsDice. */
	public void setAppRollsDice(boolean appRollsDice) {
		mAppRollsDice = appRollsDice;
	}

	/** @return The numDice. */
	public int getNumDice() {
		return mNumDice;
	}

	/** @param numDice The value to set for numDice. */
	public void setNumDice(int numDice) {
		mNumDice = numDice;
	}

	/** @return The rerollLowest. */
	public int getRerollLowest() {
		return mRerollLowest;
	}

	/** @param rerollLowest The value to set for rerollLowest. */
	public void setRerollLowest(int rerollLowest) {
		mRerollLowest = rerollLowest;
	}

	/** @return The useCommonDie. */
	public boolean useCommonDie() {
		return mUseCommonDie;
	}

	/** @param useCommonDie The value to set for useCommonDie. */
	public void setUseCommonDie(boolean useCommonDie) {
		mUseCommonDie = useCommonDie;
	}

	/** @return The autoLoad. */
	public boolean isAutoLoad() {
		return mAutoLoad;
	}

	/** @param autoLoad The value to set for autoLoad. */
	public void setAutoLoad(boolean autoLoad) {
		mAutoLoad = autoLoad;
	}
	
	/** @return The savedAutoLoad. */
	public boolean isSavedAutoLoad() {
		return mSavedAutoLoad;
	}

	/** @param savedAutoLoad The value to set for savedAutoLoad. */
	public void setSavedAutoLoad(boolean savedAutoLoad) {
		mSavedAutoLoad = savedAutoLoad;
	}

	/** @return The autoLoadKey. */
	public static String getAutoLoadKey() {
		return AUTO_LOAD_KEY;
	}

	/** @return The defaultAutoLoad. */
	public static boolean isDefaultAutoLoad() {
		return DEFAULT_AUTO_LOAD;
	}

	/** @return The autoLoad. */
	public boolean isShowToolTips() {
		return mShowToolTips;
	}

	/** @param showToolTips The value to set for showToolTips. */
	public void setShowToolTips(boolean showToolTips) {
		mShowToolTips = showToolTips;
	}

	/** @return The savedAutoLoad. */
	public boolean isSavedShowToolTips() {
		return mSavedShowToolTips;
	}

	/** @param savedShowToolTips The value to set for savedShowToolTips. */
	public void setSavedShowToolTips(boolean savedShowToolTips) {
		mSavedShowToolTips = savedShowToolTips;
	}

	/** @return The toolTipKey. */
	public static String getToolTipKey() {
		return SHOW_TOOLTIPS_KEY;
	}

	/** @return The defaultShowToolTips. */
	public static boolean isDefaultShowToolTips() {
		return DEFAULT_SHOW_TOOLTIPS;
	}

	/** @return The detailedToolTips. */
	public boolean isDetailedToolTips() {
		return mDetailedToolTips;
	}

	/** @param detailedToolTips The value to set for detailedToolTips. */
	public void setDetailedToolTips(boolean detailedToolTips) {
		mDetailedToolTips = detailedToolTips;
	}

	/** @return The savedDetailedToolTips. */
	public boolean isSavedDetailedToolTips() {
		return mSavedDetailedToolTips;
	}

	/** @param savedDetailedToolTips The value to set for savedDetailedToolTips. */
	public void setSavedDetailedToolTips(boolean savedDetailedToolTips) {
		mSavedDetailedToolTips = savedDetailedToolTips;
	}

	/** @return The DetailedToolTipsKey. */
	public static String getDetailedToolTipsKey() {
		return DETAILED_TOOLTIPS_KEY;
	}

	/** @return The defaultDetailedToolTips. */
	public static boolean isDefaultDetailedToolTips() {
		return DEFAULT_DETAILED_TOOLTIPS;
	}

	/** @param bounds The value to set for windowSize. */
	public void setWindowBounds(Rectangle bounds) {
		mWindowBounds = bounds;
	}

	/** @return The windowBounds. */
	public Rectangle getWindowBounds() {
		return mWindowBounds;
	}

	public String getCurrentFileLocation() {
		return mFileLocation;
	}

	public void setCurrentFileLocation(File file) {
		mFileLocation = file.getAbsolutePath();
	}

	public String getCurrentLastCharacter() {
		return mLastCharacter;
	}

	public void setCurrentLastCharacter(String lastCharacter) {
		mLastCharacter = lastCharacter;
	}

	/** @return The savedAppRollsDice. */
	public boolean isSavedAppRollsDice() {
		return mSavedAppRollsDice;
	}

	/** @param savedAppRollsDice The value to set for savedAppRollsDice. */
	public void setSavedAppRollsDice(boolean savedAppRollsDice) {
		mSavedAppRollsDice = savedAppRollsDice;
	}

	/** @return The savedNumDice. */
	public int getSavedNumDice() {
		return mSavedNumDice;
	}

	/** @return The savedRerollLowest. */
	public int getSavedRerollLowest() {
		return mSavedRerollLowest;
	}

	/** @return The savedUseCommonDie. */
	public boolean isSavedUseCommonDie() {
		return mSavedUseCommonDie;
	}

	/** @return The defaultNumDice. */
	public static int getDefaultNumDice() {
		return DEFAULT_NUM_DICE;
	}

	/** @return The defaultAppRollsDice. */
	public static boolean getDefaultAppRollsDice() {
		return DEFAULT_APP_ROLLS_DICE;
	}

	/** @return The defaultRerollLowest. */
	public static int getDefaultRerollLowest() {
		return DEFAULT_REROLL_LOWEST;
	}

	/** @return The defaultUseCommonDie. */
	public static boolean isDefaultUseCommonDie() {
		return DEFAULT_USE_COMMON_DIE;
	}

	public boolean isCalendarAL() {
		return mCalendarAL;
	}

	public boolean isSavedCalendarAL() {
		return mSavedCalendarAL;
	}

	public static boolean isDefaultCalendarAL() {
		return DEFAULT_CALENDAR;
	}

	/*****************************************************************************
	 * SERIALIZATION
	 ****************************************************************************/
	private void readValues() {
		File preferences = new File(SystemInfo.getPreferencesPath());
		if (!(preferences.exists() && preferences.canRead())) {
			return;
		}
		BufferedReader br = null;
		String in;

		try {
			br = new BufferedReader(new FileReader(preferences));

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

	public void saveValues() {
		writeValues();
	}

	private void writeValues() {

		String pathString = SystemInfo.getPreferencesPath();
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
			br.write(APP_ROLLS_DICE_KEY + TKStringHelpers.TAB + mAppRollsDice + System.lineSeparator());
			br.write(NUM_DICE_KEY + TKStringHelpers.TAB + mNumDice + System.lineSeparator());
			br.write(REROLL_LOWEST_KEY + TKStringHelpers.TAB + mRerollLowest + System.lineSeparator());
			br.write(USE_COMMON_DIE_KEY + TKStringHelpers.TAB + mUseCommonDie + System.lineSeparator());
			br.write(WINDOW_BOUNDS_KEY + TKStringHelpers.TAB + mWindowBounds.x + "," + mWindowBounds.y + "," + mWindowBounds.width + "," + mWindowBounds.height + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			br.write(AUTO_LOAD_KEY + TKStringHelpers.TAB + mAutoLoad + System.lineSeparator());
			br.write(CALENDAR_KEY + TKStringHelpers.TAB + mCalendarAL + System.lineSeparator());
			br.write(SHOW_TOOLTIPS_KEY + TKStringHelpers.TAB + mShowToolTips + System.lineSeparator());
			br.write(DETAILED_TOOLTIPS_KEY + TKStringHelpers.TAB + mDetailedToolTips + System.lineSeparator());
			br.write(FILE_LOCATION_KEY + TKStringHelpers.TAB + mFileLocation + System.lineSeparator());
			br.write(LAST_CHARACTER_KEY + TKStringHelpers.TAB + mLastCharacter + System.lineSeparator());

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

		setSavedValues();
	}

	private void setKeyValuePair(String key, String value) {
		if (key.equals(APP_ROLLS_DICE_KEY)) {
			mAppRollsDice = TKStringHelpers.getBoolValue(value, DEFAULT_APP_ROLLS_DICE);
		} else if (key.equals(NUM_DICE_KEY)) {
			mNumDice = TKStringHelpers.getIntValue(value, DEFAULT_NUM_DICE);
		} else if (key.equals(APP_ROLLS_DICE_KEY)) {
			mAppRollsDice = TKStringHelpers.getBoolValue(value, DEFAULT_APP_ROLLS_DICE);
		} else if (key.equals(REROLL_LOWEST_KEY)) {
			mRerollLowest = TKStringHelpers.getIntValue(value, DEFAULT_REROLL_LOWEST);
		} else if (key.equals(USE_COMMON_DIE_KEY)) {
			mUseCommonDie = TKStringHelpers.getBoolValue(value, DEFAULT_USE_COMMON_DIE);
		} else if (key.equals(WINDOW_BOUNDS_KEY)) {
			mWindowBounds = TKStringHelpers.getRectangleValue(value, PreferenceStore.DEFAULT_WINDOW_BOUNDS);
		} else if (key.equals(AUTO_LOAD_KEY)) {
			mAutoLoad = TKStringHelpers.getBoolValue(value, DEFAULT_AUTO_LOAD);
		} else if (key.equals(CALENDAR_KEY)) {
			mCalendarAL = TKStringHelpers.getBoolValue(value, DEFAULT_CALENDAR);
		} else if (key.equals(SHOW_TOOLTIPS_KEY)) {
			mShowToolTips = TKStringHelpers.getBoolValue(value, DEFAULT_SHOW_TOOLTIPS);
		} else if (key.equals(DETAILED_TOOLTIPS_KEY)) {
			mDetailedToolTips = TKStringHelpers.getBoolValue(value, DEFAULT_DETAILED_TOOLTIPS);
		} else if (key.equals(FILE_LOCATION_KEY)) {
			mFileLocation = value;
		} else if (key.equals(LAST_CHARACTER_KEY)) {
			mLastCharacter = value;
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

}
