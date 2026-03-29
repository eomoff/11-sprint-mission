package com.sprint.mission.discodeit.dto.request;

// 공개 채널 생성
public record PublicChannelCreateRequest(
        String name,
        String description
){
}
