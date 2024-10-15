package com.devchangetheworld.ewebsite.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRoomRequest {
    private Long roomId;
    private Long userId;

}
