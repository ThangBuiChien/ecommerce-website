package com.devchangetheworld.ewebsite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "chat_room_participants",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> messages = new ArrayList<>();

    public void addMessage(ChatMessage chatMessage){
        this.messages.add(chatMessage);
        chatMessage.setChatRoom(this);
    }

    public void deleteMessage(ChatMessage chatMessage){
        this.messages.remove(chatMessage);
        chatMessage.setChatRoom(null);
    }

    public void addParticipant(User user) {
        this.participants.add(user);
        user.getChatRooms().add(this);
    }

    public void removeParticipant(User user) {
        this.participants.remove(user);
        user.getChatRooms().remove(this);
    }

    public void addParticipants(List<User> users) {
        for(User user : users){
            addParticipant(user);
        }
    }
}
