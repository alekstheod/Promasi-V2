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
import org.promasi.protocol.messages.CreateGameRequest;
import org.promasi.protocol.messages.CreateGameResponse;
import org.promasi.protocol.messages.Message;
import org.promasi.utilities.file.RootDirectory;

/**
 *
 * @author alekstheod
 */
public class NewGameGamesServer extends AGamesServer {

    private final MultiPlayerGamesServer _onlineServer;
    private final AGamesServer _offlineServer;
    private final IMainFrame _mainFrame;

    public NewGameGamesServer(AGamesServer server, IMainFrame frame) throws IOException {
        _mainFrame = frame;
        _offlineServer = new SinglePlayerGamesServer(RootDirectory.getInstance().getRootDirectory() + "SinglePlayer");
        _onlineServer = (MultiPlayerGamesServer) server;
        _onlineServer.clearListeners();
        _offlineServer.addListener(new IGamesServerListener() {
            @Override
            public void updateGamesList(List<IGame> games) {
                NewGameGamesServer.this.updateGamesList(games);
            }

            @Override
            public void onJoinGame(IGame game) {
                NewGameGamesServer.this.onJoinGame(game);
            }
        });

        _onlineServer.addListener(new IGamesServerListener() {
            @Override
            public void updateGamesList(List<IGame> games) {
                NewGameGamesServer.this.updateGamesList(games);
            }

            @Override
            public void onJoinGame(IGame game) {
                NewGameGamesServer.this.onJoinGame(game);
            }
        });
    }

    @Override
    public boolean requestGamesList() {
        return _offlineServer.requestGamesList();
    }

    @Override
    public boolean joinGame(IGame game) {
        boolean result = false;
        

        return result;
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

    }
}
