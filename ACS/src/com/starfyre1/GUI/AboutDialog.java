/* Copyright (C) Starfyre Enterprises 2020. All rights reserved. */

package com.starfyre1.GUI;

import com.starfyre1.ToolKit.TKButtonRollover;
import com.starfyre1.ToolKit.TKPageTitleLabel;
import com.starfyre1.startup.ACS;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class AboutDialog extends JDialog implements HyperlinkListener {
	/*****************************************************************************
	 * CONSTANTS
	 ****************************************************************************/

	/*****************************************************************************
	 * MEMBER VARIABLES
	 ****************************************************************************/

	/*****************************************************************************
	 * CONSTRUCTORS
	 ****************************************************************************/

	public AboutDialog(JFrame parent) {
		super(parent, true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);

		JTextPane mMessageLabel = new JTextPane();
		mMessageLabel.setEditable(false);
		mMessageLabel.setFocusable(false);
		mMessageLabel.setOpaque(false);
		mMessageLabel.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		mMessageLabel.addHyperlinkListener(this);
		mMessageLabel.setText("<html>" + ACS.TITLE + "<br><br>" + ACS.getVersion() + "<br>" + ACS.getBuildDate() + "<br>" + ACS.COPYRIGHT + "<br><br>" + "GitHub: "
				+ "<br><a href=\"https://www.github.com/Starfyre1/ACS/releases\">Athri Character Sheet (ACS)</a>" + "<br>" + "<a href=\"https://github.com/Starfyre1/ACS/issues\">ACS Issues/Bugs</a>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JButton okButton = new TKButtonRollover(this, "OK", true); //$NON-NLS-1$
		okButton.setFocusable(false);

		JPanel messagePanel = new JPanel();
		messagePanel.add(mMessageLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBorder(new CompoundBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EtchedBorder(EtchedBorder.RAISED)), new EtchedBorder(EtchedBorder.LOWERED)));

		wrapper.add(new TKPageTitleLabel(CharacterSheet.ABOUT), BorderLayout.PAGE_START);
		wrapper.add(messagePanel, BorderLayout.CENTER);
		wrapper.add(buttonPanel, BorderLayout.SOUTH);

		add(wrapper);

		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	/*****************************************************************************
	 * METHODS
	 ****************************************************************************/

	/*****************************************************************************
	 * INHERITED ABSTRACT METHODS
	 ****************************************************************************/
	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        	if(Desktop.isDesktopSupported()) {
        	    try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException ioe) {
					//DW9:: Log this
					System.err.println(ioe.getMessage());
				} catch (URISyntaxException urise) {
					//DW9:: Log this
					System.err.println(urise.getMessage());
				}
        	}
        }
    }

	/*****************************************************************************
	 * ACCESSORS
	 ****************************************************************************/

	/*****************************************************************************
	 * SERIALIZATION
	 ****************************************************************************/
}
