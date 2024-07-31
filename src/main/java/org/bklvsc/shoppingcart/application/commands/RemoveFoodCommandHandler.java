package org.bklvsc.shoppingcart.application.commands;

import java.util.Optional;
import org.bklvsc.shoppingcart.application.services.UserCartService;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveFoodCommandHandler implements CommandHandler<RemoveFoodCommand, Void>{
	private FoodRepository foodRepository;
	private UserCartService userService;
	private UserRepository userRepository;
	
	private RemoveFoodCommandHandler(UserCartService userService, FoodRepository foodRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.foodRepository = foodRepository;
		this.userService = userService;
	}

	@Override
	public Void handle(RemoveFoodCommand command) {
		FoodName foodName = new FoodName(command.foodName());
		Optional<FoodDomainBehaviour> food = foodRepository.getFood(foodName);
		if(food.isEmpty())
	        return null;	
		foodRepository.remove(foodName);
		userRepository.getAllUsers()
			.stream()
				.forEach(user -> userService.removeFoodFromUsersCart(foodName, user));
		return null;
	}

}
