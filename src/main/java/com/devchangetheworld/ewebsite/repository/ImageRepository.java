package com.devchangetheworld.ewebsite.repository;

import com.devchangetheworld.ewebsite.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
