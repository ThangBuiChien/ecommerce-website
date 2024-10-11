package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.request.AddUserRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.UserResponseDTO;
import com.devchangetheworld.ewebsite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserService {

    UserResponseDTO getById(Long userId);
    UserResponseDTO createUser(AddUserRequestDTO addUserRequestDTO);
}
