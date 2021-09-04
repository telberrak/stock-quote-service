/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author telberrak
 *
 */
@Configuration
public class RabbitConfiguration {

	
	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	private String password;
	
	@Value("${spring.rabbitmq.host}")
	String host;

	@Value("${spring.rabbitmq.port}")
	private int port;
	
	@Value("${spring.rabbitmq.queue.stockQueue}")
	private String stockQueue;
	
	@Value("${spring.rabbitmq.queue.bondQueue}")
	private String bondQueue;
	
	
	@Value("${spring.rabbitmq.queue.bondQueue}")
	private String fxQueue;
	
	@Autowired
	StockQuoteConsumer stockQuoteConsumer;
	
	@Autowired
	BondQuoteConsumer bondQuoteConsumer;
	
	@Autowired
	FxQuoteConsumer fxQuoteConsumer;
	
	
	@Bean
    public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost(host);
		cachingConnectionFactory.setPort(port);	
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setUsername(password);
		return cachingConnectionFactory;
    }
	
	/*
	@Bean
	SimpleMessageListenerContainer stockContainer(ConnectionFactory connectionFactory) {
	    SimpleMessageListenerContainer stockContainer = new SimpleMessageListenerContainer();
	    stockContainer.setConnectionFactory(connectionFactory);
	    stockContainer.setQueues(stockQueue());
	    stockContainer.setMessageListener(stockQuoteConsumer);
	    return stockContainer;

	}
	
	@Bean
	SimpleMessageListenerContainer bondContainer(ConnectionFactory connectionFactory) {
	    SimpleMessageListenerContainer bondContainer = new SimpleMessageListenerContainer();
	    bondContainer.setConnectionFactory(connectionFactory);
	    bondContainer.setQueues(bondQueue());
	    bondContainer.setMessageListener(bondQuoteConsumer);
	    return bondContainer;

	}
	@Bean
	SimpleMessageListenerContainer fxContainer(ConnectionFactory connectionFactory) {
	    SimpleMessageListenerContainer fxContainer = new SimpleMessageListenerContainer();
	    fxContainer.setConnectionFactory(connectionFactory);
	    fxContainer.setQueues(fxQueue());
	    fxContainer.setMessageListener(fxQuoteConsumer);
	    return fxContainer;
	  }
	 */
	
    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
	
    @Bean
    Queue stockQueue() {
       return new Queue(stockQueue);
    }
    
    @Bean
    Queue bondQueue() {
       return new Queue(bondQueue);
    }
    
    @Bean
    Queue fxQueue() {
       return new Queue(fxQueue);
       
    }
 
}
