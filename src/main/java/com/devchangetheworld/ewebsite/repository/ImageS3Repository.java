package com.devchangetheworld.ewebsite.repository;

import com.devchangetheworld.ewebsite.entities.ImageS3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageS3Repository extends JpaRepository<ImageS3, Long> {
}
