package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.port.out.read.CartReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.read.UserReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.CartWriteRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.UserWriteRepository;
import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.mockito.ArgumentMatchers;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserService{
	private CartReadRepository cartReadRepository;
	private CartWriteRepository cartWriteRepository;
	private UserReadRepository userReadRepository;
	private UserWriteRepository userWriteRepository;

	public UserServiceImpl(CartWriteRepository cartWriteRepository, CartReadRepository cartReadRepository, UserReadRepository userReadRepository, UserWriteRepository userWriteRepository) {
		this.cartWriteRepository = cartWriteRepository;
		this.userReadRepository = userReadRepository;
		this.cartReadRepository = cartReadRepository;
		this.userWriteRepository = userWriteRepository;
	}

	@Override
	public Boolean addFoodToUsersCart(FoodName foodName, UserId userId) {
		User user = userReadRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		Cart cart = null;
        if(user.getCartId() == null) {
        	cart = Cart.with(foodName);
        	user.assignCart(cart.getCartId());	
        	cartWriteRepository.saveCart(cart);
        	return Boolean.TRUE;
        }
        
        cart = cartReadRepository.getCart(user.getCartId());
        boolean added = cart.addFood(foodName);
        if(added)
        	cartWriteRepository.saveCart(cart);
        return added;
	}

	@Override
	public Boolean removeFoodFromUsersCart(FoodName foodName, UserId userId) {
		User user = userReadRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		return removeFoodFromUsersCart(foodName, user);
	}

	@Override
	public void removeFoodFromEveryCart(FoodName name) {
		userReadRepository.getAllUsers()
			.forEach(user -> this.removeFoodFromUsersCart(name, user));
	}
	
	private Boolean removeFoodFromUsersCart(FoodName foodName, User user) {
		if(user.getCartId() == null)
        	return Boolean.FALSE;
        Cart cart = cartReadRepository.getCart(user.getCartId());
        Boolean remove = cart.removeFood(foodName);
        cartWriteRepository.saveCart(cart);
        if(cart.getFoods().size() == 0) {
        	user.assignCart(null);
        	userWriteRepository.saveUser(user);
        }      	
        return remove;
	}
}
