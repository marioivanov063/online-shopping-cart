package org.bklvsc.shoppingcart.commons.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import org.bklvsc.shoppingcart.user.domain.entities.UserDomainModel;

public record FoodName(String value) implements Serializable{
	public FoodName{
		if(value == null)
			throw new IllegalArgumentException("Name cannot be null");
		if(value.isBlank())
			throw new IllegalArgumentException("Name cannot be blank");
		if(value.length() < 3 || value.length() > 50)
			throw new IllegalArgumentException("Name size has to be between 3 and 50");
	}

	@Override
	public int hashCode() {
		return Objects.hash(value.toLowerCase());
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
		return Objects.equals(value.toLowerCase(), other.value.toLowerCase());
	}
}
