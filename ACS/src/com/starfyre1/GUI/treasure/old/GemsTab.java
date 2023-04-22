/* Copyright (C) Starfyre Enterprises 2023. All rights reserved. */

package com.starfyre1.GUI.treasure.old;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.dataModel.treasure.GemsTreasureRecord;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GemsTab extends TreasureTab {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	GEMS_TAB_TITLE	= "Gems";	//$NON-NLS-1$

	private static final String	SAVE			= "Save";	//$NON-NLS-1$
	private static final String	CANCEL			= "Cancel";	//$NON-NLS-1$

	private static final String	COUNT			= "#";
	private static final String	VALUE			= "Value";
	private static final String	MAGIC			= "Magic";

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton				mOkButton;
	private JButton				mCancelButton;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link GemsTab}.
	 *
	 * @param owner
	 */
	public GemsTab(Object owner) {
		super(owner, GEMS_TAB_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		return new JPanel();
	}

	@Override
	int getValueTabTotal() {
		return 0;
	}

	@Override
	protected void addRecord() {
		GemsTreasureRecord record;
		JDialog dialog = new JDialog(ACS.getInstance().getCharacterSheet().getFrame(), GEMS_TAB_TITLE, true);
		dialog.setSize(800, 400);
		dialog.add(createDialogPanel());
		dialog.setVisible(true);

	}

	private JPanel createDialogPanel() {
		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setBorder(new EmptyBorder(5, 15, 5, 5));
		buttonWrapper.setLayout(new BorderLayout());

		JPanel outerWrapper = getPanel(BoxLayout.X_AXIS, new EmptyBorder(5, 15, 5, 5));

		JLabel countLabel = new JLabel(COUNT);
		JLabel valueLabel = new JLabel(VALUE);
		JLabel magicLabel = new JLabel(MAGIC);
		// DW ___Work on next
		JTextField countField = TKComponentHelpers.createTextField(5, 20, this, intFilter);
		JTextField valueField = TKComponentHelpers.createTextField(10, 20, this, floatFilter);
		JTextField magicField = TKComponentHelpers.createTextField(50, 20, this);

		outerWrapper.add(countLabel);
		outerWrapper.add(valueLabel);
		outerWrapper.add(magicLabel);

		outerWrapper.add(countField);
		outerWrapper.add(valueField);
		outerWrapper.add(magicField);

		buttonWrapper.add(outerWrapper, BorderLayout.NORTH);
		buttonWrapper.add(createButtonPanel(), BorderLayout.SOUTH);

		return buttonWrapper;

	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		mOkButton = TKComponentHelpers.createButton(SAVE, this);
		mCancelButton = TKComponentHelpers.createButton(CANCEL, this);

		panel.add(mOkButton);
		panel.add(mCancelButton);

		return panel;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
