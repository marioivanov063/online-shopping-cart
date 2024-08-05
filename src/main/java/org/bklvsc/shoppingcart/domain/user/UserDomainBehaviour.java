package org.bklvsc.shoppingcart.domain.user;

import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.commons.AggregateRoot;
import org.bklvsc.shoppingcart.domain.user.UserEvent.CartAssignedToUserEvent;
import org.bklvsc.shoppingcart.domain.user.UserEvent.CartUnassignedFromUserEvent;
//import org.bklvsc.shoppingcart.domain.user.UserEvent.CartUnassignedFromUserEvent;
import org.bklvsc.shoppingcart.domain.user.UserEvent.UserCreatedEvent;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserName;

public class UserDomainBehaviour extends AggregateRoot<UserEvent>{
	private final UserDomainModel user;

	public UserDomainBehaviour(UserName userName) {
		UserId userId = new UserId();
		this.user = new UserDomainModel(userId, userName);
		this.publish(new UserCreatedEvent(userId, userName));
	}
	
	public UserDomainBehaviour(String userId, String username, String cartId) {
		this.user = new UserDomainModel(userId, username, cartId);
	}
	
	public UserDomainModel getModel() {
		return user;
	}

	public void assignCart(CartId cart) {
		if(cart == null)
			return;	
		if(user.getCart() == null) {
			this.user.setCart(cart);
			this.publish(new CartAssignedToUserEvent(this.user.getUserId(), cart));
		}
	}
	
	public void unassignCart() {
		if(user.getCart() != null)
			this.user.setCart(null);
			this.publish(new CartUnassignedFromUserEvent(this.user.getUserId()));
	}
	
	/*public UserId create(UserName username) {
		UserDomainModel user = new UserDomainModel(new UserId(), username);
		this.publish(new UserCreatedEvent(user.getUserId()));
		return user.getUserId();
	}*/
	
	/*public static UserDomainModel create(UUID uuid, String name) {
		UserId userId = UserId.from(uuid);
		UserName username = new UserName(name);
		return new UserDomainModel(userId, username);
	}*/
}
