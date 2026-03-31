package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.request.ReadStatusCreateRequest;
import com.sprint.mission.discodeit.dto.request.ReadStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/readStatuses")
public class ReadStatusController {

    private final ReadStatusService readStatusService;

    @PostMapping
    public ResponseEntity<ReadStatus> create(@RequestBody ReadStatusCreateRequest request) {
        ReadStatus readStatus = readStatusService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(readStatus);
    }

    @GetMapping("/{readStatusId}")
    public ResponseEntity<ReadStatus> find(@PathVariable UUID readStatusId) {
        ReadStatus readStatus = readStatusService.find(readStatusId);
        return ResponseEntity.ok(readStatus);
    }

    @GetMapping
    public ResponseEntity<List<ReadStatus>> findAllByUserId(@RequestParam UUID userId) {
        List<ReadStatus> readStatuses = readStatusService.findAllByUserId(userId);
        return ResponseEntity.ok(readStatuses);
    }

    @PutMapping("/{readStatusId}")
    public ResponseEntity<ReadStatus> update(
            @PathVariable UUID readStatusId,
            @RequestBody ReadStatusUpdateRequest request
    ) {
        ReadStatus updatedReadStatus = readStatusService.update(readStatusId, request);
        return ResponseEntity.ok(updatedReadStatus);
    }

    @DeleteMapping("/{readStatusId}")
    public ResponseEntity<Void> delete(@PathVariable UUID readStatusId) {
        readStatusService.delete(readStatusId);
        return ResponseEntity.noContent().build();
    }
}
