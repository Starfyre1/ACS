/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.journal;

import com.starfyre1.ToolKit.TKStringHelpers;

import java.awt.Color;

import javax.swing.JFrame;

public class CampaignDateChooser extends DateChooser {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	TITLE					= "Select Campain Date";																																															//$NON-NLS-1$

	public static final String	MONTHS_SHORT[]			= { "Jan", "Feb", "Mar", "Spr", "Apr", "May", "Jun", "Sum", "Jul", "Aug", "Sep", "Fal", "Oct", "Nov", "Dec", "Win" };																								//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final String	MONTHS[]				= { "January", "February", "March", "Spring", "April", "May", "June", "Summer", "July", "August", "September", "Fall", "October", "November", "December", "Winter" };												//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final int	DATES[]					= { 31, 28, 31, 7, 30, 31, 30, 7, 31, 31, 30, 7, 31, 30, 31, 7 };

	private static final int	YEAR_AL					= 615;																																																				// YEAR_AD			= YEAR_AL - 268;
	private static final int	DEFAULT_CAMPAIGN_YEAR	= YEAR_AL;
	private static final int	DEFAULT_CAMPAIGN_MONTH	= 4;																																																				// 0=January... 15=Winter
	private static final int	DEFAULT_CAMPAIGN_DAY	= 15;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int			mCurrentCampaignYear	= DEFAULT_CAMPAIGN_YEAR;
	private static int			mCurrentCampaignMonth	= DEFAULT_CAMPAIGN_MONTH;																																															// 0=January... 15=Winter
	private static int			mCurrentCampaignDay		= DEFAULT_CAMPAIGN_DAY;
	private static String		mCampaignDate			= new String(CampaignDateChooser.MONTHS_SHORT[mCurrentCampaignMonth] + " " + String.format("%02d", Integer.valueOf(mCurrentCampaignDay)) + ", " + String.format("%04d", Integer.valueOf(mCurrentCampaignYear)));	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link CampaignDateChooser}.
	 */
	public CampaignDateChooser(JFrame parent, int[] date) {
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
		int dayOfWeek = getDayOfWeek(mYear, mMonth, mDate);
		int daysInMonth = DATES[mMonth];
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			//			System.out.println(i + " " + (6 + dayOfWeek) + " " + day + " " + daysInMonth);
			mButton[i].setText("" + day); //$NON-NLS-1$
			if (day == getCurrentCampaignDay() && mMonth == getCurrentCampaignMonth() && mYear == getCurrentCampaignYear()) {
				mButton[i].setForeground(Color.RED);
			} else {
				mButton[i].setForeground(Color.BLACK);
			}
		}
		mSpacer.setText(MONTHS[mMonth] + " " + String.format("%04d", Integer.valueOf(mYear))); //$NON-NLS-1$ //$NON-NLS-2$
		mSpacer.setBackground(Color.WHITE);
	}

	@Override
	public String getSelectedDate() {
		if (mDay.equals("")) { //$NON-NLS-1$
			return mDay;
		}
		return MONTHS_SHORT[mMonth] + " " + String.format("%02d", Integer.valueOf(mDay)) + ", " + String.format("%04d", Integer.valueOf(mYear)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/** @return The date[] parsed as [MM] [DD] [YYYY]. */
	public static int[] parseCampaignDate(String campaignDate) {
		int date[] = new int[3];
		date[0] = parseCampaignMonth(campaignDate);
		date[1] = parseCampaignDay(campaignDate);
		date[2] = parseCampaignYear(campaignDate);
		return date;
	}

	public static int parseCampaignYear(String campaignDate) {
		return TKStringHelpers.getIntValue(campaignDate.substring(8), 0);
	}

	public static int parseCampaignMonth(String campaignDate) {
		return CampaignDateChooser.getMonthIndex(campaignDate.substring(0, 3));
	}

	public static int parseCampaignDay(String campaignDate) {
		return TKStringHelpers.getIntValue(campaignDate.substring(4, 6), 0);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The campaignDate. */
	public static String getCampaignDate() {
		return mCampaignDate;
	}

	/** @param campaignDate The value to set for campaignDate. */
	public static void setCampaignDate(String campaignDate) {
		mCampaignDate = campaignDate;
		setCurrentCampaignDay(parseCampaignDay(mCampaignDate));
		setCurrentCampaignMonth(parseCampaignMonth(mCampaignDate));
		setCurrentCampaignYear(parseCampaignYear(mCampaignDate));
	}

	/** @return The currentCampaignYear. */
	public int getCurrentCampaignYear() {
		return mCurrentCampaignYear;
	}

	/** @param currentCampaignYear The value to set for currentCampaignYear. */
	public static void setCurrentCampaignYear(int currentCampaignYear) {
		mCurrentCampaignYear = currentCampaignYear;
	}

	/** @return The currentCampaignMonth. */
	public int getCurrentCampaignMonth() {
		return mCurrentCampaignMonth;
	}

	/** @param currentCampaignMonth The value to set for currentCampaignMonth. */
	public static void setCurrentCampaignMonth(int currentCampaignMonth) {
		mCurrentCampaignMonth = currentCampaignMonth;
	}

	/** @return The currentCampaignDate. */
	public int getCurrentCampaignDay() {
		return mCurrentCampaignDay;
	}

	/** @param currentCampaignDay The value to set for currentCampaignDay. */
	public static void setCurrentCampaignDay(int currentCampaignDay) {
		mCurrentCampaignDay = currentCampaignDay;
	}

	/**
	 * @return The true month number... i.e. 1 for Jan, 15 for Win (Winter week)
	 * @param shortText needs to be the 3 letter abbreviation as specified in MONTHS_SHORT[]
	 */
	public static int getMonthIndex(String shortText) {
		for (int i = 0; i < MONTHS_SHORT.length; i++) {
			if (MONTHS_SHORT[i].equals(shortText)) {
				return i;
			}
		}
		return 0;
	}

	private int getDayOfWeek(int year, int month, int day) {

		int days = (year - 1) * 393; // == Jan 1 of year
		for (int i = 0; i < month; i++) { //  == last day of last month
			days += DATES[i];
		}
		days += day; // == number of days since epoch (1/1/1)

		days = days % 7;
		return days == 0 ? 7 : days;

	}
	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
