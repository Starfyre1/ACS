/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.startup.ACS;
import com.starfyre1.startup.SystemInfo;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

public class JournalRecord {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final Dimension	SIZE	= new Dimension(360, 480);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private Date					mWorldDate;
	private Date					mCampainDate;
	private JTextArea				mJournalEntry;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalRecord() {

		mJournalEntry = new JTextArea();
		mJournalEntry.setBorder(new EmptyBorder(5, 5, 5, 5));
		mJournalEntry.setEditable(true);
		mJournalEntry.setLineWrap(true);
		mJournalEntry.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(mJournalEntry);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		JDialog dialog = new JDialog();
		Container panel = dialog.getContentPane();
		panel.add(scrollPane);

		dialog.setTitle("Journal");
		dialog.setModal(true);
		dialog.setMinimumSize(SIZE);
		dialog.setLocation(SystemInfo.getCenterLocationPoint(ACS.getInstance().getCharacterSheet().getFrame().getBounds(), SIZE));

		dialog.setVisible(true);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public String getJournalRecordHeader() {
		String header = new String("Test");
		try {
			String line1 = "";
			String line2 = "";
			if (mJournalEntry.getDocument().getLength() > 0) {
				int start1 = mJournalEntry.getLineStartOffset(0);
				int len1 = mJournalEntry.getLineEndOffset(0) - start1;
				line1 = mJournalEntry.getText(start1, len1);

				if (mJournalEntry.getLineCount() > 1) {
					int start2 = mJournalEntry.getLineStartOffset(1);
					int len2 = mJournalEntry.getLineEndOffset(1) - start2;
					line2 = mJournalEntry.getText(start2, len2);
				}
			}

			header = new String("<html>" + mWorldDate + " " + mCampainDate + "<br>" + line1 + "<br>" + line2);
		} catch (BadLocationException exception) {
			exception.printStackTrace();
		}

		return header;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
