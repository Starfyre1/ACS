/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class TreasureTab extends TKTitledDisplay implements DocumentListener, ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link TreasureTab}.
	 *
	 * @param owner
	 * @param title
	 */
	public TreasureTab(Object owner, String title) {
		super(owner, title);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	abstract int getValueTabTotal();

	abstract void addRecord();

	@Override
	protected void loadDisplay() {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// DW not implemented yet - old treasure display
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// DW not implemented yet - old treasure display
	}

	protected JPanel getPanel(int compLayout, AbstractBorder border) {
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, compLayout);
		panel.setLayout(bl);
		panel.setBorder(border);
		panel.setAlignmentY(TOP_ALIGNMENT);

		return panel;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
