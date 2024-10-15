package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.entities.ChatRoom;
import com.devchangetheworld.ewebsite.entities.User;
import org.apache.logging.log4j.message.Message;

import java.util.List;

public interface ChatRoomService {
    public ChatRoom createChatRoom(String roomName, List<Long> userIds);
    public ChatRoom findById(Long id);
    public void addUserToRoom(Long roomId, Long userID);
    public List<ChatMessage> getChatMessageHistory(Long chatRoomId);
    public void addMessageToChatRoom(ChatMessage chatMessage, Long chatRoomId);
    public List<Long> getAllUserIdInChatRoom(Long chatRoomId);

}
