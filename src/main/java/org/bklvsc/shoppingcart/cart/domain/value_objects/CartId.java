package org.bklvsc.shoppingcart.cart.domain.value_objects;

import java.util.UUID;

import org.bklvsc.shoppingcart.commons.domain.Identity;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;

import net.bytebuddy.asm.Advice.This;
import net.bytebuddy.implementation.bind.annotation.Super;
public class CartId extends Identity{
	public CartId(String uuid) {
		super(uuid);
	}
	
	public CartId() {
		super();
	}

}
