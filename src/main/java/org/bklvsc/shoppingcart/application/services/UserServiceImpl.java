package org.bklvsc.shoppingcart.application.services;

import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.port.out.CartRepository;
import org.bklvsc.shoppingcart.domain.port.out.UserRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;
import org.mockito.ArgumentMatchers;

public class UserServiceImpl implements UserService{
	private CartRepository cartRepository;
	private UserRepository userRepository;

	public UserServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Boolean addFoodToUsersCart(FoodName foodName, UserId userId) {
		User user = userRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		Cart cart = null;
        if(user.getCartId() == null) {
        	cart = Cart.with(foodName);
        	user.assignCart(cart.getCartId());	
        	cartRepository.saveCart(cart);
        	return Boolean.TRUE;
        }
        
        cart = cartRepository.getCart(user.getCartId());
        boolean added = cart.addFood(foodName);
        if(added)
        	cartRepository.saveCart(cart);
        return added;
	}

	@Override
	public Boolean removeFoodFromUsersCart(FoodName foodName, UserId userId) {
		User user = userRepository.getUser(userId)
				.orElseThrow(() -> new UserNotFoundException());
		return removeFoodFromUsersCart(foodName, user);
	}

	@Override
	public void removeFoodFromEveryCart(FoodName name) {
		userRepository.getAllUsers()
			.forEach(user -> this.removeFoodFromUsersCart(name, user));
	}
	
	private Boolean removeFoodFromUsersCart(FoodName foodName, User user) {
		if(user.getCartId() == null)
        	return Boolean.FALSE;
        Cart cart = cartRepository.getCart(user.getCartId());
        Boolean remove = cart.removeFood(foodName);
        cartRepository.saveCart(cart);
        if(cart.getFoods().size() == 0) {
        	user.assignCart(null);
        	userRepository.saveUser(user);
        }      	
        return remove;
	}
}
