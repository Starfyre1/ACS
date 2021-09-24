/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class JournalDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	JOURNAL_TITLE	= "Journal";	//$NON-NLS-1$

	//	private static final String	GAME_DATE_LABEL		= "Game Date";		//$NON-NLS-1$
	//	private static final String	CALANDER_DATE_lABEL	= "Calander_Date";	//$NON-NLS-1$

	//DW make non-editable, add journal entry button, add journal entries with border.

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextArea			mArea;

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
		mArea = new JTextArea();
		mArea.setBackground(UIManager.getColor("Panel.background")); //$NON-NLS-1$

		JScrollPane scrollPane = new JScrollPane(mArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	protected void loadDisplay() {
		// DW Load from disk
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
