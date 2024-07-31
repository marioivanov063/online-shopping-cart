package org.bklvsc.shoppingcart.application.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bklvsc.shoppingcart.Utils;
import org.bklvsc.shoppingcart.domain.port.in.queries.GetUsersCartQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.QueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.CartDto;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodInCartDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetUsersCartQueryHandler implements QueryHandler<GetUsersCartQuery, CartDto>{
	private JdbcTemplate template;
	
	public GetUsersCartQueryHandler(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public CartDto handle(GetUsersCartQuery query) {
		List<FoodInCartDto> foods = new ArrayList<>();
		/*
		 * when a join is performed, for each row there will be duplicate
		 * columns 
		 */
		String[] replicatedFields = {"", "", ""};
		String sql = """ 
				SELECT cart.user_id, cart.cart_id, cart.total, cart_item.food_name, cart_item.quantity, cart_item.food_price 
				FROM cart 
				JOIN cart_item ON cart.cart_id = cart_item.cart_id 
				WHERE cart.user_id = ? 
			""";
		
		template.query(sql,
				(rs) -> {
					while(rs.next()) {
						//Set replicated fields only once 
						if(replicatedFields[0].isEmpty()) {
							replicatedFields[0] = Utils.bytesToUuid(rs.getBytes("cart.user_id")).toString();
							replicatedFields[1] = Utils.bytesToUuid(rs.getBytes("cart.cart_id")).toString();
							replicatedFields[2] = String.valueOf(rs.getDouble("cart.total"));
						}
						FoodInCartDto food = 
								new FoodInCartDto(
										rs.getString("cart_item.food_name"), 
										rs.getDouble("cart_item.food_price"), 
										rs.getInt("cart_item.quantity"));
						foods.add(food);
					}
					return null;
				},
				Utils.uuidToBytes(UUID.fromString(query.userId()))
			);
		
		/*
		 * if the userId is not present in the cart table 
		 * it means the cart was empty and deleted
		 */
		return !replicatedFields[0].isEmpty() ?
			 new CartDto(
					replicatedFields[0], 
					replicatedFields[1], 
					Double.parseDouble(replicatedFields[2]), 
					foods) 
			 : null;
	}
}
