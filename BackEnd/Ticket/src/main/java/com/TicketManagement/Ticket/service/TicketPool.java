package com.TicketManagement.Ticket.service;

import com.TicketManagement.Ticket.model.Ticket;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TicketPool {
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);
    private final Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final int maxCapacity;
    private volatile boolean isSystemRunning = false;


    public TicketPool(@Value("${ticket.pool.max-capacity:1000}") int maxCapacity) {
        this.maxCapacity = maxCapacity;

    }
    public void startSystem() {
        lock.lock();
        try {
            isSystemRunning = true;
            System.out.println("Ticket management system started");

        } finally {
            lock.unlock();
        }
    }

    public void stopSystem() {
        lock.lock();
        try {
            isSystemRunning = false;
            tickets.clear(); // Clear all tickets when system stops
            System.out.println("Ticket management system stopped");
        } finally {
            lock.unlock();
        }
    }

    public boolean addTicket(Ticket ticket) {
        if (!isSystemRunning) {
            System.out.println("Cannot add ticket: System is not running");
            return false;
        }

        lock.lock();
        try {
            if (tickets.size() < maxCapacity) {
                tickets.add(ticket);
                return true;
            }
            return false; // Pool is full
        } finally {
            lock.unlock();
        }
    }

    public Ticket removeTicket() {
        if (!isSystemRunning) {
            System.out.println("Cannot remove ticket: System is not running");
            return null;
        }

        lock.lock();
        try {
            Ticket ticket = tickets.poll(); // Retrieve and remove the ticket
            if (ticket == null) {
                System.out.println("No tickets available to remove.");
                return null;
            }
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentTicketCount() {
        return tickets.size();
    }
    public boolean isSystemRunning() {
        return isSystemRunning;
    }
}
