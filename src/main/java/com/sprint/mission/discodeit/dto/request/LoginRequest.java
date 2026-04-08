package com.sprint.mission.discodeit.dto.request;

// 로그인
public record LoginRequest(
        String userName,
        String password
) {
}
