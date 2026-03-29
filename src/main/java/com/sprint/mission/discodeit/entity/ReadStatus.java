package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
// 메세지 읽음 상태
public class ReadStatus implements Serializable {
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    // 개별 필드
    private UUID userId;        // 누구의 읽음 상태인지
    private UUID channelId;     // 어떤 채널에 대한 것인지
    private Instant lastReadAt; // 마지막으로 읽은 시간
    // 생성자
    public ReadStatus(UUID userId, UUID channelId, Instant lastReadAt) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.userId = userId;
        this.channelId = channelId;
        this.lastReadAt = lastReadAt;
    }
    // 수정
    public void update(Instant newLastReadAt) {
        boolean anyValueUpdate =  false;
        if (newLastReadAt != null && newLastReadAt.equals(this.lastReadAt)) {
            this.lastReadAt = newLastReadAt;
            anyValueUpdate = true;
        }
        if (anyValueUpdate) {
            this.updatedAt = Instant.now();
        }
    }

}
