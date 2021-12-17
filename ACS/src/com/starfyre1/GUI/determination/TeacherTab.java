/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	TEACHERS_DESCRIPTION	= "The payment of Teachers will be up to the Game Masters running that particular campaign.\r\n"													// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "Remember that these people will not be very cheap, they are usually very special people whose time is very precious.\r\n"															// //$NON-NLS-1$
					+ "\r\n"																																												// //$NON-NLS-1$
					+ "But these are up to you, if a character starts selling there own time to pupils for weapons training, don't let it worry you unless it starts to get in the way of your campaign.";	//$NON-NLS-1$

	static final String			TEACHER_TAB_TITLE		= "Teachers";																																		//$NON-NLS-1$
	static final String			TEACHER_TAB_TOOLTIP		= "Teachers";																																		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/**
	 * Creates a new {@link TeacherTab}.
	 *
	 * @param owner
	 * @param title
	 */
	public TeacherTab(Object owner) {
		super(owner, TEACHER_TAB_TITLE);
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
		return createPage(null, TEACHERS_DESCRIPTION, "", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
