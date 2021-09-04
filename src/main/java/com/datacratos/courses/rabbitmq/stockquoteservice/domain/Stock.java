/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice.domain;

/**
 * @author telberrak
 *
 */
public class Stock extends Asset {

	public Stock(String ticker, double price) {
		super(ticker, price);
		this.type = "STOCK";
	}

}
