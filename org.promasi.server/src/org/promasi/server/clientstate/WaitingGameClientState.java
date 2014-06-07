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
import org.promasi.protocol.messages.GameCanceledResponse;
import org.promasi.protocol.messages.GameStartedRequest;
import org.promasi.protocol.messages.GameStartedResponse;
import org.promasi.protocol.messages.InternalErrorResponse;
import org.promasi.protocol.messages.LeaveGameRequest;
import org.promasi.protocol.messages.Message;
import org.promasi.protocol.messages.MessageRequest;
import org.promasi.protocol.messages.UpdateGamePlayersListRequest;
import org.promasi.protocol.messages.WrongProtocolResponse;
import org.promasi.server.ProMaSiServer;
import org.promasi.utilities.exceptions.NullArgumentException;

/**
 * @author m1cRo
 *
 */
public class WaitingGameClientState implements IServerGameListener, IPromasiClientListener {

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
    private String _gameId;

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
     * @param clientId
     * @param server
     * @param client
     * @param gameId
     * @param game
     * @throws NullArgumentException
     */
    public WaitingGameClientState(String clientId, ProMaSiServer server, ProMaSiClient client, String gameId, MultiPlayerGame game) throws NullArgumentException {
        if (game == null) {
            throw new NullArgumentException("Wrong argument game==null");
        }

        if (clientId == null) {
            throw new NullArgumentException("Wrong argument clientId==null");
        }

        if (client == null) {
            throw new NullArgumentException("Wrong argument cleint==null");
        }

        if (server == null) {
            throw new NullArgumentException("Wrong argument server==null");
        }

        if (gameId == null) {
            throw new NullArgumentException("Wrong argument gameId==null");
        }

        _gameId = gameId;
        _server = server;
        _client = client;
        _game = game;
        _game.addListener(this);
        _clientId = clientId;
        _client.addListener(this);
    }

    /**
     *
     * @param client
     * @param object
     */
    @Override
    public void onReceive(ProMaSiClient client, Message object) {
        try {
            if (object instanceof GameStartedResponse) {

            } else if (object instanceof MessageRequest) {
                MessageRequest request = (MessageRequest) object;
                _game.sendMessage(_clientId, request.getMessage());
            } else if (object instanceof GameCanceledResponse) {
                client.removeListener(this);
                client.addListener(new ChooseGameClientState(_server, client, _clientId));
            } else if (object instanceof LeaveGameRequest) {
                _server.leaveGame(_gameId, _clientId);
                client.removeListener(this);
                client.addListener(new ChooseGameClientState(_server, client, _clientId));
            } else {
                client.send(new WrongProtocolResponse());
            }
        } catch (NetworkException | IllegalArgumentException e) {
            client.send(new InternalErrorResponse());
            client.disconnect();
        }

    }

    @Override
    public void gameStarted(String playerId, IMultiPlayerGame game,
            GameModelModel gameModel, DateTime dateTime) {
        if (playerId.equals(_clientId)) {
            try {
                _game.removeListener(this);
                _client.removeListener(this);
                _client.addListener(new PlayingGameClientState(_server, _client, _clientId, _game));
            } catch (NetworkException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            _client.send(new GameStartedRequest(gameModel, dateTime.toString()));
        }
    }

    @Override
    public void projectAssigned(String playerId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel project,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void projectFinished(String playerId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel project,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeHired(String playerId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeDischarged(String playerId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeTasksAssigned(String playerId, IMultiPlayerGame game,
            CompanyModel company, EmployeeModel employee,
            List<EmployeeTaskModel> employeeTasks, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void employeeTaskDetached(String playerId, IMultiPlayerGame game,
            MarketPlaceModel marketPlace, CompanyModel company,
            EmployeeModel employee,
            EmployeeTaskModel employeeTask, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void companyIsInsolvent(String playerId, IMultiPlayerGame game,
            CompanyModel company, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void onExecuteWorkingStep(String playerId, IMultiPlayerGame game,
            CompanyModel company, ProjectModel assignedProject,
            DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void onTick(String playerId, IMultiPlayerGame game, DateTime dateTime) {
		// TODO Auto-generated method stub

    }

    @Override
    public void messageSent(String playerId, IMultiPlayerGame game, String message) {
        MessageRequest request = new MessageRequest(playerId, message);
        _client.send(request);
    }

    @Override
    public void onDisconnect(ProMaSiClient client) {
        _game.leaveGame(_clientId);
        _client.removeListener(this);
    }

    @Override
    public void onConnect(ProMaSiClient client) {
		// TODO Auto-generated method stub

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
