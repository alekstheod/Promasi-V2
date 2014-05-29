package org.promasi.protocol.messages;

public class CreateGameFailedResponse extends Message {
    @Override
    public void process(IMessageProcessor processor) {
       processor.process(this);
    }
}
