package org.bklvsc.shoppingcart.adapters.out.write;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bklvsc.shoppingcart.adapters.out.common.Users;
import org.bklvsc.shoppingcart.domain.port.out.write.UserWriteRepository;
import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;

@Repository
class UserWriteRepositoryImpl implements UserWriteRepository{
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public User saveUser(User user) {
		Users.users.remove(user);
		Users.users.add(user);
		return user;
	}
	
}
