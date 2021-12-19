/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class DeterminationTab extends TKTitledDisplay {

	/**
	 * Creates a new {@link DeterminationTab}.
	 *
	 * @param owner
	 * @param title
	 */
	public DeterminationTab(Object owner, String title) {
		super(owner, title);
	}

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	protected static final int TEXT_FIELD_HEIGHT = 20;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public JComponent createPage(JPanel lowerPanel, String description, String text, String successText, String successTooltip, String cost, String maint) {
		JPanel page = new JPanel(new BorderLayout());

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titleWrapper = new JPanel();
		BoxLayout bl3 = new BoxLayout(titleWrapper, BoxLayout.Y_AXIS);
		titleWrapper.setLayout(bl3);
		JLabel titleLabel1 = new JLabel(text);
		JLabel titleLabel2 = new JLabel(successText);
		titleLabel2.setToolTipText(successTooltip);
		titleWrapper.add(titleLabel1);
		titleWrapper.add(titleLabel2);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel costLabel1 = new JLabel(cost);
		JLabel costLabel2 = new JLabel(maint);
		costWrapper.add(costLabel1);
		costWrapper.add(costLabel2);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);

		JTextArea descriptionTextArea = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		descriptionTextArea.setBorder(cb);
		descriptionTextArea.getInsets(new Insets(5, 5, 5, 5));
		descriptionTextArea.setBackground(CharacterSheet.LABEL_BACKGROUND);
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setText(description);

		page.add(top, BorderLayout.NORTH);
		if (lowerPanel != null) {
			page.add(lowerPanel, BorderLayout.CENTER);
		}
		page.add(descriptionTextArea, BorderLayout.SOUTH);

		return page;

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
