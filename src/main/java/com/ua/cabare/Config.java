package com.ua.cabare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ua.cabare"})
public class Config {

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(11);
  }
}
