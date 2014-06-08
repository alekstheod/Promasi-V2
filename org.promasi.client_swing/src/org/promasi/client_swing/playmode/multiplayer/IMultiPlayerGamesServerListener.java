/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.promasi.client_swing.playmode.multiplayer;

import java.util.List;
import org.promasi.game.IGamesServerListener;

/**
 *
 * @author alekstheod
 */
public interface IMultiPlayerGamesServerListener extends IGamesServerListener {
    void receiveMessage(String clientId, String message);
    void updatePlayersList( List<String> players);
}
