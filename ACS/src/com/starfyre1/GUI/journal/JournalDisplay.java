/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.journal;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.JournalRecord;
import com.starfyre1.interfaces.Savable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JournalDisplay extends TKTitledDisplay implements ActionListener, Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String			FILE_SECTION_START_KEY	= "JOURNAL_SECTION_START";		//$NON-NLS-1$
	public static final String			FILE_SECTION_END_KEY	= "JOURNAL_SECTION_END";		//$NON-NLS-1$

	private static final String			CAMPAIGN_KEY			= "CAMPAIGN_KEY";				//$NON-NLS-1$
	private static final String			JOURNAL_KEY				= "JOURNAL_KEY";				//$NON-NLS-1$
	private static final String			WORLD_KEY				= "WORLD_KEY";					//$NON-NLS-1$
	private static final String			CAMPAIGN_DATE_START_KEY	= "CAMPAIGN_DATE_START_KEY";	//$NON-NLS-1$
	private static final String			LEVEL_UP_KEY			= "LEVEL_UP_KEY";				//$NON-NLS-1$
	private static final String			DETERMINATION_KEY		= "DETERMINATION_KEY";			//$NON-NLS-1$

	private static final String			JOURNAL_TITLE			= "Journal";					//$NON-NLS-1$
	private static final String			NEW_ENTRY				= "New Entry";					//$NON-NLS-1$

	public static final Dimension		JOURNAL_ENTRY_SIZE		= new Dimension(360, 480);
	public static final String			CAMPAIGN_DATE_LABEL		= " Campaign Date: ";			//$NON-NLS-1$
	public static final String			WORLD_DATE_lABEL		= " World Date: ";				//$NON-NLS-1$

	//DW make non-editable, add journal entry button, add journal entries with border.

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JButton						mNewEntryButton;
	private JCheckBox					mCampaignDayCheckbox;
	private JCheckBox					mLevelUpCheckbox;
	private JCheckBox					mDeterminationCheckbox;
	private ArrayList<JournalRecord>	mEntries				= new ArrayList<>();
	private JPanel						mPanel;

	private String						mCampaignDate			= TKStringHelpers.EMPTY_STRING;
	private String						mJournalText			= TKStringHelpers.EMPTY_STRING;
	private String						mWorldDate				= TKStringHelpers.EMPTY_STRING;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalDisplay(CharacterSheet owner) {
		super(owner, JOURNAL_TITLE);
	}

	public void gameDayStartQuestion() {
		// yes = 0; no = 1
		CharacterSheet sheet = (CharacterSheet) getOwner();
		if (sheet != null) {
			sheet.setGameDayStarted(true);
		}
		JournalRecord record = JournalRecord.getJournalRecord(this, JournalRecord.CAMPAIGN_DAY_START);
		record.append("\n     This is the beginning of a new campaign day"); //$NON-NLS-1$
		record.setHeaderText();

		mEntries.add(0, record);
		updatePreviewPanel();
	}

	public void characterLevelUp(int level) {
		JournalRecord record = JournalRecord.getJournalRecord(this, JournalRecord.LEVEL_UP);
		record.append("\n     You have become level " + level); //$NON-NLS-1$
		record.setHeaderText();

		mEntries.add(0, record);
		updatePreviewPanel();
	}

	public void characterCreated() {
		JournalRecord record = JournalRecord.getJournalRecord(this, JournalRecord.CHARACTER_CREATION);
		record.append("\n     You have started your adventure! "); //$NON-NLS-1$
		record.setHeaderText();

		mEntries.add(0, record);
		updatePreviewPanel();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {

		JPanel outerWrapper = new JPanel(new BorderLayout());

		mCampaignDayCheckbox = TKComponentHelpers.createCheckBox(JournalRecord.CAMPAIGN_DAY_START, true, this);
		mLevelUpCheckbox = TKComponentHelpers.createCheckBox(JournalRecord.LEVEL_UP, true, this);
		mDeterminationCheckbox = TKComponentHelpers.createCheckBox(JournalRecord.DETERMINATION, true, this);

		mNewEntryButton = TKComponentHelpers.createButton(NEW_ENTRY, this);
		mNewEntryButton.setBorderPainted(false);

		JPanel wrapper = new JPanel(new GridLayout(2, 3));
		wrapper.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 5, 0, 5)));

		wrapper.add(mCampaignDayCheckbox);
		wrapper.add(mLevelUpCheckbox);
		wrapper.add(mDeterminationCheckbox);

		wrapper.add(new JLabel(CAMPAIGN_DATE_LABEL, SwingConstants.LEADING));
		wrapper.add(mNewEntryButton);
		wrapper.add(new JLabel(WORLD_DATE_lABEL, SwingConstants.TRAILING));
		wrapper.setMaximumSize(new Dimension(wrapper.getMaximumSize().width, wrapper.getMinimumSize().height));

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
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(mNewEntryButton)) {
			createJournalRecord();
		} else if (source.equals(mCampaignDayCheckbox)) {
			updatePreviewPanel();
		} else if (source.equals(mLevelUpCheckbox)) {
			updatePreviewPanel();
		} else if (source.equals(mDeterminationCheckbox)) {
			updatePreviewPanel();
		}
	}

	private void createJournalRecord() {
		JournalRecord record = new JournalRecord(this);
		record.displayJournalRecord(false);

		if (!(record.isRecordCancelled() || record.isRecordDeleted())) {
			record.setHeaderText();
			mEntries.add(0, record);
			updatePreviewPanel();
		}
	}

	/**
	 * @param journalRecord
	 */
	public void removeRecord(JournalRecord journalRecord) {
		mEntries.remove(journalRecord);
		updatePreviewPanel();
	}

	public void updatePreviewPanel() {
		mPanel.removeAll();

		Collections.sort(mEntries);

		for (JournalRecord entry : mEntries) {
			JPanel journalRecordHeader = entry.getJournalRecordHeader();
			if (JournalRecord.CAMPAIGN_DAY_START.equals(entry.getHeaderLabelText())) {
				if (mCampaignDayCheckbox.isSelected()) {
					mPanel.add(journalRecordHeader, BorderLayout.CENTER);
				}
			} else if (JournalRecord.LEVEL_UP.equals(entry.getHeaderLabelText())) {
				if (mLevelUpCheckbox.isSelected()) {
					mPanel.add(journalRecordHeader, BorderLayout.CENTER);
				}
			} else if (entry.getHeaderLabelText().startsWith(JournalRecord.DETERMINATION)) {
				if (mDeterminationCheckbox.isSelected()) {
					mPanel.add(journalRecordHeader, BorderLayout.CENTER);
				}
			} else {
				mPanel.add(journalRecordHeader, BorderLayout.CENTER);
			}
		}
		mPanel.revalidate();
		mPanel.repaint();
	}

	public void clearRecords() {
		mEntries = new ArrayList<>();
		updatePreviewPanel();

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine().trim()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTION_END_KEY)) {
						updatePreviewPanel();
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

		br.write(TKStringHelpers.TAB + CAMPAIGN_DATE_START_KEY + TKStringHelpers.SPACE + mCampaignDayCheckbox.isSelected() + System.lineSeparator());
		br.write(TKStringHelpers.TAB + LEVEL_UP_KEY + TKStringHelpers.SPACE + mLevelUpCheckbox.isSelected() + System.lineSeparator());
		br.write(TKStringHelpers.TAB + DETERMINATION_KEY + TKStringHelpers.SPACE + mDeterminationCheckbox.isSelected() + System.lineSeparator());
		for (JournalRecord record : mEntries) {
			br.write(TKStringHelpers.TAB + CAMPAIGN_KEY + TKStringHelpers.SPACE + record.getCampaignDate() + System.lineSeparator());
			br.write(TKStringHelpers.TAB + JOURNAL_KEY + TKStringHelpers.SPACE + record.getJournalText().replace("\n", "~") + System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
			br.write(TKStringHelpers.TAB + WORLD_KEY + TKStringHelpers.SPACE + record.getWorldDate() + System.lineSeparator());
		}

		br.write(FILE_SECTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		value = value.replace("~", "\n "); //$NON-NLS-1$ //$NON-NLS-2$
		if (CAMPAIGN_DATE_START_KEY.equals(key)) {
			mCampaignDayCheckbox.setSelected(TKStringHelpers.getBoolValue(value, true));
		} else if (LEVEL_UP_KEY.equals(key)) {
			mLevelUpCheckbox.setSelected(TKStringHelpers.getBoolValue(value, true));
		} else if (DETERMINATION_KEY.equals(key)) {
			mDeterminationCheckbox.setSelected(TKStringHelpers.getBoolValue(value, true));
		} else if (CAMPAIGN_KEY.equals(key)) {
			mCampaignDate = value;
		} else if (JOURNAL_KEY.equals(key)) {
			mJournalText = value;
		} else if (WORLD_KEY.equals(key)) {
			mWorldDate = value;
			mEntries.add(new JournalRecord(this, mCampaignDate, mJournalText, mWorldDate));
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass().getName() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	@Override
	protected void loadDisplay() {
		//DW  not used currently... might call createDisplay()
	}

}
