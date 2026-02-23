package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Channel {
    // 공통 필드
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    // channel 필드
    private String name;

    // 생성자
    public Channel(String name) {
        long now = System.currentTimeMillis();
        this.id = UUID.randomUUID();
        this.createdAt = now;
        this.updatedAt = now;
        this.name = name;
    }

    // getter 모음
    public UUID getId() { return id; }
    public Long getCreatedAt() { return createdAt; }
    public Long getUpdatedAt() { return updatedAt; }
    public String getName() { return name; }

    // update
    public void update(String name) {
        this.name = name;
        this.updatedAt = System.currentTimeMillis();
    }
}
