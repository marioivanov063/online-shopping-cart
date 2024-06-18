package org.bklvsc.shoppingcart.adapters.out;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.port.out.UserRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;
import org.mockito.ArgumentMatchers;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	static Set<User> users = new HashSet<>();
	

	@Override
	public User saveUser(User user) {
		users.remove(user);
		users.add(user);
		return user;
	}
	
	@Override
	public Optional<User> getUser(UserId userId) {
		return users.stream()
			.filter(user -> user.getUserId().equals(userId))
			.findAny();
	}

	@Override
	public Collection<User> getAllUsers() {
		return users;
	}
}
