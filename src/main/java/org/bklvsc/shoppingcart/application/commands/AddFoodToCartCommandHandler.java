package org.bklvsc.shoppingcart.application.commands;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.application.services.UserCartService;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.in.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.UserRepository;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
public class AddFoodToCartCommandHandler implements CommandHandler<AddFoodToCartCommand, Void>{
	private FoodRepository foodRepository;
	private UserRepository userRepository;
	private UserCartService userCartService;
		
	public AddFoodToCartCommandHandler(FoodRepository foodRepository, UserCartService userCartService, UserRepository userRepository) {
		this.userCartService = userCartService;
		this.userRepository = userRepository;
		this.foodRepository = foodRepository;
	}	
	@Override
	public Void handle(AddFoodToCartCommand command) {
		UserId userId = new UserId(command.userId());
		FoodName foodName = new FoodName(command.foodName());
        /*FoodDomainBehaviour food = foodRepository.getFood(foodName)
        		.orElseThrow(FoodNotFoundException::new); */
        UserDomainBehaviour user = userRepository.getUser(userId)
        		.orElseThrow(UserNotFoundException::new); 
        userCartService.addFoodToUsersCart(foodName, user);
        return null;
	}
}
