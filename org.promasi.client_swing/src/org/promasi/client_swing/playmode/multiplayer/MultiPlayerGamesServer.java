/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import org.promasi.game.AGamesServer;
import org.promasi.game.GameException;
import org.promasi.game.IGame;
import org.promasi.network.tcp.NetworkException;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.messages.CreateGameRequest;
import org.promasi.protocol.messages.CreateGameResponse;
import org.promasi.protocol.messages.JoinGameRequest;
import org.promasi.protocol.messages.JoinGameResponse;
import org.promasi.protocol.messages.LoginRequest;
import org.promasi.protocol.messages.LoginResponse;
import org.promasi.protocol.messages.Message;
import org.promasi.protocol.messages.UpdateAvailableGameListRequest;
import org.promasi.utilities.logger.ILogger;
import org.promasi.utilities.logger.LoggerFactory;

/**
 *
 * @author alekstheod
 */
public class MultiPlayerGamesServer extends AGamesServer {

    private final ProMaSiClient _client;

    private final String _playerId;

    private static final ILogger _logger = LoggerFactory.getInstance(MultiPlayerGamesServer.class);

    public MultiPlayerGamesServer(String playerId, ProMaSiClient client) throws NetworkException {
        _client = client;
        Message message = _client.sendRecv(new LoginRequest(playerId, ""));
        if (!(message instanceof LoginResponse)) {
            _logger.error("Login failed please check your user name and try again");
            throw new NetworkException("Login failed");
        }

        _playerId = playerId;
    }

    @Override
    public void requestGamesList() {
        _client.send(new UpdateAvailableGameListRequest());
    }

    @Override
    public void joinGame(IGame game) {
        if(game.getMemento().getCompanyModel() != null )
        {
            Message msg = _client.sendRecv(new CreateGameRequest(game.getName(), game.getMemento()));
            if (msg instanceof CreateGameResponse) {
                onJoinGame(game);
            }
        }else{
            Message msg = _client.sendRecv(new JoinGameRequest(game.getName()));
            if (msg instanceof JoinGameResponse) {
                onJoinGame(game);
            }
        }
    }

    @Override
    public boolean isNewGameAllowed() {
        return true;
    }

    public IGame toMultiPlayerGame(IGame game) throws GameException {
        return new MultiPlayerGame(game.getMemento(), _client);
    }
}
