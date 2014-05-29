package org.promasi.protocol.messages;

public class LoginFailedResponse extends Message {
    @Override
    public void process(IMessageProcessor processor) {
       processor.process(this);
    }
}
