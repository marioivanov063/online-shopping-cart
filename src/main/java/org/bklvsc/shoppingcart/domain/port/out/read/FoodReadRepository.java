package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;

public interface FoodReadRepository {
	Optional<FoodDomainModel> getFood(FoodName name);
	Collection<FoodDomainModel> getFoods();
}
