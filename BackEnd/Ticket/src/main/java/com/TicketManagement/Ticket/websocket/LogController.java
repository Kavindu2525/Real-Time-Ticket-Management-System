
package com.TicketManagement.Ticket.websocket;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LogController {
    private final SimpMessagingTemplate messagingTemplate;

    public LogController(SimpMessagingTemplate simpMessagingTemplate) {
        this.messagingTemplate = simpMessagingTemplate;
    }

    // Broadcasting logs to all WebSocket clients
    public void sendLog(String logMessage) {
        messagingTemplate.convertAndSend("/topic/logs", logMessage);
    }

    // Broadcasting ticket availability
    public void sendTicketAvailability(int availableTickets) {
        messagingTemplate.convertAndSend("/topic/ticket-availability", availableTickets);
        System.out.println("Broadcasted ticket availability: " + availableTickets);
    }

}

