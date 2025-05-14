package com.TicketManagement.Ticket.repo;

import com.TicketManagement.Ticket.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {
    Optional<Configuration> findTopByOrderByIdDesc();
}
