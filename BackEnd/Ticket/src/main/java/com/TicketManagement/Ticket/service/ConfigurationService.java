package com.TicketManagement.Ticket.service;

import com.TicketManagement.Ticket.model.Configuration;
import com.TicketManagement.Ticket.repo.ConfigurationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    @Autowired
    private  ConfigurationRepo configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepo configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    /**
     * Save a new configuration to the database.
     *
     * @param configuration the configuration to save
     * @return the saved configuration
     */
    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public Configuration getLatestConfiguration() {
        return configurationRepository.findTopByOrderByIdDesc().orElse(null);
    }
}

