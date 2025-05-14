import React, { useState, useEffect } from 'react';
import WebSocketService from '../services/WebSocketService';
import { WEBSOCKET_URL } from '../config';

const TicketAvailabilityDisplay = () => {
    const [availableTickets, setAvailableTickets] = useState(0);
    const [error, setError] = useState(null);

    useEffect(() => {
        const handleMessage = (type, message) => {
            if (type === 'ticket-availability') {
                setAvailableTickets(message);
            }
        };

        const handleError = (error) => {
            setError("WebSocket connection error.");
            console.error(error);
        };

        const webSocketService = new WebSocketService(WEBSOCKET_URL, handleMessage, handleError);
        webSocketService.connect();

        return () => {
            webSocketService.disconnect();
        };
    }, []);

    return (
        <div>
            <h2>Ticket Availability</h2>
            {error && <div className="error">{error}</div>}
            <div>{availableTickets} tickets available</div>
        </div>
    );
};

export default TicketAvailabilityDisplay;
