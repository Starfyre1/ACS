/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import java.awt.Color;

import javax.swing.JFrame;

public class WorldDate extends DateBase {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	TITLE			= "Game Date";																											//$NON-NLS-1$

	private static final String	MONTHS_SHORT[]	= { "Jan", "Feb", "Mar", "Spr", "Apr", "May", "Jun", "Sum", "Jul", "Aug", "Sep", "Fal", "Oct", "Nov", "Dec", "Win" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final String	DAYS_SHORT[]	= { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };																	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	private static int			mCurrentYear	= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	private static int			mCurrentMonth	= java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);														// 0=January... 15=Winter
	private static int			mCurrentDate	= java.util.Calendar.getInstance().get(java.util.Calendar.DATE);

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WorldDate}.
	 */
	public WorldDate(JFrame parent) {
		super(parent, mCurrentYear, mCurrentMonth, mCurrentDate);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void displayDate() {
		for (int x = 7; x < mButton.length; x++) {
			mButton[x].setText(""); //$NON-NLS-1$
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy"); //$NON-NLS-1$
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(mYear, mMonth, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			mButton[i].setText("" + day); //$NON-NLS-1$
			if (day == mCurrentDate && mMonth == mCurrentMonth && mYear == mCurrentYear) {
				mButton[i].setForeground(Color.RED);
			} else {
				mButton[i].setForeground(Color.BLACK);
			}
		}
		mSpacer.setText(sdf.format(cal.getTime()));
		mSpacer.setBackground(Color.WHITE);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
