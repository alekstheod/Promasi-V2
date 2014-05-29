/**
 *
 */
package org.promasi.protocol.client;

import org.promasi.protocol.messages.Message;

/**
 * @author m1cRo
 *
 */
public interface IPromasiClientListener {

    /**
     *
     * @param client
     * @param message
     */
    public void onReceive(ProMaSiClient client, Message message);

    /**
     *
     * @param client
     */
    public void onDisconnect(ProMaSiClient client);

    /**
     *
     * @param client
     */
    public void onConnect(ProMaSiClient client);

    /**
     *
     * @param client
     */
    public void onConnectionError(ProMaSiClient client);
}
