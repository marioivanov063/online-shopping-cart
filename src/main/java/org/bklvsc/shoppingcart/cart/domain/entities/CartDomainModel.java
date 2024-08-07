package org.bklvsc.shoppingcart.cart.domain.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartTotal;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;


public class CartDomainModel{
	 Set<FoodItemDomainModel> foods;
	 CartTotal total;
	 UUID userId;
	
	public Set<FoodItemDomainModel> getFoodItems(){
		return Collections.unmodifiableSet(this.foods);
	}
	
	public CartDomainModel(Set<FoodItemDomainModel> foods, double total, String userId) {
		this.foods = foods;
		this.total = new CartTotal(total);
		this.userId = UUID.fromString(userId);
	}
	
	public CartDomainModel(String userId) {
		this.userId = UUID.fromString(userId);
		this.foods = new HashSet<>();
		this.total = new CartTotal(0);
	}
	 
	public boolean addFood(FoodName food, FoodPrice price) {
		FoodItemDomainModel foodItem = new FoodItemDomainModel(food.name());
		foodItem.foodPrice = price;
		if(foods.add(foodItem)) {
			total = new CartTotal(total.value() + price.value());
			return true;
		}
		return false;
	}
	
	public void incrementQuantity(FoodName food) {
		this.foods.stream()
			.filter(t -> t.getFoodName().equals(food))
				.findAny()
					.ifPresent((t) -> {
						t.foodQuantity = new FoodQuantity(t.foodQuantity.value() + 1);
						total = new CartTotal(total.value() + t.foodPrice.value());
					});
	}
	
	public boolean foodExists(FoodName food) {
		return this.getFoodItems().stream()
			.filter(item -> item.getFoodName().equals(food))
				.findAny().isPresent();
	}	
	
	public void removeFood(FoodName food) {
		this.getFoodItems().stream().filter(item -> item.getFoodName().equals(food))
			.findAny()
				.ifPresent((item) -> {
					if(item.getFoodQuantity().value() > 1) {
						item.foodQuantity = new FoodQuantity(item.foodQuantity.value() - 1);
						total = new CartTotal(total.value() - item.foodPrice.value());
					}
					else {
						foods.remove(new FoodItemDomainModel(food.name()));
						total = new CartTotal(total.value() - item.foodPrice.value());
					}
				}); 
	}
	
	public int getTotalNumberOfFoods() {
		return this.getFoodItems().stream()
			.mapToInt(t -> t.getFoodQuantity().value()).sum();
	}
	
	public boolean isCartEmpty() {
		return this.getFoodItems().isEmpty();
	}
	
	public boolean isEmpty() {
		return this.foods.isEmpty();
	}

	public UUID getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDomainModel other = (CartDomainModel) obj;
		return this.userId.equals(other.getUserId());
	}

	public CartTotal getTotal() {
		return total;
	}
}
