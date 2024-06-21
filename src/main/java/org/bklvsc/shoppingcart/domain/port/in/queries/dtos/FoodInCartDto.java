package org.bklvsc.shoppingcart.domain.port.in.queries.dtos;

public record FoodInCartDto(String foodName, 
							double price, 
							int quantity,
							double total) {

}
