package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import java.util.*;
import java.util.stream.Collectors;

public class JCFUserService implements UserService {
    private final Map<UUID, User> data;

    public JCFUserService() {
        this.data = new HashMap<>();
    }

    @Override
    public User create(String name) {
        User user = new User(name);
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public User find(UUID id) {
        return data.get(id);
    }

    @Override
    public User update(UUID id, String name) {
        User user = find(id);
        if (user != null) {
            user.update(name);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }


    @Override
    public void delete(UUID id) {
        data.remove(id);
    }
}
