package org.bklvsc.shoppingcart.catalog.application.dtos.create_food;

import org.bklvsc.shoppingcart.commons.application.Command;

public record CreateFoodCommand(String foodName, double price) implements Command{

}
