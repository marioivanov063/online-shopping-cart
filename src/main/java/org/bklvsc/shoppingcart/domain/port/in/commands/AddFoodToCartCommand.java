package org.bklvsc.shoppingcart.domain.port.in.commands;

public record AddFoodToCartCommand(String foodName, String userId) implements Command{}
