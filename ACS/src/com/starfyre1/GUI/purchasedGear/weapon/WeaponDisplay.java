/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.equipment.weapon;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

public abstract class WeaponDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		WEAPON_TITLE			= "Weapons";																																																	//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "#", "Equipped", "Name", "Material", "Type", "Handed", "STR", "DEX", "Weight", "Length", "Asp", "Break", "H.B.", "1HD", "2HD", "Cost" };																	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "Material", "Type", "Handed", "Strength", "Dexterity", "Encumbrance", "Weapon Length", "Attack Speed", "Break", "Hit Bonus", "1 Handed Damage", "2 Handed Damage", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public WeaponDisplay(Object owner) {
		super(owner, WEAPON_TITLE);
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
