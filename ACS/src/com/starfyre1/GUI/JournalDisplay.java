/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.JournalRecord;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JournalDisplay extends TKTitledDisplay implements ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	JOURNAL_TITLE	= "Journal";	//$NON-NLS-1$
	private static final String	NEW_ENTRY		= "New Entry";	//$NON-NLS-1$

	//	private static final String	GAME_DATE_LABEL		= "Game Date";		//$NON-NLS-1$
	//	private static final String	CALANDER_DATE_lABEL	= "Calander_Date";	//$NON-NLS-1$

	//DW make non-editable, add journal entry button, add journal entries with border.

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mNewEntryButton;
	//	private JTextArea			mArea;

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
		//		mArea = new JTextArea();
		//		mArea.setBackground(UIManager.getColor("Panel.background")); //$NON-NLS-1$
		//		JScrollPane scrollPane = new JScrollPane(mArea);

		mNewEntryButton = TKComponentHelpers.createButton(NEW_ENTRY, this);

		JPanel panel = new JPanel();
		panel.add(mNewEntryButton);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mNewEntryButton)) {
			JournalRecord record = new JournalRecord();
		}
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
