package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductName(String productName);

    Optional<Product> findProductById(Long productId);


}