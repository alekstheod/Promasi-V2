/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.util.List;
import org.promasi.game.AGamesServer;
import org.promasi.game.IGame;
import org.promasi.network.tcp.NetworkException;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.messages.IMessageProcessor;
import org.promasi.protocol.messages.LoginFailedResponse;
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
public class MultiPlayerGamesServer extends AGamesServer implements IMessageProcessor{

    private final ProMaSiClient _client;

    private final String _playerId;
    
    private static final ILogger _logger = LoggerFactory.getInstance(MultiPlayerGamesServer.class);
    
    private boolean _isConnectionError;

    public MultiPlayerGamesServer(String playerId, ProMaSiClient client) throws NetworkException {
        _client = client;
        Message response = _client.sendRecv(new LoginRequest(playerId, ""));
        _isConnectionError = false;
        response.process(this);
        if(_isConnectionError){
            throw new NetworkException("Login failed");
        }
        
        _playerId = playerId;
    }

    @Override
    public boolean requestGamesList() {
        return _client.send(new UpdateAvailableGameListRequest());
    }

    @Override
    public boolean joinGame(IGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createGame(IGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGamesList(List<IGame> games) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onJoinGame(IGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNewGameAllowed() {
        return true;
    }

    private void processMessage(Message m){
        _logger.error("Invalid message received");
        _isConnectionError = true;
    }
    
    private void processMessage(LoginResponse m){
    }
    
    private void processMessage(LoginFailedResponse m){
        _isConnectionError = true;
    }
    
    @Override
    public <T extends Message> void process(T t) {
        processMessage(t);
    }
}
