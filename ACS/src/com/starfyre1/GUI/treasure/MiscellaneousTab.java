/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure;

import java.awt.Component;

import javax.swing.JPanel;

public class MiscellaneousTab extends TreasureTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String MISCELLANEOUS_TAB_TITLE = "Miscellaneous"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MiscellaneousTab}.
	 *
	 * @param owner
	 */
	public MiscellaneousTab(Object owner) {
		super(owner, MISCELLANEOUS_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		return new JPanel();
	}

	@Override
	int getValueTabTotal() {
		return 0;
	}

	protected void addRecord(MiscTreasureRecord record) {
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
