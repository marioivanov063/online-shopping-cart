package org.bklvsc.shoppingcart.domain.cart;
import java.util.Set;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.domain.cart.CartDomainModel.FoodItem;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.CartTotalUpdatedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.FoodAddedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.FoodRemovedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartTotal;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.domain.commons.AggregateRoot;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;

public class CartDomainBehaviour extends AggregateRoot<CartEvent>{
	private final CartDomainModel cart;
	

	public CartDomainBehaviour() {
		this.cart = new CartDomainModel();
	}
	
	public CartDomainBehaviour(String cartId, Set<FoodItem> foods, double total) {
		this.cart = new CartDomainModel(cartId, foods, total);
	}
	
	public CartDomainModel getModel() {
		return cart;
	}

	public boolean addFood(FoodName food, FoodPrice price) {
		FoodItem foodItem = new FoodItem(food);
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
	}
	
	public int getTotalNumberOfFoods() {
		return this.cart.getFoodItems().stream()
			.mapToInt(t -> t.getFoodQuantity().value()).sum();
	}
	
}
