/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import java.awt.Color;

import javax.swing.JFrame;

public class CampaignDate extends DateBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	TITLE			= "Campain Date";																																						//$NON-NLS-1$

	private static final String	MONTHS_SHORT[]	= { "Jan", "Feb", "Mar", "Spr", "Apr", "May", "Jun", "Sum", "Jul", "Aug", "Sep", "Fal", "Oct", "Nov", "Dec", "Win" };													//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final String	MONTHS[]		= { "January", "February", "March", "Spring", "April", "May", "June", "Summer", "July", "August", "September", "Fall", "October", "November", "December", "Winter" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final int	DATES[]			= { 31, 28, 31, 7, 30, 31, 30, 7, 31, 31, 30, 7, 31, 30, 31, 7 };
	private static final int	YEAR_AL			= 615;																																									// YEAR_AD			= YEAR_AL - 268;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	private static int			mCurrentYear	= YEAR_AL;
	private static int			mCurrentMonth	= 5;																																									// 0=January... 15=Winter
	private static int			mCurrentDate	= 14;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link CampaignDate}.
	 */
	public CampaignDate(JFrame parent) {
		super(parent, TITLE, mCurrentYear, mCurrentMonth, mCurrentDate);
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
		int daysInMonth = DATES[mMonth - 1];
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			//			System.out.println(i + " " + (6 + dayOfWeek) + " " + day + " " + daysInMonth);
			mButton[i].setText("" + day); //$NON-NLS-1$
			if (day == mCurrentDate && mMonth == mCurrentMonth && mYear == mCurrentYear) {
				mButton[i].setForeground(Color.RED);
			} else {
				mButton[i].setForeground(Color.BLACK);
			}
		}
		mSpacer.setText(MONTHS[mMonth - 1] + " " + String.format("%04d", Integer.valueOf(mYear))); //$NON-NLS-1$ //$NON-NLS-2$
		mSpacer.setBackground(Color.WHITE);
	}

	@Override
	public String getSelectedDate() {
		if (mDay.equals("")) { //$NON-NLS-1$
			return mDay;
		}
		return MONTHS_SHORT[mMonth - 1] + " " + String.format("%02d", Integer.valueOf(mDay)) + ", " + String.format("%04d", Integer.valueOf(mYear)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private int getDayOfWeek(int year, int month, int day) {

		int days = (year - 1) * 393; // == Jan 1 of year
		for (int i = 0; i < month - 1; i++) { //  == last day of last month
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
