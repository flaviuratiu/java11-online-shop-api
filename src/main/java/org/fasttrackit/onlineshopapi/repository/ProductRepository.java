package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Long is wrapper for primitive type long
public interface ProductRepository extends JpaRepository<Product, Long> {

    // queries derived from method names
    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(
            String partialName, int minQuantity, Pageable pageable);

    // named query using JPQL (Java Persistence Query Language)
//    @Query("SELECT product FROM Product product WHERE name LIKE '%?1'")
    // named query using native sql
    @Query(value = "SELECT * FROM product WHERE name LIKE '%?1'", nativeQuery = true)
    Page<Product> findByPartialName(String partialName, Pageable pageable);

}
