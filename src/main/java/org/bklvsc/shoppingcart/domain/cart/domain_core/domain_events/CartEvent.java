package org.bklvsc.shoppingcart.domain.cart.domain_core.domain_events;

import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartTotal;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.domain.commons.DomainEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public sealed interface CartEvent extends DomainEvent{
	public record CartCreatedEvent(CartId cartId, UserId userId) implements CartEvent{};
	public record FoodAddedEvent(CartId cartId, FoodName food, FoodPrice price) implements CartEvent{};
	public record QuantityUpdatedEvent(CartId cartId, FoodName food, FoodQuantity currentQuantity) implements CartEvent{};
	public record FoodRemovedEvent(CartId cartId, FoodName food) implements CartEvent{};
	public record CartTotalUpdatedEvent(CartId cartId, CartTotal cartTotal) implements CartEvent{};
	public record CartDeletedEvent(CartId cartId) implements CartEvent{};
}

