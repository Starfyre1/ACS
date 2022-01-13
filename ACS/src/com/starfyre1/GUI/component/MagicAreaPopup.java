/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.component;

import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.dataModel.ClassesRecord;
import com.starfyre1.dataset.MageList;
import com.starfyre1.dataset.PriestList;
import com.starfyre1.startup.ACS;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MagicAreaPopup {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	SELECT_MAGIC_AREA	= "Select Magic Area";	//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private static int			mWhich				= -1;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	public static JMenu generateMagicAreaPopup(ActionListener aListener, ItemListener iListener) {
		return generateMagicAreaPopup(aListener, iListener, -1);

	}

	public static JMenu generateMagicAreaPopup(ActionListener aListener, ItemListener iListener, int which) {

		JMenu popupMenu = TKPopupMenu.createMenu(SELECT_MAGIC_AREA);

		mWhich = which;

		MageList mages = ACS.getMages();
		PriestList priests = ACS.getPriests();

		ArrayList<String> groups = new ArrayList<String>();
		groups.addAll(mages.getMagesGroupsList());
		groups.addAll(priests.getPriestsGroupsList());

		ArrayList<String> names = new ArrayList<String>();
		names.addAll(mages.getMagesNamesList());
		names.addAll(priests.getPriestsNamesList());

		ArrayList<ClassesRecord> records = new ArrayList<ClassesRecord>();
		records.addAll(mages.getRecordsList());
		records.addAll(priests.getRecordsList());

		ArrayList<JMenu> menus = new ArrayList<>();
		for (int i = 0; i < groups.size(); i++) {
			JMenu menu = new JMenu(groups.get(i));
			menus.add(menu);
			popupMenu.add(menu);
		}

		for (JMenu element : menus) {
			for (int i = 0; i < names.size(); i++) {
				if (element.getText().equals(records.get(i).getGroup())) {
					JMenuItem menuItem = new JMenuItem(names.get(i));
					menuItem.addActionListener(aListener);
					element.add(menuItem);
				}
			}
		}

		for (int i = 0; i < names.size(); i++) {
			if (records.get(i).getGroup() == null) {
				JMenuItem menuItem = new JMenuItem(names.get(i));
				menuItem.addActionListener(aListener);
				popupMenu.add(menuItem, 0);
			}
		}

		JMenuItem menuItem = new JMenuItem(SELECT_MAGIC_AREA);
		menuItem.addActionListener(aListener);
		popupMenu.add(menuItem, 0);
		popupMenu.addItemListener(iListener);

		return popupMenu;
	}

	/** @return The mWhich. */
	public static int getWhich() {
		return mWhich;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
