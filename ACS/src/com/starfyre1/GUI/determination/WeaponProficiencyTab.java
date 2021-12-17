/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WeaponProficiencyTab extends DeterminationTab implements ActionListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	WEAPON_PROFICIENCY_DESCRIPTION	= "The character must first find a teacher with a higher Hit Bonus than the their own.\r\n"					// //$NON-NLS-1$
					+ "\r\n"																																				// //$NON-NLS-1$
					+ "The character gains 1/4 the difference between the teachers Hit Bonus and their own.\r\n"															// //$NON-NLS-1$
					+ "\r\n"																																				// //$NON-NLS-1$
					+ "The bonus will apply only when the character is using that particular weapon.";																		//$NON-NLS-1$

	static final String			WEAPON_PROFICIENCY_TAB_TITLE	= "Weapon Proficiencies";																					//$NON-NLS-1$
	static final String			WEAPON_PROFICIENCY_TAB_TOOLTIP	= "Weapon";																									//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_COST			= "Cost: 40";																								//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_COST2		= "";																										//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TITLE		= "To learn a new weapon proficiency:";																		//$NON-NLS-1$
	private static final String	WEAPON_PROFICIENCY_TITLE2		= "Success: 1D20 < Dexerity";																				//$NON-NLS-1$

	private static final int	ROWS							= 5;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link WeaponProficiencyTab}.
	 *
	 * @param owner
	 */
	public WeaponProficiencyTab(Object owner) {
		super(owner, WEAPON_PROFICIENCY_TAB_TITLE);
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
		return createPage(null, WEAPON_PROFICIENCY_DESCRIPTION, WEAPON_PROFICIENCY_TITLE, WEAPON_PROFICIENCY_TITLE2, WEAPON_PROFICIENCY_COST, WEAPON_PROFICIENCY_COST2);
	}

	private JPanel createWeaponsPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel wrapper = new JPanel();
		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
		wrapper.setLayout(bl);

		JTextField[] field = new JTextField[ROWS];
		JTextField[] bonus = new JTextField[ROWS];

		for (int i = 0; i < ROWS; i++) {
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
			field[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
			bonus[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
			inner.add(field[i]);
			inner.add(new JLabel("+")); //$NON-NLS-1$
			inner.add(bonus[i]);

			wrapper.add(inner);
		}

		panel.add(new JLabel(WEAPON_PROFICIENCY_TAB_TITLE, SwingConstants.CENTER), BorderLayout.PAGE_START);
		panel.add(wrapper, BorderLayout.CENTER);

		return panel;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
