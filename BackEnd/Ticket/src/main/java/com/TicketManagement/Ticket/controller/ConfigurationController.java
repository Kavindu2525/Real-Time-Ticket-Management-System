
package com.TicketManagement.Ticket.controller;


import com.TicketManagement.Ticket.model.Configuration;
import com.TicketManagement.Ticket.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/api")
    public class ConfigurationController {

        private final ConfigurationService configurationService;

        @Autowired
        public ConfigurationController(ConfigurationService configurationService) {
            this.configurationService = configurationService;
        }

        @PostMapping("/configurations/save")
        public ResponseEntity<Configuration> saveConfiguration(@RequestBody Configuration configuration) {
            Configuration savedConfig = configurationService.saveConfiguration(configuration);
            return ResponseEntity.ok(savedConfig);
        }

        @GetMapping("/configurations/latest")
        public ResponseEntity<Configuration> getLatestConfiguration() {
            Configuration configuration = configurationService.getLatestConfiguration();
            if (configuration != null) {
                return ResponseEntity.ok(configuration);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
    }



