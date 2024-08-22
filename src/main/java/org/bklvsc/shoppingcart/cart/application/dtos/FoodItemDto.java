package org.bklvsc.shoppingcart.cart.application.dtos;

public record FoodItemDto(
		String foodName,
		double foodPrice,
		int quantity
	) {

	@Override
	public String toString() {
		return String.format("Food[name: %s, price: %.2f, quantity: %d]%n",
				foodName, foodPrice, quantity);
	}
}
