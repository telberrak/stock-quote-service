/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author telberrak
 *
 */
@Component
public class BondQuoteConsumer implements MessageListener{

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(BondQuoteConsumer.class);

	/** */
	public BondQuoteConsumer() {

	}

	@Override
	public void onMessage(Message message) {
		LOGGER.info("BOND desk received a quote : "+message.toString());
		
	}
}
