package org.bklvsc.shoppingcart.application.exceptions;

public class FoodNotFoundException extends IllegalArgumentException{
	public FoodNotFoundException() {
		super("Food not found");
	}
}
