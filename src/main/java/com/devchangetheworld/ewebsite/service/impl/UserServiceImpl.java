package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.request.AddUserRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.UserResponseDTO;
import com.devchangetheworld.ewebsite.entities.User;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoUserMapper;
import com.devchangetheworld.ewebsite.repository.UserRepository;
import com.devchangetheworld.ewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AutoUserMapper autoUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO getById(Long userId) {
        return userRepository.findById(userId).map(autoUserMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    }

    @Override
    public UserResponseDTO createUser(AddUserRequestDTO addUserRequestDTO) {
        User newUser = new User();
        newUser.setFirstName(addUserRequestDTO.getFirstName());
        newUser.setLastName(addUserRequestDTO.getLastName());
        newUser.setEmail(addUserRequestDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(addUserRequestDTO.getPassword()));
//        User newUser = autoUserMapper.toEntity(addUserRequestDTO);
        return autoUserMapper.toResponse(userRepository.save(newUser));
    }
}
