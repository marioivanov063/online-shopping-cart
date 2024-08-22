package org.bklvsc.shoppingcart.cart.domain.value_objects;

import java.io.Serializable;
import java.util.Objects;

import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;

public record FoodItem(int quantity, double price) implements Serializable{
	public FoodItem{
		if(price <= 0)
			throw new IllegalArgumentException("Price cannot be negative");
		if(quantity <= 0)
			throw new IllegalArgumentException("Food quantity cannot be less or equal to zero");
		if(quantity > 50)
			throw new IllegalArgumentException("Food quantity exceeds limit of 50");
	}
}

