package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Getter
// 사용자 온라인,오프라인 관리
public class UserStatus implements Serializable {
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    // 개별 필드
    private UUID userId;
    private Instant lastActiveAt; // 마지막 접속 시간

    // 생성자
    public UserStatus(UUID userId, Instant lastActiveAt) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.userId = userId;
        this.lastActiveAt = lastActiveAt;
    }

    // 수정
    public void update(Instant lastActiveAt) {
        boolean anyValueUpdate = false;
        if (lastActiveAt != null & lastActiveAt.equals(this.lastActiveAt)) {
            this.lastActiveAt = lastActiveAt;
            anyValueUpdate = true;
        }
        if (anyValueUpdate) {
            this.updatedAt = Instant.now();
        }
    }

    // 온라인 상태인지 판단
    public boolean isOnline() {
        // instantFiveMinutesAgo에다가 현재시각에서 5분을 뺀 값 할당
        Instant instantFiveMinutesAgo = Instant.now().minus(Duration.ofMinutes(5));

        // 마지막 접속 시간이 현재시간의 5분전 시간의 이후 시간이면 True
        return lastActiveAt.isAfter(instantFiveMinutesAgo);
    }
}
