/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.treasure;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

public class TreasureDisplay2 extends TKTitledDisplay implements Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String			TREASURE_TITLE		= "Party Treasure";				//$NON-NLS-1$

	private static final String			TREASURE_RECORD_KEY	= "TREASURE_RECORD_KEY";		//$NON-NLS-1$
	private static final String			TOTAL_TITLE			= "Total:";						//$NON-NLS-1$
	private static final String			GOLD_TITLE			= "Gold:";						//$NON-NLS-1$
	private static final String			SILVER_TITLE		= "Silver:";					//$NON-NLS-1$
	private static final String			COPPER_TITLE		= "Copper:";					//$NON-NLS-1$

	private static final String			TOTAL_TITLE2		= "Total";						//$NON-NLS-1$
	private static final String			COUNT_TITLE			= "Count";						//$NON-NLS-1$
	private static final String			VALUE_TITLE			= "Value";						//$NON-NLS-1$
	private static final String			DESCRIPTION_TITLE	= "Description";				//$NON-NLS-1$

	private static final String			NEW_ENTRY_TITLE		= "New Entry:";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel						mTotalLabel;
	private JTextField					mGoldField;
	private JTextField					mSilverField;
	private JTextField					mCopperField;

	private int							mTotal;
	private int							mTotalGold;
	private int							mTotalSilver;
	private int							mTotalCopper;

	private JButton						mNewEntryButton;

	private ArrayList<TreasureRecord>	mEntries			= new ArrayList<>();
	private JPanel						mPanel;

	private String						mCountText			= TKStringHelpers.EMPTY_STRING;
	private String						mValueText			= TKStringHelpers.EMPTY_STRING;
	private String						mDescriptionText	= TKStringHelpers.EMPTY_STRING;
	private String						mTotalValue			= TKStringHelpers.EMPTY_STRING;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TreasureDisplay2(CharacterSheet owner) {
		super(owner, TREASURE_TITLE);

		//		updateValues();
		addRecordsToDisplay(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		outerWrapper.add(generateValuePanel());
		outerWrapper.add(getTreasureDisplay());

		return outerWrapper;
	}

	private JPanel generateValuePanel() {
		TKIntegerFilter intFilter = TKIntegerFilter.getFilterInstance();

		JPanel valuePanel = new JPanel();
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.X_AXIS));

		JPanel innerWrapper = new JPanel();
		mTotalLabel = TKComponentHelpers.createLabel(String.valueOf(mTotal));
		JLabel totalTitle = new JLabel(TOTAL_TITLE);
		innerWrapper.add(totalTitle);
		innerWrapper.add(mTotalLabel);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mGoldField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mGoldField.getDocument()).setDocumentFilter(intFilter);
		mGoldField.setText(String.valueOf(mTotalGold));
		JLabel goldTitle = new JLabel(GOLD_TITLE);
		innerWrapper.add(goldTitle);
		innerWrapper.add(mGoldField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mSilverField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mSilverField.getDocument()).setDocumentFilter(intFilter);
		mSilverField.setText(String.valueOf(mTotalSilver));
		JLabel silverTitle = new JLabel(SILVER_TITLE);
		innerWrapper.add(silverTitle);
		innerWrapper.add(mSilverField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mCopperField = TKComponentHelpers.createTextField(10, 20);
		((AbstractDocument) mCopperField.getDocument()).setDocumentFilter(intFilter);
		mCopperField.setText(String.valueOf(mTotalCopper));
		JLabel copperTitle = new JLabel(COPPER_TITLE);
		innerWrapper.add(copperTitle);
		innerWrapper.add(mCopperField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		JLabel newEntryTitle = new JLabel(NEW_ENTRY_TITLE);
		mNewEntryButton = new TKButtonRollover(this, ACS.IMAGE_PLUS_ICON, false);
		mNewEntryButton.setOpaque(true);
		mNewEntryButton.setPreferredSize(new Dimension(25, 25));
		mNewEntryButton.setFocusable(false);
		mNewEntryButton.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				addRecord();

				// DW get active table and add record
			}
		});
		innerWrapper.add(newEntryTitle);
		innerWrapper.add(mNewEntryButton);
		valuePanel.add(innerWrapper);
		valuePanel.setBorder(new LineBorder(Color.blue));

		return valuePanel;
	}

	private Component getTreasureDisplay() {

		JPanel outerWrapper = new JPanel(new BorderLayout());

		JTextField countField = new JTextField(COUNT_TITLE, 5);
		countField.setMaximumSize(new Dimension(100, 20));
		countField.setEditable(false);
		countField.setFocusable(false);
		countField.setBorder(new EmptyBorder(countField.getInsets()));
		JTextField valueField = new JTextField(VALUE_TITLE, 5);
		valueField.setMaximumSize(new Dimension(150, 20));
		valueField.setEditable(false);
		valueField.setFocusable(false);
		valueField.setBorder(new EmptyBorder(valueField.getInsets()));
		JTextField descriptionField = new JTextField(DESCRIPTION_TITLE, 20);
		descriptionField.setEditable(false);
		descriptionField.setFocusable(false);
		descriptionField.setBorder(new EmptyBorder(descriptionField.getInsets()));
		JTextField totalField = new JTextField(TOTAL_TITLE2, 5);
		totalField.setMaximumSize(new Dimension(200, 20));
		totalField.setEditable(false);
		totalField.setFocusable(false);
		totalField.setBorder(new EmptyBorder(totalField.getInsets()));

		//		JPanel wrapper = new JPanel(new GridLayout(1, 4));
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));

		wrapper.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 5, 0, 5)));

		wrapper.add(countField);
		wrapper.add(valueField);
		wrapper.add(descriptionField);
		wrapper.add(Box.createHorizontalGlue());
		wrapper.add(totalField);

		mPanel = new JPanel();
		BoxLayout bl2 = new BoxLayout(mPanel, BoxLayout.Y_AXIS);
		mPanel.setLayout(bl2);

		JScrollPane scrollPane = new JScrollPane(mPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);

		outerWrapper.add(wrapper, BorderLayout.NORTH);
		outerWrapper.add(scrollPane, BorderLayout.CENTER);

		return outerWrapper;
	}

	//	private void createTreasureRecord() {
	//		TreasureRecord record = new TreasureRecord(this);
	//		record.displayTreasureRecord(false);
	//
	//		if (!(record.isRecordCancelled() || record.isRecordDeleted())) {
	//			record.setHeaderText();
	//			mEntries.add(0, record);
	//			updateValues();
	//		}
	//	}

	@Override
	protected void loadDisplay() {
		updateValues();
		addRecordsToDisplay(false);
	}

	public void clearRecords() {
		mEntries = new ArrayList<>();
		updateValues();

	}

	public void addRecordsToDisplay(boolean clear) {
		if (clear) {
			clearRecords();
		}

		if (mEntries.isEmpty()) {
			addEntryRecord(generateDisplayRecord(null));
		} else {
			for (TreasureRecord record : mEntries) {
				addRecord(generateDisplayRecord(record));
			}
		}
		updateValues();
	}

	private JPanel generateDisplayRecord(TreasureRecord record) {
		JTextField countField = new FocusTextField(COUNT_TITLE, 5);
		countField.setMaximumSize(new Dimension(100, 20));
		JTextField valueField = new FocusTextField(VALUE_TITLE, 5);
		valueField.setMaximumSize(new Dimension(150, 20));
		JTextField descriptionField = new FocusTextField(DESCRIPTION_TITLE, 20);
		JTextField totalField = new FocusTextField(TOTAL_TITLE2, 5);
		totalField.setMaximumSize(new Dimension(200, 20));

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.putClientProperty(TREASURE_RECORD_KEY, record);
		wrapper.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 5, 0, 5)));

		wrapper.add(countField);
		wrapper.add(valueField);
		wrapper.add(descriptionField);
		wrapper.add(totalField);
		if (record != null) {
			countField.setText(String.valueOf(record.getCount()));
			valueField.setText(String.valueOf(record.getValue()));
			descriptionField.setText(record.getItem());
			totalField.setText(String.valueOf(record.getTotalValue()));
		}

		wrapper.setMaximumSize(new Dimension(wrapper.getMaximumSize().width, wrapper.getMinimumSize().height));

		return wrapper;
	}

	// adds display record
	private void addRecord(JPanel panel) {
		mPanel.add(panel);
	}

	// adds display record
	private void addEntryRecord(JPanel panel) {
		mPanel.add(panel);
		mPanel.revalidate();
		mPanel.repaint();
	}

	public void updateValues() {

		int totalValue = 0;

		for (TreasureRecord record : mEntries) {
			if (record != null) {
				totalValue += record.getTotalValue();
			}
		}
		mTotal = totalValue;
	}

	@Override
	public StringTokenizer readValues(BufferedReader br) {
		return null;
	}

	public void processRecord(FocusTextField field) {
		int cnt = mPanel.getComponentCount();

		for (int i = 0; i < cnt; i++) {
			JPanel comp = (JPanel) mPanel.getComponent(i);
			if (comp != null) {
				int cnt2 = comp.getComponentCount();
				for (int j = 0; j < cnt2; j++) {
					FocusTextField fieldOther = (FocusTextField) comp.getComponent(j);
					if (fieldOther == field) {
						TreasureRecord record = (TreasureRecord) comp.getClientProperty(TREASURE_RECORD_KEY);
						if (record == null) {
							int count = TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(0)).getText(), 0);
							int value = TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(1)).getText(), 0);
							String description = ((FocusTextField) comp.getComponent(2)).getText();
							if (count != 0 && value != 0 && !description.isBlank() && !description.equals(DESCRIPTION_TITLE)) {
								record = new TreasureRecord(this, count, value, description);
								comp.putClientProperty(TREASURE_RECORD_KEY, record);
								addEntryRecord(generateDisplayRecord(null));
								break;
							}
						} else {
							record.setCount(TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(0)).getText(), 0));
							record.setValue(TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(1)).getText(), 0));
							record.setItem(((FocusTextField) comp.getComponent(2)).getText());
							record.setTotalValue(TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(3)).getText(), 0));
							break;
						}
					}
				}
			}
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
	}

	@Override
	public void setKeyValuePair(String key, Object value) {
	}

	class FocusTextField extends JTextField {
		/**
		 * Creates a new {@link FocusTextField}.
		 *
		 * @param countTitle
		 * @param columns
		 */
		public FocusTextField(String countTitle, int columns) {
			super(countTitle, columns);
			addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					select(0, 0);
					setEditable(false);
					processRecord((TreasureDisplay2.FocusTextField) e.getSource());
				}

				@Override
				public void focusGained(FocusEvent e) {
					setEditable(true);
					select(0, getText().length());
				}
			});
		}
	}
}
