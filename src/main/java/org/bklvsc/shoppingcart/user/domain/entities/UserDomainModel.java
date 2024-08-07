package org.bklvsc.shoppingcart.user.domain.entities;
import java.util.Objects;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.domain.AggregateRoot;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.valueobjects.UserName;

public class UserDomainModel{
	private UserId userId;
	private UserName username;
	private CartId cart;

	UserDomainModel(String userId, String username, String cart) {
		this.userId = new UserId(userId);
		this.username = new UserName(username);
		if(cart == null)
			this.cart = null;
		else
			this.cart = new CartId(cart);
	}
	
	UserDomainModel(UserId userId, UserName userName){
		this.userId = userId;
		this.username = userName;
	}

	void setCart(CartId cart) {
		this.cart = cart;
	}

	public CartId getCart() {
		return this.cart;
	}
	
	public UserName getUsername() {
		return username;
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
		UserDomainModel other = (UserDomainModel) obj;
		return this.userId.equals(other.getUserId());
	}
}