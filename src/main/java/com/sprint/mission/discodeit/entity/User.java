package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {
    UUID id;
    Long createdAt;
    Long updatedAt;
    String username;
    String password;
    String email;
    String status;
    String role;

    public User(
            // id, createdAt, updatedAt을 제외한 필드는 생성자의 파라미터를 통해 초기화
            String username,
            String password,
            String email,
            String status,
            String role
    ) {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public void update() {
        this.updatedAt = System.currentTimeMillis();
    }

    public void updateProfile(String username, String password, String email, String status, String role) {
        validateUsername(username);

        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
        this.update();
    }

    private void validateUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
    }
}
