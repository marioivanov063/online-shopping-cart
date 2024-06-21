package org.bklvsc.shoppingcart.domain.port.out.read;

import java.util.Collection;
import java.util.Optional;

import org.bklvsc.shoppingcart.domain.user.User;
import org.bklvsc.shoppingcart.domain.user.valueobjects.UserId;

public interface UserReadRepository {
	public Optional<User> getUser(UserId userId);
	public Collection<User> getAllUsers();
}
