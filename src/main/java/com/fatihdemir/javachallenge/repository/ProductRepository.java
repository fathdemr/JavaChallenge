package com.fatihdemir.javachallenge.repository;

import com.fatihdemir.javachallenge.dto.product.DtoProduct;
import com.fatihdemir.javachallenge.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @NonNull
    Optional<Product> findById(@NonNull UUID id);

    @Query(value = "select * from products", nativeQuery = true)
    List<Product> findAllStudents();
}
