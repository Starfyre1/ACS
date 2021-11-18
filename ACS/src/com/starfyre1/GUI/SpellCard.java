/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.dataset.spells.SpellDescriptionList;
import com.starfyre1.dataset.spells.SpellDescriptionRecord;
import com.starfyre1.dataset.spells.SpellDescriptionRecord.Pair;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class SpellCard extends JDialog {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		SPELL_CARD	= "Spell Card";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private SpellDescriptionRecord	mRecord;

	private Color					mOldColor	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellCard(JFrame parent, String name) {
		super(parent, SPELL_CARD, false);
		mRecord = SpellDescriptionList.getRecord(name);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JTextArea spellDescription = new JTextArea(20, 20);
		spellDescription.setEditable(false);
		spellDescription.setFocusable(false);
		spellDescription.setOpaque(false);
		spellDescription.setText(mRecord.getDescription());
		spellDescription.setLineWrap(true);
		spellDescription.setWrapStyleWord(true);

		JPanel messagePanel = new JPanel();
		BoxLayout bl = new BoxLayout(messagePanel, BoxLayout.Y_AXIS);
		messagePanel.setLayout(bl);

		messagePanel.add(spellDescription);
		messagePanel.add(Box.createVerticalStrut(10));

		ArrayList<Pair> effects = mRecord.getEffects();
		for (Pair pair : effects) {
			String pairName = pair.getPairName();
			String pairDescription = pair.getPairDescription();
			JPanel panel = new JPanel(new GridLayout(0, 2));
			JLabel label1 = new JLabel(pairName + ":    ", SwingConstants.RIGHT); //$NON-NLS-1$
			JLabel label2 = new JLabel(pairDescription);
			panel.add(label1);
			panel.add(label2);
			messagePanel.add(panel);
		}

		messagePanel.add(Box.createVerticalStrut(500));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getCloseButton());

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setPreferredSize(new Dimension(400, 500));
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(new TKPageTitleLabel(name), BorderLayout.NORTH);
		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);

	}

	private JButton getCloseButton() {
		JButton button = new JButton("Close"); //$NON-NLS-1$
		button.setFocusable(false);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = button.getBackground();
				button.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					button.setBackground(mOldColor);
				}
			}
		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		return button;
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
