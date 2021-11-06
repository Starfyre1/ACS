/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.JournalRecord;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JournalDisplay extends TKTitledDisplay implements ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String			JOURNAL_TITLE		= "Journal";				//$NON-NLS-1$
	private static final String			NEW_ENTRY			= "New Entry";				//$NON-NLS-1$

	public static final Dimension		JOURNAL_ENTRY_SIZE	= new Dimension(360, 480);
	private static final String			CAMPAIGN_DATE_LABEL	= "Campaign Date";			//$NON-NLS-1$
	private static final String			WORLD_DATE_lABEL	= "World Date";				//$NON-NLS-1$

	//DW make non-editable, add journal entry button, add journal entries with border.

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton						mNewEntryButton;
	private ArrayList<JournalRecord>	mEntries			= new ArrayList<>();
	private JPanel						mPanel;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalDisplay(CharacterSheet owner) {
		super(owner, JOURNAL_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {

		mNewEntryButton = TKComponentHelpers.createButton(NEW_ENTRY, this);
		mNewEntryButton.setBorderPainted(false);

		JPanel wrapper = new JPanel(new GridLayout(1, 3));
		wrapper.setBorder(new EmptyBorder(0, 5, 0, 5));
		wrapper.add(new JLabel(CAMPAIGN_DATE_LABEL, SwingConstants.LEADING));
		wrapper.add(mNewEntryButton);
		wrapper.add(new JLabel(WORLD_DATE_lABEL, SwingConstants.TRAILING));
		wrapper.setMaximumSize(new Dimension(wrapper.getMaximumSize().width, wrapper.getMinimumSize().height));

		mPanel = new JPanel();
		BoxLayout bl2 = new BoxLayout(mPanel, BoxLayout.Y_AXIS);
		mPanel.setLayout(bl2);

		JPanel upperPanel = new JPanel();
		BoxLayout bl = new BoxLayout(upperPanel, BoxLayout.Y_AXIS);
		upperPanel.setLayout(bl);
		upperPanel.add(wrapper, BorderLayout.NORTH);
		upperPanel.add(mPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(upperPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mNewEntryButton)) {
			createNewJournalRecord();
		}
	}

	private void createNewJournalRecord() {
		JournalRecord record = new JournalRecord(this);
		record.displayJournalRecord(false);

		if (record.saveRecord() && record.getDocument().getLength() != 0) {
			mEntries.add(record);
			updatePreviewPanel();
		}
	}

	/**
	 * @param journalRecord
	 */
	public void removeRecord(JournalRecord journalRecord) {
		mEntries.remove(journalRecord);
		updatePreviewPanel();
	}

	public void updatePreviewPanel() {
		mPanel.removeAll();

		Collections.sort(mEntries);

		for (JournalRecord entry : mEntries) {
			mPanel.add(entry.getJournalRecordHeader(), BorderLayout.CENTER);
		}
		mPanel.revalidate();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	protected void loadDisplay() {
		// DW Load from disk
	}

}
