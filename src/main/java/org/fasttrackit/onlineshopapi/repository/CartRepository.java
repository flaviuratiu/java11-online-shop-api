package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
