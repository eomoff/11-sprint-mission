package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.request.UserStatusCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userStatuses")
public class UserStatusController {

    private final UserStatusService userStatusService;

    @PostMapping
    public ResponseEntity<UserStatus> create(@RequestBody UserStatusCreateRequest request) {
        UserStatus userStatus = userStatusService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userStatus);
    }

    @GetMapping("/{userStatusId}")
    public ResponseEntity<UserStatus> find(@PathVariable UUID userStatusId) {
        UserStatus userStatus = userStatusService.find(userStatusId);
        return ResponseEntity.ok(userStatus);
    }

    @GetMapping
    public ResponseEntity<List<UserStatus>> findAll() {
        List<UserStatus> userStatuses = userStatusService.findAll();
        return ResponseEntity.ok(userStatuses);
    }

    @PutMapping("/{userStatusId}")
    public ResponseEntity<UserStatus> update(
            @PathVariable UUID userStatusId,
            @RequestBody UserStatusUpdateRequest request
    ) {
        UserStatus updatedUserStatus = userStatusService.update(userStatusId, request);
        return ResponseEntity.ok(updatedUserStatus);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserStatus> updateByUserId(
            @PathVariable UUID userId,
            @RequestBody UserStatusUpdateRequest request
    ) {
        UserStatus updatedUserStatus = userStatusService.updateByUserId(userId, request);
        return ResponseEntity.ok(updatedUserStatus);
    }

    @DeleteMapping("/{userStatusId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userStatusId) {
        userStatusService.delete(userStatusId);
        return ResponseEntity.noContent().build();
    }
}
