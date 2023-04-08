/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.journal;

import com.starfyre1.ToolKit.TKButtonRollover;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class DateChooser extends JDialog {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	DAYS_SHORT[]	= { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	protected int[]				mDateValues;
	protected int				mYear;
	protected int				mMonth;																	// 0=January... 15=Winter
	protected int				mDate;
	protected String			mDay			= "";													//$NON-NLS-1$
	JButton[]					mButton			= new JButton[49];
	JLabel						mSpacer			= new JLabel("", SwingConstants.CENTER);				//$NON-NLS-1$

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link DateChooser}.
	 */
	public DateChooser(JFrame parent, String title, int[] date) {
		super(parent, true);

		mDateValues = date;

		mMonth = date[0];
		mDate = date[1];
		mYear = date[2];

		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel centerPanel = new JPanel(new GridLayout(7, 7));
		centerPanel.setPreferredSize(new Dimension(430, 120));

		for (int i = 0; i < mButton.length; i++) {
			final int selection = i;
			mButton[i] = new JButton();
			mButton[i].setFocusPainted(false);
			mButton[i].setBackground(Color.white);
			if (i > 6) {
				mButton[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						mDay = mButton[selection].getActionCommand();
						dispose();
					}
				});
			}
			if (i < 7) {
				mButton[i].setText(DAYS_SHORT[i]);
				mButton[i].setForeground(Color.BLUE);
			}
			centerPanel.add(mButton[i]);
		}

		add(getButtonPanel(), BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(parent);
		displayDate();
		setVisible(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public abstract void displayDate();

	public abstract String getSelectedDate();

	public abstract boolean isWorldCalendar();

	private JPanel getButtonPanel() {

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

		JButton previous = new TKButtonRollover(this, "<<Prevous", false); //$NON-NLS-1$
		previous.setFocusable(false);
		previous.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mMonth > 0) {
					mMonth--;
				} else {
					if (mYear == 1) {
						return;
					}
					mMonth = (isWorldCalendar() ? 12 : 16) - 1; // Zero based
					mYear--;
				}
				displayDate();
			}
		});

		JButton next = new TKButtonRollover(this, "Next>>", false); //$NON-NLS-1$
		next.setFocusable(false);
		next.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mMonth < (isWorldCalendar() ? 12 : 16) - 1) {
					mMonth++;
				} else {
					mMonth = 0;
					mYear++;
				}
				displayDate();
			}
		});

		buttonPanel.add(previous);
		buttonPanel.add(mSpacer);
		buttonPanel.add(next);

		return buttonPanel;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
