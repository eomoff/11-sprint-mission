package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
public class Channel implements Serializable{
    // 직렬화
    private static final long serialVersionUID = 1L;
    // 공통 필드
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;

    // channel 필드
    private ChannelType type;
    private String name;
    private String description;

    // 생성자
    public Channel(ChannelType type, String name, String description) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.type = type;
        this.name = name;
        this.description = description;
    }

    // update
    public void update(String newName, String newDescription) {
        boolean anyValueUpdate = false;
        if (newName != null && !newName.equals(this.name)) {
            this.name = newName;
            anyValueUpdate = true;
        }
        if (newDescription != null && !newDescription.equals(this.description)) {
            this.description = newDescription;
            anyValueUpdate = true;
        }
        if (anyValueUpdate) {
            this.updatedAt = Instant.now();
        }
    }
}
