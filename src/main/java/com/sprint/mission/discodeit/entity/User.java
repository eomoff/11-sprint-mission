package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
public class User implements Serializable{
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt; // 언제 만들어졌는지 파악
    private Instant updatedAt; // 언제 마지막으로 수정됐는지 파악

    // User 필드
    private String userName;
    private String email;
    private String password;
    private UUID profileId;

    // 생성자
    public User(String userName, String email, String password, UUID profileId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profileId = profileId;
    }

    // update
    public void update(String newName, String newEmail, String newPassword, UUID newProfileId) {
        boolean anyValueUpdated = false;
        if (newName != null && !newName.equals(this.userName)) {
            this.userName = newName;
            anyValueUpdated = true;
        }
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
            anyValueUpdated = true;
        }
        if (newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
            anyValueUpdated = true;
        }
        if (newProfileId != null && !newProfileId.equals(this.profileId)) {
            this.profileId = newProfileId;
            anyValueUpdated = true;
        }
        if (anyValueUpdated) {
            this.updatedAt = Instant.now();
        }
    }
}
