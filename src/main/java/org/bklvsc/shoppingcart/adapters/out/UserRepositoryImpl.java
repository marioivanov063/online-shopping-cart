package org.bklvsc.shoppingcart.adapters.out;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bklvsc.shoppingcart.domain.entities.Cart;
import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.port.out.UserRepository;
import org.bklvsc.shoppingcart.domain.valueobjects.CartId;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	static Set<User> users = new HashSet<>();
	

	@Override
	public User saveUser(User user) {	
		this.users.add(user);
		return user;
	}
	
	@Override
	public Optional<User> getUser(UserId userId) {
		return this.users.stream()
			.filter(user -> user.getUserId().equals(userId))
			.findAny();
	}
	
	
	
	
	
}
