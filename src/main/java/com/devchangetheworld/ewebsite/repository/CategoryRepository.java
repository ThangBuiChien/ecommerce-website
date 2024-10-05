package com.devchangetheworld.ewebsite.repository;

import com.devchangetheworld.ewebsite.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
