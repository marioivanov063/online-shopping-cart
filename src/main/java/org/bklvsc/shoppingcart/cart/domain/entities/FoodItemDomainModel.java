package org.bklvsc.shoppingcart.cart.domain.entities;

import java.util.Objects;

import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;

public class FoodItemDomainModel{
	FoodName foodName;
	FoodQuantity foodQuantity;
	FoodPrice foodPrice;
	
	public FoodPrice getFoodPrice() {
		return foodPrice;
	}

	public FoodItemDomainModel(String foodName) {
		this.foodName = new FoodName(foodName);
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

