/**
 * 
 */
package org.promasi.protocol.messages;

/**
 * @author m1cRo
 *
 */
public class CancelGameResponse extends Message {
    @Override
    public void process(IMessageProcessor processor) {
       processor.process(this);
    }
}
