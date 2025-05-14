package com.TicketManagement.Ticket.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public LogService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendLog(String message) {
        simpMessagingTemplate.convertAndSend("/topic/logs", message);
    }
}
