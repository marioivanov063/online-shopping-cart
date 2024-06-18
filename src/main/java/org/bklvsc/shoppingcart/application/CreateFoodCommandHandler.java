package org.bklvsc.shoppingcart.application;

import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;

public class CreateFoodCommandHandler implements CommandHandler<CreateFoodCommand, Food> {
	private FoodRepository foodRepository;
	
	public CreateFoodCommandHandler(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	@Override
	public Food handle(CreateFoodCommand command) {
		
		FoodName foodName = new FoodName(command.foodName());
		foodRepository.getFood(foodName)
			.ifPresent((f) -> { 
				throw new FoodAlreadyExists(f.getFoodName());
			});
		FoodPrice price = new FoodPrice(command.price());
		Food food = Food.createFood(foodName, price);
		foodRepository.saveFood(food);
		return food;
	}

}
