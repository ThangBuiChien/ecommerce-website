package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.entities.ChatMessage;
import com.devchangetheworld.ewebsite.entities.ChatRoom;
import com.devchangetheworld.ewebsite.entities.User;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.repository.ChatRoomRepository;
import com.devchangetheworld.ewebsite.repository.UserRepository;
import com.devchangetheworld.ewebsite.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);

    @Override
    public ChatRoom createChatRoom(String roomName, List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            logger.warn("Not all user IDs were found");
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);
        chatRoom.addParticipants(users);
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public ChatRoom findById(Long id) {
        return chatRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("chatroom", "id", id));
    }

    @Override
    public void addUserToRoom(Long roomId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        ChatRoom room = findById(roomId);
        room.addParticipant(user);
        chatRoomRepository.save(room);

    }

    @Override
    public List<ChatMessage> getChatMessageHistory(Long chatRoomId) {
        ChatRoom chatRoom = findById(chatRoomId);
        return chatRoom.getMessages();
    }

    @Transactional
    @Override
    public void addMessageToChatRoom(ChatMessage chatMessage, Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("chatroom", "id", chatRoomId));

        chatRoom.addMessage(chatMessage);
    }

    @Override
    public List<Long> getAllUserIdInChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findById(chatRoomId);

        return chatRoom.getParticipants().stream()
                .map(User::getId)
                .toList();

    }


}
