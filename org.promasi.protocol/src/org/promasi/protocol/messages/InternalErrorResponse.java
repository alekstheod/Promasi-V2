package org.promasi.protocol.messages;

public class InternalErrorResponse extends Message {
    @Override
    public void process(IMessageProcessor processor) {
       processor.process(this);
    }
}
