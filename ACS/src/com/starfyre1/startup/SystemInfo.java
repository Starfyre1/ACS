/* Copyright (C) Starfyre Enterprises 2016. All rights reserved. */

package com.starfyre1.startup;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("nls")
public class SystemInfo {

	private static final String	OS_NAME							= System.getProperty("os.name").toLowerCase();
	public static final String	PATH_SEPARATOR					= System.getProperty("file.separator");
	private static final String	LINE_SEPARATOR					= System.lineSeparator();
	private static final String	GAME_PREFERENCE_PATH_SUFFIX		= PATH_SEPARATOR + ".ACS" + PATH_SEPARATOR + ".preferences";
	private static final String	GAME_DATA_PATH_SUFFIX			= PATH_SEPARATOR + ".ACS" + PATH_SEPARATOR + ".data";
	private static final String	GAME_LAUNCH_OPTIONS_PATH_SUFFIX	= PATH_SEPARATOR + ".ACS" + PATH_SEPARATOR + ".launch_options";
	private static final String	ANIMAL_DATA_PATH_SUFFIX			= PATH_SEPARATOR + "Animal_User.txt";
	private static final String	ARMOR_DATA_PATH_SUFFIX			= PATH_SEPARATOR + "Armor_User.txt";
	private static final String	EQUIPMENT_DATA_PATH_SUFFIX		= PATH_SEPARATOR + "MagicItem_User.txt";
	private static final String	MAGIC_ITEM_DATA_PATH_SUFFIX		= PATH_SEPARATOR + "Weapon_User.txt";
	private static final String	WEAPON_DATA_PATH_SUFFIX			= PATH_SEPARATOR + "Equipment_User.txt";

	public static String getAnimalUserPath() {
		return getDataPath() + ANIMAL_DATA_PATH_SUFFIX;
	}

	public static String getArmorUserPath() {
		return getDataPath() + ARMOR_DATA_PATH_SUFFIX;
	}

	public static String getEquipmentUserPath() {
		return getDataPath() + EQUIPMENT_DATA_PATH_SUFFIX;
	}

	public static String getMagicItemUserPath() {
		return getDataPath() + MAGIC_ITEM_DATA_PATH_SUFFIX;
	}

	public static String getWeaponUserPath() {
		return getDataPath() + WEAPON_DATA_PATH_SUFFIX;
	}

	/**
	 * Creates a new {@link SystemInfo}.
	 */
	public SystemInfo() {
	}

	public static String getApplicationLocalPath() {
		if (isWindows()) {
			return System.getenv("APPDATA");
		} else if (isMac()) {
			return System.getProperty("user.home") + PATH_SEPARATOR + "Library" + PATH_SEPARATOR + "Application Support";
		} else if (isUnix()) {
			return System.getProperty("user.home");
		} else if (isSolaris()) {
			// DW:: Find Solaris app data
			return System.getProperty("user.dir");
		} else {
			return System.getProperty("user.dir");
		}
	}

	public static String getPreferencesPath() {
		return getApplicationLocalPath() + GAME_PREFERENCE_PATH_SUFFIX;
	}

	public static String getDataPath() {
		return getApplicationLocalPath() + GAME_DATA_PATH_SUFFIX;
	}

	public static String getLaunchOptionsPath() {
		return getApplicationLocalPath() + GAME_LAUNCH_OPTIONS_PATH_SUFFIX;
	}

	public static String getLineSeparator() {
		return LINE_SEPARATOR;
	}

	public static boolean isWindows() {
		return OS_NAME.indexOf("win") >= 0;
	}

	public static boolean isMac() {
		return OS_NAME.indexOf("mac") >= 0;
	}

	public static boolean isUnix() {
		return OS_NAME.indexOf("nix") >= 0 || OS_NAME.indexOf("nux") >= 0 || OS_NAME.indexOf("aix") >= 0;
	}

	public static boolean isSolaris() {
		return OS_NAME.indexOf("sunos") >= 0;
	}

	@Override
	public String toString() {
		System.out.println("OS Name: " + OS_NAME);
		return "OS Name: " + OS_NAME;
	}

	public static Point getCenterLocationPoint(Rectangle outer, Dimension inner) {
		return new Point((outer.width - inner.width) / 2 + outer.x, (outer.height - inner.height) / 2 + outer.y);
	}

	public static Point getShellPlacement(Rectangle bounds) {
		Point location = getMonitorSize();

		return new Point((location.x - bounds.width) / 2, (location.y - bounds.height) / 2);
	}

	public static Point getShellPlacement(Dimension bounds) {
		Point location = getMonitorSize();

		return new Point((location.x - bounds.width) / 2, (location.y - bounds.height) / 2);
	}

	public static Point getMonitorSize() {
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		return new Point(dm.getWidth(), dm.getHeight());
	}
}
