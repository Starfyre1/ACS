/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.startup.ACS;

import java.awt.Color;

import javax.swing.JFrame;

public class CampaignDateChooser extends DateChooser {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	TITLE			= "Campain Date";																																						//$NON-NLS-1$

	static final String			MONTHS_SHORT[]	= { "Jan", "Feb", "Mar", "Spr", "Apr", "May", "Jun", "Sum", "Jul", "Aug", "Sep", "Fal", "Oct", "Nov", "Dec", "Win" };													//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final String	MONTHS[]		= { "January", "February", "March", "Spring", "April", "May", "June", "Summer", "July", "August", "September", "Fall", "October", "November", "December", "Winter" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	private static final int	DATES[]			= { 31, 28, 31, 7, 30, 31, 30, 7, 31, 31, 30, 7, 31, 30, 31, 7 };

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link CampaignDateChooser}.
	 */
	public CampaignDateChooser(JFrame parent) {
		super(parent, TITLE, ACS.getInstance().getCharacterSheet().getCurrentCampaignYear(), ACS.getInstance().getCharacterSheet().getCurrentCampaignMonth(), ACS.getInstance().getCharacterSheet().getCurrentCampaignDate());
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
		CharacterSheet characterSheet = ACS.getInstance().getCharacterSheet();
		for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
			//			System.out.println(i + " " + (6 + dayOfWeek) + " " + day + " " + daysInMonth);
			mButton[i].setText("" + day); //$NON-NLS-1$
			if (day == characterSheet.getCurrentCampaignDate() && mMonth == characterSheet.getCurrentCampaignMonth() && mYear == characterSheet.getCurrentCampaignYear()) {
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

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/**
	 * @return The true month number... i.e. 1 for Jan, 15 for Win (Winter week)
	 * @param shortText needs to be the 3 letter abbreviation as specified in MONTHS_SHORT[]
	 */

	public static int getMonthIndex(String shortText) {
		for (int i = 0; i < MONTHS_SHORT.length; i++) {
			if (MONTHS_SHORT[i].equals(shortText)) {
				return i + 1;
			}
		}
		return 0;
	}

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
