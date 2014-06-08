/**
 *
 */
package org.promasi.server.clientstate;

import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.promasi.game.model.generated.CompanyModel;
import org.promasi.game.model.generated.EmployeeModel;
import org.promasi.game.model.generated.EmployeeTaskModel;
import org.promasi.game.model.generated.GameModelModel;
import org.promasi.game.model.generated.MarketPlaceModel;
import org.promasi.game.model.generated.ProjectModel;
import org.promasi.game.multiplayer.IMultiPlayerGame;
import org.promasi.game.multiplayer.IServerGameListener;
import org.promasi.game.multiplayer.MultiPlayerGame;
import org.promasi.network.tcp.NetworkException;
import org.promasi.protocol.client.IPromasiClientListener;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.messages.CancelGameRequest;
import org.promasi.protocol.messages.GameStartedRequest;
import org.promasi.protocol.messages.InternalErrorResponse;
import org.promasi.protocol.messages.Message;
import org.promasi.protocol.messages.MessageRequest;
import org.promasi.protocol.messages.StartGameRequest;
import org.promasi.protocol.messages.UpdateGamePlayersListRequest;
import org.promasi.protocol.messages.WrongProtocolResponse;
import org.promasi.server.ProMaSiServer;
import org.promasi.utilities.exceptions.NullArgumentException;

/**
 * @author m1cRo
 *
 */
public class WaitingPlayersClientState implements IServerGameListener, IPromasiClientListener {

    /**
     *
     */
    private MultiPlayerGame _game;

    /**
     *
     */
    private String _clientId;

    /**
     *
     */
    private ProMaSiClient _client;

    /**
     *
     */
    private ProMaSiServer _server;

    /**
     *
     */
    private String _gameId;

    /**
     *
     * @param clientId
     * @param gameId
     * @param client
     * @param game
     * @param server
     * @throws NullArgumentException
     */
    public WaitingPlayersClientState(String clientId, String gameId, ProMaSiClient client, MultiPlayerGame game, ProMaSiServer server) throws NullArgumentException {
        if (clientId == null ||gameId == null || game == null || client == null || server == null ) {
            throw new NullArgumentException("Invalid argument");
        }

        _game = game;
        _server = server;
        _game.addListener(this);
        _clientId = clientId;
        _client = client;
        _gameId = gameId;
    }

    /* (non-Javadoc)
     * @see org.promasi.playmode.multiplayer.IClientState#onReceive(org.promasi.playmode.multiplayer.ProMaSiClient, java.lang.String)
     */
    @Override
    public void onReceive(ProMaSiClient client, Message object) {
        try {
            if (object instanceof StartGameRequest) {
                _server.startGame(_clientId);
            } else if (object instanceof MessageRequest) {
                MessageRequest request = (MessageRequest) object;
                _game.sendMessage(_clientId, request.getMessage());
            } else if (object instanceof CancelGameRequest) {
                _server.cancelGame(_gameId);
                client.removeListener(this);
                client.addListener(new ChooseGameClientState(_server, client, _clientId));
            } else {
                client.send(new WrongProtocolResponse());
                client.disconnect();
            }
        } catch (NetworkException | IllegalArgumentException e) {
            client.send(new InternalErrorResponse());
            client.disconnect();
        }
    }

    @Override
    public void gameStarted(String clientId, IMultiPlayerGame game, GameModelModel gameModel, DateTime dateTime) {
        if (_clientId.equals(clientId)) {
            try {
                _game.removeListener(this);
                _client.removeListener(this);
                _client.addListener(new PlayingGameClientState(_server, _client, _clientId, _game));
            } catch (NetworkException e) {
                //Logger
            }

            _client.send(new GameStartedRequest(gameModel, dateTime.toString()));
        }
    }

    @Override
    public void projectAssigned(String clientId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel project,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void projectFinished(String clientId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel project,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeHired(String clientId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeDischarged(String clientId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeTasksAssigned(String clientId, IMultiPlayerGame game,
            CompanyModel company, EmployeeModel employee,
            List<EmployeeTaskModel> employeeTasks, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeTaskDetached(String clientId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee,
            EmployeeTaskModel employeeTask, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void companyIsInsolvent(String clientId, IMultiPlayerGame game,
            CompanyModel company, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void onExecuteWorkingStep(String clientId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel assignedProject,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void onTick(String clientId, IMultiPlayerGame game, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void messageSent(String clientId, IMultiPlayerGame game, String message) {
        MessageRequest request = new MessageRequest(clientId, message);
        _client.send(request);
    }

    @Override
    public void onDisconnect(ProMaSiClient client) {
        _game.leaveGame(_clientId);
        _client.removeListener(this);
    }

    @Override
    public void onConnect(ProMaSiClient client) {
    }

    @Override
    public void onConnectionError(ProMaSiClient client) {
        _game.leaveGame(_clientId);
        _client.removeListener(this);
    }

    @Override
    public void playersListUpdated(IMultiPlayerGame game, List<String> gamePlayers) {
        _client.send(new UpdateGamePlayersListRequest(gamePlayers));

    }

    @Override
    public void gameFinished(Map<String, GameModelModel> gameModels) {
		// TODO Auto-generated method stub

    }

    @Override
    public void onExecuteStep(String playerId, IMultiPlayerGame game,
            CompanyModel company, DateTime dateTime) {
		// TODO Auto-generated method stub

    }
}
