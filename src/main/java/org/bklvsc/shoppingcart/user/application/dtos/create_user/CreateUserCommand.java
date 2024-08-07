package org.bklvsc.shoppingcart.user.application.dtos.create_user;

import org.bklvsc.shoppingcart.commons.application.Command;

public record CreateUserCommand(String name) implements Command{

}
