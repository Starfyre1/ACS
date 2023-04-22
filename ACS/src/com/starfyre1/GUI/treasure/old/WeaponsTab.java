/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import com.starfyre1.dataModel.treasure.WeaponsTreasureRecord;

import java.awt.Component;

import javax.swing.JPanel;

public class WeaponsTab extends TreasureTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String WEAPON_TAB_TITLE = "Weapons"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WeaponsTab}.
	 *
	 * @param owner
	 */
	public WeaponsTab(Object owner) {
		super(owner, WEAPON_TAB_TITLE);
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
		WeaponsTreasureRecord record;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
