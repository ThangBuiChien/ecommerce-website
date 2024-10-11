package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.request.AddUserRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.UserResponseDTO;
import com.devchangetheworld.ewebsite.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AutoOrderMapper.class, AutoCartMapper.class})
public interface AutoUserMapper {

    User toEntity(AddUserRequestDTO userRequestDTO);

    UserResponseDTO toResponse(User user);
}
