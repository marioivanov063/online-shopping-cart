package org.bklvsc.shoppingcart.domain.user;

import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.commons.DomainEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserName;

public sealed interface UserEvent extends DomainEvent {
	public record CartAssignedToUserEvent(UserId userId, CartId cartId) implements UserEvent{}
	public record CartUnassignedFromUserEvent(UserId userId) implements UserEvent{}
	public record UserCreatedEvent(UserId user, UserName userName) implements UserEvent {}
}
