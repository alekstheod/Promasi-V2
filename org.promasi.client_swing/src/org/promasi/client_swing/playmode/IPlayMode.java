/**
 * 
 */
package org.promasi.client_swing.playmode;

import org.promasi.desktop_swing.IMainFrame;

/**
 * @author alekstheod
 * Play mode interface.
 */
public interface IPlayMode {
	
	/**
	 * Will return a string which represents the description of the playmode.
	 * @return description of the playmode.
	 */
	public String getDescription();
	
	/**
	 * Will switch the frame on the main panel.
	 * @param mainFrame instance of the main frame.
	 */
	public void gotoNextPanel( IMainFrame mainFrame );
}
