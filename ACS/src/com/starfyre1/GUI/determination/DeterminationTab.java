/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class DeterminationTab extends TKTitledDisplay implements DocumentListener, ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	protected static final int	TEXT_FIELD_HEIGHT	= 20;
	private static final String	GIVE_UP				= "Give Up";	//$NON-NLS-1$
	private static final String	LEARN				= "Learn";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel				mSuccessLabel;
	protected JButton			mLearnButton;
	protected JButton			mGiveUpButton;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link DeterminationTab}.
	 *
	 * @param owner
	 * @param title
	 */
	public DeterminationTab(Object owner, String title) {
		super(owner, ""); //$NON-NLS-1$
	}

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
		mSuccessLabel = new JLabel(successText);
		mSuccessLabel.setToolTipText(successTooltip);
		titleWrapper.add(titleLabel1);
		titleWrapper.add(mSuccessLabel);

		JPanel costWrapper = new JPanel();
		BoxLayout bl2 = new BoxLayout(costWrapper, BoxLayout.Y_AXIS);
		costWrapper.setLayout(bl2);

		JLabel costLabel1 = new JLabel(cost);
		JLabel costLabel2 = new JLabel(maint);
		costWrapper.add(costLabel1);
		costWrapper.add(costLabel2);

		JTextArea descriptionTextArea = new JTextArea();
		CompoundBorder cb = new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 15, 5, 15), new LineBorder(Color.BLACK)), new EmptyBorder(5, 5, 5, 5));
		descriptionTextArea.setBorder(cb);
		descriptionTextArea.getInsets(new Insets(5, 5, 5, 5));
		descriptionTextArea.setBackground(CharacterSheet.LABEL_BACKGROUND);
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setText(description);

		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);
		top.add(descriptionTextArea, BorderLayout.NORTH);

		page.add(top, BorderLayout.NORTH);
		if (lowerPanel != null) {
			page.add(lowerPanel, BorderLayout.CENTER);
		}

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		mLearnButton = TKComponentHelpers.createButton(LEARN, this, false);
		mGiveUpButton = TKComponentHelpers.createButton(GIVE_UP, this, false);

		buttonPanel.add(mGiveUpButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mLearnButton);

		page.add(buttonPanel, BorderLayout.SOUTH);
		return page;

	}

	@Override
	protected void loadDisplay() {
		setSuccessText(getSuccessText());
		updateDialogButtons();
	}

	protected JPanel getPanel(int compLayout, AbstractBorder border) {
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, compLayout);
		panel.setLayout(bl);
		panel.setBorder(border);
		panel.setAlignmentY(TOP_ALIGNMENT);

		return panel;
	}

	protected abstract String getSuccessText();

	protected void setSuccessText(String text) {
		mSuccessLabel.setText(text);
		mSuccessLabel.revalidate();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateDialogButtons();
	}

	void updateButtons(boolean hasSelection, boolean hasPointsInvested) {
		mLearnButton.setEnabled(hasSelection && !hasPointsInvested);
		mGiveUpButton.setEnabled(hasSelection && hasPointsInvested);
	}

	protected void updateDialogButtons() {
		updateButtons(hasValidEntriesToLearn(), false);
	}

	protected abstract boolean hasValidEntriesToLearn();

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
