/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.promasi.client_swing.playmode.multiplayer;

import java.util.List;
import java.util.Map;
import org.promasi.game.AGamesServer;
import org.promasi.game.GameException;
import org.promasi.game.IGame;
import org.promasi.game.IGameModelListener;
import org.promasi.game.company.ICompanyListener;
import org.promasi.game.company.IDepartmentListener;
import org.promasi.game.company.IMarketPlaceListener;
import org.promasi.game.model.generated.EmployeeTaskModel;
import org.promasi.game.model.generated.GameModelModel;
import org.promasi.game.singleplayer.IClientGameListener;
import org.promasi.protocol.client.IPromasiClientListener;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.messages.AssignEmployeeTasksRequest;
import org.promasi.protocol.messages.DischargeEmployeeRequest;
import org.promasi.protocol.messages.HireEmployeeRequest;
import org.promasi.protocol.messages.Message;
import org.promasi.protocol.messages.StartGameRequest;
import org.promasi.utilities.design.Observer;

/**
 *
 * @author alekstheod
 */
public class MultiPlayerGame implements IGame {

    private GameModelModel _model;
    private ProMaSiClient _client;
    private final Observer< IGameModelListener> _gameModelListeners = new Observer<>();
    private final Observer< ICompanyListener> _companyListeners = new Observer<>();
    private final Observer< IMarketPlaceListener> _marketPlaceListeners = new Observer<>();
    private final Observer< IDepartmentListener> _departmentListeners = new Observer<>();
    private final Observer< IClientGameListener> _clientGameListeners = new Observer<>();
    AGamesServer<IMultiPlayerGamesServerListener> _server;

    public MultiPlayerGame(AGamesServer<IMultiPlayerGamesServerListener> gameServer, GameModelModel model, ProMaSiClient client) throws GameException {
        if (model == null || client == null || gameServer == null) {
            throw new GameException("Invalid argument passed");
        }

        _model = model;
        _client = client;
        _server = gameServer;
        initializeConnection();
    }

    private void initializeConnection() {
        _client.addListener(new IPromasiClientListener() {

            @Override
            public void onReceive(ProMaSiClient client, Message message) {
            }

            @Override
            public void onDisconnect(ProMaSiClient client) {
            }

            @Override
            public void onConnect(ProMaSiClient client) {
            }

            @Override
            public void onConnectionError(ProMaSiClient client) {
            }
        });
    }

    @Override
    public String getName() {
        return _model.getGameName();
    }

    @Override
    public String getGameDescription() {
        return _model.getGameDescription();
    }

    @Override
    public boolean hireEmployee(String employeeId) {
        return _client.send(new HireEmployeeRequest(employeeId));
    }

    @Override
    public boolean dischargeEmployee(String employeeId) {
        return _client.send(new DischargeEmployeeRequest(employeeId));
    }

    @Override
    public boolean assignTasks(Map<String, List<EmployeeTaskModel>> employeeTasks) {
        return _client.send(new AssignEmployeeTasksRequest(employeeTasks));
    }

    @Override
    public boolean removeTasks(List<EmployeeTaskModel> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addListener(IClientGameListener listener) {
        return _clientGameListeners.addListener(listener);
    }

    @Override
    public boolean removeListener(IClientGameListener listener) {
        return _clientGameListeners.removeListener(listener);
    }

    @Override
    public boolean addCompanyListener(ICompanyListener listener) {
        return _companyListeners.addListener(listener);
    }

    @Override
    public boolean removeCompanyListener(ICompanyListener listener) {
        return _companyListeners.removeListener(listener);
    }

    @Override
    public boolean addDepartmentListener(IDepartmentListener listener) {
        return _departmentListeners.addListener(listener);
    }

    @Override
    public boolean removeDepartmentListener(IDepartmentListener listener) {
        return _departmentListeners.removeListener(listener);
    }

    @Override
    public boolean addMarketPlaceListener(IMarketPlaceListener listener) {
        return _marketPlaceListeners.addListener(listener);
    }

    @Override
    public boolean removeMarketPlaceListener(IMarketPlaceListener listener) {
        return _marketPlaceListeners.removeListener(listener);
    }

    @Override
    public void removeListeners() {
        _clientGameListeners.clearListeners();
        _gameModelListeners.clearListeners();
        _marketPlaceListeners.clearListeners();
        _departmentListeners.clearListeners();
        _companyListeners.clearListeners();
    }

    @Override
    public boolean startGame() {
        return _client.send(new StartGameRequest());
    }

    @Override
    public AGamesServer<IMultiPlayerGamesServerListener> getGamesServer() {
        return _server;
    }

    @Override
    public boolean stopGame() {
        return false;
    }

    @Override
    public GameModelModel getMemento() {
        return _model;
    }

    @Override
    public boolean setGameSpeed(int newSpeed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return _model.getGameName();
    }
}
