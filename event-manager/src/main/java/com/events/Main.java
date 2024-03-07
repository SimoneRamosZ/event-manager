package com.events;

import com.events.controllers.EventController;
import com.events.repositories.EventRepository;
import com.events.models.Event;
import com.events.views.EventView;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.events.repositories.EventRepository.createTable;

public class Main {
    public static void main(String[] args) throws IOException {
        EventView eventView = new EventView();
        EventRepository eventRepository = new EventRepository();
        EventController eventController = new EventController(eventView, eventRepository);

        if (!eventRepository.tableExists()) {
            createTable();
        }

        System.out.println("Escolha uma opção:");
        System.out.println("1 - Exibir eventos");
        System.out.println("2 - Cadastrar novo evento");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                eventController.displayEvents();
                System.out.println("Deseja cadastrar um novo evento? Se sim digite 2 ou 0 para sair");
                int choiceTwo = scanner.nextInt();
                scanner.nextLine();

                if(choiceTwo == 2)
                {
                    registerNewEvent(eventController, scanner);
                } else if (choiceTwo == 0) {
                    break;
                }
                break;
            case 2:
                registerNewEvent(eventController, scanner);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void registerNewEvent(EventController eventController, Scanner scanner) {
        System.out.println("Digite o nome do evento");
        String eventName = scanner.nextLine();

        System.out.println("Digite a descrição do evento");
        String eventDescription = scanner.nextLine();

        System.out.println("Digite o endereço do evento");
        String eventAddress = scanner.nextLine();

        System.out.println("Digite a categoria do evento");
        String eventCategory = scanner.nextLine();

        System.out.println("Digite a data e hora do evento (no formato yyyy-MM-dd HH:mm):");
        String dateString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime eventDateTime =  LocalDateTime.now();
        try {
           eventDateTime = LocalDateTime.parse(dateString, formatter);
        } catch (Exception e) {
            System.out.println("Formato de data e hora inválido. Certifique-se de que esteja no formato yyyy-MM-dd HH:mm.");
        }

        Event event = new Event(eventName, eventDescription, eventAddress, eventCategory, eventDateTime);
        eventController.addEvent(event);
        System.out.println("cadastro realizado com sucesso");
    }
}
