package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    // Create
    User create(
            String username,
            String password,
            String email,
            String status,
            String role
    );

    // Read
    User findById(UUID id);
    User findByEmail(String email);
    List<User> findAll();
    List<User> findByRole(String role);
    List<User> findByStatus(String status);

    // Update
    User update(
            UUID id,
            String username,
            String password,
            String email,
            String status,
            String role
    );

    // Delete
    void delete(UUID id);
}
