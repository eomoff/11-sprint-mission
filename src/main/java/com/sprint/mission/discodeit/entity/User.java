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
    private String name;

    // 생성자
    public User(String name) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.name = name;
    }

    // update
    public void update(String newName) {
        boolean anyValueUpdated = false;
        if (newName != null && !newName.equals(this.name)) {
            this.name = newName;
            anyValueUpdated = true;
        }
        if (anyValueUpdated) {
            this.updatedAt = Instant.now();
        }
    }
}
