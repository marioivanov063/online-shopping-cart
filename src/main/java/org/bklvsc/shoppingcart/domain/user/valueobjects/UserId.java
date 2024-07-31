package org.bklvsc.shoppingcart.domain.user.valueobjects;

import java.util.UUID;

import org.bklvsc.shoppingcart.domain.commons.Identity;

import net.bytebuddy.asm.Advice.This;

public class UserId extends Identity{
	
	public UserId() {
		super();
	}
	
	public UserId(String string) {
		super(string);
	}
}
