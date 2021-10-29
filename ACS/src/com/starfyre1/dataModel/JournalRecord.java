/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CampaignDate;
import com.starfyre1.startup.ACS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class JournalRecord extends JTextArea {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private Date			mWorldDate;
	private CampaignDate	mCampainDate;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalRecord() {
		super();

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setEditable(true);
		setLineWrap(true);
		setWrapStyleWord(true);

		mWorldDate = new Date(System.currentTimeMillis());
		mCampainDate = new CampaignDate(ACS.getInstance().getCharacterSheet().getFrame());

	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public JPanel getJournalRecordHeader() {
		String line1 = new String();
		String line2 = new String();
		try {
			if (getDocument().getLength() > 0) {
				int start1 = getLineStartOffset(0);
				int len1 = getLineEndOffset(0) - start1;
				line1 = getText(start1, len1);

				if (getLineCount() > 1) {
					int start2 = getLineStartOffset(1);
					int len2 = getLineEndOffset(1) - start2;
					line2 = getText(start2, len2);
				}
			}

		} catch (BadLocationException exception) {
			exception.printStackTrace();
		}

		JPanel wrapper = new JPanel(new GridLayout(2, 1, 5, 0));
		wrapper.setBorder(new EmptyBorder(0, 15, 0, 15));
		wrapper.add(new JLabel(line1));
		wrapper.add(new JLabel(line2));

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);
		panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 5, 0, 5)));

		panel.add(new JLabel(mCampainDate.getSelectedDate(), SwingConstants.LEADING));
		panel.add(wrapper);
		panel.add(new JLabel(DateFormat.getDateInstance().format(mWorldDate), SwingConstants.TRAILING));

		panel.setMaximumSize(new Dimension(panel.getMaximumSize().width, panel.getMinimumSize().height));
		return panel;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
