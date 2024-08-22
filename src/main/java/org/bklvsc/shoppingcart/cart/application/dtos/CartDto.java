package org.bklvsc.shoppingcart.cart.application.dtos;

import java.util.List;
import java.util.UUID;

public record CartDto(
		UUID user_id,
		double cartTotal,
		List<FoodItemDto> foods
	) {

	@Override
	public String toString() {
		return String.format("Cart[userId: %s, total: %.2f]%n %s",
				user_id, cartTotal, foods);
	}
}
