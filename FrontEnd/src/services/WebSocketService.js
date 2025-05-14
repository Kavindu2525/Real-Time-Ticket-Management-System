import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

class WebSocketService {
    constructor(url, onMessageCallback, onErrorCallback) {
        this.url = url;
        this.onMessageCallback = onMessageCallback;
        this.onErrorCallback = onErrorCallback;
        this.stompClient = null;
        this.subscriptions = {};
    }

    connect() {
        const socket = new SockJS(this.url);
        this.stompClient = Stomp.over(socket);
        this.stompClient.debug = null; // Disable debug logs

        this.stompClient.connect({}, (frame) => {
            console.log('WebSocket connected: ', frame);
            this.subscribeToTopics();
        }, (error) => {
            console.error('WebSocket error: ', error);
            if (this.onErrorCallback) {
                this.onErrorCallback(error);
            }
        });
    }

    subscribeToTopics() {
        // Subscribe to logs
        this.subscriptions['logs'] = this.stompClient.subscribe('/topic/logs', (message) => {
            this.onMessageCallback('logs', JSON.parse(message.body));
        });

        // Subscribe to ticket availability
        this.subscriptions['ticket-availability'] = this.stompClient.subscribe('/topic/ticket-availability', (message) => {
            this.onMessageCallback('ticket-availability', JSON.parse(message.body));
        });
    }

    disconnect() {
        if (this.stompClient) {
            Object.values(this.subscriptions).forEach(sub => sub.unsubscribe());
            this.stompClient.disconnect();
            console.log('WebSocket disconnected');
        }
    }
}

export default WebSocketService;
