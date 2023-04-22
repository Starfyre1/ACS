/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import com.starfyre1.dataModel.treasure.JewelryTreasureRecord;

import java.awt.Component;

import javax.swing.JPanel;

public class JewelryTab extends TreasureTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String JEWELRY_TAB_TITLE = "Jewelry"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link JewelryTab}.
	 *
	 * @param owner
	 */
	public JewelryTab(Object owner) {
		super(owner, JEWELRY_TAB_TITLE);
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
		JewelryTreasureRecord record;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
