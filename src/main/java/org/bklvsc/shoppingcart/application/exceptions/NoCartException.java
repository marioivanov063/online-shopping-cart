package org.bklvsc.shoppingcart.application.exceptions;

public class NoCartException extends IllegalArgumentException {

	public NoCartException() {
		super("No cart available for the user");
	}
	
}
