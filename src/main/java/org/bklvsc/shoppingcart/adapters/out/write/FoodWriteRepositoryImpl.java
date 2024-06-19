package org.bklvsc.shoppingcart.adapters.out.write;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bklvsc.shoppingcart.adapters.out.common.Foods;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodWriteRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.springframework.stereotype.Repository;

@Repository
class FoodWriteRepositoryImpl implements FoodWriteRepository{
	@Override
	public Food saveFood(Food food) {
		Foods.foods.remove(food);
		Foods.foods.add(food);
		return food;
		
	}

	@Override
	public Collection<Food> getFoods() {
		return Foods.foods;
	}

}
