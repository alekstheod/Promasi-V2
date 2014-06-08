/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.promasi.client_swing.playmode.singleplayer.SinglePlayerGamesServer;
import org.promasi.desktop_swing.IMainFrame;
import org.promasi.game.AGamesServer;
import org.promasi.game.GameException;
import org.promasi.game.IGame;
import org.promasi.game.IGamesServerListener;
import org.promasi.utilities.file.RootDirectory;

/**
 *
 * @author alekstheod
 */
public class NewGameGamesServer extends AGamesServer<IGamesServerListener> implements IMultiPlayerGamesServerListener {

    private final MultiPlayerGamesServer _onlineServer;
    private final AGamesServer<IGamesServerListener> _offlineServer;
    private final IMainFrame _mainFrame;

    public NewGameGamesServer(AGamesServer<?> server, IMainFrame frame) throws IOException {
        _mainFrame = frame;
        _offlineServer = new SinglePlayerGamesServer(RootDirectory.getInstance().getRootDirectory() + "SinglePlayer");
        _onlineServer = (MultiPlayerGamesServer) server;
        _onlineServer.clearListeners();
        _onlineServer.addListener(this);
        _offlineServer.addListener(this);
    }

    @Override
    public void requestGamesList() {
        _offlineServer.requestGamesList();
    }

    @Override
    public void joinGame(IGame game) {
        _onlineServer.joinGame(game);
    }

    @Override
    public boolean isNewGameAllowed() {
        return false;
    }

    @Override
    public void updateGamesList(List<IGame> games) {
        List<IGame> multiPlayerGames = new LinkedList<>();
        for (IGame game : games) {
            try {
                multiPlayerGames.add(_onlineServer.toMultiPlayerGame(game));
            } catch (GameException ex) {
                Logger.getLogger(NewGameGamesServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (IGamesServerListener listener : getListeners()) {
            listener.updateGamesList(multiPlayerGames);
        }
    }

    @Override
    public void onJoinGame(IGame game) {
        WaitingPlayersJPanel newPanel = new WaitingPlayersJPanel(_mainFrame, _onlineServer, game);
        _mainFrame.changePanel(newPanel);
    }

    @Override
    public void receiveMessage(String clientId, String message) {}

    @Override
    public void updatePlayersList(List<String> players) {}
}
