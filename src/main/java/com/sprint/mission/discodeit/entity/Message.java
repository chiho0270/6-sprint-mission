package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {
    UUID id;
    Long createdAt;
    Long updatedAt;
    String content;

    public Message(
            String content
    ) {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void update() {
        updatedAt = System.currentTimeMillis();
    }
}
