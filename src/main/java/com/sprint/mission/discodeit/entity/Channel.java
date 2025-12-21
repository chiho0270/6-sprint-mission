package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Channel {
    UUID id;
    Long createdAt;
    Long updatedAt;
    String name;
    String description;
    String type;
    String role;

    public Channel(
            // id, createdAt, updatedAt을 제외한 필드는 생성자의 파라미터를 통해 초기화
            String name,
            String description,
            String type,
            String role
    ) {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        this.name = name;
        this.description = description;
        this.type = type;
        this.role = role;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getRole() {
        return role;
    }

    public void update() {
        updatedAt = System.currentTimeMillis();
    }
}
