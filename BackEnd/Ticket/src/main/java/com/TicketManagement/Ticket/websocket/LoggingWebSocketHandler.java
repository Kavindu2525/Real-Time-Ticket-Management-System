package com.TicketManagement.Ticket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class LoggingWebSocketHandler implements WebSocketHandler {

private static final Logger logger = LoggerFactory.getLogger(LoggingWebSocketHandler.class);
private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

@Override
public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    logger.info("New WebSocket connection established: {}", session.getId());
    sessions.add(session);
}

@Override
public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    logger.info("Received message: {}", message.getPayload());
}

@Override
public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    logger.error("WebSocket transport error: {}", exception.getMessage());
    sessions.remove(session);
}

@Override
public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    logger.info("WebSocket connection closed: {}, Status: {}", session.getId(), closeStatus);
    sessions.remove(session);
}

@Override
public boolean supportsPartialMessages() {
    return false;
}

public void broadcastMessage(String message) {
    for (WebSocketSession session : sessions) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
                logger.info("Broadcast message: {}", message);
            }
        } catch (IOException e) {
            logger.error("Error broadcasting message", e);
        }
    }
}
public void broadcastTicketCount(int count) {
    for (WebSocketSession session : sessions) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(String.valueOf(count)));
                logger.info("Broadcast TicketCount: {}", count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}