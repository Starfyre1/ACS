/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SKILLS_DESCRIPTION	= "The character must find a teacher with the skill they want at the Highest Skill level they can find.\r\n"					// //$NON-NLS-1$
					+ "\r\n"																																						// //$NON-NLS-1$
					+ "To further increase the skill it will require further training and D.P. assignment";																			//$NON-NLS-1$

	static final String			SKILL_TAB_TITLE		= "Skills";																														//$NON-NLS-1$
	static final String			SKILL_TAB_TOOLTIP	= "Skills";																														//$NON-NLS-1$
	private static final String	SKILL_COST			= "Cost: 40-60";																												//$NON-NLS-1$
	private static final String	SKILL_COST2			= "Maintain: 1 DP / week";																										//$NON-NLS-1$
	private static final String	SKILL_TITLE			= "To learn a skill:";																											//$NON-NLS-1$
	private static final String	SKILL_TITLE2		= "Success: 1D20 < Intelligence";																								//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link SkillTab}.
	 *
	 * @param owner
	 * @param title
	 */
	public SkillTab(Object owner) {
		super(owner, SKILL_TAB_TITLE);
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
		return createPage(null, SKILLS_DESCRIPTION, SKILL_TITLE, SKILL_TITLE2, SKILL_COST, SKILL_COST2);
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
