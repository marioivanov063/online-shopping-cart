package org.bklvsc.shoppingcart.domain.entities;


import java.util.Objects;

import org.bklvsc.shoppingcart.domain.commons.Identity;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodQuantity;

public class Food{
	private FoodName foodName;
	private FoodPrice foodPrice;
	private FoodQuantity foodQuantity;
	
	private Food(FoodName foodName, FoodPrice foodPrice, FoodQuantity foodQuantity) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodQuantity = foodQuantity;
	}
	
	public FoodQuantity getFoodQuantity() {
		return foodQuantity;
	}

	public void incrementStock(Integer quantity) {
		this.foodQuantity = 
				new FoodQuantity(this.foodQuantity.quantity() + quantity);
	}
	
	public static Food createFood(String name, double price, int quantity) {
		FoodName foodName = new FoodName(name);
		FoodPrice foodPrice = new FoodPrice(price);
		FoodQuantity foodQuantity = new FoodQuantity(quantity);
		return new Food(foodName, foodPrice, foodQuantity);
	}

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
		Food other = (Food) obj;
		return this.foodName.equals(other.getFoodName());
	}
}
