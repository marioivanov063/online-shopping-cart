package org.bklvsc.shoppingcart.domain.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.domain.commons.Identity;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class Cart{
	private CartId cartId;
	private Set<FoodName> foods;

	private Cart(CartId cartId, Set<FoodName> foods) {
		super();
		this.cartId = cartId;
		this.foods = foods;
	}
	
	public static Cart with(FoodName foodName) {
		Set<FoodName> foods = new HashSet<>();
		foods.add(foodName);
		return new Cart(CartId.createNewId(), foods);
	}
	
	public Set<FoodName> getFoods(){
		return this.foods;
	}
	
	public boolean addFood(FoodName food) {
		return this.foods.add(food);
	}
	
	public boolean removeFood(FoodName food) {
		return this.foods.remove(food);
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
		Cart other = (Cart) obj;
		return this.cartId.equals(other.getCartId());
	}
}
