/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author telberrak
 *
 */
@Component
public abstract class QuoteConsumer extends MessageListenerAdapter {

	
	public QuoteConsumer() {

	}

	@Override
	public void onMessage(Message message) {

		processQuote(message);

	}

	/**
	 * to be implemented by concrete classes
	 * 
	 * @param asset
	 */
	public abstract void processQuote(Message message);

}
