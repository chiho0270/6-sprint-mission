package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {
    UUID id;
    Long createdAt;
    Long updatedAt;

    public User(
            // id, createdAt, updatedAt을 제외한 필드는 생성자의 파라미터를 통해 초기화
    ) {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
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

    public void update() {
        updatedAt = System.currentTimeMillis();
    }
}
