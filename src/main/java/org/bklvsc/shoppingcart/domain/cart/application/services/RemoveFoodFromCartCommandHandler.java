package org.bklvsc.shoppingcart.domain.cart.application.services;

import java.util.UUID;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.ports.in.UserCartService;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.UserDomainModel;
import org.bklvsc.shoppingcart.domain.cart.domain_core.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
public class RemoveFoodFromCartCommandHandler implements CommandHandler<RemoveFoodFromCartCommand, Void>{
	private CartRepository cartRepository;
	
	public RemoveFoodFromCartCommandHandler(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public Void handle(RemoveFoodFromCartCommand command) {
		CartDomainModel cart = cartRepository.getCart(new UserId(command.userId()));
		if(cart == null)
		   	return null;
		else {
		    cart.removeFood(new FoodName(command.foodName()));
		    if(cart.isCartEmpty()) {
		    	cartRepository.remove(cart);
		    	return null;
		    }
		}
		cartRepository.save(cart);
		return null;
	}
}
