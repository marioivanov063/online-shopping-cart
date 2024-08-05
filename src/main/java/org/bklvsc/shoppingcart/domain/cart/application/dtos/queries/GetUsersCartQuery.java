package org.bklvsc.shoppingcart.domain.cart.application.dtos.queries;

import org.bklvsc.shoppingcart.domain.port.in.queries.Query;

public record GetUsersCartQuery(String userId) implements Query{}
