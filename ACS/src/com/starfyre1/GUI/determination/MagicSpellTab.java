/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagicSpellTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	MAGIC_SPELL_DESCRIPTION	= "A power 3 spell would cost 96 D.P.'s ((3+1) Squared, times 6).\r\n"					// //$NON-NLS-1$
					+ "\r\n"																													// //$NON-NLS-1$
					+ "For most Mages there is also a monitory investment.\r\n"																	// //$NON-NLS-1$
					+ "\r\n"																													// //$NON-NLS-1$
					+ "The success rate is outlined in the Research Section of the Magic System.";												//$NON-NLS-1$

	static final String			MAGIC_SPELL_TAB_TITLE	= "Magic Spells";																		//$NON-NLS-1$
	static final String			MAGIC_SPELL_TAB_TOOLTIP	= "Magic";																				//$NON-NLS-1$
	private static final String	MAGIC_SPELL_COST		= "Cost: 6 X (spell power + 1/squared)";												//$NON-NLS-1$
	private static final String	MAGIC_SPELL_COST2		= "";																					//$NON-NLS-1$
	private static final String	MAGIC_SPELL_TITLE		= "To research a new magical spell:";													//$NON-NLS-1$
	private static final String	MAGIC_SPELL_TITLE2		= "Success: TBD";																		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link MagicSpellTab}.
	 *
	 * @param owner
	 */
	public MagicSpellTab(Object owner) {
		super(owner, MAGIC_SPELL_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	}

	@Override
	protected void loadDisplay() {
		//DW to do
	}

	@Override
	protected Component createDisplay() {
		return createPage(null, MAGIC_SPELL_DESCRIPTION, MAGIC_SPELL_TITLE, MAGIC_SPELL_TITLE2, MAGIC_SPELL_COST, MAGIC_SPELL_COST2);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
