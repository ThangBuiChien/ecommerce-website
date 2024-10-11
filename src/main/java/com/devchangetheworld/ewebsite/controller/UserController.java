package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.request.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.request.AddUserRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ApiResponse;
import com.devchangetheworld.ewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("User Get By Id successfully")
                .result(userService.getById(id))
                .build());
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addUser(@RequestBody AddUserRequestDTO addUserRequestDTO){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("User created successfully")
                .result(userService.createUser(addUserRequestDTO))
                .build());
    }

}
