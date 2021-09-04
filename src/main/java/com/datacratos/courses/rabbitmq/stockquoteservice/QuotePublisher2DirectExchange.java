/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
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
public class QuotePublisher2DirectExchange {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(QuotePublisher2DirectExchange.class);

	private String DIRECT_EXCHANGE = "direct_asset_exchange";
	
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
	public QuotePublisher2DirectExchange(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
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
			
			String routingKey = asset.getType().toLowerCase();
			
			amqpTemplate.convertAndSend(DIRECT_EXCHANGE, routingKey, asset.toString());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}

	}

}
