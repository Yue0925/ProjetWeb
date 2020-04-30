package com.example.MakeUp.ProductInOrder;

import com.example.MakeUp.Product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductInOrderRepository extends CrudRepository<ProductInOrder, Long> {
    Optional<ProductInOrder> findById(Long id);
}
