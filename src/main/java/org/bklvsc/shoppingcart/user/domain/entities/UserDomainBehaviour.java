package org.bklvsc.shoppingcart.user.domain.entities;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.domain.AggregateRoot;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.domain_events.UserEvent;
import org.bklvsc.shoppingcart.user.domain.domain_events.UserEvent.UserCreatedEvent;
import org.bklvsc.shoppingcart.user.domain.valueobjects.UserName;

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
