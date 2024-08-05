package org.bklvsc.shoppingcart.domain.cart.domain_core.exceptions;

public class FoodNotFoundException extends IllegalArgumentException{
	public FoodNotFoundException() {
		super("Food not found");
	}
}
