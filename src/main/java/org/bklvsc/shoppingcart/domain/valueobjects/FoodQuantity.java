package org.bklvsc.shoppingcart.domain.valueobjects;

public record FoodQuantity(int quantity) {
	public FoodQuantity{
		if(quantity <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
	}
}
