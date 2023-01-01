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
	static final String	LANGUAGE_TITLE	= "Languages";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JLabel		nameLabel[];

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
		nameLabel = new JLabel[LanguageRecord.getNumLang()];

		JPanel center = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		for (int i = 0; i < languages.length; i++) {
			String name = languages[i];

			nameLabel[i] = new JLabel(name);
			nameLabel[i].setToolTipText(languageToolTips[i]);
			c.anchor = GridBagConstraints.LINE_END;
			c.insets = new Insets(0, 0, 0, 15);
			c.gridx = 0;
			c.gridy = i;
			center.add(nameLabel[i], c);

			JLabel typeLabel = new JLabel(languageTypes[i]);
			typeLabel.setToolTipText(languageToolTips[i]);
			typeLabel.setForeground(Color.GRAY);
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = i;
			center.add(typeLabel, c);
		}
		return center;
	}

	@Override
	public void loadDisplay() {
		LanguageRecord record = ((CharacterSheet) getOwner()).getLanguageRecord();
		if (record == null) {
			return;
		}
		for (JLabel label : nameLabel) {
			label.setForeground(record.isLanguageKnown(label.getText()) ? Color.BLACK : Color.GRAY);
		}
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
