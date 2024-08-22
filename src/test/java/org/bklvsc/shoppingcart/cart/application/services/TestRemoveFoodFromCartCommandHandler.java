package org.bklvsc.shoppingcart.cart.application.services;

import static org.mockito.BDDMockito.given;

import java.security.PublicKey;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bklvsc.shoppingcart.cart.application.dtos.CartDto;
import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestRemoveFoodFromCartCommandHandler {
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private FoodRepository foodRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AddFoodToCartCommandHandler addFoodToCartCommandHandler;
	@Autowired
	private RemoveFoodFromCartCommandHandler removeFoodFromCartCommandHandler;
	private UUID userId = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
	
	@Test
	public void shouldThrowUserNotFoundException() {
		given(userRepository.getUser(userId))
			.willReturn(Optional.empty());
	
		Assertions.assertThrows(
			UserNotFoundException.class, 
			() -> removeFoodFromCartCommandHandler.handle(
					new RemoveFoodFromCartCommand("baklava", userId)));
	}
	
	@Test
	public void shouldReturnNull() {
		/*given(userRepository.getUser(userId))
		.willReturn(Optional.of(
				new UserDomainModel(userId.toString(), 
						"bklvsc", 
						null)));*/
		
		CartDto cart = removeFoodFromCartCommandHandler.handle(
				new RemoveFoodFromCartCommand("baklava", userId));
		Assertions.assertNull(cart);
		
		CartDomainBehaviour cartDomainBehaviour = cartRepository.getCart(userId);
		Assertions.assertNull(cartDomainBehaviour);
	}
	
	@Test
	public void removeFoodFromCart() {
		/*given(userRepository.getUser(userId))
			.willReturn(Optional.of(
				new UserDomainModel(userId.toString(), 
						"bklvsc", 
						null)));*/
		
		given(foodRepository.getFood("baklava"))
			.willReturn(Optional.of(new FoodDomainModel("baklava", 5.5)));
		
		addFoodToCartCommandHandler
			.handle(new AddFoodToCartCommand("baklava", userId));

		CartDto cart = removeFoodFromCartCommandHandler
			.handle(new RemoveFoodFromCartCommand("baklava", userId));
		Assertions.assertNull(cart);
		
		addFoodToCartCommandHandler
			.handle(new AddFoodToCartCommand("baklava", userId));
		addFoodToCartCommandHandler
			.handle(new AddFoodToCartCommand("Baklava", userId));
		cart = removeFoodFromCartCommandHandler
			.handle(new RemoveFoodFromCartCommand("baklava", userId));
		
		Assertions.assertTrue(cart.foods().size() == 1);
		Assertions.assertTrue(cart.cartTotal() == 5.5);
	}
	
	@Test
	public void concurrentAccessToCart() {
		given(userRepository.getUser(userId))
			.willReturn(Optional.of(
					new UserDomainModel(userId.toString(), 
					"bklvsc", 
					null)));
		
		given(foodRepository.getFood("baklava"))
			.willReturn(Optional.of(new FoodDomainModel("baklava", 5.5)));
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Thread addThread = new Thread(() -> addFoodToCartCommandHandler
				.handle(new AddFoodToCartCommand("baklava", userId)));
		Thread removeThread = new Thread(() -> removeFoodFromCartCommandHandler
				.handle(new RemoveFoodFromCartCommand("baklava", userId)));
		
		executor.submit(addThread);
		executor.submit(addThread);
		executor.submit(removeThread);
		
	}
}
