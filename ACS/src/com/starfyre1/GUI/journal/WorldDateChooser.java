/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.journal;

import com.starfyre1.ToolKit.TKStringHelpers;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;

public class WorldDateChooser extends DateChooser {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String			MONTHS_SHORT[]		= { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };		//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$

	private static final String	TITLE				= "Select Calander Date";																		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private int					mCurrentWorldYear	= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	private int					mCurrentWorldMonth	= java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	private int					mCurrentWorldDay	= java.util.Calendar.getInstance().get(java.util.Calendar.DATE);
	private static String		mWorldDate			= new String(new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime()));	//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WorldDateChooser}.
	 */
	public WorldDateChooser(JFrame parent, int[] date) {
		super(parent, TITLE, date);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void displayDate() {
		for (int x = 7; x < mButton.length; x++) {
			mButton[x].setText(""); //$NON-NLS-1$
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy"); //$NON-NLS-1$
		Calendar cal = Calendar.getInstance();
		cal.set(mYear, mMonth, 1);
		//		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		//		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			mButton[i].setText(Integer.toString(day));
			if (day == getCurrentWorldDay() && mMonth == getCurrentWorldMonth() && mYear == getCurrentWorldYear()) {
				mButton[i].setForeground(Color.RED);
			} else {
				mButton[i].setForeground(Color.BLACK);
			}
		}
		mSpacer.setText(sdf.format(cal.getTime()));
		mSpacer.setBackground(Color.WHITE);
	}

	@Override
	public String getSelectedDate() {
		if (mDay.equals("")) { //$NON-NLS-1$
			return mDay;
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM dd, yyyy"); //$NON-NLS-1$
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(mYear, mMonth, Integer.parseInt(mDay));
		return sdf.format(cal.getTime());
	}

	/** @return The date[] parsed as [MM] [DD] [YYYY]. */
	public static int[] parseWorldDate(String worldDate) {
		int date[] = new int[3];
		date[0] = parseWorldMonth(worldDate) - 1;
		date[1] = parseWorldDay(worldDate);
		date[2] = parseWorldYear(worldDate);
		return date;
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

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/**
	 * @return The true month number... i.e. 1 for Jan, 12 for Dec
	 * @param shortText needs to be the 3 letter abbreviation as specified in MONTHS_SHORT
	 */
	public static int getMonthIndex(String shortText) {
		for (int i = 0; i < MONTHS_SHORT.length; i++) {
			if (MONTHS_SHORT[i].equals(shortText)) {
				return i + 1;
			}
		}
		return 0;
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

	/** @return The worldDate. */
	public static String getWorldDate() {
		return mWorldDate;
	}

	/** @param worldDate The value to set for worldDate. */
	public void setWorldDate(String worldDate) {
		mWorldDate = worldDate;
		setCurrentWorldDay(parseWorldDay(mWorldDate));
		setCurrentWorldMonth(parseWorldMonth(mWorldDate));
		setCurrentWorldYear(parseWorldYear(mWorldDate));
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
