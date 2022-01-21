/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.spells;

import com.starfyre1.dataset.spells.SpellDescriptionList;
import com.starfyre1.dataset.spells.SpellDescriptionRecord;
import com.starfyre1.dataset.spells.SpellDescriptionRecord.Pair;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class SpellDescriptionCard2 extends JTextArea {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private SpellDescriptionRecord	mRecord			= null;
	private JPanel					mPowersPanel	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellDescriptionCard2(String name) {
		super(10, 25);
		if (name != null) {
			mRecord = SpellDescriptionList.getRecord(name);
		}

		setEditable(false);
		setFocusable(false);
		setOpaque(false);
		setText(mRecord != null ? mRecord.getDescription() : "Spell description not available"); //$NON-NLS-1$
		setLineWrap(true);
		setWrapStyleWord(true);

		add(updatePowersPanel());

		setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		setVisible(true);

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void setDescriptionText(String name) {
		if (name != null) {
			mRecord = SpellDescriptionList.getRecord(name);
			mPowersPanel = updatePowersPanel();
		}

		setText(mRecord != null ? mRecord.getDescription() : "Spell description not available"); //$NON-NLS-1$
		getParent().validate();
		getParent().repaint();
	}

	private JPanel updatePowersPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setAlignmentY(TOP_ALIGNMENT);
		if (mRecord != null) {
			ArrayList<Pair> effects = mRecord.getEffects();
			GridBagConstraints c = new GridBagConstraints();
			int row = 0;
			for (Pair pair : effects) {
				JLabel label1 = new JLabel(pair.getPairName() + ":    ", SwingConstants.RIGHT); //$NON-NLS-1$

				c.gridx = 0;
				c.gridy = row;
				c.insets = new Insets(10, 0, 0, 0);
				c.anchor = GridBagConstraints.WEST;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.5;
				panel.add(label1, c);

				JTextArea label2 = new JTextArea(pair.getPairDescription());
				label2.setEditable(false);
				label2.setWrapStyleWord(true);
				label2.setLineWrap(true);
				label2.setBackground(label1.getBackground());

				c.gridx = 1;
				c.gridy = row++;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.5;
				panel.add(label2, c);
			}
		}
		return panel;
	}
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
