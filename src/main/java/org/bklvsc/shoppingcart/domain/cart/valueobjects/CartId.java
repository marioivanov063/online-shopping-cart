package org.bklvsc.shoppingcart.domain.cart.valueobjects;

import java.util.UUID;

import org.bklvsc.shoppingcart.domain.commons.Identity;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

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
