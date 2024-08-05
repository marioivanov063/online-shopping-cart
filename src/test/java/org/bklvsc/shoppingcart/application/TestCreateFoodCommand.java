package org.bklvsc.shoppingcart.application;

import java.util.List;

import org.bklvsc.shoppingcart.application.commands.CreateFoodCommandHandler;
import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.application.queries.GetFoodsQueryHandler;
import org.bklvsc.shoppingcart.domain.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.cart.persistence.FoodRepositoryImpl;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;

import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodsQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles(value = "test")
public class TestCreateFoodCommand {
	@Value("${spring.datasource.url}")
    private String propertyString;
	@Autowired
	private CreateFoodCommandHandler createFoodCommandHandler;
	@Autowired
	private GetFoodsQueryHandler getFoodsQueryHandler;
	
	@Test
	public void testValueForProperty() {
		Assertions.assertEquals("jdbc:mysql://localhost:5222/shopping_cart_test", propertyString);
	}
		
	@Transactional
	@Test
	public void TestCreateSameFoodTwice() {
		
		createFoodCommandHandler
			.handle(new CreateFoodCommand("baklava", 5.5));
		
		Assertions.assertThrows(FoodAlreadyExists.class, 
				() -> createFoodCommandHandler
						.handle(new CreateFoodCommand("Baklava", 5.5)));
		
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> createFoodCommandHandler
					.handle(new CreateFoodCommand("ba", 2)));
		
		createFoodCommandHandler
			.handle(new CreateFoodCommand("chocolate", 4));
		
		List<FoodDto> foods = getFoodsQueryHandler.handle(new GetFoodsQuery());
		Assertions.assertTrue(foods.size() == 2);
	}
	
	/*@Configuration
	@ComponentScan(basePackages = {"org.bklvsc.shoppingcart.adapters.out.write"})
	static class ConfigurationClass {
		@Mock
		JdbcTemplate jdbcTemplate;
		@Bean
		public CreateFoodCommandHandler addFoodToCartCommandHandler(FoodRepositoryImpl foodRepositoryImpl) {
			return new CreateFoodCommandHandler(foodRepositoryImpl);
		}
		
		@Bean
		public GetFoodsQueryHandler getFoodsQueryHandler(JdbcTemplate template) {
			return new GetFoodsQueryHandler(template);
		}
	}	*/
}


