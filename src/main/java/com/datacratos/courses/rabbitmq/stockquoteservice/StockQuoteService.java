package com.datacratos.courses.rabbitmq.stockquoteservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockQuoteService {

	
	public static void main(String[] args) {
		SpringApplication.run(StockQuoteService.class, args);
	}
	
   @Bean
   public CommandLineRunner start(QuotePublisher2TopicExchange quotePublisher) {
      return (args) -> {
         
    	  quotePublisher.publish();
    	  
      };
   }

}
