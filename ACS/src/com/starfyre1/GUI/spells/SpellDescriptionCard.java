/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.spells;

import com.starfyre1.ToolKit.TKScrollablePanel;
import com.starfyre1.dataset.spells.SpellDescriptionList;
import com.starfyre1.dataset.spells.SpellDescriptionRecord;
import com.starfyre1.dataset.spells.SpellDescriptionRecord.Pair;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class SpellDescriptionCard extends JPanel {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private SpellDescriptionRecord	mRecord				= null;
	private JPanel					mPowersPanel		= null;
	private JTextArea				mDescriptionArea	= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SpellDescriptionCard(String name) {
		super();

		mDescriptionArea = new JTextArea();
		if (name != null) {
			mRecord = SpellDescriptionList.getRecord(name);
		}

		mDescriptionArea.setEditable(false);
		mDescriptionArea.setFocusable(false);
		mDescriptionArea.setOpaque(false);
		mDescriptionArea.setText(mRecord != null ? mRecord.getDescription() : "Spell description not available"); //$NON-NLS-1$
		mDescriptionArea.setLineWrap(true);
		mDescriptionArea.setWrapStyleWord(true);

		mPowersPanel = new JPanel(new GridBagLayout());
		updatePowersPanel(mPowersPanel);

		TKScrollablePanel wrapper = new TKScrollablePanel(16, 16, true, false);
		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
		wrapper.setLayout(bl);
		wrapper.add(mDescriptionArea);
		wrapper.add(mPowersPanel);

		JScrollPane scrollPane = new JScrollPane(wrapper);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void setDescriptionText(String name) {
		mRecord = null;
		if (name != null) {
			mRecord = SpellDescriptionList.getRecord(name);
		}
		updatePowersPanel(mPowersPanel);

		mDescriptionArea.setText(mRecord != null ? mRecord.getDescription() : "Spell description not available"); //$NON-NLS-1$
		mDescriptionArea.revalidate();
		mDescriptionArea.repaint();
	}

	private void updatePowersPanel(JPanel panel) {
		panel.setAlignmentY(TOP_ALIGNMENT);
		panel.removeAll();
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
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
