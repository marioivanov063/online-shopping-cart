package org.bklvsc.shoppingcart.application.exceptions;

public class UserNotFoundException extends IllegalArgumentException{
	public UserNotFoundException() {
		super("User not found");
	}

}