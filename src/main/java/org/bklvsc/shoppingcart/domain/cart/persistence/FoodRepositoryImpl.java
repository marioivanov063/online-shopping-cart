package org.bklvsc.shoppingcart.domain.cart.persistence;

import java.util.List;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.cart.application.ports.out.FoodRepository;
import org.bklvsc.shoppingcart.domain.cart.domain_core.entities.FoodDomainModel;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.FoodCreatedEvent;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.StockDecrementedEvent;
import org.bklvsc.shoppingcart.domain.food.FoodEvent.StockIncrementedEvent;
import org.bklvsc.shoppingcart.domain.food.valueobjects.FoodName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
	private JdbcTemplate template;
	
	public FoodRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Optional<FoodDomainModel> getFood(String food) {
		String sql = "SELECT * FROM food WHERE food_name = ?";
		List<FoodDomainModel> foodModel = this.template.query(
				sql, 
				(rs, rn) -> new FoodDomainModel(rs.getString(1), rs.getDouble(2)),
				food
			);
		return foodModel.isEmpty() ? Optional.empty() : 
			Optional.of(foodModel.get(0));  
	}
}
