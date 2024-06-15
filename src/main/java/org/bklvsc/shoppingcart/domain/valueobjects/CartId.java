package org.bklvsc.shoppingcart.domain.valueobjects;

import org.bklvsc.shoppingcart.domain.commons.Identity;

public class CartId extends Identity{
	private CartId(String string) {
		super(string);
	}
	
	private CartId() {
		super();
	}

	public static CartId from(String string) {
		return new CartId(string);
	}
	
	public static CartId createNewId() {
		return new CartId();
	}
}
