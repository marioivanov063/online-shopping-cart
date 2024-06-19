package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.entities.User;
import org.bklvsc.shoppingcart.domain.valueobjects.UserId;

public interface UserReadRepository {
	public Optional<User> getUser(UserId userId);
	public Collection<User> getAllUsers();
}
