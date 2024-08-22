package org.bklvsc.shoppingcart.commons.valueobjects;

import java.io.Serializable;

public record FoodPrice(double value) implements Serializable{
	public FoodPrice{
		if(value <= 0)
			throw new IllegalArgumentException("Price cannot be negative");
	}
}
