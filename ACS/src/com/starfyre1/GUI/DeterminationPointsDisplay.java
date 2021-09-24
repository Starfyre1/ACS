/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKComponentHelpers;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.AttributesRecord;
import com.starfyre1.interfaces.LevelListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DeterminationPointsDisplay extends TKTitledDisplay implements LevelListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	DETERMINATION_POINTS_TITLE	= "Determination Points";	//$NON-NLS-1$

	private static final String	POINTS_WEEK_LABEL			= "Points / Week";			//$NON-NLS-1$
	private static final String	ATTRIBUTES_LABEL			= "Attributes";				//$NON-NLS-1$
	private static final String	LANGUAGES_LABEL				= "Languages";				//$NON-NLS-1$
	private static final String	WEAPONS_LABEL				= "Weapons";				//$NON-NLS-1$

	private static final String	MAX_VALUE					= " / Max Value";			//$NON-NLS-1$

	private static final int	ROWS						= 9;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private JTextField			mPointsPerWeekField;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	DeterminationPointsDisplay(CharacterSheet owner) {
		super(owner, DETERMINATION_POINTS_TITLE);

		updateValues();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	public void levelChanged() {
		updateValues();
	}

	private void updateValues() {
		mPointsPerWeekField.setText(TKStringHelpers.EMPTY_STRING + getDeterminationPoints());
	}

	/** @return The determinationPointsTitle. */
	public int getDeterminationPoints() {
		int value = 0;

		CharacterSheet owner = (CharacterSheet) getOwner();
		if (owner != null) {
			AttributesRecord stats = owner.getAttributesRecord();
			if (stats != null) {
				value = 6;
				value += stats.getStat(8) - 12;
				value += (owner.getHeaderRecord().getLevel() - 1) * 3;
			}
		}
		return value;
	}

	@Override
	protected Component createDisplay() {
		JPanel outerWrapper = new JPanel();
		outerWrapper.setLayout(new BoxLayout(outerWrapper, BoxLayout.Y_AXIS));

		JPanel topWrapper = new JPanel();

		JLabel pointsPerWeekLabel = new JLabel(POINTS_WEEK_LABEL);
		mPointsPerWeekField = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
		JLabel pointsPerWeekTotalLabel = new JLabel(MAX_VALUE);

		topWrapper.add(pointsPerWeekLabel);
		topWrapper.add(mPointsPerWeekField);
		topWrapper.add(pointsPerWeekTotalLabel);

		JPanel mainWrapper = new JPanel(new GridLayout(1, 3, 5, 0));
		mainWrapper.setBorder(new EmptyBorder(0, 0, 5, 0));

		JPanel attributesPanel = createAttributesPanel();
		JPanel languagesPanel = createLanguagesPanel();
		JPanel weaponsPanel = createWeaponsPanel();

		mainWrapper.add(attributesPanel);
		mainWrapper.add(languagesPanel);
		mainWrapper.add(weaponsPanel);

		outerWrapper.add(topWrapper);
		outerWrapper.add(mainWrapper);

		return outerWrapper;
	}

	private JPanel createAttributesPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel wrapper = new JPanel();
		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
		wrapper.setLayout(bl);

		//		JTextField[] field = new JTextField[ROWS];
		JLabel[] field = new JLabel[ROWS];
		JTextField[] bonus = new JTextField[ROWS];

		for (int i = 0; i < ROWS; i++) {
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
			//			field[i] = TKComponentHelpers.createTextField(CharacterSheet.MED_FIELD_SIZE, 20);
			field[i] = new JLabel(AttributesRecord.mStatNames[i]);
			bonus[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
			inner.add(field[i]);
			inner.add(new JLabel("+")); //$NON-NLS-1$
			inner.add(bonus[i]);

			wrapper.add(inner);
		}

		panel.add(new JLabel(ATTRIBUTES_LABEL, SwingConstants.RIGHT), BorderLayout.PAGE_START);
		panel.add(wrapper, BorderLayout.CENTER);

		return panel;
	}

	private JPanel createLanguagesPanel() {
		JPanel panel = new JPanel(new BorderLayout(0, 5));

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

		JTextField[] field = new JTextField[ROWS];
		for (int i = 0; i < ROWS; i++) {
			field[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_LARGE, 20);
			wrapper.add(field[i]);
		}

		panel.add(new JLabel(LANGUAGES_LABEL, SwingConstants.CENTER), BorderLayout.PAGE_START);
		panel.add(wrapper, BorderLayout.CENTER);

		return panel;
	}

	private JPanel createWeaponsPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel wrapper = new JPanel();
		BoxLayout bl = new BoxLayout(wrapper, BoxLayout.Y_AXIS);
		wrapper.setLayout(bl);

		JTextField[] field = new JTextField[ROWS];
		JTextField[] bonus = new JTextField[ROWS];

		for (int i = 0; i < ROWS; i++) {
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
			field[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_MEDIUM, 20);
			bonus[i] = TKComponentHelpers.createTextField(CharacterSheet.FIELD_SIZE_SMALL, 20);
			inner.add(field[i]);
			inner.add(new JLabel("+")); //$NON-NLS-1$
			inner.add(bonus[i]);

			wrapper.add(inner);
		}

		panel.add(new JLabel(WEAPONS_LABEL, SwingConstants.CENTER), BorderLayout.PAGE_START);
		panel.add(wrapper, BorderLayout.CENTER);

		return panel;
	}

	@Override
	protected void loadDisplay() {
		updateValues();
		// DW load used points from file

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
