package org.bklvsc.shoppingcart.cart.domain.domain_events;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartTotal;
import org.bklvsc.shoppingcart.commons.domain.DomainEvent;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;

public sealed interface CartEvent extends DomainEvent{
	public record CartCreatedEvent(CartId cartId, UserId userId) implements CartEvent{};
	public record FoodAddedEvent(CartId cartId, FoodName food, FoodPrice price) implements CartEvent{};
	public record QuantityUpdatedEvent(CartId cartId, FoodName food, FoodQuantity currentQuantity) implements CartEvent{};
	public record FoodRemovedEvent(CartId cartId, FoodName food) implements CartEvent{};
	public record CartTotalUpdatedEvent(CartId cartId, CartTotal cartTotal) implements CartEvent{};
	public record CartDeletedEvent(CartId cartId) implements CartEvent{};
}

