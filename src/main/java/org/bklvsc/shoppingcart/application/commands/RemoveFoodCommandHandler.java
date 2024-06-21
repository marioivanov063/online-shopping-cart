package org.bklvsc.shoppingcart.application.commands;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.services.CartService;
import org.bklvsc.shoppingcart.application.services.UserService;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.CartWriteRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodWriteRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;

public class RemoveFoodCommandHandler implements CommandHandler<RemoveFoodCommand, Boolean>{
	private FoodReadRepository foodRepository;
	private FoodWriteRepository foodWriteRepository;
	private UserService userService;
	
	private RemoveFoodCommandHandler(FoodReadRepository foodRepository, UserService userService, FoodWriteRepository foodWriteRepository) {
		super();
		this.foodRepository = foodRepository;
		this.userService = userService;
		this.foodWriteRepository = foodWriteRepository;
	}

	@Override
	public Boolean handle(RemoveFoodCommand command) {
		FoodName foodName = new FoodName(command.foodName());
		Optional<Food> food = foodRepository.getFood(foodName);
		if(food.isEmpty())
	        return false;	
		foodWriteRepository.removeFood(food.get());
		userService.removeFoodFromEveryCart(foodName);
		return true;
	}

}
