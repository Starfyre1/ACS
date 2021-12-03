/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.equipment;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class TitlesLandsPropertiesDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TITLES_LANDS_PROPERTIES_TITLE	= "Titles, Lands, and Properties";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextArea			mArea;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TitlesLandsPropertiesDisplay(CharacterSheet owner) {
		super(owner, TITLES_LANDS_PROPERTIES_TITLE);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		mArea = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(mArea);
		mArea.setBackground(UIManager.getColor("Panel.background")); //$NON-NLS-1$
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		// DW Read from file
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
