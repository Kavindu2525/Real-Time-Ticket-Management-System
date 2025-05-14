package com.TicketManagement.Ticket.service;

import com.TicketManagement.Ticket.model.Ticket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VendorService {

    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);


    @Async
    public void releaseTickets(TicketPool ticketPool, int releaseRate, int totalTickets) {
        System.out.println("Vendor thread starting...");  // Debug
        for (int i = 0; i < totalTickets; i++) {
            if (!ticketPool.isSystemRunning() || Thread.currentThread().isInterrupted()) {
                break;
            }
            Ticket ticket = new Ticket(i + 1, BigDecimal.valueOf(1000), "pandama");
            ticketPool.addTicket(ticket);
            System.out.println("Vendor-" + Thread.currentThread().getName() + ": Ticket added: " + ticket +
                    " Current Size is " + ticketPool.getCurrentTicketCount());
            try {
                Thread.sleep(releaseRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


}
