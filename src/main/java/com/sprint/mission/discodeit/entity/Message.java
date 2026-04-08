package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class Message implements Serializable {
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;

    // Message 필드
    private String content;
    private UUID channelId;
    private UUID authorId;
    private List<UUID> attachmentIds; // 첨부파일ID

    // 생성자
    public Message(String content, UUID channelId, UUID authorId, List<UUID> attachmentIds) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
        this.attachmentIds = attachmentIds;
    }

    // update
    public void update(String newContent) {
        boolean anyValueUpdate = false;
        if (newContent != null && !newContent.equals(this.content)) {
            this.content = newContent;
            anyValueUpdate = true;
        }
        if (anyValueUpdate) {
            this.updatedAt = Instant.now();
        }
    }
}
