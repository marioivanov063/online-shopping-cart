package org.bklvsc.shoppingcart.domain.port.out;

import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface FoodRepository {
	public Optional<Food> getFood(FoodName name);
	public Food saveFood(Food food);
}
