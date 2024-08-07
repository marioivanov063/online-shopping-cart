package org.bklvsc.shoppingcart.cart.application.exceptions;

public class UserNotFoundException extends IllegalArgumentException{
	public UserNotFoundException() {
		super("User not found");
	}

}