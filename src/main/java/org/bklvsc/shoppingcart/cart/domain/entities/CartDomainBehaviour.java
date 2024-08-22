package org.bklvsc.shoppingcart.cart.domain.entities;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.CartTotalUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodAddedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodRemovedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartTotal;
import org.bklvsc.shoppingcart.cart.domain.value_objects.FoodItem;
import org.bklvsc.shoppingcart.commons.domain.AggregateRoot;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
public class CartDomainBehaviour extends AggregateRoot<CartEvent>{
	private final CartDomainModel cart;
	
	public CartDomainBehaviour(String userId) {
		this.cart = new CartDomainModel(userId);
		//this.publish(new CartCreatedEvent(UUID.fromString(userId)));
	}
	
	public CartDomainBehaviour(Map<FoodName, FoodItem> foods, double total, String userId) {
		this.cart = new CartDomainModel(foods, total, userId);
	}
	
	public CartDomainModel getModel() {
		return cart;
	}

	public void addFood(FoodName foodName, FoodPrice price) {
		FoodItem foodItem = new FoodItem(1, price.value());
		this.cart.foods.put(foodName, foodItem);
		cart.total = new CartTotal(cart.total.value() + price.value());
		this.publish(new FoodAddedEvent(cart.getUserId(), foodName.value(), price.value()));
		this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total.value()));
	}
	
	public void incrementQuantity(FoodName foodName) {
		FoodItem foodItem = cart.foods.get(foodName);
		FoodItem updatedfoodItem = new FoodItem(foodItem.quantity() + 1, foodItem.price());
		cart.total = new CartTotal(cart.total.value() + foodItem.price());
		this.cart.foods.put(foodName, updatedfoodItem);
		this.publish(new QuantityUpdatedEvent(this.cart.getUserId(), foodName.value(), updatedfoodItem.quantity()));
		this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total.value()));
		/*this.cart.foods.stream()
			.filter(t -> t.getFoodName().equals(food))
				.findAny()
					.ifPresent((t) -> {
						t.foodQuantity = new FoodQuantity(t.foodQuantity.value() + 1);
						this.publish(new QuantityUpdatedEvent(this.cart.getUserId(), t.foodName, t.foodQuantity));
						cart.total = new CartTotal(cart.total.value() + t.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total));
					});*/
	}
	
	/*public boolean foodExists(String foodName) {
		return cart.foods.containsKey(new FoodName(foodName));
		/*FoodName food = new FoodName(foodName);
		return this.cart.getFoodItems().stream()
			.filter(item -> item.getFoodName().equals(food))
				.findAny().isPresent();
	}*/
	
	public FoodName getFood(String foodName) {
		FoodName name = new FoodName(foodName.toLowerCase());
		return cart.foods.containsKey(name) ? name : null;
	}
	
	public void removeFood(FoodName food) {
		  if(this.cart.foods.containsKey(food)){
		  		FoodItem item = cart.foods.get(food);
		  		if(item.quantity() > 1){
		  			FoodItem updatedfoodItem = new FoodItem(item.quantity() - 1, item.price());
		  			cart.foods.replace(food, updatedfoodItem);
		  			this.publish(new QuantityUpdatedEvent(this.cart.getUserId(), food.value(), updatedfoodItem.quantity()));
		  		}
		  		else{
		  			cart.foods.remove(food);
		  			this.publish(new FoodRemovedEvent(this.cart.getUserId(), food.value()));
		  		}
		 		cart.total = new CartTotal(cart.total.value() - item.price());
				this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total.value()));
		   }
		 
		/*this.cart.getFoodItems().stream().filter(item -> item.getFoodName().equals(food))
			.findAny()
				.ifPresent((item) -> {
					if(item.getFoodQuantity().value() > 1) {
						item.foodQuantity = new FoodQuantity(item.foodQuantity.value() - 1);
						cart.total = new CartTotal(cart.total.value() - item.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total));
						this.publish(new QuantityUpdatedEvent(this.cart.getUserId(), food, item.getFoodQuantity()));
					}
					else {
						cart.foods.remove(new FoodItemDomainModel(food));
						cart.total = new CartTotal(cart.total.value() - item.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getUserId(), cart.total));
						this.publish(new FoodRemovedEvent(this.cart.getUserId(), food));
					}
				}); */
	}
	
	public int getTotalNumberOfFoods() {
		return cart.foods.values().stream()
				.mapToInt(t -> t.quantity()).sum();
		/*return this.cart.getFoodItems().stream()
			.mapToInt(t -> t.getFoodQuantity().value()).sum();*/
	}
	
	public boolean isCartEmpty() {
		return this.cart.getFoodItems().isEmpty();
	}
	
}
