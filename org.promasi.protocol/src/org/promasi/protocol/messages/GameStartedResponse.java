package org.promasi.protocol.messages;

public class GameStartedResponse extends Message {

    @Override
    public void process(IMessageProcessor processor) {
        processor.process(this);
    }
}
