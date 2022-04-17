/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JButtonRollover extends JButton {
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private Component mOwner;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link JButtonRollover}.
	 *
	 * @param owner
	 * @param text
	 * @param disposeOnPress
	 */
	public JButtonRollover(Component owner, String text, boolean disposeOnPress) {
		super(text);
		mOwner = owner;
		addChangeListener(disposeOnPress && owner != null && owner instanceof Dialog);
	}

	/**
	 * Creates a new {@link JButtonRollover}.
	 *
	 * @param owner
	 * @param icon
	 * @param disposeOnPress
	 */
	public JButtonRollover(Component owner, ImageIcon icon, boolean disposeOnPress) {
		super(icon);
		mOwner = owner;
		addChangeListener(disposeOnPress && owner != null && owner instanceof Dialog);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	private void addChangeListener(boolean dispose) {
		getModel().addChangeListener(new ChangeListener() {
			private Color oldColor = null;

			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel bm = (ButtonModel) e.getSource();
				if (bm.isPressed()) {
					if (dispose) {
						((Dialog) mOwner).dispose();
					}
				} else if (bm.isRollover()) {
					if (oldColor == null) {
						oldColor = getBackground();
					}
					setBackground(Color.GRAY);
				} else {
					if (oldColor != null) {
						setBackground(oldColor);
					}
				}
			}
		});

	}
	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
