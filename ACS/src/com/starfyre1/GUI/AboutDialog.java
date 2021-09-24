/* Copyright (C) Starfyre Enterprises 2020. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	private static final String	ABOUT		= "About";	//$NON-NLS-1$
	/*****************************************************************************
	 * MEMBER VARIABLES
	 ****************************************************************************/
	private Color				mOldColor	= null;

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

		JButton mOKButton = new JButton("OK"); //$NON-NLS-1$
		mOKButton.setFocusable(false);
		mOKButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = mOKButton.getBackground();
				mOKButton.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					mOKButton.setBackground(mOldColor);
				}
			}
		});
		mOKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel messagePanel = new JPanel();
		messagePanel.add(mMessageLabel);
		//		messagePanel.setBackground(Color.WHITE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(mOKButton);
		//		buttonPanel.setBackground(Color.WHITE);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(new TKPageTitleLabel(ABOUT), BorderLayout.PAGE_START);
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
