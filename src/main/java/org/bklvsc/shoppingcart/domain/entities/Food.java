package org.bklvsc.shoppingcart.domain.entities;


import java.util.Objects;

import org.bklvsc.shoppingcart.domain.commons.Identity;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;

public class Food{
	private FoodName foodName;
	private FoodPrice foodPrice;
	
	private Food(FoodName foodName, FoodPrice foodPrice) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}
	
	public static Food createFood(FoodName name, FoodPrice price) {	
		return new Food(name, price);
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
