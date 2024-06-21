package org.bklvsc.shoppingcart.domain.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.domain.commons.Identity;
import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.mockito.ArgumentMatchers;

public class Cart{
	private CartId cartId;
	private Map<FoodName, Integer> foods;

	private Cart(CartId cartId, Map<FoodName, Integer> foods) {
		super();
		this.cartId = cartId;
		this.foods = foods;
	}
	
	public static Cart with(FoodName foodName) {
		Map<FoodName, Integer> foods = new ConcurrentHashMap<>();
		foods.put(foodName, 1);
		return new Cart(CartId.createNewId(), foods);
	}
	
	public Map<FoodName, Integer> getFoods(){
		return this.foods;
	}
	
	public boolean addFood(FoodName food) {
		this.foods.merge(food, 1, Integer::sum);
		return true;
	}
	
	public boolean removeFood(FoodName food) {
		if(!this.foods.containsKey(food))
			return false;
		this.foods.put(food, foods.get(food) - 1);
		if(this.foods.get(food) == 0)
			this.foods.remove(food);
		return true;
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
