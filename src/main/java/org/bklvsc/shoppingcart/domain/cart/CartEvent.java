package org.bklvsc.shoppingcart.domain.cart;

import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartTotal;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.domain.commons.DomainEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;

public sealed interface CartEvent extends DomainEvent{
	public record FoodAddedEvent(CartId cartId, FoodName food, FoodPrice price) implements CartEvent{};
	public record QuantityUpdatedEvent(CartId cartId, FoodName food, FoodQuantity currentQuantity) implements CartEvent{};
	public record FoodRemovedEvent(CartId cartId, FoodName food) implements CartEvent{};
	public record CartTotalUpdatedEvent(CartId cartId, CartTotal cartTotal) implements CartEvent{};
}

