package org.bklvsc.shoppingcart.domain.cart.application.services;

import java.util.UUID;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.ports.in.UserCartService;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.UserDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
public class AddFoodToCartCommandHandler implements CommandHandler<AddFoodToCartCommand, Void>{
	private FoodRepository foodRepository;
	private UserRepository userRepository;
	private CartRepository cartRepository;
		
	public AddFoodToCartCommandHandler(FoodRepository foodRepository, CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.foodRepository = foodRepository;
	}	
	@Override
	public Void handle(AddFoodToCartCommand command) {
		UserDomainModel userExists = userRepository.getUser(UUID.fromString(command.userId()))
				.orElseThrow(UserNotFoundException::new);
		CartDomainModel cart = cartRepository.getCart(new UserId(command.userId()));
		
		if(cart == null){ //user does not have a cart
			cart = new CartDomainModel(command.userId());
		 	FoodDomainModel food = foodRepository.getFood(command.foodName())
        		.orElseThrow(FoodNotFoundException::new);
        	cart.addFood(food.getFoodName(), food.getFoodPrice());
		}
        else {
        	FoodName food = new FoodName(command.foodName());
        	if(cart.foodExists(food))
        		cart.incrementQuantity(food);
        	else {
        		FoodDomainModel foodModel = foodRepository.getFood(command.foodName())
            		.orElseThrow(FoodNotFoundException::new);
            	cart.addFood(foodModel.getFoodName(), foodModel.getFoodPrice());
        	}
        }
        cartRepository.save(cart);
        return null;
	}
}
