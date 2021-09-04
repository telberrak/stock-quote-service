/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datacratos.courses.rabbitmq.stockquoteservice.domain.Asset;
import com.datacratos.courses.rabbitmq.stockquoteservice.domain.Bond;
import com.datacratos.courses.rabbitmq.stockquoteservice.domain.Fx;
import com.datacratos.courses.rabbitmq.stockquoteservice.domain.Stock;

/**
 * @author telberrak
 *
 */

@Component
public class QuotePublisher2HeadersExchange {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(QuotePublisher2HeadersExchange.class);

	private static final String HEADERS_EXCHANGE = "headers_asset_exchange";

	@Autowired
	private final AmqpAdmin amqpAdmin;

	@Autowired
	private final AmqpTemplate amqpTemplate;

	/**
	 * constructs a quote publisher
	 * 
	 * @param amqpAdmin
	 * @param amqpTemplate
	 */
	public QuotePublisher2HeadersExchange(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
		this.amqpAdmin = null;
		this.amqpTemplate = null;

	}

	/**
	 * send an asset
	 * 
	 * @param asset
	 */
	public void publish() {

		Asset stock = new Stock("AAPL", 152);
		Asset bond = new Bond("US10YT", 1.33);
		Asset fx = new Fx("GBPUSD", 1.31);

		
		List<Asset> assets = new ArrayList<Asset>(4);

		assets.add(0, null);
		assets.add(1, stock);
		assets.add(2, bond);
		assets.add(3, fx);

		for (int i=0; i<10; i++){

			Asset asset = assets.get((int) Math.ceil(3 * Math.random()));

			LOGGER.info("publishing "+asset.toString());
			
			
			MessageProperties messageProperties = new MessageProperties();
			
			if("BOND".equalsIgnoreCase(asset.getType()))
			{
				messageProperties.setHeader("cash", "yes");
			}

			Message message = new SimpleMessageConverter().toMessage(asset.toString(), messageProperties);

			amqpTemplate.convertAndSend(HEADERS_EXCHANGE, null, message);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}

	}

}
