/**
 *
 */
package org.promasi.protocol.messages;

/**
 * @author m1cRo
 *
 */
public class GameCanceledRequest extends Message {

    @Override
    public void process(IMessageProcessor processor) {
        processor.process(this);
    }
}
