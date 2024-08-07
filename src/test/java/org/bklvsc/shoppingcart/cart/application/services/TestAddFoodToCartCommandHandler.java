package org.bklvsc.shoppingcart.cart.application.services;

import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.cart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.cart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.FoodItemDomainModel;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestAddFoodToCartCommandHandler {
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private FoodRepository foodRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AddFoodToCartCommandHandler addFoodToCartCommandHandler;
	
	@Test
	public void shouldThrowUserNotFoundException() {
		given(userRepository.getUser(UUID.fromString("123e4567-e89b-42d3-a456-556642440000")))
			.willReturn(Optional.empty());
		
		Assertions.assertThrows(
				UserNotFoundException.class, 
				() -> addFoodToCartCommandHandler.handle(
						new AddFoodToCartCommand("baklava", UUID.fromString("123e4567-e89b-42d3-a456-556642440000"))));
	}
	
	@Test
	public void shouldThrowFoodNotFoundException() {
		given(userRepository.getUser(UUID.fromString("123e4567-e89b-42d3-a456-556642440000")))
			.willReturn(Optional.of(
					new UserDomainModel("123e4567-e89b-42d3-a456-556642440000", 
							"bklvsc", 
							null)));
		
		given(foodRepository.getFood("baklava"))
			.willReturn(Optional.empty());
		
		Assertions.assertThrows(
				FoodNotFoundException.class, 
				() -> addFoodToCartCommandHandler.handle(
						new AddFoodToCartCommand("baklava", UUID.fromString("123e4567-e89b-42d3-a456-556642440000"))));
	}
	
	@Test
	public void addFoodToCartTest1() {
		UUID userId = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
		given(userRepository.getUser(userId))
			.willReturn(Optional.of(
				new UserDomainModel("123e4567-e89b-42d3-a456-556642440000", 
						"bklvsc", 
						null)));
	
		given(foodRepository.getFood("baklava"))
			.willReturn(Optional.of(new FoodDomainModel("baklava", 5.5)));
		
		addFoodToCartCommandHandler
			.handle(new AddFoodToCartCommand("baklava", userId));
		CartDomainModel cart = cartRepository.getCart(userId);
		
		Assertions.assertTrue(cart.getFoodItems()
				.contains(new FoodItemDomainModel("baklava")));
		Assertions.assertTrue(cart.getTotal().value() == 5.5);
		
		addFoodToCartCommandHandler
			.handle(new AddFoodToCartCommand("Baklava", userId));
		cart = cartRepository.getCart(userId);
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 2);
		Assertions.assertTrue(cart.getTotal().value() == 11);	
	}
}
