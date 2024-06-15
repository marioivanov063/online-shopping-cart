package org.bklvsc.shoppingcart.application.exceptions;

import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class FoodAlreadyExists extends IllegalArgumentException{
	private FoodName name;
	public FoodAlreadyExists(FoodName s) {
		super(String.format("Food with name %s already exists", s));
	}

}
