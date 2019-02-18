package com.jwesolowski.simpletodo.runner;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.jwesolowski.simpletodo.domain")
@EnableJpaRepositories("com.jwesolowski.simpletodo.repository")
@ComponentScan(basePackages = {"com.jwesolowski.simpletodo.config", "com.jwesolowski.simpletodo",
    "com.jwesolowski.simpletodo.controllers", "com.jwesolowski.simpletodo.service"})
@EnableEmailTools
@EnableScheduling
public class ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationRunner.class, args);
  }
}
