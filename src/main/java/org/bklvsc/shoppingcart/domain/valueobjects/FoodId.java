package org.bklvsc.shoppingcart.domain.valueobjects;

import org.bklvsc.shoppingcart.domain.commons.Identity;

public class FoodId extends Identity{

	private FoodId(String string) {
		super(string);
	}
	
	private FoodId() {
		super();
	}

	public static FoodId from(String string) {
		return new FoodId(string);
	}
	
	public static FoodId createNewId() {
		return new FoodId();
	}
}
