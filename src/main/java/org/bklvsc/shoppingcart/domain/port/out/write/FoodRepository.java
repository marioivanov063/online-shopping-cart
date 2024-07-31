package org.bklvsc.shoppingcart.domain.port.out.write;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;

public interface FoodRepository {
	void save(FoodDomainBehaviour food);
	void remove(FoodName foodName);
	Optional<FoodDomainBehaviour> getFood(FoodName food);
}
