package org.bklvsc.shoppingcart.application.commands;

import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateFoodCommandHandler implements CommandHandler<CreateFoodCommand, Void> {
	private FoodRepository foodRepository;
	
	public CreateFoodCommandHandler(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	@Override
	public Void handle(CreateFoodCommand command) {
		FoodName foodName = new FoodName(command.foodName());
		foodRepository.getFood(foodName)
			.ifPresent((f) -> { 
				throw new FoodAlreadyExists(foodName);
			});
		FoodDomainBehaviour food = 
				new FoodDomainBehaviour(
						new FoodName(command.foodName()), 
						new FoodPrice(command.price()));
		foodRepository.save(food);
		return null;
	}

}
