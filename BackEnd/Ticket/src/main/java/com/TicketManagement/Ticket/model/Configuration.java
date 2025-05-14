package com.TicketManagement.Ticket.model;

import javax.persistence.*;

@Entity
@Table(name = "configurations")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    private int maxTicketCapacity;

    @Column(nullable = false)
    private int totalTickets;

    @Column(nullable = false)
    private int releaseRate;

    @Column(nullable = false)
    private int retrievalRate;

    public Configuration() {}

    public Configuration(int maxTicketCapacity, int totalTickets, int releaseRate, int retrievalRate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }

    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }

    public int getReleaseRate() { return releaseRate; }
    public void setReleaseRate(int releaseRate) { this.releaseRate = releaseRate; }

    public int getRetrievalRate() { return retrievalRate; }
    public void setRetrievalRate(int retrievalRate) { this.retrievalRate = retrievalRate; }
}
