package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class Channel implements Serializable{

    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    // channel 필드
    private String name;

    // 생성자
    public Channel(String name) {
        long now = System.currentTimeMillis();
        this.id = UUID.randomUUID();
        this.createdAt = now;
        this.updatedAt = now;
        this.name = name;
    }

    // update
    public void update(String name) {
        this.name = name;
        this.updatedAt = System.currentTimeMillis();
    }
}
