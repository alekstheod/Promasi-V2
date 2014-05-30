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
import org.promasi.game.company.ICompanyListener;
import org.promasi.game.company.IDepartmentListener;
import org.promasi.game.company.IMarketPlaceListener;
import org.promasi.game.model.generated.EmployeeTaskModel;
import org.promasi.game.model.generated.GameModelModel;
import org.promasi.game.singleplayer.IClientGameListener;
import org.promasi.protocol.client.ProMaSiClient;

/**
 *
 * @author alekstheod
 */
public class MultiPlayerGame implements IGame {

    private GameModelModel _model;
    private ProMaSiClient _client;
    
    public MultiPlayerGame(GameModelModel model, ProMaSiClient client )throws GameException{
        if(model == null || client == null ){
            throw new GameException("Invalid argument passed");
        }
        
        _model = model;
        _client = client;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dischargeEmployee(String employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean assignTasks(Map<String, List<EmployeeTaskModel>> employeeTasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeTasks(List<EmployeeTaskModel> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addListener(IClientGameListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeListener(IClientGameListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addCompanyListener(ICompanyListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeCompanyListener(ICompanyListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addDepartmentListener(IDepartmentListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeDepartmentListener(IDepartmentListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addMarketPlaceListener(IMarketPlaceListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeMarketPlaceListener(IMarketPlaceListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean startGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AGamesServer getGamesServer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean stopGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String toString(){
        return _model.getGameName();
    }
}
