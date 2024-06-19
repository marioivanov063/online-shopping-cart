package org.bklvsc.shoppingcart.application;

import java.util.Optional;

import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CommandHandler;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodWriteRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class TestCreateFoodCommand {
	@Autowired
	private CreateFoodCommandHandler createFoodCommandHandler;
			
	@Test
	public void TestCreateSameFoodTwice() {
		
		Food baklava = createFoodCommandHandler
				.handle(new CreateFoodCommand("baklava", 5.5));
		
		Assertions.assertThrows(FoodAlreadyExists.class, 
				() -> createFoodCommandHandler
						.handle(new CreateFoodCommand("Baklava", 5.5)));
		
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> createFoodCommandHandler
					.handle(new CreateFoodCommand("ba", 2)));
		
		Food chocolate = createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 4));
		
	}
	
	@Configuration
	@ComponentScan(basePackages = {"org.bklvsc.shoppingcart.application.services",
								  "org.bklvsc.shoppingcart.adapters.out.read",
								  "org.bklvsc.shoppingcart.adapters.out.write"})
	static class ConfigurationClass {
		@Autowired
		FoodWriteRepository foodWriteRepository;
		@Autowired
		FoodReadRepository foodReadRepository;
		
		@Bean
		public CreateFoodCommandHandler addFoodToCartCommandHandler() {
			return new CreateFoodCommandHandler(foodWriteRepository, foodReadRepository);
		}
	}
	
}


