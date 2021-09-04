/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice.domain;

/**
 * @author telberrak
 *
 */
public class Bond extends Asset {

	/**
	 * @param ticker
	 * @param price
	 */
	public Bond(String ticker, double price) {
		super(ticker, price);
		this.type = "BOND";
	}

}
