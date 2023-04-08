/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class LogFileDialog extends JDialog {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public LogFileDialog(JFrame parent) {
		super(parent, true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);

		JTextArea mMessageLabel = new JTextArea(20, 20);
		mMessageLabel.setEditable(false);
		mMessageLabel.setFocusable(false);
		mMessageLabel.setOpaque(false);
		mMessageLabel.setText(ACS.TITLE + "\n\n" + ACS.getVersion() + "\n" + ACS.getBuildDate() + "\n" + ACS.COPYRIGHT); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton closeButton = new TKButtonRollover(this, "Close", true); //$NON-NLS-1$
		closeButton.setFocusable(false);

		JButton clearButton = new TKButtonRollover(this, "Clear", true); //$NON-NLS-1$
		clearButton.setFocusable(false);

		JPanel messagePanel = new JPanel();
		messagePanel.add(mMessageLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		buttonPanel.add(clearButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(closeButton);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(new TKPageTitleLabel(CharacterSheet.LOG), BorderLayout.PAGE_START);
		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
