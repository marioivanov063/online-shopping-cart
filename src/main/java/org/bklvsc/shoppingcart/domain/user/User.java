package org.bklvsc.shoppingcart.domain.user;

import java.util.Objects;

import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;

public class User{
	private UserId userId;
	private CartId cartId;

	private User(UserId userId) {
		this.userId = userId;
	}
	
	public static User create() {
		return new User(UserId.createNewId());
	}

	public CartId getCartId() {
		return cartId;
	}

	private void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public void assignCart(CartId cartId) {
		this.setCartId(cartId);
	}

	public UserId getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return this.userId.equals(other.getUserId());
	}
	
	
}