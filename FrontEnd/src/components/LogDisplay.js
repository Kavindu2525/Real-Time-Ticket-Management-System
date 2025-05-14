import React, { useState, useEffect } from 'react';
import WebSocketService from '../services/WebSocketService';
import { WEBSOCKET_URL } from '../config';

const LogDisplay = () => {
    const [logs, setLogs] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const handleMessage = (type, message) => {
            if (type === 'logs') {
                setLogs(prevLogs => [...prevLogs, message]);
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
            <h2>System Logs</h2>
            {error && <div className="error">{error}</div>}
            <div>
                {logs.map((log, index) => (
                    <div key={index}>{log}</div>
                ))}
            </div>
        </div>
    );
};

export default LogDisplay;
