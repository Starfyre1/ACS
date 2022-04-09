/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.purchasedGear.armor;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.Component;

public abstract class ArmorDisplay extends TKTitledDisplay {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		ARMOR_TITLE				= "Armor";																																																																																																																								//$NON-NLS-1$

	protected static final String[]	COLUMN_HEADER_NAMES		= { "#", "Equipped", "Name", "Material", "Type", "AR", "lbs", "Absorb", "Bonus", "Missile", "Str", "Parry", "Break", "Cost" };																																																																																											//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$
	protected static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Count", "Equipped", "Name", "<html>Material:<br>Iron<br>Ang<br>Borang<br>Ardacer<br>Ethru<br>Ithilnur<br>Mithril<br>Laen<br>Eog<br>Tasarung</html>", "<html>Protection Type:<br>0=Head-Top<br>1=Head-Side<br>2=Head-Face<br>3=Neck<br>4=Torso<br>5=Arms<br>6=Hands<br>7=Legs<br>8=Feet/Calves<br>9=Shield</html>", "Protection Amount", "Encumbrance", "Absorption", "<html>50% Chance<br>Bonus to Absorption</html>", "Missile Absorption", "Strength Requirement", "Parry", "Break", "Cost" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	public ArmorDisplay(Object owner) {
		super(owner, ARMOR_TITLE);
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
