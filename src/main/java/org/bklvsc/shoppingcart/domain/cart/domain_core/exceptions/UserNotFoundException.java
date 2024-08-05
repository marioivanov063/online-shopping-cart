package org.bklvsc.shoppingcart.domain.cart.domain_core.exceptions;

public class UserNotFoundException extends IllegalArgumentException{
	public UserNotFoundException() {
		super("User not found");
	}

}