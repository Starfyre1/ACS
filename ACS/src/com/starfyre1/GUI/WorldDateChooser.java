/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.startup.ACS;

import java.awt.Color;

import javax.swing.JFrame;

public class WorldDateChooser extends DateChooser {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String			MONTHS_SHORT[]	= { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$

	private static final String	TITLE			= "Calander Date";																			//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WorldDateChooser}.
	 */
	public WorldDateChooser(JFrame parent) {
		super(parent, TITLE, ACS.getInstance().getCharacterSheet().getCurrentWorldYear(), ACS.getInstance().getCharacterSheet().getCurrentWorldMonth(), ACS.getInstance().getCharacterSheet().getCurrentWorldDate());
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
		CharacterSheet characterSheet = ACS.getInstance().getCharacterSheet();
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			mButton[i].setText("" + day); //$NON-NLS-1$
			if (day == characterSheet.getCurrentWorldDate() && mMonth == characterSheet.getCurrentWorldMonth() && mYear == characterSheet.getCurrentWorldYear()) {
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

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
