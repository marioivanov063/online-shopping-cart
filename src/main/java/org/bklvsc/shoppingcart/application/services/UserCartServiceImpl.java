package org.bklvsc.shoppingcart.application.services;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.exceptions.NoCartException;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.port.out.write.CartRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.UserRepository;
import org.bklvsc.shoppingcart.domain.cart.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.UserDomainModel;
import org.springframework.stereotype.Service;

@Service
class UserCartServiceImpl implements UserCartService{
	/*@Autowired
	private DomainEventPublisher publisher;*/
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private FoodRepository foodRepository;

	public UserCartServiceImpl(CartRepository cartRepository, UserRepository userRepository, FoodRepository foodRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.foodRepository = foodRepository;
	}

	@Override
	public void addFoodToUsersCart(FoodName food, UserDomainBehaviour user) {
		CartDomainBehaviour cart;
        if(user.getModel().getCart() == null) {
        	cart = new CartDomainBehaviour();
        	user.assignCart(cart.getModel().getCartId());
        	this.userRepository.save(user);
        	/*
        	 * food is for sure not present in the cart as the cart 
 			 * is created now, therefore, fetch it from food repository 
 			 * with name and price
        	 */
        	FoodDomainBehaviour foodBehaviour = foodRepository.getFood(food)
        			.orElseThrow(FoodNotFoundException::new);
        	cart.addFood(food, foodBehaviour.getModel().getFoodPrice());
        }
        else {
        	//get cart
        	cart = cartRepository.getCart(user.getModel().getCart())
            		.orElseThrow(NoCartException::new);
        	/*
        	 * if food is present in the cart, update total based on the price
        	 * of the item IN THE CART
        	 */
        	if(cart.foodExists(food))
        		cart.incrementQuantity(food);
        	/*
        	 * if food is not present in the cart, fetch it from food repository
        	 * with name and price
        	 */
        	else {
        		FoodDomainBehaviour foodBehaviour = foodRepository.getFood(food)
            			.orElseThrow(FoodNotFoundException::new);
            	cart.addFood(food, foodBehaviour.getModel().getFoodPrice());
        	}
        }	
        cartRepository.save(cart);
	}
	
	@Override
	public void removeFoodFromUsersCart(FoodName food, UserDomainBehaviour user) {
		CartId cartId;
		/*
		 * if the user don't have a cart don't bother and simply return
		 */
		if((cartId = user.getModel().getCart()) == null)
        	return;
        CartDomainBehaviour cart = cartRepository.getCart(cartId)
        		.orElseThrow(NoCartException::new);
        cart.removeFood(food);
        
        /*
         * change across aggregates 
         * (cart.getDomainEvents.forEach(publisher::publishEvent))
         */
        if(cart.getModel().getFoodItems().isEmpty()) { 
        	cartRepository.save(cart);
        	user.unassignCart();
        	userRepository.save(user);
        }
        else
        	cartRepository.save(cart);
	}
}
