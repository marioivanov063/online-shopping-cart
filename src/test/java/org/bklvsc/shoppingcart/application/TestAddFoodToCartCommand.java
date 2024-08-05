package org.bklvsc.shoppingcart.application;

import org.bklvsc.shoppingcart.application.commands.CreateFoodCommandHandler;
import org.bklvsc.shoppingcart.application.commands.CreateUserCommandHandler;
import org.bklvsc.shoppingcart.application.exceptions.FoodNotFoundException;
import org.bklvsc.shoppingcart.application.exceptions.UserNotFoundException;
import org.bklvsc.shoppingcart.application.queries.GetUsersCartQueryHandler;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.commands.AddFoodToCartCommand;
import org.bklvsc.shoppingcart.domain.cart.application.dtos.queries.GetUsersCartQuery;
import org.bklvsc.shoppingcart.domain.cart.application.services.AddFoodToCartCommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateUserCommand;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.CartDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles(value = "test")
public class TestAddFoodToCartCommand {
	@Autowired
	private AddFoodToCartCommandHandler addFoodToCartCommandHandler;
	@Autowired
	private CreateFoodCommandHandler createFoodCommandHandler;
	@Autowired
	private CreateUserCommandHandler createUserCommandHandler;
	@Autowired
	private GetUsersCartQueryHandler getUsersCartQueryHandler;
	
	@Transactional
	@Test
	public void shouldThrowUserNotFoundException() {
		createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 6.50));
		AddFoodToCartCommand command = new AddFoodToCartCommand("chocolate", "e2f8dd48-4a8b-4d1a-929d-93c1b4c59b73");
		Assertions.assertThrows(UserNotFoundException.class,
				() -> this.addFoodToCartCommandHandler.handle(command));
	}
	
	@Transactional
	@Test
	public void testAddFoodToUsersCartMethod() {
		String userId = createUserCommandHandler.handle(new CreateUserCommand("John"));
		createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 6.50));
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("chocolate", userId));
		
		CartDto cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertNotNull(cart);
		Assertions.assertEquals("chocolate", cart.foods().get(0).foodName());
		Assertions.assertEquals(6.50, cart.cartTotal());
		
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("Chocolate", userId));
		cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertEquals(2, cart.foods().getFirst().quantity());
		Assertions.assertEquals(13, cart.cartTotal());
		
		createFoodCommandHandler.handle(new CreateFoodCommand("baklava", 5.50));
		addFoodToCartCommandHandler.handle(new AddFoodToCartCommand("baklava", userId));
		cart = getUsersCartQueryHandler.handle(new GetUsersCartQuery(userId));
		Assertions.assertEquals(18.5, cart.cartTotal());
	}
	
}
