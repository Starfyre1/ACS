/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.LanguageRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LanguageDisplay extends TKTitledDisplay {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	static final String	LANGUAGE_TITLE	= "Languages";				//$NON-NLS-1$
	static final Color	MEDIUM_GRAY		= new Color(108, 108, 108);

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel		mNameLabel[];
	private JLabel		mTypeLabel[];

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public LanguageDisplay(CharacterSheet owner) {
		super(owner, LANGUAGE_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	protected Component createDisplay() {
		JPanel outer = new JPanel();
		BoxLayout bl = new BoxLayout(outer, BoxLayout.Y_AXIS);
		outer.setLayout(bl);

		String[] languages = LanguageRecord.getLanguages();
		String[] languageTypes = LanguageRecord.getLanguagesTypes();
		String[] languageToolTips = LanguageRecord.getLanguagesDescriptions();
		mNameLabel = new JLabel[LanguageRecord.getNumLang()];
		mTypeLabel = new JLabel[LanguageRecord.getNumLang()];

		JPanel center = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		for (int i = 0; i < languages.length; i++) {
			String name = languages[i];

			mNameLabel[i] = new JLabel(name);
			mNameLabel[i].setToolTipText(languageToolTips[i]);
			mNameLabel[i].setForeground(Color.LIGHT_GRAY);
			c.anchor = GridBagConstraints.LINE_END;
			c.insets = new Insets(0, 0, 0, 15);
			c.gridx = 0;
			c.gridy = i;
			center.add(mNameLabel[i], c);

			mTypeLabel[i] = new JLabel(languageTypes[i]);
			mTypeLabel[i].setToolTipText(languageToolTips[i]);
			mTypeLabel[i].setForeground(Color.LIGHT_GRAY);
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = i;
			center.add(mTypeLabel[i], c);
		}
		return center;
	}

	@Override
	public void loadDisplay() {
		LanguageRecord record = ((CharacterSheet) getOwner()).getLanguageRecord();
		if (record == null) {
			return;
		}
		for (int i = 0; i < mNameLabel.length; i++) {
			if (record.isLanguageKnown(mNameLabel[i].getText())) {
				mNameLabel[i].setForeground(Color.BLACK);
				mTypeLabel[i].setForeground(MEDIUM_GRAY);
			} else {
				mNameLabel[i].setForeground(Color.LIGHT_GRAY);
				mTypeLabel[i].setForeground(Color.LIGHT_GRAY);
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
