/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.promasi.client_swing.playmode.IPlayMode;
import static org.promasi.client_swing.playmode.singleplayer.SinglePlayerPlayMode._logger;
import org.promasi.desktop_swing.IMainFrame;
import org.promasi.utilities.file.RootDirectory;
import org.promasi.utils_swing.components.jlist.IMenuEntry;

/**
 *
 * @author alekstheod
 */
public class MultiPlayerPlayMode implements IPlayMode, IMenuEntry {

    private static final String _name = "MultiPlayer";

    private Icon _menuIcon;

    /**
     *
     */
    public static final String CONST_PLAYMODE_DESCRIPTION = "The purpose of this play mode is to gather the highest score against the other players.<br>"
            + "You will play through various levels. On each level you will have to complete a project.<br>";

    public MultiPlayerPlayMode() {
        try {
            _menuIcon = new ImageIcon(RootDirectory.getInstance().getImagesDirectory() + "user_group.png");
        } catch (IOException e) {
            _logger.error(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return CONST_PLAYMODE_DESCRIPTION;
    }

    @Override
    public void gotoNextPanel(IMainFrame mainFrame) {
        mainFrame.changePanel(new ConnectServerJPanel(mainFrame));
    }

    @Override
    public String toString() {
        return _name;
    }

    @Override
    public Icon getIcon() {
        return _menuIcon;
    }

}
