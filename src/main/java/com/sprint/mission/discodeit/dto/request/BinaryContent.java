package com.sprint.mission.discodeit.dto.request;

public record BinaryContent(
        String fileName,
        String contentType,
        byte[] bytes
) {
}
