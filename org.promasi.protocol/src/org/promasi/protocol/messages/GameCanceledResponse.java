/**
 *
 */
package org.promasi.protocol.messages;

/**
 * @author m1cRo
 *
 */
public class GameCanceledResponse extends Message {

    @Override
    public void process(IMessageProcessor processor) {
        processor.process(this);
    }
}
