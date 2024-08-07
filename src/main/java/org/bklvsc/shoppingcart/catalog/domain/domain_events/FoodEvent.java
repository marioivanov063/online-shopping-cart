package org.bklvsc.shoppingcart.catalog.domain.domain_events;

import org.bklvsc.shoppingcart.catalog.domain.valueobjects.FoodStockQuantity;
import org.bklvsc.shoppingcart.commons.domain.DomainEvent;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;

public sealed interface FoodEvent extends DomainEvent{
	public record StockIncrementedEvent(FoodName foodName, FoodStockQuantity stockQuantity) implements FoodEvent{}
	public record StockDecrementedEvent(FoodName foodName, FoodStockQuantity stockQuantity) implements FoodEvent{}
	public record FoodCreatedEvent(FoodName foodName, FoodPrice price) implements FoodEvent{}
}
