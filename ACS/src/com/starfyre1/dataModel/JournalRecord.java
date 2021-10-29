/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CampaignDate;
import com.starfyre1.GUI.WorldDate;
import com.starfyre1.startup.ACS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class JournalRecord extends JTextArea {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private final class CampaignDateActionListener implements ActionListener {
		/**
		 * Creates a new {@link CampaignDateActionListener}.
		 */
		private CampaignDateActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			// DW fix this to be relative to owner
			CampaignDate cal = new CampaignDate(ACS.getInstance().getCharacterSheet().getFrame());
			mCampaignButton.setText(cal.getSelectedDate());
		}
	}

	private final class WorldDateActionListener implements ActionListener {
		/**
		 * Creates a new {@link WorldDateActionListener}.
		 */
		private WorldDateActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			// DW fix this to be relative to owner
			WorldDate cal = new WorldDate(ACS.getInstance().getCharacterSheet().getFrame());
			mWorldButton.setText(cal.getSelectedDate());
		}
	}

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private WorldDate		mWorldDate;
	private JButton			mWorldButton;
	private CampaignDate	mCampainDate;
	private JButton			mCampaignButton;
	private Color			mOldColor	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalRecord() {
		super();

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setEditable(true);
		setLineWrap(true);
		setWrapStyleWord(true);

		// DW fix this to be relative to owner
		mWorldDate = new WorldDate(ACS.getInstance().getCharacterSheet().getFrame());
		mCampainDate = new CampaignDate(ACS.getInstance().getCharacterSheet().getFrame());

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public JPanel getJournalRecordHeader() {
		String line1 = new String();
		String line2 = new String();
		try {
			if (getDocument().getLength() > 0) {
				int start1 = getLineStartOffset(0);
				int len1 = getLineEndOffset(0) - start1;
				line1 = getText(start1, len1);

				if (getLineCount() > 1) {
					int start2 = getLineStartOffset(1);
					int len2 = getLineEndOffset(1) - start2;
					line2 = getText(start2, len2);
				}
			}

		} catch (BadLocationException exception) {
			exception.printStackTrace();
		}

		mCampaignButton = getDateButton(mCampainDate.getSelectedDate(), new CampaignDateActionListener());
		mWorldButton = getDateButton(mWorldDate.getSelectedDate(), new WorldDateActionListener());

		JPanel wrapper = new JPanel(new GridLayout(2, 1, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 15, 0, 15));

		JLabel label1 = new JLabel(line1);
		JLabel label2 = new JLabel(line2);

		Font font = label1.getFont().deriveFont(11.0f);

		label1.setFont(font);
		label2.setFont(font);

		wrapper.add(label1);
		wrapper.add(label2);

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);
		panel.setBorder(new LineBorder(Color.BLACK));

		panel.add(mCampaignButton);
		panel.add(wrapper);
		panel.add(mWorldButton);

		panel.setMaximumSize(new Dimension(panel.getMaximumSize().width, panel.getMinimumSize().height));
		return panel;
	}

	private JButton getDateButton(String date, ActionListener listener) {
		JButton dateButton = new JButton(date);
		dateButton.setBorderPainted(false);
		dateButton.setFocusPainted(false);
		dateButton.addActionListener(listener);

		dateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = dateButton.getBackground();
				dateButton.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					dateButton.setBackground(mOldColor);
				}
			}
		});
		return dateButton;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
