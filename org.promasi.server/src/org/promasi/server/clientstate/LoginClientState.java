package org.promasi.server.clientstate;

import org.promasi.network.tcp.NetworkException;
import org.promasi.protocol.client.IPromasiClientListener;
import org.promasi.protocol.client.ProMaSiClient;
import org.promasi.protocol.messages.InternalErrorResponse;
import org.promasi.protocol.messages.LoginFailedResponse;
import org.promasi.protocol.messages.LoginRequest;
import org.promasi.protocol.messages.LoginResponse;
import org.promasi.protocol.messages.Message;
import org.promasi.server.ProMaSiServer;
import org.promasi.utilities.logger.ILogger;
import org.promasi.utilities.logger.LoggerFactory;

/**
 *
 * @author m1cRo Represent the Login State. In this state user should insert his
 * credentials in order to income to the ProMaSi system.
 */
public class LoginClientState implements IPromasiClientListener {

    /**
     * Instance of {@link ProMaSiServer}
     */
    private ProMaSiServer _server;

    /**
     * Instance of {@link ProMaSiClient}.
     */
    private ProMaSiClient _client;

    /**
     * Instance of {@link ILogger} interface implementation, need for logging.
     */
    private final ILogger _logger = LoggerFactory.getInstance(ChooseGameClientState.class);

    /**
     * Constructor will initialize the object
     *
     * @param server instance of {@link ProMaSiServer} which is responsible on
     * the given client handling.
     * @param client the client.
     * @throws NetworkException In case of invalid arguments or internal errors.
     */
    public LoginClientState(ProMaSiServer server, ProMaSiClient client) throws NetworkException {
        if (server == null) {
            _logger.error("Initialization failed because a wrong argument server == null");
            throw new NetworkException("Wrong argument server==null");
        }

        if (client == null) {
            _logger.error("Initialization failed because a wrong argument client == null");
            throw new NetworkException("Wrong argument client==null");
        }

        _client = client;
        _server = server;
        _logger.debug("Initialization initializatin complete");
    }

    /**
     * Only the LoginRequest message is accepted. Any other type of message will
     * be ignored.
     *
     * @param object
     */
    @Override
    public void onReceive(ProMaSiClient client, Message object) {
        try {
            if (object instanceof LoginRequest) {
                _logger.info("Received message :'" + LoginRequest.class.toString() + "'");
                LoginRequest request = (LoginRequest) object;
                if (_server.login(request.getClientId(), client)) {
                    LoginResponse response = new LoginResponse(request.getClientId(), _server.getAvailableGames());
                    client.removeListener(this);
                    client.addListener(new ChooseGameClientState(_server, client, request.getClientId()));
                    client.send(response);
                    _logger.info("Login succeed");
                } else {
                    _logger.info("Login failed for client id '" + request.getClientId() + "'");
                    client.send(new LoginFailedResponse());
                }
            }
        } catch (NetworkException e) {
            client.send(new InternalErrorResponse());
            client.disconnect();
        }
    }

    @Override
    public void onDisconnect(ProMaSiClient client) {
        _client.removeListener(this);
        _logger.debug("Client disconnected");
    }

    @Override
    public void onConnect(ProMaSiClient client) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConnectionError(ProMaSiClient client) {
        _client.removeListener(this);
        _logger.debug("Client connectino error");

    }
}
