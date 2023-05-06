/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import java.awt.Component;

import javax.swing.JPanel;

public class AnimalsTab extends TreasureTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String ANIMALS_TAB_TITLE = "Animals"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link AnimalsTab}.
	 *
	 * @param owner
	 */
	public AnimalsTab(Object owner) {
		super(owner, ANIMALS_TAB_TITLE);
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

	@Override
	protected void addRecord() {
		// DW not implemented yet - old treasure display
		//		AnimalsTreasureRecord record;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
