/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CampaignDate;
import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.JournalDisplay;
import com.starfyre1.GUI.WorldDate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class JournalRecord extends JTextArea {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JournalDisplay	mParent;
	private JButton			mWorldButton;
	private JButton			mCampaignButton;
	private String			mWorldDate;
	private String			mCampaignDate;
	private JLabel			mHeaderLabel1;
	private JLabel			mHeaderLabel2;
	private Color			mOldColor	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalRecord(JournalDisplay parent) {
		super();

		mParent = parent;

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setEditable(true);
		setLineWrap(true);
		setWrapStyleWord(true);

		mHeaderLabel1 = new JLabel();
		mHeaderLabel2 = new JLabel();

		CharacterSheet sheet = (CharacterSheet) parent.getOwner();
		mWorldDate = sheet.getWorldDate();
		mCampaignDate = sheet.getCampaignDate();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	private void setHeaderText() {
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

		mHeaderLabel1.setText(line1);
		mHeaderLabel2.setText(line2);
	}

	public JPanel getJournalRecordHeader() {
		mCampaignButton = getDateButton(mCampaignDate, new CampaignDateActionListener());
		mWorldButton = getDateButton(mWorldDate, new WorldDateActionListener());

		JPanel textHeaderPanel = new JPanel(new GridLayout(2, 1, 5, 0));
		textHeaderPanel.setBorder(new EmptyBorder(0, 15, 0, 15));
		textHeaderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				displayJournalRecord();
			}
		});

		textHeaderPanel.add(mHeaderLabel1);
		textHeaderPanel.add(mHeaderLabel2);

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);
		panel.setBorder(new LineBorder(Color.BLACK));

		panel.add(mCampaignButton);
		panel.add(textHeaderPanel);
		panel.add(mWorldButton);

		panel.setMaximumSize(new Dimension(panel.getMaximumSize().width, panel.getMinimumSize().height));
		return panel;
	}

	public void displayJournalRecord() {
		JDialog dialog = new JDialog();

		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		dialog.add(scrollPane);

		dialog.setTitle("Journal"); //$NON-NLS-1$
		dialog.setModal(true);
		dialog.setMinimumSize(JournalDisplay.JOURNAL_ENTRY_SIZE);
		dialog.setLocationRelativeTo(((CharacterSheet) mParent.getOwner()).getFrame());
		dialog.setVisible(true);

		setHeaderText();

		mParent.revalidate();
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

	private final class CampaignDateActionListener implements ActionListener {
		/**
		 * Creates a new {@link CampaignDateActionListener}.
		 */
		private CampaignDateActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			CampaignDate cal = new CampaignDate(((CharacterSheet) mParent.getOwner()).getFrame());
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
			WorldDate cal = new WorldDate(((CharacterSheet) mParent.getOwner()).getFrame());
			mWorldButton.setText(cal.getSelectedDate());
		}
	}
}
