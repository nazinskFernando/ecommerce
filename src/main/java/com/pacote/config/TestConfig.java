package com.pacote.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pacote.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {// configuração de hambiente de teste  usando o h2DB

	@Autowired
	private DBService dbService;
	@Bean
	public Boolean instantianteDatabase() throws ParseException {
		dbService.instantiateTesteDatabase();
		return true;
	}
	
	/*@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}*/
}
