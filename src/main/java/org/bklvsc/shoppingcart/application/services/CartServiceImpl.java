package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class CartServiceImpl implements CartService{
	private CartRepository cartRepository;

	private CartServiceImpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}

	@Override
	public void removeFoodFromCarts(FoodName foodName) {
		cartRepository
			.getCarts()
			.stream().forEach(cart -> {
				cart.removeFood(foodName);
			});
	}
}