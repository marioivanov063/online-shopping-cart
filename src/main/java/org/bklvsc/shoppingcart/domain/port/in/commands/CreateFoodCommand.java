package org.bklvsc.shoppingcart.domain.port.in.commands;

public record CreateFoodCommand(String foodName, double price, int quantity) implements Command{

}
