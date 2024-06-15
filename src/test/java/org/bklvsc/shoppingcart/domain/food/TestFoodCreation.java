package org.bklvsc.shoppingcart.domain.food;

import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestFoodCreation {
	@Test
	public void TestNameShouldNotBeNull() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> new FoodName(null));
	}
	
	@Test
	public void TestNameSizeShouldNotBeBlank() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> new FoodName(" "));
	}
	
	@Test
	public void TestNameSizeShouldBeBetween() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> new FoodName("ba"));
	}
	
	@Test 
	public void TestPriceShouldBeGreaterThan() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> new FoodPrice(-2));
	}
	
	@Test
	public void TestEquality() {
		Food food = Food.createFood(new FoodName("baklava"), new FoodPrice(3));
		Food food1 = Food.createFood(new FoodName("baklav"), new FoodPrice(3));
		Assertions.assertTrue(!food.equals(food1));
	}
}
