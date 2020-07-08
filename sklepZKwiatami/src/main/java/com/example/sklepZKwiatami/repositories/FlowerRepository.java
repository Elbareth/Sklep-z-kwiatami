package com.example.sklepZKwiatami.repositories;

import com.example.sklepZKwiatami.entity.Flower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends CrudRepository<Flower, Integer> {
    Optional<Flower> findByName(String name);
    List<Flower> findByQuantityGreaterThan(Integer quantity);
    List<Flower> findAllByOrderByPriceAsc();
    List<Flower> findAllByOrderByPriceDesc();
}
