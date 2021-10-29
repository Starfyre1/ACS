/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class DateBase extends JDialog {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	DAYS_SHORT[]	= { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	private Color				mOldColor		= null;
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
	 * Creates a new {@link DateBase}.
	 */
	public DateBase(JFrame parent, String title, int year, int month, int date) {
		super(parent, true);
		mYear = year;
		mMonth = month;
		mDate = date;

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

	private JPanel getButtonPanel() {

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

		JButton previous = new JButton("<<Prevous"); //$NON-NLS-1$
		previous.setFocusable(false);
		previous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = previous.getBackground();
				previous.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					previous.setBackground(mOldColor);
				}
			}
		});
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (mMonth > 1) {
					mMonth--;
				} else {
					if (mYear == 1) {
						return;
					}
					mMonth = 15;
					mYear--;
				}
				displayDate();
			}
		});

		JButton next = new JButton("Next>>"); //$NON-NLS-1$
		next.setFocusable(false);
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = next.getBackground();
				next.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					next.setBackground(mOldColor);
				}
			}
		});
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (mMonth < 15) {
					mMonth++;
				} else {
					mMonth = 1;
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
