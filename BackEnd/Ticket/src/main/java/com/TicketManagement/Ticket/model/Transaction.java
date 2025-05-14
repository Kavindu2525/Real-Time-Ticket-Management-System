/*
package com.TicketManagement.Ticket.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private int ticketId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime transactionTime;



    public Transaction(int ticketId, BigDecimal price, LocalDateTime transactionTime) {
        this.customerId = customerId;
        this.ticketId = ticketId;
        this.price = price;
        this.transactionTime = transactionTime;
    }
}
*/
