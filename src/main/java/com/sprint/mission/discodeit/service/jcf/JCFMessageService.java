package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import java.util.*;
import java.util.stream.Collectors;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data;

    public JCFMessageService() {
        this.data = new HashMap<>();
    }

    @Override
    public Message create(String content) {
        Message message = new Message(content);
        data.put(message.getId(), message);
        return message;
    }

    @Override
    public Message find(UUID id) {
        return data.get(id);
    }

    @Override
    public List<Message> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }


    @Override
    public Message update(UUID id, String content) {
        Message message = data.get(id);
        if (message != null) {
            message.update(content);
        }
        return message;
    }

    @Override
    public void delete(UUID id) {
        data.remove(id);
    }
}
