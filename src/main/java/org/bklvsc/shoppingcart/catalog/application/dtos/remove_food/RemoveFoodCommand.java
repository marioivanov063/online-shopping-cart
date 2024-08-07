package org.bklvsc.shoppingcart.catalog.application.dtos.remove_food;

import org.bklvsc.shoppingcart.commons.application.Command;

public record RemoveFoodCommand(String foodName) implements Command{

}
