/*
package com.TicketManagement.Ticket.controller;

import com.TicketManagement.Ticket.service.TicketPool;
import com.TicketManagement.Ticket.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TicketController {
    private final TicketPool ticketPool;
    private final LogService logService;

    @Autowired
    public TicketController(TicketPool ticketPool, LogService logService) {
        this.ticketPool = ticketPool;
        this.logService = logService;
    }

    @PostMapping("/system/start")
    public String startSystem() {
        ticketPool.startSystem();
        logService.sendLog("[LOG] System started.");
        return "System started.";
    }

    @PostMapping("/system/stop")
    public String stopSystem() {
        ticketPool.stopSystem();
        logService.sendLog("[LOG] System stopped.");
        return "System stopped.";
    }
}
*/
