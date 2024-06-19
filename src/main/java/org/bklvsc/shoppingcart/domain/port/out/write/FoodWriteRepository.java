package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface FoodWriteRepository {
	Food saveFood(Food food);
	Collection<Food> getFoods();
}
