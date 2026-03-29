package com.sprint.mission.discodeit.dto.request;

// 회원가입
public record UserCreateRequest(
        String userName,
        String email,
        String password
) {
}
