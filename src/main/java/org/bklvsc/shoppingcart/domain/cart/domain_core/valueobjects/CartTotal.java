package org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects;

import java.io.Serializable;

public record CartTotal(double value) implements Serializable{
	public CartTotal{
		if(value < 0)
			throw new IllegalArgumentException("Total cannot be negative");
	}
}
