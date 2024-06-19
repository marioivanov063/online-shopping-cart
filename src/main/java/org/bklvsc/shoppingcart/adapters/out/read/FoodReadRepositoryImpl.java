package org.bklvsc.shoppingcart.adapters.out.read;

import java.util.Optional;

import org.bklvsc.shoppingcart.adapters.out.common.Foods;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.springframework.stereotype.Repository;

@Repository
class FoodReadRepositoryImpl implements FoodReadRepository{
	@Override
	public Optional<Food> getFood(FoodName name) {
		return Foods.foods.stream().filter(f -> f.getFoodName().equals(name))
			.findAny();
	}
}
