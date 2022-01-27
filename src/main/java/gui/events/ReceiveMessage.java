package gui.events;

import database.Message;

public class ReceiveMessage implements Event {
    private final Message message;

    public ReceiveMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }
}
