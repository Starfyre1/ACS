/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.storage;

import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataModel.HistoryRecord;
import com.starfyre1.interfaces.Savable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class HistoryManager implements Savable {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String						FILE_SECTTION_START_KEY	= "HISTORY_SECTTION_START";	//$NON-NLS-1$
	public static final String						FILE_SECTTION_END_KEY	= "HISTORY_SECTTION_END";	//$NON-NLS-1$

	public static final String						EXPERIENCE_KEY			= "EXPERIENCE_KEY";			//$NON-NLS-1$
	public static final String						LEVEL_KEY				= "LEVEL_KEY";				//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static HistoryManager					sInstance				= null;
	private Hashtable<String, List<HistoryRecord>>	mTable;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	private HistoryManager() {
		mTable = new Hashtable<>();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public void addRecord(String key, HistoryRecord record) {
		int value = record.getNewValue();
		List<HistoryRecord> list = mTable.get(key);
		if (list == null) {
			list = new ArrayList<HistoryRecord>();
			list.add(record);
			mTable.put(key, list);
		} else if (!(list.get(list.size() - 1).getNewValue() == record.getNewValue())) {
			list.add(record);
			mTable.put(key, list);
		}
	}

	public boolean matchesLastEntry(HistoryRecord record, int value) {
		return record.getNewValue() == value;
	}

	public String getTooltip(String key) {
		StringBuilder sb = new StringBuilder();
		List<HistoryRecord> list = mTable.get(key);
		DateFormat date = DateFormat.getDateInstance();
		for (HistoryRecord record : list) {
			if (sb.isEmpty()) {
				sb.append("<html>"); //$NON-NLS-1$
			}
			sb.append(date.format(record.getDate()) + ": " + record.getNewValue() + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		sb.append("</html>"); //$NON-NLS-1$
		return sb.toString();
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static HistoryManager getInstance() {
		if (sInstance == null) {
			sInstance = new HistoryManager();
		}
		return sInstance;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;
		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						break;
						//						String value = ""; //$NON-NLS-1$
						//						Date date = new Date(TKStringHelpers.getLongValue(date, 0));
						//						int history = TKStringHelpers.getIntValue(value, 0);
						//						HistoryRecord record = new HistoryRecord(date, history);
						//						setKeyValuePair(key, record);
					}
					long date = TKStringHelpers.getLongValue(tokenizer.nextToken(), 0);
					int history = TKStringHelpers.getIntValue(tokenizer.nextToken(), 0);

					HistoryRecord record = new HistoryRecord(new Date(date), history);
					setKeyValuePair(key, record);
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
		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());

		Enumeration<String> enu = mTable.keys();
		while (enu.hasMoreElements()) {
			String test = enu.nextElement();
			List<HistoryRecord> list = mTable.get(test);
			for (HistoryRecord record : list) {
				br.write(test + TKStringHelpers.SPACE + record.getRawDate() + TKStringHelpers.SPACE + record.getNewValue() + System.lineSeparator());
			}
		}

		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	// DW need to add magic info to Armor
	@Override
	public void setKeyValuePair(String key, Object obj) {
		HistoryRecord value = (HistoryRecord) obj;
		if (EXPERIENCE_KEY.equals(key)) {
			addRecord(key, value);
		} else if (LEVEL_KEY.equals(key)) {
			addRecord(key, value);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + getClass() + " " + key); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
