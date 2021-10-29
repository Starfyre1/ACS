/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.JournalRecord;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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

	private static final Dimension		SIZE				= new Dimension(360, 480);
	private static final String			GAME_DATE_LABEL		= "Campaign Date";			//$NON-NLS-1$
	private static final String			CALANDER_DATE_lABEL	= "Calander Date";			//$NON-NLS-1$

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
		wrapper.add(new JLabel(GAME_DATE_LABEL, SwingConstants.LEADING));
		wrapper.add(mNewEntryButton);
		wrapper.add(new JLabel(CALANDER_DATE_lABEL, SwingConstants.TRAILING));
		wrapper.setMaximumSize(new Dimension(wrapper.getMaximumSize().width, wrapper.getMinimumSize().height));

		mPanel = new JPanel();
		BoxLayout bl = new BoxLayout(mPanel, BoxLayout.Y_AXIS);
		mPanel.setLayout(bl);
		mPanel.add(wrapper, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(mPanel);
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
		JournalRecord record = new JournalRecord();
		JDialog dialog = new JDialog();

		JScrollPane scrollPane = new JScrollPane(record);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		dialog.add(scrollPane);

		dialog.setTitle("Journal"); //$NON-NLS-1$
		dialog.setModal(true);
		dialog.setMinimumSize(SIZE);
		// DW fix this to be relative to owner
		dialog.setLocationRelativeTo(ACS.getInstance().getCharacterSheet().getFrame());
		dialog.setVisible(true);

		mEntries.add(record);
		mPanel.add(record.getJournalRecordHeader(), BorderLayout.CENTER);
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
