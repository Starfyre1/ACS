/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.treasure;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKCalculator;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKIntegerFilter;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;
import com.starfyre1.startup.SystemInfo;
import com.starfyre1.storage.PreferenceStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

public class TreasureDisplay2 extends TKTitledDisplay implements Savable, KeyListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTION_START_KEY	= "PARTY_TREASURE_SECTION_START";	//$NON-NLS-1$
	public static final String			FILE_SECTION_END_KEY	= "PARTY_TREASURE_SECTION_END";		//$NON-NLS-1$

	private static final String			GOLD_KEY				= "GOLD_KEY";						//$NON-NLS-1$
	private static final String			SILVER_KEY				= "SILVER_KEY";						//$NON-NLS-1$
	private static final String			COPPER_KEY				= "COPPER_KEY";						//$NON-NLS-1$
	private static final String			COUNT_KEY				= "COUNT_KEY";						//$NON-NLS-1$
	private static final String			VALUE_KEY				= "VALUE_KEY";						//$NON-NLS-1$
	private static final String			DESCRIPTION_KEY			= "DESCRIPTION_KEY";				//$NON-NLS-1$

	private static final String			TREASURE_TITLE			= "Party Treasure";					//$NON-NLS-1$

	private static final String			PARTY_TREASURE_FILENAME	= "PartyTreasure.apt";				//$NON-NLS-1$
	private static final String			TREASURE_RECORD_KEY		= "TREASURE_RECORD_KEY";			//$NON-NLS-1$
	private static final String			TOTAL_TITLE				= "Total (SP):";					//$NON-NLS-1$
	private static final String			GOLD_TITLE				= "Gold:";							//$NON-NLS-1$
	private static final String			SILVER_TITLE			= "Silver:";						//$NON-NLS-1$
	private static final String			COPPER_TITLE			= "Copper:";						//$NON-NLS-1$

	private static final String			DELETE_ENTRY_TITLE		= "Delete Entry:";					//$NON-NLS-1$

	private static final String			TOTAL_TITLE2			= "Total";							//$NON-NLS-1$
	private static final String			COUNT_TITLE				= "Count";							//$NON-NLS-1$
	private static final String			VALUE_TITLE				= "Value";							//$NON-NLS-1$
	private static final String			DESCRIPTION_TITLE		= "Description";					//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel						mTotalLabel;
	private JTextField					mGoldField;
	private JTextField					mSilverField;
	private JTextField					mCopperField;

	private float						mTotal;
	private int							mTotalGold;
	private int							mTotalSilver;
	private int							mTotalCopper;

	private JButton						mDeleteEntryButton;

	private ArrayList<TreasureRecord>	mEntries				= new ArrayList<>();
	private JPanel						mPanel;
	private static Color				mOldColor;

	private String						mCountText				= TKStringHelpers.EMPTY_STRING;
	private String						mValueText				= TKStringHelpers.EMPTY_STRING;
	private String						mDescriptionText		= TKStringHelpers.EMPTY_STRING;

	TKIntegerFilter						mIntegerFilter;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public TreasureDisplay2(CharacterSheet owner) {
		super(owner, TREASURE_TITLE);
		addRecordsToDisplay(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		mIntegerFilter = TKIntegerFilter.getFilterInstance();

		outerWrapper.add(generateValuePanel());
		outerWrapper.add(getTreasureDisplay());

		return outerWrapper;
	}

	private JPanel generateValuePanel() {
		JPanel valuePanel = new JPanel();
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.X_AXIS));

		JPanel innerWrapper = new JPanel();
		mTotalLabel = TKComponentHelpers.createLabel(NumberFormat.getNumberInstance().format(mTotal));
		innerWrapper.add(new JLabel(TOTAL_TITLE));
		innerWrapper.add(mTotalLabel);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mGoldField = new FocusTextField(String.valueOf(mTotalGold), 10);
		((AbstractDocument) mGoldField.getDocument()).setDocumentFilter(mIntegerFilter);
		mGoldField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				mTotalGold = TKStringHelpers.getIntValue(mGoldField.getText(), 0);
				updateValues();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// nothing to do
			}
		});
		mGoldField.addKeyListener(this);
		innerWrapper.add(new JLabel(GOLD_TITLE));
		innerWrapper.add(mGoldField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mSilverField = new FocusTextField(String.valueOf(mTotalSilver), 10);
		((AbstractDocument) mSilverField.getDocument()).setDocumentFilter(mIntegerFilter);
		mSilverField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				mTotalSilver = TKStringHelpers.getIntValue(mSilverField.getText(), 0);
				updateValues();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// nothing to do
			}
		});
		mSilverField.addKeyListener(this);
		innerWrapper.add(new JLabel(SILVER_TITLE));
		innerWrapper.add(mSilverField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		mCopperField = new FocusTextField(String.valueOf(mTotalCopper), 10);
		((AbstractDocument) mCopperField.getDocument()).setDocumentFilter(mIntegerFilter);
		mCopperField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				mTotalCopper = TKStringHelpers.getIntValue(mCopperField.getText(), 0);
				updateValues();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// nothing to do
			}
		});
		mCopperField.addKeyListener(this);
		innerWrapper.add(new JLabel(COPPER_TITLE));
		innerWrapper.add(mCopperField);
		valuePanel.add(innerWrapper);

		innerWrapper = new JPanel();
		JLabel deleteEntryTitle = new JLabel(DELETE_ENTRY_TITLE);
		mDeleteEntryButton = new TKButtonRollover(this, ACS.IMAGE_MINUS_ICON, false);
		mDeleteEntryButton.setOpaque(true);
		mDeleteEntryButton.setPreferredSize(new Dimension(25, 25));
		mDeleteEntryButton.setFocusable(false);
		mDeleteEntryButton.setEnabled(false);
		mDeleteEntryButton.getModel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int count = mPanel.getComponentCount() - 1;
				for (int i = count; i > 0; i--) {
					JPanel panel = (JPanel) mPanel.getComponent(i);
					if (panel.getBackground() == Color.BLUE) {
						TreasureRecord record = (TreasureRecord) panel.getClientProperty(TREASURE_RECORD_KEY);
						mEntries.remove(record);
						mPanel.remove(panel);
					}
				}
				updateValues();
				mPanel.revalidate();
				mPanel.repaint();
				mDeleteEntryButton.setEnabled(hasSelectedRows());
			}
		});
		innerWrapper.add(deleteEntryTitle);
		innerWrapper.add(mDeleteEntryButton);
		valuePanel.add(innerWrapper);

		return valuePanel;
	}

	// DW should be able to consolidate this and GenerateDisplayRecord(TreasureRecord)
	// this is used to create the labels in the header
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

		JPanel wrapper = new JPanel();
		mOldColor = wrapper.getBackground();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(0, 5, 0, 5)));

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

		if (mEntries.isEmpty() && mPanel.getComponentCount() == 0) {
			addEntryRecord(generateDisplayRecord(null));
		} else {
			for (TreasureRecord record : mEntries) {
				addRecord(generateDisplayRecord(record));
			}
		}
		updateValues();
	}

	private JPanel generateDisplayRecord(TreasureRecord record) {
		boolean enable = record == null;
		JTextField countField = new FocusTextField(COUNT_TITLE, 5);
		countField.setMaximumSize(new Dimension(100, 20));
		countField.setHorizontalAlignment(SwingConstants.RIGHT);
		countField.setEditable(enable);
		((AbstractDocument) countField.getDocument()).setDocumentFilter(mIntegerFilter);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				countField.requestFocusInWindow();
			}
		});

		JTextField valueField = new FocusTextField(VALUE_TITLE, 5);
		valueField.setMaximumSize(new Dimension(150, 20));
		valueField.setHorizontalAlignment(SwingConstants.RIGHT);
		valueField.setEditable(enable);
		((AbstractDocument) valueField.getDocument()).setDocumentFilter(mIntegerFilter);

		JTextField descriptionField = new FocusTextField(DESCRIPTION_TITLE, 20);
		descriptionField.setEditable(enable);

		JTextField totalField = new FocusTextField(TOTAL_TITLE2, 5);
		totalField.setMaximumSize(new Dimension(200, 20));
		totalField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalField.setEditable(false);
		((AbstractDocument) totalField.getDocument()).setDocumentFilter(mIntegerFilter);

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.putClientProperty(TREASURE_RECORD_KEY, record);
		wrapper.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1), new EmptyBorder(0, 5, 0, 5)));
		wrapper.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// nothing to do
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// nothing to do
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// nothing to do
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// nothing to do
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel source = (JPanel) e.getSource();
				if (mPanel.getComponent(0).equals(source)) {
					return;
				}
				if (source.getBackground() == Color.BLUE) {
					source.setBackground(mOldColor);
					mDeleteEntryButton.setEnabled(hasSelectedRows());
				} else {
					source.setBackground(Color.BLUE);
					mDeleteEntryButton.setEnabled(true);
				}
			}
		});

		wrapper.add(countField);
		wrapper.add(valueField);
		wrapper.add(descriptionField);
		wrapper.add(totalField);
		if (record != null) {
			countField.setText(String.valueOf(record.getCount()));
			valueField.setText(String.valueOf(record.getValue()));
			descriptionField.setText(record.getDescription());
			totalField.setText(String.valueOf(record.getTotalValue()));
		}

		wrapper.setMaximumSize(new Dimension(wrapper.getMaximumSize().width, wrapper.getMinimumSize().height));

		return wrapper;
	}

	private boolean hasSelectedRows() {
		boolean test = false;
		int count = mPanel.getComponentCount();
		for (int i = 1; i < count; i++) {
			if (mPanel.getComponent(i).getBackground() == Color.BLUE) {
				test = true;
				break;
			}
		}
		return test;
	}

	// adds display record
	private void addRecord(JPanel panel) {
		mPanel.add(panel);
	}

	// adds display record
	private void addEntryRecord(JPanel panel) {
		mPanel.add(panel, 0);
		mPanel.revalidate();
		mPanel.repaint();
	}

	public void updateValues() {

		float totalValue = 0;

		for (TreasureRecord record : mEntries) {
			if (record != null) {
				totalValue += record.getTotalValue();
			}
		}
		totalValue += mTotalGold * 10;
		totalValue += mTotalSilver;
		totalValue += (float) mTotalCopper / 10;
		mTotal = totalValue;
		mTotalLabel.setText(NumberFormat.getNumberInstance().format(mTotal));
		mTotalLabel.revalidate();
		mTotalLabel.repaint();
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
							if (count != 0 && !description.isBlank() && !description.equals(DESCRIPTION_TITLE)) {
								record = new TreasureRecord(this, count, value, description);
								mEntries.add(record);
								((FocusTextField) comp.getComponent(3)).setText(String.valueOf(record.getTotalValue()));
								comp.putClientProperty(TREASURE_RECORD_KEY, record);
								addEntryRecord(generateDisplayRecord(null));
								updateValues();
								break;
							}
						} else {
							int count = TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(0)).getText(), 0);
							int value = TKStringHelpers.getIntValue(((FocusTextField) comp.getComponent(1)).getText(), 0);
							int total = count * value;
							record.setCount(count);
							record.setValue(value);
							record.setDescription(((FocusTextField) comp.getComponent(2)).getText());
							record.setTotalValue(total);
							((FocusTextField) comp.getComponent(3)).setText(String.valueOf(total));
							updateValues();
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

	public void readPartyTreasure() {
		File file = new File(getPartyTreasureFileName());
		BufferedReader br = null;
		String in;

		try {
			if (file.exists()) {
				file = ((CharacterSheet) getOwner()).verifyDataFileVersion(file);
				br = new BufferedReader(new FileReader(file));
				while ((in = br.readLine()) != null) {
					if (in.equals(ACS.getDataFileVersion())) {
						in = br.readLine();
					}
					StringTokenizer tokenizer = new StringTokenizer(in);
					while (tokenizer.hasMoreTokens()) {
						switch (in) {
							case FILE_SECTION_START_KEY: {
								tokenizer = readValues(br);
								break;
							}
							default:
								throw new IllegalArgumentException("Unexpected value: " + in); //$NON-NLS-1$
						}
					}
				}
			}
		} catch (FileNotFoundException fnfe) {
			//DW9:: Log this
			System.err.println(fnfe.getMessage());
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			loadDisplay();
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException exception) {
				// nothing we can do...
			}
		}
	}

	public void savePartyTreasure() {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(getPartyTreasureFileName()));
			br.write(ACS.getDataFileVersion() + SystemInfo.getLineSeparator());
			saveValues(br);
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

	}

	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						String value = ""; //$NON-NLS-1$
						setKeyValuePair(key, value);
					} else {
						String value = tokenizer.nextToken();
						while (tokenizer.hasMoreTokens()) {
							value = value + " " + tokenizer.nextToken(); //$NON-NLS-1$
						}
						setKeyValuePair(key, value);
					}
				}
			}
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {
		br.write(FILE_SECTION_START_KEY + System.lineSeparator());

		br.write(TKStringHelpers.TAB + GOLD_KEY + TKStringHelpers.SPACE + mGoldField.getText().trim() + System.lineSeparator());
		br.write(TKStringHelpers.TAB + SILVER_KEY + TKStringHelpers.SPACE + mSilverField.getText().trim() + System.lineSeparator());
		br.write(TKStringHelpers.TAB + COPPER_KEY + TKStringHelpers.SPACE + mCopperField.getText().trim() + System.lineSeparator());
		for (TreasureRecord record : mEntries) {
			br.write(TKStringHelpers.TAB + COUNT_KEY + TKStringHelpers.SPACE + record.getCount() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + VALUE_KEY + TKStringHelpers.SPACE + record.getValue() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + DESCRIPTION_KEY + TKStringHelpers.SPACE + record.getDescription() + System.lineSeparator());
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (GOLD_KEY.equals(key)) {
			mGoldField.setText(value);
			mTotalGold = TKStringHelpers.getIntValue(value, 0);
		} else if (SILVER_KEY.equals(key)) {
			mSilverField.setText(value);
			mTotalSilver = TKStringHelpers.getIntValue(value, 0);
		} else if (COPPER_KEY.equals(key)) {
			mCopperField.setText(value);
			mTotalCopper = TKStringHelpers.getIntValue(value, 0);
		} else if (COUNT_KEY.equals(key)) {
			mCountText = value;
		} else if (VALUE_KEY.equals(key)) {
			mValueText = value;
		} else if (DESCRIPTION_KEY.equals(key)) {
			mDescriptionText = value;
			mEntries.add(new TreasureRecord(this, TKStringHelpers.getIntValue(mCountText, 0), TKStringHelpers.getIntValue(mValueText, 0), mDescriptionText));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key + " " + value); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
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
					getCaret().setVisible(true);
					select(0, getText().length());
				}
			});
		}
	}

	public String getPartyTreasureFileName() {
		return PreferenceStore.getInstance().getCurrentFileLocation() + PARTY_TREASURE_FILENAME;
	}

	private boolean validateCalculatorInput(String originalValue, String operator) {
		try {
			Float.parseFloat(originalValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}
		if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("÷") && !operator.equals("/")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			return false;
		}
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		JTextField source = (JTextField) e.getSource();
		String value = source.getText();
		String operator = String.valueOf(e.getKeyChar());
		if (validateCalculatorInput(value, operator)) {
			TKCalculator calc = new TKCalculator(((CharacterSheet) getOwner()).getFrame(), value, operator);
			String returnValue = calc.getReturnValue();
			if (returnValue != null) {
				source.setText(returnValue);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing to do
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// nothing to do
	}
}
