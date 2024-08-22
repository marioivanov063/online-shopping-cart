package org.bklvsc.shoppingcart.cart.domain.domain_events;

import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartTotal;
import org.bklvsc.shoppingcart.commons.domain.DomainEvent;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;

public sealed interface CartEvent extends DomainEvent{
	//public record CartCreatedEvent(UUID userId) implements CartEvent{};
	public record FoodAddedEvent(UUID userId, String foodName, double price) implements CartEvent{};
	public record QuantityUpdatedEvent(UUID userId, String food, int currentQuantity) implements CartEvent{};
	public record FoodRemovedEvent(UUID userId, String food) implements CartEvent{};
	public record CartTotalUpdatedEvent(UUID userId, double cartTotal) implements CartEvent{};
}

