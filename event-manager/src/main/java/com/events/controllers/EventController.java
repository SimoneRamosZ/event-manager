package com.events.controllers;

import com.events.repositories.EventRepository;
import com.events.models.Event;
import com.events.views.EventView;
import java.util.List;

public class EventController {
    private EventView eventView;
    private EventRepository eventRepository;

    public EventController(EventView eventView, EventRepository eventRepository) {
        this.eventView = eventView;
        this.eventRepository = eventRepository;
    }

    public void addEvent(Event event) {
        eventRepository.insertEvent(event);
    }

    public void displayEvents() {
        List<Event> events = eventRepository.getAllEvents();
        for (Event event : events) {
           eventView.displayEventDetails(event);
        }
    }
}
