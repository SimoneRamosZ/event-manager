package com.events.views;

import com.events.models.Event;

public class EventView {
    public void displayEventDetails(Event event) {
        System.out.println("Nome do Evento: " + event.getName());
        System.out.println("Endereço: " + event.getAddress());
        System.out.println("Categoria: " + event.getCategory());
        System.out.println("Data e Hora: " + event.getDateTime());
        System.out.println("Descrição: " + event.getDescription());
        System.out.println();
    }
}
