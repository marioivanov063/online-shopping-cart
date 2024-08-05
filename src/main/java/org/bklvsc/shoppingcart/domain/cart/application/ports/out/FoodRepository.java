package org.bklvsc.shoppingcart.domain.cart.application.ports.out;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;

public interface FoodRepository {
	Optional<FoodDomainModel> getFood(String food);
}
