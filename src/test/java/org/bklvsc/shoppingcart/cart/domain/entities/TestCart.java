package org.bklvsc.shoppingcart.cart.domain.entities;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodPrice;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodQuantity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestCart {
	private static CartDomainModel cart;
	
	@BeforeEach
	void setUp() {
		cart = new CartDomainModel("123e4567-e89b-42d3-a456-556642440000");
	}
	
	@Test
	public void testAddFoodToCart() {
		cart.addFood(new FoodName("baklava"), new FoodPrice(5.5));
		Assertions.assertTrue(cart.getFoodItems().size() == 1);
		Assertions.assertTrue(cart.total.value() == 5.5);
		Assertions.assertFalse(cart.addFood(new FoodName("Baklava"), new FoodPrice(5.5)));
		Assertions.assertTrue(cart.getFoodItems().size() == 1);
		cart.addFood(new FoodName("chocolate"), new FoodPrice(4.5));
		Assertions.assertTrue(cart.getFoodItems().size() == 2);
	}
	
	@Test
	public void testIncrementQuantity() {
		cart.addFood(new FoodName("baklava"), new FoodPrice(5.5));
		cart.incrementQuantity(new FoodName("Baklava"));
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 2);
		Assertions.assertTrue(cart.total.value() == 11);
		
		cart.incrementQuantity(new FoodName("chocolate"));
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 2);
		Assertions.assertTrue(cart.total.value() == 11);
	}
	
	@Test
	public void testRemoveFoodFromCart() {
		FoodName food = new FoodName("baklava");
		FoodPrice price = new FoodPrice(5.5);
		cart.addFood(food, price);
		cart.removeFood(food);
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 0);
	}
	
	@Test
	public void shouldDecrementQuantity() {
		cart.addFood(new FoodName("baklava"), new FoodPrice(5.5));
		cart.incrementQuantity(new FoodName("baklava"));
		cart.removeFood(new FoodName("Baklava"));
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 1);	
		cart.removeFood(new FoodName("Baklava"));
		Assertions.assertTrue(cart.getTotalNumberOfFoods() == 0);
	}
	
	
}
