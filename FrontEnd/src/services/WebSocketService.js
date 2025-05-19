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
        this.client = new Client({
            webSocketFactory: () => new SockJS(this.url),
            debug: (str) => {
                console.log(str);
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        });

        this.client.onConnect = () => {
            console.log('Connected to WebSocket');
            this.subscribeToTopics();
        };

        this.client.onStompError = (frame) => {
            console.error('WebSocket error: ', frame);
            if (this.onErrorCallback) {
                this.onErrorCallback(frame);
            }
        };

        this.client.activate();
    }

    subscribeToTopics() {
        // Subscribe to logs
        this.subscriptions['logs'] = this.client.subscribe('/topic/logs', (message) => {
            this.onMessageCallback('logs', JSON.parse(message.body));
        });

        // Subscribe to ticket availability
        this.subscriptions['ticket-availability'] = this.client.subscribe('/topic/ticket-availability', (message) => {
            this.onMessageCallback('ticket-availability', JSON.parse(message.body));
        });
    }

    disconnect() {
        if (this.client) {
            Object.values(this.subscriptions).forEach(sub => sub.unsubscribe());
            this.client.deactivate();
            console.log('WebSocket disconnected');
        }
    }
}

export default WebSocketService;