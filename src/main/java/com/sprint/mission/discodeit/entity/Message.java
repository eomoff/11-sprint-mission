package com.sprint.mission.discodeit.entity;

import lombok.Getter

import java.io.Serializable;
import java.util.UUID;

@Getter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    // Message 필드
    private String content;
    private UUID channelId;
    private UUID authorId;

    // 생성자
    public Message(String content, UUID channelId, UUID authorId) {
        long now = System.currentTimeMillis();
        this.id = UUID.randomUUID();
        this.createdAt = now;
        this.updatedAt = now;
        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
    }

    // update
    public void update(String content) {
        this.content = content;
        this.updatedAt = System.currentTimeMillis();
    }
}
