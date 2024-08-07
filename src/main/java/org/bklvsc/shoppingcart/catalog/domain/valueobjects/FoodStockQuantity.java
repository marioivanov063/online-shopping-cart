package org.bklvsc.shoppingcart.catalog.domain.valueobjects;


public record FoodStockQuantity(int quantity) {
	public FoodStockQuantity{
		if(quantity <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
	}
}
