package org.bklvsc.shoppingcart.domain.port.in.commands;

public record RemoveFoodFromCartCommand(String foodName, String userId) implements Command{

}
