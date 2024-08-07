package org.bklvsc.shoppingcart.cart.persistence;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.Utils;
import org.bklvsc.shoppingcart.cart.application.ports.out.UserRepository;
import org.bklvsc.shoppingcart.cart.domain.entities.UserDomainModel;
import org.bklvsc.shoppingcart.commons.valueobjects.UserId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	private JdbcTemplate template;	
	
	public UserRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}


	@Override
	public Optional<UserDomainModel> getUser(UUID userId) {
		String sql = """	
			SELECT customer_id, customer_name, cart_id 
			FROM customer 
			LEFT JOIN cart ON customer_id = user_id 
			WHERE customer_id = ?
			""";
		List<UserDomainModel> user = this.template.query(
				sql, 
				(rs, rn) -> {
					String id = Utils.bytesToUuid(rs.getBytes(1)).toString();
					String cartId = rs.getBytes(3) != null ? 
							Utils.bytesToUuid(rs.getBytes(3)).toString() : null;
					return new UserDomainModel(id, rs.getString(2), cartId);
				},
				Utils.uuidToBytes(userId)
		);
		return user.isEmpty() ? Optional.empty() : 
			Optional.of(user.get(0));  
	}
}
