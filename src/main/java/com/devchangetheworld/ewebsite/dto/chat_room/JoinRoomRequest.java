package com.devchangetheworld.ewebsite.dto.chat_room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRoomRequest {
    private Long roomId;
    private Long userId;

}
