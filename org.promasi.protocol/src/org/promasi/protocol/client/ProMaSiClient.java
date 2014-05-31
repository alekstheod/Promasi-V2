/**
 *
 */
package org.promasi.protocol.client;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.codec.binary.Base64;
import org.promasi.network.tcp.ITcpClientListener;
import org.promasi.network.tcp.NetworkException;
import org.promasi.network.tcp.TcpClient;
import org.promasi.protocol.compression.CompressionException;
import org.promasi.protocol.compression.ICompression;
import org.promasi.protocol.messages.Message;
import org.promasi.protocol.messages.WrongProtocolResponse;
import org.promasi.utilities.design.Observer;
import org.promasi.utilities.logger.ILogger;
import org.promasi.utilities.logger.LoggerFactory;

/**
 * @author m1cRo Represent the network client on ProMaSi system. This class will
 * communicate with the ProMaSi server, also this class provide the state
 * machine engine so user can change the states of the client in order to
 * receive messages.
 */
public class ProMaSiClient extends Observer<IPromasiClientListener> implements ITcpClientListener {

    /**
     * TCP communication port for current user.
     */
    private TcpClient _client;

    /**
     * @brief instance of logger
     */
    private final ILogger _logger = LoggerFactory.getInstance(ProMaSiClient.class);

    /**
     * Instance of {@link ICompression} interface implementation which provides
     * the compression algorithm in order to compress and decompress the
     * messages.
     */
    private ICompression _compression;

    /**
     *
     */
    private Lock _lockObject;

    /**
     *
     * @param clientId
     * @param client
     * @param compression
     * @throws NetworkException
     */
    public ProMaSiClient(TcpClient client, ICompression compression) throws NetworkException {
        if (client == null) {
            throw new NetworkException("Wrong client argument");
        }

        _client = client;
        _client.addListener(this);
        _lockObject = new ReentrantLock();
        _compression = compression;
    }
    
    private String convert(Message msg) {
        String result = "";
        if (msg != null) {
            try {
                JAXBContext jc = JAXBContext.newInstance(Message.class);
                Marshaller m = jc.createMarshaller();
                StringWriter sw = new StringWriter();
                m.marshal(msg, sw);
                String message = sw.toString();
                byte[] outputMessage = message.getBytes();
                if (_compression != null) {
                    outputMessage = _compression.compress(message.getBytes());
                }

                Base64 base64 = new Base64();
                byte data[] = base64.encode(outputMessage);
                String temp = new String(data);
                StringBuilder builder = new StringBuilder();
                builder.append(temp.replaceAll("\n", "").replaceAll("\r", ""));
                builder.append("\r\n");
                result = builder.toString();
            } catch (JAXBException | CompressionException ex) {
                _logger.error("Request packing error with message : " + ex.getMessage() );
            }
        }

        return result;
    }

    private Message convert(String msg) {
        Message message;
        try {
            Base64 base64 = new Base64();
            byte[] messageByte = base64.decode(msg.getBytes());

            final String recData;
            if (_compression != null) {
                recData = new String(_compression.deCompress(messageByte));
            } else {
                recData = new String(messageByte);
            }

            JAXBContext jc = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Object object = unmarshaller.unmarshal(new ByteArrayInputStream(recData.getBytes()));
            if (object instanceof Message) {
                message = (Message) object;
            } else {
                _logger.error("Unpack message failed");
                message = new WrongProtocolResponse();
            }
        } catch (CompressionException | JAXBException e) {
            _logger.error("Unpack message failed with message: " + e.getMessage());
            message = new WrongProtocolResponse();
        }

        return message;
    }

    /**
     *
     * @param message
     * @return true if message was sent, false otherwise.
     */
    public boolean send(Message message) {
        boolean result = false;

        try {
            _lockObject.lock();
            result = _client.send(convert(message));
        } finally {
            _lockObject.unlock();
        }

        return result;
    }

    public Message sendRecv(Message message) {
        final Message msg;
        try {
            _lockObject.lock();
            msg = convert(_client.sendRecv(convert(message)));
        } finally {
            _lockObject.unlock();
        }

        return msg;
    }

    /**
     * This method will close the connection with current user and will
     * terminate the receive thread.
     *
     * @return true if the connection was successfully closed, false otherwise.
     */
    public boolean disconnect() {
        boolean result = false;

        try {
            _lockObject.lock();
            result = _client.disconnect();
            List< IPromasiClientListener> listeners = getListeners();
            for (IPromasiClientListener listener : listeners) {
                listener.onDisconnect(this);
            }
        } finally {
            _lockObject.unlock();
        }

        return result;
    }

    @Override
    public void onReceive(String line) {
        try {
            _lockObject.lock();
            Message message = convert(line);
            List< IPromasiClientListener> listeners = getListeners();
            for (IPromasiClientListener listener : listeners) {
                listener.onReceive(this, message);
            }
        } finally {
            _lockObject.unlock();
        }
    }

    @Override
    public void onConnect() {
        try {
            _lockObject.lock();
            List< IPromasiClientListener> listeners = getListeners();
            for (IPromasiClientListener listener : listeners) {
                listener.onConnect(this);
            }
        } finally {
            _lockObject.unlock();
        }

    }

    @Override
    public void onDisconnect() {
        try {
            _lockObject.lock();
            List< IPromasiClientListener> listeners = getListeners();
            for (IPromasiClientListener listener : listeners) {
                listener.onDisconnect(this);
            }
        } finally {
            _lockObject.unlock();
        }
    }

    @Override
    public void onConnectionError() {
        try {
            _lockObject.lock();
            List< IPromasiClientListener> listeners = getListeners();
            for (IPromasiClientListener listener : listeners) {
                listener.onConnectionError(this);
            }
        } finally {
            _lockObject.unlock();
        }
    }

    /**
     *
     * @param client
     * @return
     */
    public boolean equals(TcpClient client) {
        boolean result = false;

        try {
            _lockObject.lock();
            result = (client == _client);
        } finally {
            _lockObject.unlock();
        }

        return result;
    }

}
