package org.bklvsc.shoppingcart.commons.valueobjects;

import java.util.UUID;

import org.bklvsc.shoppingcart.commons.domain.Identity;

import net.bytebuddy.asm.Advice.This;

public class UserId extends Identity{
	
	public UserId() {
		super();
	}
	
	public UserId(String string) {
		super(string);
	}
}
