package org.bklvsc.shoppingcart.domain.cart.valueobjects;

public record FoodQuantity(int value) {
	public FoodQuantity{
		if(value <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
		if(value > 50)
			throw new IllegalArgumentException("Food quantity exceeds limit of 50");
	}
}
