package org.bklvsc.shoppingcart.adapters.out.write;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.bklvsc.shoppingcart.Utils;
import org.bklvsc.shoppingcart.domain.cart.CartDomainBehaviour;
import org.bklvsc.shoppingcart.domain.cart.CartDomainModel;
import org.bklvsc.shoppingcart.domain.cart.CartDomainModel.FoodItem;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.CartTotalUpdatedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.FoodAddedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.FoodRemovedEvent;
import org.bklvsc.shoppingcart.domain.cart.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.domain.cart.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.port.out.write.CartRepository;
import org.mockito.ArgumentMatchers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryImpl implements CartRepository{
	private JdbcTemplate template;
	
	public CartRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void save(CartDomainBehaviour cartDomainBehaviour) {
		cartDomainBehaviour.getDomainEvents().forEach((event) -> {
			switch(event) {
				case FoodAddedEvent e -> {
					addFood(e);
					return;
				}
				case QuantityUpdatedEvent e -> {
					updateQuantity(e);
					return;
				}
				case FoodRemovedEvent e -> {
					removeFood(e);
					return;
				}
				case CartTotalUpdatedEvent e -> {
					updateCartTotal(e);
					return;
				}
			}
		});
		
	}
	
	private void updateCartTotal(CartTotalUpdatedEvent event) {
		double total = event.cartTotal().value();
		UUID cartId = event.cartId().getId();
		String sql = "UPDATE cart SET total = ? WHERE cart_id = ?";
		this.template.update(sql, total, Utils.uuidToBytes(cartId));
	}

	private void removeFood(FoodRemovedEvent event) {
		byte[] cartId = Utils.uuidToBytes(event.cartId().getId());
		String foodName = event.food().name();
		String sql = "DELETE FROM cart_item WHERE cart_id = ? AND food_name = ?";
		this.template.update(sql, cartId, foodName);
	}

	private void addFood(FoodAddedEvent event) {
		byte[] cartId = Utils.uuidToBytes(event.cartId().getId());
		String foodName = event.food().name();
		double foodPrice = event.price().value();
		String sql = "INSERT INTO cart_item (cart_id, food_name, food_price, quantity) VALUES (?,?,?,?)";  
        this.template.update(sql, cartId, foodName, foodPrice, 1); 
	}
	
	private void updateQuantity(QuantityUpdatedEvent event) {
		byte[] cartId = Utils.uuidToBytes(event.cartId().getId());
		String foodName = event.food().name();
		int currentQuantity = event.currentQuantity().value();
		String sql = "UPDATE cart_item SET quantity = ? WHERE cart_id = ? AND food_name = ?";
		this.template.update(sql, currentQuantity, cartId, foodName);
	}

	@Override
	public Optional<CartDomainBehaviour> getCart(CartId cartId) {
		String sqlTotal = "SELECT total FROM cart WHERE cart_id = ?";
		List<Double> totalList = this.template.query(
				sqlTotal, 
				(rs, rn) -> rs.getDouble(1),
				Utils.uuidToBytes(cartId.getId())
			);
		if(totalList.isEmpty())
	    	return Optional.empty();
		double total = totalList.getFirst();
		String sql = "SELECT food_name, food_price, quantity FROM cart_item WHERE cart_id = ?";
	    List<FoodItem> foodItems = this.template.query(
				sql, 
				(rs, rn) -> new FoodItem(rs.getString(1), rs.getInt(3), rs.getDouble(2)),
				Utils.uuidToBytes(cartId.getId())
		);
	    /*if(foodItems.isEmpty())
	    	return Optional.empty();*/
		return Optional.of(
				new CartDomainBehaviour(
						cartId.toString(), 
						new HashSet<>(foodItems), 
						total
					)
				);
	}
}
