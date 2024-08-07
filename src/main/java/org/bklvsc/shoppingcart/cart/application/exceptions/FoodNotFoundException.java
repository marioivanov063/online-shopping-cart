package org.bklvsc.shoppingcart.cart.application.exceptions;

public class FoodNotFoundException extends IllegalArgumentException{
	public FoodNotFoundException() {
		super("Food not found");
	}
}
