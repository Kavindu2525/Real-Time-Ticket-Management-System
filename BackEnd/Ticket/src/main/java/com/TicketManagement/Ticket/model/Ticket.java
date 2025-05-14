package com.TicketManagement.Ticket.model;

import java.math.BigDecimal;

public class Ticket {
    private int ticketID;
    private BigDecimal ticketPrice;
    private String eventName;

    public Ticket(int ticketID, BigDecimal ticketPrice, String eventName) {
        this.ticketID = ticketID;
        this.ticketPrice = ticketPrice;
        this.eventName = eventName;
    }

    public Ticket() {

    }

    public int getTicketID() {
        return ticketID;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", ticketPrice=" + ticketPrice +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}

