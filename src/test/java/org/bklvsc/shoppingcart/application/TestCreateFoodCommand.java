package org.bklvsc.shoppingcart.application;

import java.util.List;

import org.bklvsc.shoppingcart.application.commands.CreateFoodCommandHandler;
import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.application.queries.GetFoodQueryHandler;
import org.bklvsc.shoppingcart.application.queries.GetFoodsQueryHandler;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodsQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.bklvsc.shoppingcart.domain.port.out.write.FoodWriteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class TestCreateFoodCommand {
	@Autowired
	private CreateFoodCommandHandler createFoodCommandHandler;
	@Autowired
	private GetFoodsQueryHandler getFoodsQueryHandler;
			
	@Test
	public void TestCreateSameFoodTwice() {
		
		Food baklava = createFoodCommandHandler
				.handle(new CreateFoodCommand("baklava", 5.5, 1));
		
		Assertions.assertThrows(FoodAlreadyExists.class, 
				() -> createFoodCommandHandler
						.handle(new CreateFoodCommand("Baklava", 5.5, 2)));
		
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> createFoodCommandHandler
					.handle(new CreateFoodCommand("ba", 2, 1)));
		
		Food chocolate = createFoodCommandHandler.handle(new CreateFoodCommand("chocolate", 4, 3));
		
		List<FoodDto> foods = getFoodsQueryHandler.handle(new GetFoodsQuery());
		Assertions.assertTrue(foods.size() == 2);
	}
	
	@Configuration
	@ComponentScan(basePackages = {"org.bklvsc.shoppingcart.application.services",
								  "org.bklvsc.shoppingcart.adapters.out.read",
								  "org.bklvsc.shoppingcart.adapters.out.write"})
	static class ConfigurationClass {
		FoodWriteRepository foodWriteRepository;
		FoodReadRepository foodReadRepository;
		
		public ConfigurationClass(FoodWriteRepository foodWriteRepository, FoodReadRepository foodReadRepository) {
			this.foodWriteRepository = foodWriteRepository;
			this.foodReadRepository = foodReadRepository;
		}

		@Bean
		public CreateFoodCommandHandler addFoodToCartCommandHandler() {
			return new CreateFoodCommandHandler(foodWriteRepository, foodReadRepository);
		}
		
		@Bean
		public GetFoodsQueryHandler getFoodsQueryHandler() {
			return new GetFoodsQueryHandler(foodReadRepository);
		}
	}	
}


