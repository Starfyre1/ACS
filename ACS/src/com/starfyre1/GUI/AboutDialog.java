/* Copyright (C) Starfyre Enterprises 2020. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class AboutDialog extends JDialog {
	/*****************************************************************************
	 * CONSTANTS
	 ****************************************************************************/

	/*****************************************************************************
	 * MEMBER VARIABLES
	 ****************************************************************************/

	/*****************************************************************************
	 * CONSTRUCTORS
	 ****************************************************************************/

	public AboutDialog(JFrame parent) {
		super(parent, true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);

		JTextArea mMessageLabel = new JTextArea(20, 20);
		mMessageLabel.setEditable(false);
		mMessageLabel.setFocusable(false);
		mMessageLabel.setOpaque(false);
		mMessageLabel.setText(ACS.TITLE + "\n\n" + ACS.getVersion() + "\n" + ACS.getBuildDate() + "\n" + ACS.COPYRIGHT); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton okButton = new TKButtonRollover(this, "OK", true); //$NON-NLS-1$
		okButton.setFocusable(false);

		JPanel messagePanel = new JPanel();
		messagePanel.add(mMessageLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(new TKPageTitleLabel(CharacterSheet.ABOUT), BorderLayout.PAGE_START);
		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	/*****************************************************************************
	 * METHODS
	 ****************************************************************************/

	/*****************************************************************************
	 * INHERITED ABSTRACT METHODS
	 ****************************************************************************/

	/*****************************************************************************
	 * ACCESSORS
	 ****************************************************************************/

	/*****************************************************************************
	 * SERIALIZATION
	 ****************************************************************************/
}
