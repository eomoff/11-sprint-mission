package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
// 이미지, 문서 등 파일 데이터 저장
public class BinaryContent implements Serializable {
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt;
    // 개별 필드
    private String fileName;    // 파일명
    private Long size;          // 파일 크기
    private String contentType; // 확장자
    private byte[] bytes;       // 실제 파일 데이터
    // 생성자
    public BinaryContent(String fileName, Long size, String contentType, byte[] bytes) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
    }
}
