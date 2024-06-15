package org.bklvsc.shoppingcart.domain.valueobjects;

import org.bklvsc.shoppingcart.domain.commons.Identity;

public class UserId extends Identity{
	private UserId(String string) {
		super(string);
	}
	
	private UserId() {
		super();
	}

	public static UserId from(String string) {
		return new UserId(string);
	}
	
	public static UserId createNewId() {
		return new UserId();
	}
}
