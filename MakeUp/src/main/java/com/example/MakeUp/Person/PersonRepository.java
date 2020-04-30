package com.example.MakeUp.Person;

import com.example.MakeUp.Order.Order;
import com.example.MakeUp.Product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findById(Long id);
}
