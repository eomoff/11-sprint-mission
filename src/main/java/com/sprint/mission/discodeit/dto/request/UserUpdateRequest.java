package com.sprint.mission.discodeit.dto.request;

// 유저 정보 수정
public record UserUpdateRequest(
        String newUserName,
        String newEmail,
        String newPassword
) {
}
