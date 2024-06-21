package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public interface FoodReadRepository {
	Optional<Food> getFood(FoodName name);
	Collection<Food> getFoods();
}
