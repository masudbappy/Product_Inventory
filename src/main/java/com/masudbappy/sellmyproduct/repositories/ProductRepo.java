package com.masudbappy.sellmyproduct.repositories;

import com.masudbappy.sellmyproduct.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
