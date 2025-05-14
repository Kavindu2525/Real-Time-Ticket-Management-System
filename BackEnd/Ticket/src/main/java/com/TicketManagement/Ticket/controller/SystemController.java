package com.TicketManagement.Ticket.controller;

import com.TicketManagement.Ticket.model.Configuration;
import com.TicketManagement.Ticket.service.*;
import com.TicketManagement.Ticket.dto.CustomerRequest;
import com.TicketManagement.Ticket.dto.VendorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class SystemController {

    private final TicketPool ticketPool;
    private final VendorService vendorService;
    private final CustomerService customerService;
    private final ConfigurationService configurationService;

    @Autowired
    public SystemController(
            TicketPool ticketPool,
            VendorService vendorService,
            CustomerService customerService,
            ConfigurationService configurationService) {
        this.ticketPool = ticketPool;
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.configurationService = configurationService;
    }

    @PostMapping("/system/start")
    public String startSystem() {
        ticketPool.startSystem();
        return "System started.";
    }

    @PostMapping("/system/stop")
    public String stopSystem() {
        ticketPool.stopSystem();
        return "System stopped.";
    }

    @PostMapping("/system/addVendor")
    public String addVendor(@RequestBody VendorRequest vendorRequest) {
        Configuration configuration = configurationService.getLatestConfiguration();
        if (configuration == null) {
            return "Configuration not found.";
        }

        int releaseRate = configuration.getReleaseRate();
        int totalTickets = configuration.getTotalTickets();

        for (int i = 0; i < vendorRequest.getNumberOfVendors(); i++) {
            vendorService.releaseTickets(ticketPool, releaseRate, totalTickets);
        }
        return vendorRequest.getNumberOfVendors() + " vendor(s) added.";
    }

    @PostMapping("/system/addCustomer")
    public String addCustomer(@RequestBody CustomerRequest customerRequest) {
        Configuration configuration = configurationService.getLatestConfiguration();
        if (configuration == null) {
            return "Configuration not found.";
        }

        int retrievalRate = configuration.getRetrievalRate();

        for (int i = 0; i < customerRequest.getNumberOfCustomers(); i++) {
            customerService.purchaseTickets(ticketPool, retrievalRate, customerRequest.getQuantity());
        }
        return customerRequest.getNumberOfCustomers() + " customer(s) added.";
    }
}
