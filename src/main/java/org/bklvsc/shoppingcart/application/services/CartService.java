package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface CartService {
	public void removeFoodFromCarts(FoodName foodName);
}
