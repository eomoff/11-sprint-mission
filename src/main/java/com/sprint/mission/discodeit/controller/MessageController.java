package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.request.BinaryContentCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageUpdateRequest;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message> create(
            @RequestPart("messageCreateRequest") MessageCreateRequest messageCreateRequest,
            @RequestPart(value = "attachments", required = false) List<MultipartFile> attachments
    ) throws IOException {
        List<BinaryContentCreateRequest> binaryContentCreateRequests = new ArrayList<>();
        if (attachments != null) {
            for (MultipartFile attachment : attachments) {
                binaryContentCreateRequests.add(new BinaryContentCreateRequest(
                        attachment.getOriginalFilename(),
                        attachment.getContentType(),
                        attachment.getBytes()
                ));
            }
        }

        Message message = messageService.create(messageCreateRequest, binaryContentCreateRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> find(@PathVariable UUID messageId) {
        Message message = messageService.find(messageId);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<Message>> findAllByChannelId(@RequestParam UUID channelId) {
        List<Message> messages = messageService.findAllByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<Message> update(
            @PathVariable UUID messageId,
            @RequestBody MessageUpdateRequest request
    ) {
        Message updatedMessage = messageService.update(messageId, request);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> delete(@PathVariable UUID messageId) {
        messageService.delete(messageId);
        return ResponseEntity.noContent().build();
    }
}
