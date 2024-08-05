package org.bklvsc.shoppingcart.domain.commons;

import java.util.List;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.commons.FoodRepository;
import org.bklvsc.shoppingcart.domain.food.FoodDomainBehaviour;
import org.bklvsc.shoppingcart.domain.food.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.FoodCreatedEvent;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.StockDecrementedEvent;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.StockIncrementedEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
	private JdbcTemplate template;
	
	public FoodRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	
	@Override
	public void save(FoodDomainBehaviour food) {
		food.getDomainEvents().forEach(event -> {
			switch(event) {
				case FoodCreatedEvent e -> {
					createFood(e);
					return;
				}
				case StockIncrementedEvent e -> {}
				case StockDecrementedEvent e -> {}
			}
		});
		
	}
	
	private void createFood(FoodCreatedEvent event) {
		String foodName = event.foodName().name();
		double foodPrice = event.price().value();
		String sql = "INSERT INTO food (food_name, food_price, description) VALUES (?,?,?)";
		this.template.update(sql, foodName, foodPrice, "something");
	}

	@Override
	public void remove(FoodName foodName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<FoodDomainBehaviour> getFood(FoodName food) {
		String sql = "SELECT * FROM food WHERE food_name = ?";
		List<FoodDomainBehaviour> foodDomainBehaviour = this.template.query(
				sql, 
				(rs, rn) -> new FoodDomainBehaviour(rs.getString(1), rs.getDouble(2)),
				food.name()
			);
		return foodDomainBehaviour.isEmpty() ? Optional.empty() : 
			Optional.of(foodDomainBehaviour.get(0));  
	}
}
