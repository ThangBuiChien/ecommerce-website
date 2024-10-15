package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.entities.ChatRoom;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.repository.ChatMessageRepository;
import com.devchangetheworld.ewebsite.repository.ChatRoomRepository;
import com.devchangetheworld.ewebsite.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {



    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomService chatRoomService;


    @MessageMapping("/chat.register/{roomId}")
    @SendTo("/topic/room/{roomId}")  // Use room ID dynamically
    public ChatMessage register(@DestinationVariable Long roomId, @Payload ChatMessage chatMessage,
                                SimpMessageHeaderAccessor headerAccessor) {
        // Add the username in WebSocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        ChatMessage newMessage = new ChatMessage();
        newMessage.setSender(chatMessage.getSender());
        newMessage.setType(chatMessage.getType());
        newMessage.setContent(chatMessage.getContent());

        chatRoomService.addMessageToChatRoom(newMessage, roomId);

        return chatMessage;  // Notify other users in the room about the new user
    }

    @MessageMapping("/chat.send/{roomId}")
    @SendTo("/topic/room/{roomId}")  // Send message to a specific room
    public ChatMessage sendMessage(@DestinationVariable Long roomId, @Payload ChatMessage chatMessage) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("chatroom", "id", roomId));
        ChatMessage newMessage = new ChatMessage();
        newMessage.setSender(chatMessage.getSender());
        newMessage.setType(chatMessage.getType());
        newMessage.setChatRoom(chatRoom);
        newMessage.setContent(chatMessage.getContent());

        chatRoomService.addMessageToChatRoom(newMessage, roomId);

        return chatMessage;  // Broadcast the message to the room
    }




}
