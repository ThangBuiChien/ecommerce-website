package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.User.AddUserRequestDTO;
import com.devchangetheworld.ewebsite.dto.User.UserResponseDTO;

public interface UserService {

    UserResponseDTO getById(Long userId);
    UserResponseDTO createUser(AddUserRequestDTO addUserRequestDTO);
}
