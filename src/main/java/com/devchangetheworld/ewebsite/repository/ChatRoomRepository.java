package com.devchangetheworld.ewebsite.repository;

import com.devchangetheworld.ewebsite.entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
