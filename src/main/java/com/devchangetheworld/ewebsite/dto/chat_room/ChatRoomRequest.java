package com.devchangetheworld.ewebsite.dto.chat_room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomRequest {
    private String roomName;
    private List<Long> userIds;
}
