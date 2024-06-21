package org.bklvsc.shoppingcart.domain.valueobjects;

import java.util.Objects;

import org.bklvsc.shoppingcart.domain.user.User;

public record FoodName(String name) {
	public FoodName{
		if(name == null)
			throw new IllegalArgumentException("Name cannot be null");
		if(name.isBlank())
			throw new IllegalArgumentException("Name cannot be blank");
		if(name.length() < 3 || name.length() > 50)
			throw new IllegalArgumentException("Name size has to be between 3 and 50");
	}

	@Override
	public int hashCode() {
		return Objects.hash(name.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodName other = (FoodName) obj;
		return Objects.equals(name.toLowerCase(), other.name.toLowerCase());
	}
	
	

}
