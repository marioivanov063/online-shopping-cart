package org.bklvsc.shoppingcart.application;

import org.bklvsc.shoppingcart.adapters.out.FoodRepositoryImpl;
import org.bklvsc.shoppingcart.application.exceptions.FoodAlreadyExists;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.port.in.commands.CreateFoodCommand;
import org.bklvsc.shoppingcart.domain.port.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.bytebuddy.asm.Advice.This;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

public class TestCreateFoodCommand {
	private CreateFoodCommandHandler createFoodCommandHandler;
			
	@BeforeEach
	void setUp() {
		this.createFoodCommandHandler = 
				new CreateFoodCommandHandler(new FoodRepositoryImpl());
	}
	
	@Test
	public void TestCreateNewFood() {
		Food food = createFoodCommandHandler
			.handle(new CreateFoodCommand("baklava", 5.5));
		
		/*Food foodFromRepoFood = foodRepository.getFood(new FoodName("baklava")).orElse(null);	
		Assertions.assertEquals(food, foodFromRepoFood);	*/
	}
	
	@Test
	public void TestCreateSameFoodTwice() {
		Food food = createFoodCommandHandler
				.handle(new CreateFoodCommand("baklava", 5.5));
		
		Assertions.assertThrows(FoodAlreadyExists.class, 
				() -> createFoodCommandHandler
						.handle(new CreateFoodCommand("baklava", 5.5)));
	}
	
}
