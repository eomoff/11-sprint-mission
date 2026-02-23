package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {
    // 공통 필드
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    // Message 필드
    private String content;

    // 생성자
    public Message(String content) {
        long now = System.currentTimeMillis();
        this.id = UUID.randomUUID();
        this.createdAt = now;
        this.updatedAt = now;
        this.content = content;
    }

    // getter 모음
    public UUID getId() { return id; }
    public Long getCreatedAt() { return createdAt; }
    public Long getUpdatedAt() { return updatedAt; }
    public String getContent() { return content; }

    // update
    public void update(String content) {
        this.content = content;
        this.updatedAt = System.currentTimeMillis();
    }
}
