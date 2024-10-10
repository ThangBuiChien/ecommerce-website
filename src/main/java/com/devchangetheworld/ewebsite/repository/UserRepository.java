package com.devchangetheworld.ewebsite.repository;

import com.devchangetheworld.ewebsite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
