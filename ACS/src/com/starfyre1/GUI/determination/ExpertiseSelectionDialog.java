/* Copyright (C) Starfyre Enterprises 2022. All rights reserved. */

package com.starfyre1.GUI.determination;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.GUI.character.SkillsDisplay;
import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.ToolKit.TKPopupMenu;
import com.starfyre1.dataset.WeaponList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class ExpertiseSelectionDialog extends JDialog implements ActionListener, MouseListener {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	private static final String	SELECT_EXPERTISE	= "Select Expertise";	//$NON-NLS-1$

	private static final String	SELECT				= "Select";				//$NON-NLS-1$
	private static final String	CANCEL				= "Cancel";				//$NON-NLS-1$

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mSheet;
	private String				mTeacher;
	private String				mExpertise			= null;

	private Color				mOldColor			= null;
	private Color				mOldSelectedColor	= null;
	private JLabel				mOldLabel			= null;

	private JButton				mSelectButton		= null;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link ExpertiseSelectionDialog}.
	 *
	 * @param frame
	 */
	public ExpertiseSelectionDialog(CharacterSheet owner, String teacher) {
		super(owner.getFrame(), SELECT_EXPERTISE, true);
		mSheet = owner;
		mTeacher = teacher;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		mSelectButton = getButton(SELECT);
		updateButtons(false);

		buttonPanel.add(mSelectButton);
		buttonPanel.add(getButton(CANCEL));

		JPanel header = new JPanel();
		BoxLayout bl = new BoxLayout(header, BoxLayout.Y_AXIS);
		header.setLayout(bl);

		header.add(new TKPageTitleLabel(SELECT_EXPERTISE + " for " + mTeacher)); //$NON-NLS-1$

		JPanel popupPanel = new JPanel();
		//		JLabel popupLabel = new JLabel(LEVEL);

		//		popupPanel.add(popupLabel);
		popupPanel.add(new TKPopupMenu(generateExpertisePopup()));
		//
		//		header.add(popupPanel);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setPreferredSize(new Dimension(400, 500));
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(header, BorderLayout.NORTH);
		wrapper.add(popupPanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);

	}

	/**
	 * @return
	 */
	private JMenu generateExpertisePopup() {
		JMenu expertisePopupMenu = TKPopupMenu.createMenu(SELECT_EXPERTISE);

		String weaponList[] = WeaponList.getProficiencyList();
		String skillBasicList[] = SkillsDisplay.getBasicSkillsLabels();
		String skillThiefList[] = SkillsDisplay.getThiefSkillsLabels();

		String[] groups = { "Weapon Proficiency", "Basic Skills", "Thief Skills" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		ArrayList<JMenu> menus = new ArrayList<>();
		expertisePopupMenu.addSeparator();
		for (String group : groups) {
			JMenu menu = new JMenu(group);
			menus.add(menu);
			expertisePopupMenu.add(menu);
		}

		for (String name : weaponList) {
			if (name != null) {
				JMenuItem menuItem = new JMenuItem(name);
				menuItem.addActionListener(this);
				menus.get(0).add(menuItem);
			}
		}

		for (String name : skillBasicList) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(1).add(menuItem);
		}

		for (String name : skillThiefList) {
			JMenuItem menuItem = new JMenuItem(name);
			menuItem.addActionListener(this);
			menus.get(2).add(menuItem);
		}

		JMenuItem menuItem = new JMenuItem(SELECT_EXPERTISE);
		menuItem.addActionListener(this);
		expertisePopupMenu.add(menuItem, 0);
		//		expertisePopupMenu.addItemListener(this);

		return expertisePopupMenu;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		//		if (source instanceof JMenuItem) {
		//			mSpellLevel = TKStringHelpers.getIntValue(((JMenuItem) source).getText(), 0);
		//			updatePanel(mSpellLevel);
		//			updateButtons(false);
		//
		//		}

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

		//		if (!mSheet.getSpellListDisplay().getCurrentList().isSpellKnown(getSpellRecord(((JLabel) e.getSource()).getText()))) {
		//			mOldLabel = (JLabel) e.getSource();
		//			mOldSelectedColor = mOldLabel.getBackground();
		//
		//			mOldLabel.setBackground(CharacterSheet.SELECTED_COLOR);
		//
		//			updateButtons(true);
		//		} else {
		//			updateButtons(false);
		//		}
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

	private void updateButtons(boolean enable) {

		mSelectButton.setEnabled(enable);

	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
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
					if (CANCEL.equals(buttonName)) {
						dispose();
					} else if (SELECT.equals(buttonName)) {
						mExpertise = mOldLabel.getText();
						dispose();
					}

				}
			}

		});
		return button;
	}

	/**
	 * @return
	 */
	public String getExpertise() {
		return mExpertise;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
