package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Long is wrapper for primitive type long
public interface ProductRepository extends JpaRepository<Product, Long>{
}
