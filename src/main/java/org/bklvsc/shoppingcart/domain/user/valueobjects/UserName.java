package org.bklvsc.shoppingcart.domain.user.valueobjects;

public record UserName(String username) {
	public UserName{
		if(username == null)
			throw new IllegalArgumentException("Name cannot be null");
		if(username.isBlank())
			throw new IllegalArgumentException("Name cannot be blank");
		if(username.length() < 3 || username.length() > 50)
			throw new IllegalArgumentException("Name size has to be between 3 and 50");
	}
}
