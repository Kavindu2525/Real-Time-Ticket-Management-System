/*
package com.TicketManagement.Ticket.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

    @Service
    public class TicketEventPublisher {

        private final List<Consumer<String>> listeners = new ArrayList<>();

        public void subscribe(Consumer<String> listener) {
            listeners.add(listener);
        }

        public void publish(String message) {
            for (Consumer<String> listener : listeners) {
                listener.accept(message);
            }
        }
    }


*/
