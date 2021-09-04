/**
 * 
 */
package com.datacratos.courses.rabbitmq.stockquoteservice.domain;

/**
 * @author telberrak
 *
 */
public abstract class Asset {

	
	private final String ticker;
	private final double price;
	protected String type;
	
	
	public Asset(String ticker, double price) {
		this.ticker = ticker;
		this.price = price;
	}

	public String getTicker() {
		return ticker;
	}


	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Asset [ticker=" + ticker + ", price=" + price + ", type=" + type + "]";
	}
}
