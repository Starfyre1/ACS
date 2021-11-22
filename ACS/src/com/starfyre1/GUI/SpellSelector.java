/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.common.SpellUser;
import com.starfyre1.dataset.spells.SpellRecord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;

public class SpellSelector extends JDialog implements ActionListener, MouseListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SPELL_SELECTOR		= "Spell Selector";											//$NON-NLS-1$
	private static final String	POWER				= "Power ";													//$NON-NLS-1$

	private static final String	DESCRIPTION			= "Description";											//$NON-NLS-1$
	private static final String	LEARN				= "Learn";													//$NON-NLS-1$
	private static final String	CLOSE				= "Close";													//$NON-NLS-1$

	private static final Color	SELECTED_COLOR		= new ColorUIResource(UIManager.getColor("Button.focus"));	//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String				mSpellAreaName;
	private int					mSpellLevel			= 0;
	private SpellRecord			mSpellToLearn		= null;

	private Color				mOldColor			= null;
	private Color				mOldSelectedColor	= null;
	private JLabel				mOldLabel			= null;

	private JPanel				mSpellListPanel		= new JPanel();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * DW add Description
	 */
	public SpellSelector(JFrame owner, String spellAreaName) {
		super(owner, SPELL_SELECTOR, true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		mSpellAreaName = spellAreaName;

		updatePanel(mSpellLevel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getButton(DESCRIPTION));
		buttonPanel.add(getButton(LEARN));
		buttonPanel.add(getButton(CLOSE));

		JPanel header = new JPanel();
		BoxLayout bl = new BoxLayout(header, BoxLayout.Y_AXIS);
		header.setLayout(bl);

		header.add(new TKPageTitleLabel(mSpellAreaName));

		JPanel popupPanel = new JPanel();
		JLabel popupLabel = new JLabel(POWER);

		popupPanel.add(popupLabel);
		popupPanel.add(new TKPopupMenu(generateSpellLevelPopup()));

		header.add(popupPanel);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setPreferredSize(new Dimension(400, 500));
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(header, BorderLayout.NORTH);
		wrapper.add(mSpellListPanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	private void updatePanel(int spellLevel) {
		SpellUser caster = (SpellUser) ClassList.getCharacterClass(mSpellAreaName);
		ArrayList<SpellRecord> spells = caster.getSpellList(spellLevel);

		mSpellListPanel.removeAll();
		mSpellListPanel.repaint(mSpellListPanel.getBounds());
		mSpellListPanel.revalidate();

		JPanel panel = new JPanel(new GridLayout(0, 3));
		JLabel label1 = new JLabel("Name", SwingConstants.CENTER); //$NON-NLS-1$
		JLabel label2 = new JLabel("Speed", SwingConstants.CENTER); //$NON-NLS-1$
		JLabel label3 = new JLabel("Power", SwingConstants.CENTER); //$NON-NLS-1$
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		mSpellListPanel.add(panel);

		for (SpellRecord record : spells) {
			String name = record.getName();
			int castingSpeed = record.getCastingTime();
			int power = record.getPower();
			label1 = new JLabel(name, SwingConstants.RIGHT);
			label1.setOpaque(true);
			label1.addMouseListener(this);
			label2 = new JLabel("" + castingSpeed, SwingConstants.CENTER); //$NON-NLS-1$
			label3 = new JLabel("" + power, SwingConstants.CENTER); //$NON-NLS-1$
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			mSpellListPanel.add(panel);
		}
	}

	private JMenu generateSpellLevelPopup() {
		SpellUser caster = (SpellUser) ClassList.getCharacterClass(mSpellAreaName);

		JMenu powerPopupMenu = TKPopupMenu.createMenu("0");
		int level = caster.getSpellLevels();
		for (int i = 0; i < level; i++) {
			JMenuItem menuItem = new JMenuItem("" + i);
			menuItem.setEnabled(true);
			menuItem.addActionListener(this);
			powerPopupMenu.add(menuItem);
		}
		return powerPopupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof JMenuItem) {
			mSpellLevel = TKStringHelpers.getIntValue(((JMenuItem) source).getText(), 0);
			updatePanel(mSpellLevel);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mOldLabel != null && mOldSelectedColor != null) {
			mOldLabel.setBackground(mOldSelectedColor);
		}

		mOldLabel = (JLabel) e.getSource();
		mOldSelectedColor = mOldLabel.getBackground();

		mOldLabel.setBackground(SELECTED_COLOR);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Does Nothing
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The spellToLearn. */
	public SpellRecord getSpellToLearn() {
		return mSpellToLearn;
	}

	private JButton getButton(String name) {
		JButton button = new JButton(name);
		button.setFocusable(false);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				mOldColor = button.getBackground();
				button.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				if (mOldColor != null) {
					button.setBackground(mOldColor);
				}
			}
		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj instanceof JButton) {
					String buttonName = ((JButton) obj).getText();
					if (CLOSE.equals(buttonName)) {
						dispose();
					} else if (LEARN.equals(buttonName)) {
						SpellUser caster = (SpellUser) ClassList.getCharacterClass(mSpellAreaName);
						ArrayList<SpellRecord> spells = caster.getSpellList(mSpellLevel);
						for (SpellRecord record : spells) {
							if (record.getName().equals(mOldLabel.getText())) {
								mSpellToLearn = record;
							}
						}
						System.out.println(mSpellToLearn.getName());
						dispose();
					} else if (DESCRIPTION.equals(buttonName)) {
						if (mOldLabel != null) {
							new SpellCard((JFrame) getOwner(), mOldLabel.getText());
						}
					}

				}
			}
		});
		return button;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}