package fr.codenames.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("fr.codenames")
@EnableTransactionManagement
@EnableJpaRepositories("fr.codenames.dao")
public class Config {

}
