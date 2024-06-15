package org.bklvsc.shoppingcart.domain.valueobjects;

public record FoodName(String name) {
	public FoodName{
		if(name == null)
			throw new IllegalArgumentException("Name cannot be null");
		if(name.isBlank())
			throw new IllegalArgumentException("Name cannot be blank");
		if(name.length() < 3 || name.length() > 50)
			throw new IllegalArgumentException("Name size has to be between 3 and 50");
		name = name.toLowerCase();
	}
}
