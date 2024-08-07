package org.bklvsc.shoppingcart.cart.application.dtos.add_to_cart;

import java.util.UUID;

import org.bklvsc.shoppingcart.commons.application.Command;

public record AddFoodToCartCommand(String foodName, UUID userId) implements Command{}
