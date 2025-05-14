package com.TicketManagement.Ticket.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

public interface TicketAvailabilityWebSocket {
    void afterConnectionEstablished(WebSocketSession session);

    void afterConnectionClosed(WebSocketSession session, CloseStatus status);
}
