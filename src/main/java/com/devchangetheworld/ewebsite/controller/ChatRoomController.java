package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.request.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.request.ChatRoomRequest;
import com.devchangetheworld.ewebsite.dto.response.ApiResponse;
import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.repository.ChatRoomRepository;
import com.devchangetheworld.ewebsite.service.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

}