package org.bklvsc.shoppingcart.cart.application.dtos.queries;

import org.bklvsc.shoppingcart.commons.application.Query;

public record GetUsersCartQuery(String userId) implements Query{}
