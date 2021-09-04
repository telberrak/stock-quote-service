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
public class FxQuoteConsumer  implements MessageListener{

	/** The logger */
	private static final Logger LOGGER  = Logger.getLogger(FxQuoteConsumer.class);
	
	public FxQuoteConsumer() {
		
	}

	@Override
	public void onMessage(Message message) {
		LOGGER.info("FX desk received a quote : "+message.toString());
	}

}
