package org.bklvsc.shoppingcart.application;

import org.bklvsc.shoppingcart.application.commands.AddFoodToCartCommandHandler;
import org.bklvsc.shoppingcart.application.commands.CreateFoodCommandHandler;
import org.bklvsc.shoppingcart.application.commands.CreateUserCommandHandler;
import org.bklvsc.shoppingcart.application.commands.RemoveFoodFromCartCommandHandler;
import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.application.queries.GetUsersCartQueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateUserCommand;
import org.bklvsc.shoppingcart.domain.port.in.commands.RemoveFoodFromCartCommand;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetUsersCartQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.CartDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles(value = "test")
public class TestRemoveFoodFromCartCommand {
	@Autowired
	private AddFoodToCartCommandHandler addFoodToCartCommandHandler;
	@Autowired
	private CreateFoodCommandHandler createFoodCommandHandler;
	@Autowired
	private CreateUserCommandHandler createUserCommandHandler;
	@Autowired
	private GetUsersCartQueryHandler getUsersCartQueryHandler;
	@Autowired
	private RemoveFoodFromCartCommandHandler removeFoodFromCartCommandHandler;
	
	@Transactional
	@Test
	public void shouldThrowUserNotFoundException() {
		createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 6.50));
		RemoveFoodFromCartCommand command = new RemoveFoodFromCartCommand("chocolate", "e2f8dd48-4a8b-4d1a-929d-93c1b4c59b73");
		Assertions.assertThrows(UserNotFoundException.class,
				() -> this.removeFoodFromCartCommandHandler.handle(command));
	}
	
	@Transactional
	@Test
	public void testRemoveFoodFromCart1() {
		String userId = createUserCommandHandler.handle(new CreateUserCommand("John"));
		createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 6.50));
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("chocolate", userId));
		
		removeFoodFromCartCommandHandler
			.handle(new RemoveFoodFromCartCommand("Chocolate", userId));
		CartDto cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertNull(cart);
	}
	
	@Transactional
	@Test
	public void testRemoveFoodFromCart2() {
		String userId = createUserCommandHandler.handle(new CreateUserCommand("John"));
		createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 6.50));
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("chocolate", userId));
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("chocolate", userId));
		
		removeFoodFromCartCommandHandler
			.handle(new RemoveFoodFromCartCommand("Chocolate", userId));
		CartDto cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertEquals(cart.cartTotal(), 6.50);
		
		removeFoodFromCartCommandHandler
			.handle(new RemoveFoodFromCartCommand("Chocolate", userId));
		cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertNull(cart);
	}
}
