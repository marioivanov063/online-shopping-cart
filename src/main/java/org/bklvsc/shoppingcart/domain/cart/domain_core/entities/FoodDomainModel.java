package org.bklvsc.shoppingcart.domain.cart.domain_core.entities;


import java.util.Objects;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodStockQuantity;

public class FoodDomainModel{
	 private FoodName foodName;
	 private FoodPrice foodPrice;
	
	 public FoodDomainModel(String foodName, double foodPrice) {
		this.foodName = new FoodName(foodName);
		this.foodPrice = new FoodPrice(foodPrice);
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
		FoodDomainModel other = (FoodDomainModel) obj;
		return this.foodName.equals(other.getFoodName());
	}
}
