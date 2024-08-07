package org.bklvsc.shoppingcart.catalog.domain.entities;


import java.util.Objects;

import org.bklvsc.shoppingcart.catalog.domain.valueobjects.FoodStockQuantity;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;

public class FoodDomainModel{
	 private FoodName foodName;
	 private FoodPrice foodPrice;
	/*private FoodStockQuantity foodQuantity;*/
	
	FoodDomainModel(String foodName, double foodPrice /*FoodStockQuantity foodQuantity*/) {
		this.foodName = new FoodName(foodName);
		this.foodPrice = new FoodPrice(foodPrice);
		/*this.foodQuantity = foodQuantity;*/
	}
	
	public FoodDomainModel(FoodName foodName, FoodPrice foodPrice) {
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}
	
	
	
	
	/*public FoodStockQuantity getFoodQuantity() {
		return foodQuantity;
	}*/

	/*public static FoodDomainModel createFood(String name, double price, int quantity) {
		FoodName foodName = new FoodName(name);
		FoodPrice foodPrice = new FoodPrice(price);
		FoodStockQuantity foodQuantity = new FoodStockQuantity(quantity);
		FoodDomainModel food = new FoodDomainModel(foodName, foodPrice, foodQuantity);
		return food;
	}*/

	public FoodName getFoodName() {
		return foodName;
	}

	public FoodPrice getFoodPrice() {
		return foodPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.foodName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodDomainModel other = (FoodDomainModel) obj;
		return this.foodName.equals(other.getFoodName());
	}
}
