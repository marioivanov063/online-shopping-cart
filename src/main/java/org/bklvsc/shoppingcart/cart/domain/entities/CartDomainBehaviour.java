package org.bklvsc.shoppingcart.cart.domain.entities;
import java.util.Set;

import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.CartCreatedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.CartDeletedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.CartTotalUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodAddedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodRemovedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartTotal;
import org.bklvsc.shoppingcart.commons.domain.AggregateRoot;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
public class CartDomainBehaviour extends AggregateRoot<CartEvent>{
	/*private final CartDomainModel cart;
	

	public CartDomainBehaviour(String userId) {
		this.cart = new CartDomainModel(userId);
		this.publish(new CartCreatedEvent(cart.getCartId(), new UserId(userId)));
	}
	
	public CartDomainBehaviour(String cartId, Set<FoodItem> foods, double total, String userId) {
		this.cart = new CartDomainModel(cartId, foods, total, userId);
	}
	
	public CartDomainModel getModel() {
		return cart;
	}

	public boolean addFood(FoodName food, FoodPrice price) {
		FoodItemDomainModel foodItem = new FoodItemDomainModel(food);
		foodItem.foodPrice = price;
		if(this.cart.foods.add(foodItem)) {
			cart.total = new CartTotal(cart.total.value() + price.value());
			this.publish(new CartTotalUpdatedEvent(cart.getCartId(), cart.total));
			this.publish(new FoodAddedEvent(cart.getCartId(), food, price));
			
			return true;
		}
		return false;
	}
	
	public void incrementQuantity(FoodName food) {
		this.cart.foods.stream()
			.filter(t -> t.getFoodName().equals(food))
				.findAny()
					.ifPresent((t) -> {
						t.foodQuantity = new FoodQuantity(t.foodQuantity.value() + 1);
						this.publish(new QuantityUpdatedEvent(this.cart.getCartId(), t.foodName, t.foodQuantity));
						cart.total = new CartTotal(cart.total.value() + t.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getCartId(), cart.total));
					});
	}
	
	public boolean foodExists(FoodName food) {
		return this.cart.getFoodItems().stream()
			.filter(item -> item.getFoodName().equals(food))
				.findAny().isPresent();
	}
	
	public void removeFood(FoodName food) {
		this.cart.getFoodItems().stream().filter(item -> item.getFoodName().equals(food))
			.findAny()
				.ifPresent((item) -> {
					if(item.getFoodQuantity().value() > 1) {
						item.foodQuantity = new FoodQuantity(item.foodQuantity.value() - 1);
						cart.total = new CartTotal(cart.total.value() - item.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getCartId(), cart.total));
						this.publish(new QuantityUpdatedEvent(this.cart.getCartId(), food, item.getFoodQuantity()));
					}
					else {
						cart.foods.remove(new FoodItem(food));
						cart.total = new CartTotal(cart.total.value() - item.foodPrice.value());
						this.publish(new CartTotalUpdatedEvent(cart.getCartId(), cart.total));
						this.publish(new FoodRemovedEvent(this.cart.getCartId(), food));//generate event
					}
				});
		
	  if(isCartEmpty())
	  		this.publish(new CartDeletedEvent(this.cart.getCartId())); 
	}
	
	public int getTotalNumberOfFoods() {
		return this.cart.getFoodItems().stream()
			.mapToInt(t -> t.getFoodQuantity().value()).sum();
	}
	
	public boolean isCartEmpty() {
		return this.cart.getFoodItems().isEmpty();
	}*/
	
}
