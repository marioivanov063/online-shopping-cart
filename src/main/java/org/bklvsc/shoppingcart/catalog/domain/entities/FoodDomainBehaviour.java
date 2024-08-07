package org.bklvsc.shoppingcart.catalog.domain.entities;

import org.bklvsc.shoppingcart.catalog.domain.domain_events.FoodEvent;
import org.bklvsc.shoppingcart.catalog.domain.domain_events.FoodEvent.FoodCreatedEvent;
import org.bklvsc.shoppingcart.catalog.domain.domain_events.FoodEvent.StockDecrementedEvent;
import org.bklvsc.shoppingcart.catalog.domain.domain_events.FoodEvent.StockIncrementedEvent;
import org.bklvsc.shoppingcart.catalog.domain.valueobjects.FoodStockQuantity;
import org.bklvsc.shoppingcart.commons.domain.AggregateRoot;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainModel;

public class FoodDomainBehaviour extends AggregateRoot<FoodEvent>{
	private final FoodDomainModel food;

	public FoodDomainBehaviour(FoodName foodName, FoodPrice foodPrice) {
		this.food = new FoodDomainModel(foodName, foodPrice);
		this.publish(new FoodCreatedEvent(foodName, foodPrice));
	}
	
	public FoodDomainBehaviour(String name, double price) {
		FoodName foodName = new FoodName(name);
		FoodPrice foodPrice = new FoodPrice(price);
		this.food = new FoodDomainModel(name, price);
	}
	
	public FoodDomainModel getModel() {
		return food;
	}

	/*public void incrementStock() {
		FoodStockQuantity stockQuantity = 
				new FoodStockQuantity(food.getFoodQuantity().quantity() + 1);
		this.publish(new StockIncrementedEvent(this.food.getFoodName(), stockQuantity));
	}*/
	
	/*public void decrementStock() {
		FoodStockQuantity stockQuantity = 
				new FoodStockQuantity(food.getFoodQuantity().quantity() - 1);
		this.publish(new StockDecrementedEvent(this.food.getFoodName(), stockQuantity));
	}*/
	
	
}
