package org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects;

import java.util.Objects;

import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;

public class FoodInfo {
	private FoodName foodName;
	private FoodPrice foodPrice;
	
	public FoodInfo(FoodName foodName, FoodPrice foodPrice) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}

	public FoodName getFoodName() {
		return foodName;
	}

	public FoodPrice getFoodPrice() {
		return foodPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(foodName, foodPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodInfo other = (FoodInfo) obj;
		return Objects.equals(foodName, other.foodName);
	}
	
	
	
	
	
}
