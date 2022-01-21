/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPageTitleLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class HelpDialog extends JDialog {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String HELP = "Help"; //$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link HelpDialog}.
	 *
	 * @param owner
	 */
	public HelpDialog(Frame owner) {
		super(owner, false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//		setUndecorated(true);

		JTextPane mMessageLabel = new JTextPane();
		mMessageLabel.setContentType("text/html"); //$NON-NLS-1$
		mMessageLabel.setEditable(false);
		mMessageLabel.setFocusable(false);
		mMessageLabel.setOpaque(false);
		mMessageLabel.setText(getTutorial());

		JButton mOKButton = new JButton("OK"); //$NON-NLS-1$
		mOKButton.setFocusable(false);
		mOKButton.addMouseListener(new MouseAdapter() {
			private Color mOldColor = null;

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

		wrapper.add(new TKPageTitleLabel(HELP), BorderLayout.PAGE_START);
		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private String getTutorial() {
		StringBuilder text = new StringBuilder();

		text.append("<html> Start by either <b>File->New</b> or <b>File->Open</b> from the file menu.<br>"); //$NON-NLS-1$
		text.append("You will then be asked, 'is this a new game day?'. Answer yes to start a new calendar day.<br>"); //$NON-NLS-1$
		text.append("Once you have your <b>Attributes</b> you can then enter your character and player names.<br>"); //$NON-NLS-1$
		text.append("Then select your <b>Class</b>. Your attributes may limit some selections.<br><br>"); //$NON-NLS-1$
		text.append("If you are entering a character that already has equipment, you will want to give<br>"); //$NON-NLS-1$
		text.append("your character a bunch of gold before buying your equipment. Remember to reduce<br>"); //$NON-NLS-1$
		text.append("your gold to what you actually have after you get your equipment.<br>"); //$NON-NLS-1$
		text.append("To purchase your equipment go to <b>Options->Market Place</b>.<br>"); //$NON-NLS-1$
		text.append("Enter the number of items you want, select the checkbox to equip/carry them.<br>"); //$NON-NLS-1$
		text.append("In the case of <b>Weapons</b> or <b>Armor</b> you can also change the <b>Material</b>.<br>"); //$NON-NLS-1$
		text.append("Yes, I know that <b>Leather</b> is listed as <b>Iron</b>. Also <b>Magic Items</b> are not enabled yet.<br><br>"); //$NON-NLS-1$
		text.append("<b>Spells</b> first select the <b>Magic Area</b> and then click the green <b>Learn Spell</b> button.<br>"); //$NON-NLS-1$
		text.append("If you already know a spell it will be grey and unselectable.<br><br>"); //$NON-NLS-1$
		text.append("<b>Journal</b> .<br>"); //$NON-NLS-1$

		return text.toString();
	}
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
