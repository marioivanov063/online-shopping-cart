package org.bklvsc.shoppingcart.commons.valueobjects;

public record FoodPrice(double value) {
	public FoodPrice{
		if(value <= 0)
			throw new IllegalArgumentException("Price cannot be negative");
	}
}
