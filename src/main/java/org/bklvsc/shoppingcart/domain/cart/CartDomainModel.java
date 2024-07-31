package org.bklvsc.shoppingcart.domain.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartTotal;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.FoodQuantity;


public class CartDomainModel{
	 CartId cartId;
	 Set<FoodItem> foods;
	 CartTotal total;
	 UserId userId;
	
	public Set<FoodItem> getFoodItems(){
		return Collections.unmodifiableSet(this.foods);
	}
	
	CartDomainModel(String cartId, Set<FoodItem> foods, double total) {
		this.cartId = new CartId(cartId);
		this.foods = foods;
		this.total = new CartTotal(total);
	}
	
	 CartDomainModel() {
		this.cartId = new CartId();
		this.foods = new HashSet<>();
		this.total = new CartTotal(0);
	}
		
	
	public boolean isEmpty() {
		return this.foods.isEmpty();
	}

	public CartId getCartId() {
		return cartId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.cartId);
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
		return this.cartId.equals(other.getCartId());
	}
	
	public static class FoodItem{
		FoodName foodName;
		FoodQuantity foodQuantity;
		FoodPrice foodPrice;
		
		FoodItem(FoodName food) {
			super();
			this.foodName = food;
			this.foodQuantity = new FoodQuantity(1);
		}
		
		public FoodItem(String food, int foodQuantity, double foodPrice) {
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
			FoodItem other = (FoodItem) obj;
			return Objects.equals(foodName, other.foodName);
		}	
	}
}
