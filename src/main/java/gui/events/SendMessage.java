package gui.events;

import database.Message;

import java.util.UUID;

public class SendMessage implements Event {
    private final Message message;
    private final UUID to;

    public SendMessage(Message message, UUID to) {
        this.message = message;
        this.to = to;
    }

    public Message getMessage() {
        return this.message;
    }

    public UUID getTo() {
        return this.to;
    }
}
