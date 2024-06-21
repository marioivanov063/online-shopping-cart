package org.bklvsc.shoppingcart.application.commands;

import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodWriteRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodQuantity;

public class CreateFoodCommandHandler implements CommandHandler<CreateFoodCommand, Food> {
	private FoodWriteRepository foodWriteRepository;
	private FoodReadRepository foodReadRepository;
	
	public CreateFoodCommandHandler(FoodWriteRepository foodWriteRepository, FoodReadRepository foodReadRepository) {
		this.foodWriteRepository = foodWriteRepository;
		this.foodReadRepository = foodReadRepository;
	}

	@Override
	public Food handle(CreateFoodCommand command) {
		FoodName foodName = new FoodName(command.foodName());
		foodReadRepository.getFood(foodName)
			.ifPresent((f) -> { 
				throw new FoodAlreadyExists(f.getFoodName());
			});
		Food food = Food.createFood(command.foodName(), command.price(), command.quantity());
		foodWriteRepository.saveFood(food);
		return food;
	}

}
