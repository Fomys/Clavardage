package gui;

import gui.events.Event;

public interface Panel {
    void propagate_event(Event event);

    void converge_event(Event event);
}
