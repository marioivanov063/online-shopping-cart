package org.bklvsc.shoppingcart.domain.cart.domain_core.entities;

import java.util.Objects;

import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.FoodQuantity;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;

public class FoodItemDomainModel{
	FoodName foodName;
	FoodQuantity foodQuantity;
	FoodPrice foodPrice;
	
	public FoodPrice getFoodPrice() {
		return foodPrice;
	}

	FoodItemDomainModel(FoodName food) {
		this.foodName = food;
		this.foodQuantity = new FoodQuantity(1);
	}
	
	public FoodItemDomainModel(String food, int foodQuantity, double foodPrice) {
		this.foodName = new FoodName(food);
		this.foodQuantity = new FoodQuantity(foodQuantity);
		this.foodPrice = new FoodPrice(foodPrice);
	}

	//should probably be changed
	@Override
	public int hashCode() {
		return Objects.hash(this.foodName);
	}

	public FoodName getFoodName() {
		return this.foodName;
	}

	public FoodQuantity getFoodQuantity() {
		return foodQuantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodItemDomainModel other = (FoodItemDomainModel) obj;
		return Objects.equals(foodName, other.foodName);
	}	
}

