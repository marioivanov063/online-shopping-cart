package org.bklvsc.shoppingcart.domain.valueobjects;

public record FoodPrice(double price) {
	public FoodPrice{
		if(price <= 0)
			throw new IllegalArgumentException("Price cannot be negative");
	}
}
