package com.devchangetheworld.ewebsite.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomRequest {
    private String roomName;
    private List<Long> userIds;
}
