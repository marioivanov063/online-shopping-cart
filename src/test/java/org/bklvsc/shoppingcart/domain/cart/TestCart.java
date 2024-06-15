package org.bklvsc.shoppingcart.domain.cart;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.Food;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodName;
import org.bklvsc.shoppingcart.domain.valueobjects.FoodPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCart {
	private static Cart cart;
	
	@BeforeEach
	void setUp() {
		cart = Cart.with(new FoodName("baklava"));
	}
	
	@Test
	public void TestAddSameFood() {
		cart.addFood(new FoodName("baklava"));
		Assertions.assertTrue(cart.getFoods().size() == 1);
		
		cart.addFood(new FoodName("Baklava"));
		Assertions.assertTrue(cart.getFoods().size() == 1);
		
		cart.addFood(new FoodName("Chocolate"));
		Assertions.assertTrue(cart.getFoods().size() == 2);
	}
	
	@Test
	public void TestRemoveFood() {
		cart.removeFood(new FoodName("Baklava"));
		Assertions.assertTrue(cart.getFoods().size() == 0);
	}
	
	@Test
	public void TestEquality() {
		Cart cart0 = Cart.with(new FoodName("baklava"));
		Assertions.assertTrue(!cart0.equals(cart));
	}
	
	
	
}
