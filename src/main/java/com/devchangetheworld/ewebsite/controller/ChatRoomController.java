package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.chat_room.ChatRoomRequest;
import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.service.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/chat_room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createChatRoom(@RequestBody @Valid ChatRoomRequest chatRoomRequest){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Chatroom created successfully")
                .result(chatRoomService.createChatRoom(chatRoomRequest.getRoomName(), chatRoomRequest.getUserIds()))
                .build());
    }

    @GetMapping("/history/{roomId}")
    public List<ChatMessage> getChatHistory(@PathVariable Long roomId) {
        return chatRoomService.getChatMessageHistory(roomId);
    }

    @GetMapping("participants/{roomId}")
    public List<Long> getParticipantsId(@PathVariable Long roomId) {
        return chatRoomService.getAllUserIdInChatRoom(roomId);
    }

    @DeleteMapping("room/{roomId}/message/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable Long roomId, @PathVariable Long messageId) {
         chatRoomService.deleteMessageFromChatRoom(messageId, roomId);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Deleted messages successfully")
                .build());
    }

}
