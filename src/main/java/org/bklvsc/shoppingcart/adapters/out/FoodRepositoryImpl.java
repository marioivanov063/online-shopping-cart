package org.bklvsc.shoppingcart.adapters.out;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
	static Set<Food> foods = new HashSet<>();
		
	@Override
	public Optional<Food> getFood(FoodName name) {
		return foods.stream().filter(f -> f.getFoodName().equals(name))
			.findAny();
	}

	@Override
	public Food saveFood(Food food) {
		foods.remove(food);
		foods.add(food);
		return food;
		
	}

}
