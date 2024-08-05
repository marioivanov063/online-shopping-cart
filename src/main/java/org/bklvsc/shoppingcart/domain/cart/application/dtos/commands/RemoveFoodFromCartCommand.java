package org.bklvsc.shoppingcart.domain.cart.application.dtos.commands;

import org.bklvsc.shoppingcart.domain.port.in.commands.Command;

public record RemoveFoodFromCartCommand(String foodName, String userId) implements Command{

}
