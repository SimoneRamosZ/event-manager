package com.events.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private int id;
    private String name;
    private String description;
    private String address;
    private String category;
    private LocalDateTime dateTime;

    public Event(String name, String description, String address, String category, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return  name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

