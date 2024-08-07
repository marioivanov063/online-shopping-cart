package org.bklvsc.shoppingcart.cart.application.dtos.remove_from_cart;

import java.util.UUID;

import org.bklvsc.shoppingcart.commons.application.Command;

public record RemoveFoodFromCartCommand(String foodName, UUID userId) implements Command{

}
