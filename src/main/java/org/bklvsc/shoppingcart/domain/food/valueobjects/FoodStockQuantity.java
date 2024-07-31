package org.bklvsc.shoppingcart.domain.food.valueobjects;


public record FoodStockQuantity(int quantity) {
	public FoodStockQuantity{
		if(quantity <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
	}
}
