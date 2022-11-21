/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI.character;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKTable;
import com.starfyre1.ToolKit.TKTableModel;
import com.starfyre1.ToolKit.TKTitledDisplay;
import com.starfyre1.dataModel.CombatInformationRecord;
import com.starfyre1.dataModel.WeaponRecord;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AttackTotalsDisplay extends TKTitledDisplay implements TableModelListener {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String		ATTACKS_TOTAL_TITLE		= "Attacks Total";						//$NON-NLS-1$

	private static final String[]	COLUMN_HEADER_NAMES		= { "Speed", "Hit Bonus", "Damage" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	private static final String[]	COLUMN_HEADER_TOOLTIPS	= { "Speed", "Hit Bonus", "Damage" };	//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private TKTable					mTable;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public AttackTotalsDisplay(CharacterSheet owner) {
		super(owner, ATTACKS_TOTAL_TITLE);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	@Override
	protected Component createDisplay() {
		// This is loaded from the equipped weapons in the information section on the main page
		mTable = new TKTable(new TKTableModel(COLUMN_HEADER_NAMES, COLUMN_HEADER_TOOLTIPS, 0));
		mTable.setPreferredScrollableViewportSize(CharacterSheet.WEAPONS_TAB_TABLE_SIZE);
		//		table.setFillsViewportHeight(true);

		//		mTable.setRowHeight(20);
		mTable.getColumnModel().getColumn(0).setMinWidth(75);
		mTable.getColumnModel().getColumn(1).setMinWidth(75);
		//		mTable.getColumnModel().getColumn(2).setMinWidth(75);
		mTable.getColumnModel().getColumn(0).setMaxWidth(75);
		mTable.getColumnModel().getColumn(1).setMaxWidth(75);
		//		mTable.getColumnModel().getColumn(2).setMaxWidth(75);

		mTable.getModel().addTableModelListener(this);

		JScrollPane scrollPane = new JScrollPane(mTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	@Override
	public void loadDisplay() {
		CharacterSheet owner = (CharacterSheet) getOwner();
		ArrayList<WeaponRecord> weapons = owner.getEquippedWeaponRecords();
		if (weapons != null) {
			TKTableModel model = (TKTableModel) mTable.getModel();
			model.setRowCount(0);
			for (WeaponRecord record : weapons) {
				if (record.isEquipped()) {
					model.addRow(new Object[] { getSpeed(record), getHitBonus(record), getDamageBonus(record) });
				}
			}
		}

	}

	private Integer getDamageBonus(WeaponRecord record) {
		CharacterSheet owner = (CharacterSheet) getOwner();
		CombatInformationRecord cir = owner.getCombatInformationRecord();
		int damage1 = cir.getDamageBonus();
		int damage2 = cir.getDamageBonus();

		// DW Fix - need to provide a way to select 1 or 2 handed
		//		int damage1 = record.getDamageOneHanded();
		//		int damage2 = record.getDamageTwoHanded();
		damage1 += record.getDamageOneHanded();
		damage2 += record.getDamageTwoHanded();

		return Integer.valueOf(damage1 > damage2 ? damage1 : damage2);
	}

	private Integer getHitBonus(WeaponRecord record) {
		CharacterSheet owner = (CharacterSheet) getOwner();
		CombatInformationRecord cir = owner.getCombatInformationRecord();
		int hitBonus = record.getHitBonus();

		if (record.getType() < 3) {
			hitBonus += cir.getHitBonus();
			hitBonus += cir.getHitLevelBonus();
			hitBonus += record.getDPHitBonus();
		} else if (record.getType() == 3) {
			hitBonus += cir.getMissileBonus();
			hitBonus += record.getDPHitBonus();
		} else if (record.getType() == 4) {
			hitBonus += cir.getBowBonus();
			hitBonus += cir.getBowLevelBonus();
			hitBonus += record.getDPHitBonus();
		}

		return Integer.valueOf(hitBonus);
	}

	private Integer getSpeed(WeaponRecord record) {
		CharacterSheet owner = (CharacterSheet) getOwner();
		CombatInformationRecord cir = owner.getCombatInformationRecord();
		int speed = record.getAttackSpeed();
		if (record.getType() < 3) {
			speed += cir.getAttackSpeed();
		} else if (record.getType() == 3) {
			speed += cir.getMissileSpeed();
		} else if (record.getType() == 4) {
			speed += cir.getBowSpeed();
		}

		return Integer.valueOf(speed);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		updateTable();
	}

	private void updateTable() {
		// DW fill this out
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
