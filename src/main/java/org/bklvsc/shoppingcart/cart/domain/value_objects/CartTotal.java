package org.bklvsc.shoppingcart.cart.domain.value_objects;

import java.io.Serializable;

public record CartTotal(double value) implements Serializable{
	public CartTotal{
		if(value < 0)
			throw new IllegalArgumentException("Total cannot be negative");
	}
}
