/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.MoneyRecord;
import com.starfyre1.startup.ACS;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class MoneyDisplay extends TKTitledDisplay implements DocumentListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	MONEY_TITLE		= "Money";		//$NON-NLS-1$

	private static final String	GOLD_LABEL		= "Gold";		//$NON-NLS-1$
	private static final String	SILVER_LABEL	= "Silver";		//$NON-NLS-1$
	private static final String	COPPER_LABEL	= "Copper";		//$NON-NLS-1$
	private static final String	GEMS_LABEL		= "Gems";		//$NON-NLS-1$
	private static final String	JEWELRY_LABEL	= "Jewelry";	//$NON-NLS-1$
	private static final String	OTHER_LABEL		= "Other";		//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	JTextField					mGoldField;
	JTextField					mSilverField;
	JTextField					mCopperField;
	JTextArea					mGemsArea;
	JTextArea					mJewelryArea;
	JTextArea					mOtherArea;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public MoneyDisplay(CharacterSheet owner) {
		super(owner, MONEY_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setBorder(new EmptyBorder(0, 5, 5, 10));
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		TKIntegerFilter filter = TKIntegerFilter.getFilterInstance();

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel goldLabel = new JLabel(GOLD_LABEL, SwingConstants.RIGHT);
		goldLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		mGoldField = TKComponentHelpers.createTextField(4, 20, this, filter);

		JLabel silverLabel = new JLabel(SILVER_LABEL, SwingConstants.RIGHT);
		silverLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
		mSilverField = TKComponentHelpers.createTextField(4, 20, this, filter);

		JLabel copperLabel = new JLabel(COPPER_LABEL, SwingConstants.RIGHT);
		copperLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
		mCopperField = TKComponentHelpers.createTextField(4, 20, this, filter);

		JLabel gemsLabel = new JLabel(GEMS_LABEL, SwingConstants.RIGHT);
		mGemsArea = new JTextArea(3, 10);
		mGemsArea.getDocument().addDocumentListener(this);

		JScrollPane gemsScrollPane = new JScrollPane(mGemsArea);
		gemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gemsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gemsScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		gemsScrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		JLabel jewelryLabel = new JLabel(JEWELRY_LABEL, SwingConstants.RIGHT);
		mJewelryArea = new JTextArea(3, 10);
		mJewelryArea.getDocument().addDocumentListener(this);

		JScrollPane jewelryScrollPane = new JScrollPane(mJewelryArea);
		jewelryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jewelryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jewelryScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		jewelryScrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		JLabel otherLabel = new JLabel(OTHER_LABEL, SwingConstants.RIGHT);
		mOtherArea = new JTextArea(3, 10);
		mOtherArea.getDocument().addDocumentListener(this);

		JScrollPane otherScrollPane = new JScrollPane(mOtherArea);
		otherScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		otherScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		otherScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		otherScrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		wrapper.add(goldLabel);
		wrapper.add(mGoldField);
		wrapper.add(silverLabel);
		wrapper.add(mSilverField);
		wrapper.add(copperLabel);
		wrapper.add(mCopperField);

		JPanel wrapper2 = new JPanel();
		wrapper2.setLayout(new BoxLayout(wrapper2, BoxLayout.Y_AXIS));
		wrapper2.setAlignmentX(Component.LEFT_ALIGNMENT);

		wrapper2.add(gemsLabel);
		wrapper2.add(gemsScrollPane);
		wrapper2.add(jewelryLabel);
		wrapper2.add(jewelryScrollPane);
		wrapper2.add(otherLabel);
		wrapper2.add(otherScrollPane);

		outerWrapper.add(wrapper);
		outerWrapper.add(wrapper2);

		return outerWrapper;

	}

	@Override
	public void loadDisplay() {
		MoneyRecord record = ((CharacterSheet) getOwner()).getMoneyRecord();

		mGoldField.setText(record == null ? null : String.valueOf(record.getGold()));
		mSilverField.setText(record == null ? null : String.valueOf(record.getSilver()));
		mCopperField.setText(record == null ? null : String.valueOf(record.getCopper()));
		mGemsArea.setText(record == null ? null : record.getGemsArea());
		mJewelryArea.setText(record == null ? null : record.getJewelryArea());
		mOtherArea.setText(record == null ? null : record.getOtherArea());
		mOtherArea.setCaretPosition(0);
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
		Document document = e.getDocument();
		Object source = document.getProperty(TKComponentHelpers.DOCUMENT_OWNER);

		MoneyRecord record = ((CharacterSheet) getOwner()).getMoneyRecord();
		if (record == null) {
			return;
		}

		if (source instanceof JTextField) {
			if (((JTextField) source).equals(mGoldField)) {
				record.setGold(TKStringHelpers.getIntValue(mGoldField.getText(), record.getGoldOld()));
			} else if (((JTextField) source).equals(mSilverField)) {
				record.setSilver(TKStringHelpers.getIntValue(mSilverField.getText(), record.getSilverOld()));
			} else if (((JTextField) source).equals(mCopperField)) {
				record.setCopper(TKStringHelpers.getIntValue(mCopperField.getText(), record.getCopperOld()));
			}
			ACS.getInstance().getCharacterSheet().updateForEncubrance();
			ACS.getInstance().getCharacterSheet().getDefenseInformationDisplay().loadDisplay();
		} else {
			if (document.equals(mGemsArea.getDocument())) {
				record.setGemsArea(mGemsArea.getText());
			} else if (document.equals(mJewelryArea.getDocument())) {
				record.setJewelryArea(mJewelryArea.getText());
			} else if (document.equals(mOtherArea.getDocument())) {
				record.setOtherArea(mOtherArea.getText());
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
