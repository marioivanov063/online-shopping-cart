package org.bklvsc.shoppingcart.domain.cart.domain_core.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartTotal;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;


public class CartDomainModel{
	 Set<FoodItemDomainModel> foods;
	 CartTotal total;
	 UserId userId;
	
	public Set<FoodItemDomainModel> getFoodItems(){
		return Collections.unmodifiableSet(this.foods);
	}
	
	public CartDomainModel(Set<FoodItemDomainModel> foods, double total, String userId) {
		this.foods = foods;
		this.total = new CartTotal(total);
		this.userId = new UserId(userId);
	}
	
	public CartDomainModel(String userId) {
		this.userId = new UserId(userId);
		this.foods = new HashSet<>();
		this.total = new CartTotal(0);
	}
	 
	public boolean addFood(FoodName food, FoodPrice price) {
		FoodItemDomainModel foodItem = new FoodItemDomainModel(food);
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
						foods.remove(new FoodItemDomainModel(food));
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

	public UserId getOwner() {
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
		return this.userId.equals(other.getOwner());
	}
}
