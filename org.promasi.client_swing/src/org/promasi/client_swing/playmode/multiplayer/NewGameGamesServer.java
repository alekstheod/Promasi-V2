/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.io.IOException;
import java.util.List;
import org.promasi.client_swing.playmode.singleplayer.SinglePlayerGamesServer;
import org.promasi.game.AGamesServer;
import org.promasi.game.IGame;
import org.promasi.game.IGamesServerListener;
import org.promasi.utilities.file.RootDirectory;

/**
 *
 * @author alekstheod
 */
public class NewGameGamesServer extends AGamesServer {

    private final AGamesServer _onlineServer;
    private final AGamesServer _offlineServer;

    NewGameGamesServer(AGamesServer server) throws IOException {
        _offlineServer = new SinglePlayerGamesServer(RootDirectory.getInstance().getRootDirectory() + "SinglePlayer");
        _onlineServer = server;
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
        return _onlineServer.createGame(game);
    }

    @Override
    public boolean createGame(IGame game) {
        return _onlineServer.createGame(game);
    }

    @Override
    public boolean isNewGameAllowed() {
       return true;
    }
}
