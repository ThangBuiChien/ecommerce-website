package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.entities.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    public ChatRoom createChatRoom(String roomName, List<Long> userIds);

    public ChatRoom findByIdToEntity(Long id);

    public void addUserToRoom(Long roomId, Long userID);

    public List<ChatMessage> getChatMessageHistory(Long chatRoomId);

    public void addMessageToChatRoom(ChatMessage chatMessage, Long chatRoomId);

    public void deleteMessageFromChatRoom(Long chatMessageId, Long chatRoomId);

    public List<Long> getAllUserIdInChatRoom(Long chatRoomId);

}
