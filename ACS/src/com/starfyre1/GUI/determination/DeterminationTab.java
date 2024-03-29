/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.startup.ACS;

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
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public abstract class DeterminationTab extends TKTitledDisplay implements DocumentListener, ActionListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	protected static final int	TEXT_FIELD_HEIGHT	= 20;
	protected static final int	POPUP_HEIGHT		= 24;
	private static final String	GIVE_UP				= "Give Up";	//$NON-NLS-1$
	// DW not implemented yet - Determination - verify not going to be used anymore
	//	private static final String	OK					= "OK";			//$NON-NLS-1$
	private static final String	NEW					= "New";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel				mSuccessLabel;
	protected JButton			mNewButton;
	protected JButton			mOKButton;
	protected JButton			mGiveUpButton;
	private boolean				mDPPerWeekError		= false;

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
	/**
	 * @param lowerPanel Use null if it is not used
	 * @param lowerPanel2 Use null if it is not used
	 * @param description
	 * @param text
	 * @param successText
	 * @param successTooltip
	 * @param cost
	 * @param maint
	 * @return The created page
	 */
	public JComponent createPage(JPanel lowerPanel, JPanel lowerPanel2, String description, String text, String successText, String successTooltip, String cost, String maint) {
		JPanel page = new JPanel(new BorderLayout());

		page.add(createTopPanel(description, text, successText, successTooltip, cost, maint), BorderLayout.NORTH);
		page.add(createLowerPanel(lowerPanel, lowerPanel2), BorderLayout.CENTER);
		page.add(getButtonPanel(), BorderLayout.SOUTH);

		return page;

	}

	private JPanel createTopPanel(String description, String text, String successText, String successTooltip, String cost, String maint) {
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

		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(new EmptyBorder(5, 5, 5, 5));
		top.add(titleWrapper, BorderLayout.WEST);
		top.add(costWrapper, BorderLayout.EAST);
		top.add(descriptionTextArea, BorderLayout.NORTH);

		return top;
	}

	private JPanel createLowerPanel(JPanel lowerPanel, JPanel lowerPanel2) {
		JPanel tempPage = new JPanel();
		BoxLayout bl = new BoxLayout(tempPage, BoxLayout.Y_AXIS);
		tempPage.setLayout(bl);
		if (lowerPanel != null) {
			lowerPanel.setBorder(new LineBorder(Color.BLACK));
			tempPage.add(lowerPanel, BorderLayout.CENTER);
		}
		if (lowerPanel2 != null) {
			lowerPanel2.setBorder(new LineBorder(Color.BLACK));
			tempPage.add(lowerPanel2, BorderLayout.CENTER);
		}
		return tempPage;
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(TKComponentHelpers.BORDER_INSETS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		mNewButton = TKComponentHelpers.createButton(NEW, this, false);
		mGiveUpButton = TKComponentHelpers.createButton(GIVE_UP, this, false);

		buttonPanel.add(mGiveUpButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(mNewButton);
		return buttonPanel;
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
	public void changedUpdate(DocumentEvent e) {
		// no need
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (validateDPPerWeekInput(e)) {
			DPPerWeekError(false);
			updateDialogButtons();
		} else {
			DPPerWeekError(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (validateDPPerWeekInput(e)) {
			updateDialogButtons();
		}
	}

	private boolean validateDPPerWeekInput(DocumentEvent e) {
		String text = null;
		Document doc = e.getDocument();
		try {
			text = doc.getText(0, doc.getLength());
		} catch (BadLocationException exception) {
			exception.printStackTrace();
		}

		return getDPPerWeekTabTotal() + TKStringHelpers.getIntValue(text, 0) <= ACS.getInstance().getCharacterSheet().getDeterminationPointsDisplay().getDeterminationPoints();
	}

	private void DPPerWeekError(boolean isTrue) {
		// DW not implemented yet - Still need to use mDPPerWeekError;
		if (mDPPerWeekError != isTrue) {
			mDPPerWeekError = isTrue;
		}
	}

	private void updateButtons(boolean hasSelection, boolean hasPointsInvested) {
		//		mNewButton.setEnabled(hasSelection && !hasPointsInvested);
		mNewButton.setEnabled(true);
		mGiveUpButton.setEnabled(hasSelection && hasPointsInvested);
	}

	protected void updateDialogButtons() {
		updateButtons(hasValidEntriesToLearn(), false);
	}

	protected abstract boolean hasValidEntriesToLearn();

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public abstract int getDPPerWeekTabTotal();

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
