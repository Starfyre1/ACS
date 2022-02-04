/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.journal.CampaignDateChooser;
import com.starfyre1.GUI.journal.JournalDisplay;
import com.starfyre1.GUI.journal.WorldDateChooser;
import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class JournalRecord extends JTextArea implements Comparable<JournalRecord> {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	CREATE							= "Create";							//$NON-NLS-1$
	private static final String	CANCEL							= "Cancel";							//$NON-NLS-1$
	private static final String	DELETE							= "Delete";							//$NON-NLS-1$

	public static final String	CAMPAIGN_DAY_START				= "CAMPAIGN DAY START";				//$NON-NLS-1$
	public static final String	CAMPAIGN_DAY_END				= "CAMPAIGN DAY END";				//$NON-NLS-1$
	public static final String	LEVEL_UP						= "LEVEL UP!";						//$NON-NLS-1$
	public static final String	CHARACTER_CREATION				= "CHARACTER CREATION!";			//$NON-NLS-1$
	public static final String	DETERMINATION_START				= "DETERMINATION START";			//$NON-NLS-1$
	public static final String	DETERMINATION_COMPLETE_SUCCESS	= "DETERMINATION COMPLETE SUCCESS";	//$NON-NLS-1$
	public static final String	DETERMINATION_COMPLETE_FAILED	= "DETERMINATION COMPLETE FAILED";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JournalDisplay		mParent;
	private JButton				mWorldDateButton;
	private JButton				mCampaignDateButton;
	private String				mWorldDate;
	private String				mCampaignDate;
	private JLabel				mHeaderLabel1;
	private JLabel				mHeaderLabel2;
	private boolean				mRecordDeleted					= false;
	private boolean				mRecordCancelled				= false;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public JournalRecord(JournalDisplay parent) {
		super();

		mParent = parent;

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setEditable(true);
		setLineWrap(true);
		setWrapStyleWord(true);

		mHeaderLabel1 = new JLabel();
		mHeaderLabel2 = new JLabel();

		mWorldDate = WorldDateChooser.getWorldDate();
		mCampaignDate = CampaignDateChooser.getCampaignDate();
	}

	public JournalRecord(JournalDisplay parent, String campaignDate, String journalText, String worldDate) {
		super();

		mParent = parent;

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setEditable(true);
		setLineWrap(true);
		setWrapStyleWord(true);

		mHeaderLabel1 = new JLabel();
		mHeaderLabel2 = new JLabel();

		mCampaignDate = campaignDate;
		setText(journalText);
		mWorldDate = worldDate;

		setHeaderText();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	public static JournalRecord getJournalRecord(JournalDisplay parent, String which) {
		String campaignDate = CampaignDateChooser.getCampaignDate();
		String worldDate = WorldDateChooser.getWorldDate();
		JournalRecord record = switch (which) {
			case CAMPAIGN_DAY_START:
				yield new JournalRecord(parent, campaignDate, CAMPAIGN_DAY_START, worldDate);
			case CAMPAIGN_DAY_END:
				yield new JournalRecord(parent, campaignDate, CAMPAIGN_DAY_END, worldDate);
			case LEVEL_UP:
				yield new JournalRecord(parent, campaignDate, LEVEL_UP, worldDate);
			case CHARACTER_CREATION:
				yield new JournalRecord(parent, campaignDate, CHARACTER_CREATION, worldDate);
			case DETERMINATION_START:
				yield new JournalRecord(parent, campaignDate, DETERMINATION_START, worldDate);
			case DETERMINATION_COMPLETE_SUCCESS:
				yield new JournalRecord(parent, campaignDate, DETERMINATION_COMPLETE_SUCCESS, worldDate);
			case DETERMINATION_COMPLETE_FAILED:
				yield new JournalRecord(parent, campaignDate, DETERMINATION_COMPLETE_FAILED, worldDate);
			default:
				throw new IllegalArgumentException("Unexpected value: " + which); //$NON-NLS-1$
		};

		return record;
	}

	public void setHeaderText() {
		String line1 = new String();
		String line2 = new String();
		try {
			if (getDocument().getLength() > 0) {
				int start1 = getLineStartOffset(0);
				int len1 = getLineEndOffset(0) - start1;
				line1 = getText(start1, len1);
				if (line1.length() > 180) {
					line1 = line1.substring(0, 150) + "..."; //$NON-NLS-1$
				}

				if (getLineCount() > 1) {
					int start2 = getLineStartOffset(1);
					int len2 = getLineEndOffset(1) - start2;
					line2 = getText(start2, len2);
					if (line2.length() > 180) {
						line2 = line2.substring(0, 150) + "..."; //$NON-NLS-1$
					}
				}
			}

		} catch (BadLocationException exception) {
			exception.printStackTrace();
		}

		mHeaderLabel1.setText(line1);
		mHeaderLabel2.setText(line2);
	}

	public JPanel getJournalRecordHeader() {
		mCampaignDateButton = getDateButton(mCampaignDate, new CampaignDateActionListener());
		mWorldDateButton = getDateButton(mWorldDate, new WorldDateActionListener());

		JPanel textHeaderPanel = new JPanel(new GridLayout(2, 1, 5, 0));
		textHeaderPanel.setBorder(new EmptyBorder(0, 15, 0, 15));
		textHeaderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				displayJournalRecord(true);
			}
		});

		textHeaderPanel.add(mHeaderLabel1);
		textHeaderPanel.add(mHeaderLabel2);

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);
		panel.setBorder(new LineBorder(Color.BLACK));

		panel.add(mCampaignDateButton);
		panel.add(textHeaderPanel);
		panel.add(mWorldDateButton);

		panel.setMaximumSize(new Dimension(panel.getMaximumSize().width, panel.getMinimumSize().height));
		return panel;
	}

	public void displayJournalRecord(boolean saveIfCanceled) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel buttonPanel = createButtonPanel(dialog);

		dialog.add(scrollPane, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);

		dialog.setTitle("Journal"); //$NON-NLS-1$
		dialog.setModal(true);
		dialog.setMinimumSize(JournalDisplay.JOURNAL_ENTRY_SIZE);
		dialog.setLocationRelativeTo(((CharacterSheet) mParent.getOwner()).getFrame());
		dialog.setVisible(true);
		if (saveIfCanceled && !isRecordDeleted()) {
			setHeaderText();
		} else {
			mParent.removeRecord(this);
		}

	}

	private JPanel createButtonPanel(JDialog dialog) {
		JPanel panel = new JPanel();

		JButton cancelButton = TKComponentHelpers.createButton(CANCEL, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mRecordCancelled = true;
				dialog.dispose();
			}

		});

		// DW need to add a listener on the JTextArea to disable the createButton when there is no text
		JButton createButton = TKComponentHelpers.createButton(CREATE, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getDocument().getLength() == 0) {
					mRecordDeleted = true;
				}
				dialog.dispose();
			}

		});

		// DW need to add a listener on the JTextArea to disable the createButton when there is no text
		JButton deleteButton = TKComponentHelpers.createButton(DELETE, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mRecordDeleted = true;
				dialog.dispose();
			}

		});

		panel.add(deleteButton);
		panel.add(Box.createHorizontalStrut(50));
		panel.add(createButton);
		panel.add(cancelButton);

		return panel;
	}

	public static JButton getDateButton(String date, ActionListener listener) {
		JButton dateButton = new JButton(date);
		dateButton.setFont(CharacterSheet.MONOSPACED_FONT);
		dateButton.setBorderPainted(false);
		dateButton.setFocusPainted(false);
		dateButton.addActionListener(listener);

		dateButton.addMouseListener(new MouseAdapter() {
			Color oldColor = null;

			@Override
			public void mouseEntered(MouseEvent evt) {
				oldColor = dateButton.getBackground();
				dateButton.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (oldColor != null) {
					dateButton.setBackground(oldColor);
				}
			}
		});
		return dateButton;
	}

	private int getWorldYear() {
		return TKStringHelpers.getIntValue(mWorldDate.substring(8), 0);
	}

	private int getWorldMonth() {
		return WorldDateChooser.getMonthIndex(mWorldDate.substring(0, 3));
	}

	private int getWorldDay() {
		return TKStringHelpers.getIntValue(mWorldDate.substring(4, 6), 0);
	}

	private int getCampaignYear() {
		return TKStringHelpers.getIntValue(mCampaignDate.substring(8), 0);
	}

	private int getCampaignMonth() {
		return CampaignDateChooser.getMonthIndex(mCampaignDate.substring(0, 3));
	}

	private int getCampaignDay() {
		return TKStringHelpers.getIntValue(mCampaignDate.substring(4, 6), 0);
	}

	public String getCampaignDate() {
		return mCampaignDate;
	}

	public String getWorldDate() {
		return mWorldDate;
	}

	public String getJournalText() {
		Document doc = getDocument();
		try {
			return doc.getText(0, doc.getLength());
		} catch (BadLocationException exception) {
			return TKStringHelpers.EMPTY_STRING;
		}
	}

	/** @return The deleteRecord. */
	public boolean isRecordDeleted() {
		return mRecordDeleted;
	}

	/** @return The saveRecordIfCancelled. */
	public boolean isRecordCancelled() {
		return mRecordCancelled;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

	private final class CampaignDateActionListener implements ActionListener {
		/**
		 * Creates a new {@link CampaignDateActionListener}.
		 */
		private CampaignDateActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			CampaignDateChooser cal = new CampaignDateChooser(((CharacterSheet) mParent.getOwner()).getFrame(), CampaignDateChooser.parseCampaignDate(mCampaignDate));
			String date = cal.getSelectedDate();
			if (!date.isEmpty()) {
				mCampaignDate = date;
				mCampaignDateButton.setText(mCampaignDate);
				mParent.updatePreviewPanel();
			}
		}
	}

	private final class WorldDateActionListener implements ActionListener {
		/**
		 * Creates a new {@link WorldDateActionListener}.
		 */
		private WorldDateActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			WorldDateChooser cal = new WorldDateChooser(((CharacterSheet) mParent.getOwner()).getFrame(), WorldDateChooser.parseWorldDate(mWorldDate));
			String date = cal.getSelectedDate();
			if (!date.isEmpty()) {
				mWorldDate = date;
				mWorldDateButton.setText(mWorldDate);
				mParent.updatePreviewPanel();
			}
		}
	}

	// DW may want to reverse this so we sort by campaign calendar first
	@Override
	public int compareTo(JournalRecord o) {
		if (mWorldDate.equals(o.mWorldDate)) {
			int year = getCampaignYear();
			int mth = getCampaignMonth();
			int date = getCampaignDay();
			int oYear = o.getCampaignYear();
			int oMth = o.getCampaignMonth();
			int oDate = o.getCampaignDay();
			return year == oYear ? mth == oMth ? date - oDate : mth - oMth : year - oYear;
		}
		int year = getWorldYear();
		int mth = getWorldMonth();
		int date = getWorldDay();
		int oYear = o.getWorldYear();
		int oMth = o.getWorldMonth();
		int oDate = o.getWorldDay();
		return year == oYear ? mth == oMth ? date - oDate : mth - oMth : year - oYear;
	}
}
