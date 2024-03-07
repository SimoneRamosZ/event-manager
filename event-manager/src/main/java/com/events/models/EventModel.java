package com.events.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventModel implements Serializable {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
