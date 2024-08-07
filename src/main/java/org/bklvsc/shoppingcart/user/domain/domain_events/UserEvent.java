package org.bklvsc.shoppingcart.user.domain.domain_events;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.domain.DomainEvent;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.valueobjects.UserName;

public sealed interface UserEvent extends DomainEvent {
	public record UserCreatedEvent(UserId user, UserName userName) implements UserEvent {}
}
