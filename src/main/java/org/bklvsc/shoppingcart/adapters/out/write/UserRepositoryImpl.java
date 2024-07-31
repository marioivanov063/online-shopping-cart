package org.bklvsc.shoppingcart.adapters.out.write;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bklvsc.shoppingcart.Utils;
import org.bklvsc.shoppingcart.domain.port.out.write.UserRepository;
import org.bklvsc.shoppingcart.domain.user.UserDomainBehaviour;
import org.bklvsc.shoppingcart.domain.user.UserDomainModel;
import org.bklvsc.shoppingcart.domain.user.UserEvent;
import org.bklvsc.shoppingcart.domain.user.UserEvent.CartAssignedToUserEvent;
import org.bklvsc.shoppingcart.domain.user.UserEvent.CartUnassignedFromUserEvent;
import org.bklvsc.shoppingcart.domain.user.UserEvent.UserCreatedEvent;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	private JdbcTemplate template;	
	
	public UserRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void save(UserDomainBehaviour user) {
		user.getDomainEvents().forEach((event) -> {
			switch(event) {
				case CartAssignedToUserEvent e -> {
					assignCartToUser(e);
					return;
				}
				case UserCreatedEvent e -> {
					createUser(e);
					return;
				}
				case CartUnassignedFromUserEvent e -> {
					deleteUsersCart(e);
					return;
				}
			}
		});
	}
	
	private void createUser(UserCreatedEvent event) {
		byte[] userId = Utils.uuidToBytes(event.user().getId());
		String username = event.userName().username();
		String sql = "INSERT INTO customer (customer_id, customer_name) VALUES (?, ?)";
		this.template.update(sql, userId, username);
	}

	private void assignCartToUser(CartAssignedToUserEvent event) {
		byte[] cartId = Utils.uuidToBytes(event.cartId().getId());
		byte[] userId = Utils.uuidToBytes(event.userId().getId());
		String sql = "INSERT INTO cart (cart_id, user_id, total) VALUES (?, ?, ?)";
		this.template.update(sql, cartId, userId, 0);
	}
	
	private void deleteUsersCart(CartUnassignedFromUserEvent event) {
		UUID userId = event.userId().getId();
		String sql = "DELETE FROM cart WHERE user_id = ?";
		this.template.update(sql, Utils.uuidToBytes(userId));
	}

	@Override
	public Optional<UserDomainBehaviour> getUser(UserId userId) {
		String sql = """	
			SELECT customer_id, customer_name, cart_id 
			FROM customer 
			LEFT JOIN cart ON customer_id = user_id 
			WHERE customer_id = ?
			""";
		List<UserDomainBehaviour> user = this.template.query(
				sql, 
				(rs, rn) -> {
					String id = Utils.bytesToUuid(rs.getBytes(1)).toString();
					String cartId = rs.getBytes(3) != null ? 
							Utils.bytesToUuid(rs.getBytes(3)).toString() : null;
					return new UserDomainBehaviour(id, rs.getString(2), cartId);
				},
				Utils.uuidToBytes(userId.getId())
		);
		return user.isEmpty() ? Optional.empty() : 
			Optional.of(user.get(0));  
	}

	@Override
	public Collection<UserDomainBehaviour> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
