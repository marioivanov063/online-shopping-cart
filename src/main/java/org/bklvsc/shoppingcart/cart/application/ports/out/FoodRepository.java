package org.bklvsc.shoppingcart.cart.application.ports.out;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.cart.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;

public interface FoodRepository {
	Optional<FoodDomainModel> getFood(String food);
}
