package org.bklvsc.shoppingcart.commons.persistence;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.catalog.domain.entities.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.catalog.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;

public interface FoodRepository {
	void save(FoodDomainBehaviour food);
	void remove(FoodName foodName);
	Optional<FoodDomainBehaviour> getFood(FoodName food);
}
