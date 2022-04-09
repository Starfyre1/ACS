/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.equipment;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

public abstract class EquipmentDisplay extends TKTitledDisplay {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		EQUIPMENT_TITLE			= "Equipment";														//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "#", "Equipped", "Name", "lbs", "Cost", "Notes" };				//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Encumbrance", "Cost", "Notes" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public EquipmentDisplay(Object owner) {
		super(owner, EQUIPMENT_TITLE);
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
