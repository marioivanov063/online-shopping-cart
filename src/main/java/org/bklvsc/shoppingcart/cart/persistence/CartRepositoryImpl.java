package org.bklvsc.shoppingcart.cart.persistence;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.cart.application.ports.out.CartRepository;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.CartTotalUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodAddedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.FoodRemovedEvent;
import org.bklvsc.shoppingcart.cart.domain.domain_events.CartEvent.QuantityUpdatedEvent;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainBehaviour;
import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.bklvsc.shoppingcart.cart.domain.value_objects.CartId;
import org.bklvsc.shoppingcart.cart.domain.value_objects.FoodItem;
import org.bklvsc.shoppingcart.catalog.domain.entities.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.commons.domain.DomainEvent;
import org.bklvsc.shoppingcart.commons.valueobjects.FoodName;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CartRepositoryImpl implements CartRepository{
	private final RedisTemplate<String, String> template;
	
	public CartRepositoryImpl(RedisTemplate<String, String> template) {
		this.template = template;
	}

	@Override
	public CartDomainBehaviour save(CartDomainBehaviour cartDomainBehaviour) {
		cartDomainBehaviour.getDomainEvents().forEach((event) -> {
			switch(event) {
				case FoodAddedEvent e -> {
					addFood(e);
					break;
				}
				case QuantityUpdatedEvent e -> {
					updateQuantity(e);
					break;
				}
				case FoodRemovedEvent e -> {
					removeFood(e);
					return;
				}
				case CartTotalUpdatedEvent e -> {
					updateCartTotal(e);
					break;
				}
			}
		});
		return this.getCart(cartDomainBehaviour.getModel().getUserId());
	}

	/*private void createCart(CartCreatedEvent e) {
		initTotal(e.userId());
	}*/
	
	private void addFood(FoodAddedEvent event) {
		String foodItemHash = foodItemHash(event.userId(), event.foodName());
		template.opsForHash().put(foodItemHash, "price", String.valueOf(event.price()));
		template.opsForHash().put(foodItemHash, "quantity", String.valueOf(1));
		template.opsForHash().put(cartHash(event.userId()), "foodItem-" + event.foodName(), foodItemHash);
	}
	
	private void updateQuantity(QuantityUpdatedEvent event) {
		String foodItemHash = foodItemHash(event.userId(), event.food());
		template.opsForHash().put(foodItemHash, "quantity", String.valueOf(event.currentQuantity()));
	}
	
	private void updateCartTotal(CartTotalUpdatedEvent event) {
		template.opsForValue().set(cartHash(event.userId()) + ":total", String.valueOf(event.cartTotal()));
	}
	
	private String foodItemHash(UUID userId, String foodName) {
		String foodItemsHash = "foodItems:" + userId.toString() 
				+ ":" + foodName;
		return foodItemsHash;
	}
	
	private String cartHash(UUID userId) {
		String cartHash = "cart:" + userId.toString();
		return cartHash;
	}
	
	
	private void removeFood(FoodRemovedEvent event) {
		template.delete(foodItemHash(event.userId(), event.food()));
		template.opsForHash().delete(cartHash(event.userId()), "foodItem-" + event.food());
	}

	@Override
	public CartDomainBehaviour getCart(UUID userId) {
		if(!template.hasKey(cartHash(userId)))
			return null;
		Map<FoodName, FoodItem> cart = deserializeCart(userId);
		double total = Double.parseDouble(template.opsForValue().get(cartHash(userId) + ":total"));
		CartDomainBehaviour cartDomainBehaviour = new CartDomainBehaviour(cart, total, userId.toString());
		return cartDomainBehaviour;
	}
	
	private Map<FoodName, FoodItem> deserializeCart(UUID userId) {
		Map<FoodName, FoodItem> cart = scanHashForPattern(cartHash(userId), "foodItem-*")
				.entrySet().stream()
				.collect(Collectors.toMap(
						entry -> {
							String name = entry.getKey().toString().split("-")[1];
							FoodName food = new FoodName(name);
							return food;
						}, 
						entry -> {
							Map<String, String> item = template.opsForHash()
									.entries(entry.getValue().toString())
									.entrySet().stream()
									.collect(Collectors.toMap(
											e -> e.getKey().toString(),
											e -> e.getValue().toString()
										));
							
							FoodItem foodItem = new FoodItem(
									Integer.parseInt(item.get("quantity")), 
									Double.parseDouble(item.get("price")
								));
							return foodItem;	
						}));
		return cart;
	}
	
	public Map<Object, Object> scanHashForPattern(String hashKey, String pattern) {
        Map<Object, Object> resultMap = new HashMap<>();

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(pattern)
                .count(100)
                .build();
        
        try (Cursor<Map.Entry<Object, Object>> cursor = template.opsForHash().scan(hashKey, scanOptions)) {
        	cursor.forEachRemaining(entry -> resultMap.put(entry.getKey(), entry.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return resultMap;
    }
	
	/*private FoodItem deserializeFoodItem(Map.Entry<Object, Object>) {
		Map<String, String> item = template.opsForHash()
				.entries(entry.getValue().toString())
				.entrySet().stream()
				.collect(Collectors.toMap(
						e -> e.getKey().toString(),
						e -> e.getValue().toString()
					));
		
		FoodItem foodItem = new FoodItem(
				Integer.parseInt(item.get("quantity")), 
				Double.parseDouble(item.get("price")
			));
		return foodItem;
	}*/
	

	/*private void updateCartTotal(CartTotalUpdatedEvent event) {
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
	public Optional<CartDomainBehaviour> getCart(String cartId) {
		String sqlTotal = "SELECT total, user_id FROM cart WHERE cart_id = ?";
		List<Double> totalList = this.template.query(
				sqlTotal, 
				(rs, rn) -> rs.getDouble(1),
				Utils.uuidToBytes(cartId)
			);
		if(totalList.isEmpty())
	    	return Optional.empty();
		double total) = totalList.getFirst();
		String sql = "SELECT food_name, food_price, quantity FROM cart_item WHERE cart_id = ?";
	    List<FoodItem> foodItems = this.template.query(
				sql, 
				(rs, rn) -> new FoodItem(rs.getString(1), rs.getInt(3), rs.getDouble(2)),
				Utils.uuidToBytes(cartId.getId())
		);
	}*/
}
