/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice.domain;

/**
 * @author telberrak
 *
 */
public class Fx extends Asset {

	/**
	 * @param ticker
	 * @param price
	 */
	public Fx(String ticker, double price) {
		super(ticker, price);
		this.type = "FX";
	}

}
