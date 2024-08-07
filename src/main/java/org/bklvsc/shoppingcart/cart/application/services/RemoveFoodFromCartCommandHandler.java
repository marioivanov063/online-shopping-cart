package org.bklvsc.shoppingcart.cart.application.services;

import java.lang.invoke.VolatileCallSite;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.ports.in.UserCartService;
import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.commons.application.CommandHandler;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.bklvsc.shoppingcart.user.domain.entities.UserDomainBehaviour;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

@Service
public class RemoveFoodFromCartCommandHandler implements CommandHandler<RemoveFoodFromCartCommand, CartDomainModel>{
	private CartRepository cartRepository;
	private UserRepository userRepository;
	
	public RemoveFoodFromCartCommandHandler(CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}

	@Override
	public CartDomainModel handle(RemoveFoodFromCartCommand command) {
		UserDomainModel userExists = userRepository.getUser(command.userId())
				.orElseThrow(UserNotFoundException::new);
		CartDomainModel cart = cartRepository.getCart(command.userId());
		if(cart == null)
		   	return null;
		else {
		    cart.removeFood(new FoodName(command.foodName()));
		    if(cart.isCartEmpty()) { 
		    	 cartRepository.remove(cart);
		    	 return null;
		    }
		}
		return cartRepository.save(cart);
	}
}
