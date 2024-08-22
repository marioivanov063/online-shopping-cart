package org.bklvsc.shoppingcart.commons.valueobjects;

import java.io.Serializable;

public record FoodQuantity(int value) implements Serializable{
	public FoodQuantity{
		if(value <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
		if(value > 50)
			throw new IllegalArgumentException("Food quantity exceeds limit of 50");
	}
}
