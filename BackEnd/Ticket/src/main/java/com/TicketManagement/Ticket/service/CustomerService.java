package com.TicketManagement.Ticket.service;

import com.TicketManagement.Ticket.model.Ticket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Async
    public void purchaseTickets(TicketPool ticketPool, int retrievalRate, int quantity) {
        System.out.println("Customer thread starting...");  // Debug
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.removeTicket();
            if (ticket == null) {
                System.out.println("No more tickets available");
                break;
            }
            System.out.println("Customer-" + Thread.currentThread().getName() + " Retrieved " + ticket +
                    " Current pool size: " + ticketPool.getCurrentTicketCount());
            try {
                Thread.sleep(retrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


}






