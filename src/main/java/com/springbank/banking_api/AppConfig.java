package com.springbank.banking_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner seedDatabase(AccountRepository accountRepository) {
        return args -> {
            if(accountRepository.findById("Acc123").isEmpty()) {
                accountRepository.save(new Account("Acc123", "Test User", 1250.75));
            }
        };
    }
}
