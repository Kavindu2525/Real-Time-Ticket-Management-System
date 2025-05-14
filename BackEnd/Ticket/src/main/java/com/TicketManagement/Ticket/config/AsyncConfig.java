package com.TicketManagement.Ticket.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

        @Bean(name = "taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);  // Minimum number of threads
            executor.setMaxPoolSize(50);  // Maximum number of threads
            executor.setQueueCapacity(100);  // Capacity of task queue
            executor.setThreadNamePrefix("Task-");
            executor.initialize();
            return executor;
        }
    }


