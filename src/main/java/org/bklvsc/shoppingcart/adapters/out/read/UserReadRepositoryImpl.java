package org.bklvsc.shoppingcart.adapters.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.adapters.out.common.Users;
import org.bklvsc.shoppingcart.domain.port.out.read.UserReadRepository;
import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class UserReadRepositoryImpl implements UserReadRepository{
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Optional<User> getUser(UserId userId) {
		return Users.users.stream()
			.filter(user -> user.getUserId().equals(userId))
			.findAny();
	}

	@Override
	public Collection<User> getAllUsers() {
		return Users.users;
	}
}
