import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

class WebSocketService {
    constructor(url, onMessageCallback, onErrorCallback) {
        this.url = url;
        this.onMessageCallback = onMessageCallback;
        this.onErrorCallback = onErrorCallback;
        this.client = null;
        this.subscriptions = {};
    }

    connect() {
        try {
            this.client = new Client({
                webSocketFactory: () => new SockJS(this.url),
                debug: (str) => {
                    console.log(str);
                },
                reconnectDelay: 5000,
                heartbeatIncoming: 4000,
                heartbeatOutgoing: 4000,
                onStompError: (frame) => {
                    console.error('STOMP error:', frame);
                    if (this.onErrorCallback) {
                        this.onErrorCallback(frame);
                    }
                }
            });

            this.client.onConnect = () => {
                console.log('Connected to WebSocket');
                this.subscribeToTopics();
            };

            this.client.activate();
        } catch (error) {
            console.error('WebSocket connection error:', error);
            if (this.onErrorCallback) {
                this.onErrorCallback(error);
            }
        }
    }

    subscribeToTopics() {
        try {
            // Subscribe to logs
            this.subscriptions['logs'] = this.client.subscribe('/topic/logs', (message) => {
                const data = message.body ? JSON.parse(message.body) : null;
                this.onMessageCallback('logs', data);
            });

            // Subscribe to ticket availability
            this.subscriptions['ticket-availability'] = this.client.subscribe('/topic/ticket-availability', (message) => {
                const data = message.body ? JSON.parse(message.body) : null;
                this.onMessageCallback('ticket-availability', data);
            });
        } catch (error) {
            console.error('Error subscribing to topics:', error);
            if (this.onErrorCallback) {
                this.onErrorCallback(error);
            }
        }
    }

    disconnect() {
        if (this.client) {
            Object.values(this.subscriptions).forEach(sub => {
                try {
                    sub.unsubscribe();
                } catch (error) {
                    console.error('Error unsubscribing:', error);
                }
            });
            this.client.deactivate();
            console.log('WebSocket disconnected');
        }
    }
}

export default WebSocketService;