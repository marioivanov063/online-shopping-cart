package org.bklvsc.shoppingcart.domain.food;

import org.bklvsc.shoppingcart.domain.commons.DomainEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodStockQuantity;

public sealed interface FoodEvent extends DomainEvent{
	public record StockIncrementedEvent(FoodName foodName, FoodStockQuantity stockQuantity) implements FoodEvent{}
	public record StockDecrementedEvent(FoodName foodName, FoodStockQuantity stockQuantity) implements FoodEvent{}
	public record FoodCreatedEvent(FoodName foodName, FoodPrice price) implements FoodEvent{}
}
