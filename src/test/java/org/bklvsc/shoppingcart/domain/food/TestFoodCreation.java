package org.bklvsc.shoppingcart.domain.food;

import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodPrice;
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
	
	/*@Test
	public void TestEquality() {
		FoodDomainModel food = FoodDomainModel.createFood("baklava", 5, 1);
		FoodDomainModel food1 = FoodDomainModel.createFood("baklav", 5, 1);
		Assertions.assertTrue(!food.equals(food1));
	}*/
}
