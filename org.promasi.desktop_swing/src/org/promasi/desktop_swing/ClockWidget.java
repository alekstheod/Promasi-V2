/**
 * 
 */
package org.promasi.desktop_swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.promasi.utils_swing.GuiException;

/**
 * @author alekstheod
 * Represent the Clock widget in the ProMaSi's 
 * desktop.
 */
public class ClockWidget extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor will initialize the object.
	 */
	public ClockWidget()throws GuiException{
		JPanel internalPanel = new JPanel();
		internalPanel.setLayout(new BorderLayout());
		internalPanel.setOpaque(false);
		
		setLayout(new BorderLayout());
		setVisible(true);
		setPreferredSize(new Dimension(250,200));
		setOpaque(false);
		
		add(internalPanel);
	}
}
