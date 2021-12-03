/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.animal;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

public abstract class AnimalsDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		ANIMALS_TITLE			= "Animals";																									//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "Count", "Animal", "Carry", "Move", "Travel", "Hits", "Hit Bonus", "Damage", "Armor", "Cost", "Notes" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Animal", "Carry", "Move", "Travel", "Hits", "Hit Bonus", "Damage", "Armor", "Cost", "Notes" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public AnimalsDisplay(Object owner) {
		super(owner, ANIMALS_TITLE);
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
