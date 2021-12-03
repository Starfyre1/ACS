/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.magicItems;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

public abstract class MagicItemsDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		MAGIC_ITEMS_TITLE		= "Magic Items";										//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "Count", "Equipped", "Name", "Charges", "Cost" };	// , "modify" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Charges", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MagicItemsDisplay(Object owner) {
		super(owner, MAGIC_ITEMS_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected abstract Component createDisplay();

	@Override
	public abstract void loadDisplay();

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
